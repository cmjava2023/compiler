package cmjava2023.util.treePrinter;

import org.cmjava2023.symboltable.Scope;
import org.cmjava2023.symboltable.Symbol;
import org.cmjava2023.symboltable.SymbolTable;
import org.cmjava2023.symboltable.SymbolWithScope;

import java.util.*;

public class SymbolTableTreePrinter {


    public String print(SymbolTable symbolTable) {
        return print(symbolTable.getCurrentScope(), new TreePrefixData());
    }

    private String print(Scope scope, TreePrefixData treePrefixData) {
        StringBuilder builder = new StringBuilder();

        if (!(scope instanceof SymbolWithScope)) {
            treePrefixData.appendPrefixToBuilder(builder);
            builder.append(scope.getClass().getSimpleName()).append("\n");
        }

        ArrayList<Scope> childScopes = scope.getChildScopes();

        TreePrefixData nextLevelTreePrefixData = treePrefixData.createForNextLevel();

        Iterator<Map.Entry<String, Symbol>> symbolIterator = scope.getSymbols().entrySet().iterator();

        while (symbolIterator.hasNext()) {
            Map.Entry<String, Symbol> symbol = symbolIterator.next();

            if (!symbolIterator.hasNext() && childScopes.isEmpty()) {
                nextLevelTreePrefixData = treePrefixData.createForLastElementOfNextLevel();
            }

            nextLevelTreePrefixData.appendPrefixToBuilder(builder);
            builder.append(symbol.getKey()).append(": ").append(symbol.getValue().getType().getName()).append("\n");

            if (symbol.getValue() instanceof Scope childScope) {
                builder.append(print(childScope, nextLevelTreePrefixData));
            }
        }

        Iterator<Scope> childScopeIterator = childScopes.iterator();

        nextLevelTreePrefixData = treePrefixData.createForNextLevel();
        while (childScopeIterator.hasNext()) {
            Scope childLocalScope = childScopeIterator.next();

            if (!childScopeIterator.hasNext()) {
                nextLevelTreePrefixData = treePrefixData.createForLastElementOfNextLevel();
            }

            builder.append(print(childLocalScope, nextLevelTreePrefixData));
        }

        return builder.toString();
    }
}
