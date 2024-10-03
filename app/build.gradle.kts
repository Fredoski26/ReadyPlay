import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.readyplay"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.readyplay"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    val localProperties = gradleLocalProperties(rootDir, providers)
    val apiKey: String = localProperties.getProperty("API_KEY") ?: ""
    val baseUrl: String = localProperties.getProperty("BASE_URL") ?: ""
    val imageUrl: String = localProperties.getProperty("IMAGE_BASE_UR") ?: ""


    buildTypes {
        all {
            buildConfigField("int", "PATCH_VERSION_CODE", "1")
            buildConfigField("String", "IMAGE_BASE_UR", "\"$imageUrl\"")
        }

        getByName("debug") {
            versionNameSuffix = " - debug-1"
            applicationIdSuffix = ".debug"
            buildConfigField("String", "API_KEY", "\"$apiKey\"")
            buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
        }

        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "API_KEY", "\"$apiKey\"")
            buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
        }

       /* release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }*/
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    val composeBom = platform("androidx.compose:compose-bom:2024.09.02")
    implementation(composeBom)

    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.ui:ui-tooling-preview")

    implementation("androidx.core:core-splashscreen:1.0.1")

    implementation ("com.google.accompanist:accompanist-pager:0.22.0-rc")
    implementation ("com.google.accompanist:accompanist-pager-indicators:0.24.13-rc")


    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)

    // Compose dependencies
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.accompanist.flowlayout)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Coroutine Lifecycle Scopes
    implementation(libs.androidx.lifecycle.viewmodel.ktx)





    // RamCosta Navigation
    implementation(libs.compose.destination.core)


    // Navigation animation
    implementation(libs.accompanist.navigation.animation)

    // Accompanist System UI controller
    implementation(libs.accompanist.systemuicontroller)

    // Insets
    implementation(libs.accompanist.insets)

    // Coil
    implementation(libs.coil.compose)



    // Paging Compose
    implementation(libs.androidx.paging.compose)

    // Swipe to refresh
    implementation(libs.accompanist.swiperefresh)

    // DataStore
    implementation(libs.datastore.preferences)
}