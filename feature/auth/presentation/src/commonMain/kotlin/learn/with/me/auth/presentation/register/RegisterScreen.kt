package learn.with.me.auth.presentation.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import learn.with.me.auth.presentation.SharedAuthViewModel
import learn.with.me.auth.presentation.components.UserInput
import learnwithme.feature.auth.presentation.generated.resources.Res
import learnwithme.feature.auth.presentation.generated.resources.register
import org.jetbrains.compose.resources.stringResource

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    sharedAuthViewModel: SharedAuthViewModel,
    onRegisterClick: (String, String) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        UserInput(
            onClick = onRegisterClick,
            buttonText = stringResource(Res.string.register),
            sharedAuthViewModel = sharedAuthViewModel
        )
    }
}