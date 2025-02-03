package dev.renheyzer.tazalyk.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

data class TazalykStyle(
    val textSize: TazalykSize,
    val isDarkMode: Boolean
)

data class TazalykColors(
    val primaryText: Color,
    val primaryBackground: Color,
    val secondaryText: Color,
    val secondaryBackground: Color,
    val accentColor: Color,
    val errorColor: Color
)

data class TazalykTypography(
    val primaryHeading: TextStyle,
    val secondaryHeading: TextStyle,
    val body: TextStyle,
    val toolbar: TextStyle
)

data object TazalykTheme {
    val colors: TazalykColors
        @Composable
        get() = LocalTazalykColors.current

    val typography: TazalykTypography
        @Composable
        get() = LocalTazalykTypography.current
}

enum class TazalykSize {
    Small, Medium, Big
}

val LocalTazalykColors = staticCompositionLocalOf<TazalykColors> {
    error("No colors provided")
}

val LocalTazalykTypography = staticCompositionLocalOf<TazalykTypography> {
    error("No fonts provided")
}
