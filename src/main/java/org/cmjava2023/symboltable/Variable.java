package org.cmjava2023.symboltable;

import org.cmjava2023.ASTNodes;

public class Variable extends BaseSymbol {
    private final ASTNodes.Expression initialExpression;

    public Variable(String name, Type type, Scope scope, ASTNodes.Expression initialExpression) {
        super(name, type, scope);
        this.initialExpression = initialExpression;
    }

    public ASTNodes.Expression getInitialExpression() {
        return initialExpression;
    }
}
