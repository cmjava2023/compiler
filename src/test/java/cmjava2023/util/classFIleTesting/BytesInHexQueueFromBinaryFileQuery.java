package cmjava2023.util.classFIleTesting;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HexFormat;
import java.util.LinkedList;
import java.util.Queue;

public class BytesInHexQueueFromBinaryFileQuery {
    public static Queue<String> fetch(String path) {
        try {
            String hexString = HexFormat.of().formatHex(Files.readAllBytes(Paths.get(path))).toUpperCase();
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
}
