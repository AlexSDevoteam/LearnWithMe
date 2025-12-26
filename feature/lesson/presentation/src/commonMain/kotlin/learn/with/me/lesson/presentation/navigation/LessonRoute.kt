package learn.with.me.lesson.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface LessonRoute : NavKey {
    @Serializable
    data object Lesson : LessonRoute {
        @Serializable
        data object List : LessonRoute

        @Serializable
        data object Favorites : LessonRoute

        @Serializable
        data class Detail(val lessonId: String) : LessonRoute
    }
}