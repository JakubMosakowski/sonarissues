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
    "**/presentation/**/*Adapter.*",
    "**/presentation/**/*AdapterImpl.*",
    "**/*View.*",
    "**/network/rest/model/**",
)
rootProject.extra.set("coverageExcludeFiles", getCoverageExcludeFiles())

tasks.register<JacocoReport>("customJacocoTestReport") {
    group = "verification"
    description = "Code coverage report for debug unit tests."

    val classDirectoriesFileTrees = subprojects.map { project ->
        val javaDebugTree =
            fileTree(baseDir = "${project.buildDir}/intermediates/javac/debug/classes/") {
                exclude(getCoverageExcludeFiles())
            }
        val kotlinDebugTree =
            fileTree(baseDir = "${project.buildDir}/tmp/kotlin-classes/debug") {
                exclude(getCoverageExcludeFiles())
            }

        listOf(javaDebugTree, kotlinDebugTree)
    }

    val sourceDirectoriesFileTrees = subprojects.map { project ->
        listOf("${project.projectDir}/src/main/java", "${project.projectDir}/src/main/kotlin")
    }

    val executionDataFileTrees = subprojects.map { project ->
        "${project.buildDir}/jacoco/testDebugUnitTest.exec"
    }

    println(classDirectoriesFileTrees.flatten())
    println(sourceDirectoriesFileTrees.flatten())
    println(executionDataFileTrees)

    classDirectories.setFrom(files(classDirectoriesFileTrees.flatten()))
    sourceDirectories.setFrom(files(sourceDirectoriesFileTrees.flatten()))
    executionData.setFrom(files(executionDataFileTrees))

    reports {
        xml.required.set(true)
        csv.required.set(true)
        html.required.set(true)
    }
}
