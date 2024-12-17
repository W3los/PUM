plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.lista6"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.lista6"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true // Włączenie obsługi Compose
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4" // Zaktualizowana wersja kompilatora Compose
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    // Zależności do Jetpack Compose
    implementation(libs.androidx.activity.compose) // Wymagane dla setContent
    implementation("androidx.compose.ui:ui:1.7.6") // Podstawowe komponenty Compose
    implementation("androidx.navigation:navigation-compose:2.8.5")// Material 3
    implementation(libs.androidx.compose.tooling.preview)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.material)
    implementation(libs.androidx.navigation.compose) // Podgląd
    debugImplementation(libs.androidx.compose.tooling) // Debugowanie Compose


    // Reszta zależności
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.runtime.android)
    implementation("androidx.compose.material3:material3:1.3.1")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}