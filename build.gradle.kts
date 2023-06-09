buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.3.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.20")
        classpath("org.jacoco:org.jacoco.agent:0.8.10")
    }
}

apply(from = "$rootDir/jacoco/configure-jacoco.gradle.kts")
apply(plugin = "base-sonarqube")
