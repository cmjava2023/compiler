package cmjava2023.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LineWiseEqualsAssertion {
    public static void expectedEqualsActualSystemIndependent(String expected, String actual) {
        String[] expectedLines = expected.split("\r?\n");
        String[] actualLines = actual.split("\r?\n");
        String lineTerminationIndependentExcepted = String.join("\n", expectedLines);
        String lineTerminationIndependentActual = String.join("\n", actualLines);
        assertEquals(lineTerminationIndependentExcepted, lineTerminationIndependentActual);
    }
}
