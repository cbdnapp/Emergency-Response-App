package com.cbdn.reports.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = jet,
    onPrimary = goldenrod,
    primaryContainer = jet,
    onPrimaryContainer = goldenrod,

    secondary = eerie_black,
    onSecondary = platinum,
    secondaryContainer = goldenrod,
    onSecondaryContainer = onyx,

//    tertiary = azul,
//    onTertiary = azul,
//    tertiaryContainer = azul,
//    onTertiaryContainer = azul,
//
    background = platinum,
    onBackground = jet,
    surface = platinum,
    onSurface = eerie_black,
//
    surfaceVariant = white,
    onSurfaceVariant = imperial_red,

//    outline = azul,
//    inverseOnSurface = azul,
//    inverseSurface = azul,
//    inversePrimary = azul,
//    surfaceTint = azul,
//    outlineVariant = azul,
//    scrim = azul,
//
//    error = azul,
//    errorContainer = azul,
//    onError = azul,
//    onErrorContainer = azul,
)

private val LightColorScheme = lightColorScheme(
    primary = jet,
    onPrimary = goldenrod,
    primaryContainer = jet,
    onPrimaryContainer = goldenrod,

    secondary = eerie_black,
    onSecondary = platinum,
    secondaryContainer = goldenrod,
    onSecondaryContainer = onyx,

//    tertiary = azul,
//    onTertiary = azul,
//    tertiaryContainer = azul,
//    onTertiaryContainer = azul,
//
    background = platinum,
    onBackground = jet,
    surface = platinum,
    onSurface = eerie_black,
//
    surfaceVariant = white,
    onSurfaceVariant = imperial_red,

//    outline = azul,
//    inverseOnSurface = azul,
//    inverseSurface = azul,
//    inversePrimary = azul,
//    surfaceTint = azul,
//    outlineVariant = azul,
//    scrim = azul,
//
//    error = azul,
//    errorContainer = azul,
//    onError = azul,
//    onErrorContainer = azul,
)

@Composable
fun CBDNReportsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
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
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}