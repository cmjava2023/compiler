package cmjava2023.util.classFIleTesting;

import org.cmjava2023.util.BytesInHexQueue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BytesInHexQueueFromBinaryFileQuery {
    public static BytesInHexQueue fetch(String path) {
        try {
            return new BytesInHexQueue(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
