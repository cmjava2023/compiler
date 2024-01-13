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
    
    public static boolean builtInTypeSensitiveEquals(Object a, Object b) {
        if (a instanceof BuiltInType aAsBuiltIn && b instanceof BuiltInType bAsBuiltIn) {
           return aAsBuiltIn == bAsBuiltIn;
        } else if (a instanceof Type aASType && b instanceof Type bAsType) {
            return aASType.getName().equals(bAsType.getName());
        } else {
            throw new NotImplementedError("a:" + a.getClass().getName() + "\nb:" + b.getClass().getName());
        }
    }
}
