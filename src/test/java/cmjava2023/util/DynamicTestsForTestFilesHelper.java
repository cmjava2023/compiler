package cmjava2023.util;

import org.junit.jupiter.api.DynamicTest;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;

public class DynamicTestsForTestFilesHelper {

    public interface DynamicTestCallback {
        DynamicTest createTestForMainAndExpectedContent(String testName, String pathToMain, String contentOfExpectationFile);
    }

    public static Collection<DynamicTest> createForAllTestMainsWithTxtOfNameBeside(String expectationTxtFileName, DynamicTestCallback dynamicTestCallback) throws IOException {
        ArrayList<DynamicTest> result = new ArrayList<>();
            Files.walkFileTree(Path.of(TestPathsHelper.JAVA_TEST_FILES_RESOURCE_FOLDER_PATH), new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.endsWith("Main.java")) {
                        Path txtFileWithExpected = file.resolveSibling(expectationTxtFileName + ".txt");

                        if (Files.exists(txtFileWithExpected)) {
                            String testName = Path.of(TestPathsHelper.JAVA_TEST_FILES_RESOURCE_FOLDER_PATH + "/cmjava2023").relativize(file.getParent()).toString();
                            result.add(dynamicTestCallback.createTestForMainAndExpectedContent(
                                    testName,
                                    file.toAbsolutePath().toString(),
                                    Files.readString(txtFileWithExpected.toAbsolutePath()).replaceAll("\r\n", "\n")));
                        }
                    }
                    return super.visitFile(file, attrs);
                }
            });

        return result;
    }
}
