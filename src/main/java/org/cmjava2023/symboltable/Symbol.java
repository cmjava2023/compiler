package org.cmjava2023.symboltable;


public interface Symbol {
    String getName();
    Scope getScope();
    Type getType();

    void setType(Type type);
}
