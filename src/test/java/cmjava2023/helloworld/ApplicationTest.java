package cmjava2023.helloworld;

import cmjava2023.util.HexClassFileTester;
import cmjava2023.util.MethodTestData;
import cmjava2023.util.HexQueueFromBinaryFileQuery;
import cmjava2023.util.JavaRunner;
import cmjava2023.util.TestPathsHelper;
import org.cmjava2023.Main;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import java.io.IOException;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationTest {

    @Test
    @DisabledIfEnvironmentVariable(named = "CI", matches = "true")
    public void helloWorld_debugCompiling() throws IOException {
        TestPathsHelper testPathsHelper = new TestPathsHelper(this);
        Main.main(new String[] {
            testPathsHelper.GetPathOfMainJavaTestResourceInSamePackage(),
            TestPathsHelper.OUR_COMPILER_COMPILED_TEST_FILES_FOLDER + "/" + testPathsHelper.nonRootPackagePartsTheHelpedClassIsIn
        });
    }

    @Test
    public void helloWorld_runningPrintsHelloWorld() {
        String expectedOutput = "Hello world!";
        String actualOutput = JavaRunner.RunClassAndGetStdOut(TestPathsHelper.OUR_COMPILER_COMPILED_TEST_FILES_FOLDER, "cmjava2023/helloworld/Main");
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void helloWorld_testsForClassFileContent() {
        Queue<String> resultClassFileByteHex = HexQueueFromBinaryFileQuery.fetch(new TestPathsHelper(this).GetPathOfMainClassCompiledByUsInSamePackage());

        String classAccessModifierHex = "0021";
        String thisClass = "cmjava2023/helloworld/Main";
        String superClass = "java/lang/Object";

        HexClassFileTester.test(
            resultClassFileByteHex,
            classAccessModifierHex,
            thisClass,
            superClass,
            new MethodTestData[] {
                new MethodTestData( "0009", "main", "([Ljava/lang/String;)V", "B2000D120FB60015B1"),
                new MethodTestData( "0001", "<init>", "()V", "2AB7001EB1"),
            }
        );
    }
}