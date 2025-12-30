@file:OptIn(ExperimentalMaterial3Api::class)

package learn.with.me.auth.presentation.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import learn.with.me.Resources
import learn.with.me.auth.presentation.AuthRoute
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AuthTopAppBar(
    modifier: Modifier = Modifier,
    authBackStack: NavBackStack<NavKey>
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(Resources.String.app_name) + "!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            if (authBackStack.contains(AuthRoute.Auth.Register)) {
                IconButton(
                    onClick = {
                        authBackStack.remove(AuthRoute.Auth.Register)
                    },
                    content = {
                        Icon(
                            painter = painterResource(Resources.Drawable.arrow_back_24),
                            contentDescription = stringResource(Resources.String.back)
                        )
                    }
                )
            }
        },
    )
}