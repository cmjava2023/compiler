package cmjava2023.helloworld;

import cmjava2023.util.TestPathsHelper;
import cmjava2023.util.TreePrinter;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.cmjava2023.ast.ASTVisitor;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(cmjava2023.util.QualifiedDisplayNameGenerator.class)
public class SymbolTableTest {
        @Test
        public void snapshot() throws IOException {
            CharStream charStreamOfGivenFilePath = CharStreams.fromFileName(new TestPathsHelper(this).GetPathOfMainJavaTestResourceInSamePackage());
            Lexer lexer = new org.cmjava2023.generated_from_antlr.MainAntlrLexer(charStreamOfGivenFilePath);
            org.cmjava2023.generated_from_antlr.MainAntlrParser parser = new org.cmjava2023.generated_from_antlr.MainAntlrParser(new CommonTokenStream(lexer));

            ParseTree tree = parser.start();

            ASTVisitor visitor = new ASTVisitor();

            visitor.visit(tree);

            String expected =
                    """
                    GlobalScope
                    |- boolean: boolean
                    |- void: void
                    |- byte: byte
                    |- double: double
                    |- char: char
                    |- short: short
                    |- String: String
                    |- float: float
                    |- Main: Main
                    |  L  main: void
                    |     |- args: String[]
                    |     L  LocalScope
                    |- int: int
                    |- long: long
                    L  System.out.println: void
                       L  x: String
                    """;
            assertEquals(expected, new TreePrinter().printSymbolTable(visitor.symbolTable));
        }
}