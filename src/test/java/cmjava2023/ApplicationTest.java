package cmjava2023;


import cmjava2023.util.DynamicTestsForTestFilesHelper;
import cmjava2023.util.LineWiseEqualsAssertion;
import cmjava2023.util.TestPathsHelper;
import cmjava2023.util.classFIleTesting.*;
import org.cmjava2023.Main;
import org.cmjava2023.util.BytesInHexQueue;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class ApplicationTest implements DynamicTestsForTestFilesHelper.DynamicTestCallback {
    @TestFactory
    Collection<DynamicTest> tests() throws IOException {
        return DynamicTestsForTestFilesHelper.createForAllTestMainsWithFileOfNameBeside("ClassFileContent.json", this, null);
    }

    @Override
    public Collection<DynamicTest> createTestForMainAndExpectedContent(String nonRootPackagePartsTheClassIsIn, String pathToMain, String contentOfExpectationFile) {
        compileToFileUsingOurCompiler(pathToMain);
        return List.of(DynamicTest.dynamicTest(nonRootPackagePartsTheClassIsIn + " ClassFileAsExpected", () -> {
            String pathToFileCompiledByUs = new TestPathsHelper("cmjava2023/" + nonRootPackagePartsTheClassIsIn.replace("\\", "/")).GetPathOfMainClassCompiledByUsInSamePackage();
            BytesInHexQueue bytesInHex = BytesInHexQueueFromBinaryFileQuery.fetch(pathToFileCompiledByUs);
            new HexClassFileTester().test(bytesInHex, ClassFileContent.load(contentOfExpectationFile, pathToMain));
        }), DynamicTest.dynamicTest(nonRootPackagePartsTheClassIsIn + " outputSameAsJdk", () -> {
            String fullyQualifiedClassNameWithSlash = "cmjava2023/" + nonRootPackagePartsTheClassIsIn.replace("\\", "/") + "/Main";
            String expectedOutput = OutputOfJdkCompiledClassFileQuery.fetch(pathToMain, fullyQualifiedClassNameWithSlash);
            String actualOutput = JavaRunner.RunClassAndGetStdOut(TestPathsHelper.OUR_COMPILER_COMPILED_TEST_FILES_FOLDER, fullyQualifiedClassNameWithSlash);
            LineWiseEqualsAssertion.expectedEqualsActualSystemIndependent(expectedOutput, actualOutput);
        }));
    }

    private void compileToFileUsingOurCompiler(String pathToMain) {
        try {
            Main.main(new String[]{pathToMain, TestPathsHelper.OUR_COMPILER_COMPILED_TEST_FILES_FOLDER});
        } catch (IOException e) {
            fail(e);
        }
    }
}
