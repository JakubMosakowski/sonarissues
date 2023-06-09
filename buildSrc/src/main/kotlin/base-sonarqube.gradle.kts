plugins {
    id("org.sonarqube")
}

sonar {
    properties {
        val coverageExcludeFiles = rootProject.extra.get("coverageExcludeFiles") as List<*>

        property("sonar.projectKey", System.getenv("PRIVATE_SONAR_PROJECT_KEY"))
        property("sonar.host.url", System.getenv("PRIVATE_SONAR_HOST_URL"))
        property("sonar.login", System.getenv("PRIVATE_SONAR_TOKEN"))
        property("sonar.core.codeCoveragePlugin", "jacoco")
        property(
            "sonar.coverage.jacoco.xmlReportPaths",
            "${project.buildDir}/reports/jacoco/jacocoTestReport/jacocoTestReport.xml"
        )
        property(
            "sonar.coverage.exclusions",
            coverageExcludeFiles.joinToString(separator = ",")
        )
    }
}
