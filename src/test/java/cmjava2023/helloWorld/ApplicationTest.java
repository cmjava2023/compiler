package cmjava2023.helloWorld;

import cmjava2023.AbstractTestUsingResourceFiles;
import org.cmjava2023.Main;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationTest extends AbstractTestUsingResourceFiles {
    @Test
    @Disabled
    public void helloWorld_OurClassFileEqualsJdkEightCompiledClassFileBasedOnJavaP() throws IOException {
        Main.main(new String[]{ GetPathOfJavaResourceInSamePackage("Main.java"), GetTemporaryFolderPath() });
        String expectedJavaP = GetContentOfTestClassFileInSamePackage("Main.javap.txt");
        String actualJavaP = GetJavaPForFileInTemporaryFolder("Main.class");
        assertEquals(expectedJavaP, actualJavaP);
    }


    @Test
    public void helloWorld_runningPrintsHelloWorld() throws IOException {
        Main.main(new String[]{ GetPathOfJavaResourceInSamePackage("Main.java"), GetTemporaryFolderPath() });
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
        String constantPoolSizeHex = pollMultiple(resultClassFileByteHex, 2);
        assertEquals("001F", constantPoolSizeHex);
        ArrayList<String> constantPoolItems = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(constantPoolSizeHex, 16) - 1; i++) {
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
                    constantPoolItem += utf8FromHexString(pollMultiple(resultClassFileByteHex, length));
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

        String[] expectedItems = new String[]{
                "010004Main",
                "070001",
                "010010java/lang/Object",
                "070003",
                "010004main",
                "010016([Ljava/lang/String;)V",
                "010004Code",
                "010010java/lang/System",
                "070008",
                "010003out",
                "010015Ljava/io/PrintStream;",
                "0C000A000B",
                "090009000C",
                "01000CHello world!",
                "08000E",
                "010013java/io/PrintStream",
                "070010",
                "010007println",
                "010015(Ljava/lang/String;)V",
                "0C00120013",
                "0A00110014",
                "010006<init>",
                "010003()V",
                "010004Code",
                "010010java/lang/Object",
                "070019",
                "010006<init>",
                "010003()V",
                "0C001B001C",
                "0A001A001D",
        };
        List<String> expectedItemList = new ArrayList<>(Arrays.stream(expectedItems).toList());
        expectedItemList.addAll(Arrays.stream(new String[constantPoolItems.size() - expectedItems.length]).map( s -> "").toList());
        assertArrayEquals(expectedItemList.toArray(), constantPoolItems.toArray());
        assertArrayEquals(expectedItems, constantPoolItems.toArray());
    }

    private String utf8FromHexString(String hexString) {
        StringBuilder builder = new StringBuilder();
        for ( int i = 0 ; i < hexString.length() ; i += 2 ) {
            String substring = hexString.substring( i , i + 2 );
            int codePoint = Integer.parseInt( substring , 16 );
            builder.appendCodePoint( codePoint );
        }
        return builder.toString();
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
        assertEquals("0002", pollMultiple(resultClassFileByteHex, 2));

        methodAccessFlags(resultClassFileByteHex, "0009");
        methodNameIndex(resultClassFileByteHex, "0005");
        typeDescriptorIndex(resultClassFileByteHex, "0006");
        methodAttributeCount(resultClassFileByteHex, "0001");
        codeAttribute(resultClassFileByteHex, "0007" , "00000015", "0002", "0001", "00000009", "B2000D120FB60015B1");


        methodAccessFlags(resultClassFileByteHex, "0001");
        methodNameIndex(resultClassFileByteHex, "0016");
        typeDescriptorIndex(resultClassFileByteHex, "0017");
        methodAttributeCount(resultClassFileByteHex, "0001");
        codeAttribute(resultClassFileByteHex, "0018", "00000011", "0002", "0001", "00000005", "2AB7001EB1");
    }

    private static void methodAccessFlags(Queue<String> resultClassFileByteHex, String expected) {
        assertEquals(expected, pollMultiple(resultClassFileByteHex, 2));
    }

    private static void methodNameIndex(Queue<String> resultClassFileByteHex, String expected) {
        assertEquals(expected, pollMultiple(resultClassFileByteHex, 2));
    }

    private static void typeDescriptorIndex(Queue<String> resultClassFileByteHex, String expected) {
        assertEquals(expected, pollMultiple(resultClassFileByteHex, 2));
    }

    private static void methodAttributeCount(Queue<String> resultClassFileByteHex, String expected) {
        assertEquals(expected, pollMultiple(resultClassFileByteHex, 2));
    }

    private static void codeAttribute(Queue<String> resultClassFileByteHex,  String indexToAttributeName, String expectedAttributeLength, String maxStack, String maxLocals, String codeLength, String expectedCode) {
        indexToAttributeNameCode(resultClassFileByteHex, indexToAttributeName);
        assertEquals(expectedAttributeLength, pollMultiple(resultClassFileByteHex, 4));
        assertEquals(maxStack, pollMultiple(resultClassFileByteHex, 2));
        assertEquals(maxLocals, pollMultiple(resultClassFileByteHex, 2));
        assertEquals(codeLength, pollMultiple(resultClassFileByteHex, 4));
        assertEquals(expectedCode, pollMultiple(resultClassFileByteHex, expectedCode.length() / 2));
        assertEquals("0000", pollMultiple(resultClassFileByteHex, 2));
        assertEquals("0000", pollMultiple(resultClassFileByteHex, 2));
    }

    private static void indexToAttributeNameCode(Queue<String> resultClassFileByteHex, String expected) {
        assertEquals(expected, pollMultiple(resultClassFileByteHex, 2));
    }

    private void classAttributeCount(Queue<String> resultClassFileByteHex) {
        assertEquals("0000", pollMultiple(resultClassFileByteHex, 2));
    }
}