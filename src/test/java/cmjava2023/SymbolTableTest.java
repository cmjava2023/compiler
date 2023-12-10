package cmjava2023;


import cmjava2023.util.DynamicTestsForTestFilesHelper;
import cmjava2023.util.treePrinter.SymbolTableTreePrinter;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.cmjava2023.ast.ASTNodes;
import org.cmjava2023.ast.ASTVisitor;
import org.cmjava2023.semanticanalysis.SemanticAnalysisTraverser;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.io.IOException;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SymbolTableTest implements DynamicTestsForTestFilesHelper.DynamicTestCallback {
    @TestFactory
    Collection<DynamicTest> snapshotTests() throws IOException {
        return DynamicTestsForTestFilesHelper.createForAllTestMainsWithTxtOfNameBeside("SymbolTable", this);
    }

    @Override
    public DynamicTest createTestForMainAndExpectedContent(String testName, String pathToMain, String contentOfExpectationFile) {
        return DynamicTest.dynamicTest(testName, () -> {
            CharStream charStreamOfGivenFilePath = CharStreams.fromFileName(pathToMain);
            Lexer lexer = new org.cmjava2023.generated_from_antlr.MainAntlrLexer(charStreamOfGivenFilePath);
            org.cmjava2023.generated_from_antlr.MainAntlrParser parser = new org.cmjava2023.generated_from_antlr.MainAntlrParser(new CommonTokenStream(lexer));

            ParseTree tree = parser.start();

            ASTVisitor visitor = new ASTVisitor();

            ASTNodes.Node ast = visitor.visit(tree);

            SemanticAnalysisTraverser semanticAnalysisTraverser = new SemanticAnalysisTraverser(visitor.errors);

            semanticAnalysisTraverser.visit((ASTNodes.StartNode) ast);

            String actual = SymbolTableTreePrinter.print(visitor.symbolTable);
            assertEquals(contentOfExpectationFile, actual);
        });
    }
}
