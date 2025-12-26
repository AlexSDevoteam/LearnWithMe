package learn.with.me.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import learn.with.me.auth.learn.with.me.presentation.AuthNavigation
import learn.with.me.lesson.presentation.LessonNavigation

@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {
    val rootBackStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(Route.Auth::class, Route.Auth.serializer())
                    subclass(Route.Lesson::class, Route.Lesson.serializer())
                }
            }
        },
        Route.Auth
    )
    NavDisplay(
        modifier = modifier,
        backStack = rootBackStack,
        transitionSpec = {
            fadeIn(animationSpec = tween(durationMillis = 1000)) togetherWith
                    fadeOut(animationSpec = tween(durationMillis = 1000))
        },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<Route.Auth> {
                AuthNavigation(
                    onLogin = {
                        rootBackStack.add(Route.Lesson)
                    }
                )
            }
            entry<Route.Lesson> {
                LessonNavigation()
            }
        }
    )
}