package com.goldenhour.ui.screens

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import kotlin.math.sqrt
import com.goldenhour.model.VictimRange
import com.goldenhour.ui.components.BrandHeader
import com.goldenhour.ui.components.ConstrainedContent
import com.goldenhour.ui.components.InfoRow
import com.goldenhour.ui.components.PremiumCard
import com.goldenhour.ui.components.PrototypeBadge
import com.goldenhour.ui.components.PulsingSosButton
import com.goldenhour.ui.components.ScreenBackdrop
import com.goldenhour.ui.components.SecondaryButton
import com.goldenhour.ui.components.SectionTitle
import com.goldenhour.ui.components.StatusBadge
import com.goldenhour.ui.components.VictimSelector
import com.goldenhour.ui.theme.Amber
import com.goldenhour.ui.theme.EmergencyRed
import com.goldenhour.ui.theme.Success
import com.goldenhour.ui.theme.TextMuted
import com.goldenhour.ui.theme.TextPrimary
import com.goldenhour.ui.theme.TextSecondary
import com.goldenhour.utils.openDialer
import com.goldenhour.utils.stringsFor
import com.goldenhour.viewmodel.SOSViewModel

@Composable
fun SOSScreen(
    viewModel: SOSViewModel,
    onSos: () -> Unit,
    modifier: Modifier = Modifier
) {
    val language by viewModel.selectedLanguage.collectAsStateWithLifecycle()
    val session by viewModel.session.collectAsStateWithLifecycle()
    val strings = stringsFor(language)
    val context = LocalContext.current
    val haptic = LocalHapticFeedback.current

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
                Spacer(Modifier.height(13.dp))
                VictimSelector(
                    selected = session.victimRange,
                    title = strings.victimCount,
                    options = VictimRange.entries.map { it to it.label },
                    onSelect = { range -> viewModel.selectVictimRange(range) }
                )
                Spacer(Modifier.height(16.dp))
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

