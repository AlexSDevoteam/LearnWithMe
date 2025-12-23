package learn.with.me.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route : NavKey {

    @Serializable
    data object Auth : Route {
        @Serializable
        data object Login : Route

        @Serializable
        data object Register : Route
    }

    @Serializable
    data object Lesson : Route {
        @Serializable
        data object LessonList : Route
        @Serializable
        data object LessonFavorites : Route

        @Serializable
        data class LessonDetail(val lessonId: String) : Route


    }

    @Serializable
    data object Settings : Route
}