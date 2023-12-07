package cmjava2023.util.treePrinter;

import org.antlr.v4.runtime.tree.ParseTree;

public class ParseTreePrinter {
    public String print(ParseTree parseTree) {
        return print(parseTree, new TreePrefixData());
    }

    private String print(ParseTree parseTree, TreePrefixData treePrefixData) {
        StringBuilder builder = treePrefixData.getStringBuilderStartingWithPrefix();
        if (!parseTree.getClass().getSimpleName().equals("TerminalNodeImpl")) {
            builder.append(parseTree.getClass().getSimpleName().split("Context")[0]);
            builder.append(": ");
        }
        builder.append(parseTree.getText()).append("\n");

        TreePrefixData nextLevelTreePrefixData = treePrefixData.createForNextLevel();
        for (int i = 0; i < parseTree.getChildCount(); i++) {
            if (i >= parseTree.getChildCount() - 1) {
                nextLevelTreePrefixData = treePrefixData.createForLastElementOfNextLevel();
            }
            builder.append(print(parseTree.getChild(i), nextLevelTreePrefixData));
        }

        return builder.toString();
    }
}
