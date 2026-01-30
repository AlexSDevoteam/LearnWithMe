
plugins {
    alias(libs.plugins.learn.with.me.feature)
}

kotlin {
    androidLibrary {
        namespace = "learn.with.me.auth.domain"
        compileSdk = libs.versions.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
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
