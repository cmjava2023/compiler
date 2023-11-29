package org.cmjava2023;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.cmjava2023.generated_from_antlr.MainAntlrBaseVisitor;
import org.cmjava2023.generated_from_antlr.MainAntlrLexer;
import org.cmjava2023.generated_from_antlr.MainAntlrParser;
import org.cmjava2023.symboltable.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ASTVisitor extends MainAntlrBaseVisitor<ASTNodes.Node> {
    public final SymbolTable symbolTable = new SymbolTable();

    // ########ANTLR########
    // start : (global_scope)+;
    // global_scope: class_declaration | package_declaration SEMICOLON;
    public ASTNodes.Node visitStart(MainAntlrParser.StartContext ctx) {
        return new ASTNodes.StartNode(getGlobalStatements(ctx.children));
    }

    private ASTNodes.Statement[] getGlobalStatements(List<ParseTree> children) {
        int size = children.size();
        ASTNodes.Statement[] statements = new ASTNodes.Statement[size];

        for (int i = 0; i < size; i++) {
            statements[i] = (ASTNodes.Statement) visit(children.get(i).getChild(0));
        }
        return statements;
    }

    private ASTNodes.Statement[] getStatements(List<ParseTree> children) {
        int size = getSize(children);
        ASTNodes.Statement[] statements = new ASTNodes.Statement[size];

        for (int i = 0; i < size; i++) {
            statements[i] = (ASTNodes.Statement) visit(children.get(i));
        }
        return statements;
    }

    private ASTNodes.ParameterNode[] getParameters(List<ParseTree> children) {
        int size = getSize(children);
        ASTNodes.ParameterNode[] parameters = new ASTNodes.ParameterNode[size];

        for (int i = 0; i < size; i++) {
            parameters[i] = (ASTNodes.ParameterNode) visit(children.get(i));
        }
        return parameters;
    }

    private ASTNodes.Expression[] getExpressions(List<ParseTree> children) {
        int size = getSize(children);
        ASTNodes.Expression[] expressions = new ASTNodes.Expression[size];

        for (int i = 0; i < size; i++) {
            expressions[i] = (ASTNodes.Expression) visit(children.get(i));
        }
        return expressions;
    }

    private static int getSize(List<ParseTree> children) {
        int size = children.size();
        if (Objects.equals(children.get(size - 1).getText(), ";")) {
            children.remove(size - 1);
            size--;
        }
        return size;
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
        String[] identifiers = new String[size];

        for (int i = 0; i < size; i++) {
            identifiers[i] = ctx.IDENTIFIER().get(i).getText();
        }

        return new ASTNodes.NestedIdentifierNode(identifiers);
    }

    // ########ANTLR########
    // class_declaration: access_modifier CLASS_KEYWORD IDENTIFIER CURLY_OPEN class_scope CURLY_CLOSE;
    // class_scope: (function_declaration | ((variable_declaration | assignment ) SEMICOLON))*;
    public ASTNodes.Node visitClass_declaration(MainAntlrParser.Class_declarationContext ctx) {
        ASTNodes.AccessModifier accessModifier = ASTNodes.AccessModifier.valueOf(ctx.access_modifier().getText().toUpperCase());
        Clazz classSymbol = setClassScope(ctx, null, accessModifier, null);

        ASTNodes.Statement[] statements = getStatements(ctx.class_scope().children);

        symbolTable.popScope();
        return new ASTNodes.ClassNode(classSymbol, statements);
    }

    private Clazz setClassScope(MainAntlrParser.Class_declarationContext ctx, Clazz parentClazz, ASTNodes.AccessModifier accessModifier, ASTNodes.Modifier instanceModifier) {
        Clazz classSymbol = new Clazz(symbolTable.getCurrentScope(), new HashMap<>(), ctx.IDENTIFIER().getText(), null, parentClazz, accessModifier, instanceModifier);
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

        ASTNodes.ParameterNode[] parameters = ctx.function_declaration_args() == null ? null : getParameters(ctx.function_declaration_args().children);

        setLocalScope();

        ASTNodes.Statement[] statements = getStatements(ctx.function_scope().children);

        symbolTable.popScope();

        symbolTable.popScope();
        return new ASTNodes.FunctionNode(functionSymbol, parameters, statements);
    }

    private Function setFunctionScope(MainAntlrParser.Function_declarationContext ctx, ASTNodes.AccessModifier accessModifier, ASTNodes.Modifier modifier) {
        Function functionSymbol = new Function(symbolTable.getCurrentScope(), new HashMap<>(), ctx.IDENTIFIER().getText(), null, accessModifier, modifier);
        setType(ctx.type(), functionSymbol);
        symbolTable.addSymbol(functionSymbol);
        symbolTable.setScope(functionSymbol);

        return functionSymbol;
    }

    // ########ANTLR########
    // function_declaration_arg: type IDENTIFIER;
    public ASTNodes.Node visitFunction_declaration_arg(MainAntlrParser.Function_declaration_argContext ctx) {
        Paramater parameterSymbol = new Paramater(ctx.IDENTIFIER().getText(), null, symbolTable.getCurrentScope());
        setType(ctx.type(), parameterSymbol);
        symbolTable.addSymbol(parameterSymbol);
        return new ASTNodes.ParameterNode(parameterSymbol);
    }

    // ########ANTLR########
    // function_call: nested_identifier PAREN_OPEN function_args? PAREN_CLOSE;
    // function_args: function_arg (COMMA function_arg)*;
    public ASTNodes.Node visitFunction_call(MainAntlrParser.Function_callContext ctx) {
        ASTNodes.NestedIdentifierNode nestedIdentifier = (ASTNodes.NestedIdentifierNode) visit(ctx.identifier());
        ASTNodes.Expression[] argumentExpressions = ctx.function_args() == null ? null : getExpressions(ctx.function_args().children);
        return new ASTNodes.FunctionCallNode(nestedIdentifier.nestedIdentifier(), argumentExpressions);
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
        Variable variableSymbol = new Variable(variableName, null, symbolTable.getCurrentScope(), null);
        setType(ctx.primitive_type(), variableSymbol);
        symbolTable.addSymbol(variableSymbol);
        return new ASTNodes.VariableNode(variableSymbol, null);
    }

    private void setType(ParserRuleContext ctx, Symbol symbol) {
        ASTNodes.Type type = (ASTNodes.Type) visit(ctx);
        if (type instanceof ASTNodes.ArrayTypeNode) {
            ASTNodes.ArrayTypeNode typeBase = (ASTNodes.ArrayTypeNode) type;
            Symbol typeSymbol = symbolTable.getCurrentScope().resolve(typeBase.type());
            if (typeSymbol == null) {
                symbol.setType(new InvalidType(typeBase.type() + "[]"));
            } else {
                symbol.setType(new ArrayType(typeSymbol.getType()));
            }
        } else {
            ASTNodes.TypeNode typeBase = (ASTNodes.TypeNode) type;
            Symbol typeSymbol = symbolTable.getCurrentScope().resolve(typeBase.type());

            if (typeSymbol == null) {
                symbol.setType(new InvalidType(typeBase.type()));
            } else {
                symbol.setType(typeSymbol.getType());
            }
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
            return new ASTNodes.VariableAssigmentNode(variableName.nestedIdentifier(), expression);
        }
    }

    // ########ANTLR########
    // expressions: expression (expression_operator expression)?;
    public ASTNodes.Node visitExpressions(MainAntlrParser.ExpressionsContext ctx) {
        if (ctx.expression().size() > 1) {
            if (ctx.expression_operator().children.get(0) instanceof TerminalNode terminalNode) {
                int tokenType = terminalNode.getSymbol().getType();
                String tokenName = MainAntlrLexer.VOCABULARY.getSymbolicName(tokenType);
                ASTNodes.Operators operator = ASTNodes.Operators.valueOf(tokenName);
                return new ASTNodes.ComparisonNode((ASTNodes.ExpressionNode) visit(ctx.expression().get(0)), operator, (ASTNodes.ExpressionNode) visit(ctx.expression().get(1)));
            } else {
                throw new IllegalArgumentException("Expected a TerminalNode");
            }
        } else {
            return visit(ctx.expression().get(0));
        }
    }

    // ########ANTLR########
    // expression: function_call | DECIMAL | INTEGER | IDENTIFIER | STRING | nested_identifier;
    public ASTNodes.Node visitExpression(MainAntlrParser.ExpressionContext ctx) {
        return ctx.function_call() != null ? visit(ctx.function_call())
                : ctx.identifier() != null ? visit(ctx.identifier())
                : ctx.IDENTIFIER() != null ? new ASTNodes.IdentifierNode(ctx.IDENTIFIER().getText())
                : ctx.STRING() != null ? new ASTNodes.ValueNode(ctx.STRING().getText())
                : ctx.INTEGER() != null ? new ASTNodes.ValueNode(ctx.INTEGER().getText())
                : ctx.DECIMAL() != null ? new ASTNodes.ValueNode(ctx.DECIMAL().getText())
                : null;//ERROR! None got declared!
    }

    // ########ANTLR########
    // block_scope: if_statement | if_else_statement;
    // if_statement: IF_KEYWORD PAREN_OPEN expressions PAREN_CLOSE CURLY_OPEN function_scope CURLY_CLOSE;
    // if_else_statement: if_statement else_statement;
    public ASTNodes.Node visitBlock_scope(MainAntlrParser.Block_scopeContext ctx) {
        ASTNodes.Condition[] conditions = new ASTNodes.Condition[2];
        if (ctx.if_statement() != null) {
            conditions[0] = (ASTNodes.Condition) visit(ctx.if_statement());
        } else {
            conditions[0] = (ASTNodes.Condition) visit(ctx.if_else_statement().if_statement());
            conditions[1] = (ASTNodes.Condition) visit(ctx.if_else_statement().else_statement());
        }
        return new ASTNodes.BlockScopeNode(conditions);
    }

    // ########ANTLR########
    // if_statement: IF_KEYWORD PAREN_OPEN expressions PAREN_CLOSE CURLY_OPEN function_scope CURLY_CLOSE;
    // function_scope: ((expressions | assignment | variable_declaration | return_statement) SEMICOLON | block_scope)*;
    public ASTNodes.Node visitIf_statement(MainAntlrParser.If_statementContext ctx) {
        setLocalScope();
        ASTNodes.Statement[] statements = getStatements(ctx.function_scope().children);
        ASTNodes.Expression expression = (ASTNodes.Expression) visit(ctx.expressions());
        symbolTable.popScope();
        return new ASTNodes.IfNode(expression, statements);
    }

    // ########ANTLR########
    // else_statement: ELSE_KEYWORD CURLY_OPEN function_scope CURLY_CLOSE;
    // function_scope: ((expressions | assignment | variable_declaration | return_statement) SEMICOLON | block_scope)*;
    public ASTNodes.Node visitElse_statement(MainAntlrParser.Else_statementContext ctx) {
        setLocalScope();
        ASTNodes.Statement[] statements = getStatements(ctx.function_scope().children);
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
