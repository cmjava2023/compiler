package org.cmjava2023.symboltable;

public enum BuiltInType implements Type {
    BYTE,
    SHORT,
    INT,
    LONG,
    FLOAT,
    DOUBLE,
    CHAR,
    BOOLEAN,
    VOID,
    STRING;

    @Override
    public String getName() {
        return this.name();
    }
}
