package cmjava2023.util;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LineWiseEqualsAssertion {
    public static void expectedEqualsActual(String expected, String actual) {
        String[] expectedLines = expected.split("\r?\n");
        String[] actualLines = actual.split("\r?\n");
        
        StringBuilder assertionMessage = new StringBuilder();
        
        if (expectedLines.length != actualLines.length) {
            assertionMessage = new StringBuilder("Different Line Count, lines only in actual:\n"
                    + String.join("\n", elementsInFirstAndNotInSecond(actualLines, expectedLines))
                    + "lines only in expected:"
                    + String.join("\n", elementsInFirstAndNotInSecond(expectedLines, actualLines)));
        } else {
            for (int i = 0; i < expectedLines.length; i++) {
                if(!expectedLines[i].equals(actualLines[i])) {
                    assertionMessage.append("Line ").append(i).append(" Exp:")
                            .append(makeCharactersVisible(expectedLines[i]))
                            .append("\nLine ").append(i).append(" Act:").append(makeCharactersVisible(actualLines[i])).append("\n\n");                    
                }
            }
        }
        assertEquals(String.join("\n", expectedLines), String.join("\n", actualLines), assertionMessage.toString());
    }
    private static String makeCharactersVisible(String s) {
        return s.replace("\r", "\\r").replace("\n", "\\n").replace("\t", "\\t") + "<lineEnd";
    }
    
    private static List<String> elementsInFirstAndNotInSecond(String[] first, String[] second) {
        return Arrays.stream(first).filter(actualLine -> Arrays.stream(second).noneMatch(expectedLine ->expectedLine.equals(actualLine))).toList();
    }
}
