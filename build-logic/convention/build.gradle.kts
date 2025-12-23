import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}
group = "learn.with.me.buildlogic"
java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}
kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_21
    }
}
dependencies{
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.android.gradle.api.plugin)
//    compileOnly(libs.android.tools.common)
    compileOnly(libs.ksp.gradle.plugin)
}
gradlePlugin{
    plugins{
        register("library") {
            id = libs.plugins.learn.with.me.library.get().pluginId
            implementationClass = "LibraryConventionPlugin"
        }
        register("feature") {
            id = libs.plugins.learn.with.me.feature.get().pluginId
            implementationClass = "FeatureConventionPlugin"
        }
    }
}
