package org.cmjava2023.symboltable;

import java.util.HashMap;

public abstract class BaseScope implements Scope {
    protected final Scope enclosingScope;
    protected final HashMap<String, Symbol> symbols;

    public BaseScope(Scope enclosingScope, HashMap<String, Symbol> symbols) {
        this.enclosingScope = enclosingScope;
        this.symbols = symbols;
    }

    public void bind(Symbol symbol) {
        symbols.put(symbol.getName(), symbol);
    }

    public Symbol resolve(String name) {
        if (symbols.containsKey(name)) {
            return symbols.get(name);
        }

        if (enclosingScope != null) {
            return enclosingScope.resolve(name);
        }

        return null;
    }

    public Scope getEnclosingScope() {
        return enclosingScope;
    }

    public HashMap<String, Symbol> getSymbols() {
        return symbols;
    }
}
