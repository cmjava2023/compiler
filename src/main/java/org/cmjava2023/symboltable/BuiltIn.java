package org.cmjava2023.symboltable;

public class BuiltIn extends BaseSymbol implements Type {
    public BuiltIn(String name, Type type, Scope scope) {
        super(name, type, scope);
    }
}
