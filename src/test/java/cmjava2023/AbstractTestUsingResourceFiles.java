package cmjava2023;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractTestUsingResourceFiles {
    protected static String JAVA_RESOURCE_FOLDER_PATH = "src/test/resources/java";
    protected static  String TEST_CLASS_FILES_FOLDER_PATH = "build/test-classfiles";
    protected static String TEST_TEMPORARY_FOLDER_PATH = "build/test-temp";

    protected String GetNonRootPackagesThisClassIsInAsPath() {
        List<String> canonicalNameParts = Arrays.stream(this.getClass().getCanonicalName().split("\\.")).toList();
        return String.join("/", canonicalNameParts.subList(0, canonicalNameParts.size() - 1));
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

    protected Queue<String> GetByteHexOfFileInTemporaryFolder(String fileName) {
        try {
            String hexString = HexFormat.of().formatHex(Files.readAllBytes(Paths.get(GetTemporaryFolderPath() + "/" + fileName))).toUpperCase();
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

    protected String GetJavaPForFileInTemporaryFolder(String fileName) {
        String pathToFile = GetTemporaryFolderPath() + "/" + fileName;
        String pathToResultFile = GetTemporaryFolderPath() + "/" + fileName + ".javap.txt";

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

    protected String RunClassAndGetStdOut(String className) {
        String[] commandParts = new String[]{"java", "-cp", TEST_TEMPORARY_FOLDER_PATH, GetNonRootPackagesThisClassIsInAsPath() + "/" +className };
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
