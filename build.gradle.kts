plugins {
    id("java")
    antlr // https://docs.gradle.org/current/userguide/antlr_plugin.html
    kotlin("jvm") version "1.9.20"
}

group = "org.cmjava2023"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    antlr("org.antlr:antlr4:4.13.1")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    dependsOn("prepareTestFilesWithJdk8")
    useJUnitPlatform()
}

tasks.compileKotlin{
    dependsOn("generateGrammarSource")
}

tasks.register("prepareTestFilesWithJdk8") {
    doFirst {
        val testFilesFolder = File("src/test/resources/java-test-files")
        val jdkCompiledTestFilesFolder = File("build/test-files-jdk-compiled")

        jdkCompiledTestFilesFolder.deleteRecursively()
        jdkCompiledTestFilesFolder.mkdirs()

        println("Compile Java test files using jdk version 8. Files from $testFilesFolder compiled to $jdkCompiledTestFilesFolder.")
        testFilesFolder.walk().forEach {
            if (it.extension == "java") {
                val commandParts = listOf("javac", it.path, "--release",  "8", "-d", jdkCompiledTestFilesFolder.toString())
                println("  " + commandParts.joinToString(" "))
                ProcessBuilder(commandParts)
                        .redirectOutput(ProcessBuilder.Redirect.PIPE)
                        .redirectError(ProcessBuilder.Redirect.INHERIT)
                        .start()
                        .waitFor()
            }
        }

        println()

        jdkCompiledTestFilesFolder.walk().forEach {
            if (it.extension == "class") {
                val commandParts = listOf("javap", "-c", "-p", "-verbose", it.path)
                println("  " + commandParts.joinToString(" "))
                val outputFile = File(it.parentFile, it.nameWithoutExtension + ".javap.txt")
                println("  Output:$outputFile")
                ProcessBuilder(commandParts)
                        .redirectOutput(outputFile)
                        .redirectError(ProcessBuilder.Redirect.INHERIT)
                        .start()
                        .waitFor()
            }
        }
    }
}

tasks.generateGrammarSource {
    maxHeapSize = "64m"
    arguments = arguments + listOf("-visitor", "-long-messages")
}