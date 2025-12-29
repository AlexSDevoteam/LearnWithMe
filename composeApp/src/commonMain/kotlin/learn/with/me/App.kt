package learn.with.me

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import learn.with.me.navigation.NavigationRoot
import learn.with.me.navigation.Route
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val isLoggedIn = false

    MaterialTheme {
        val startDestination = if (isLoggedIn) {
            Route.Home
        } else {
            Route.Auth
        }

        NavigationRoot(modifier = Modifier, startDestination = startDestination)
    }
}

