package learn.with.me.auth.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import learn.with.me.Resources
import learn.with.me.auth.presentation.SharedAuthViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun UserInput(
    onClick: (String, String) -> Unit,
    buttonText: String,
    sharedAuthViewModel: SharedAuthViewModel,
    canSubmit: () -> Boolean,
    optionalContent: @Composable ((Modifier) -> Unit) = {}
) {
    val email by sharedAuthViewModel.email.collectAsStateWithLifecycle()
    val password by sharedAuthViewModel.password.collectAsStateWithLifecycle()

    val isPasswordInvalid = password.isNotEmpty() && !sharedAuthViewModel.isPasswordValid()

    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()
    val outlineTextFieldModifier = Modifier
        .fillMaxWidth()
        .onFocusEvent { focusState ->
            if (focusState.isFocused) {
                coroutineScope.launch {
                    bringIntoViewRequester.bringIntoView()
                }
            }
        }

    Image(
        painter = painterResource(Resources.Drawable.computer_image),
        contentDescription = stringResource(Resources.String.logo),
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(size = 24.dp)),
    )
    Spacer(modifier = Modifier.height(32.dp))
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = sharedAuthViewModel::onEmailTextChange,
            singleLine = true,
            modifier = outlineTextFieldModifier,
            label = { Text(stringResource(Resources.String.email)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = !sharedAuthViewModel.isEmailValid() && email.isNotEmpty(),
            supportingText = {}
        )
        OutlinedTextField(
            value = password,
            onValueChange = sharedAuthViewModel::onPasswordTextChange,
            singleLine = true,
            modifier = outlineTextFieldModifier,
            label = { Text(stringResource(Resources.String.password)) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isError = isPasswordInvalid,
            supportingText = {
                if (isPasswordInvalid) {
                    Text(text = stringResource(Resources.String.password_error))
                }
            }
        )
        optionalContent(outlineTextFieldModifier)
    }
    Button(
        onClick = { onClick(email, password) },
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .heightIn(24.dp, 48.dp)
            .bringIntoViewRequester(bringIntoViewRequester),
        enabled = canSubmit()
    ) {
        Text(buttonText)
    }
}