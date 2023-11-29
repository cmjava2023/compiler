package org.cmjava2023.symboltable;

import org.cmjava2023.ASTNodes;

import java.util.HashMap;

public class Function extends SymbolWithScope {

    private final ASTNodes.AccessModifier accessModifier;

    private final ASTNodes.Modifier instanceModifier;

    public Function(Scope enclosingScope, HashMap<String, Symbol> symbols, String name, Type type, ASTNodes.AccessModifier accessModifier, ASTNodes.Modifier instanceModifier) {
        super(enclosingScope, symbols, name, type);
        this.accessModifier = accessModifier;
        this.instanceModifier = instanceModifier;
    }

    public ASTNodes.AccessModifier getAccessModifier() {
        return accessModifier;
    }

    public ASTNodes.Modifier getInstanceModifier() {
        return instanceModifier;
    }
}
