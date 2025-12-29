package learn.with.me.auth.presentation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface AuthRoute : NavKey {
    @Serializable
    data object Auth : AuthRoute {
        @Serializable
        data object Login : AuthRoute

        @Serializable
        data object Register : AuthRoute
    }
}
