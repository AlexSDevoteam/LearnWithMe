import com.android.build.api.dsl.androidLibrary

plugins {
    alias(libs.plugins.learn.with.me.feature.presentation)
}

kotlin {
    androidLibrary {
        namespace = "learn.with.me.lesson.presentation"
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
