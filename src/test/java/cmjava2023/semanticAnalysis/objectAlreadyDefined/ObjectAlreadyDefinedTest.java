package cmjava2023.semanticAnalysis.objectAlreadyDefined;

import cmjava2023.util.TestPathsHelper;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.cmjava2023.ast.ASTNodes;
import org.cmjava2023.ast.ParseTreeVisitor;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(cmjava2023.util.QualifiedDisplayNameGenerator.class)
public class ObjectAlreadyDefinedTest {
    @Test
    public void snapshot() throws IOException {
        CharStream charStreamOfGivenFilePath = CharStreams.fromFileName(new TestPathsHelper(this).GetPathOfMainJavaTestResourceInSamePackage());
        Lexer lexer = new org.cmjava2023.generated_from_antlr.MainAntlrLexer(charStreamOfGivenFilePath);
        org.cmjava2023.generated_from_antlr.MainAntlrParser parser = new org.cmjava2023.generated_from_antlr.MainAntlrParser(new CommonTokenStream(lexer));

        ParseTree tree = parser.start();

        ParseTreeVisitor visitor = new ParseTreeVisitor();

        ASTNodes.Node ast = visitor.visit(tree);

        visitor.errors.forEach(System.out::println);

        assertEquals(visitor.errors.size(), 5);
    }
}
