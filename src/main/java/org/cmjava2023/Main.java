package org.cmjava2023;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.cmjava2023.generated_from_antlr.MainAntlrLexer;
import org.cmjava2023.generated_from_antlr.MainAntlrParser;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        CharStream charStreamOfGivenFilePath = CharStreams.fromFileName(args[0]);
        Lexer lexer = new MainAntlrLexer(charStreamOfGivenFilePath);
        MainAntlrParser parser = new MainAntlrParser(new CommonTokenStream(lexer));

        ParseTree tree = parser.start();

        Path outputDirPath = Paths.get(args[1]);
        Files.createDirectories(outputDirPath);
        PrintWriter printWriter = new PrintWriter(Paths.get(outputDirPath.toString(), fileNameWithoutExtensionOf(args[0]) + ".class").toString());
        printWriter.write(tree.getText());
        printWriter.close();
    }

    private static String fileNameWithoutExtensionOf(String path) {
        String fileNameWithExtension = Paths.get(path).getFileName().toString();
        return fileNameWithExtension.split("\\.")[0];
    }
}