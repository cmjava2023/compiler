package cmjava2023.util.classFIleTesting;

import org.cmjava2023.util.BytesInHexQueue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HexFormat;

public class BytesInHexQueueFromBinaryFileQuery {
    public static BytesInHexQueue fetch(String path) {
        try {
            String hexString = HexFormat.of().formatHex(Files.readAllBytes(Paths.get(path))).toUpperCase();
            BytesInHexQueue result = new BytesInHexQueue();
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
