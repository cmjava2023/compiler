package org.cmjava2023.semanticanalysis;

import org.cmjava2023.ast.ASTNodes;
import org.cmjava2023.ast.ASTTraverser;
import org.cmjava2023.symboltable.*;

import java.util.ArrayList;
import java.util.Objects;

public class ASTVisitorFirst extends ASTTraverser<ASTNodes.Node> {
    public ArrayList<String> errors;

    public ASTVisitorFirst(ArrayList<String> errors) {
        this.errors = errors;
    }

    private ArrayList<ASTNodes.Statement> getModifiedStatements(ArrayList<ASTNodes.Statement> statements) {
        ArrayList<ASTNodes.Statement> statementList = new ArrayList<>();

        for (ASTNodes.Statement statement : statements) {
            statementList.add((ASTNodes.Statement) statement.accept(this));
        }

        return statementList;
    }

    private ArrayList<ASTNodes.Condition> getModifiedConditions(ArrayList<ASTNodes.Condition> conditions) {
        ArrayList<ASTNodes.Condition> conditionList = new ArrayList<>();

        for (ASTNodes.Condition condition : conditions) {
            conditionList.add((ASTNodes.Condition) condition.accept(this));
        }

        return conditionList;
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
    public ASTNodes.Node visit(ASTNodes.FunctionNode node) {
        Function functionSymbol = node.functionSymbol();
        if (functionSymbol.getType() instanceof InvalidType invalidType) {
            checkForType(invalidType, "return type of Function", functionSymbol);
        }

        return new ASTNodes.FunctionNode(node.functionSymbol(), getModifiedParameters(node.parameters()), getModifiedStatements(node.body()));
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
    public ASTNodes.Node visit(ASTNodes.RawFunctionCallNode node) {
        Symbol functionSymbol = resolveNestedIdentifier(null, node.nestedIdentifier(), node.scope());

        if (functionSymbol instanceof Function function) {
            return new ASTNodes.FunctionCallNode(function, getModifiedExpressions(node.values()));
        }

        errors.add(String.format("Function %s is not declared", String.join(".", node.nestedIdentifier())));
        return new ASTNodes.FunctionCallNode(null, getModifiedExpressions(node.values()));

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
        return new ASTNodes.IfNode(node.expression(), getModifiedStatements(node.statements()));
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.ElseNode node) {
        return new ASTNodes.ElseNode(getModifiedStatements(node.statements()));
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.BlockScopeNode node) {
        return new ASTNodes.BlockScopeNode(getModifiedConditions(node.conditions()));
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.VariableNode node) {
        Variable variableSymbol = node.variableSymbol();
        if (variableSymbol.getType() instanceof InvalidType invalidType) {
            checkForVoidType("Variable", variableSymbol, invalidType);
            checkForType(invalidType, "Variable", variableSymbol);
        }
        if (variableSymbol.getInitialExpression() != null) {
            variableSymbol.setInitialExpression((ASTNodes.Expression) variableSymbol.getInitialExpression().accept(this));
        }
        return node;
    }

    private void checkForType(InvalidType invalidType, String errorMessagePart, Symbol symbol) {
        String type = invalidType.getName();
        String arrayIndicator = "[]";
        boolean isArray = type.contains(arrayIndicator);

        if (isArray) {
            type = type.replace(arrayIndicator, "");
        }

        Symbol typeSymbol = symbol.getScope().resolve(type);

        if (typeSymbol != null) {
            if (isArray) {
                symbol.setType(new ArrayType(typeSymbol.getType()));
            } else {
                symbol.setType(typeSymbol.getType());
            }
        } else {
            errors.add(String.format("Cannot find type %s for %s %s", invalidType.getName(), errorMessagePart, symbol.getName()));
        }
    }

    private void checkForVoidType(String objectName, Symbol symbol, InvalidType invalidType) {
        if (Objects.equals(invalidType.getName(), "void")) {
            errors.add(String.format("%s %s cannot have the type void", objectName, symbol.getName()));
        }
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.VariableAssigmentNode node) {
        return new ASTNodes.VariableAssigmentNode(node.variable(), (ASTNodes.Expression) node.expression().accept(this));
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.ValueNode<?> valueNode) {
        return valueNode;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.FunctionCallNode rawFunctionCallNode) {
        return rawFunctionCallNode;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.NestedIdentifierNode node) {
        return node;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.ComparisonNode node) {
        return node;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.RawIdentifierNode rawIdentifierNode) {
        Symbol identifierSymbol = rawIdentifierNode.scope().resolve(rawIdentifierNode.name());

        if (identifierSymbol instanceof Variable variable) {
            return new ASTNodes.ResolvedIdentifierNode(rawIdentifierNode.name(), variable);
        }

        errors.add(String.format("Variable %s is not declared", rawIdentifierNode.name()));
        return new ASTNodes.ResolvedIdentifierNode(rawIdentifierNode.name(), null);
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.ResolvedIdentifierNode resolvedIdentifierNode) {
        return resolvedIdentifierNode;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.ReturnNode node) {
        return node;
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
    public ASTNodes.Node visit(ASTNodes.ParameterAssigmentNode node) {
        return node;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.InfixNode node) {
        return new ASTNodes.InfixNode((ASTNodes.Expression) dispatch(node.leftExpression()), node.operator(), (ASTNodes.Expression) dispatch(node.rightExpression()));
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.UnaryPrefixNode node) {
        return node;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.UnarySuffixNode node) {
        return node;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.ParenthesesNode node) {
        return node;
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
                    return new ASTNodes.CastNode(new ArrayType(typeSymbol.getType()), node.expression(), node.scope());
                } else {
                    return new ASTNodes.CastNode(typeSymbol.getType(), node.expression(), node.scope());
                }
            } else {
                errors.add(String.format("Cannot find type %s for cast expression", invalidType.getName()));
            }
        }
        return node;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.ArrayInstantiationWithValuesNode node) {
        return node;
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
        return new ASTNodes.ForLoopNode(node.loopVariable(), node.termination(), node.increment(), getModifiedStatements(node.body()));
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.WhileLoopNode node) {
        return new ASTNodes.WhileLoopNode(node.expression(), getModifiedStatements(node.body()));
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.DoWhileLoopNode node) {
        return new ASTNodes.DoWhileLoopNode(node.expression(), getModifiedStatements(node.body()));
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.OperatorNode node) {
        return node;
    }
}
