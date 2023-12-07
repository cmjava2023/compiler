package cmjava2023.helloworld;

import cmjava2023.util.TestPathsHelper;
import cmjava2023.util.treePrinter.GenericTreePrinter;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.cmjava2023.ast.ASTNodes;
import org.cmjava2023.ast.ASTVisitor;
import org.cmjava2023.generated_from_antlr.MainAntlrParser;
import org.cmjava2023.semanticanalysis.SemanticAnalysisTraverser;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(cmjava2023.util.QualifiedDisplayNameGenerator.class)
public class ASTTest {
    @Test
    public void snapshot() throws IOException {
        CharStream charStreamOfGivenFilePath = CharStreams.fromFileName(new TestPathsHelper(this).GetPathOfMainJavaTestResourceInSamePackage());
        Lexer lexer = new org.cmjava2023.generated_from_antlr.MainAntlrLexer(charStreamOfGivenFilePath);
        MainAntlrParser parser = new MainAntlrParser(new CommonTokenStream(lexer));

        ParseTree tree = parser.start();

        ASTVisitor visitor = new ASTVisitor();

        ASTNodes.Node ast = visitor.visit(tree);

        SemanticAnalysisTraverser semanticAnalysisTraverser = new SemanticAnalysisTraverser(visitor.errors);

        semanticAnalysisTraverser.visit((ASTNodes.StartNode) ast);

        String expected =
                """
                        root (field of type StartNode)
                        L  body
                           |- 0 (field of type PackageNode)
                           |  L  nestedIdentifier
                           |     |- 0: cmjava2023
                           |     L  1: helloworld
                           L  1 (field of type ClassNode)
                              |- classSymbol (field of type Clazz)
                              |  |- accessModifier: PUBLIC
                              |  |- instanceModifier: null
                              |  L  name: Main
                              L  body
                                 L  0 (field of type FunctionNode)
                                    |- functionSymbol (field of type Function)
                                    |  |- accessModifier: PUBLIC
                                    |  |- instanceModifier: STATIC
                                    |  |- name: main
                                    |  L  type (field of type BuiltIn)
                                    |     L  name: void
                                    |- parameters
                                    |  L  0 (field of type ParameterNode)
                                    |     L  parameterSymbol (field of type Parameter)
                                    |        |- name: args
                                    |        L  type (field of type ArrayType)
                                    |           L  maxArraySize: -1
                                    L  body
                                       L  0 (field of type FunctionCallNode)
                                          |- nestedIdentifier
                                          |  |- 0: System
                                          |  |- 1: out
                                          |  L  2: println
                                          L  values
                                             L  0 (field of type ValueNode)
                                                L  value: Hello world!
                            """;
        assertEquals(expected, new GenericTreePrinter().print(ast));
    }
}
