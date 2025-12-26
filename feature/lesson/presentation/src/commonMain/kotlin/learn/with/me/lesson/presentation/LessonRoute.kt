package learn.with.me.lesson.presentation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface LessonRoute : NavKey {
    @Serializable
    data object Lesson : LessonRoute {
        @Serializable
        data object LessonList : LessonRoute

        @Serializable
        data object LessonFavorites : LessonRoute

        @Serializable
        data class LessonDetail(val lessonId: String) : LessonRoute
    }
}