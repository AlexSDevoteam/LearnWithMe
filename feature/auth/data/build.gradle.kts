
plugins {
    alias(libs.plugins.learn.with.me.feature)
}

kotlin {
    androidLibrary {
        namespace = "learn.with.me.auth.data"
        compileSdk = libs.versions.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        androidMain.dependencies {
            implementation(libs.firebase.auth.android)
        }
        commonMain.dependencies {
            implementation(libs.firebase.auth)

            implementation(projects.feature.auth.domain)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
