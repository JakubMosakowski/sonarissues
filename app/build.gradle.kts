plugins {
    `android-base-application`
}

android {
    namespace = "com.mosjak.sonarissue.app"
}

dependencies {

    implementation(project(":utils"))
    testImplementation("junit:junit:4.13.2")
}
