package org.cmjava2023;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.cmjava2023.ast.ASTNodes;
import org.cmjava2023.ast.ASTVisitor;
import org.cmjava2023.generated_from_antlr.MainAntlrLexer;
import org.cmjava2023.generated_from_antlr.MainAntlrParser;
import org.cmjava2023.semanticanalysis.SemanticAnalysisTraverser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {

        CharStream charStreamOfGivenFilePath = CharStreams.fromFileName(args[0]);
        Lexer lexer = new MainAntlrLexer(charStreamOfGivenFilePath);
        MainAntlrParser parser = new MainAntlrParser(new CommonTokenStream(lexer));

        ParseTree tree = parser.start();
        ASTVisitor visitor = new ASTVisitor();
        ASTNodes.Node ast = visitor.visit(tree);
        SemanticAnalysisTraverser semanticAnalysisTraverser = new SemanticAnalysisTraverser(visitor.errors);
        semanticAnalysisTraverser.visit((ASTNodes.StartNode) ast);

        var classFileModel = new ClassfileModelFromAst().generate((ASTNodes.StartNode)ast);
        var bytesForClassFile = new BytecodeFromClassfileModel().generate(classFileModel);

        Path outputDirPath = Paths.get(args[1], classFileModel.getPackageNameWithDelimiterForClassFile());
        Files.createDirectories(outputDirPath);
        Files.write(
            Paths.get(outputDirPath.toString(), fileNameWithoutExtensionOf(args[0]) + ".class"),
            bytesForClassFile
        );
    }

    private static String fileNameWithoutExtensionOf(String path) {
        String fileNameWithExtension = Paths.get(path).getFileName().toString();
        return fileNameWithExtension.split("\\.")[0];
    }
}