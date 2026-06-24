package com.goldenhour.model

import android.graphics.Bitmap

enum class VictimRange(val storedCount: Int, val label: String) {
    ONE(1, "1"),
    TWO_TO_FIVE(3, "2–5"),
    FIVE_PLUS(6, "5+")
}

data class AccidentLocation(
    val placeName: String = "Kamothe, Raigad",
    val coordinates: String = "18.9156° N, 73.0987° E",
    val landmark: String = "NH-66 Near Kamothe Junction"
)

data class HospitalInfo(
    val name: String = "MGM Hospital, Kamothe",
    val traumaLevel: String = "Level I Trauma Centre",
    val distanceKm: Double = 2.3,
    val etaMinutes: Int = 6,
    val phone: String = "+91-22-27437900"
)

data class TriageData(
    val isConscious: Boolean? = null,
    val hasHeavyBleeding: Boolean? = null,
    val isBreathing: Boolean? = null,
    val sceneReport: String = "",
    val scenePhoto: Bitmap? = null
) {
    val answeredCount: Int
        get() = listOf(isConscious, hasHeavyBleeding, isBreathing).count { it != null }

    val isComplete: Boolean
        get() = answeredCount == 3

    val rtsScore: Int
        get() {
            val gcs = if (isConscious == true) 4 else if (isConscious == false) 0 else 4
            val rr = if (isBreathing == true) 4 else if (isBreathing == false) 0 else 4
            val sbp = if (hasHeavyBleeding == true) 2 else if (hasHeavyBleeding == false) 4 else 4
            return gcs + rr + sbp
        }

    val rtsSeverity: String
        get() = when (rtsScore) {
            in 11..12 -> "Low Risk - Stable"
            in 8..10 -> "Moderate Risk - Urgent"
            in 4..7 -> "High Risk - Critical"
            else -> "Extreme Risk - Resuscitation"
        }
}

data class AmbulanceTelemetry(
    val progress: Float = 0f,
    val etaMinutes: Int = 12,
    val currentLocation: String = "MGM Ambulance Bay",
    val speedKph: Int = 0
)

data class EmergencySession(
    val location: AccidentLocation = AccidentLocation(),
    val hospital: HospitalInfo = HospitalInfo(),
    val nearbyHospitals: List<HospitalInfo> = emptyList(),
    val victimRange: VictimRange = VictimRange.ONE,
    val triage: TriageData = TriageData()
)

data class RoadmapFeature(
    val title: String,
    val description: String
)
