plugins {
    id("java")
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.18"
}

group = "nl.casperlambers"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    // Paper
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    // Paper
    compileOnly("io.papermc.paper:paper-api:1.17.1-R0.1-SNAPSHOT")
    paperweight.paperDevBundle("1.21.8-R0.1-SNAPSHOT")
}

// Paper
java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks.test {
    useJUnitPlatform()
}