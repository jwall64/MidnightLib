import buildlogic.Utils

plugins {
    id("java")
}

repositories {
    mavenCentral()
    maven("https://maven.jwall64.dev/releases")
}

dependencies {

    testImplementation("org.junit.jupiter:junit-jupiter:6.1.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
    withSourcesJar()
}

tasks.withType<Jar> {
    archiveBaseName.set(Utils.getArchiveName(project, rootProject))
}

tasks.named<Test>("test") {
    useJUnitPlatform()
    workingDir("run/test")
    run {
        mkdir("run/test")
    }
}
