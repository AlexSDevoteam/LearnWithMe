package learn.with.me

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import learn.with.me.navigation.NavigationRoot
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        NavigationRoot()
    }
}