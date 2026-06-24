package com.goldenhour.ui.screens

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.goldenhour.model.VictimRange
import com.goldenhour.ui.components.BrandHeader
import com.goldenhour.ui.components.ConstrainedContent
import com.goldenhour.ui.components.InfoRow
import com.goldenhour.ui.components.KeepScreenOn
import com.goldenhour.ui.components.PremiumCard
import com.goldenhour.ui.components.PrototypeBadge
import com.goldenhour.ui.components.PulsingSosButton
import com.goldenhour.ui.components.ScreenBackdrop
import com.goldenhour.ui.components.SecondaryButton
import com.goldenhour.ui.components.SectionTitle
import com.goldenhour.ui.components.StatusBadge
import com.goldenhour.ui.components.VictimSelector
import com.goldenhour.ui.theme.Amber
import com.goldenhour.ui.theme.Outline
import com.goldenhour.ui.theme.Success
import com.goldenhour.ui.theme.SurfaceTertiary
import com.goldenhour.ui.theme.TextMuted
import com.goldenhour.ui.theme.TextPrimary
import com.goldenhour.ui.theme.TextSecondary
import com.goldenhour.utils.openDialer
import com.goldenhour.utils.stringsFor
import com.goldenhour.viewmodel.SOSViewModel
import kotlin.math.sqrt

@Composable
fun SOSScreen(
    viewModel: SOSViewModel,
    onSos: () -> Unit,
    modifier: Modifier = Modifier
) {
    KeepScreenOn()
    val language by viewModel.selectedLanguage.collectAsStateWithLifecycle()
    val session by viewModel.session.collectAsStateWithLifecycle()
    val strings = stringsFor(language)
    val context = LocalContext.current
    val haptic = LocalHapticFeedback.current

    // --- Runtime Location Permission Handling & Tracking ---
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { _ -> }

    LaunchedEffect(Unit) {
        permissionLauncher.launch(
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    val locationFlow = remember(context) { viewModel.currentLocationFlow(context) }
    LaunchedEffect(locationFlow) {
        try {
            locationFlow.collect { location ->
                val placeName = viewModel.reverseGeocode(location.latitude, location.longitude)
                viewModel.updateLocation(context, location.latitude, location.longitude, placeName)
            }
        } catch (e: SecurityException) {
            // Permission not granted, fall back to mock location (pre-populated)
        } catch (e: Exception) {
            // Handle other network/parsing errors gracefully
        }
    }

    // --- Shake-to-SOS Detection ---
    ShakeDetector {
        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
        viewModel.activateSos()
        onSos()
    }

    ScreenBackdrop(modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ConstrainedContent(horizontalAlignment = Alignment.CenterHorizontally) {
                BrandHeader(
                    compact = true,
                    trailing = {
                        StatusBadge(
                            strings.gslProtected,
                            Success,
                            icon = Icons.Default.Shield
                        )
                    }
                )
                Spacer(Modifier.height(12.dp))
                PrototypeBadge(strings.prototypeNotice, Modifier.align(Alignment.CenterHorizontally))
                Spacer(Modifier.height(22.dp))

                // 1. Current Accident Location Card
                PremiumCard(accent = Amber) {
                    SectionTitle(strings.locationTitle, Icons.Default.LocationOn)
                    Text(
                        session.location.placeName,
                        color = TextPrimary,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        session.location.coordinates,
                        color = Amber,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        session.location.landmark,
                        color = TextSecondary,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Spacer(Modifier.height(13.dp))

                // 2. Selected Trauma Centre Card
                PremiumCard(accent = Success) {
                    SectionTitle(strings.traumaCentreTitle, Icons.Default.LocalHospital, iconColor = Success)
                    Text(
                        session.hospital.name,
                        color = TextPrimary,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        session.hospital.traumaLevel,
                        color = Success,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    InfoRow(strings.distance, "${session.hospital.distanceKm} km")
                    InfoRow(strings.eta, "${session.hospital.etaMinutes} min", valueColor = Amber)
                }

                // 3. Selectable Nearby Trauma Centres List (Calculated Offline)
                if (session.nearbyHospitals.isNotEmpty()) {
                    Spacer(Modifier.height(16.dp))
                    Text(
                        strings.nearbyHospitalsTitle,
                        style = MaterialTheme.typography.titleMedium,
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(8.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        session.nearbyHospitals.forEach { hosp ->
                            val isSelected = hosp.name == session.hospital.name
                            Surface(
                                onClick = { viewModel.selectHospital(hosp) },
                                modifier = Modifier.fillMaxWidth(),
                                color = if (isSelected) Success.copy(alpha = 0.14f) else SurfaceTertiary,
                                border = BorderStroke(if (isSelected) 2.dp else 1.dp, if (isSelected) Success else Outline),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Row(
                                    modifier = Modifier.padding(14.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            hosp.name,
                                            color = TextPrimary,
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            hosp.traumaLevel,
                                            color = Success,
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                    }
                                    Column(horizontalAlignment = Alignment.End) {
                                        Text(
                                            "${hosp.distanceKm} km",
                                            color = TextPrimary,
                                            style = MaterialTheme.typography.bodyMedium,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            "${hosp.etaMinutes} min ETA",
                                            color = Amber,
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(Modifier.height(16.dp))
                VictimSelector(
                    selected = session.victimRange,
                    title = strings.victimCount,
                    options = VictimRange.entries.map { it to it.label },
                    onSelect = { range -> viewModel.selectVictimRange(range) }
                )
                Spacer(Modifier.height(16.dp))

                // Pulsing SOS Trigger
                PulsingSosButton(
                    onClick = {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        viewModel.activateSos()
                        onSos()
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Text(
                    strings.tapAlert,
                    color = TextSecondary,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                )
                Text(
                    strings.sosSimulationNotice,
                    color = TextMuted,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp, bottom = 18.dp)
                )

                // Call buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    SecondaryButton(
                        text = strings.call108,
                        onClick = { context.openDialer("108") },
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.Call
                    )
                    SecondaryButton(
                        text = strings.call112,
                        onClick = { context.openDialer("112") },
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.Call
                    )
                }
                Spacer(Modifier.height(18.dp))
            }
        }
    }
}

@Composable
private fun ShakeDetector(
    onShake: () -> Unit
) {
    val context = LocalContext.current
    DisposableEffect(Unit) {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        var shakeTimestamp: Long = 0
        val sensorListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]

                val gX = x / SensorManager.GRAVITY_EARTH
                val gY = y / SensorManager.GRAVITY_EARTH
                val gZ = z / SensorManager.GRAVITY_EARTH

                val gForce = sqrt(gX * gX + gY * gY + gZ * gZ)

                if (gForce > 2.5f) {
                    val now = System.currentTimeMillis()
                    if (shakeTimestamp + 1200 > now) return
                    shakeTimestamp = now
                    onShake()
                }
            }

            override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
        }
        if (accelerometer != null) {
            sensorManager.registerListener(sensorListener, accelerometer, SensorManager.SENSOR_DELAY_UI)
        }
        onDispose {
            sensorManager.unregisterListener(sensorListener)
        }
    }
}
