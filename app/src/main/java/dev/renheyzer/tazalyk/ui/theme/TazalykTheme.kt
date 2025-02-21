package dev.renheyzer.tazalyk.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle

data class TazalykStyle(
    val textSize: TazalykSize,
    val corner: TazalykCorner,
    val isDarkMode: Boolean,
)

data class TazalykColors(
    val primaryText: Color,
    val primaryBackground: Color,
    val secondaryText: Color,
    val secondaryBackground: Color,
    val accentColor: Color,
    val errorColor: Color,
    val onAccentText: Color,
    val borderColor: Color
)

data class TazalykTypography(
    val primaryHeading: TextStyle,
    val secondaryHeading: TextStyle,
    val body: TextStyle,
    val toolbar: TextStyle,
    val button: TextStyle
)

data class TazalykShape(
    val shape: Shape
)

data object TazalykTheme {
    val colors: TazalykColors
        @Composable
        get() = LocalTazalykColors.current

    val typography: TazalykTypography
        @Composable
        get() = LocalTazalykTypography.current

    val shape: TazalykShape
        @Composable
        get() = LocalTazalykShape.current
}

enum class TazalykSize {
    Small, Medium, Big
}

enum class TazalykCorner {
    Small, Medium, Big, Full
}

val LocalTazalykColors = staticCompositionLocalOf<TazalykColors> {
    error("No colors provided")
}

val LocalTazalykTypography = staticCompositionLocalOf<TazalykTypography> {
    error("No fonts provided")
}

val LocalTazalykShape = staticCompositionLocalOf<TazalykShape> {
    error("No shapes provided")
}