package learn.with.me.auth.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import learn.with.me.Resources
import learn.with.me.auth.presentation.SharedAuthViewModel
import learn.with.me.auth.presentation.components.UserInput
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    sharedAuthViewModel: SharedAuthViewModel,
    onLoginClick: (String, String) -> Unit,
    onRegisterClick: () -> Unit
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        UserInput(
            onClick = onLoginClick,
            buttonText = stringResource(Resources.String.login),
            sharedAuthViewModel = sharedAuthViewModel,
            canSubmit = sharedAuthViewModel::canLogin
        )
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(Resources.String.do_not_have_account))
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(Resources.String.register),
                textDecoration = TextDecoration.Underline,
                color = Color.Blue,
                modifier = Modifier.clickable {
                    onRegisterClick()
                }
            )
        }
    }
}