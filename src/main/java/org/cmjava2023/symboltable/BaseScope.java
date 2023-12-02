package org.cmjava2023.symboltable;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class BaseScope implements Scope {
    protected final Scope enclosingScope;
    protected HashMap<String, Symbol> symbols;

    protected ArrayList<Scope> childScopes = new ArrayList<>();

    public BaseScope(Scope enclosingScope, HashMap<String, Symbol> symbols) {
        this.enclosingScope = enclosingScope;
        this.symbols = symbols;
    }

    public void addChildScope(Scope scope) {
        if (!(scope instanceof SymbolWithScope)) {
            childScopes.add(scope);
        }
    }

    public ArrayList<Scope> getChildScopes() {
        return childScopes;
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

    public void setSymbols(HashMap<String, Symbol> symbols) {
        this.symbols = symbols;
    }
}
