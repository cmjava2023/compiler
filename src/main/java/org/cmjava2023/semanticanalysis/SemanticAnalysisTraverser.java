package org.cmjava2023.semanticanalysis;

import org.cmjava2023.ast.ASTNodes;
import org.cmjava2023.ast.ASTTraverser;
import org.cmjava2023.symboltable.*;

import java.util.ArrayList;
import java.util.Objects;

public class SemanticAnalysisTraverser implements ASTTraverser {
    public ArrayList<String> errors;

    public SemanticAnalysisTraverser(ArrayList<String> errors) {
        this.errors = errors;
    }

    @Override
    public void visit(ASTNodes.StartNode node) {
        for (ASTNodes.Statement statement : node.body()) {
            statement.accept(this);
        }
    }

    @Override
    public void visit(ASTNodes.PackageNode node) {
    }

    @Override
    public void visit(ASTNodes.ClassNode node) {
        for (ASTNodes.Statement statement : node.body()) {
            statement.accept(this);
        }
    }

    @Override
    public void visit(ASTNodes.FunctionNode node) {
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
    }

    @Override
    public void visit(ASTNodes.ParameterNode node) {
        Parameter parameterSymbol = node.parameterSymbol();
        if (parameterSymbol.getType() instanceof InvalidType invalidType) {
            checkForVoidType("Parameter", parameterSymbol, invalidType);
            checkForType(invalidType, "Parameter", parameterSymbol);
        }
    }

    @Override
    public void visit(ASTNodes.FunctionCallNode node) {
    }

    @Override
    public void visit(ASTNodes.IfNode node) {
        for (ASTNodes.Statement statement : node.statements()) {
            statement.accept(this);
        }
    }

    @Override
    public void visit(ASTNodes.ElseNode node) {
        for (ASTNodes.Statement statement : node.statements()) {
            statement.accept(this);
        }
    }

    @Override
    public void visit(ASTNodes.BlockScopeNode node) {
    }

    @Override
    public void visit(ASTNodes.VariableNode node) {
        Variable variableSymbol = node.variableSymbol();
        if (variableSymbol.getType() instanceof InvalidType invalidType) {
            checkForVoidType("Variable", variableSymbol, invalidType);
            checkForType(invalidType, "Variable", variableSymbol);
        }
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
    public void visit(ASTNodes.VariableAssigmentNode node) {
    }

    @Override
    public void visit(ASTNodes.ValueNode node) {
    }

    @Override
    public void visit(ASTNodes.NestedIdentifierNode node) {
    }

    @Override
    public void visit(ASTNodes.ComparisonNode node) {
    }

    @Override
    public void visit(ASTNodes.ExpressionNode node) {
    }

    @Override
    public void visit(ASTNodes.IdentifierNode node) {
    }

    @Override
    public void visit(ASTNodes.ReturnNode node) {
    }

    @Override
    public void visit(ASTNodes.TypeNode node) {
    }

    @Override
    public void visit(ASTNodes.ArrayTypeNode node) {
    }
}
