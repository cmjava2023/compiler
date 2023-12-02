package cmjava2023.util;

import org.antlr.v4.runtime.tree.ParseTree;
import org.cmjava2023.ASTNodes;
import org.cmjava2023.symboltable.Scope;
import org.cmjava2023.symboltable.Symbol;
import org.cmjava2023.symboltable.SymbolTable;
import org.cmjava2023.symboltable.SymbolWithScope;

import java.lang.reflect.Field;
import java.util.*;

public class TreePrinter {
    public String printAst(ASTNodes.Node ast) {
        return print("", ast, "", "");
    }

    public String printParseTree(ParseTree parseTree) {
        return print(parseTree, "", "");
    }

    public String printSymbolTable(SymbolTable symbolTable) {
        return print(symbolTable.getCurrentScope(), "", "");
    }

    private final String lastNodeOnLevelPrefix = "L  ";
    private final String childrenPrefixWhenNodesFollowOnLevel = "|- ";
    private final String childrenPrefixForLastNodeOnLevel = "   ";
    private final String prefixToFill = "|  ";

    private String print(ParseTree parseTree, String prefix, String childrenPrefix) {
        StringBuilder result = new StringBuilder();
        result.append(prefix);
        if (!parseTree.getClass().getSimpleName().equals("TerminalNodeImpl")) {
            result.append(parseTree.getClass().getSimpleName().split("Context")[0]);
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
        if (object.getClass().isPrimitive() || object instanceof String || object instanceof Enum<?>) {
            result.append(prefix);
            result.append(fieldName);
            result.append(": ");
            result.append(object);
            result.append("\n");
        } else {
            result.append(prefix);
            if (object instanceof Collection<?> || object.getClass().isArray()) {
                Object[] array;
                if (object instanceof Collection<?> collection) {
                    array = collection.toArray();
                } else {
                    array = (Object[]) object;
                }
                result.append(fieldName);
                result.append("\n");
                for (int i = 0; i < array.length; i++) {
                    String prefixToBe = childrenPrefix + childrenPrefixWhenNodesFollowOnLevel;
                    String childrenPrefixToBe = childrenPrefix + prefixToFill;
                    if (i == array.length - 1) {
                        prefixToBe = childrenPrefix + lastNodeOnLevelPrefix;
                        childrenPrefixToBe = childrenPrefix + childrenPrefixForLastNodeOnLevel;
                    }
                    result.append(print(String.valueOf(i), array[i], prefixToBe, childrenPrefixToBe));
                }
            } else {
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
                        if (fieldValue != null) {
                            result.append(print(field.getName(), fieldValue, prefixToBe, childrenPrefixToBe));
                        }
                    } catch (IllegalAccessException ignored) {
                    }
                }
            }
        }

        return result.toString();
    }

    private String print(Scope scope, String prefix, String childrenPrefix) {
        StringBuilder result = new StringBuilder();

        if (!(scope instanceof SymbolWithScope)) {
            result.append(prefix);
            result.append(scope.getClass().getSimpleName());
            result.append("\n");
        }

        HashMap<String, Symbol> globalSymbols = scope.getSymbols();
        ArrayList<Scope> childLocalScopes = scope.getChildScopes();

        String prefixToBe = childrenPrefix + childrenPrefixWhenNodesFollowOnLevel;
        String childrenPrefixToBe = childrenPrefix + prefixToFill;

        Iterator<Map.Entry<String, Symbol>> symbolIterator = globalSymbols.entrySet().iterator();

        while (symbolIterator.hasNext()) {
            Map.Entry<String, Symbol> symbol = symbolIterator.next();

            if (!symbolIterator.hasNext() && childLocalScopes.isEmpty()) {
                prefixToBe = childrenPrefix + lastNodeOnLevelPrefix;
                childrenPrefixToBe = childrenPrefix + childrenPrefixForLastNodeOnLevel;
            }

            result.append(prefixToBe);
            result.append(symbol.getKey());
            result.append(": ");
            result.append(symbol.getValue().getType().getName());
            result.append("\n");

            if (symbol.getValue() instanceof Scope childScope) {
                result.append(print(childScope, prefixToBe, childrenPrefixToBe));
            }
        }

        Iterator<Scope> childScopeIterator = childLocalScopes.iterator();

        while (childScopeIterator.hasNext()) {
            Scope childLocalScope = childScopeIterator.next();

            if (!childScopeIterator.hasNext()) {
                prefixToBe = childrenPrefix + lastNodeOnLevelPrefix;
                childrenPrefixToBe = childrenPrefix + childrenPrefixForLastNodeOnLevel;
            }

            result.append(print(childLocalScope, prefixToBe, childrenPrefixToBe));
        }

        return result.toString();
    }
}
