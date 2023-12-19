package org.cmjava2023.symboltable;

import org.cmjava2023.ast.ASTNodes;

import java.util.HashMap;

public class SymbolTable {

    private Scope currentScope;

    public SymbolTable() {
        this.currentScope = new GlobalScope(null, new HashMap<>());
        HashMap<String, Symbol> builtIn = bindPrimitiveTypes();

        bindPrintFunction(builtIn);

        this.currentScope.setSymbols(builtIn);
    }

    private HashMap<String, Symbol> bindPrimitiveTypes() {
        String[] primitiveTypes = {"byte", "short", "int", "long", "float", "double", "char", "boolean", "void", "String"};
        HashMap<String, Symbol> builtIn = new HashMap<>();

        for (String type : primitiveTypes) {
            BuiltIn builtInSymbol = new BuiltIn(type, null, this.currentScope);
            builtInSymbol.setType(builtInSymbol);
            builtIn.put(type, builtInSymbol);
        }
        return builtIn;
    }

    private void bindPrintFunction(HashMap<String, Symbol> builtIn) {
        Function printFunction = new Function(this.currentScope, new HashMap<>(), "System.out.println", builtIn.get("void").getType(), ASTNodes.AccessModifier.PUBLIC, null);
        Parameter printParameter = new Parameter("x", builtIn.get("String").getType(), printFunction);
        printFunction.bind(printParameter);
        builtIn.put(printFunction.getName(), printFunction);
    }

    public void addSymbol(Symbol symbol) {
        this.currentScope.bind(symbol);
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

    public Scope getCurrentScope() {
        return currentScope;
    }
}
