package org.cmjava2023;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.cmjava2023.generated_from_antlr.MainAntlrLexer;
import org.cmjava2023.generated_from_antlr.MainAntlrParser;
import org.cmjava2023.BytecodeFromClassfileModel;

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

        // To Bytecode :)
        var classFileToAst = new ClassfileModelFromAst();
        var model = classFileToAst.generate((ASTNodes.StartNode)ast);

        var byteCodeFromClassFileModel = new BytecodeFromClassfileModel();
        var bytes = byteCodeFromClassFileModel.generate(model);

        Path outputDirPath = Paths.get(args[1]);
        Files.createDirectories(outputDirPath);
        Files.write(
            Paths.get(outputDirPath.toString(), fileNameWithoutExtensionOf(args[0]) + ".class"),
            bytes
        );
    }

    private static String fileNameWithoutExtensionOf(String path) {
        String fileNameWithExtension = Paths.get(path).getFileName().toString();
        return fileNameWithExtension.split("\\.")[0];
    }
}