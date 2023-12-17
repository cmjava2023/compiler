package cmjava2023.util.treePrinter;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class GenericTreePrinter {
    public static String print(Object root) {
        String resultWithEmptyLineAtEnd = print("root", root, new TreePrefixData());
        return resultWithEmptyLineAtEnd.stripTrailing();
    }

    private static String print(String fieldName, Object object, TreePrefixData treePrefixData) {
        StringBuilder builder = treePrefixData.getStringBuilderStartingWithPrefix();
        if (object == null || object.getClass().isPrimitive() || object instanceof Character || object instanceof Number || object instanceof Boolean || object instanceof String || object instanceof Enum<?>) {
            builder.append(fieldName).append(": ").append(object).append("\n");
        } else {
            TreePrefixData nextLevelTreePrefixData = treePrefixData.createForNextLevel();
            if (object instanceof Collection<?> || object.getClass().isArray()) {
                Object[] array;
                if (object instanceof Collection<?> collection) {
                    array = collection.toArray();
                } else {
                    array = (Object[]) object;
                }
                builder.append(fieldName).append("\n");
                for (int i = 0; i < array.length; i++) {
                    if (i >= array.length - 1) {
                        nextLevelTreePrefixData = treePrefixData.createForLastElementOfNextLevel();
                    }
                    builder.append(print(String.valueOf(i), array[i], nextLevelTreePrefixData));
                }
            } else {
                builder.append(fieldName).append(" (field of type ").append(object.getClass().getSimpleName()).append(")").append("\n");
                ArrayList<Field> fields = getFieldsDeclaredSelfOrInSuperThatDoNotStartEndlessRecursion(object, fieldName);
                for (int i = 0; i < fields.size(); i++) {
                    if (i >= fields.size() - 1) {
                        nextLevelTreePrefixData = treePrefixData.createForLastElementOfNextLevel();
                    }
                    Field field = fields.get(i);
                    try {
                        field.trySetAccessible();
                        var fieldValue = field.get(object);
                        builder.append(print(field.getName(), fieldValue, nextLevelTreePrefixData));
                    } catch (IllegalAccessException ignored) {
                    }
                }
            }
        }

        return builder.toString();
    }

    @NotNull
    private static ArrayList<Field> getFieldsDeclaredSelfOrInSuperThatDoNotStartEndlessRecursion(Object object, String fieldName) {
        Class<?> currentClass = object.getClass();

        ArrayList<Class<?>> objectClassAndSuperClassesAndInterfaces = new ArrayList<>();
        addClassAndInterfaces(objectClassAndSuperClassesAndInterfaces, currentClass);

        ArrayList<Field> fields = new ArrayList<>();
        addAllDeclaredFields(fields, currentClass);
        while (currentClass.getSuperclass() != null
                && isSuperClassDefinedByUs(currentClass)
                && !currentClass.getSuperclass().getSimpleName().equals("BaseScope")) {
            currentClass = currentClass.getSuperclass();
            addAllDeclaredFields(fields, currentClass);

            addClassAndInterfaces(objectClassAndSuperClassesAndInterfaces, currentClass);
        }
        fields.removeIf(f -> (objectClassAndSuperClassesAndInterfaces.contains(f.getType()) && f.getName().equals(fieldName))
                || f.getType().getSimpleName().equals("Scope"));
        return fields;
    }

    private static void addAllDeclaredFields(ArrayList<Field> fields, Class<?> currentClass) {
        fields.addAll(Arrays.stream(currentClass.getDeclaredFields()).toList());
    }

    private static void addClassAndInterfaces(ArrayList<Class<?>> objectClassAndSuperClassesAndInterfaces, Class<?> currentClass) {
        objectClassAndSuperClassesAndInterfaces.add(currentClass);
        objectClassAndSuperClassesAndInterfaces.addAll(Arrays.stream(currentClass.getInterfaces()).toList());
    }

    private static boolean isSuperClassDefinedByUs(Class<?> currentClass) {
        return currentClass.getSuperclass().getCanonicalName().startsWith("org.cmjava2023");
    }
}
