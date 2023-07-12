import java.net.URI

plugins {
    id("java")
    id("java-library")
    id("maven-publish")
}

group = "dev.jwall64"
version = "1.1.0"

java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11

java.withSourcesJar()

repositories {
    mavenCentral()
    maven("https://maven.jwall64.dev/")
    mavenLocal()
}

dependencies {

    api("dev.jwall64:midnightcfg:1.0.0")

    implementation("org.slf4j:slf4j-api:2.0.7")

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
}

tasks.test {
    useJUnitPlatform()
    workingDir("run/test")
    maxHeapSize = "1G"
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = group as String
            version = version as String
            from(components["java"])
        }
    }
    repositories {
        if (project.hasProperty("pubUrl")) {
            maven {
                name = "pub"
                url = URI.create(project.properties["pubUrl"] as String)
                credentials(PasswordCredentials::class.java)
            }
        }
    }
}