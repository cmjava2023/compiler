package org.cmjava2023.symboltable;

import java.util.HashMap;

public interface Scope {
    void bind(Symbol symbol);
    void addChildScope(Scope scope);
    Symbol resolve(String name);
    Scope getEnclosingScope();
    HashMap<String, Symbol> getSymbols();
    void setSymbols(HashMap<String, Symbol> symbols);
}
