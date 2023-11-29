plugins {
    id("java")
    antlr // https://docs.gradle.org/current/userguide/antlr_plugin.html
    kotlin("jvm") version "1.9.20"
}

group = "org.cmjava2023"
version = "1.0-SNAPSHOT"

val testFilesFolder = File("src/test/resources/java-test-files")
val jdkCompiledTestFilesFolder = File("build/test-files-jdk-compiled")
val ourCompilerCompiledTestFilesFolder = File("build/test-files-compiled-by-us")

repositories {
    mavenCentral()
}

dependencies {
    antlr("org.antlr:antlr4:4.13.1")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    dependsOn("compileTestFilesWithJdk8")
    dependsOn("compileTestFilesWithOurCompiler")
    dependsOn("createJavapTxtFiles")
    useJUnitPlatform()
}

tasks.compileKotlin{
    dependsOn("generateGrammarSource")
}

tasks.register("compileTestFilesWithJdk8") {
    doLast {
        jdkCompiledTestFilesFolder.deleteRecursively()
        jdkCompiledTestFilesFolder.mkdirs()

        println("Compile Java test files using jdk version 8. Files from $testFilesFolder compiled to $jdkCompiledTestFilesFolder.")
        testFilesFolder.walk().forEach {
            if (it.extension == "java") {
                val commandParts = listOf(
                    "javac",
                    it.path,
                    "--release",
                    "8",
                    "-d",
                    jdkCompiledTestFilesFolder.toString()
                )
                println("  " + commandParts.joinToString(" "))
                exec {
                    commandLine(commandParts)
                }
            }
        }
    }
}

tasks.register("compileTestFilesWithOurCompiler") {
    dependsOn(tasks.compileJava)
    doLast {
        ourCompilerCompiledTestFilesFolder.deleteRecursively()
        ourCompilerCompiledTestFilesFolder.mkdirs()

        println("Compile Java test files using our compiler. Files from $testFilesFolder compiled to $ourCompilerCompiledTestFilesFolder.")
        testFilesFolder.walk().forEach { file ->
            if (file.extension == "java") {
                val commandParts =
                    listOf(
                        "java",
                        "-verbose:class",
                        "-verbose:module",
                        "-classpath",
                        "\"build/classes/java/main;build/classes/kotlin/main;" + configurations.compileClasspath.get().joinToString(";") { it.path } + "\"",
                        "org/cmjava2023/Main",
                        file.path,
                        ourCompilerCompiledTestFilesFolder.path
                    )
                println("  " + commandParts.joinToString(" "))
                exec {
                    commandLine(commandParts) // TODO Fix server that server execution finds classfile
                }
            }
        }
    }
}

tasks.register("createJavapTxtFiles") {
    dependsOn("compileTestFilesWithJdk8")
    dependsOn("compileTestFilesWithOurCompiler")

    doLast {
        createJavapTxtFilesForAllClassFilesInFolder(jdkCompiledTestFilesFolder)
        println()
        createJavapTxtFilesForAllClassFilesInFolder(ourCompilerCompiledTestFilesFolder)
    }
}

fun createJavapTxtFilesForAllClassFilesInFolder(folder: File) {
    println("Folder: $folder")
    folder.walk().forEach {
        if (it.extension == "class") {
            val commandParts = listOf("javap", "-c", "-p", "-verbose", it.path)
            println("  " + commandParts.joinToString(" "))
            val outputFile = File(it.parentFile, it.nameWithoutExtension + ".javap.txt")
            exec {
                standardOutput = outputFile.outputStream()
                commandLine(commandParts)
            }
        }
    }
}

tasks.generateGrammarSource {
    maxHeapSize = "64m"
    arguments = arguments + listOf("-visitor", "-long-messages")
}