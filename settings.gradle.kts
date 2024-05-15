pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.fabricmc.net/")
        maven("https://maven.jwall64.dev/plugins")
    }
    includeBuild("gradle/plugins")
}

rootProject.name = "midnightlib"
