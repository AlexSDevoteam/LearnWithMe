
plugins {
    alias(libs.plugins.learn.with.me.feature)
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
