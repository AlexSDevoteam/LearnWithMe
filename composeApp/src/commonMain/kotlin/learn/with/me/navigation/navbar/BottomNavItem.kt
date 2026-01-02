package learn.with.me.navigation.navbar

import learn.with.me.Resources
import learn.with.me.lesson.presentation.navigation.LessonRoute
import learn.with.me.settings.presentation.navigation.SettingsRoute
import learnwithme.composeapp.generated.resources.Res
import learnwithme.composeapp.generated.resources.outline_favorite_24
import learnwithme.composeapp.generated.resources.outline_school_24
import learnwithme.composeapp.generated.resources.outline_settings_24
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class BottomNavItem(
    val icon: DrawableResource,
    val title: StringResource
)

val TOP_LEVEL_DESTINATIONS = mapOf(
    LessonRoute.Lesson.List to BottomNavItem(
        icon = Res.drawable.outline_school_24,
        title = Resources.String.lessons
    ),
    LessonRoute.Lesson.Favorites to BottomNavItem(
        icon = Res.drawable.outline_favorite_24,
        title = Resources.String.favorites
    ),
    SettingsRoute.Settings to BottomNavItem(
        icon = Res.drawable.outline_settings_24,
        title = Resources.String.settings
    )
)