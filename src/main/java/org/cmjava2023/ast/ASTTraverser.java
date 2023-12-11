package org.cmjava2023.ast;

public interface ASTTraverser<T> {
    T visit(ASTNodes.StartNode node);

    T visit(ASTNodes.PackageNode node);

    T visit(ASTNodes.ClassNode node);

    T visit(ASTNodes.FunctionNode node);

    T visit(ASTNodes.ParameterNode node);

    T visit(ASTNodes.RawFunctionCallNode node);

    T visit(ASTNodes.IfNode node);

    T visit(ASTNodes.ElseNode node);

    T visit(ASTNodes.BlockScopeNode node);

    T visit(ASTNodes.VariableNode node);

    T visit(ASTNodes.VariableAssigmentNode node);

    T visit(ASTNodes.ValueNode node);

    T visit(ASTNodes.NestedIdentifierNode node);

    T visit(ASTNodes.ComparisonNode node);

    T visit(ASTNodes.ExpressionNode node);

    T visit(ASTNodes.IdentifierNode node);

    T visit(ASTNodes.ReturnNode node);

    T visit(ASTNodes.TypeNode node);

    T visit(ASTNodes.ArrayTypeNode node);

    T visit(T functionCallNode);
}
