package com.goldenhour.repository

import android.content.Context
import android.graphics.Bitmap
import android.os.Looper
import com.goldenhour.model.AccidentLocation
import com.goldenhour.model.AmbulanceTelemetry
import com.goldenhour.model.EmergencySession
import com.goldenhour.model.HospitalInfo
import com.goldenhour.model.TriageData
import com.goldenhour.model.VictimRange
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton

private data class HospitalRecord(
    val name: String,
    val lat: Double,
    val lng: Double,
    val traumaLevel: Int,
    val phone: String
)

@Singleton
class EmergencyRepository @Inject constructor() {
    private val _session = MutableStateFlow(EmergencySession())
    val session = _session.asStateFlow()

    fun selectVictimRange(range: VictimRange) {
        _session.value = _session.value.copy(victimRange = range)
    }

    fun updateTriage(triageData: TriageData) {
        _session.value = _session.value.copy(triage = triageData)
    }

    fun selectHospital(hospital: HospitalInfo) {
        _session.value = _session.value.copy(hospital = hospital)
    }

    fun updateScenePhoto(bitmap: Bitmap) {
        _session.value = _session.value.copy(
            triage = _session.value.triage.copy(scenePhoto = bitmap)
        )
    }

    fun reset() {
        _session.value = EmergencySession()
    }

    // --- Offline Hospital Directory & Matching ---

    private fun parseHospitalsJson(json: String): List<HospitalRecord> {
        val records = mutableListOf<HospitalRecord>()
        val objectRegex = "\\{[^\\}]+\\}".toRegex()
        val matches = objectRegex.findAll(json)
        for (match in matches) {
            val objText = match.value
            val name = "\"name\"\\s*:\\s*\"([^\"]+)\"".toRegex().find(objText)?.groupValues?.get(1) ?: ""
            val lat = "\"lat\"\\s*:\\s*([\\d\\.-]+)".toRegex().find(objText)?.groupValues?.get(1)?.toDoubleOrNull() ?: 0.0
            val lng = "\"lng\"\\s*:\\s*([\\d\\.-]+)".toRegex().find(objText)?.groupValues?.get(1)?.toDoubleOrNull() ?: 0.0
            val traumaLevel = "\"traumaLevel\"\\s*:\\s*(\\d+)".toRegex().find(objText)?.groupValues?.get(1)?.toIntOrNull() ?: 2
            val phone = "\"phone\"\\s*:\\s*\"([^\"]+)\"".toRegex().find(objText)?.groupValues?.get(1) ?: ""
            if (name.isNotEmpty()) {
                records.add(HospitalRecord(name, lat, lng, traumaLevel, phone))
            }
        }
        return records
    }

    private fun loadHospitals(context: Context): List<HospitalRecord> {
        return try {
            val json = context.assets.open("hospitals.json").bufferedReader().use { it.readText() }
            parseHospitalsJson(json)
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun haversineDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val r = 6371.0 // Earth's radius in km
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                Math.sin(dLon / 2) * Math.sin(dLon / 2)
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        return r * c
    }

    fun updateLocationAndMatchHospitals(context: Context, latitude: Double, longitude: Double, placeName: String, landmark: String = "Near current location") {
        val hospitals = loadHospitals(context)
        if (hospitals.isEmpty()) return

        val nearby = hospitals.map { record ->
            val dist = haversineDistance(latitude, longitude, record.lat, record.lng)
            val eta = ((dist * 2.5) + 2).toInt().coerceAtLeast(3)
            HospitalInfo(
                name = record.name,
                traumaLevel = "Level ${if (record.traumaLevel == 1) "I" else "II"} Trauma Centre",
                distanceKm = Math.round(dist * 10.0) / 10.0,
                etaMinutes = eta,
                phone = record.phone
            )
        }.sortedBy { it.distanceKm }.take(3)

        if (nearby.isNotEmpty()) {
            val currentLoc = AccidentLocation(
                placeName = placeName,
                coordinates = String.format(java.util.Locale.US, "%.4f° N, %.4f° E", latitude, longitude),
                landmark = landmark
            )
            _session.value = _session.value.copy(
                location = currentLoc,
                hospital = nearby.first(),
                nearbyHospitals = nearby
            )
        }
    }

    // --- Real GPS Location Tracking & Geocoding ---

    @android.annotation.SuppressLint("MissingPermission")
    fun currentLocation(context: Context): Flow<android.location.Location> = callbackFlow {
        val client = com.google.android.gms.location.LocationServices.getFusedLocationProviderClient(context)
        val request = com.google.android.gms.location.LocationRequest.Builder(
            com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY,
            5000
        ).build()
        val callback = object : com.google.android.gms.location.LocationCallback() {
            override fun onLocationResult(result: com.google.android.gms.location.LocationResult) {
                result.lastLocation?.let { trySend(it) }
            }
        }
        client.requestLocationUpdates(request, callback, Looper.getMainLooper())
        awaitClose { client.removeLocationUpdates(callback) }
    }

    suspend fun reverseGeocode(latitude: Double, longitude: Double): String {
        return withContext(Dispatchers.IO) {
            try {
                val url = URL("https://nominatim.openstreetmap.org/reverse?format=json&lat=$latitude&lon=$longitude&zoom=18&addressdetails=1")
                val connection = url.openConnection() as HttpURLConnection
                connection.setRequestProperty("User-Agent", "GoldenHourApp/1.0")
                connection.connectTimeout = 4000
                connection.readTimeout = 4000
                val text = connection.inputStream.bufferedReader().use { it.readText() }
                val regex = "\"display_name\"\\s*:\\s*\"([^\"]+)\"".toRegex()
                val match = regex.find(text)
                val fullDisplayName = match?.groupValues?.get(1)
                if (fullDisplayName != null) {
                    fullDisplayName.split(",").take(3).joinToString(",").trim()
                } else {
                    String.format(java.util.Locale.US, "%.4f° N, %.4f° E", latitude, longitude)
                }
            } catch (e: Exception) {
                String.format(java.util.Locale.US, "%.4f° N, %.4f° E", latitude, longitude)
            }
        }
    }

    // --- Simulated Ambulance Route Telemetry ---

    fun ambulanceTelemetry(): Flow<AmbulanceTelemetry> = flow {
        val route = listOf(
            "MGM Ambulance Bay",
            "Sector 12, Kamothe",
            "Kamothe Flyover",
            "Sector 7, Kamothe",
            "NH-66 Junction",
            "Accident Site"
        )

        for (step in 0..100 step 2) {
            val routeIndex = ((step / 100f) * route.lastIndex)
                .toInt()
                .coerceIn(route.indices)
            emit(
                AmbulanceTelemetry(
                    progress = step / 100f,
                    etaMinutes = if (step == 100) 0 else (12 - (step * 12 / 100)).coerceAtLeast(1),
                    currentLocation = route[routeIndex],
                    speedKph = if (step == 0 || step == 100) 0 else 42 + (step % 9)
                )
            )
            delay(900)
        }
    }
}
