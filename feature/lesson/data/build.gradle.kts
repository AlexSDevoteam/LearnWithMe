
plugins {
    alias(libs.plugins.learn.with.me.feature)
}

kotlin {
    androidLibrary {
        compileSdk = libs.versions.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
        namespace = "learn.with.me.lesson.data"
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
