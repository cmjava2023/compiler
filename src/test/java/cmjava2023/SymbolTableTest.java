package cmjava2023;


import cmjava2023.util.DynamicTestsForTestFilesHelper;
import cmjava2023.util.LineWiseEqualsAssertion;
import cmjava2023.util.treePrinter.SymbolTableTreePrinter;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.cmjava2023.ast.ASTNodes;
import org.cmjava2023.ast.ParseTreeVisitor;
import org.cmjava2023.semanticanalysis.ASTVisitorFirst;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class SymbolTableTest implements DynamicTestsForTestFilesHelper.DynamicTestCallback {
    @TestFactory
    Collection<DynamicTest> snapshotTests() throws IOException {
        return DynamicTestsForTestFilesHelper.createForAllTestMainsWithFileOfNameBeside("SymbolTable.txt", this, null);
    }

    @Override
    public Collection<DynamicTest> createTestForMainAndExpectedContent(String nonRootPackagePartsTheHelpedClassIsIn, String pathToMain, String contentOfExpectationFile) {
        return List.of(DynamicTest.dynamicTest(nonRootPackagePartsTheHelpedClassIsIn, () -> {
            CharStream charStreamOfGivenFilePath = CharStreams.fromFileName(pathToMain);
            Lexer lexer = new org.cmjava2023.generated_from_antlr.MainAntlrLexer(charStreamOfGivenFilePath);
            org.cmjava2023.generated_from_antlr.MainAntlrParser parser = new org.cmjava2023.generated_from_antlr.MainAntlrParser(new CommonTokenStream(lexer));

            ParseTree tree = parser.start();

            ParseTreeVisitor visitor = new ParseTreeVisitor();

            ASTNodes.Node ast = visitor.visit(tree);

            ASTVisitorFirst astVisitorFirst = new ASTVisitorFirst(visitor.errors);
            ast.accept(astVisitorFirst);

            String actual = SymbolTableTreePrinter.print(visitor.symbolTable);
            LineWiseEqualsAssertion.expectedEqualsActualSystemIndependent(contentOfExpectationFile, actual);
        }));
    }
}
