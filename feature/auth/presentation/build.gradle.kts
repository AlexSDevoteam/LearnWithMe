
plugins {
    alias(libs.plugins.learn.with.me.feat.presentation)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.auth.domain)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
