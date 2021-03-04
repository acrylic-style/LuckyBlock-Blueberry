plugins {
    java
    kotlin("jvm") version "1.4.30"
}

group = "xyz.acrylicstyle"
version = "1.0.0"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("junit", "junit", "4.12")
    compileOnly("net.blueberrymc:blueberry:21w08b")
}
