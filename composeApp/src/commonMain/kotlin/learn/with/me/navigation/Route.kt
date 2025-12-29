package learn.with.me.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface Route : NavKey {
    @Serializable
    data object Auth : Route

    @Serializable
    data object Home : Route
}