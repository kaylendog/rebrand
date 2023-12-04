plugins {
    kotlin("jvm")
}

repositories {
    maven("https://maven.fabricmc.net")
}

dependencies {
    implementation("net.fabricmc:fabric-loader:${rootProject.properties["loader_version"]}")
    implementation("com.moandjiezana.toml:toml4j:0.7.2")
    implementation("org.apache.logging.log4j:log4j-api:2.22.0")
}
