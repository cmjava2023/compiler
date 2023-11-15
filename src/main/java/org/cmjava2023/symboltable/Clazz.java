package org.cmjava2023.symboltable;

import java.util.HashMap;

public class Clazz extends SymbolWithScope implements Type {
    private final Clazz parentClazz;

    public Clazz(Scope enclosingScope, HashMap<String, Symbol> symbols, String name, Type type, Clazz parentClazz) {
        super(enclosingScope, symbols, name, type);
        this.parentClazz = parentClazz;
    }

    public Symbol resolveMember(String name) {
        if (symbols.containsKey(name)) {
            return symbols.get(name);
        }

        if (parentClazz != null) {
            return parentClazz.resolveMember(name);
        }

        return null;
    }
}
