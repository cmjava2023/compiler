package cmjava2023.helloWorld;

import cmjava2023.AbstractTestUsingResourceFiles;
import org.cmjava2023.Main;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationTest extends AbstractTestUsingResourceFiles {
    @Test
    @Disabled
    public void helloWorld_OurClassFileEqualsJdkEightCompiledClassFileBasedOnJavaP() {
        String expectedJavaP = GetContentOfTestClassFileInSamePackage("Main.javap.txt");
        String actualJavaP = GetJavaPForFileInTemporaryFolder("Main.class");
        assertEquals(expectedJavaP, actualJavaP);
    }

    @Test
    public void helloWorld_runningPrintsHelloWorld() {
        String expectedOutput = "Hello world!";
        String actualOutput = RunClassAndGetStdOut("Main");
        assertEquals(expectedOutput, actualOutput);
    }
    @Test
    public void helloWorld_testsForClassFileContent() throws IOException {
        Main.main(new String[]{ GetPathOfJavaResourceInSamePackage("Main.java"), GetTemporaryFolderPath() });
        Queue<String> resultClassFileByteHex = GetByteHexOfFileInTemporaryFolder("Main.class");

        classFileIndicatorCafeBabe(resultClassFileByteHex);
        javaVersion8(resultClassFileByteHex);
        constantPool(resultClassFileByteHex);
        classAccessModifier(resultClassFileByteHex);
        thisClassIndex(resultClassFileByteHex);
        superClassIndex(resultClassFileByteHex);
        numberOfInterfaces(resultClassFileByteHex);
        numberOfFields(resultClassFileByteHex);
        methods(resultClassFileByteHex);
        classAttributeCount(resultClassFileByteHex);
    }

    private void classFileIndicatorCafeBabe(Queue<String> resultClassFileByteHex) {
        assertEquals("CAFEBABE", pollMultiple(resultClassFileByteHex, 4));
    }

    private void javaVersion8(Queue<String> resultClassFileByteHex) {
        assertEquals("00000034", pollMultiple(resultClassFileByteHex, 4));
    }

    private void constantPool(Queue<String> resultClassFileByteHex) {
        assertEquals("0016", pollMultiple(resultClassFileByteHex, 2));
        ArrayList<String> constantPoolItems = new ArrayList<>();
        for (int i = 0; i < 0x15; i++) {
            String constantInfoTag = resultClassFileByteHex.poll();
            String constantPoolItem = constantInfoTag;
            switch(constantInfoTag){
                case "07":
                case "08":
                    constantPoolItem += pollMultiple(resultClassFileByteHex, 2);
                break;
                case "01":
                    String lengthHex = pollMultiple(resultClassFileByteHex, 2);
                    constantPoolItem += lengthHex;
                    int length = Integer.parseInt(lengthHex, 16);
                    constantPoolItem += pollMultiple(resultClassFileByteHex, length);
                break;
                case "09":
                case "0A":
                case "0C":
                    constantPoolItem += pollMultiple(resultClassFileByteHex, 4);
                break;
                default:
                    fail("test does not support constant pool type " + constantInfoTag);
                break;
            }
            constantPoolItems.add(constantPoolItem);
        }
        assertArrayEquals(
                new String[]{
                        "0100044D61696E",
                        "070001",
                        "0100106A6176612F6C616E672F4F626A656374",
                        "070003",
                        "0100046D61696E",
                        "010016285B4C6A6176612F6C616E672F537472696E673B2956",
                        "010004436F6465",
                        "0100106A6176612F6C616E672F53797374656D",
                        "070008",
                        "0100036F7574",
                        "0100154C6A6176612F696F2F5072696E7453747265616D3B",
                        "0C000A000B",
                        "090009000C",
                        "01000C48656C6C6F20776F726C6421",
                        "08000E",
                        "0100136A6176612F696F2F5072696E7453747265616D",
                        "070010",
                        "0100077072696E746C6E",
                        "010015284C6A6176612F6C616E672F537472696E673B2956",
                        "0C00120013",
                        "0A00110014"
                },
                constantPoolItems.toArray());
    }

    private void classAccessModifier(Queue<String> resultClassFileByteHex) {
        assertEquals("0021", pollMultiple(resultClassFileByteHex, 2));
    }

    private void thisClassIndex(Queue<String> resultClassFileByteHex) {
        assertEquals("0002", pollMultiple(resultClassFileByteHex, 2));
    }

    private void superClassIndex(Queue<String> resultClassFileByteHex) {
        assertEquals("0004", pollMultiple(resultClassFileByteHex, 2));
    }

    private void numberOfInterfaces(Queue<String> resultClassFileByteHex) {
        assertEquals("0000", pollMultiple(resultClassFileByteHex, 2));
    }

    private void numberOfFields(Queue<String> resultClassFileByteHex) {
        assertEquals("0000", pollMultiple(resultClassFileByteHex, 2));
    }

    private void methods(Queue<String> resultClassFileByteHex) {
        assertEquals("0001", pollMultiple(resultClassFileByteHex, 2));
        methodAccessFlags(resultClassFileByteHex);
        methodNameIndex(resultClassFileByteHex);
        typeDescriptorIndex(resultClassFileByteHex);
        methodAttributeCount(resultClassFileByteHex);
        codeAttribute(resultClassFileByteHex);
    }

    private static void methodAccessFlags(Queue<String> resultClassFileByteHex) {
        assertEquals("0009", pollMultiple(resultClassFileByteHex, 2));
    }

    private static void methodNameIndex(Queue<String> resultClassFileByteHex) {
        assertEquals("0005", pollMultiple(resultClassFileByteHex, 2));
    }

    private static void typeDescriptorIndex(Queue<String> resultClassFileByteHex) {
        assertEquals("0006", pollMultiple(resultClassFileByteHex, 2));
    }

    private static void methodAttributeCount(Queue<String> resultClassFileByteHex) {
        assertEquals("0001", pollMultiple(resultClassFileByteHex, 2));
    }

    private static void codeAttribute(Queue<String> resultClassFileByteHex) {
        indexToAttributeNameCode(resultClassFileByteHex);
        assertEquals("00000015", pollMultiple(resultClassFileByteHex, 4));
        assertEquals("0002", pollMultiple(resultClassFileByteHex, 2));
        assertEquals("0001", pollMultiple(resultClassFileByteHex, 2));
        assertEquals("00000009", pollMultiple(resultClassFileByteHex, 4));
        assertEquals("B2000D120FB60015B1", pollMultiple(resultClassFileByteHex, 0x9));
    }

    private static void indexToAttributeNameCode(Queue<String> resultClassFileByteHex) {
        assertEquals("0007", pollMultiple(resultClassFileByteHex, 2));
    }

    private void classAttributeCount(Queue<String> resultClassFileByteHex) {
        assertEquals("0000", pollMultiple(resultClassFileByteHex, 2));
    }
}