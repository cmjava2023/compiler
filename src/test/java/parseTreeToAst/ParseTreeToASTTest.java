package parseTreeToAst;

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

public class ParseTreeToASTTest {
    @Test
    public void test() throws IOException {
        CharStream charStreamOfGivenFilePath = CharStreams.fromFileName("src/test/resources/OurClassFileEqualsJdksClassFile/hello_world/Main.java");
        Lexer lexer = new org.cmjava2023.generated_from_antlr.MainAntlrLexer(charStreamOfGivenFilePath);
        MainAntlrParser parser = new MainAntlrParser(new CommonTokenStream(lexer));

        ParseTree tree = parser.start();

        ASTVisitor visitor = new ASTVisitor();

        ASTNodes.Node ast = visitor.visit(tree);

        assertEquals("", ast.toString());
    }
}
