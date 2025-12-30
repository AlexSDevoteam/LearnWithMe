package learn.with.me.auth.presentation


import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import learn.with.me.auth.presentation.login.LoginScreen
import learn.with.me.auth.presentation.register.RegisterScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthNavigation(
    modifier: Modifier = Modifier,
    onLogin: () -> Unit,
) {
    val authBackStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(AuthRoute.Auth.Login::class, AuthRoute.Auth.Login.serializer())
                    subclass(AuthRoute.Auth.Register::class, AuthRoute.Auth.Register.serializer())
                }
            }
        }, AuthRoute.Auth.Login
    )

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Box(
                    modifier = modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Learn with me!",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            })
        }) {
        NavDisplay(
            modifier = modifier,
            backStack = authBackStack,
            transitionSpec = {
                fadeIn(animationSpec = tween(durationMillis = 500)) togetherWith fadeOut(
                    animationSpec = tween(
                        durationMillis = 500
                    )
                )
            }, entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
            ), entryProvider = entryProvider {
                entry<AuthRoute.Auth.Login> {

                    LoginScreen(
                        modifier = modifier,
                        onLoginClick = { _, _ ->
                            onLogin()
                        }, onRegisterClick = {
                            authBackStack.remove(AuthRoute.Auth.Login)
                            authBackStack.add(AuthRoute.Auth.Register)
                        })
                }
                entry<AuthRoute.Auth.Register> {
                    RegisterScreen(
                        modifier = modifier,
                        onLogin = {
                            authBackStack.remove(AuthRoute.Auth.Register)
                            authBackStack.add(AuthRoute.Auth.Login)
                        })
                }
            })
    }
}