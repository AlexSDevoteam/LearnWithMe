package learn.with.me.lesson.presentation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import learn.with.me.SERVER_PORT

@Composable
fun LessonNavigation(modifier: Modifier = Modifier) {
    val lessonBackStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(LessonRoute.Lesson.LessonList::class, LessonRoute.Lesson.LessonList.serializer())
                    subclass(LessonRoute.Lesson.LessonFavorites::class, LessonRoute.Lesson.LessonFavorites.serializer())
                    subclass(LessonRoute.Lesson.LessonDetail::class, LessonRoute.Lesson.LessonDetail.serializer())
                }
            }
        },
        LessonRoute.Lesson.LessonList
    )
    NavDisplay(
        modifier = modifier,
        backStack = lessonBackStack,
        transitionSpec = {
            fadeIn(animationSpec = tween(durationMillis = 1000)) togetherWith
                    fadeOut(animationSpec = tween(durationMillis = 1000))
        },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<LessonRoute.Lesson.LessonList> {
                Box(
                    modifier = modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Lesson Navigation Screen $SERVER_PORT")
                }
            }
        }
    )
}