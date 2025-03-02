package dev.renheyzer.tazalyk.presentation.screens.confirm.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.renheyzer.tazalyk.ui.theme.TazalykTheme

@Composable
fun SmsCodeInput(
    modifier: Modifier = Modifier,
    codeLength: Int = 4,
    onCodeEntered: (String) -> Unit
) {
    var code by remember { mutableStateOf(List(codeLength) { "" }) }
    val focusRequesters = List(codeLength) { remember { FocusRequester() } }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        code.forEachIndexed { index, digit ->
            BasicTextField(
                value = digit,
                onValueChange = { newValue ->
                    if (newValue.length <= 1 && newValue.all { it.isDigit() }) {
                        code = code.toMutableList().apply {
                            this[index] = newValue
                        }
                        if (newValue.isNotEmpty() && index < codeLength - 1) {
                            focusRequesters[index + 1].requestFocus()
                        }
                        if (code.joinToString("").length == codeLength) {
                            onCodeEntered(code.joinToString(""))
                        }
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                textStyle = TazalykTheme.typography.secondaryHeading.copy(textAlign = TextAlign.Center),
                singleLine = true,
                modifier = Modifier
                    .size(50.dp)
                    .border(1.dp, TazalykTheme.colors.borderColor, TazalykTheme.shape.shape)
                    .focusRequester(focusRequesters[index])
                    .onKeyEvent { keyEvent ->
                        if (keyEvent.key == Key.Backspace && keyEvent.type == KeyEventType.KeyUp) {
                            if (digit.isEmpty() && index > 0) {
                                focusRequesters[index - 1].requestFocus()
                            }
                            code = code
                                .toMutableList()
                                .apply {
                                    this[index] = ""
                                }
                            true
                        } else {
                            false
                        }
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSmsCodeInput() {
    TazalykTheme {
        SmsCodeInput(
            modifier = Modifier.wrapContentSize()
        ) { }
    }
}