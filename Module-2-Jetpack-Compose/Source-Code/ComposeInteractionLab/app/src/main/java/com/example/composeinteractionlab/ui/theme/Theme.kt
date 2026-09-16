package com.example.composeinteractionlab.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFF365E9D),
    secondary = Color(0xFF4E6356),
    tertiary = Color(0xFF67587A),
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFAEC6FF),
    secondary = Color(0xFFB5CCBB),
    tertiary = Color(0xFFD3BFE6),
)

@Composable
fun PortfolioTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) DarkColors else LightColors,
        content = content,
    )
}
