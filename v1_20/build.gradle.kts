plugins {
    id("fabric-loom")
}

dependencies {
    minecraft("com.mojang:minecraft:${project.properties["minecraft_version"]}")
    mappings("net.fabricmc:yarn:${project.properties["yarn_mappings"]}:v2")
    modImplementation("net.fabricmc:fabric-loader:${project.properties["loader_version"]}")

    // cloth api
    modApi("me.shedaniel.cloth:cloth-config-fabric:11.0.99") {
        exclude(group = "net.fabricmc.fabric-api")
    }
    // mod menu
    modImplementation("com.terraformersmc:modmenu:7.1.0")

    // common
    implementation(project(":common"))
}

tasks.processResources {
    files("fabric.mod.json") {
        expand(rootProject.properties)
        expand(project.properties)
    }
}
