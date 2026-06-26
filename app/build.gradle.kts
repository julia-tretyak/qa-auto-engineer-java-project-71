System.setProperty("sonar.gradle.skipCompile", "true")

plugins {
    application
    checkstyle
    id("org.sonarqube") version "7.3.1.8318"
    jacoco
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    checkstyle("com.puppycrawl.tools:checkstyle:10.12.0")
    implementation("info.picocli:picocli:4.7.6")
    annotationProcessor("info.picocli:picocli:4.7.6")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.17.0")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

application {
    mainClass = "hexlet.code.App"
}

tasks.getByName("run", JavaExec::class) {
    standardInput = System.`in`
}

checkstyle {
    toolVersion = "10.12.0"
    configFile = file("${project.rootDir}/config/checkstyle/checkstyle.xml")
    maxErrors = 0
}
jacoco {
    toolVersion = "0.8.12"
}


tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

sonarqube {
    properties {
        property("sonar.projectKey", "julia-tretyak_qa-auto-engineer-java-project-71")
        property("sonar.organization", "julia-tretyak")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.sources", "src/main/java")
        property("sonar.java.binaries", "build/classes")
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/test/html/jacocoTestReport.xml")
        property("sonar.gradle.skipCompile", "true")
    }
}


