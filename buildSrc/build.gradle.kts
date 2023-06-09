import org.gradle.kotlin.dsl.`kotlin-dsl`
plugins {
    `kotlin-dsl`
}
repositories {
    google()
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
}

dependencies {
    gradleApi()
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.20")
    implementation("com.android.tools.build:gradle:7.3.1")
    implementation("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:4.2.0.3129")
}

kotlinDslPluginOptions {
    jvmTarget.set("11")
}
