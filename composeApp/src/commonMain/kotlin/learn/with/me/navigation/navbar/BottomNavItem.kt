package learn.with.me.navigation.navbar

import learn.with.me.lesson.presentation.navigation.LessonRoute
import learn.with.me.settings.presentation.navigation.SettingsRoute
import learnwithme.composeapp.generated.resources.Res
import learnwithme.composeapp.generated.resources.outline_favorite_24
import learnwithme.composeapp.generated.resources.outline_school_24
import learnwithme.composeapp.generated.resources.outline_settings_24
import org.jetbrains.compose.resources.DrawableResource

data class BottomNavItem(
    val icon: DrawableResource,
    val title: String
)

val TOP_LEVEL_DESTINATIONS = mapOf(
    LessonRoute.Lesson.List to BottomNavItem(
        icon = Res.drawable.outline_school_24,
        title = "Lessons"
    ),
    LessonRoute.Lesson.Favorites to BottomNavItem(
        icon = Res.drawable.outline_favorite_24,
        title = "Favorites"
    ),
    SettingsRoute.Settings to BottomNavItem(
        icon = Res.drawable.outline_settings_24,
        title = "Settings"
    )
)