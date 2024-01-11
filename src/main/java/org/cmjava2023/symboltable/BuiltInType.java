package org.cmjava2023.symboltable;

import kotlin.NotImplementedError;

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
    
    public static BuiltInType typeOf(Object value) {
        if (value instanceof Byte) {
            return BuiltInType.BYTE;
        } else if (value instanceof Short) {
            return BuiltInType.SHORT;
        } else if (value instanceof Integer) {
            return BuiltInType.INT;
        } else if (value instanceof Long) {
            return BuiltInType.LONG;
        } else if (value instanceof Float) {
            return BuiltInType.FLOAT;
        } else if (value instanceof Double) {
            return BuiltInType.DOUBLE;
        } else if (value instanceof Character) {
            return BuiltInType.CHAR;
        } else if (value instanceof Boolean) {
            return BuiltInType.BOOLEAN;
        } else if (value instanceof String) {
            return BuiltInType.STRING;
        } else {
            throw new NotImplementedError(value.getClass().getSimpleName());
        }
    }
}
