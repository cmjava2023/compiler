package cmjava2023.primitive.ints.logicops;

import cmjava2023.util.TestPathsHelper;
import cmjava2023.util.TreePrinter;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.cmjava2023.generated_from_antlr.MainAntlrParser;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(cmjava2023.util.QualifiedDisplayNameGenerator.class)
public class ParserTest {
    @Test
    public void snapshot() throws IOException {
        CharStream charStreamOfGivenFilePath = CharStreams.fromFileName(new TestPathsHelper(this).GetPathOfMainJavaTestResourceInSamePackage());
        Lexer lexer = new org.cmjava2023.generated_from_antlr.MainAntlrLexer(charStreamOfGivenFilePath);
        MainAntlrParser parser = new MainAntlrParser(new CommonTokenStream(lexer));

        ParseTree tree = parser.start();

        String expected =
                """
                Start: packagecmjava2023.helloworld;publicclassMain{publicstaticvoidmain(String[]args){System.out.println("Hello world!");}}
                |- Statement: packagecmjava2023.helloworld;
                |  |- Package_declaration: packagecmjava2023.helloworld
                |  |  |- package
                |  |  L  cmjava2023.helloworld
                |  L  ;
                L  Statement: publicclassMain{publicstaticvoidmain(String[]args){System.out.println("Hello world!");}}
                   L  Class_declaration: publicclassMain{publicstaticvoidmain(String[]args){System.out.println("Hello world!");}}
                      |- public
                      |- class
                      |- Main
                      |- {
                      |- Class_body: publicstaticvoidmain(String[]args){System.out.println("Hello world!");}
                      |  L  Function_declaration: publicstaticvoidmain(String[]args){System.out.println("Hello world!");}
                      |     |- public
                      |     |- static
                      |     |- void
                      |     |- main
                      |     |- (
                      |     |- Function_declaration_args: String[]args
                      |     |  L  Function_declaration_arg: String[]args
                      |     |     |- String[]
                      |     |     L  args
                      |     |- )
                      |     |- {
                      |     |- Function_declaration_body: System.out.println("Hello world!");
                      |     |  L  Expression: System.out.println("Hello world!");
                      |     |     |- Function_call: System.out.println("Hello world!")
                      |     |     |  |- System.out.println
                      |     |     |  |- (
                      |     |     |  |- Function_args: "Hello world!"
                      |     |     |  |  L  Function_arg: "Hello world!"
                      |     |     |  |     L  "Hello world!"
                      |     |     |  L  )
                      |     |     L  ;
                      |     L  }
                      L  }
                """;

        assertEquals(expected, new TreePrinter().printParseTree(tree));
    }
}
