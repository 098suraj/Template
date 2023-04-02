// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    extra.apply {
        set("detektVersion", "1.21.0")
        set("kotlinVersion", "1.8.10")
          }
    repositories {
        google()
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${rootProject.extra.get("detektVersion")}")
        classpath("com.android.tools.build:gradle:8.1.0-alpha11")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${rootProject.extra.get("kotlinVersion")}")
        classpath("com.squareup:javapoet:1.13.0")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

plugins {
    id("org.jmailen.kotlinter") version "3.13.0" apply false
}

apply(from = "/buildScript/setup.gradle")

subprojects {
    apply(from = "../buildScript/detekt.gradle")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}