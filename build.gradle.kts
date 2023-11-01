plugins {
    id("java")
    antlr // https://docs.gradle.org/current/userguide/antlr_plugin.html
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

tasks.register("prepareTestFilesWithJdk8") {
    doFirst {
        val outputRoot = File("build/test-classfiles")
        val testJavaFilesRoot = File("src/test/resources/java")

        outputRoot.deleteRecursively()
        outputRoot.mkdirs()

        println("Compile Java test files using jdk version 8. Files from $testJavaFilesRoot compiled to $outputRoot.")
        testJavaFilesRoot.walk().forEach {
            if (it.extension == "java") {
                val outputFolder = File(outputRoot, it.parentFile.relativeTo(testJavaFilesRoot).path)
                outputFolder.mkdirs()
                val commandParts = listOf("javac", it.path, "-d", outputFolder.path, "--release",  "8")
                println("  " + commandParts.joinToString(" "))
                ProcessBuilder(commandParts)
                        .redirectOutput(ProcessBuilder.Redirect.PIPE)
                        .redirectError(ProcessBuilder.Redirect.INHERIT)
                        .start()
                        .waitFor()
            }
        }

        println()
        println("Save javap output")

        outputRoot.walk().forEach {
            if (it.extension == "class") {
                val commandParts = listOf("javap", "-c", "-p", it.path)
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