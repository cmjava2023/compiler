package cmjava2023.helloWorld;

import cmjava2023.AbstractTestUsingResourceFiles;
import org.cmjava2023.Main;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

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
}