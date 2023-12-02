package cmjava2023.helloworld;

import cmjava2023.util.TestPathsHelper;
import cmjava2023.util.TreePrinter;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.cmjava2023.ast.ASTNodes;
import org.cmjava2023.ast.ASTVisitor;
import org.cmjava2023.semanticanalysis.SemanticAnalysisTraverser;
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

        SemanticAnalysisTraverser semantic = new SemanticAnalysisTraverser();
        semantic.visit((ASTNodes.StartNode) ast);

        String expected =
                """
                StartNode
                L  body
                   |- PackageNode
                   |  L  nestedIdentifier
                   |     |- 0: cmjava2023
                   |     L  1: helloworld
                   L  ClassNode
                      |- Clazz
                      |  |- accessModifier: PUBLIC
                      L  body
                         L  FunctionNode
                            |- Function
                            |  |- accessModifier: PUBLIC
                            |  L  instanceModifier: STATIC
                            |- parameters
                            |  L  ParameterNode
                            |     L  Parameter
                            L  body
                               L  FunctionCallNode
                                  |- nestedIdentifier
                                  |  |- 0: System
                                  |  |- 1: out
                                  |  L  2: println
                                  L  values
                                     L  ValueNode
                                        L  value: "Hello world!"
                """;
        assertEquals(expected, new TreePrinter().printAst(ast));
    }
}
