package cmjava2023;


import cmjava2023.util.DynamicTestsForTestFilesHelper;
import cmjava2023.util.TestPathsHelper;
import cmjava2023.util.classFIleTesting.*;
import com.google.gson.Gson;
import org.cmjava2023.Main;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ApplicationTest implements DynamicTestsForTestFilesHelper.DynamicTestCallback {
    @TestFactory
    Collection<DynamicTest> tests() throws IOException {
        return DynamicTestsForTestFilesHelper.createForAllTestMainsWithFileOfNameBeside("ClassFileContent.json", this);
    }

    @Override
    public Collection<DynamicTest> createTestForMainAndExpectedContent(String nonRootPackagePartsTheHelpedClassIsIn, String pathToMain, String contentOfExpectationFile) {
        runOurCompiler(pathToMain);
        return List.of(DynamicTest.dynamicTest(nonRootPackagePartsTheHelpedClassIsIn + " ClassFileAsExpected", () -> {
            ClassFileContent expectedClassFileContent = new Gson().fromJson(contentOfExpectationFile, ClassFileContent.class);
            Queue<String> bytesInHex = BytesInHexQueueFromBinaryFileQuery.fetch(new TestPathsHelper("cmjava2023/" + nonRootPackagePartsTheHelpedClassIsIn).GetPathOfMainClassCompiledByUsInSamePackage());
            HexClassFileTester.test(bytesInHex, expectedClassFileContent);
        }), DynamicTest.dynamicTest(nonRootPackagePartsTheHelpedClassIsIn + " outputSameAsJdk", () -> {
            String fullyQualifiedClassNameWithSlash = "cmjava2023/" + nonRootPackagePartsTheHelpedClassIsIn.replace("\\", "/") + "/Main";
            String expectedOutput = OutputOfJdkCompiledClassFileQuery.fetch(pathToMain, fullyQualifiedClassNameWithSlash);
            String actualOutput = JavaRunner.RunClassAndGetStdOut(TestPathsHelper.OUR_COMPILER_COMPILED_TEST_FILES_FOLDER, fullyQualifiedClassNameWithSlash);
            assertEquals(expectedOutput, actualOutput);
        }));
    }

    private void runOurCompiler(String pathToMain) {
        try {
            Main.main(new String[]{pathToMain, TestPathsHelper.OUR_COMPILER_COMPILED_TEST_FILES_FOLDER});
        } catch (IOException e) {
            fail(e);
        }
    }
}
