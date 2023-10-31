package OurClassFileEqualsJdksClassFile.helloWorld;

import org.cmjava2023.Main;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class HelloWorldTest {

    @Test
    public void test() throws IOException {
        Main.main(new String[] { "src/test/resources/OurClassFileEqualsJdksClassFile/hello_world/Main.java", "build/tmp/test/OurClassFileEqualsJdksClassFile/hello_world" });
        String expected = new String(Files.readAllBytes(Paths.get("src/test/resources/OurClassFileEqualsJdksClassFile/hello_world/Main.class")));
        String actual = new String(Files.readAllBytes(Paths.get("build/tmp/test/OurClassFileEqualsJdksClassFile/hello_world/Main.class")));
        assertEquals(expected, actual);
    }
}