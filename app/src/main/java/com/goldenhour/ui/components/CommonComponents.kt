package com.goldenhour.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goldenhour.ui.theme.Amber
import com.goldenhour.ui.theme.EmergencyRed
import com.goldenhour.ui.theme.EmergencyRedDark
import com.goldenhour.ui.theme.Navy700
import com.goldenhour.ui.theme.Navy800
import com.goldenhour.ui.theme.Navy900
import com.goldenhour.ui.theme.Outline
import com.goldenhour.ui.theme.Success
import com.goldenhour.ui.theme.TextMuted
import com.goldenhour.ui.theme.TextSecondary
import com.goldenhour.ui.theme.White
import kotlin.math.sqrt

@Composable
fun ScreenBackdrop(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color.White,
                        Navy900,
                        Color(0xFFFFF7ED)
                    )
                )
            ),
        content = content
    )
}

@Composable
fun ConstrainedContent(
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .widthIn(max = 900.dp),
        horizontalAlignment = horizontalAlignment,
        content = content
    )
}

@Composable
fun BrandHeader(
    modifier: Modifier = Modifier,
    compact: Boolean = false,
    trailing: (@Composable () -> Unit)? = null
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Surface(
                color = EmergencyRed,
                shape = RoundedCornerShape(if (compact) 12.dp else 20.dp),
                modifier = Modifier.size(if (compact) 44.dp else 72.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        Icons.Default.LocalHospital,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(if (compact) 26.dp else 42.dp)
                    )
                }
            }
            Text(
                text = "GoldenHour",
                style = if (compact) MaterialTheme.typography.titleLarge
                else MaterialTheme.typography.headlineLarge,
                color = White,
                fontWeight = FontWeight.ExtraBold
            )
        }
        trailing?.invoke()
    }
}

@Composable
fun PrototypeBadge(
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = Amber.copy(alpha = 0.14f),
        contentColor = Amber,
        border = BorderStroke(1.dp, Amber.copy(alpha = 0.45f)),
        shape = RoundedCornerShape(999.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 11.dp, vertical = 6.dp),
            style = MaterialTheme.typography.labelMedium,
            letterSpacing = 1.sp
        )
    }
}

@Composable
fun PremiumCard(
    modifier: Modifier = Modifier,
    accent: Color? = null,
    contentPadding: PaddingValues = PaddingValues(18.dp),
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Navy800.copy(alpha = 0.94f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = BorderStroke(1.dp, accent?.copy(alpha = 0.5f) ?: Outline)
    ) {
        Column(
            modifier = Modifier.padding(contentPadding),
            verticalArrangement = Arrangement.spacedBy(11.dp),
            content = content
        )
    }
}

@Composable
fun SectionTitle(
    title: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    iconColor: Color = Amber
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Icon(icon, contentDescription = null, tint = iconColor)
        Text(title, style = MaterialTheme.typography.titleLarge, color = White)
    }
}

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: ImageVector? = null
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 58.dp),
        shape = RoundedCornerShape(19.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = EmergencyRed,
            contentColor = Color.White,
            disabledContainerColor = Navy700,
            disabledContentColor = TextMuted
        )
    ) {
        if (icon != null) {
            Icon(icon, contentDescription = null)
            Spacer(Modifier.width(9.dp))
        }
        Text(text, style = MaterialTheme.typography.labelLarge)
    }
}

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.heightIn(min = 54.dp),
        border = BorderStroke(1.dp, Outline),
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = EmergencyRed)
    ) {
        if (icon != null) {
            Icon(icon, contentDescription = null)
            Spacer(Modifier.width(8.dp))
        }
        Text(text, style = MaterialTheme.typography.labelLarge)
    }
}

@Composable
fun InfoRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    valueColor: Color = White
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Text(label, color = TextSecondary, style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.width(16.dp))
        Text(
            value,
            color = valueColor,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.End
        )
    }
}

@Composable
fun StatusBadge(
    text: String,
    color: Color,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null
) {
    Surface(
        modifier = modifier,
        color = color.copy(alpha = 0.15f),
        contentColor = color,
        border = BorderStroke(1.dp, color.copy(alpha = 0.45f)),
        shape = RoundedCornerShape(999.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 11.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            if (icon != null) Icon(icon, contentDescription = null, modifier = Modifier.size(16.dp))
            Text(text, style = MaterialTheme.typography.labelMedium)
        }
    }
}

@Composable
fun LanguageCard(
    title: String,
    subtitle: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val container by animateColorAsState(
        if (selected) EmergencyRed.copy(alpha = 0.18f) else Navy800,
        label = "language-card"
    )
    Surface(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 82.dp)
            .semantics { role = Role.RadioButton },
        color = container,
        shape = RoundedCornerShape(21.dp),
        border = BorderStroke(if (selected) 2.dp else 1.dp, if (selected) EmergencyRed else Outline)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(title, color = White, style = MaterialTheme.typography.titleLarge)
                Text(subtitle, color = TextSecondary, style = MaterialTheme.typography.bodyMedium)
            }
            Surface(
                color = if (selected) EmergencyRed else Navy700,
                shape = CircleShape,
                modifier = Modifier.size(30.dp)
            ) {
                if (selected) {
                    Icon(Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.padding(6.dp))
                }
            }
        }
    }
}

@Composable
fun FaqCard(
    question: String,
    answer: String,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(if (expanded) 180f else 0f, label = "faq-arrow")

    PremiumCard(
        modifier = modifier.clickable { expanded = !expanded },
        contentPadding = PaddingValues(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                question,
                color = White,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )
            Icon(
                Icons.Default.ExpandMore,
                contentDescription = null,
                tint = Amber,
                modifier = Modifier.rotate(rotation)
            )
        }
        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Column {
                HorizontalDivider(color = Outline)
                Text(
                    answer,
                    color = TextSecondary,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }
    }
}

@Composable
fun VictimSelector(
    selectedLabel: String,
    title: String,
    options: List<String>,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    PremiumCard(modifier) {
        Text(title, style = MaterialTheme.typography.titleMedium, color = White)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(9.dp)
        ) {
            options.forEach { option ->
                val selected = option == selectedLabel
                Surface(
                    onClick = { onSelect(option) },
                    modifier = Modifier
                        .weight(1f)
                        .height(54.dp)
                        .semantics {
                            role = Role.RadioButton
                            contentDescription = "$title $option"
                        },
                    color = if (selected) EmergencyRed else Navy700,
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(1.dp, if (selected) EmergencyRed else Outline)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(option, color = if (selected) Color.White else White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun PulsingSosButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val transition = rememberInfiniteTransition(label = "sos-pulse")
    val ringScale by transition.animateFloat(
        initialValue = 0.85f,
        targetValue = 1.35f,
        animationSpec = infiniteRepeatable(
            animation = tween(1_500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "ring-scale"
    )
    val ringAlpha by transition.animateFloat(
        initialValue = 0.55f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1_500),
            repeatMode = RepeatMode.Restart
        ),
        label = "ring-alpha"
    )

    Box(
        modifier = modifier.size(270.dp),
        contentAlignment = Alignment.Center
    ) {
        repeat(3) { index ->
            Box(
                Modifier
                    .size(210.dp)
                    .scale((ringScale - index * 0.12f).coerceAtLeast(0.75f))
                    .alpha((ringAlpha - index * 0.1f).coerceAtLeast(0f))
                    .border(3.dp, EmergencyRed, CircleShape)
            )
        }
        Surface(
            onClick = onClick,
            modifier = Modifier
                .size(170.dp)
                .semantics { contentDescription = "SOS accident simulation" },
            shape = CircleShape,
            color = EmergencyRed,
            shadowElevation = 18.dp,
            border = BorderStroke(5.dp, EmergencyRedDark)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(Icons.Default.Warning, contentDescription = null, tint = Color.White, modifier = Modifier.size(32.dp))
                Text("SOS", color = Color.White, fontSize = 38.sp, fontWeight = FontWeight.Black)
                Text("ACCIDENT", color = Color.White.copy(alpha = .9f), letterSpacing = 2.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun ProgressStepper(
    activeStep: Int,
    labels: List<String>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        labels.forEachIndexed { index, label ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Surface(
                    color = if (index <= activeStep) EmergencyRed else Navy700,
                    shape = CircleShape,
                    modifier = Modifier.size(34.dp),
                    border = BorderStroke(1.dp, if (index <= activeStep) EmergencyRed else Outline)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        if (index < activeStep) {
                            Icon(Icons.Default.Check, null, tint = Color.White, modifier = Modifier.size(18.dp))
                        } else {
                            Text("${index + 1}", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                }
                Spacer(Modifier.height(6.dp))
                Text(
                    label,
                    color = if (index <= activeStep) White else TextMuted,
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Center
                )
            }
            if (index < labels.lastIndex) {
                Box(
                    Modifier
                        .weight(.55f)
                        .height(2.dp)
                        .background(if (index < activeStep) EmergencyRed else Outline)
                )
            }
        }
    }
}

@Composable
fun ChecklistItem(
    text: String,
    completed: Boolean,
    modifier: Modifier = Modifier
) {
    val color by animateColorAsState(if (completed) Success else TextMuted, label = "check-color")
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Surface(
            color = color.copy(alpha = .16f),
            shape = CircleShape,
            modifier = Modifier.size(30.dp),
            border = BorderStroke(1.dp, color.copy(alpha = .5f))
        ) {
            if (completed) Icon(Icons.Default.Check, null, tint = color, modifier = Modifier.padding(6.dp))
        }
        Text(
            text,
            color = if (completed) White else TextMuted,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = if (completed) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}

@Composable
fun AmbulanceRoute(
    progress: Float,
    modifier: Modifier = Modifier
) {
    val animatedProgress by animateFloatAsState(
        progress,
        animationSpec = tween(850, easing = FastOutSlowInEasing),
        label = "route-progress"
    )
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(260.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFFF8FBFF),
                        Color(0xFFEFF6FF)
                    )
                )
            )
            .border(1.dp, Outline, RoundedCornerShape(28.dp))
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val roadColor = Color(0xFFD7DEE8)
            val minorRoad = Color(0xFFE8EEF5)
            val blockFill = Color.White.copy(alpha = 0.72f)
            val corner = CornerRadius(18.dp.toPx(), 18.dp.toPx())

            repeat(4) { row ->
                drawRoundRect(
                    color = blockFill,
                    topLeft = Offset(size.width * (.08f + row * .22f), size.height * .11f),
                    size = Size(size.width * .15f, size.height * .18f),
                    cornerRadius = corner
                )
                drawRoundRect(
                    color = blockFill,
                    topLeft = Offset(size.width * (.04f + row * .23f), size.height * .66f),
                    size = Size(size.width * .17f, size.height * .18f),
                    cornerRadius = corner
                )
            }

            repeat(5) { index ->
                val y = size.height * (.18f + index * .16f)
                drawLine(minorRoad, Offset(0f, y), Offset(size.width, y), strokeWidth = 3.dp.toPx())
            }
            repeat(5) { index ->
                val x = size.width * (.12f + index * .19f)
                drawLine(minorRoad, Offset(x, 0f), Offset(x, size.height), strokeWidth = 3.dp.toPx())
            }

            val routePoints = listOf(
                Offset(size.width * .10f, size.height * .78f),
                Offset(size.width * .25f, size.height * .62f),
                Offset(size.width * .43f, size.height * .66f),
                Offset(size.width * .58f, size.height * .46f),
                Offset(size.width * .74f, size.height * .37f),
                Offset(size.width * .90f, size.height * .22f)
            )

            routePoints.zipWithNext().forEach { (start, end) ->
                drawLine(
                    color = roadColor,
                    start = start,
                    end = end,
                    strokeWidth = 22.dp.toPx(),
                    cap = StrokeCap.Round
                )
                drawLine(
                    color = Color.White,
                    start = start,
                    end = end,
                    strokeWidth = 14.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }

            val totalLength = routePoints.zipWithNext().sumOf { (start, end) ->
                segmentLength(start, end).toDouble()
            }.toFloat()
            var remaining = totalLength * animatedProgress.coerceIn(0f, 1f)
            var ambulance = routePoints.first()

            routePoints.zipWithNext().forEach { (start, end) ->
                val length = segmentLength(start, end)
                val drawRatio = when {
                    remaining >= length -> 1f
                    remaining > 0f -> remaining / length
                    else -> 0f
                }
                if (drawRatio > 0f) {
                    val partial = interpolate(start, end, drawRatio)
                    drawLine(
                        color = EmergencyRed,
                        start = start,
                        end = partial,
                        strokeWidth = 8.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                    ambulance = partial
                }
                remaining -= length
            }

            val start = routePoints.first()
            val destination = routePoints.last()
            drawCircle(Success.copy(alpha = .18f), 23.dp.toPx(), start)
            drawCircle(Success, 10.dp.toPx(), start)
            drawCircle(EmergencyRed.copy(alpha = .18f), 25.dp.toPx(), destination)
            drawCircle(EmergencyRed, 12.dp.toPx(), destination)
            drawCircle(Color.White, 4.dp.toPx(), destination)

            drawCircle(EmergencyRed.copy(alpha = .20f), 30.dp.toPx(), ambulance)
            drawRoundRect(
                color = EmergencyRed,
                topLeft = Offset(ambulance.x - 24.dp.toPx(), ambulance.y - 16.dp.toPx()),
                size = Size(48.dp.toPx(), 32.dp.toPx()),
                cornerRadius = CornerRadius(10.dp.toPx(), 10.dp.toPx())
            )
            drawCircle(Color.White, 4.dp.toPx(), Offset(ambulance.x - 13.dp.toPx(), ambulance.y + 13.dp.toPx()))
            drawCircle(Color.White, 4.dp.toPx(), Offset(ambulance.x + 13.dp.toPx(), ambulance.y + 13.dp.toPx()))
            drawLine(Color.White, Offset(ambulance.x - 7.dp.toPx(), ambulance.y), Offset(ambulance.x + 7.dp.toPx(), ambulance.y), strokeWidth = 3.dp.toPx(), cap = StrokeCap.Round)
            drawLine(Color.White, Offset(ambulance.x, ambulance.y - 7.dp.toPx()), Offset(ambulance.x, ambulance.y + 7.dp.toPx()), strokeWidth = 3.dp.toPx(), cap = StrokeCap.Round)
        }
    }
}

@Composable
fun LiveTrackingTimeline(
    steps: List<String>,
    activeStep: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        steps.forEachIndexed { index, step ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Surface(
                        color = if (index <= activeStep) EmergencyRed else Navy700,
                        shape = CircleShape,
                        modifier = Modifier.size(24.dp),
                        border = BorderStroke(1.dp, if (index <= activeStep) EmergencyRed else Outline)
                    ) {
                        if (index < activeStep) {
                            Icon(Icons.Default.Check, null, tint = Color.White, modifier = Modifier.padding(5.dp))
                        }
                    }
                    if (index < steps.lastIndex) {
                        Box(
                            Modifier
                                .width(2.dp)
                                .height(28.dp)
                                .background(if (index < activeStep) EmergencyRed else Outline)
                        )
                    }
                }
                Text(
                    text = step,
                    color = if (index <= activeStep) White else TextMuted,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = if (index == activeStep) FontWeight.ExtraBold else FontWeight.Medium,
                    modifier = Modifier.padding(top = 1.dp)
                )
            }
        }
    }
}

private fun segmentLength(start: Offset, end: Offset): Float {
    val dx = end.x - start.x
    val dy = end.y - start.y
    return sqrt(dx * dx + dy * dy)
}

private fun interpolate(start: Offset, end: Offset, progress: Float): Offset =
    Offset(
        x = start.x + (end.x - start.x) * progress,
        y = start.y + (end.y - start.y) * progress
    )

@Composable
fun RoadmapCard(
    title: String,
    badge: String,
    modifier: Modifier = Modifier
) {
    PremiumCard(modifier, accent = Amber) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(Icons.Default.HealthAndSafety, null, tint = Amber)
                Text(title, color = White, style = MaterialTheme.typography.titleMedium)
            }
            StatusBadge(badge, Amber)
        }
    }
}
