package cmjava2023.util.classFIleTesting;

import cmjava2023.util.TestPathsHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class OutputOfJdkCompiledClassFIleQuery {
    public static String fetch(String pathToMainJava) {
        String[] commandParts = new String[]{"javac", pathToMainJava, "--release", "8", "-d", TestPathsHelper.JDK_COMPILED_TEST_FILES_FOLDER };
        System.out.println(String.join(" ", commandParts));
        try {
            Process process = new ProcessBuilder(commandParts).start();
            process.waitFor();
            String error = new BufferedReader(new InputStreamReader(process.getErrorStream())).lines().collect(Collectors.joining("\n"));
            if (!error.isEmpty()) {
                return error;
            }
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }

        return JavaRunner.RunClassAndGetStdOut(TestPathsHelper.JDK_COMPILED_TEST_FILES_FOLDER, "cmjava2023/helloworld/Main");
    }
}
