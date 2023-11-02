package cmjava2023.helloWorld;

import cmjava2023.AbstractTestUsingResourceFiles;
import cmjava2023.TreePrinter;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.cmjava2023.generated_from_antlr.MainAntlrParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest extends AbstractTestUsingResourceFiles {
    @Test
    public void helloWorld_parser() throws IOException {
        CharStream charStreamOfGivenFilePath = CharStreams.fromFileName(GetPathOfJavaResourceInSamePackage("Main.java"));
        Lexer lexer = new org.cmjava2023.generated_from_antlr.MainAntlrLexer(charStreamOfGivenFilePath);
        MainAntlrParser parser = new MainAntlrParser(new CommonTokenStream(lexer));

        ParseTree tree = parser.start();

        String expected =
                """
                StartContext: packageorg.cmjava2023;publicclassMain{publicstaticvoidmain(String[]args){System.out.println("Hello world!");}}
                |- StatementContext: packageorg.cmjava2023;
                |  |- Package_declarationContext: packageorg.cmjava2023
                |  |  |- package
                |  |  L  org.cmjava2023
                |  L  ;
                L  StatementContext: publicclassMain{publicstaticvoidmain(String[]args){System.out.println("Hello world!");}}
                   L  Class_declarationContext: publicclassMain{publicstaticvoidmain(String[]args){System.out.println("Hello world!");}}
                      |- public
                      |- class
                      |- Main
                      |- {
                      |- Class_bodyContext: publicstaticvoidmain(String[]args){System.out.println("Hello world!");}
                      |  L  Function_declarationContext: publicstaticvoidmain(String[]args){System.out.println("Hello world!");}
                      |     |- public
                      |     |- static
                      |     |- void
                      |     |- main
                      |     |- (
                      |     |- Function_declaration_argsContext: String[]args
                      |     |  L  Function_declaration_argContext: String[]args
                      |     |     |- String[]
                      |     |     L  args
                      |     |- )
                      |     |- {
                      |     |- Function_declaration_bodyContext: System.out.println("Hello world!");
                      |     |  L  ExpressionContext: System.out.println("Hello world!");
                      |     |     |- Function_callContext: System.out.println("Hello world!")
                      |     |     |  |- System.out.println
                      |     |     |  |- (
                      |     |     |  |- Function_argsContext: "Hello world!"
                      |     |     |  |  L  Function_argContext: "Hello world!"
                      |     |     |  |     L  "Hello world!"
                      |     |     |  L  )
                      |     |     L  ;
                      |     L  }
                      L  }
                """;

        assertEquals(expected, new TreePrinter().printParseTree(tree));
    }
}
