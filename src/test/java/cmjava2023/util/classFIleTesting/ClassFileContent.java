package cmjava2023.util.classFIleTesting;

public record ClassFileContent(
        String classAccessModifierHex,
        String thisClass,
        String superClass,
        MethodDescription[] methodDescriptions) {}
