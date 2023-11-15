package org.cmjava2023.symboltable;

public interface Scope {
    void bind(Symbol symbol);

    Symbol resolve(String name);
}
