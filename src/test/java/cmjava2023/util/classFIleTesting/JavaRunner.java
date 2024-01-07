package cmjava2023.util.classFIleTesting;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class JavaRunner {

    public static String RunClassAndGetStdOut(String rootFolder, String fullyQualifiedClassName) {
        String[] commandParts = new String[]{"java", "-cp", rootFolder, fullyQualifiedClassName };
        System.out.println(String.join(" ", commandParts));
        try {
            Process process = new ProcessBuilder(commandParts)
                    .redirectInput(ProcessBuilder.Redirect.PIPE)
                    .redirectOutput(ProcessBuilder.Redirect.PIPE)
                    .redirectError(ProcessBuilder.Redirect.PIPE)
                    .start();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(process.getOutputStream(), StandardCharsets.UTF_8));
            String error = "";
            try {
                bw.write("c");
                bw.newLine();
                bw.flush();
            } catch(Exception e) {
                error = e.toString();
            }
            
            process.waitFor();
            error += new BufferedReader(new InputStreamReader(process.getErrorStream())).lines().collect(Collectors.joining("\n"));
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
