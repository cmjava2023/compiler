package org.cmjava2023.semanticanalysis;

import org.cmjava2023.ast.ASTNodes;
import org.cmjava2023.ast.ASTTraverser;

public class SemanticAnalysisTraverser implements ASTTraverser {
    @Override
    public void visit(ASTNodes.StartNode node) {
        System.out.println(node);
        for (ASTNodes.Statement statement : node.body()) {
            statement.accept(this);
        }
    }

    @Override
    public void visit(ASTNodes.PackageNode node) {
        System.out.println(node);
    }

    @Override
    public void visit(ASTNodes.ClassNode node) {
        System.out.println(node);
        for (ASTNodes.Statement statement : node.body()) {
            statement.accept(this);
        }
    }

    @Override
    public void visit(ASTNodes.FunctionNode node) {
        System.out.println(node);
        for (ASTNodes.ParameterNode statement : node.parameters()) {
            statement.accept(this);
        }
        for (ASTNodes.Statement statement : node.body()) {
            statement.accept(this);
        }
    }

    @Override
    public void visit(ASTNodes.ParameterNode node) {
        System.out.println(node);
    }

    @Override
    public void visit(ASTNodes.FunctionCallNode node) {
        System.out.println(node);
    }

    @Override
    public void visit(ASTNodes.IfNode node) {
        System.out.println(node);
        for (ASTNodes.Statement statement : node.statements()) {
            statement.accept(this);
        }
    }

    @Override
    public void visit(ASTNodes.ElseNode node) {
        System.out.println(node);
        for (ASTNodes.Statement statement : node.statements()) {
            statement.accept(this);
        }
    }

    @Override
    public void visit(ASTNodes.BlockScopeNode node) {
        System.out.println(node);
    }

    @Override
    public void visit(ASTNodes.VariableNode node) {
        System.out.println(node);
    }

    @Override
    public void visit(ASTNodes.VariableAssigmentNode node) {
        System.out.println(node);
    }

    @Override
    public void visit(ASTNodes.ValueNode node) {
        System.out.println(node);
    }

    @Override
    public void visit(ASTNodes.NestedIdentifierNode node) {
        System.out.println(node);
    }

    @Override
    public void visit(ASTNodes.ComparisonNode node) {
        System.out.println(node);
    }

    @Override
    public void visit(ASTNodes.ExpressionNode node) {
        System.out.println(node);
    }

    @Override
    public void visit(ASTNodes.IdentifierNode node) {
        System.out.println(node);
    }

    @Override
    public void visit(ASTNodes.ReturnNode node) {
        System.out.println(node);
    }

    @Override
    public void visit(ASTNodes.TypeNode node) {
        System.out.println(node);
    }

    @Override
    public void visit(ASTNodes.ArrayTypeNode node) {
        System.out.println(node);
    }
}
