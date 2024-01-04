package org.cmjava2023.symboltable;

import org.cmjava2023.ast.ASTNodes;

import java.util.HashMap;

public class InvalidClazz extends Clazz {
    public InvalidClazz(Scope enclosingScope, HashMap<String, Symbol> symbols, String name, Type type, Clazz parentClazz, ASTNodes.AccessModifier accessModifier, ASTNodes.Modifier instanceModifier) {
        super(enclosingScope, symbols, name, type, parentClazz, accessModifier, instanceModifier);
    }
}
