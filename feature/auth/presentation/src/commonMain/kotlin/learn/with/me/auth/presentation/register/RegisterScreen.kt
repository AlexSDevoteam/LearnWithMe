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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import learn.with.me.Resources
import learn.with.me.auth.presentation.SharedAuthViewModel
import learn.with.me.auth.presentation.components.UserInput
import org.jetbrains.compose.resources.stringResource

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    sharedAuthViewModel: SharedAuthViewModel,
    registerViewModel: RegisterViewModel,
    onRegisterClick: (String, String) -> Unit
) {
    val password by sharedAuthViewModel.password.collectAsStateWithLifecycle()
    val confirmPassword by registerViewModel.confirmPassword.collectAsStateWithLifecycle()

    val confirmPasswordValid = registerViewModel.isConfirmPasswordValid(password)
    val isError = confirmPassword.isNotEmpty() && !confirmPasswordValid

    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        UserInput(
            onClick = { email, password ->
                registerViewModel.register(email, password)
                // TODO Only when result is success
                onRegisterClick(email, password)

            },
            buttonText = stringResource(Resources.String.register),
            sharedAuthViewModel = sharedAuthViewModel,
            canSubmit = { sharedAuthViewModel.areCredentialsValid() && confirmPasswordValid },
            optionalContent = { outlinedTextFieldModifier ->
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = registerViewModel::onConfirmPasswordTextChange,
                    singleLine = true,
                    modifier = outlinedTextFieldModifier,
                    label = { Text(stringResource(Resources.String.confirm_password)) },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    isError = isError,
                    supportingText = {
                        if (isError) {
                            Text(text = stringResource(Resources.String.confirm_password_error))
                        }
                    }
                )
            }
        )
    }
}