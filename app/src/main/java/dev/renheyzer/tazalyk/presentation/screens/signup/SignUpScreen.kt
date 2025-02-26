package dev.renheyzer.tazalyk.presentation.screens.signup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.renheyzer.tazalyk.R
import dev.renheyzer.tazalyk.components.signup.SignUpComponent
import dev.renheyzer.tazalyk.presentation.utils.MaskVisualTransformation
import dev.renheyzer.tazalyk.presentation.utils.NumberDefaults
import dev.renheyzer.tazalyk.presentation.utils.OutlinedErrorTextField
import dev.renheyzer.tazalyk.ui.theme.TazalykTheme

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    component: SignUpComponent,
) {

    val uiState by component.uiState.collectAsState()
    var inputValue by remember {
        mutableStateOf(
            TextFieldValue(uiState.inputValue, TextRange(uiState.cursorPosition))
        )
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.20f))
        Text(
            modifier = Modifier.padding(bottom = 32.dp),
            text = stringResource(R.string.sign_up),
            color = TazalykTheme.colors.primaryText,
            style = TazalykTheme.typography.secondaryHeading,
        )
        Spacer(modifier = Modifier.weight(0.15f))

        OutlinedErrorTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = inputValue,
            onValueChange = {
                if (it.text.length <= NumberDefaults.INPUT_LENGTH) {
                    inputValue = it
                    component.updateInputValue(it.text)
                    component.updateCursorPosition(it.selection.start)
                }
            },
            isError = false,
            errorText = "Empty",
            prefix = {
                if (uiState.isPhoneInput) {
                    Text(
                        text = stringResource(R.string.phone_prefix),
                        color = TazalykTheme.colors.primaryText,
                        style = TazalykTheme.typography.body
                    )
                }
            },
            label = {
                Text(
                    if (uiState.isPhoneInput) stringResource(R.string.phone_number) else stringResource(
                        R.string.email
                    ),
                    color = TazalykTheme.colors.primaryText,
                    style = TazalykTheme.typography.body
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = if (uiState.isPhoneInput) KeyboardType.Phone else KeyboardType.Email),
            textStyle = TazalykTheme.typography.body,
            shape = TazalykTheme.shape.shape,
            visualTransformation = MaskVisualTransformation(NumberDefaults.MASK)
        )

        ElevatedButton(
            onClick = { component.onSignUpClick(uiState) },
            modifier = Modifier
                .fillMaxWidth()
                .height(76.dp)
                .padding(16.dp),
            enabled = uiState.inputValue.isNotBlank(),
            elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 2.dp),
            shape = TazalykTheme.shape.shape,
            colors = ButtonDefaults.elevatedButtonColors(containerColor = TazalykTheme.colors.accentColor)
        ) {
            Text(
                text = stringResource(R.string.next),
                color = TazalykTheme.colors.onAccentText,
                style = TazalykTheme.typography.button,
            )
        }

        Text(
            text = if (uiState.isPhoneInput) stringResource(R.string.sign_up_with_email) else stringResource(
                R.string.sign_up_with_phone
            ),
            modifier = Modifier
                .padding(16.dp)
                .clickable { component.changeInput(!uiState.isPhoneInput) },
            color = TazalykTheme.colors.secondaryText,
            style = TazalykTheme.typography.body,
            textAlign = TextAlign.Center
        )

        Text(
            text = stringResource(R.string.sign_in),
            modifier = Modifier
                .padding(16.dp)
                .clickable { },
            color = TazalykTheme.colors.secondaryText,
            style = TazalykTheme.typography.body,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(0.35f))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignUpContent() {
    TazalykTheme {
        SignUpScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            component = PreviewSignUpComponent(),
        )
    }
}