plugins {
    id("org.sonarqube")
}

sonar {
    properties {
        val coverageExcludeFiles = rootProject.extra.get("coverageExcludeFiles") as List<*>

        property("sonar.projectKey", System.getenv("SONAR_PROJECT_KEY2"))
        property("sonar.host.url", System.getenv("SONAR_URL2"))
        property("sonar.login", System.getenv("SONAR_TOKEN2"))
        property("sonar.core.codeCoveragePlugin", "jacoco")
        property(
            "sonar.coverage.jacoco.xmlReportPaths",
            "${project.buildDir}/reports/jacoco/jacocoTestReport/jacocoEnterpriseTestReport.xml"
        )
        property(
            "sonar.coverage.exclusions",
            coverageExcludeFiles.joinToString(separator = ",")
        )
    }
}
