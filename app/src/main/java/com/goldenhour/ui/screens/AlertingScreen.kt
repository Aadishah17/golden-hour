package com.goldenhour.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import com.goldenhour.ui.components.BrandHeader
import com.goldenhour.ui.components.ChecklistItem
import com.goldenhour.ui.components.ConstrainedContent
import com.goldenhour.ui.components.PremiumCard
import com.goldenhour.ui.components.ProgressStepper
import com.goldenhour.ui.components.PrototypeBadge
import com.goldenhour.ui.components.ScreenBackdrop
import com.goldenhour.ui.components.SectionTitle
import com.goldenhour.ui.theme.Amber
import com.goldenhour.ui.theme.EmergencyRed
import com.goldenhour.ui.theme.Navy700
import com.goldenhour.ui.theme.TextMuted
import com.goldenhour.ui.theme.TextSecondary
import com.goldenhour.ui.theme.White
import com.goldenhour.utils.stringsFor
import com.goldenhour.viewmodel.AlertingViewModel

@Composable
fun AlertingScreen(
    viewModel: AlertingViewModel,
    onFinished: () -> Unit,
    modifier: Modifier = Modifier
) {
    val language by viewModel.selectedLanguage.collectAsStateWithLifecycle()
    val progress by viewModel.progress.collectAsStateWithLifecycle()
    val completedSteps by viewModel.completedSteps.collectAsStateWithLifecycle()
    val finished by viewModel.finished.collectAsStateWithLifecycle()
    val strings = stringsFor(language)
    val checklist = listOf(
        strings.locationPinged,
        strings.traumaIdentified,
        strings.triageSent,
        strings.ambulanceDispatched,
        strings.corridorActivated
    )

    LaunchedEffect(Unit) {
        viewModel.startSimulation()
    }
    LaunchedEffect(finished) {
        if (finished) onFinished()
    }

    ScreenBackdrop(modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ConstrainedContent(horizontalAlignment = Alignment.CenterHorizontally) {
                BrandHeader(compact = true)
                Spacer(Modifier.height(12.dp))
                PrototypeBadge(strings.simulationBadge)
                Spacer(Modifier.height(26.dp))

                CircularProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.height(92.dp),
                    color = EmergencyRed,
                    trackColor = Navy700,
                    strokeWidth = 8.dp
                )
                Spacer(Modifier.height(18.dp))
                Text(
                    strings.alertingTitle,
                    color = White,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    strings.alertingSubtitle,
                    color = TextSecondary,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp)
                )
                Text(
                    "${(progress * 100).toInt().coerceIn(0, 100)}%",
                    color = Amber,
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 14.dp)
                )
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp),
                    color = EmergencyRed,
                    trackColor = Navy700
                )

                Spacer(Modifier.height(22.dp))
                ProgressStepper(
                    activeStep = 1,
                    labels = listOf(strings.stepAlert, strings.stepRoute, strings.stepHospital)
                )
                Spacer(Modifier.height(18.dp))

                PremiumCard(accent = EmergencyRed) {
                    SectionTitle(strings.simulatedDispatchChecklist, Icons.Default.Sync)
                    checklist.forEachIndexed { index, item ->
                        ChecklistItem(item, completed = index < completedSteps)
                    }
                }

                Spacer(Modifier.height(14.dp))
                PremiumCard(accent = Amber) {
                    SectionTitle(strings.academicSafetyNote, Icons.Default.LocalHospital, iconColor = Amber)
                    Text(
                        strings.alertingSafetyNotice,
                        color = TextMuted,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
