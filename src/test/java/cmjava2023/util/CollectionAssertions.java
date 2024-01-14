package cmjava2023.util;

import java.util.ArrayList;

import static cmjava2023.util.BetterNamedAssertions.assertExpectedEqualsActual;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectionAssertions {
    public static <T> void assertExpectedArrayListEqualsActual(String[] expected, ArrayList<T> actual) {
        assertExpectedEqualsActual(expected.length, actual.size(), "actualElements:\n" + String.join("\n", actual.stream().map(Object::toString).toList()));
        for (int i = 0; i < expected.length; i++) {
            assertExpectedEqualsActual(expected[i], actual.get(i), "index:" + i);       
        }
    }
}
