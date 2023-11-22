package org.cmjava2023.symboltable;

public abstract  class BaseSymbol implements Symbol {
    protected final String name;
    protected Type type;
    protected final Scope scope;

    public BaseSymbol(String name, Type type, Scope scope) {
        this.name = name;
        this.type = type;
        this.scope = scope;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public Scope getScope() {
        return scope;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }
}
