package org.cmjava2023.ast;


import kotlin.NotImplementedError;

public abstract class ASTTraverser<T> {
    public T dispatch(ASTNodes.Statement statementNode) {
        if (statementNode instanceof ASTNodes.PackageNode) {
            return visit((ASTNodes.PackageNode) statementNode);
        } else if (statementNode instanceof ASTNodes.ClassNode) {
            return visit((ASTNodes.ClassNode) statementNode);
        } else if (statementNode instanceof ASTNodes.FunctionNode) {
            return visit((ASTNodes.FunctionNode) statementNode);
        } else if (statementNode instanceof ASTNodes.FunctionCallNode) {
            return visit((ASTNodes.FunctionCallNode) statementNode);
        } else if (statementNode instanceof ASTNodes.IfNode) {
            return visit((ASTNodes.IfNode) statementNode);
        } else if (statementNode instanceof ASTNodes.ElseNode) {
            return visit((ASTNodes.ElseNode) statementNode);
        } else if (statementNode instanceof ASTNodes.BlockScopeNode) {
            return visit((ASTNodes.BlockScopeNode) statementNode);
        } else if (statementNode instanceof ASTNodes.VariableNode) {
            return visit((ASTNodes.VariableNode) statementNode);
        } else if (statementNode instanceof ASTNodes.ReturnNode) {
            return visit((ASTNodes.ReturnNode) statementNode);
        } else if (statementNode instanceof ASTNodes.VariableAssignmentNode) {
            return visit((ASTNodes.VariableAssignmentNode) statementNode);
        } else if (statementNode instanceof ASTNodes.UnarySuffixNode) {
            return visit((ASTNodes.UnarySuffixNode) statementNode);
        } else {
            throw new NotImplementedError("type " + statementNode.getClass().getName());
        }
    }

    public T dispatch(ASTNodes.Expression expressionNode) {
        if (expressionNode instanceof ASTNodes.ValueNode<?>) {
            return visit((ASTNodes.ValueNode<?>) expressionNode);
        }
        if (expressionNode instanceof ASTNodes.NestedIdentifierNode) {
            return visit((ASTNodes.NestedIdentifierNode) expressionNode);
        } else if (expressionNode instanceof ASTNodes.ComparisonNode) {
            return visit((ASTNodes.ComparisonNode) expressionNode);
        } else if (expressionNode instanceof ASTNodes.VariableCallNode) {
            return visit((ASTNodes.VariableCallNode) expressionNode);
        } else if (expressionNode instanceof ASTNodes.CastNode) {
            return visit((ASTNodes.CastNode) expressionNode);
        } else if (expressionNode instanceof ASTNodes.InfixNode) {
                return visit((ASTNodes.InfixNode) expressionNode);
        } else if (expressionNode instanceof ASTNodes.UnaryPrefixNode) {
                return visit((ASTNodes.UnaryPrefixNode) expressionNode);
        } else {
            throw new NotImplementedError("type " + expressionNode.getClass().getName());
        }
    }

    public abstract T visit(ASTNodes.StartNode startNode);

    public abstract T visit(ASTNodes.PackageNode packageNode);

    public abstract T visit(ASTNodes.ClassNode classNode);

    public abstract T visit(ASTNodes.ContinueNode continueNodeNode);

    public abstract T visit(ASTNodes.BreakNode breakNode);

    public abstract T visit(ASTNodes.EnumNode enumNode);

    public abstract T visit(ASTNodes.SwitchNode switchNode);

    public abstract T visit(ASTNodes.CaseNode caseNode);

    public abstract T visit(ASTNodes.FunctionNode functionNode);

    public abstract T visit(ASTNodes.ParameterNode node);

    public abstract T visit(ASTNodes.FunctionCallNode rawFunctionCallNode);

    public abstract T visit(ASTNodes.IfNode ifNode);

    public abstract T visit(ASTNodes.ElseNode elseNode);

    public abstract T visit(ASTNodes.BlockScopeNode blockScopeNode);

    public abstract T visit(ASTNodes.VariableNode variableNode);

    public abstract T visit(ASTNodes.VariableAssignmentNode variableAssignmentNode);

    public abstract T visit(ASTNodes.ValueNode<?> valueNode);

    public abstract T visit(ASTNodes.NestedIdentifierNode nestedIdentifierNode);

    public abstract T visit(ASTNodes.ComparisonNode comparisonNode);

    public abstract T visit(ASTNodes.VariableCallNode variableCallNode);

    public abstract T visit(ASTNodes.ReturnNode returnNode);

    public abstract T visit(ASTNodes.TypeNode typeNode);

    public abstract T visit(ASTNodes.ArrayTypeNode arrayTypeNode);

    public abstract T visit(ASTNodes.ParameterAssignmentNode parameterAssigmentNode);

    public abstract T visit(ASTNodes.InfixNode infixNode);

    public abstract T visit(ASTNodes.UnaryPrefixNode unaryPrefixNode);

    public abstract T visit(ASTNodes.UnarySuffixNode unarySuffixNode);

    public abstract T visit(ASTNodes.ParenthesesNode parenthesesNode);

    public abstract T visit(ASTNodes.CastNode castNode);

    public abstract T visit(ASTNodes.ArrayInstantiationWithValuesNode arrayInstantiationWithValuesNode);

    public abstract T visit(ASTNodes.ArrayInstantiationNode arrayInstantiationNode);

    public abstract T visit(ASTNodes.ArrayAccessNode arrayAccessNode);

    public abstract T visit(ASTNodes.ObjectInstantiationNode objectInstantiationNode);

    public abstract T visit(ASTNodes.ForLoopNode forLoopNode);

    public abstract T visit(ASTNodes.WhileLoopNode whileLoopNode);

    public abstract T visit(ASTNodes.DoWhileLoopNode doWhileLoopNode);

    public abstract T visit(ASTNodes.OperatorNode operatorNode);

    public abstract T visit(ASTNodes.VariableFieldCallNode variableFieldCallNode);

    public abstract T visit(ASTNodes.VariableFunctionCallNode variableFunctionCallNode);
}
