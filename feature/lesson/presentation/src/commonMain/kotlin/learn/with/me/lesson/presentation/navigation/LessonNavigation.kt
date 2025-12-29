package learn.with.me.lesson.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
                    subclass(
                        LessonRoute.Lesson.Favorites::class,
                        LessonRoute.Lesson.Favorites.serializer()
                    )
                    subclass(
                        LessonRoute.Lesson.Detail::class,
                        LessonRoute.Lesson.Detail.serializer()
                    )
                }
            }
        },
        LessonRoute.Lesson.Favorites
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
            entry<LessonRoute.Lesson.Detail> {
                Box(
                    modifier = modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Lesson Detail Screen ${it.lessonId} $SERVER_PORT")
                }
            }
            entry<LessonRoute.Lesson.Favorites> {
                Box(
                    modifier = modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        Text("Lesson Favorites Screen")
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { lessonBackStack.add(LessonRoute.Lesson.Detail("123")) }
                        ) {
                            Text("Details Screen")
                        }
                    }
                }
            }
        }
    )
}