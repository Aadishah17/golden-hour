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
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.VisibilityOff
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
import com.goldenhour.ui.components.FaqCard
import com.goldenhour.ui.components.PremiumCard
import com.goldenhour.ui.components.PrimaryButton
import com.goldenhour.ui.components.PrototypeBadge
import com.goldenhour.ui.components.ScreenBackdrop
import com.goldenhour.ui.components.SectionTitle
import com.goldenhour.ui.theme.Amber
import com.goldenhour.ui.theme.EmergencyRed
import com.goldenhour.ui.theme.Success
import com.goldenhour.ui.theme.TextSecondary
import com.goldenhour.ui.theme.White
import com.goldenhour.utils.stringsFor
import com.goldenhour.viewmodel.GoodSamaritanViewModel

@Composable
fun GoodSamaritanScreen(
    viewModel: GoodSamaritanViewModel,
    onContinue: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val language by viewModel.selectedLanguage.collectAsStateWithLifecycle()
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
            ConstrainedContent {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = White)
                    }
                    BrandHeader(
                        compact = true,
                        modifier = Modifier.weight(1f),
                        trailing = { PrototypeBadge(strings.simulationBadge) }
                    )
                }
                Spacer(Modifier.height(28.dp))
                Icon(
                    Icons.Default.Shield,
                    contentDescription = null,
                    tint = Success,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 12.dp)
                )
                Text(
                    strings.protectedTitle,
                    style = MaterialTheme.typography.headlineLarge,
                    color = White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    strings.protectedDescription,
                    style = MaterialTheme.typography.bodyLarge,
                    color = TextSecondary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                )
                Text(
                    strings.treatmentNotice,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Amber,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                )
                Text(
                    strings.anonymityNotice,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Success,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp, bottom = 22.dp)
                )

                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    PremiumCard(accent = EmergencyRed) {
                        SectionTitle(strings.legalTitle, Icons.Default.AccountBalance, iconColor = EmergencyRed)
                        Text(strings.legalBody, color = TextSecondary, style = MaterialTheme.typography.bodyLarge)
                    }
                    PremiumCard(accent = Amber) {
                        SectionTitle(strings.financialTitle, Icons.Default.Payments)
                        Text(strings.financialBody, color = TextSecondary, style = MaterialTheme.typography.bodyLarge)
                    }
                    PremiumCard(accent = Success) {
                        SectionTitle(strings.anonymousTitle, Icons.Default.VisibilityOff, iconColor = Success)
                        Text(strings.anonymousBody, color = TextSecondary, style = MaterialTheme.typography.bodyLarge)
                    }
                }

                Text(
                    strings.faqTitle,
                    style = MaterialTheme.typography.titleLarge,
                    color = White,
                    modifier = Modifier.padding(top = 28.dp, bottom = 12.dp)
                )
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    FaqCard(strings.faqPoliceQuestion, strings.faqPoliceAnswer)
                    FaqCard(strings.faqProtectedQuestion, strings.faqProtectedAnswer)
                    FaqCard(strings.faqIdentityQuestion, strings.faqIdentityAnswer)
                }
                Spacer(Modifier.height(26.dp))
                PrimaryButton(strings.understandButton, onContinue, icon = Icons.Default.Shield)
                Spacer(Modifier.height(14.dp))
            }
        }
    }
}
