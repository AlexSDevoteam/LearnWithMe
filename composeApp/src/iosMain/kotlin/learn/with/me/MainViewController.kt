package learn.with.me

import androidx.compose.ui.window.ComposeUIViewController
import learn.with.me.di.initializeKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initializeKoin() }
) { App() }