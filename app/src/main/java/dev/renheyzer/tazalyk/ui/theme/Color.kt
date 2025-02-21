package dev.renheyzer.tazalyk.ui.theme

import androidx.compose.ui.graphics.Color

val baseLightPalette = TazalykColors(
    primaryText = Color.Black,
    primaryBackground = Color.White,
    secondaryText = Color.DarkGray,
    secondaryBackground = Color(0xFF2196F3),
    accentColor = Color(0xFF34B238),
    errorColor = Color.Red,
    onAccentText = Color.White,
    borderColor = Color.Black
)

val baseDarkPalette = TazalykColors(
    primaryText = Color.White,
    primaryBackground = Color.Black,
    secondaryText = Color.LightGray,
    secondaryBackground = Color(0xFF2196F3),
    accentColor = Color(0xFF02A865),
    errorColor = Color(0xFFE91E63),
    onAccentText = Color.White,
    borderColor = Color(0xFF02A865),
)