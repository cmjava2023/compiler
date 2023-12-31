package org.cmjava2023.symboltable;

import java.util.HashMap;

public class GlobalScope extends BaseScope {
    public GlobalScope(Scope enclosingScope, HashMap<String, Symbol> symbols) {
        super(enclosingScope, symbols);
    }

    @Override
    public String getName() {
        return "GlobalScope";
    }
}
