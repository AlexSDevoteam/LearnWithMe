plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    alias(libs.plugins.kmpLibrary)
    alias(libs.plugins.serialization)
}

kotlin {
    androidLibrary {
        compileSdk = libs.versions.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
        namespace = "learn.with.me.composeapp"
        experimentalProperties["android.experimental.kmp.enableAndroidResources"] = true
    }
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            implementation(libs.koin.android)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            //Nav3
            implementation(libs.jetbrains.lifecycle.viewmodel.nav3)
            implementation(libs.jetbrains.navigation3.ui)

            //Koin
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            implementation(projects.shared)
            implementation(projects.feature.auth.presentation)
            implementation(projects.feature.auth.domain)
            implementation(projects.feature.lesson.presentation)
            implementation(projects.feature.settings.presentation)
            implementation(projects.di)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}