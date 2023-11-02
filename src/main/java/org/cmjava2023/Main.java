package org.cmjava2023;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.cmjava2023.generated_from_antlr.MainAntlrLexer;
import org.cmjava2023.generated_from_antlr.MainAntlrParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HexFormat;

public class Main {
    public static void main(String[] args) throws IOException {
        CharStream charStreamOfGivenFilePath = CharStreams.fromFileName(args[0]);
        Lexer lexer = new MainAntlrLexer(charStreamOfGivenFilePath);
        MainAntlrParser parser = new MainAntlrParser(new CommonTokenStream(lexer));

        ParseTree tree = parser.start();

        Path outputDirPath = Paths.get(args[1]);
        Files.createDirectories(outputDirPath);
        Files.write(
            Paths.get(outputDirPath.toString(), fileNameWithoutExtensionOf(args[0]) + ".class"),
            HexFormat.of().parseHex("cafebabe00000034001d0a000200030700040c000500060100106a6176612f6c616e672f4f626a6563740100063c696e69743e010003282956090008000907000a0c000b000c0100106a6176612f6c616e672f53797374656d0100036f75740100154c6a6176612f696f2f5072696e7453747265616d3b08000e01000c48656c6c6f20776f726c64210a001000110700120c001300140100136a6176612f696f2f5072696e7453747265616d0100077072696e746c6e010015284c6a6176612f6c616e672f537472696e673b29560700160100136f72672f636d6a617661323032332f4d61696e010004436f646501000f4c696e654e756d6265725461626c650100046d61696e010016285b4c6a6176612f6c616e672f537472696e673b295601000a536f7572636546696c650100094d61696e2e6a617661002100150002000000000002000100050006000100170000001d00010001000000052ab70001b10000000100180000000600010000000300090019001a00010017000000250002000100000009b20007120db6000fb10000000100180000000a000200000005000800060001001b00000002001c"));
    }

    private static String fileNameWithoutExtensionOf(String path) {
        String fileNameWithExtension = Paths.get(path).getFileName().toString();
        return fileNameWithExtension.split("\\.")[0];
    }
}