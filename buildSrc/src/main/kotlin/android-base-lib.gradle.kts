plugins {
    id("com.android.library")
    kotlin("android")
    id("jacoco")
}

android {
    compileSdk = 31
    defaultConfig {
        minSdk = 21
        targetSdk = 29
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}
