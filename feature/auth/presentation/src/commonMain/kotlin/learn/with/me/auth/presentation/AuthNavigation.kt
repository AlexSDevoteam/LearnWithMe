package learn.with.me.auth.presentation


import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import learn.with.me.auth.presentation.components.AuthTopAppBar
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

    val sharedAuthViewModel = SharedAuthViewModel()

    Scaffold(
        topBar = {
            AuthTopAppBar(authBackStack = authBackStack)
        }) { paddingValues ->
        NavDisplay(
            modifier = modifier.imePadding(),
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
                        modifier = modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .consumeWindowInsets(paddingValues),
                        sharedAuthViewModel = sharedAuthViewModel,
                        onLoginClick = { _, _ ->
                            onLogin()
                        },
                        onRegisterClick = {
                            authBackStack.add(AuthRoute.Auth.Register)
                        })
                }
                entry<AuthRoute.Auth.Register> {
                    RegisterScreen(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .consumeWindowInsets(paddingValues),
                        sharedAuthViewModel = sharedAuthViewModel,
                        onRegisterClick = { _, _ ->
                            onLogin()
                        })
                }
            })
    }
}