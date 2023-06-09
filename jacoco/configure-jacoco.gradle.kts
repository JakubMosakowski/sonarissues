apply(plugin = "jacoco")

extensions.getByType<JacocoPluginExtension>().apply {
    toolVersion = "0.8.10"
}

fun getIncludeClassFiles(): List<String> = listOf(
    "**/classes/**/main/**",
    "**/intermediates/classes/debug/**",
    "**/intermediates/javac/debug/*/classes/**",
    "**/tmp/kotlin-classes/debug/**"
)

fun getExecutionFiles(): List<String> = listOf(
    "jacoco/*.exec",
    "outputs/code_coverage/*/connected/*.ec"
)

fun getSourceFiles(): List<String> = listOf(
    "src/main/kotlin",
    "src/main/java"
)

fun getCoverageExcludeFiles(): List<String> = listOf(
    "**/R.*",
    "**/R$*.*",
    "*/BuildConfig.*",
    "**/Manifest*.*",
    "android/**/*.*",
    "**/databinding/**",
    "*/*NavDirections*",
    "**/*NavArgs*",
    "**/*Injector.*",
    "**/*Hilt*",
    "**/*Activity.*",
    "*/*Fragment.*",
    "**/*Service.*",
    "*/*Dialog.*",
    "**/*Application*.*",
    "*/*JsonAdapter.*",
    "**/di/**",
    "*/widget/**",
    "*/*ModuleKt.*",
    "**/*View.*",
)
rootProject.extra.set("coverageExcludeFiles", getCoverageExcludeFiles())

tasks.register<JacocoReport>("jacocoTestReport") {
    group = "verification"
    description = "Code coverage report for debug unit tests."

    val classDirectoriesFileTrees = subprojects.map { project ->
        fileTree(baseDir = project.buildDir) {
            include(getIncludeClassFiles())
            exclude(getCoverageExcludeFiles())
        }
    }
    classDirectories.setFrom(files(classDirectoriesFileTrees))

    val executionDataFileTrees = subprojects.map { project ->
        fileTree(baseDir = project.buildDir) {
            include(getExecutionFiles())
        }
    }
    executionData.setFrom(files(executionDataFileTrees))
    sourceDirectories.setFrom(files(getSourceFiles()))

    reports {
        xml.required.set(true)
        csv.required.set(false)
        html.required.set(false)
    }
}

