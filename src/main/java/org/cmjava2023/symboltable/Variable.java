package org.cmjava2023.symboltable;

import org.cmjava2023.ast.ASTNodes;

public class Variable extends BaseSymbol {
    private ASTNodes.Expression initialExpression;

    public Variable(String name, Type type, Scope scope) {
        super(name, type, scope);
    }

    public void setInitialExpression(ASTNodes.Expression initialExpression) {
        this.initialExpression = initialExpression;
    }

    public ASTNodes.Expression getInitialExpression() {
        return initialExpression;
    }
}
