import org.gradle.configurationcache.extensions.capitalized

plugins {
    id("jacoco")
}

jacoco {
    toolVersion = "0.8.10"
}

fun getCoverageExcludeFiles(): List<String> = listOf(
    "**/R.*",
    "**/R$*.*",
    "**/*\$ViewInjector*.*",
    "**/BuildConfig.*",
    "**/Manifest*.*",
    "android/**/*.*",
    "**/*NavDirections*",
    "**/*NavArgs*",
    "**/*Injector.*",
    "**/*Activity.*",
    "**/*Fragment.*",
    "**/*Service.*",
    "**/*Dialog.*",
    "**/*Application*.*",
    "**/*JsonAdapter.*",
    "**/di/**",
    "**/widget/**",
    "**/*ModuleKt.*",
    "**/*View.*",
)
rootProject.extra.set("coverageExcludeFiles", getCoverageExcludeFiles())

tasks.register<JacocoReport>("jacocoTestReport") {
    group = "verification"
    description = "Code coverage report for debug unit tests."

    val variants = listOf("debug", "saltyDebug")

    val classDirectoriesFileTrees = subprojects.map { project ->
        variants.map { variant ->
            val javaDebugTree =
                fileTree(baseDir = "${project.buildDir}/intermediates/javac/$variant/classes/") {
                    exclude(getCoverageExcludeFiles())
                }
            val kotlinDebugTree =
                fileTree(baseDir = "${project.buildDir}/tmp/kotlin-classes/$variant") {
                    exclude(getCoverageExcludeFiles())
                }

            listOf(javaDebugTree, kotlinDebugTree)
        }
    }.flatten()

    val sourceDirectoriesFileTrees = subprojects.map { project ->
        listOf("${project.projectDir}/src/main/java", "${project.projectDir}/src/main/kotlin")
    }

    val executionDataFileTrees = subprojects.map { project ->
        variants.map { variant ->
            "${project.buildDir}/jacoco/test${variant.capitalized()}UnitTest.exec"
        }
    }.flatten()

    classDirectories.setFrom(files(classDirectoriesFileTrees.flatten()))
    sourceDirectories.setFrom(files(sourceDirectoriesFileTrees.flatten()))
    executionData.setFrom(files(executionDataFileTrees))

    reports {
        xml.required.set(true)
        csv.required.set(true)
        html.required.set(true)
    }
}
