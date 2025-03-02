package dev.renheyzer.tazalyk.presentation.screens.createpassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.renheyzer.tazalyk.R
import dev.renheyzer.tazalyk.components.createpassword.CreatePasswordComponent
import dev.renheyzer.tazalyk.presentation.utils.components.OutlinedErrorTextField
import dev.renheyzer.tazalyk.presentation.utils.components.TazalykButton
import dev.renheyzer.tazalyk.ui.theme.TazalykTheme

@Composable
fun CreatePasswordScreen(
    modifier: Modifier = Modifier,
    component: CreatePasswordComponent
) {

    val uiState by component.uiState.collectAsState()
    var password by remember { mutableStateOf(TextFieldValue(uiState.password)) }
    var confirmPassword by remember { mutableStateOf(TextFieldValue(uiState.confirmPassword)) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.20f))
        Text(
            text = stringResource(R.string.create_password),
            color = TazalykTheme.colors.primaryText,
            style = TazalykTheme.typography.secondaryHeading,
        )
        Spacer(modifier = Modifier.weight(0.20f))

        OutlinedErrorTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            value = password,
            onValueChange = {
                password = it
                component.updatePassword(it.text)
            },
            isError = uiState.errorMessage.isNotBlank(),
            errorText = uiState.errorMessage,
            label = { Text(stringResource(R.string.password)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            shape = TazalykTheme.shape.shape,
        )

        OutlinedErrorTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                component.updateConfirmPassword(it.text)
            },
            isError = uiState.errorMessage.isNotBlank(),
            errorText = uiState.errorMessage,
            label = { Text(stringResource(R.string.confirm_password)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            shape = TazalykTheme.shape.shape,
        )

        TazalykButton(
            text = stringResource(R.string.confirm_text),
            enabled = uiState.password.isNotBlank() && uiState.confirmPassword.isNotBlank()
        ) {
            component.onConfirmPasswordClick()
        }
        Spacer(modifier = Modifier.weight(0.35f))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCreatePasswordScreen() {
    TazalykTheme {
        CreatePasswordScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            component = PreviewCreatePasswordComponent()
        )
    }
}