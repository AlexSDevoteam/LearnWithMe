plugins {
    alias(libs.plugins.learn.with.me.library)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            implementation(projects.feature.auth.data)
            implementation(projects.feature.auth.domain)
            implementation(projects.feature.auth.presentation)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
