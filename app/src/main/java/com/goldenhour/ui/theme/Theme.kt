package com.goldenhour.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val GoldenHourColors = lightColorScheme(
    primary = EmergencyRed,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFE2E5),
    onPrimaryContainer = Color(0xFF7A111B),
    secondary = Amber,
    onSecondary = Color.White,
    tertiary = Success,
    onTertiary = Color.White,
    background = SurfacePrimary,
    onBackground = TextPrimary,
    surface = SurfaceSecondary,
    onSurface = TextPrimary,
    surfaceVariant = SurfaceTertiary,
    onSurfaceVariant = TextSecondary,
    outline = Outline,
    error = EmergencyRed
)

@Composable
fun GoldenHourTheme(content: @Composable () -> Unit) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = true
                isAppearanceLightNavigationBars = true
            }
        }
    }

    MaterialTheme(
        colorScheme = GoldenHourColors,
        typography = GoldenHourTypography,
        content = content
    )
}
