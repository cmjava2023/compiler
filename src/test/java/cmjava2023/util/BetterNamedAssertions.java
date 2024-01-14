package cmjava2023.util;

import org.junit.jupiter.api.Assertions;

public class BetterNamedAssertions {
    public static <T> void assertExpectedEqualsActual(T expected, T actual) {
        Assertions.assertEquals(expected, actual, "");
    }
    public static <T> void assertExpectedEqualsActual(T expected, T actual, String message) {
        Assertions.assertEquals(expected, actual, message);
    }
}
