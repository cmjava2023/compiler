package org.cmjava2023.symboltable;

import java.util.HashMap;

public abstract  class SymbolWithScope extends BaseScope implements Symbol {
    protected final String name;
    private final Type type;

    public SymbolWithScope(Scope enclosingScope, HashMap<String, Symbol> symbols, String name, Type type) {
        super(enclosingScope, symbols);
        this.name = name;
        this.type = type;
    }

    @Override
    public Scope getScope() {
        return enclosingScope;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Type getType() {
        return type;
    }
}
