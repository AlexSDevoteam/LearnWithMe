import com.android.build.api.dsl.androidLibrary

plugins {
    alias(libs.plugins.learn.with.me.feature.presentation)
}

kotlin {
    androidLibrary {
        namespace = "learn.with.me.auth.presentation"
    }
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.auth.domain)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
