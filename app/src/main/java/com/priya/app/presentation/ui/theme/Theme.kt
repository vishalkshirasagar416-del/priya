package com.priya.app.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFF7C3AED),
    secondary = Color(0xFF22C55E),
    tertiary = Color(0xFF38BDF8),
    background = Color(0xFFF4F7FF),
    surface = Color(0xFFFFFFFF),
    surfaceVariant = Color(0xFFE9EEF9),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color(0xFF0F172A),
    onSurface = Color(0xFF0F172A),
    onSurfaceVariant = Color(0xFF475569),
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFA78BFA),
    secondary = Color(0xFF34D399),
    tertiary = Color(0xFF7DD3FC),
    background = Color(0xFF070B16),
    surface = Color(0xFF111827),
    surfaceVariant = Color(0xFF1E293B),
    onPrimary = Color(0xFF0F172A),
    onSecondary = Color.Black,
    onBackground = Color(0xFFE2E8F0),
    onSurface = Color(0xFFE2E8F0),
    onSurfaceVariant = Color(0xFFCBD5E1),
)

@Composable
fun PriyaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}
