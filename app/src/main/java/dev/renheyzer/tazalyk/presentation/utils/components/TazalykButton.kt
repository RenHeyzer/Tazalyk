package dev.renheyzer.tazalyk.presentation.utils.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.renheyzer.tazalyk.ui.theme.TazalykTheme

@Composable
fun TazalykButton(
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    ElevatedButton(
        onClick = onClick,
        modifier = Modifier
            .widthIn(min = 150.dp, max = 320.dp)
            .height(76.dp)
            .padding(16.dp),
        enabled = enabled,
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 2.dp),
        shape = TazalykTheme.shape.shape,
        colors = ButtonDefaults.elevatedButtonColors(containerColor = TazalykTheme.colors.accentColor)
    ) {
        Text(
            text = text,
            color = TazalykTheme.colors.onAccentText,
            style = TazalykTheme.typography.button,
        )
    }
}