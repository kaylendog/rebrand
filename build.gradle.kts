import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName

plugins {
    java
    kotlin("jvm") version "1.9.21"
    id("com.diffplug.spotless") version "6.23.3"
    id("fabric-loom") version "1.2-SNAPSHOT" apply false
}

allprojects {
    group = "dog.kaylen"
    version = "2.0.0"
    repositories { mavenCentral() }
}

subprojects {
    apply(plugin = "java")

    repositories {
        maven("https://maven.shedaniel.me")
        maven("https://maven.terraformersmc.com")
    }

    java {
        sourceCompatibility =
            JavaVersion.toVersion(rootProject.properties["java_version"] as String)
        archivesName.set("rebrand-${version}+${project.properties["minecraft_version"]}")
    }
}

spotless {
    encoding("UTF-8")
    java {
        googleJavaFormat().aosp().reflowLongStrings()
        licenseHeaderFile(rootProject.file("LICENSE_HEADER"))
    }
    json {
        target("**/*.json")
        gson().indentWithSpaces(4)
    }
    kotlinGradle { ktfmt().dropboxStyle() }
}
