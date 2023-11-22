package org.cmjava2023.symboltable;

import java.util.HashMap;

public class SymbolTable {

    private Scope currentScope;

    public SymbolTable() {
        this.currentScope = new GlobalScope(null, new HashMap<>());
        String[] primitiveTypes = {"byte", "short", "int", "long", "float", "double", "char", "boolean", "void", "String"};
        HashMap<String, Symbol> builtIn = new HashMap<>();

        for (String type : primitiveTypes) {
            BuiltIn builtInSymbol = new BuiltIn(type, null, this.currentScope);
            builtInSymbol.setType(builtInSymbol);
            builtIn.put(type, builtInSymbol);
        }

        Function printFunction = new Function(this.currentScope, new HashMap<>(), "System.out.println", builtIn.get("void").getType());
        Variable printParameter = new Variable("x", builtIn.get("String").getType(), printFunction);
        printFunction.bind(printParameter);
        builtIn.put(printFunction.getName(), printFunction);

        this.currentScope.setSymbols(builtIn);
    }

    public void addSymbol(Symbol symbol) {
        this.currentScope.addSymbol(symbol);
    }

    public void setSymbols(HashMap<String, Symbol> symbols) {
        this.currentScope.setSymbols(symbols);
    }

    public void setScope(Scope scope) {
        this.currentScope = scope;
    }

    public void popScope() {
        this.currentScope = currentScope.getEnclosingScope();
    }
}
