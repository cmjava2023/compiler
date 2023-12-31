package cmjava2023.util.classFIleTesting;

public record MethodDescription(String accessModifierHex, String name, String typeDescriptor, String code) {
    public String getAssertMessage(String propertyName) {
        return "Wrong " + propertyName + " for method " + name();
    }
}