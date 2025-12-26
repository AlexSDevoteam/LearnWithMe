package learn.with.me.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import learn.with.me.auth.presentation.AuthNavigation
import learn.with.me.lesson.presentation.navigation.LessonNavigation
import learn.with.me.lesson.presentation.navigation.LessonRoute
import learn.with.me.navigation.navbar.HomeNavigationBar
import learn.with.me.navigation.navbar.TOP_LEVEL_DESTINATIONS
import learn.with.me.settings.presentation.SettingsScreen
import learn.with.me.settings.presentation.navigation.SettingsRoute

@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {
    val navigationState = rememberNavigationState(
        startRoute = LessonRoute.Lesson.List,
        topLevelRoutes = TOP_LEVEL_DESTINATIONS.keys
    )
    val navigator = remember {
        Navigator(navigationState)
    }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            HomeNavigationBar(
                selectedKey = navigationState.topLevelRoute,
                onSelectKey = { navigator.navigate(it) }
            )
        }
    ) {
        NavDisplay(
            modifier = modifier,
            onBack = navigator::goBack,
            transitionSpec = {
                slideInHorizontally(animationSpec = tween(durationMillis = 500)) { it } +
                        fadeIn() togetherWith slideOutHorizontally { -it } + fadeOut()
            },
            popTransitionSpec = {
                slideInHorizontally { -it } + fadeIn() togetherWith
                        slideOutHorizontally { it } + fadeOut()
                //  fadeIn(animationSpec = tween(durationMillis = 1000)) togetherWith fadeOut(animationSpec = tween(durationMillis = 1000))
            },
            predictivePopTransitionSpec = {
                slideInHorizontally { -it } + fadeIn() togetherWith
                        slideOutHorizontally { it } + fadeOut()
            },
            entries = navigationState.toEntries(
                entryProvider {
                    entry<LessonRoute.Lesson.List> {
                        AuthNavigation(
                            onLogin = {
                                navigator.navigate(LessonRoute.Lesson.List)
                            }
                        )
                    }
                    entry<LessonRoute.Lesson.Favorites> {
                        LessonNavigation()
//                        LessonFavoritesScreen()
                    }
                    entry<SettingsRoute.Settings> {
                        SettingsScreen()
                    }
                }
            )
        )
    }
}