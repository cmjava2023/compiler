package cmjava2023.util.classFIleTesting;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.fail;

public record ClassFileContent(
        String classAccessModifierHex,
        String thisClass,
        String superClass,
        MethodDescription[] methodDescriptions) {
    public static ClassFileContent load(String jsonContent, String pathToMain) {
        ClassFileContent initial = new Gson().fromJson(jsonContent, ClassFileContent.class);
        MethodDescription[] resolvedMethodDescriptions = new MethodDescription[initial.methodDescriptions.length];
        MethodDescription[] descriptions = initial.methodDescriptions;
        for (int i = 0; i < descriptions.length; i++) {
            MethodDescription methodDescription = descriptions[i];
            if (methodDescription.code() == null) {
                try {
                    String code = Files.readString(Path.of(pathToMain).resolveSibling(Path.of(methodDescription.name() + ".code.txt")).toAbsolutePath()).replaceAll("\r\n", "\n");
                    resolvedMethodDescriptions[i] = new MethodDescription(methodDescription.accessModifierHex(), methodDescription.name(), methodDescription.typeDescriptor(), code);
                } catch (IOException e) {
                    fail(e);
                }
            } else {
                resolvedMethodDescriptions[i] = methodDescription;
            }
        }
        return new ClassFileContent(initial.classAccessModifierHex, initial.thisClass, initial.superClass, resolvedMethodDescriptions);
    }
}
