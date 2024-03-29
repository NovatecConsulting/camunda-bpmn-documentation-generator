/*
 * This file was generated by the Gradle 'init' task.
 */
plugins {
    java
    `maven-publish`
    `java-gradle-plugin`
    id("com.diffplug.spotless") version "6.12.0"
    id("org.jetbrains.kotlin.jvm") version "1.9.22"
    id("org.jlleitschuh.gradle.ktlint") version "11.2.0"
    id("org.jetbrains.dokka") version "1.9.10"
}

repositories {
    mavenLocal()
    mavenCentral()
}

group = "info.novatec"
version = "3.0-SNAPSHOT"
description = "camunda-bpmn-documentation-generator"
java.sourceCompatibility = JavaVersion.VERSION_17

dependencies {
    implementation("org.camunda.bpm.model:camunda-bpmn-model:7.18.0")
    implementation("org.freemarker:freemarker:2.3.32")
    implementation("org.slf4j:slf4j-api:2.0.7")
    dokkaHtmlPlugin("org.jetbrains.dokka:kotlin-as-java-plugin:1.9.10")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.1")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.1")
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.named("build") {
    dependsOn("spotlessApply")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

configure<com.diffplug.gradle.spotless.SpotlessExtension> {
    kotlin {
        target("**/*.kt")
        ktlint()
    }
    kotlinGradle {
        target("*.gradle.kts")
        ktlint()
    }
}

gradlePlugin {
    plugins {
        create("CamundaBpmnDocumentationGenerator") {
            id = "info.novatec.cbdg"
            implementationClass = "info.novatec.cbdg.plugin.CamundaBpmnDocumentationGenerator"
        }
    }
}
