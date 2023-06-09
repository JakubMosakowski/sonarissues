plugins {
    `android-base-application`
}

android {
    namespace = "com.mosjak.sonarissue.app"

    flavorDimensions += "taste"
    productFlavors {
        create("sweet")
        create("salty")
    }
}

dependencies {

    implementation(project(":utils"))
    testImplementation("junit:junit:4.13.2")
}
