package com.goldenhour.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Route
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Summarize
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.goldenhour.model.TriageData
import com.goldenhour.ui.components.AmbulanceRoute
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.draw.clip
import com.goldenhour.ui.components.BrandHeader
import com.goldenhour.ui.components.ChecklistItem
import com.goldenhour.ui.components.ConstrainedContent
import com.goldenhour.ui.components.InfoRow
import com.goldenhour.ui.components.LiveTrackingTimeline
import com.goldenhour.ui.components.PremiumCard
import com.goldenhour.ui.components.PrimaryButton
import com.goldenhour.ui.components.ProgressStepper
import com.goldenhour.ui.components.PrototypeBadge
import com.goldenhour.ui.components.ScreenBackdrop
import com.goldenhour.ui.components.SectionTitle
import com.goldenhour.ui.components.StatusBadge
import com.goldenhour.ui.theme.Amber
import com.goldenhour.ui.theme.EmergencyRed
import com.goldenhour.ui.theme.Sky
import com.goldenhour.ui.theme.Success
import com.goldenhour.ui.theme.TextMuted
import com.goldenhour.ui.theme.TextSecondary
import com.goldenhour.ui.theme.TextPrimary
import com.goldenhour.utils.AppStrings
import com.goldenhour.utils.stringsFor
import com.goldenhour.ui.components.KeepScreenOn
import com.goldenhour.viewmodel.DashboardViewModel
import java.util.Locale

@Composable
fun HospitalDashboardScreen(
    viewModel: DashboardViewModel,
    onRestart: () -> Unit,
    modifier: Modifier = Modifier
) {
    KeepScreenOn()
    val language by viewModel.selectedLanguage.collectAsStateWithLifecycle()
    val session by viewModel.session.collectAsStateWithLifecycle()
    val ambulance by viewModel.ambulance.collectAsStateWithLifecycle()
    val strings = stringsFor(language)
    val teamChecklist = listOf(
        strings.surgeonAlerted,
        strings.bloodBank,
        strings.emergencyBay,
        strings.radiology,
        strings.theatre,
        strings.triageReceived
    )
    val trackingSteps = listOf(
        strings.trackingDispatched,
        strings.trackingEnRoute,
        strings.trackingNearScene,
        strings.trackingArriving
    )
    val activeTrackingStep = when {
        ambulance.progress < 0.25f -> 0
        ambulance.progress < 0.65f -> 1
        ambulance.progress < 0.90f -> 2
        else -> 3
    }
    val remainingKm = (session.hospital.distanceKm * (1f - ambulance.progress))
        .coerceAtLeast(0.05)
    val remainingText = String.format(Locale.US, "%.1f km", remainingKm)
    val severityConfidence = when {
        session.triage.isBreathing == false -> 0.96f
        session.triage.hasHeavyBleeding == true -> 0.88f
        session.triage.isConscious == false -> 0.84f
        else -> 0.74f
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
                        StatusBadge(strings.gslProtected, Success, icon = Icons.Default.Shield)
                    }
                )
                Spacer(Modifier.height(12.dp))
                PrototypeBadge(strings.prototypeNotice)
                Spacer(Modifier.height(18.dp))

                Text(
                    strings.hospitalAlerted,
                    color = TextPrimary,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    strings.dashboardSubtitle,
                    color = TextSecondary,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 6.dp)
                )
                Spacer(Modifier.height(18.dp))
                ProgressStepper(
                    activeStep = 2,
                    labels = listOf(strings.stepAlert, strings.stepRoute, strings.stepHospital)
                )

                Spacer(Modifier.height(16.dp))
                PremiumCard(accent = Success) {
                    SectionTitle(strings.hospitalAlerted, Icons.Default.LocalHospital, iconColor = Success)
                    Text(
                        "🏥 ${session.hospital.name}",
                        color = TextPrimary,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(
                        session.hospital.traumaLevel,
                        color = Success,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                    InfoRow(strings.ambulanceEta, "${ambulance.etaMinutes} ${strings.minutesUnit}", valueColor = Amber)
                    InfoRow(strings.corridor, strings.active, valueColor = Success)
                    StatusBadge(strings.greenCorridorActive, EmergencyRed)
                }

                Spacer(Modifier.height(14.dp))
                PremiumCard(accent = Amber) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            SectionTitle(strings.liveTracking, Icons.Default.Route)
                            Text(
                                strings.ambulanceOnWay,
                                color = TextPrimary,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.ExtraBold
                            )
                            Text(
                                strings.paramedicUnit,
                                color = TextSecondary,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        StatusBadge(strings.liveGpsSimulation, Success)
                    }
                    Surface(
                        color = EmergencyRed.copy(alpha = 0.08f),
                        shape = RoundedCornerShape(22.dp),
                        border = BorderStroke(1.dp, EmergencyRed.copy(alpha = .18f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    strings.ambulanceEta,
                                    color = TextSecondary,
                                    style = MaterialTheme.typography.labelLarge
                                )
                                Text(
                                    "${ambulance.etaMinutes} ${strings.minutesUnit}",
                                    color = EmergencyRed,
                                    style = MaterialTheme.typography.displaySmall,
                                    fontWeight = FontWeight.Black
                                )
                                Text(
                                    "ETA may vary — traffic info unavailable",
                                    color = TextMuted,
                                    style = MaterialTheme.typography.labelSmall
                                )
                            }
                            Column(horizontalAlignment = Alignment.End) {
                                Text(strings.distanceRemaining, color = TextSecondary, style = MaterialTheme.typography.labelLarge)
                                Text(remainingText, color = TextPrimary, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.ExtraBold)
                                Text(strings.lastUpdated, color = TextMuted, style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    }
                    AmbulanceRoute(progress = ambulance.progress)
                    InfoRow(strings.currentLocation, ambulance.currentLocation, valueColor = TextPrimary)
                    InfoRow(strings.speed, "${ambulance.speedKph} ${strings.speedUnit}", valueColor = Amber)
                    InfoRow(strings.destination, session.location.landmark, valueColor = TextSecondary)
                    LiveTrackingTimeline(
                        steps = trackingSteps,
                        activeStep = activeTrackingStep
                    )
                }

                Spacer(Modifier.height(14.dp))
                PremiumCard(accent = Success) {
                    SectionTitle(strings.traumaTeamPreparing, Icons.Default.CheckCircle, iconColor = Success)
                    teamChecklist.forEach { item ->
                        ChecklistItem(item, completed = true)
                    }
                }

                Spacer(Modifier.height(14.dp))
                PremiumCard(accent = EmergencyRed) {
                    SectionTitle(strings.triageSummary, Icons.Default.Summarize, iconColor = EmergencyRed)
                    InfoRow(strings.victimCount, session.victimRange.label)
                    InfoRow(strings.questionConscious, session.triage.consciousLabel(strings), valueColor = triageColor(session.triage.isConscious))
                    InfoRow(strings.questionBleeding, session.triage.bleedingLabel(strings), valueColor = triageColor(session.triage.hasHeavyBleeding))
                    InfoRow(strings.questionBreathing, session.triage.breathingLabel(strings), valueColor = triageColor(session.triage.isBreathing))
                    if (session.triage.scenePhoto != null) {
                        Spacer(Modifier.height(6.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                bitmap = session.triage.scenePhoto!!.asImageBitmap(),
                                contentDescription = "Captured scene photo thumbnail",
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(RoundedCornerShape(12.dp)),
                                contentScale = androidx.compose.ui.layout.ContentScale.Crop
                            )
                            Text(
                                strings.photoCaptured,
                                color = Success,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                    if (session.triage.sceneReport.isNotBlank()) {
                        Text(
                            session.triage.sceneReport,
                            color = TextSecondary,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                Spacer(Modifier.height(14.dp))
                PremiumCard(accent = Amber) {
                    SectionTitle(strings.simulatedAiTitle, Icons.Default.AccessTime)
                    Text(
                        strings.roadmapDescription,
                        color = TextMuted,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    val dynamicSeverity = session.triage.rtsSeverity
                    val dynamicSeverityConfidence = when (session.triage.rtsScore) {
                        12 -> 0.74f
                        in 8..11 -> 0.84f
                        in 4..7 -> 0.92f
                        else -> 0.98f
                    }
                    AiModuleCard(
                        title = "🧠 ${strings.aiSeverity}",
                        value = dynamicSeverity,
                        detail = "Physiological risk level",
                        confidence = dynamicSeverityConfidence,
                        accent = EmergencyRed,
                        badge = strings.activeSimulation
                    )
                    AiModuleCard(
                        title = "🧠 ${strings.aiScoring}",
                        value = "RTS: ${session.triage.rtsScore}/12",
                        detail = "Revised Trauma Score (GCS + RR + SBP)",
                        confidence = 1.0f,
                        accent = Amber,
                        badge = strings.activeSimulation
                    )
                    AiModuleCard(
                        title = "🧠 ${strings.aiAllocation}",
                        value = session.hospital.name,
                        detail = strings.allocationReason,
                        confidence = 0.91f,
                        accent = Success,
                        badge = strings.activeSimulation
                    )
                    AiModuleCard(
                        title = "🧠 ${strings.aiScene}",
                        value = strings.sceneAnalysisResult,
                        detail = session.triage.sceneReport.ifBlank { strings.offlineComputed },
                        confidence = 0.78f,
                        accent = Sky,
                        badge = strings.activeSimulation
                    )
                }

                Spacer(Modifier.height(16.dp))
                PrimaryButton(
                    text = strings.restart,
                    onClick = {
                        viewModel.reset()
                        onRestart()
                    },
                    icon = Icons.Default.Refresh
                )
                Spacer(Modifier.height(18.dp))
            }
        }
    }
}

private fun TriageData.consciousLabel(strings: AppStrings): String = when (isConscious) {
    true -> strings.conscious
    false -> strings.unconscious
    null -> strings.notAnswered
}

private fun TriageData.bleedingLabel(strings: AppStrings): String = when (hasHeavyBleeding) {
    true -> strings.bleeding
    false -> strings.noBleeding
    null -> strings.notAnswered
}

private fun TriageData.breathingLabel(strings: AppStrings): String = when (isBreathing) {
    true -> strings.breathing
    false -> strings.notBreathing
    null -> strings.notAnswered
}

private fun triageColor(value: Boolean?): androidx.compose.ui.graphics.Color = when (value) {
    true -> Success
    false -> EmergencyRed
    null -> TextMuted
}

@Composable
private fun AiModuleCard(
    title: String,
    value: String,
    detail: String,
    confidence: Float,
    accent: androidx.compose.ui.graphics.Color,
    badge: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = accent.copy(alpha = 0.07f),
        shape = RoundedCornerShape(22.dp),
        border = BorderStroke(1.dp, accent.copy(alpha = 0.24f))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(9.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(title, color = TextPrimary, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.ExtraBold)
                    Text(value, color = accent, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Black)
                }
                StatusBadge(badge, accent)
            }
            LinearProgressIndicator(
                progress = { confidence.coerceIn(0f, 1f) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                color = accent,
                trackColor = accent.copy(alpha = .16f)
            )
            Text(
                "${(confidence * 100).toInt()}% · $detail",
                color = TextSecondary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
