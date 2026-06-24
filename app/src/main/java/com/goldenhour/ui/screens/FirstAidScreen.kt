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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.goldenhour.ui.components.BrandHeader
import com.goldenhour.ui.components.ConstrainedContent
import com.goldenhour.ui.components.KeepScreenOn
import com.goldenhour.ui.components.PremiumCard
import com.goldenhour.ui.components.PrimaryButton
import com.goldenhour.ui.components.ProgressStepper
import com.goldenhour.ui.components.PrototypeBadge
import com.goldenhour.ui.components.ScreenBackdrop
import com.goldenhour.ui.components.SectionTitle
import com.goldenhour.ui.theme.Amber
import com.goldenhour.ui.theme.EmergencyRed
import com.goldenhour.ui.theme.Success
import com.goldenhour.ui.theme.TextMuted
import com.goldenhour.ui.theme.TextPrimary
import com.goldenhour.ui.theme.TextSecondary
import com.goldenhour.utils.stringsFor
import com.goldenhour.viewmodel.FirstAidViewModel

@Composable
fun FirstAidScreen(
    viewModel: FirstAidViewModel,
    onContinue: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    KeepScreenOn()
    val language by viewModel.selectedLanguage.collectAsStateWithLifecycle()
    val triage by viewModel.triage.collectAsStateWithLifecycle()
    val strings = stringsFor(language)

    ScreenBackdrop(modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ConstrainedContent(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = TextPrimary)
                    }
                    BrandHeader(compact = true, modifier = Modifier.weight(1f))
                }

                Spacer(Modifier.height(12.dp))
                PrototypeBadge(strings.simulationBadge)
                Spacer(Modifier.height(16.dp))

                Text(
                    strings.firstAidTitle,
                    color = TextPrimary,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    strings.firstAidSubtitle,
                    color = TextSecondary,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp)
                )
                Spacer(Modifier.height(20.dp))

                ProgressStepper(
                    activeStep = 0,
                    labels = listOf(strings.stepAlert, strings.stepRoute, strings.stepHospital)
                )
                Spacer(Modifier.height(20.dp))

                // 1. Breathing / CPR Card (Highest Priority)
                if (triage.isBreathing == false) {
                    PremiumCard(accent = EmergencyRed) {
                        SectionTitle(strings.firstAidBreathingTitle, Icons.Default.Warning, iconColor = EmergencyRed)
                        Text(
                            strings.firstAidBreathingText,
                            color = TextPrimary,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(Modifier.height(12.dp))
                }

                // 2. Bleeding Card (High Priority)
                if (triage.hasHeavyBleeding == true) {
                    PremiumCard(accent = EmergencyRed) {
                        SectionTitle(strings.firstAidBleedingTitle, Icons.Default.Warning, iconColor = EmergencyRed)
                        Text(
                            strings.firstAidBleedingText,
                            color = TextPrimary,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(Modifier.height(12.dp))
                }

                // 3. Consciousness / Airway Card
                if (triage.isConscious == false) {
                    PremiumCard(accent = Amber) {
                        SectionTitle(strings.firstAidConsciousTitle, Icons.Default.Info, iconColor = Amber)
                        Text(
                            strings.firstAidConsciousText,
                            color = TextPrimary,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(Modifier.height(12.dp))
                }

                // Green/Normal Cards (Show Reassurance if any observations are OK)
                val showReassurance = triage.isBreathing == true || triage.hasHeavyBleeding == false || triage.isConscious == true
                if (showReassurance) {
                    PremiumCard(accent = Success) {
                        SectionTitle("First Aid Status", Icons.Default.MedicalServices, iconColor = Success)
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            if (triage.isBreathing == true) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Success)
                                    Text(strings.firstAidBreathingOk, color = TextPrimary, style = MaterialTheme.typography.bodyLarge)
                                }
                            }
                            if (triage.hasHeavyBleeding == false) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Success)
                                    Text(strings.firstAidBleedingOk, color = TextPrimary, style = MaterialTheme.typography.bodyLarge)
                                }
                            }
                            if (triage.isConscious == true) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Success)
                                    Text(strings.firstAidConsciousOk, color = TextPrimary, style = MaterialTheme.typography.bodyLarge)
                                }
                            }
                        }
                    }
                    Spacer(Modifier.height(12.dp))
                }

                // 4. Legal / Good Samaritan Disclaimer Card
                PremiumCard(accent = Success) {
                    SectionTitle("Good Samaritan Protection", Icons.Default.Info, iconColor = Success)
                    Text(
                        strings.firstAidDisclaimer,
                        color = TextMuted,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Spacer(Modifier.height(20.dp))
                PrimaryButton(
                    text = strings.proceed,
                    onClick = onContinue,
                    icon = Icons.AutoMirrored.Filled.Send
                )
                Spacer(Modifier.height(18.dp))
            }
        }
    }
}
