package org.cmjava2023.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.cmjava2023.generated_from_antlr.MainAntlrBaseVisitor;
import org.cmjava2023.generated_from_antlr.MainAntlrLexer;
import org.cmjava2023.generated_from_antlr.MainAntlrParser;
import org.cmjava2023.semanticanalysis.ASTVisitorFirst;
import org.cmjava2023.symboltable.*;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParseTreeVisitor extends MainAntlrBaseVisitor<ASTNodes.Node> {
    public final SymbolTable symbolTable = new SymbolTable();
    public ArrayList<String> errors = new ArrayList<>();

    // ########ANTLR########
    // start : (global_scope)+;
    // global_scope: class_declaration | package_declaration SEMICOLON;
    public ASTNodes.Node visitStart(MainAntlrParser.StartContext ctx) {
        return new ASTNodes.StartNode(getGlobalStatements(ctx.children));
    }

    private ArrayList<ASTNodes.Statement> getGlobalStatements(List<ParseTree> children) {
        ArrayList<ASTNodes.Statement> statementList = new ArrayList<>();

        for (ParseTree child : children) {
            statementList.add((ASTNodes.Statement) visit(child.getChild(0)));
        }

        return statementList;
    }

    private ArrayList<ASTNodes.Statement> getStatements(List<ParseTree> children) {
        ArrayList<ASTNodes.Statement> statementList = new ArrayList<>();

        for (ParseTree child : children) {
            if (!child.getText().equals(";")) {
                statementList.add((ASTNodes.Statement) visit(child));
            }
        }

        return statementList;
    }

    private ArrayList<ASTNodes.ParameterNode> getParameters(List<ParseTree> children) {
        ArrayList<ASTNodes.ParameterNode> parameterList = new ArrayList<>();

        for (ParseTree child : children) {
            if (!child.getText().equals(",")) {
                parameterList.add((ASTNodes.ParameterNode) visit(child));
            }
        }

        return parameterList;
    }

    private ArrayList<ASTNodes.Expression> getExpressions(List<ParseTree> children) {
        ArrayList<ASTNodes.Expression> expressionList = new ArrayList<>();

        for (ParseTree child : children) {
            if (!child.getText().equals(",")) {
                expressionList.add((ASTNodes.Expression) visit(child));
            }
        }

        return expressionList;
    }

    // ########ANTLR########
    // package_declaration: PACKAGE_KEYWORD nested_identifier;
    public ASTNodes.Node visitPackage_declaration(MainAntlrParser.Package_declarationContext ctx) {
        ASTNodes.NestedIdentifierNode nestedIdentifier = (ASTNodes.NestedIdentifierNode) visit(ctx.identifier());
        return new ASTNodes.PackageNode(nestedIdentifier.nestedIdentifier());
    }

    // ########ANTLR########
    // nested_identifier: IDENTIFIER (DOT IDENTIFIER)*;
    public ASTNodes.Node visitIdentifier(MainAntlrParser.IdentifierContext ctx) {
        int size = ctx.IDENTIFIER().size();
        ArrayList<String> identifiers = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            identifiers.add(ctx.IDENTIFIER().get(i).getText());
        }

        return new ASTNodes.NestedIdentifierNode(identifiers);
    }

    // ########ANTLR########
    // class_declaration: access_modifier CLASS_KEYWORD IDENTIFIER CURLY_OPEN class_scope CURLY_CLOSE;
    // class_scope: (function_declaration | ((variable_declaration | assignment ) SEMICOLON))*;
    public ASTNodes.Node visitClass_declaration(MainAntlrParser.Class_declarationContext ctx) {
        ASTNodes.AccessModifier accessModifier = ASTNodes.AccessModifier.valueOf(ctx.access_modifier().getText().toUpperCase());
        Clazz classSymbol = setClassScope(ctx, null, accessModifier, null);

        ArrayList<ASTNodes.Statement> statements = getStatements(ctx.class_scope().children);

        symbolTable.popScope();
        return new ASTNodes.ClassNode(classSymbol, statements);
    }

    private Clazz setClassScope(MainAntlrParser.Class_declarationContext ctx, Clazz parentClazz, ASTNodes.AccessModifier accessModifier, ASTNodes.Modifier instanceModifier) {
        String className = ctx.IDENTIFIER().getText();
        Scope currentScope = symbolTable.getCurrentScope();
        checkAlreadyDeclared("Class", className, currentScope);
        Clazz classSymbol = new Clazz(currentScope, new HashMap<>(), className, null, parentClazz, accessModifier, instanceModifier);
        classSymbol.setType(classSymbol);
        symbolTable.addSymbol(classSymbol);
        symbolTable.setScope(classSymbol);

        return classSymbol;
    }

    // ########ANTLR########
    // function_declaration: access_modifier INSTANCE_MODIFIER? type IDENTIFIER PAREN_OPEN function_declaration_args PAREN_CLOSE CURLY_OPEN function_scope CURLY_CLOSE;
    // function_scope: ((expressions | assignment | variable_declaration | return_statement) SEMICOLON | block_scope)*;
    // function_declaration_args: function_declaration_arg (COMMA function_declaration_arg)*;
    public ASTNodes.Node visitFunction_declaration(MainAntlrParser.Function_declarationContext ctx) {
        ASTNodes.Modifier instanceModifier = ctx.INSTANCE_MODIFIER() == null ? null : ASTNodes.Modifier.valueOf(ctx.INSTANCE_MODIFIER().getText().toUpperCase());
        ASTNodes.AccessModifier accessModifier = ASTNodes.AccessModifier.valueOf(ctx.access_modifier().getText().toUpperCase());
        Function functionSymbol = setFunctionScope(ctx, accessModifier, instanceModifier);

        ArrayList<ASTNodes.ParameterNode> parameters = ctx.function_declaration_args() == null ? null : getParameters(ctx.function_declaration_args().children);

        setLocalScope();

        ArrayList<ASTNodes.Statement> statements = getStatements(ctx.function_scope().children);

        symbolTable.popScope();

        symbolTable.popScope();
        return new ASTNodes.FunctionNode(functionSymbol, parameters, statements);
    }

    private Function setFunctionScope(MainAntlrParser.Function_declarationContext ctx, ASTNodes.AccessModifier accessModifier, ASTNodes.Modifier modifier) {
        String functionName = ctx.IDENTIFIER().getText();
        Scope currentScope = symbolTable.getCurrentScope();
        checkAlreadyDeclared("Function", functionName, currentScope);
        Function functionSymbol = new Function(currentScope, new HashMap<>(), functionName, null, accessModifier, modifier);
        setInvalidType(ctx.type(), functionSymbol);
        symbolTable.addSymbol(functionSymbol);
        symbolTable.setScope(functionSymbol);

        return functionSymbol;
    }

    private void checkAlreadyDeclared(String typeNameOfObject, String name, Scope scope) {
        if (scope.resolveInScope(name) != null) {
            errors.add(String.format("%s %s already defined in %s", typeNameOfObject, name, scope.getName()));
        }
    }

    // ########ANTLR########
    // function_declaration_arg: type IDENTIFIER;
    public ASTNodes.Node visitFunction_declaration_arg(MainAntlrParser.Function_declaration_argContext ctx) {
        String parameterName = ctx.IDENTIFIER().getText();
        Scope currentScope = symbolTable.getCurrentScope();
        checkAlreadyDeclared("Parameter", parameterName, currentScope);
        Parameter parameterSymbol = new Parameter(parameterName, null, currentScope);
        setInvalidType(ctx.type(), parameterSymbol);
        symbolTable.addSymbol(parameterSymbol);
        return new ASTNodes.ParameterNode(parameterSymbol);
    }

    // ########ANTLR########
    // function_call: nested_identifier PAREN_OPEN function_args? PAREN_CLOSE;
    // function_args: function_arg (COMMA function_arg)*;
    public ASTNodes.Node visitFunction_call(MainAntlrParser.Function_callContext ctx) {
        ASTNodes.NestedIdentifierNode nestedIdentifier = (ASTNodes.NestedIdentifierNode) visit(ctx.identifier());
        ArrayList<ASTNodes.Expression> argumentExpressions = ctx.function_args() == null ? null : getExpressions(ctx.function_args().children);
        return new ASTNodes.RawFunctionCallNode(nestedIdentifier.nestedIdentifier(), argumentExpressions, symbolTable.getCurrentScope());
    }

    // ########ANTLR########
    // function_arg: expressions;
    public ASTNodes.Node visitFunction_arg(MainAntlrParser.Function_argContext ctx) {
        return visit(ctx.expressions());
    }

    // ########ANTLR########
    // variable_declaration: primitive_type nested_identifier;
    public ASTNodes.Node visitVariable_declaration(MainAntlrParser.Variable_declarationContext ctx) {
        String variableName = ctx.IDENTIFIER().getText();
        Scope currentScope = symbolTable.getCurrentScope();
        checkAlreadyDeclared("Variable", variableName, currentScope);
        Function enclosingFunction = getEnclosingFunction(currentScope);
        if (enclosingFunction != null) {
            checkAlreadyDeclared("Variable", variableName, enclosingFunction);
        }
        Variable variableSymbol = new Variable(variableName, null, currentScope, null);
        setInvalidType(ctx.primitive_type(), variableSymbol);
        symbolTable.addSymbol(variableSymbol);
        return new ASTNodes.VariableNode(variableSymbol, null);
    }

    private Function getEnclosingFunction(Scope scope) {
        if (scope instanceof Function functionScope) {
            return functionScope;
        }

        if (scope.getEnclosingScope() != null) {
            return getEnclosingFunction(scope.getEnclosingScope());
        }

        return null;
    }

    private void setInvalidType(ParserRuleContext ctx, Symbol symbol) {
        ASTNodes.Type type = (ASTNodes.Type) visit(ctx);

        if (type instanceof ASTNodes.ArrayTypeNode arrayType) {
            symbol.setType(new InvalidType(arrayType.type() + "[]"));
        } else if (type instanceof ASTNodes.TypeNode baseType) {
            symbol.setType(new InvalidType(baseType.type()));
        }
    }

    // ########ANTLR########
    // assignment: (variable_declaration | nested_identifier) EQUALS expressions;
    public ASTNodes.Node visitAssignment(MainAntlrParser.AssignmentContext ctx) {
        ASTNodes.Expression expression = (ASTNodes.Expression) visit(ctx.expressions());
        if (ctx.variable_declaration() != null) {
            ASTNodes.VariableNode variable = (ASTNodes.VariableNode) visit(ctx.variable_declaration());
            return new ASTNodes.VariableNode(variable.variableSymbol(), expression);
        } else {
            ASTNodes.NestedIdentifierNode variableName = (ASTNodes.NestedIdentifierNode) visit(ctx.identifier());

            Symbol variableSymbol = ASTVisitorFirst.resolveNestedIdentifier(null, variableName.nestedIdentifier(), symbolTable.getCurrentScope());

            if (variableSymbol instanceof Variable variable) {
                return new ASTNodes.VariableAssigmentNode(variable, expression);
            }

            if (variableSymbol instanceof Parameter parameter) {
                return new ASTNodes.ParameterAssigmentNode(parameter, expression);
            }

            errors.add(String.format("Variable %s is not declared", String.join(".", variableName.nestedIdentifier())));
            return new ASTNodes.VariableAssigmentNode(null, expression);
        }
    }

    // ########ANTLR########
    // expressions: expression (expression_operator expression)?;
    public ASTNodes.Node visitExpressions(MainAntlrParser.ExpressionsContext ctx) {
        if (ctx.expression().size() > 1) {
            ASTNodes.Operator operator = getOperator(ctx.expression_operator());
            return new ASTNodes.ComparisonNode((ASTNodes.Expression) visit(ctx.expression().get(0)), (ASTNodes.ComparisonOperator) operator, (ASTNodes.Expression) visit(ctx.expression().get(1)));
        } else {
            return visit(ctx.expression().get(0));
        }
    }

    private ASTNodes.Operator getOperator(ParserRuleContext ctx) {
        ASTNodes.OperatorNode operatorNode = getOperatorNode(ctx);
        return operatorNode.operator();
    }

    // ########ANTLR########
    // expression: function_call | IDENTIFIER | STRING | CHARACTER| FLOAT | DECIMAL | INTEGER | LONG | FALSE | TRUE
    // | identifier | casting | expression expression_concatinator expression | PAREN_OPEN expression PAREN_CLOSE
    // | array_expression | instantiation | access_attribute | access_index | (numerical_prefix | logical_prefix) expressions | expression expression_suffix;
    public ASTNodes.Node visitExpression(MainAntlrParser.ExpressionContext ctx) {
        if (ctx.function_call() != null) {
            return visit(ctx.function_call());
        } else if (ctx.STRING() != null) {
            String string = ctx.STRING().getText();
            if (string.startsWith("\"") && string.endsWith("\"")) {
                return new ASTNodes.ValueNode<>(string.substring(1, string.length() - 1));
            } else {
                return new ASTNodes.ValueNode<>(string);
            }
        } else if (ctx.CHARACTER() != null) {
            return new ASTNodes.ValueNode<>(ctx.INTEGER().getText().charAt(0));
        } else if (ctx.FLOAT() != null) {
            return new ASTNodes.ValueNode<>(Float.parseFloat(ctx.INTEGER().getText()));
        } else if (ctx.DECIMAL() != null) {
            return new ASTNodes.ValueNode<>(Double.parseDouble(ctx.DECIMAL().getText()));
        } else if (ctx.INTEGER() != null) {
            return new ASTNodes.ValueNode<>(Integer.parseInt(ctx.INTEGER().getText()));
        } else if (ctx.LONG() != null) {
            return new ASTNodes.ValueNode<>(Long.parseLong(ctx.INTEGER().getText()));
        } else if (ctx.FALSE() != null) {
            return new ASTNodes.ValueNode<>(Boolean.parseBoolean(ctx.INTEGER().getText()));
        } else if (ctx.TRUE() != null) {
            return new ASTNodes.ValueNode<>(Boolean.parseBoolean(ctx.INTEGER().getText()));
        } else if (ctx.identifier() != null) {
            return visit(ctx.identifier());
        } else if (ctx.casting() != null) {
            return visit(ctx.casting());
        } else if (ctx.expression_concatinator() != null) {
            ASTNodes.Expression leftExpression = (ASTNodes.Expression) visit(ctx.expression().get(0));
            ASTNodes.Expression rightExpression = (ASTNodes.Expression) visit(ctx.expression().get(1));
            ASTNodes.Operator operator = getOperator(ctx.expression_concatinator());
            return new ASTNodes.InfixNode(leftExpression, (ASTNodes.InfixOperator) operator, rightExpression);
        } else if (ctx.PAREN_OPEN() != null && ctx.PAREN_CLOSE() != null) {
            return new ASTNodes.ParenthesesNode((ASTNodes.Expression) visit(ctx.expression().get(0)));
        } else if (ctx.array_expression() != null) {
            return visit(ctx.array_expression());
        } else if (ctx.instantiation() != null) {
            return visit(ctx.instantiation());
        } else if (ctx.access_index() != null) {
            return visit(ctx.access_index());
        } else if (ctx.numerical_prefix() != null) {
            ASTNodes.Operator operator = getOperator(ctx.numerical_prefix());
            return new ASTNodes.UnaryPrefixNode((ASTNodes.PrefixOperator) operator, (ASTNodes.Expression) visit(ctx.expression().get(0)));
        } else if (ctx.logical_prefix() != null) {
            ASTNodes.Operator operator = getOperator(ctx.logical_prefix());
            return new ASTNodes.UnaryPrefixNode((ASTNodes.PrefixOperator) operator, (ASTNodes.Expression) visit(ctx.expression().get(0)));
        } else if (ctx.expression_suffix() != null) {
            ASTNodes.Operator operator = getOperator(ctx.expression_suffix());
            return new ASTNodes.UnarySuffixNode((ASTNodes.SuffixOperator) operator, (ASTNodes.Expression) visit(ctx.expression().get(0)));
        } else {
            return null;
        }
    }

    private static ASTNodes.OperatorNode getOperatorNode(ParserRuleContext ctx) {
        if (ctx.children.get(0).getChild(0) instanceof TerminalNode terminalNode) {
            int tokenType = terminalNode.getSymbol().getType();
            String tokenName = MainAntlrLexer.VOCABULARY.getSymbolicName(tokenType);
            ASTNodes.InfixOperator infixOperator = ASTNodes.InfixOperator.valueOf(tokenName);
            return new ASTNodes.OperatorNode(infixOperator);
        } else {
            throw new IllegalArgumentException("Expected a TerminalNode");
        }
    }

    public ASTNodes.Node visitCasting(MainAntlrParser.CastingContext ctx) {
        Type castType = getInvalidType(ctx.type());

        return new ASTNodes.CastNode(castType, (ASTNodes.Expression) visit(ctx.expressions()));
    }

    public ASTNodes.Node visitAccess_index(MainAntlrParser.Access_indexContext ctx) {
        ASTNodes.NestedIdentifierNode variableName = (ASTNodes.NestedIdentifierNode) visit(ctx.identifier());

        Symbol variableSymbol = ASTVisitorFirst.resolveNestedIdentifier(null, variableName.nestedIdentifier(), symbolTable.getCurrentScope());

        if (variableSymbol instanceof Variable variable && variable.getType() instanceof ArrayType) {
            return new ASTNodes.ArrayAccessNode(variable, getArrayIntegers(ctx.INTEGER()));
        }

        errors.add(String.format("Variable %s is not declared or not an Array", String.join(".", variableName.nestedIdentifier())));

        return new ASTNodes.ArrayAccessNode(null, null);
    }

    public ASTNodes.Node visitArray_expression(MainAntlrParser.Array_expressionContext ctx) {
        ArrayList<ASTNodes.Expression> expressions = getExpressions(ctx.children);

        return new ASTNodes.ArrayInstantiationWithValuesNode(expressions);
    }

    public ASTNodes.Node visitInstantiation(MainAntlrParser.InstantiationContext ctx) {
        Type objectType = getInvalidType(ctx.type());

        if (ctx.INTEGER().isEmpty()) {
            return new ASTNodes.ObjectInstantiationNode(objectType);
        } else {
            return new ASTNodes.ArrayInstantiationNode(objectType, getArrayIntegers(ctx.INTEGER()));
        }
    }

    private static ArrayList<Integer> getArrayIntegers(List<TerminalNode> integerTerminalNodes) {
        ArrayList<Integer> dimensionSizes = new ArrayList<>();

        for (TerminalNode integerTerminalNode : integerTerminalNodes) {
            dimensionSizes.add(Integer.parseInt(integerTerminalNode.getText()));
        }
        return dimensionSizes;
    }

    @Nullable
    private Type getInvalidType(MainAntlrParser.TypeContext ctx) {
        ASTNodes.Type type = (ASTNodes.Type) visit(ctx);

        Type castType = null;

        if (type instanceof ASTNodes.ArrayTypeNode arrayType) {
            castType = new InvalidType(arrayType.type() + "[]");
        } else if (type instanceof ASTNodes.TypeNode baseType) {
            castType = new InvalidType(baseType.type());
        }
        return castType;
    }

    // ########ANTLR########
    // block_scope: if_statement | if_else_statement;
    // if_statement: IF_KEYWORD PAREN_OPEN expressions PAREN_CLOSE CURLY_OPEN function_scope CURLY_CLOSE;
    // if_else_statement: if_statement else_statement;
    public ASTNodes.Node visitBlock_scope(MainAntlrParser.Block_scopeContext ctx) {
        ArrayList<ASTNodes.Condition> conditions = new ArrayList<>();
        if (ctx.if_statement() != null) {
            conditions.add((ASTNodes.Condition) visit(ctx.if_statement()));
        } else {
            conditions.add((ASTNodes.Condition) visit(ctx.if_else_statement().if_statement()));
            conditions.add((ASTNodes.Condition) visit(ctx.if_else_statement().else_statement()));
        }
        return new ASTNodes.BlockScopeNode(conditions);
    }

    // ########ANTLR########
    // if_statement: IF_KEYWORD PAREN_OPEN expressions PAREN_CLOSE CURLY_OPEN function_scope CURLY_CLOSE;
    // function_scope: ((expressions | assignment | variable_declaration | return_statement) SEMICOLON | block_scope)*;
    public ASTNodes.Node visitIf_statement(MainAntlrParser.If_statementContext ctx) {
        setLocalScope();
        ArrayList<ASTNodes.Statement> statements = getStatements(ctx.function_scope().children);
        ASTNodes.Expression expression = (ASTNodes.Expression) visit(ctx.expressions());
        symbolTable.popScope();
        return new ASTNodes.IfNode(expression, statements);
    }

    // ########ANTLR########
    // else_statement: ELSE_KEYWORD CURLY_OPEN function_scope CURLY_CLOSE;
    // function_scope: ((expressions | assignment | variable_declaration | return_statement) SEMICOLON | block_scope)*;
    public ASTNodes.Node visitElse_statement(MainAntlrParser.Else_statementContext ctx) {
        setLocalScope();
        ArrayList<ASTNodes.Statement> statements = getStatements(ctx.function_scope().children);
        symbolTable.popScope();
        return new ASTNodes.ElseNode(statements);
    }

    private void setLocalScope() {
        LocalScope elseScope = new LocalScope(symbolTable.getCurrentScope(), new HashMap<>());
        symbolTable.getCurrentScope().addChildScope(elseScope);
        symbolTable.setScope(elseScope);
    }

    // ########ANTLR########
    // return_statement: RETURN_KEYWORD expressions;
    public ASTNodes.Node visitReturn_statement(MainAntlrParser.Return_statementContext ctx) {
        return new ASTNodes.ReturnNode((ASTNodes.Expression) visit(ctx.expressions()));
    }

    public ASTNodes.Node visitType(MainAntlrParser.TypeContext ctx) {
        if (ctx.primitive_type() != null) {
            return visit(ctx.primitive_type());
        } else if (ctx.array_type() != null) {
            ASTNodes.TypeNode arrayType = (ASTNodes.TypeNode) visit(ctx.array_type());
            return new ASTNodes.ArrayTypeNode(arrayType.type());
        } else if (ctx.reference_type() != null) {
            return visit(ctx.reference_type());
        } else {
            return new ASTNodes.TypeNode(ctx.VOID_KEYWORD().getText());
        }
    }

    public ASTNodes.Node visitPrimitive_type(MainAntlrParser.Primitive_typeContext ctx) {
        if (ctx.numeric_type() != null) {
            return visit(ctx.numeric_type());
        } else {
            return new ASTNodes.TypeNode(ctx.BOOLEAN_KEYWORD().getText());
        }
    }

    public ASTNodes.Node visitNumeric_type(MainAntlrParser.Numeric_typeContext ctx) {
        if (ctx.integral_type() != null) {
            return visit(ctx.integral_type());
        } else {
            return visit(ctx.floating_point_type());
        }
    }

    public ASTNodes.Node visitIntegral_type(MainAntlrParser.Integral_typeContext ctx) {
        return new ASTNodes.TypeNode(ctx.getText());
    }

    public ASTNodes.Node visitFloating_point_type(MainAntlrParser.Floating_point_typeContext ctx) {
        return new ASTNodes.TypeNode(ctx.getText());
    }

    public ASTNodes.Node visitArray_type(MainAntlrParser.Array_typeContext ctx) {
        if (ctx.primitive_type() != null) {
            return visit(ctx.primitive_type());
        } else if (ctx.class_type() != null) {
            return visit(ctx.class_type());
        } else {
            return visit(ctx.type_variable());
        }
    }

    public ASTNodes.Node visitClass_type(MainAntlrParser.Class_typeContext ctx) {
        return new ASTNodes.TypeNode(ctx.IDENTIFIER().getText());
    }

    public ASTNodes.Node visitReference_type(MainAntlrParser.Reference_typeContext ctx) {
        if (ctx.class_type() != null) {
            return visit(ctx.class_type());
        } else if (ctx.type_variable() != null) {
            return visit(ctx.type_variable());
        } else {
            return visit(ctx.array_type());
        }
    }

    public ASTNodes.Node visitType_variable(MainAntlrParser.Type_variableContext ctx) {
        return new ASTNodes.TypeNode(ctx.IDENTIFIER().getText());
    }
}
