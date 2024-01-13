package cmjava2023.util.classFIleTesting;

import cmjava2023.bytecodeTestUtil.NextOperationFromByteQueueQuery;
import kotlin.NotImplementedError;
import org.cmjava2023.util.BytesInHexQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static cmjava2023.util.BetterNamedAssertions.assertExpectedEqualsActual;
import static com.ibm.icu.impl.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HexClassFileTester {
    private BytesInHexQueue bytesInHex;
    private String classAccessModifierHex;
    private String thisClass;
    private String superClass;
    private MethodDescription[] methodDescriptions;
    private List<String> constantPoolItems;

    private record ConstantPoolItemToResolve(String type, short index) {
    }

    public void test(BytesInHexQueue bytesInHex, ClassFileContent classFileContent) {
        this.bytesInHex = bytesInHex;
        this.classAccessModifierHex = classFileContent.classAccessModifierHex();
        this.thisClass = classFileContent.thisClass();
        this.superClass = classFileContent.superClass();
        this.methodDescriptions = classFileContent.methodDescriptions();
        this.constantPoolItems = new ArrayList<>();

        classFileIndicatorCafeBabe();
        javaVersion6();
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
       assertExpectedEqualsActual("CAFEBABE", bytesInHex.dequeueHexBytes(4), "ClassFile Start");
    }

    private void javaVersion6() {
       assertExpectedEqualsActual(50, bytesInHex.dequeue4ByteInt(), "JavaVersion");
    }

    private void parseConstantPool() {
        short constantPoolSize = bytesInHex.dequeue2ByteShort();
        ArrayList<Object> unresolvedConstantPool = new ArrayList<>();
        unresolvedConstantPool.add("emptyElementBecauseConstantPoolIndicesStartWith1");
        ArrayList<Integer> unresolvedFieldsInConstantPool = new ArrayList<>();
        for (int i = 0; i < constantPoolSize - 1; i++) {
            String constantInfoTag = bytesInHex.dequeueHexBytes(1);
            switch (Objects.requireNonNull(constantInfoTag)) {
                case "01":
                    short length = bytesInHex.dequeue2ByteShort();
                    unresolvedConstantPool.add(utf8FromHexString(bytesInHex.dequeueHexBytes(length)));
                    break;
                case "03":
                    unresolvedConstantPool.add("Int " + bytesInHex.dequeue4ByteInt());
                    break;
                case "04":
                    unresolvedConstantPool.add("Float " + Float.intBitsToFloat(bytesInHex.dequeue4ByteInt()));
                    break;
                case "05":
                    unresolvedConstantPool.add("Long " + bytesInHex.dequeue8ByteLong());
                    unresolvedConstantPool.add("2nd Long Slot");
                    i++;
                    break;
                case "06":
                    unresolvedConstantPool.add("Double " + Double.longBitsToDouble(bytesInHex.dequeue8ByteLong()));
                    unresolvedConstantPool.add("2nd Double Slot");
                    i++;
                    break;
                case "07":
                    unresolvedConstantPool.add(new ConstantPoolItemToResolve("Class", bytesInHex.dequeue2ByteShort()));
                    break;
                case "08":
                    unresolvedConstantPool.add(new ConstantPoolItemToResolve("Str", bytesInHex.dequeue2ByteShort()));
                    break;
                case "09", "0A", "0C":
                    unresolvedConstantPool.add(constantInfoTag + " " + bytesInHex.dequeueHexBytes(2) + "." + bytesInHex.dequeueHexBytes(2));
                    unresolvedFieldsInConstantPool.add(unresolvedConstantPool.size() - 1);
                    break;
                default:
                    fail("test does not support constant pool type " + constantInfoTag + " constantPool so far:\n" + unresolvedConstantPool.toString().replace(",", ",\n"));
                    break;
            }
        }
        List<String> tempList = unresolvedConstantPool.stream().map(e -> {
            if (e instanceof String s) {
                return s;
            } else if (e instanceof ConstantPoolItemToResolve c) {
                return c.type + " " + unresolvedConstantPool.get(c.index);
            } else {
                throw new NotImplementedError();
            }
        }).toList();

        ArrayList<String> tempConstantPool = new ArrayList<>(tempList);

        for (String e : tempConstantPool){
            if(unresolvedFieldsInConstantPool.contains(tempConstantPool.indexOf(e))){
                String[] splitElement = e.split(" ");
                String referenceSeperator = ".";
                String ConstantInfoType;
                var ConstantType = splitElement[0];
                if (Objects.equals(ConstantType, "0C")){
                    referenceSeperator = ":";
                    ConstantInfoType = "NameAndType";
                } else if (Objects.equals(ConstantType, "0A")){
                    ConstantInfoType = "Methodref";
                }else if (Objects.equals(ConstantType, "09")){
                    ConstantInfoType = "Fieldref";
                } else {
                    throw new NotImplementedError();
                }
                String[] references = splitElement[1].split("\\.");
                var firstReference = Short.parseShort(references[0], 16);
                var secondReference = Short.parseShort(references[1], 16);
                var firstValue = tempConstantPool.get(firstReference);
                var secondValue = tempConstantPool.get(secondReference);
                // if length == 2 .split(" ")[0] refers to "NameAndType", "Methodref", etc.
                // this creates problems when a Methodref contains a NameAndType
                if (firstValue.split(" ").length == 2){
                    firstValue = firstValue.split(" ")[1];
                }
                if (secondValue.split(" ").length == 2){
                    secondValue = secondValue.split(" ")[1];
                }
                tempConstantPool.set(tempConstantPool.indexOf(e), ConstantInfoType + " " + firstValue + referenceSeperator + secondValue);
            }
        }
        constantPoolItems = tempConstantPool.stream().toList();
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
       assertExpectedEqualsActual(classAccessModifierHex, bytesInHex.dequeueHexBytes(2), "classAccessModifier");
    }

    private void thisClass() {
        dequeueResolveAssertConstantPoolIndex(thisClass, "thisClass");
    }

    private void superClass() {
        dequeueResolveAssertConstantPoolIndex(superClass, "superClass");
    }

    private void noInterfaces() {
       assertExpectedEqualsActual((short) 0, bytesInHex.dequeue2ByteShort(), "interfaceCount");
    }

    private void noFields() {
       assertExpectedEqualsActual((short) 0, bytesInHex.dequeue2ByteShort(), "FieldCount");
    }

    private void methods() {
       assertExpectedEqualsActual((short) methodDescriptions.length, bytesInHex.dequeue2ByteShort(), "methodCount");

        for (MethodDescription methodDescription : methodDescriptions) {
           assertExpectedEqualsActual(methodDescription.accessModifierHex(), bytesInHex.dequeueHexBytes(2), methodDescription.getAssertMessage("accessModifier"));
            dequeueResolveAssertConstantPoolIndex(methodDescription.name(), methodDescription.getAssertMessage("name"));
            dequeueResolveAssertConstantPoolIndex(methodDescription.typeDescriptor(), methodDescription.getAssertMessage("typeDescriptor"));
           assertExpectedEqualsActual((short) 1, bytesInHex.dequeue2ByteShort(), methodDescription.getAssertMessage("attributeCount"));
            codeAttribute(methodDescription);
        }
    }

    private void codeAttribute(MethodDescription methodDescription) {
        dequeueResolveAssertConstantPoolIndex("Code", methodDescription.getAssertMessage("attributeName"));
        int actualAttributeSize = bytesInHex.dequeue4ByteInt();
        @SuppressWarnings("unused") short maxStackSize = bytesInHex.dequeue2ByteShort();
        @SuppressWarnings("unused") short maxLocalVars = bytesInHex.dequeue2ByteShort();

        int actualCodeSize = bytesInHex.dequeue4ByteInt();
        BytesInHexQueue actualCode = bytesInHex.getSubQueue(actualCodeSize);
        ArrayList<NextOperationFromByteQueueQuery.ParsedOperation> parsedOperations = new ArrayList<>();
        while(!actualCode.isEmpty()){
            parsedOperations.add(NextOperationFromByteQueueQuery.Companion.fetch(actualCode, constantPoolItems));
        }
        
        StringBuilder opCodesAsString = new StringBuilder();
        for (NextOperationFromByteQueueQuery.ParsedOperation parsedOperation : parsedOperations) {            
            opCodesAsString.append(parsedOperation.getName()).append(": ");
            for(Object value : parsedOperation.getOperands()) {
                opCodesAsString.append("'").append(value.toString()).append("', ");
            }
            opCodesAsString.append("\n");
        }                
        
       assertExpectedEqualsActual(methodDescription.code(), opCodesAsString.toString().stripTrailing(), methodDescription.getAssertMessage("code"));
       assertExpectedEqualsActual((short) 0, bytesInHex.dequeue2ByteShort(), methodDescription.getAssertMessage("exceptionTableLength"));
       assertExpectedEqualsActual((short) 0, bytesInHex.dequeue2ByteShort(), methodDescription.getAssertMessage("attributeAttributesCount"));

        int expectedAttributeSize = 2 + 2 + 4 + actualCodeSize + 2 + 2;
       assertExpectedEqualsActual(expectedAttributeSize, actualAttributeSize, methodDescription.getAssertMessage("attributeSize"));
    }

    private void noClassAttributes() {
        short classAttributeCount = bytesInHex.dequeue2ByteShort();
        assertTrue(bytesInHex.isEmpty(), "classFile ends when expected");
       assertExpectedEqualsActual((short) 0, classAttributeCount, "classAttributesCount");
    }

    private void dequeueResolveAssertConstantPoolIndex(String expected, String context) {
        short index = bytesInHex.dequeue2ByteShort();
       assertExpectedEqualsActual(expected, constantPoolItems.get(index), context + ": ConstantPoolItem at index " + index);
    }
}
