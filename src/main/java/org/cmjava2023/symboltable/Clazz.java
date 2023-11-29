package org.cmjava2023.symboltable;

import org.cmjava2023.ASTNodes;

import java.util.HashMap;

public class Clazz extends SymbolWithScope implements Type {

    private final Clazz parentClazz;

    private final ASTNodes.AccessModifier accessModifier;

    private final ASTNodes.Modifier instanceModifier;

    public Clazz(Scope enclosingScope, HashMap<String, Symbol> symbols, String name, Type type, Clazz parentClazz, ASTNodes.AccessModifier accessModifier, ASTNodes.Modifier instanceModifier) {
        super(enclosingScope, symbols, name, type);
        this.parentClazz = parentClazz;
        this.accessModifier = accessModifier;
        this.instanceModifier = instanceModifier;
    }

    @Override
    public Symbol resolve(String name) {
        Symbol memberSymbol = resolveMember(name);
        if (memberSymbol != null) {
            return memberSymbol;
        }

        if (enclosingScope != null) {
            return enclosingScope.resolve(name);
        }

        return null;
    }

    public Symbol resolveMember(String name) {
        if (symbols.containsKey(name)) {
            return symbols.get(name);
        }

        if (parentClazz != null) {
            return parentClazz.resolveMember(name);
        }

        return null;
    }

    public Clazz getParentClazz() {
        return parentClazz;
    }

    public ASTNodes.AccessModifier getAccessModifier() {
        return accessModifier;
    }

    public ASTNodes.Modifier getInstanceModifier() {
        return instanceModifier;
    }
}
