package org.cmjava2023.symboltable;

import org.cmjava2023.ast.ASTNodes;

import java.util.HashMap;

public class SymbolTable {

    private Scope currentScope;

    public SymbolTable() {
        this.currentScope = new GlobalScope(null, new HashMap<>());
        HashMap<String, Symbol> builtIn = bindPrimitiveTypes();

        bindPrintFunction(builtIn);
        bindReadFunction(builtIn);
        bindIoException(builtIn);

        this.currentScope.setSymbols(builtIn);
    }

    private HashMap<String, Symbol> bindPrimitiveTypes() {
        HashMap<String, Symbol> builtIn = new HashMap<>();

        for (BuiltInType type : BuiltInType.values()) {
            builtIn.put(type.getName(), new BuiltInSymbol(type, this.currentScope));
        }
        return builtIn;
    }

    private void bindPrintFunction(HashMap<String, Symbol> builtIn) {
        Function printFunction = new Function(this.currentScope, new HashMap<>(), "System.out.println", BuiltInType.VOID, ASTNodes.AccessModifier.PUBLIC, null);
        Parameter printParameter = new Parameter("x", BuiltInType.STRING, printFunction);
        printFunction.bind(printParameter);
        builtIn.put(printFunction.getName(), printFunction);
    }

    private void bindReadFunction(HashMap<String, Symbol> builtIn) {
        Function readFunction = new Function(this.currentScope, new HashMap<>(), "System.in.read", BuiltInType.INT, ASTNodes.AccessModifier.PUBLIC, null);
        builtIn.put(readFunction.getName(), readFunction);
    }

    private void bindIoException(HashMap<String, Symbol> builtIn) {
        Clazz ioExceptionClazz = new Clazz(this.currentScope, new HashMap<>(), "java.io.IOException", null, null, ASTNodes.AccessModifier.PUBLIC, null);
        ioExceptionClazz.setType(ioExceptionClazz);
        builtIn.put(ioExceptionClazz.getName(), ioExceptionClazz);
    }

    public void addSymbol(Symbol symbol) {
        this.currentScope.bind(symbol);
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
