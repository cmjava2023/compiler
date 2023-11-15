package org.cmjava2023.symboltable;

import java.util.HashMap;

public class Function extends SymbolWithScope {

    public Function(Scope enclosingScope, HashMap<String, Symbol> symbols, String name, Type type) {
        super(enclosingScope, symbols, name, type);
    }
}
