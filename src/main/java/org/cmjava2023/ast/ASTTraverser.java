package org.cmjava2023.ast;

import kotlin.NotImplementedError;

public abstract class ASTTraverser<T> {
    public T dispatch(ASTNodes.Node node) {
        if (node instanceof ASTNodes.PackageNode) {
            return visit((ASTNodes.PackageNode) node);
        } else if (node instanceof ASTNodes.ClassNode) {
            return visit((ASTNodes.ClassNode) node);
        } else if (node instanceof ASTNodes.FunctionNode) {
            return visit((ASTNodes.FunctionNode) node);
        } else if (node instanceof ASTNodes.FunctionCallNode) {
            return visit((ASTNodes.FunctionCallNode) node);
        } else if (node instanceof ASTNodes.IfNode) {
            return visit((ASTNodes.IfNode) node);
        } else if (node instanceof ASTNodes.ElseNode) {
            return visit((ASTNodes.ElseNode) node);
        } else if (node instanceof ASTNodes.VariableNode) {
            return visit((ASTNodes.VariableNode) node);
        } else if (node instanceof ASTNodes.ReturnNode) {
            return visit((ASTNodes.ReturnNode) node);
        } else if (node instanceof ASTNodes.VariableAssignmentNode) {
            return visit((ASTNodes.VariableAssignmentNode) node);
        } else if (node instanceof ASTNodes.UnarySuffixNode) {
            return visit((ASTNodes.UnarySuffixNode) node);
        } else if (node instanceof ASTNodes.IfBlockNode) {
            return visit((ASTNodes.IfBlockNode) node);
        } else if (node instanceof ASTNodes.WhileLoopNode) {
            return visit((ASTNodes.WhileLoopNode) node);
        } else if (node instanceof ASTNodes.DoWhileLoopNode) {
            return visit((ASTNodes.DoWhileLoopNode) node);
        } else if (node instanceof ASTNodes.ContinueNode) {
            return visit((ASTNodes.ContinueNode) node);
        } else if (node instanceof ASTNodes.ValueNode<?>) {
            return visit((ASTNodes.ValueNode<?>) node);
        } else if (node instanceof ASTNodes.NestedIdentifierNode) {
            return visit((ASTNodes.NestedIdentifierNode) node);
        } else if (node instanceof ASTNodes.ComparisonNode) {
            return visit((ASTNodes.ComparisonNode) node);
        } else if (node instanceof ASTNodes.VariableCallNode) {
            return visit((ASTNodes.VariableCallNode) node);
        } else if (node instanceof ASTNodes.CastNode) {
            return visit((ASTNodes.CastNode) node);
        } else if (node instanceof ASTNodes.InfixNode) {
            return visit((ASTNodes.InfixNode) node);
        } else if (node instanceof ASTNodes.UnaryPrefixNode) {
            return visit((ASTNodes.UnaryPrefixNode) node);
        } else if (node instanceof ASTNodes.ArrayInstantiationWithValuesNode) {
            return visit((ASTNodes.ArrayInstantiationWithValuesNode) node);
        } else if (node instanceof ASTNodes.ArrayInstantiationNode) {
            return visit((ASTNodes.ArrayInstantiationNode) node);
        } else if (node instanceof ASTNodes.ArrayAccessNode) {
            return visit((ASTNodes.ArrayAccessNode) node);
        } else {
            throw new NotImplementedError("type " + node.getClass().getName());
        }
    }
    
    public abstract T defaultValue(ASTNodes.Node node);
    public T visit(ASTNodes.StartNode startNode) { return defaultValue(startNode); }
    public T visit(ASTNodes.PackageNode packageNode) { return defaultValue(packageNode); }
    public T visit(ASTNodes.ClassNode classNode) { return defaultValue(classNode); }
    public T visit(ASTNodes.ContinueNode continueNodeNode) { return defaultValue(continueNodeNode); }
    public T visit(ASTNodes.BreakNode breakNode) { return defaultValue(breakNode); }
    public T visit(ASTNodes.EnumNode enumNode) { return defaultValue(enumNode); }
    public T visit(ASTNodes.SwitchNode switchNode) { return defaultValue(switchNode); }
    public T visit(ASTNodes.CaseNode caseNode) { return defaultValue(caseNode); }
    public T visit(ASTNodes.FunctionNode functionNode) { return defaultValue(functionNode); }
    public T visit(ASTNodes.ParameterNode node) { return defaultValue(node); }
    
    public T visit(ASTNodes.ParameterCallNode parameterCallNode) { return defaultValue(parameterCallNode); }
    public T visit(ASTNodes.FunctionCallNode rawFunctionCallNode) { return defaultValue(rawFunctionCallNode); }
    public T visit(ASTNodes.IfNode ifNode) { return defaultValue(ifNode); }
    public T visit(ASTNodes.ElseNode elseNode) { return defaultValue(elseNode); }
    public T visit(ASTNodes.VariableNode variableNode) { return defaultValue(variableNode); }
    public T visit(ASTNodes.VariableAssignmentNode variableAssignmentNode) { return defaultValue(variableAssignmentNode); }
    public T visit(ASTNodes.ValueNode<?> valueNode) { return defaultValue(valueNode); }
    public T visit(ASTNodes.NestedIdentifierNode nestedIdentifierNode) { return defaultValue(nestedIdentifierNode); }
    public T visit(ASTNodes.ComparisonNode comparisonNode) { return defaultValue(comparisonNode); }
    public T visit(ASTNodes.VariableCallNode variableCallNode) { return defaultValue(variableCallNode); }
    public T visit(ASTNodes.ReturnNode returnNode) { return defaultValue(returnNode); }
    public T visit(ASTNodes.TypeNode typeNode) { return defaultValue(typeNode); }
    public T visit(ASTNodes.ArrayTypeNode arrayTypeNode) { return defaultValue(arrayTypeNode); }
    public T visit(ASTNodes.ParameterAssignmentNode parameterAssigmentNode) { return defaultValue(parameterAssigmentNode); }
    public T visit(ASTNodes.InfixNode infixNode) { return defaultValue(infixNode); }
    public T visit(ASTNodes.UnaryPrefixNode unaryPrefixNode) { return defaultValue(unaryPrefixNode); }
    public T visit(ASTNodes.UnarySuffixNode unarySuffixNode) { return defaultValue(unarySuffixNode); }
    public T visit(ASTNodes.ParenthesesNode parenthesesNode) { return defaultValue(parenthesesNode); }
    public T visit(ASTNodes.CastNode castNode) { return defaultValue(castNode); }
    public T visit(ASTNodes.ArrayInstantiationWithValuesNode arrayInstantiationWithValuesNode) { return defaultValue(arrayInstantiationWithValuesNode); }
    public T visit(ASTNodes.ArrayInstantiationNode arrayInstantiationNode) { return defaultValue(arrayInstantiationNode); }
    public T visit(ASTNodes.ArrayAccessNode arrayAccessNode) { return defaultValue(arrayAccessNode); }
    public T visit(ASTNodes.ObjectInstantiationNode objectInstantiationNode) { return defaultValue(objectInstantiationNode); }
    public T visit(ASTNodes.ForLoopNode forLoopNode) { return defaultValue(forLoopNode); }
    public T visit(ASTNodes.ForEachLoopNode forEachLoopNode) { return defaultValue(forEachLoopNode); }
    public T visit(ASTNodes.WhileLoopNode whileLoopNode) { return defaultValue(whileLoopNode); }
    public T visit(ASTNodes.DoWhileLoopNode doWhileLoopNode) { return defaultValue(doWhileLoopNode); }
    public T visit(ASTNodes.VariableFieldCallNode variableFieldCallNode) { return defaultValue(variableFieldCallNode); }
    public T visit(ASTNodes.VariableFunctionCallNode variableFunctionCallNode) { return defaultValue(variableFunctionCallNode); }
    public T visit(ASTNodes.IfBlockNode ifBlockNode) { return defaultValue(ifBlockNode); }
}
