package cmjava2023.primitive.doubles.mathops;

import cmjava2023.util.TestPathsHelper;
import cmjava2023.util.treePrinter.ParseTreePrinter;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.cmjava2023.generated_from_antlr.MainAntlrParser;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(cmjava2023.util.QualifiedDisplayNameGenerator.class)
public class ParserTest {
    @Test
    @Disabled
    public void snapshot() throws IOException {
        CharStream charStreamOfGivenFilePath = CharStreams.fromFileName(new TestPathsHelper(this).GetPathOfMainJavaTestResourceInSamePackage());
        Lexer lexer = new org.cmjava2023.generated_from_antlr.MainAntlrLexer(charStreamOfGivenFilePath);
        MainAntlrParser parser = new MainAntlrParser(new CommonTokenStream(lexer));

        ParseTree tree = parser.start();

                String expected =
                """
                <Start: packagecmjava2023.helloworld;publicclassMain{publicstaticvoidmain(String[]args){System.out.println("Hello world!");}}
                          |- Global_scope: packagecmjava2023.helloworld;
                          |  |- Package_declaration: packagecmjava2023.helloworld
                          |  |  |- package
                          |  |  L  Identifier: cmjava2023.helloworld
                          |  |     |- cmjava2023
                          |  |     |- .
                          |  |     L  helloworld
                          |  L  ;
                          L  Global_scope: publicclassMain{publicstaticvoidmain(String[]args){System.out.println("Hello world!");}}
                             L  Class_declaration: publicclassMain{publicstaticvoidmain(String[]args){System.out.println("Hello world!");}}
                                |- Access_modifier: public
                                |  L  public
                                |- class
                                |- Main
                                |- {
                                |- Class_scope: publicstaticvoidmain(String[]args){System.out.println("Hello world!");}
                                |  L  Function_declaration: publicstaticvoidmain(String[]args){System.out.println("Hello world!");}
                                |     |- Access_modifier: public
                                |     |  L  public
                                |     |- static
                                |     |- Type: void
                                |     |  L  void
                                |     |- main
                                |     |- (
                                |     |- Function_declaration_args: String[]args
                                |     |  L  Function_declaration_arg: String[]args
                                |     |     |- Type: String[]
                                |     |     |  L  Array_type: String[]
                                |     |     |     |- Class_type: String
                                |     |     |     |  L  String
                                |     |     |     |- [
                                |     |     |     L  ]
                                |     |     L  args
                                |     |- )
                                |     |- {
                                |     |- Function_scope: System.out.println("Hello world!");
                                |     |  |- Expressions: System.out.println("Hello world!")
                                |     |  |  L  Expression: System.out.println("Hello world!")
                                |     |  |     L  Function_call: System.out.println("Hello world!")
                                |     |  |        |- Identifier: System.out.println
                                |     |  |        |  |- System
                                |     |  |        |  |- .
                                |     |  |        |  |- out
                                |     |  |        |  |- .
                                |     |  |        |  L  println
                                |     |  |        |- (
                                |     |  |        |- Function_args: "Hello world!"
                                |     |  |        |  L  Function_arg: "Hello world!"
                                |     |  |        |     L  Expressions: "Hello world!"
                                |     |  |        |        L  Expression: "Hello world!"
                                |     |  |        |           L  "Hello world!"
                                |     |  |        L  )
                                |     |  L  ;
                                |     L  }
                                L  }
                        """;

        assertEquals(expected, new ParseTreePrinter().print(tree));
    }
}
