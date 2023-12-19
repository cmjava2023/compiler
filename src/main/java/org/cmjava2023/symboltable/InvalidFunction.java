package org.cmjava2023.symboltable;

import org.cmjava2023.ast.ASTNodes;

import java.util.HashMap;

public class InvalidFunction extends Function {
    public InvalidFunction(Scope enclosingScope, HashMap<String, Symbol> symbols, String name, Type type, ASTNodes.AccessModifier accessModifier, ASTNodes.Modifier instanceModifier) {
        super(enclosingScope, symbols, name, type, accessModifier, instanceModifier);
    }
}
