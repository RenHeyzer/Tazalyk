package dev.renheyzer.tazalyk.presentation.screens.confirm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.renheyzer.tazalyk.R
import dev.renheyzer.tazalyk.components.confirm.ConfirmComponent
import dev.renheyzer.tazalyk.presentation.screens.confirm.components.SmsCodeInput
import dev.renheyzer.tazalyk.ui.theme.TazalykTheme

@Composable
fun ConfirmScreen(
    modifier: Modifier = Modifier,
    component: ConfirmComponent
) {
 Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.10f))
        Text(
            modifier = Modifier.padding(16.dp),
            text = stringResource(R.string.confirm),
            color = TazalykTheme.colors.primaryText,
            style = TazalykTheme.typography.secondaryHeading,
        )

        Text(
            text = with(component.arguments) {
                stringResource(
                    R.string.confirm_description,
                    stringResource(if (isPhoneInput) R.string.phone_number else R.string.email)
                        .replaceFirstChar { it.lowercase() },
                    inputValue,
                    stringResource(if (isPhoneInput) R.string.sms else R.string.email_sent)
                )
            },
            color = TazalykTheme.colors.secondaryText,
            style = TazalykTheme.typography.body,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(0.15f))
        SmsCodeInput(modifier = Modifier.wrapContentSize()) { code ->
            component.onCodeEntered(code)
        }

        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(R.string.resend_code),
            color = TazalykTheme.colors.primaryText,
            style = TazalykTheme.typography.body,
            textDecoration = TextDecoration.Underline
        )

        Spacer(modifier = Modifier.weight(0.35f))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewConfirmScreen() {
    TazalykTheme {
        ConfirmScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            component = PreviewConfirmComponent()
        )
    }
}