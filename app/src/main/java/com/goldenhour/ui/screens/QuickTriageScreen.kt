package com.goldenhour.ui.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.goldenhour.model.TriageData
import com.goldenhour.ui.components.BrandHeader
import com.goldenhour.ui.components.ConstrainedContent
import com.goldenhour.ui.components.PremiumCard
import com.goldenhour.ui.components.PrimaryButton
import com.goldenhour.ui.components.ProgressStepper
import com.goldenhour.ui.components.PrototypeBadge
import com.goldenhour.ui.components.ScreenBackdrop
import com.goldenhour.ui.components.SecondaryButton
import com.goldenhour.ui.components.SectionTitle
import com.goldenhour.ui.theme.Amber
import com.goldenhour.ui.theme.EmergencyRed
import com.goldenhour.ui.theme.Navy700
import com.goldenhour.ui.theme.Outline
import com.goldenhour.ui.theme.Success
import com.goldenhour.ui.theme.TextMuted
import com.goldenhour.ui.theme.TextSecondary
import com.goldenhour.ui.theme.White
import com.goldenhour.utils.AppStrings
import com.goldenhour.utils.stringsFor
import com.goldenhour.viewmodel.TriageViewModel

@Composable
fun QuickTriageScreen(
    viewModel: TriageViewModel,
    onContinue: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val language by viewModel.selectedLanguage.collectAsStateWithLifecycle()
    val triage by viewModel.triage.collectAsStateWithLifecycle()
    val questionIndex by viewModel.questionIndex.collectAsStateWithLifecycle()
    val isListening by viewModel.isListening.collectAsStateWithLifecycle()
    val strings = stringsFor(language)
    val questions = listOf(
        strings.questionConscious,
        strings.questionBleeding,
        strings.questionBreathing
    )
    val progress = (questionIndex + 1) / questions.size.toFloat()

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
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = White)
                    }
                    BrandHeader(compact = true, modifier = Modifier.weight(1f))
                }

                Spacer(Modifier.height(12.dp))
                PrototypeBadge(strings.simulationBadge)
                Spacer(Modifier.height(16.dp))
                Text(
                    strings.triageTitle,
                    color = White,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    strings.triageSubtitle,
                    color = TextSecondary,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp)
                )
                Spacer(Modifier.height(22.dp))

                ProgressStepper(
                    activeStep = 0,
                    labels = listOf(strings.stepAlert, strings.stepRoute, strings.stepHospital)
                )
                Spacer(Modifier.height(20.dp))

                PremiumCard(accent = EmergencyRed) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SectionTitle(strings.triageTitle, Icons.Default.MedicalServices, modifier = Modifier.weight(1f))
                        Text(
                            "${questionIndex + 1}/${questions.size}",
                            color = Amber,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp),
                        color = EmergencyRed,
                        trackColor = Navy700
                    )
                    Text(
                        questions[questionIndex],
                        color = White,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                    AnswerButtons(
                        yes = strings.yes,
                        no = strings.no,
                        selected = triage.answerFor(questionIndex),
                        onAnswer = viewModel::answer
                    )
                    if (questionIndex > 0) {
                        TextButton(onClick = viewModel::previousQuestion) {
                            Text(strings.previous, color = Amber)
                        }
                    }
                }

                Spacer(Modifier.height(14.dp))
                PremiumCard(accent = Amber) {
                    SectionTitle(strings.voiceReport, Icons.Default.Mic)
                    VoiceMicButton(
                        label = if (isListening) strings.simulatedListening else "🎤 ${strings.tapSpeak}",
                        isListening = isListening,
                        onClick = viewModel::toggleVoiceSimulation
                    )
                    OutlinedTextField(
                        value = triage.sceneReport,
                        onValueChange = viewModel::updateReport,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 132.dp),
                        label = { Text(strings.describeScene) },
                        placeholder = { Text(strings.scenePlaceholder) },
                        minLines = 4,
                        maxLines = 5,
                        shape = RoundedCornerShape(20.dp)
                    )
                    Text(
                        strings.triageSimulationNotice,
                        color = TextMuted,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    SecondaryButton(
                        text = strings.skip,
                        onClick = onContinue,
                        modifier = Modifier.weight(1f)
                    )
                    PrimaryButton(
                        text = strings.proceed,
                        onClick = onContinue,
                        modifier = Modifier.weight(1f),
                        icon = Icons.AutoMirrored.Filled.Send
                    )
                }
                Spacer(Modifier.height(18.dp))
            }
        }
    }
}

@Composable
private fun AnswerButtons(
    yes: String,
    no: String,
    selected: Boolean?,
    onAnswer: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AnswerButton(
            text = yes,
            selected = selected == true,
            color = Success,
            onClick = { onAnswer(true) },
            modifier = Modifier.weight(1f)
        )
        AnswerButton(
            text = no,
            selected = selected == false,
            color = EmergencyRed,
            onClick = { onAnswer(false) },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun AnswerButton(
    text: String,
    selected: Boolean,
    color: androidx.compose.ui.graphics.Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onClick,
        modifier = modifier.height(72.dp),
        shape = RoundedCornerShape(22.dp),
        color = if (selected) color else Navy700,
        border = BorderStroke(if (selected) 2.dp else 1.dp, if (selected) color else Outline)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (selected) {
                Icon(Icons.Default.CheckCircle, contentDescription = null, tint = androidx.compose.ui.graphics.Color.White)
                Spacer(Modifier.width(8.dp))
            }
            Text(
                text,
                color = if (selected) androidx.compose.ui.graphics.Color.White else White,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Black
            )
        }
    }
}

@Composable
private fun VoiceMicButton(
    label: String,
    isListening: Boolean,
    onClick: () -> Unit
) {
    val transition = rememberInfiniteTransition(label = "voice-mic")
    val scale by transition.animateFloat(
        initialValue = 0.92f,
        targetValue = 1.12f,
        animationSpec = infiniteRepeatable(
            animation = tween(850, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "voice-scale"
    )
    val alpha by transition.animateFloat(
        initialValue = 0.35f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(850),
            repeatMode = RepeatMode.Reverse
        ),
        label = "voice-alpha"
    )

    Surface(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        color = if (isListening) EmergencyRed.copy(alpha = 0.16f) else Navy700,
        shape = RoundedCornerShape(22.dp),
        border = BorderStroke(1.dp, if (isListening) EmergencyRed else Outline)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                if (isListening) {
                    Surface(
                        modifier = Modifier
                            .size(56.dp)
                            .scale(scale)
                            .alpha(alpha),
                        color = EmergencyRed.copy(alpha = 0.35f),
                        shape = CircleShape
                    ) {}
                }
                Surface(color = EmergencyRed, shape = CircleShape, modifier = Modifier.size(48.dp)) {
                    Icon(Icons.Default.Mic, contentDescription = null, tint = androidx.compose.ui.graphics.Color.White, modifier = Modifier.padding(12.dp))
                }
            }
            Text(
                label,
                color = White,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

private fun TriageData.answerFor(index: Int): Boolean? = when (index) {
    0 -> isConscious
    1 -> hasHeavyBleeding
    else -> isBreathing
}
