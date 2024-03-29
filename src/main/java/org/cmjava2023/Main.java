package org.cmjava2023;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.cmjava2023.ast.ASTNodes;
import org.cmjava2023.ast.ParseTreeVisitor;
import org.cmjava2023.astToClassFileData.ClassfileDataFromAstQuery;
import org.cmjava2023.classFileDataToBytes.ClassFileDataBytesQuery;
import org.cmjava2023.astToClassFileData.ConstantPoolBuilder;
import org.cmjava2023.generated_from_antlr.MainAntlrLexer;
import org.cmjava2023.generated_from_antlr.MainAntlrParser;
import org.cmjava2023.semanticanalysis.ASTVisitorFirst;

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
        ParseTreeVisitor visitor = new ParseTreeVisitor();
        ASTNodes.Node ast = visitor.visit(tree);
        ASTVisitorFirst astVisitorFirst = new ASTVisitorFirst(visitor.errors);
        ASTNodes.Node modifiedAST = ast.accept(astVisitorFirst);

        if(!visitor.errors.isEmpty()) {
            throw new IllegalArgumentException(String.join("\n\n", visitor.errors));
        }
        var constantPoolBuilder = new ConstantPoolBuilder();
        var classFileModel = new ClassfileDataFromAstQuery(constantPoolBuilder).fetch((ASTNodes.StartNode)modifiedAST);
        var bytesForClassFile = new ClassFileDataBytesQuery(constantPoolBuilder).fetch(classFileModel);

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