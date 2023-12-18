package cmjava2023.util.classFIleTesting;

import kotlin.NotImplementedError;

import java.util.*;

import static com.ibm.icu.impl.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HexClassFileTester {
    private Queue<String> bytesInHex;
    private String classAccessModifierHex;
    private String thisClass;
    private String superClass;
    private MethodDescription[] methodDescriptions;
    private List<String> constantPoolItems;

    private record ConstantPoolItemToResolve(short index) {
    }

    public void test(Queue<String> bytesInHex, ClassFileContent classFileContent) {
        this.bytesInHex = bytesInHex;
        this.classAccessModifierHex = classFileContent.classAccessModifierHex();
        this.thisClass = classFileContent.thisClass();
        this.superClass = classFileContent.superClass();
        this.methodDescriptions = classFileContent.methodDescriptions();
        this.constantPoolItems = new ArrayList<>();

        classFileIndicatorCafeBabe();
        javaVersion8();
        parseConstantPool();
        classAccessModifier();
        thisClass();
        superClass();
        noInterfaces();
        noFields();
        methods();
        noClassAttributes();
    }

    private void classFileIndicatorCafeBabe() {
        assertEquals("CAFEBABE", dequeueHexBytes(4), "ClassFile Start");
    }

    private void javaVersion8() {
        assertEquals(52, dequeue4ByteInt(), "JavaVersion");
    }

    private void parseConstantPool() {
        short constantPoolSize = dequeue2ByteShort();
        ArrayList<Object> unresolvedConstantPool = new ArrayList<>();
        unresolvedConstantPool.add("emptyElementBecauseConstantPoolIndicesStartWith1");
        for (int i = 0; i < constantPoolSize - 1; i++) {
            String constantInfoTag = bytesInHex.poll();
            switch (Objects.requireNonNull(constantInfoTag)) {
                case "03":
                    unresolvedConstantPool.add(dequeue4ByteInt().toString());
                    break;
                case "04":
                    unresolvedConstantPool.add(Float.toString(Float.intBitsToFloat(dequeue4ByteInt())));
                    break;
                case "05":
                    unresolvedConstantPool.add(dequeue8ByteLong().toString());
                    break;
                case "06":
                    unresolvedConstantPool.add(Double.toString(Double.longBitsToDouble(dequeue8ByteLong())));
                    break;
                case "07":
                case "08":
                    unresolvedConstantPool.add(new ConstantPoolItemToResolve(dequeue2ByteShort()));
                    break;
                case "01":
                    short length = dequeue2ByteShort();
                    unresolvedConstantPool.add(utf8FromHexString(dequeueHexBytes(length)));
                    break;
                case "09":
                case "0A":
                case "0C":
                    unresolvedConstantPool.add(constantInfoTag + " " + dequeueHexBytes(2) + "." + dequeueHexBytes(2));
                    break;
                default:
                    fail("test does not support constant pool type " + constantInfoTag + " constantPool so far:\n" + unresolvedConstantPool.toString().replace(",", ",\n"));
                    break;
            }
        }
        constantPoolItems = unresolvedConstantPool.stream().map(e -> {
            if (e instanceof String s) {
                return s;
            } else if (e instanceof ConstantPoolItemToResolve c) {
                return (String) unresolvedConstantPool.get(c.index);
            } else {
                throw new NotImplementedError();
            }
        }).toList();
    }

    private String utf8FromHexString(String hexString) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < hexString.length(); i += 2) {
            String substring = hexString.substring(i, i + 2);
            int codePoint = Integer.parseInt(substring, 16);
            builder.appendCodePoint(codePoint);
        }
        return builder.toString();
    }

    private void classAccessModifier() {
        assertEquals(classAccessModifierHex, dequeueHexBytes(2), "classAccessModifier");
    }

    private void thisClass() {
        dequeueResolveAssertConstantPoolIndex(thisClass, "thisClass");
    }

    private void superClass() {
        dequeueResolveAssertConstantPoolIndex(superClass, "superClass");
    }

    private void noInterfaces() {
        assertEquals((short) 0, dequeue2ByteShort(), "interfaceCount");
    }

    private void noFields() {
        assertEquals((short) 0, dequeue2ByteShort(), "FieldCount");
    }

    private void methods() {
        assertEquals((short) methodDescriptions.length, dequeue2ByteShort(), "methodCount");

        for (MethodDescription methodDescription : methodDescriptions) {
            assertEquals(methodDescription.accessModifierHex(), dequeueHexBytes(2), methodDescription.getAssertMessage("accessModifier"));
            dequeueResolveAssertConstantPoolIndex(methodDescription.name(), methodDescription.getAssertMessage("name"));
            dequeueResolveAssertConstantPoolIndex(methodDescription.typeDescriptor(), methodDescription.getAssertMessage("typeDescriptor"));
            assertEquals((short) 1, dequeue2ByteShort(), methodDescription.getAssertMessage("attributeCount"));
            codeAttribute(methodDescription);
        }
    }

    private void codeAttribute(MethodDescription methodDescription) {
        dequeueResolveAssertConstantPoolIndex("Code", methodDescription.getAssertMessage("attributeName"));
        int expectedCodeSize = methodDescription.code().length() / 2;
        int expectedAttributeSize = 2 + 2 + 4 + expectedCodeSize + 2 + 2;
        int actualAttributeSize = dequeue4ByteInt();
        assertEquals((short) 2, dequeue2ByteShort(), methodDescription.getAssertMessage("stackSize"));
        assertEquals((short) 1, dequeue2ByteShort(), methodDescription.getAssertMessage("maxLocalVars"));

        int actualCodeSize = dequeue4ByteInt();
        assertEquals(methodDescription.code(), dequeueHexBytes(actualCodeSize), methodDescription.getAssertMessage("code"));
        assertEquals((short) 0, dequeue2ByteShort(), methodDescription.getAssertMessage("exceptionTableLength"));
        assertEquals((short) 0, dequeue2ByteShort(), methodDescription.getAssertMessage("attributeAttributesCount"));

        assertEquals(expectedAttributeSize, actualAttributeSize, methodDescription.getAssertMessage("attributeSize"));
        assertEquals(expectedCodeSize, actualCodeSize, methodDescription.getAssertMessage("codeSize"));
    }

    private void noClassAttributes() {
        short classAttributeCount = dequeue2ByteShort();
        assertTrue(bytesInHex.isEmpty(), "classFile ends when expected");
        assertEquals((short) 0, classAttributeCount, "classAttributesCount");
    }

    private String dequeueHexBytes(int amountOfBytes) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < amountOfBytes; i++) {
            result.append(bytesInHex.poll());
        }
        return result.toString();
    }

    private void dequeueResolveAssertConstantPoolIndex(String expected, String context) {
        short index = dequeue2ByteShort();
        assertEquals(expected, constantPoolItems.get(index), context + ": ConstantPoolItem at index " + index);
    }

    private Short dequeue2ByteShort() {
        return Short.parseShort(dequeueHexBytes(2), 16);
    }

    private Integer dequeue4ByteInt() {
        return Integer.parseInt(dequeueHexBytes(4), 16);
    }

    private Long dequeue8ByteLong() {
        return Long.parseLong(dequeueHexBytes(8), 16);
    }
}
