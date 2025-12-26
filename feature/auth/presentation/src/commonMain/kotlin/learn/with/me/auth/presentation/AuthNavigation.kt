package learn.with.me.auth.presentation


import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
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
        },
        AuthRoute.Auth.Register
    )
    NavDisplay(
        modifier = modifier,
        backStack = authBackStack,
        transitionSpec = {
            fadeIn(animationSpec = tween(durationMillis = 1000)) togetherWith
                    fadeOut(animationSpec = tween(durationMillis = 1000))
        },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<AuthRoute.Auth.Login> {
                LoginScreen(
                    modifier = modifier,
                    onLogin = onLogin
                )
            }
            entry<AuthRoute.Auth.Register> {
                RegisterScreen(
                    modifier = modifier,
                    onRegister = {
                        authBackStack.remove(AuthRoute.Auth.Register)
                        authBackStack.add(AuthRoute.Auth.Login)
                    }
                )
            }
        }
    )
}