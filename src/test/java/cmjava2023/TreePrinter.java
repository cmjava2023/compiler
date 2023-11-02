package cmjava2023;

import org.antlr.v4.runtime.tree.ParseTree;
import org.cmjava2023.ASTNodes;

import java.lang.reflect.Field;
import java.util.Collection;

public class TreePrinter {
    public String printAst(ASTNodes.Node ast) {
        return print("", ast, "", "");
    }
    public String printParseTree(ParseTree parseTree) {
        return print(parseTree, "", "");
    }

    private final String lastNodeOnLevelPrefix = "L  ";
    private final String childrenPrefixWhenNodesFollowOnLevel = "|- ";
    private final String childrenPrefixForLastNodeOnLevel = "   ";
    private final String prefixToFill = "|  ";
    private String print(ParseTree parseTree, String prefix, String childrenPrefix) {
        StringBuilder result = new StringBuilder();
        result.append(prefix);
        if(!parseTree.getClass().getSimpleName().equals("TerminalNodeImpl")) {
            result.append(parseTree.getClass().getSimpleName());
            result.append(": ");
        }
        result.append(parseTree.getText());
        result.append("\n");

        String prefixToBe = childrenPrefix + childrenPrefixWhenNodesFollowOnLevel;
        String childrenPrefixToBe = childrenPrefix + prefixToFill;
        for (int i = 0; i < parseTree.getChildCount(); i++) {
            if (i >= parseTree.getChildCount() - 1) {
                prefixToBe = childrenPrefix + lastNodeOnLevelPrefix;
                childrenPrefixToBe = childrenPrefix + childrenPrefixForLastNodeOnLevel;
            }
            result.append(print(parseTree.getChild(i), prefixToBe, childrenPrefixToBe));
        }

        return result.toString();
    }

    private String print(String fieldName, Object object, String prefix, String childrenPrefix) {
        StringBuilder result = new StringBuilder();
        if(object.getClass().isPrimitive() || object instanceof String || object instanceof Enum<?>) {
            result.append(prefix);
            result.append(fieldName);
            result.append(": ");
            result.append(object);
            result.append("\n");
        } else {
            result.append(prefix);
            if(object instanceof Collection<?> collection) {
                var array = collection.toArray();
                result.append(fieldName);
                result.append("\n");
                for (int i = 0; i < collection.size(); i++) {
                    String prefixToBe = childrenPrefix + childrenPrefixWhenNodesFollowOnLevel;
                    String childrenPrefixToBe = childrenPrefix + prefixToFill;
                    if (i == collection.size() - 1) {
                        prefixToBe = childrenPrefix + lastNodeOnLevelPrefix;
                        childrenPrefixToBe = childrenPrefix + childrenPrefixForLastNodeOnLevel;
                    }
                    result.append(print(String.valueOf(i), array[i], prefixToBe, childrenPrefixToBe));
                }
            }
            else {
                result.append(object.getClass().getSimpleName());
                result.append("\n");
                Field[] fields = object.getClass().getDeclaredFields();
                String prefixToBe = childrenPrefix + childrenPrefixWhenNodesFollowOnLevel;
                String childrenPrefixToBe = childrenPrefix + prefixToFill;
                for (int i = 0; i < fields.length; i++) {
                    if (i >= fields.length - 1) {
                        prefixToBe = childrenPrefix + lastNodeOnLevelPrefix;
                        childrenPrefixToBe = childrenPrefix + childrenPrefixForLastNodeOnLevel;
                    }
                    Field field = fields[i];
                    try {
                        field.trySetAccessible();
                        var fieldValue = field.get(object);
                        result.append(print(field.getName(), fieldValue, prefixToBe, childrenPrefixToBe));
                    } catch (IllegalAccessException ignored) {
                    }
                }
            }
        }

        return result.toString();
    }
}
