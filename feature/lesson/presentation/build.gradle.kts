plugins {
    alias(libs.plugins.learn.with.me.feat.presentation)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
