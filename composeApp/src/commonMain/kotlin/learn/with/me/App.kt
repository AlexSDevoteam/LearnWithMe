package learn.with.me

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import learn.with.me.auth.domain.auth.AuthRepository
import learn.with.me.navigation.NavigationRoot
import learn.with.me.navigation.Route
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
@Preview
fun App() {
    val authRepository = koinInject<AuthRepository>()
    val isLoggedIn = remember {
        authRepository.isUserLoggedIn
    }

    MaterialTheme {
        val startDestination = if (isLoggedIn) {
            Route.Home
        } else {
            Route.Auth
        }

        NavigationRoot(modifier = Modifier, startDestination = startDestination)
    }
}

