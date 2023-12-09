package org.cmjava2023.ast;


import kotlin.NotImplementedError;

public abstract class ASTTraverser<T> {
    public T dispatch(ASTNodes.Statement statementNode) {
        if (statementNode instanceof ASTNodes.PackageNode) { return visit((ASTNodes.PackageNode)statementNode); }
        else if (statementNode instanceof ASTNodes.ClassNode) { return visit((ASTNodes.ClassNode)statementNode); }
        else if (statementNode instanceof ASTNodes.FunctionNode) { return visit((ASTNodes.FunctionNode)statementNode); }
        else if (statementNode instanceof ASTNodes.FunctionCallNode) { return visit((ASTNodes.FunctionCallNode)statementNode); }
        else if (statementNode instanceof ASTNodes.IfNode) { return visit((ASTNodes.IfNode)statementNode); }
        else if (statementNode instanceof ASTNodes.ElseNode) { return visit((ASTNodes.ElseNode)statementNode); }
        else if (statementNode instanceof ASTNodes.BlockScopeNode) { return visit((ASTNodes.BlockScopeNode)statementNode); }
        else if (statementNode instanceof ASTNodes.VariableNode) { return visit((ASTNodes.VariableNode)statementNode); }
        else if (statementNode instanceof ASTNodes.ExpressionNode) { return visit((ASTNodes.ExpressionNode)statementNode); }
        else if (statementNode instanceof ASTNodes.ReturnNode) { return visit((ASTNodes.ReturnNode)statementNode); }
        else { throw new NotImplementedError(); }
    }

    public T dispatch(ASTNodes.Expression expressionNode) {
        if (expressionNode instanceof ASTNodes.NestedIdentifierNode) { return visit((ASTNodes.NestedIdentifierNode)expressionNode); }
        else if (expressionNode instanceof ASTNodes.ComparisonNode) { return visit((ASTNodes.ComparisonNode)expressionNode); }
        else if (expressionNode instanceof ASTNodes.ExpressionNode) { return visit((ASTNodes.ExpressionNode)expressionNode); }
        else if (expressionNode instanceof ASTNodes.IdentifierNode) { return visit((ASTNodes.IdentifierNode)expressionNode); }
        else { throw new NotImplementedError(); }
    }

    public abstract T visit(ASTNodes.StartNode startNode);

    public abstract T visit(ASTNodes.PackageNode packageNode);

    public abstract T visit(ASTNodes.ClassNode classNode);

    public abstract T visit(ASTNodes.FunctionNode functionNode);

    public abstract T visit(ASTNodes.ParameterNode node);

    public abstract T visit(ASTNodes.RawFunctionCallNode rawFunctionCallNode);

    public abstract T visit(ASTNodes.FunctionCallNode rawFunctionCallNode);

    public abstract T visit(ASTNodes.IfNode ifNode);

    public abstract T visit(ASTNodes.ElseNode elseNode);

    public abstract T visit(ASTNodes.BlockScopeNode blockScopeNode);

    public abstract T visit(ASTNodes.VariableNode variableNode);

    public abstract T visit(ASTNodes.VariableAssigmentNode variableAssigmentNode);

    public abstract T visit(ASTNodes.NestedIdentifierNode nestedIdentifierNode);

    public abstract T visit(ASTNodes.ComparisonNode comparisonNode);

    public abstract T visit(ASTNodes.ExpressionNode expressionNode);

    public abstract T visit(ASTNodes.IdentifierNode identifierNode);

    public abstract T visit(ASTNodes.ReturnNode returnNode);

    public abstract T visit(ASTNodes.TypeNode typeNode);

    public abstract T visit(ASTNodes.ArrayTypeNode arrayTypeNode);

    public abstract T visit(T functionCallNode);
}
