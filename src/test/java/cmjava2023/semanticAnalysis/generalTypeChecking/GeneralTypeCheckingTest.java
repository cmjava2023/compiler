package cmjava2023.semanticAnalysis.generalTypeChecking;

import cmjava2023.util.TestPathsHelper;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.cmjava2023.ast.ASTNodes;
import org.cmjava2023.ast.ASTVisitor;
import org.cmjava2023.semanticanalysis.SemanticAnalysisTraverser;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(cmjava2023.util.QualifiedDisplayNameGenerator.class)
public class GeneralTypeCheckingTest {
    @Test
    public void snapshot() throws IOException {
        CharStream charStreamOfGivenFilePath = CharStreams.fromFileName(new TestPathsHelper(this).GetPathOfMainJavaTestResourceInSamePackage());
        Lexer lexer = new org.cmjava2023.generated_from_antlr.MainAntlrLexer(charStreamOfGivenFilePath);
        org.cmjava2023.generated_from_antlr.MainAntlrParser parser = new org.cmjava2023.generated_from_antlr.MainAntlrParser(new CommonTokenStream(lexer));

        ParseTree tree = parser.start();

        ASTVisitor visitor = new ASTVisitor();

        ASTNodes.Node ast = visitor.visit(tree);

        SemanticAnalysisTraverser semanticAnalysisTraverser = new SemanticAnalysisTraverser(visitor.errors);

        semanticAnalysisTraverser.visit((ASTNodes.StartNode) ast);

        semanticAnalysisTraverser.errors.forEach(System.out::println);

        assertEquals(semanticAnalysisTraverser.errors.size(), 3);
    }
}
