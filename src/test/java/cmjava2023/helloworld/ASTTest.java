package cmjava2023.helloworld;

import cmjava2023.util.TestPathsHelper;
import cmjava2023.util.TreePrinter;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.cmjava2023.ASTNodes;
import org.cmjava2023.ASTVisitor;
import org.junit.jupiter.api.Test;
import org.cmjava2023.generated_from_antlr.MainAntlrParser;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ASTTest {
    @Test
    public void helloWorld_AST() throws IOException {
        CharStream charStreamOfGivenFilePath = CharStreams.fromFileName(new TestPathsHelper(this).GetPathOfMainJavaTestResourceInSamePackage());
        Lexer lexer = new org.cmjava2023.generated_from_antlr.MainAntlrLexer(charStreamOfGivenFilePath);
        MainAntlrParser parser = new MainAntlrParser(new CommonTokenStream(lexer));

        ParseTree tree = parser.start();

        ASTVisitor visitor = new ASTVisitor();

        ASTNodes.Node ast = visitor.visit(tree);

        String expected =
                """
                StartNode
                L  body
                   |- PackageNode
                   |  L  identifier: cmjava2023.helloworld
                   L  ClassNode
                      |- identifier: Main
                      |- modifier
                      |  L  0: PUBLIC
                      L  body
                         L  FunctionNode
                            |- identifier: main
                            |- modifier
                            |  |- 0: PUBLIC
                            |  L  1: STATIC
                            |- returnType: void
                            |- parameters
                            |  L  ParameterNode
                            |     |- type: String[]
                            |     L  identifier: args
                            L  body
                               L  FunctionCallNode
                                  |- identifier: System.out.println
                                  L  values
                                     L  ValueNode
                                        L  value: "Hello world!"
                """;
        System.out.println(new TreePrinter().printAst(ast));
        System.out.println(new TreePrinter().printSymbolTable(visitor.symbolTable));
        assertEquals(expected, new TreePrinter().printAst(ast));
    }
}
