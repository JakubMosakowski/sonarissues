plugins {
    id("com.android.application")
    kotlin("android")
    id("jacoco")
}

android {
    compileSdk = 31
    defaultConfig {
        versionName = "1.0.0"
        versionCode = 1
        minSdk = 21
        targetSdk = 29
    }

    packagingOptions {
        resources.excludes.add("META-INF/*")
        resources.excludes.add("**/LICENSE.txt")
        resources.excludes.add("**/README.txt")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}
