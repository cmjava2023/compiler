package org.cmjava2023.semanticanalysis;

import org.cmjava2023.ast.ASTNodes;
import org.cmjava2023.ast.ASTTraverser;
import org.cmjava2023.symboltable.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ASTVisitorFirst extends ASTTraverser<ASTNodes.Node> {
    public ArrayList<String> errors;

    public ASTVisitorFirst(ArrayList<String> errors) {
        this.errors = errors;
    }

    private ArrayList<ASTNodes.Statement> getModifiedStatements(ArrayList<ASTNodes.Statement> statements) {
        ArrayList<ASTNodes.Statement> statementList = new ArrayList<>();

        for (ASTNodes.Statement statement : statements) {
            if(statement!=null) {
                statementList.add((ASTNodes.Statement) statement.accept(this));
            }
        }

        return statementList;
    }

    private ArrayList<ASTNodes.ParameterNode> getModifiedParameters(ArrayList<ASTNodes.ParameterNode> parameters) {
        ArrayList<ASTNodes.ParameterNode> conditionList = new ArrayList<>();

        for (ASTNodes.ParameterNode parameter : parameters) {
            conditionList.add((ASTNodes.ParameterNode) parameter.accept(this));
        }

        return conditionList;
    }

    private ArrayList<ASTNodes.Expression> getModifiedExpressions(ArrayList<ASTNodes.Expression> expressions) {
        ArrayList<ASTNodes.Expression> expressionList = new ArrayList<>();

        for (ASTNodes.Expression expression : expressions) {
            expressionList.add((ASTNodes.Expression) expression.accept(this));
        }

        return expressionList;
    }

    private ArrayList<ASTNodes.IfNode> getModifiedIfNodes(ArrayList<ASTNodes.IfNode> ifNodes) {
        ArrayList<ASTNodes.IfNode> ifNodeList = new ArrayList<>();

        for (ASTNodes.IfNode ifNode : ifNodes) {
            ifNodeList.add((ASTNodes.IfNode) ifNode.accept(this));
        }

        return ifNodeList;
    }

    private ArrayList<ASTNodes.CaseNode> getModifiedCaseNodes(ArrayList<ASTNodes.CaseNode> caseNodes) {
        ArrayList<ASTNodes.CaseNode> caseNodeList = new ArrayList<>();

        for (ASTNodes.CaseNode caseNode : caseNodes) {

            caseNodeList.add((ASTNodes.CaseNode) caseNode.accept(this));
        }

        return caseNodeList;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.StartNode node) {
        return new ASTNodes.StartNode(getModifiedStatements(node.body()));
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.PackageNode node) {
        return node;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.ClassNode node) {
        return new ASTNodes.ClassNode(node.classSymbol(), getModifiedStatements(node.body()));
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.ContinueNode continueNodeNode) {
        return continueNodeNode;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.BreakNode breakNode) {
        return breakNode;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.EnumNode enumNode) {
        return enumNode;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.SwitchNode switchNode) {
        return new ASTNodes.SwitchNode((ASTNodes.Expression) switchNode.switchEx().accept(this), getModifiedCaseNodes(switchNode.caseNodes()), getModifiedStatements(switchNode.defaultStatements()));
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.CaseNode caseNode) {
        return new ASTNodes.CaseNode((ASTNodes.Expression) caseNode.caseEx().accept(this), getModifiedStatements(caseNode.body()));
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.FunctionNode node) {
        Function functionSymbol = node.functionSymbol();
        if (functionSymbol.getType() instanceof InvalidType invalidType) {
            checkForType(invalidType, "return type of Function", functionSymbol);
        }

        return new ASTNodes.FunctionNode(node.functionSymbol(), getModifiedParameters(node.parameters()), node.exception(), getModifiedStatements(node.body()));
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.ParameterNode node) {
        Parameter parameterSymbol = node.parameterSymbol();
        if (parameterSymbol.getType() instanceof InvalidType invalidType) {
            checkForVoidType("Parameter", parameterSymbol, invalidType);
            checkForType(invalidType, "Parameter", parameterSymbol);
        }
        return node;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.FunctionCallNode node) {
        String[] nestedIdentifierArray = node.function().getName().split("\\.");
        ArrayList<String> nestedIdentifier = new ArrayList<>(Arrays.asList(nestedIdentifierArray));
        Symbol functionSymbol = resolveNestedIdentifier(null, nestedIdentifier, node.function().getScope());

        if (functionSymbol instanceof Function function) {
            return new ASTNodes.FunctionCallNode(function, getModifiedExpressions(node.values()));
        }

        errors.add(String.format("Function %s is not declared", String.join(".", node.function().getName())));
        return node;
    }

    private static ArrayList<String> removeFirstElement(ArrayList<String> list) {
        if (list.size() > 1) {
            return new ArrayList<>(list.subList(1, list.size()));
        } else {
            return new ArrayList<>();
        }
    }

    public static Symbol resolveNestedIdentifier(Symbol currentSymbol, ArrayList<String> strings, Scope scope) {
        if (strings.isEmpty()) {
            return currentSymbol;
        }

        if (currentSymbol == null) {
            Symbol builtIn = scope.resolve(String.join(".", strings));

            if (builtIn != null) {
                return builtIn;
            }

            Symbol symbol = scope.resolve(strings.get(0));

            if (symbol == null) {
                return null;
            }
            return resolveNestedIdentifier(symbol, removeFirstElement(strings), scope);
        }

        if (currentSymbol instanceof Clazz newScope) {
            Symbol classSymbol = newScope.resolveMember(strings.get(0));
            return resolveNestedIdentifier(classSymbol, removeFirstElement(strings), newScope);
        }

        return null;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.IfNode node) {
        return new ASTNodes.IfNode((ASTNodes.Expression) node.expression().accept(this), getModifiedStatements(node.statements()));
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.ElseNode node) {
        return new ASTNodes.ElseNode(getModifiedStatements(node.statements()));
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.VariableNode node) {
        checkVariable(node.variableSymbol());
        return node;
    }

    private void checkVariable(Variable variable) {
        if (variable.getType() instanceof InvalidType invalidType) {
            checkForVoidType("Variable", variable, invalidType);
            checkForType(invalidType, "Variable", variable);
        }
        if (variable.getInitialExpression() != null) {
            variable.setInitialExpression((ASTNodes.Expression) variable.getInitialExpression().accept(this));
        }
    }

    private void checkForType(InvalidType invalidType, String errorMessagePart, Symbol symbol) {
        String type = invalidType.getName();
        String arrayIndicator = "[]";
        boolean isArray = type.contains(arrayIndicator);
        int dimensions = 0;

        if (isArray) {
            dimensions = countOccurrences(type, arrayIndicator);
            type = type.replace(arrayIndicator, "");
        }

        Symbol typeSymbol = symbol.getScope().resolve(type);

        if (typeSymbol != null) {
            if (isArray) {
                symbol.setType(new ArrayType(typeSymbol.getType(), dimensions));
            } else {
                symbol.setType(typeSymbol.getType());
            }
        } else {
            errors.add(String.format("Cannot find type %s for %s %s", invalidType.getName(), errorMessagePart, symbol.getName()));
        }
    }

    public static int countOccurrences(String input, String pattern) {
        int count = 0;
        int index = 0;

        while ((index = input.indexOf(pattern, index)) != -1) {
            count++;
            index += pattern.length();
        }

        return count;
    }

    private void checkForVoidType(String objectName, Symbol symbol, InvalidType invalidType) {
        if (Objects.equals(invalidType.getName(), "void")) {
            errors.add(String.format("%s %s cannot have the type void", objectName, symbol.getName()));
        }
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.VariableAssignmentNode node) {
        String[] variableNameArray = node.variable().getName().split("\\.");
        ArrayList<String> variableName = new ArrayList<>(Arrays.asList(variableNameArray));
        Symbol variableSymbol = ASTVisitorFirst.resolveNestedIdentifier(null, variableName, node.variable().getScope());

        if (variableSymbol instanceof Variable variable) {
            checkVariable(node.variable());
            return new ASTNodes.VariableAssignmentNode(variable, (ASTNodes.Expression) node.expression().accept(this));
        }

        if (variableSymbol instanceof Parameter parameter) {
            return new ASTNodes.ParameterAssignmentNode(parameter, (ASTNodes.Expression) node.expression().accept(this));
        }

        errors.add(String.format("Variable %s is not declared", node.variable().getName()));
        return new ASTNodes.VariableAssignmentNode(node.variable(), (ASTNodes.Expression) node.expression().accept(this));
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.ValueNode<?> valueNode) {
        return valueNode;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.NestedIdentifierNode node) {
        return node;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.ComparisonNode node) {
        return new ASTNodes.ComparisonNode((ASTNodes.Expression) node.leftExpression().accept(this), node.comparisonOperator(), (ASTNodes.Expression) node.rightExpression().accept(this));
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.VariableCallNode node) {
        String symbolName = node.symbol().getName();
        Symbol identifierSymbol = node.symbol().getScope().resolve(symbolName);

        if (identifierSymbol instanceof Variable variable) {
            return new ASTNodes.VariableCallNode(variable);
        }

        if (identifierSymbol instanceof Parameter parameter) {
            return new ASTNodes.ParameterCallNode(parameter);
        }

        errors.add(String.format("Variable %s is not declared", symbolName));
        return node;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.ReturnNode node) {
        if (node.value() == null) {
            return node;
        } else {
            return new ASTNodes.ReturnNode((ASTNodes.Expression) node.value().accept(this));
        }
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.TypeNode node) {
        return node;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.ArrayTypeNode node) {
        return node;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.ParameterAssignmentNode node) {
        return node;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.InfixNode node) {
        return new ASTNodes.InfixNode((ASTNodes.Expression) node.leftExpression().accept(this), node.operator(), (ASTNodes.Expression) node.rightExpression().accept(this));
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.UnaryPrefixNode node) {
        return new ASTNodes.UnaryPrefixNode(node.operator(), (ASTNodes.Expression) node.Expression().accept(this));
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.UnarySuffixNode node) {
        return new ASTNodes.UnarySuffixNode(node.operator(), (ASTNodes.Expression) node.Expression().accept(this));
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.ParenthesesNode node) {
        return new ASTNodes.ParenthesesNode((ASTNodes.Expression) node.Expression().accept(this));
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.CastNode node) {
        if (node.type() instanceof InvalidType invalidType) {
            if (Objects.equals(invalidType.getName(), "void")) {
                errors.add("Casts cannot be of type void");
            }

            String type = invalidType.getName();
            String arrayIndicator = "[]";
            boolean isArray = type.contains(arrayIndicator);

            if (isArray) {
                type = type.replace(arrayIndicator, "");
            }

            Symbol typeSymbol = node.scope().resolve(type);

            if (typeSymbol != null) {
                if (isArray) {
                    return new ASTNodes.CastNode(new ArrayType(typeSymbol.getType()), (ASTNodes.Expression) node.expression().accept(this), node.scope());
                } else {
                    return new ASTNodes.CastNode(typeSymbol.getType(), (ASTNodes.Expression) node.expression().accept(this), node.scope());
                }
            } else {
                errors.add(String.format("Cannot find type %s for cast expression", invalidType.getName()));
            }
        }
        return node;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.ArrayInstantiationWithValuesNode node) {
        return new ASTNodes.ArrayInstantiationWithValuesNode(getModifiedExpressions(node.expressions()));
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.ArrayInstantiationNode node) {
        return node;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.ArrayAccessNode node) {
        return node;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.ObjectInstantiationNode node) {
        return node;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.ForLoopNode node) {
        return new ASTNodes.ForLoopNode((ASTNodes.VariableUsage) node.loopVariable().accept(this), (ASTNodes.Expression) node.termination().accept(this), (ASTNodes.Expression) node.increment().accept(this), getModifiedStatements(node.body()));
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.ForEachLoopNode forEachLoopNode) {
        return null;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.WhileLoopNode node) {
        return new ASTNodes.WhileLoopNode((ASTNodes.Expression) node.expression().accept(this), getModifiedStatements(node.body()));
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.DoWhileLoopNode node) {
        return new ASTNodes.DoWhileLoopNode((ASTNodes.Expression) node.expression().accept(this), getModifiedStatements(node.body()));
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.OperatorNode node) {
        return node;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.VariableFieldCallNode variableFieldCallNode) {
        return null;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.VariableFunctionCallNode variableFunctionCallNode) {
        return null;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.Node ifBlockNode) {
        return null;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.IfBlockNode node) {
        return new ASTNodes.IfBlockNode(getModifiedIfNodes(node.ifNodes()), node.elseNode() != null ? (ASTNodes.ElseNode) node.elseNode().accept(this) : null);
    }
}
