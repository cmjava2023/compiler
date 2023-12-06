package cmjava2023.helloworld;

import cmjava2023.util.HexClassFileTester;
import cmjava2023.util.MethodDescription;
import cmjava2023.util.BytesInHexQueueFromBinaryFileQuery;
import cmjava2023.util.JavaRunner;
import cmjava2023.util.TestPathsHelper;
import org.cmjava2023.Main;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(cmjava2023.util.QualifiedDisplayNameGenerator.class)
public class ApplicationTest {

    @BeforeAll
    static void compileWithOurCompiler() throws IOException {
        TestPathsHelper testPathsHelper = new TestPathsHelper(new ApplicationTest());
        Main.main(new String[] {
            testPathsHelper.GetPathOfMainJavaTestResourceInSamePackage(),
            TestPathsHelper.OUR_COMPILER_COMPILED_TEST_FILES_FOLDER
        });
    }

    @Test
    public void runningPrintsCorrectOutput() {
        String expectedOutput = "Hello world!";
        String actualOutput = JavaRunner.RunClassAndGetStdOut(TestPathsHelper.OUR_COMPILER_COMPILED_TEST_FILES_FOLDER, "cmjava2023/helloworld/Main");
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void classFileCorrect() {
        Queue<String> bytesInHex = BytesInHexQueueFromBinaryFileQuery.fetch(new TestPathsHelper(this).GetPathOfMainClassCompiledByUsInSamePackage());

        String classAccessModifierHex = "0021";
        String thisClass = "cmjava2023/helloworld/Main";
        String superClass = "java/lang/Object";

        HexClassFileTester.test(
            bytesInHex,
            classAccessModifierHex,
            thisClass,
            superClass,
            new MethodDescription[] {
                new MethodDescription( "0009", "main", "([Ljava/lang/String;)V", "B2000D120FB60015B1"),
                new MethodDescription( "0001", "<init>", "()V", "2AB7001EB1"),
            }
        );
    }
}