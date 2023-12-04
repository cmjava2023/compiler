package cmjava2023.util;

import org.junit.jupiter.api.DisplayNameGenerator;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class QualifiedDisplayNameGenerator extends DisplayNameGenerator.Standard {
    @Override
    public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
        List<String> namespacesOfClass = Arrays.stream(testClass.getCanonicalName().split("\\.")).toList();
        return String.join(".", namespacesOfClass.subList(1, namespacesOfClass.size() - 1)) + " " + testMethod.getName();
    }
}