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
import com.goldenhour.ui.components.LanguageCard
import com.goldenhour.ui.components.PrimaryButton
import com.goldenhour.ui.components.PrototypeBadge
import com.goldenhour.ui.components.ScreenBackdrop
import com.goldenhour.ui.theme.Amber
import com.goldenhour.ui.theme.TextMuted
import com.goldenhour.ui.theme.TextSecondary
import com.goldenhour.ui.theme.White
import com.goldenhour.utils.stringsFor
import com.goldenhour.viewmodel.LanguageViewModel

@Composable
fun LanguageSelectionScreen(
    viewModel: LanguageViewModel,
    onContinue: () -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedLanguage by viewModel.selectedLanguage.collectAsStateWithLifecycle()
    val strings = stringsFor(selectedLanguage)

    ScreenBackdrop(modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 22.dp, vertical = 22.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ConstrainedContent(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(Modifier.height(20.dp))
                BrandHeader()
                Spacer(Modifier.height(22.dp))
                Text(
                    strings.subtitle,
                    style = MaterialTheme.typography.titleLarge,
                    color = TextSecondary,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    strings.tagline,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Amber,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(32.dp))
                PrototypeBadge(strings.simulationBadge)
                Spacer(Modifier.height(30.dp))
                Text(
                    strings.languagePrompt,
                    style = MaterialTheme.typography.titleLarge,
                    color = White,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(14.dp))
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    LanguageCard(
                        title = "English",
                        subtitle = "English",
                        selected = selectedLanguage == "en",
                        onClick = { viewModel.selectLanguage("en") }
                    )
                    LanguageCard(
                        title = "हिन्दी",
                        subtitle = "Hindi",
                        selected = selectedLanguage == "hi",
                        onClick = { viewModel.selectLanguage("hi") }
                    )
                    LanguageCard(
                        title = "मराठी",
                        subtitle = "Marathi",
                        selected = selectedLanguage == "mr",
                        onClick = { viewModel.selectLanguage("mr") }
                    )
                }
                Spacer(Modifier.height(26.dp))
                PrimaryButton(strings.continueLabel, onContinue)
                Spacer(Modifier.height(24.dp))
                Text(
                    strings.developedBy,
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    strings.prototypeNotice,
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextMuted,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp, bottom = 12.dp)
                )
            }
        }
    }
}
