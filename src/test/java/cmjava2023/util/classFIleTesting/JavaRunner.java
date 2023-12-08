package cmjava2023.util.classFIleTesting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class JavaRunner {

    public static String RunClassAndGetStdOut(String rootFolder, String fullyQualifiedClassName) {
        String[] commandParts = new String[]{"java", "-cp", rootFolder, fullyQualifiedClassName };
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
