import learn.with.me.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class FeaturePresentationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "learn.with.me.feature")
            //plugins need to be in "core" build.gradle
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")
            apply(plugin = "org.jetbrains.compose")

            val compose = extensions.getByType<ComposeExtension>()
            val kotlin = extensions.getByType<KotlinMultiplatformExtension>()

            kotlin.sourceSets.getByName("commonMain").dependencies {
                implementation(compose.dependencies.material3)
                implementation(compose.dependencies.components.resources)
                implementation(libs.findLibrary("jetbrains-lifecycle-viewmodel-nav3").get())
                implementation(libs.findLibrary("jetbrains-navigation3-ui").get())
                implementation(libs.findLibrary("koin-compose-viewmodel").get())
            }
        }
    }
}