package org.cmjava2023.symboltable;

import java.util.HashMap;

public class LocalScope extends BaseScope {
    public LocalScope(Scope enclosingScope, HashMap<String, Symbol> symbols) {
        super(enclosingScope, symbols);
    }
}
