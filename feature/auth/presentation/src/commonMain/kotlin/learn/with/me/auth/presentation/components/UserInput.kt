package learn.with.me.auth.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import learn.with.me.auth.presentation.SharedAuthViewModel
import learnwithme.feature.auth.presentation.generated.resources.Res
import learnwithme.feature.auth.presentation.generated.resources.computer_image
import learnwithme.feature.auth.presentation.generated.resources.email
import learnwithme.feature.auth.presentation.generated.resources.logo
import learnwithme.feature.auth.presentation.generated.resources.password
import learnwithme.feature.auth.presentation.generated.resources.password_error
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun UserInput(
    onClick: (String, String) -> Unit,
    buttonText: String,
    sharedAuthViewModel: SharedAuthViewModel
) {
    val email by sharedAuthViewModel.email.collectAsStateWithLifecycle()
    val password by sharedAuthViewModel.password.collectAsStateWithLifecycle()

    val isPasswordInvalid = !sharedAuthViewModel.isPasswordValid() && password.isNotEmpty()

    Image(
        painter = painterResource(Res.drawable.computer_image),
        contentDescription = stringResource(Res.string.logo),
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(size = 24.dp)),
    )
    Spacer(modifier = Modifier.height(32.dp))
    OutlinedTextField(
        value = email,
        onValueChange = sharedAuthViewModel::onEmailTextChange,
        singleLine = true,
        label = { Text(stringResource(Res.string.email)) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        isError = !sharedAuthViewModel.isEmailValid() && email.isNotEmpty()
    )
    Spacer(modifier = Modifier.height(16.dp))
    OutlinedTextField(
        value = password,
        onValueChange = sharedAuthViewModel::onPasswordTextChange,
        singleLine = true,
        label = { Text(stringResource(Res.string.password)) },
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        isError = isPasswordInvalid,
        supportingText = {
            if (isPasswordInvalid) {
                Text(text = stringResource(Res.string.password_error))
            }
        }
    )
    Spacer(modifier = Modifier.height(16.dp))
    Button(
        onClick = { onClick(email, password) },
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .heightIn(24.dp, 48.dp),
        enabled = sharedAuthViewModel.canLogin()
    ) {
        Text(buttonText)
    }
}