package learn.with.me.settings.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface SettingsRoute : NavKey {
    @Serializable
    data object Settings : SettingsRoute
}