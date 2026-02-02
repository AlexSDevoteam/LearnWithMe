
plugins {
    alias(libs.plugins.learn.with.me.feature)
}

kotlin {
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
