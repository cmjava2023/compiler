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
    useJUnitPlatform()
    doFirst {
        ourCompilerCompiledTestFilesFolder.deleteRecursively()
    }
}

tasks.compileKotlin{
    dependsOn("generateGrammarSource")
}

tasks.withType<Exec>() {
    doFirst {
        println(commandLine)
    }
}

tasks.register("compileTestFilesWithJdk8AndCreateJavaP") {
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
                    isIgnoreExitValue = true
                    commandLine(commandParts)
                }
            }
        }

        createJavapTxtFilesForAllClassFilesInFolder(jdkCompiledTestFilesFolder)
    }
}

tasks.register("compileTestFilesWithOurCompilerAndCreateJavaP") {
    dependsOn(tasks.compileJava)
    doLast {
        ourCompilerCompiledTestFilesFolder.deleteRecursively()
        ourCompilerCompiledTestFilesFolder.mkdirs()

        println("Compile Java test files using our compiler. Files from $testFilesFolder compiled to $ourCompilerCompiledTestFilesFolder.")
        val classPathDelimiter = if (System.getProperty("os.name").lowercase().contains("win")) { ";" } else { ":" }
        val classPathElements = listOf("build/classes/java/main", "build/classes/kotlin/main") + configurations.compileClasspath.get().map {it.path}
        val classPath = classPathElements.joinToString(classPathDelimiter)

        testFilesFolder.walk().forEach { file ->
            if (file.extension == "java") {
                val commandParts =
                    listOf(
                        "java",
                        "-classpath",
                        classPath,
                        "org.cmjava2023.Main",
                        file.path,
                        ourCompilerCompiledTestFilesFolder.path
                    )
                println("  " + commandParts.joinToString(" ") {
                    if (it == "org.cmjava2023.Main") {
                        "$it\n      "
                    } else {
                        it
                    }
                })
                exec {
                    isIgnoreExitValue = true
                    commandLine(commandParts)
                }
            }
        }

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
                isIgnoreExitValue = true
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