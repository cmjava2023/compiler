package org.cmjava2023.symboltable;

public class BuiltInSymbol extends BaseSymbol {
    public BuiltInSymbol(BuiltInType builtInType, Scope scope) {
        super(builtInType.name(), builtInType, scope);
    }
}
