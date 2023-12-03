package cmjava2023.util;

import java.util.Arrays;
import java.util.List;

public class TestPathsHelper {

    protected static String JAVA_FILE_ENDING = ".java";
    protected static String CLASS_FILE_ENDING = ".class";

    public final String nonRootPackagePartsTheHelpedClassIsIn;

    public TestPathsHelper(Object testClassObjectDefiningPackageForResources) {
        List<String> canonicalNameParts = Arrays.stream(testClassObjectDefiningPackageForResources.getClass().getCanonicalName().split("\\.")).toList();
        nonRootPackagePartsTheHelpedClassIsIn = String.join("/", canonicalNameParts.subList(0, canonicalNameParts.size() - 1));
    }

    public static String JAVA_TEST_FILES_RESOURCE_FOLDER_PATH = "src/test/resources/java-test-files";
    public static String OUR_COMPILER_COMPILED_TEST_FILES_FOLDER = "build/test-files-compiled-by-us";
    public static String JDK_COMPILED_TEST_FILES_FOLDER = "build/test-files-jdk-compiled";

    public String GetPathOfMainJavaTestResourceInSamePackage() {
        return JAVA_TEST_FILES_RESOURCE_FOLDER_PATH + "/" + nonRootPackagePartsTheHelpedClassIsIn + "/Main" + JAVA_FILE_ENDING;
    }

    public String GetPathOfMainClassCompiledByUsInSamePackage() {
        return OUR_COMPILER_COMPILED_TEST_FILES_FOLDER + "/" + nonRootPackagePartsTheHelpedClassIsIn + "/Main" + CLASS_FILE_ENDING;
    }

}
