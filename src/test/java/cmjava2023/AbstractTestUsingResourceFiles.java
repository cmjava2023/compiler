package cmjava2023;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractTestUsingResourceFiles {
    protected String JAVA_RESOURCE_FOLDER_PATH = "src/test/resources/java";
    protected String TEST_CLASS_FILES_FOLDER_PATH = "build/test-classfiles";
    protected String TEST_TEMPORARY_FOLDER_PATH = "build/test-temp";

    protected String GetNonRootPackagesThisClassIsInAsPath() {
        List<String> canonicalNameParts = Arrays.stream(this.getClass().getCanonicalName().split("\\.")).toList();
        return String.join("/", canonicalNameParts.subList(1, canonicalNameParts.size() - 1));
    }

    protected String GetPathOfJavaResourceInSamePackage(String fileName) {
        return JAVA_RESOURCE_FOLDER_PATH + "/" + GetNonRootPackagesThisClassIsInAsPath() + "/" + fileName;
    }

    protected String GetTemporaryFolderPath() {
        return TEST_TEMPORARY_FOLDER_PATH + "/" + GetNonRootPackagesThisClassIsInAsPath();
    }

    protected String GetContentOfTestClassFileInSamePackage(String fileName) {
        try {
            return Files.readString(Paths.get(TEST_CLASS_FILES_FOLDER_PATH + "/" + GetNonRootPackagesThisClassIsInAsPath() + "/" + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected String GetJavaPForFileInTemporaryFolder(String fileName) {
        String pathToFile = GetTemporaryFolderPath() + "/" + fileName;
        String pathToResultFile = GetTemporaryFolderPath() + "/" + fileName + ".javap.txt";

        String[] commandParts = new String[]{"javap", "-c", "-p", "-verbose", pathToFile };
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

    protected String RunClassAndGetStdOut(String className) {
        String[] commandParts = new String[]{"java", "-cp", GetTemporaryFolderPath(), className };
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
