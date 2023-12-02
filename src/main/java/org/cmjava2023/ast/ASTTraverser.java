package org.cmjava2023.ast;

public interface ASTTraverser {
    void visit(ASTNodes.StartNode node);

    void visit(ASTNodes.PackageNode node);

    void visit(ASTNodes.ClassNode node);

    void visit(ASTNodes.FunctionNode node);

    void visit(ASTNodes.ParameterNode node);

    void visit(ASTNodes.FunctionCallNode node);

    void visit(ASTNodes.IfNode node);

    void visit(ASTNodes.ElseNode node);

    void visit(ASTNodes.BlockScopeNode node);

    void visit(ASTNodes.VariableNode node);

    void visit(ASTNodes.VariableAssigmentNode node);

    void visit(ASTNodes.ValueNode node);

    void visit(ASTNodes.NestedIdentifierNode node);

    void visit(ASTNodes.ComparisonNode node);

    void visit(ASTNodes.ExpressionNode node);

    void visit(ASTNodes.IdentifierNode node);

    void visit(ASTNodes.ReturnNode node);

    void visit(ASTNodes.TypeNode node);

    void visit(ASTNodes.ArrayTypeNode node);
}
