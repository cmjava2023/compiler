package cmjava2023.util;

import org.junit.jupiter.api.DynamicTest;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;

public class DynamicTestsForTestFilesHelper {

    public interface DynamicTestCallback {
        Collection<DynamicTest> createTestForMainAndExpectedContent(String nonRootPackagePartsTheClassIsIn, String pathToMain, String contentOfExpectationFile);
    }

    public static Collection<DynamicTest> createForAllTestMainsWithFileOfNameBeside(String expectedFileName, DynamicTestCallback dynamicTestCallback, String nullOrOnlyForFilesContainingThis) throws IOException {
        ArrayList<DynamicTest> result = new ArrayList<>();
            Files.walkFileTree(Path.of(TestPathsHelper.JAVA_TEST_FILES_RESOURCE_FOLDER_PATH), new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.endsWith("Main.java") && (nullOrOnlyForFilesContainingThis == null || file.toString().contains(nullOrOnlyForFilesContainingThis))) {
                        Path txtFileWithExpected = file.resolveSibling(expectedFileName);

                        if (Files.exists(txtFileWithExpected)) {
                            String nonRootPackagePartsTheHelpedClassIsIn = Path.of(TestPathsHelper.JAVA_TEST_FILES_RESOURCE_FOLDER_PATH + "/cmjava2023").relativize(file.getParent()).toString().replace("\\", "/");
                            result.addAll(dynamicTestCallback.createTestForMainAndExpectedContent(
                                    nonRootPackagePartsTheHelpedClassIsIn,
                                    file.toString(),
                                    Files.readString(txtFileWithExpected.toAbsolutePath()).replace("\r", "")));
                        }
                    }
                    return super.visitFile(file, attrs);
                }
            });

        return result;
    }
}
