plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.lista4"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.lista4"
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
    implementation(libs.androidx.compose.ui) // Podstawowe komponenty Compose
    implementation(libs.androidx.compose.material3)// Material 3
    implementation(libs.androidx.compose.tooling.preview) // Podgląd
    debugImplementation(libs.androidx.compose.tooling) // Debugowanie Compose

    // Reszta zależności
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.runtime.android)
    implementation(libs.androidx.material3.android)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}