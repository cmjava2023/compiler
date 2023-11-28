package cmjava2023;

import org.cmjava2023.Main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractTestUsingResourceFiles {
    protected static String JAVA_TEST_FILES_RESOURCE_FOLDER_PATH = "src/test/resources/java-test-files";
    protected static  String JDK_COMPILED_TEST_FILES_FOLDER = "build/test-files-jdk-compiled";
    protected static String OUR_COMPILER_COMPILED_TEST_FILES_FOLDER = "build/test-files-compiled-by-us";
    
    protected static String JAVA_FILE_ENDING = ".java";
    protected static String CLASS_FILE_ENDING = ".class";
    protected static String JAVAP_OUTPUT_FILE_ENDING = ".javap.txt";

    protected String GetTestClassName() {
        return "Main";
    }

    protected AbstractTestUsingResourceFiles() {
        try {
            Main.main(new String[]{ GetPathOfJavaTestResourceInSamePackage(), GetOurCompilerCompiledTestFilesFolderForThisPackage() });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GetJavaPForFileCompiledByUs();
    }

    protected String GetNonRootPackagesThisClassIsInAsPath() {
        List<String> canonicalNameParts = Arrays.stream(this.getClass().getCanonicalName().split("\\.")).toList();
        return String.join("/", canonicalNameParts.subList(0, canonicalNameParts.size() - 1));
    }

    protected String GetPathOfJavaTestResourceInSamePackage() {
        return JAVA_TEST_FILES_RESOURCE_FOLDER_PATH + "/" + GetNonRootPackagesThisClassIsInAsPath() + "/" + GetTestClassName() + JAVA_FILE_ENDING;
    }

    protected String GetOurCompilerCompiledTestFilesFolderForThisPackage() {
        return OUR_COMPILER_COMPILED_TEST_FILES_FOLDER + "/" + GetNonRootPackagesThisClassIsInAsPath();
    }

    protected String GetContentOfJdkCompiledTestFileInSamePackage(String fileName) {
        try {
            return Files.readString(Paths.get(JDK_COMPILED_TEST_FILES_FOLDER + "/" + GetNonRootPackagesThisClassIsInAsPath() + "/" + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected Queue<String> GetByteHexOfFileCompiledByUs() {
        try {
            String hexString = HexFormat.of().formatHex(Files.readAllBytes(Paths.get(GetOurCompilerCompiledTestFilesFolderForThisPackage() + "/" + GetTestClassName() + CLASS_FILE_ENDING))).toUpperCase();
            Queue<String> result = new LinkedList<>();
            char[] charArray = hexString.toCharArray();
            for (int i = 0; i < charArray.length; i+=2) {
                result.add(String.valueOf(charArray[i]) + charArray[i + 1]);
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected static String pollMultiple(Queue<String> queue, int amount) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < amount; i++) {
            result.append(queue.poll());
        }
        return result.toString();
    }

    protected String GetJavaPForFileCompiledByUs() {
        String ourCompilerCompiledTestFilesFolderForThisPackage = GetOurCompilerCompiledTestFilesFolderForThisPackage();
        String pathToFile = ourCompilerCompiledTestFilesFolderForThisPackage + "/" + GetTestClassName() + CLASS_FILE_ENDING;
        String pathToResultFile = ourCompilerCompiledTestFilesFolderForThisPackage + "/" + GetTestClassName() + JAVAP_OUTPUT_FILE_ENDING;

        String[] commandParts = new String[]{"javap", "-c", "-p", "-verbose", pathToFile };
        System.out.println(String.join(" ", commandParts));
        try {
            new ProcessBuilder(commandParts)
                    .redirectOutput(new File(pathToResultFile))
                    .redirectError(ProcessBuilder.Redirect.INHERIT)
                    .start()
                    .waitFor();
            return Files.readString(Paths.get(pathToResultFile));
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected String RunClassCompiledByUsAndGetStdOut() {
        String[] commandParts = new String[]{"java", "-cp", OUR_COMPILER_COMPILED_TEST_FILES_FOLDER, GetNonRootPackagesThisClassIsInAsPath()  + "/" + GetTestClassName() };
        System.out.println(String.join(" ", commandParts));
        try {
            Process process = new ProcessBuilder(commandParts).start();
            process.waitFor();
            String error = new BufferedReader(new InputStreamReader(process.getErrorStream())).lines().collect(Collectors.joining("\n"));
            if (error.isEmpty()) {
                return new BufferedReader(new InputStreamReader(process.getInputStream()))
                        .lines().collect(Collectors.joining("\n"));
            }
            else {
                return error;
            }
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
