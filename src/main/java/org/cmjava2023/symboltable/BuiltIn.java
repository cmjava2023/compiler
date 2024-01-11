package org.cmjava2023.symboltable;

public class BuiltIn extends BaseSymbol implements Type {
    public BuiltIn(BuiltInType builtInType, Scope scope) {
        super(builtInType.name(), builtInType, scope);
    }
}
