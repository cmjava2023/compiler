package cmjava2023.util;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LineWiseEqualsAssertion {
    public static void expectedEqualsActual(String expected, String actual) {
        String[] expectedLines = expected.split("\r?\n");
        String[] actualLines = actual.split("\r?\n");
        
        assertEquals(expectedLines.length,
                actualLines.length,
                "Different Line Count, lines only in actual:\n" 
                        + String.join("\n", elementsInFirstAndNotInSecond(actualLines, expectedLines)) 
                        + "lines only in expected:"
                        + String.join("\n", elementsInFirstAndNotInSecond(expectedLines, actualLines)));

        for (int i = 0; i < expectedLines.length; i++) {
            assertEquals(makeCharactersVisible(expectedLines[i]), makeCharactersVisible(actualLines[i]), "Line " + i + " not as expected");
        }
    }
    private static String makeCharactersVisible(String s) {
        return s.replace("\r", "\\r").replace("\n", "\\n").replace("\t", "\\t").replace(" ", Charset.forName("windows-1252").encode("¿").asCharBuffer());
    }
    
    private static List<String> elementsInFirstAndNotInSecond(String[] first, String[] second) {
        return Arrays.stream(first).filter(actualLine -> Arrays.stream(second).noneMatch(expectedLine ->expectedLine.equals(actualLine))).toList();
    }
}
