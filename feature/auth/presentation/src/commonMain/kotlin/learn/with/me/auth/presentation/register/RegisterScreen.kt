package learn.with.me.auth.presentation.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import learn.with.me.Resources
import learn.with.me.auth.presentation.SharedAuthViewModel
import learn.with.me.auth.presentation.components.UserInput
import org.jetbrains.compose.resources.stringResource

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    sharedAuthViewModel: SharedAuthViewModel,
    onRegisterClick: (String, String) -> Unit
) {
    var confirmPassword by remember { mutableStateOf("") }
    val isConfirmPasswordInvalid =
        confirmPassword.isNotEmpty() && !sharedAuthViewModel.isConfirmPasswordValid(confirmPassword)

    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        UserInput(
            onClick = onRegisterClick,
            buttonText = stringResource(Resources.String.register),
            sharedAuthViewModel = sharedAuthViewModel,
            canSubmit = { sharedAuthViewModel.canRegister(confirmPassword) },
            optionalContent = { outlinedTextFieldModifier ->
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    singleLine = true,
                    modifier = outlinedTextFieldModifier,
                    label = { Text(stringResource(Resources.String.confirm_password)) },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    isError = isConfirmPasswordInvalid,
                    supportingText = {
                        if (isConfirmPasswordInvalid) {
                            Text(text = stringResource(Resources.String.confirm_password_error))
                        }
                    }
                )
            }
        )
    }
}