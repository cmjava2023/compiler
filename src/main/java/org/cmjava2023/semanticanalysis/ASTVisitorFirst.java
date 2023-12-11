package org.cmjava2023.semanticanalysis;

import org.cmjava2023.ast.ASTNodes;
import org.cmjava2023.ast.ASTTraverser;
import org.cmjava2023.symboltable.*;

import java.util.ArrayList;
import java.util.Objects;

public class ASTVisitorFirst implements ASTTraverser<ASTNodes.Node> {
    public ArrayList<String> errors;

    public ASTVisitorFirst(ArrayList<String> errors) {
        this.errors = errors;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.StartNode node) {
        for (ASTNodes.Statement statement : node.body()) {
            statement.accept(this);
        }
        return node;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.PackageNode node) {
        return node;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.ClassNode node) {
        for (ASTNodes.Statement statement : node.body()) {
            statement.accept(this);
        }
        return node;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.FunctionNode node) {
        Function functionSymbol = node.functionSymbol();
        if (functionSymbol.getType() instanceof InvalidType invalidType) {
            checkForType(invalidType, "return type of Function", functionSymbol);
        }
        for (ASTNodes.ParameterNode statement : node.parameters()) {
            statement.accept(this);
        }
        for (ASTNodes.Statement statement : node.body()) {
            statement.accept(this);
        }
        return node;
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
        return node;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.IfNode node) {
        for (ASTNodes.Statement statement : node.statements()) {
            statement.accept(this);
        }
        return node;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.ElseNode node) {
        for (ASTNodes.Statement statement : node.statements()) {
            statement.accept(this);
        }
        return node;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.BlockScopeNode node) {
        return node;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.VariableNode node) {
        Variable variableSymbol = node.variableSymbol();
        if (variableSymbol.getType() instanceof InvalidType invalidType) {
            checkForVoidType("Variable", variableSymbol, invalidType);
            checkForType(invalidType, "Variable", variableSymbol);
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
        return node;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.ValueNode node) {
        return node;
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
    public ASTNodes.Node visit(ASTNodes.ExpressionNode node) {
        return node;
    }

    @Override
    public ASTNodes.Node visit(ASTNodes.IdentifierNode node) {
        return node;
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
}
