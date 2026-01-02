import com.android.build.api.dsl.androidLibrary

plugins {
    alias(libs.plugins.learn.with.me.feature)
}

kotlin {
    androidLibrary {
        namespace = "learn.with.me.auth.domain"
    }
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
