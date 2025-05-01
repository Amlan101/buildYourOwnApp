package com.amlan.rapidsplit.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = RapidoYellow,
    onPrimary = RapidoBlack,
    primaryContainer = RapidoYellow.copy(alpha = 0.8f),
    onPrimaryContainer = RapidoBlack,

    secondary = RapidoGrey,
    onSecondary = RapidoWhite,
    secondaryContainer = RapidoGrey.copy(alpha = 0.7f),
    onSecondaryContainer = RapidoWhite,

    tertiary = RapidoBlue,
    onTertiary = RapidoBlack,
    tertiaryContainer = RapidoBlue.copy(alpha = 0.7f),
    onTertiaryContainer = RapidoBlack,

    error = RapidoRed,
    onError = RapidoWhite,

    background = RapidoBackgroundGrey,
    onBackground = RapidoBlack,

    surface = RapidoWhite,
    onSurface = RapidoBlack,

    surfaceVariant = RapidoBackgroundGrey,
    onSurfaceVariant = RapidoGrey
)

private val DarkColorScheme = darkColorScheme(
    primary = RapidoYellow,
    onPrimary = RapidoBlack,
    primaryContainer = RapidoYellow.copy(alpha = 0.7f),
    onPrimaryContainer = RapidoBlack,

    secondary = RapidoGrey.copy(alpha = 0.8f),
    onSecondary = RapidoWhite,
    secondaryContainer = RapidoGrey.copy(alpha = 0.6f),
    onSecondaryContainer = RapidoWhite,

    tertiary = RapidoBlue,
    onTertiary = RapidoWhite,
    tertiaryContainer = RapidoBlue.copy(alpha = 0.6f),
    onTertiaryContainer = RapidoWhite,

    error = RapidoRed,
    onError = RapidoWhite,

    background = RapidoBlack,
    onBackground = RapidoWhite,

    surface = RapidoGrey,
    onSurface = RapidoWhite,

    surfaceVariant = RapidoGrey.copy(alpha = 0.3f),
    onSurfaceVariant = RapidoLightGrey
)

@Composable
fun RapidSplitTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}