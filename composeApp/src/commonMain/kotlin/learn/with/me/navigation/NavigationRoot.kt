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
import learn.with.me.auth.presentation.AuthNavigation
import learn.with.me.auth.presentation.AuthRoute
import learn.with.me.lesson.presentation.LessonNavigation
import learn.with.me.lesson.presentation.LessonRoute

@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {
    val rootBackStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(AuthRoute.Auth::class, AuthRoute.Auth.serializer())
                    subclass(LessonRoute.Lesson::class, LessonRoute.Lesson.serializer())
                }
            }
        },
        AuthRoute.Auth
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
            entry<AuthRoute.Auth> {
                AuthNavigation(
                    onLogin = {
                        rootBackStack.add(LessonRoute.Lesson)
                    }
                )
            }
            entry<LessonRoute.Lesson> {
                LessonNavigation()
            }
        }
    )
}