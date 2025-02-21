package dev.renheyzer.tazalyk.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TazalykTheme(
    textSize: TazalykSize = TazalykSize.Medium,
    corner: TazalykCorner = TazalykCorner.Medium,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> baseDarkPalette
        else -> baseLightPalette
    }

    val typography = TazalykTypography(
        primaryHeading = TextStyle(
            fontSize = when (textSize) {
                TazalykSize.Small -> 24.sp
                TazalykSize.Medium -> 28.sp
                TazalykSize.Big -> 32.sp
            },
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Bold
        ),
        secondaryHeading = TextStyle(
            fontSize = when (textSize) {
                TazalykSize.Small -> 20.sp
                TazalykSize.Medium -> 24.sp
                TazalykSize.Big -> 28.sp
            },
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Medium
        ),
        body = TextStyle(
            fontSize = when (textSize) {
                TazalykSize.Small -> 14.sp
                TazalykSize.Medium -> 16.sp
                TazalykSize.Big -> 18.sp
            },
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Normal
        ),
        toolbar = TextStyle(
            fontSize = when (textSize) {
                TazalykSize.Small -> 14.sp
                TazalykSize.Medium -> 16.sp
                TazalykSize.Big -> 18.sp
            },
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Medium
        ),
        button = TextStyle(
            fontSize = when (textSize) {
                TazalykSize.Small -> 14.sp
                TazalykSize.Medium -> 16.sp
                TazalykSize.Big -> 18.sp
            },
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Medium
        )
    )

    val shape = TazalykShape(
        shape = when (corner) {
            TazalykCorner.Small -> RoundedCornerShape(8.dp)
            TazalykCorner.Medium -> RoundedCornerShape(12.dp)
            TazalykCorner.Big -> RoundedCornerShape(16.dp)
            TazalykCorner.Full -> CircleShape
        }
    )

    CompositionLocalProvider(
        LocalTazalykColors provides colorScheme,
        LocalTazalykTypography provides typography,
        LocalTazalykShape provides shape,
        content = content
    )
}