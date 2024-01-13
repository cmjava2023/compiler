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
        String nameInCode = this.name().toLowerCase();
        if(this.equals(STRING)) {
            nameInCode = (nameInCode.charAt(0) + "").toUpperCase() + nameInCode.substring(1);
        }
        return nameInCode;
    }

    public boolean equals(InvalidType invalidType) {
        return invalidType.getName().equals(getName());
    }
}
