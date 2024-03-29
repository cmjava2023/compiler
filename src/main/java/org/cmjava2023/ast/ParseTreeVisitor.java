package org.cmjava2023.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.cmjava2023.generated_from_antlr.MainAntlrBaseVisitor;
import org.cmjava2023.generated_from_antlr.MainAntlrParser;
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
    public ASTNodes.Node visitStart(MainAntlrParser.StartContext ctx) {
        return new ASTNodes.StartNode(getGlobalStatements(ctx.children));
    }

    // ########ANTLR########
    // global_scope: class_declaration | package_declaration SEMICOLON;
    private ArrayList<ASTNodes.Statement> getGlobalStatements(List<ParseTree> children) {
        ArrayList<ASTNodes.Statement> statementList = new ArrayList<>();

        for (ParseTree child : children) {
            statementList.add((ASTNodes.Statement) visit(child.getChild(0)));// Excluding the Semicolon, always position 0
        }

        return statementList;
    }

    // ########ANTLR########
    // class_scope: (enum_declaration | class_declaration | function_declaration | ((variable_declaration | assignment ) SEMICOLON))*;
    // function_scope: ( enum_declaration | block_scope | (expressions | assignment | variable_declaration | return_statement | continue_statement | break_statement) SEMICOLON)*;
    private ArrayList<ASTNodes.Statement> getStatements(List<ParseTree> children) {
        ArrayList<ASTNodes.Statement> statementList = new ArrayList<>();
        if (children != null) {
            for (ParseTree child : children) {
                if (!child.getText().equals(";")) {
                    statementList.add((ASTNodes.Statement) visit(child));
                }
            }
        }

        return statementList;
    }

    // ########ANTLR########
    // continue_statement: CONTINUE_KEYWORD;
    public ASTNodes.Node visitContinue_statement(MainAntlrParser.Continue_statementContext ctx) {
        return new ASTNodes.ContinueNode();
    }

    // ########ANTLR########
    // break_statement: BREAK_KEYWORD;
    public ASTNodes.Node visitBreak_statement(MainAntlrParser.Break_statementContext ctx) {
        return new ASTNodes.BreakNode();
    }

    // ########ANTLR########
    // function_declaration_args: function_declaration_arg (COMMA function_declaration_arg)*;
    private ArrayList<ASTNodes.ParameterNode> getParameters(List<ParseTree> children) {
        ArrayList<ASTNodes.ParameterNode> parameterList = new ArrayList<>();

        for (ParseTree child : children) {
            if (!child.getText().equals(",")) {
                parameterList.add((ASTNodes.ParameterNode) visit(child));
            }
        }

        return parameterList;
    }

    //  (expressions (COMMA expressions)*);
    // function_arg (COMMA function_arg)*;
    private ArrayList<ASTNodes.Expression> getExpressions(List<ParseTree> children) {
        ArrayList<ASTNodes.Expression> expressionList = new ArrayList<>();

        for (ParseTree child : children) {
            if (!child.getText().equals(",") && !child.getText().equals("{") && !child.getText().equals("}")) {
                expressionList.add((ASTNodes.Expression) visit(child));
            }
        }

        return expressionList;
    }

    private ArrayList<ASTNodes.IfNode> getIfNodes(List<MainAntlrParser.If_statementContext> children) {
        ArrayList<ASTNodes.IfNode> ifNodes = new ArrayList<>();

        for (MainAntlrParser.If_statementContext child : children) {
            ifNodes.add((ASTNodes.IfNode) visit(child));
        }

        return ifNodes;
    }

    // ########ANTLR########
    // package_declaration: PACKAGE_KEYWORD nested_identifier;
    public ASTNodes.Node visitPackage_declaration(MainAntlrParser.Package_declarationContext ctx) {
        ASTNodes.NestedIdentifierNode nestedIdentifier = (ASTNodes.NestedIdentifierNode) visit(ctx.identifier());
        return new ASTNodes.PackageNode(nestedIdentifier.nestedIdentifier());
    }

    // ########ANTLR########
    // identifier: IDENTIFIER (DOT IDENTIFIER)*;
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
    // class_scope: (enum_declaration | class_declaration | function_declaration | ((variable_declaration | assignment ) SEMICOLON))*;
    public ASTNodes.Node visitClass_declaration(MainAntlrParser.Class_declarationContext ctx) {
        ASTNodes.AccessModifier accessModifier = ASTNodes.AccessModifier.valueOf(ctx.access_modifier().getText().toUpperCase());
        Clazz classSymbol = setClassScope(ctx, null, accessModifier, null);

        ArrayList<ASTNodes.Statement> statements = getStatements(ctx.class_scope().children);

        symbolTable.popScope();
        return new ASTNodes.ClassNode(classSymbol, statements);
    }

    // ########ANTLR########
    // enum_declaration: ENUM_KEYWORD IDENTIFIER CURLY_OPEN IDENTIFIER (COMMA IDENTIFIER)* CURLY_CLOSE;
    public ASTNodes.Node visitEnum_declaration(MainAntlrParser.Enum_declarationContext ctx) {
        Clazz enumClass = setEnumScope(ctx);
        List<TerminalNode> constantsNodes = ctx.IDENTIFIER();
        constantsNodes.remove(constantsNodes.get(0));
        ArrayList<Variable> constants = new ArrayList<>();
        for (TerminalNode constant : constantsNodes) {
            Variable constantVariable = new Variable(constant.getText(), BuiltInType.INT, symbolTable.getCurrentScope());
            constants.add(constantVariable);
        }

        return new ASTNodes.EnumNode(enumClass, constants);
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

    private Clazz setEnumScope(MainAntlrParser.Enum_declarationContext ctx) {
        String enumName = ctx.IDENTIFIER().get(0).getText();
        Scope currentScope = symbolTable.getCurrentScope();
        checkAlreadyDeclared("Class", enumName, currentScope);
        Clazz classSymbol = new Clazz(currentScope, new HashMap<>(), enumName, null, null, null, null);
        classSymbol.setType(classSymbol);
        symbolTable.addSymbol(classSymbol);
        symbolTable.setScope(classSymbol);

        return classSymbol;
    }

    // ########ANTLR########
    //function_declaration: access_modifier INSTANCE_MODIFIER? type IDENTIFIER PAREN_OPEN function_declaration_args? PAREN_CLOSE CURLY_OPEN function_scope CURLY_CLOSE;
    public ASTNodes.Node visitFunction_declaration(MainAntlrParser.Function_declarationContext ctx) {
        ASTNodes.Modifier instanceModifier = ctx.INSTANCE_MODIFIER() == null ? null : ASTNodes.Modifier.valueOf(ctx.INSTANCE_MODIFIER().get(0).getText().toUpperCase());
        ASTNodes.AccessModifier accessModifier = ASTNodes.AccessModifier.valueOf(ctx.access_modifier().getText().toUpperCase());
        Function functionSymbol = setFunctionScope(ctx, accessModifier, instanceModifier);

        ArrayList<ASTNodes.ParameterNode> parameters = ctx.function_declaration_args() == null ? new ArrayList<>() : getParameters(ctx.function_declaration_args().children);

        Clazz exception=null;
        if(ctx.THROWS_KEYWORD()!=null){
            ASTNodes.NestedIdentifierNode nestedIdentifier = (ASTNodes.NestedIdentifierNode) visit(ctx.identifier());
            exception= new InvalidClazz(symbolTable.getCurrentScope(), null, String.join(".", nestedIdentifier.nestedIdentifier()), null, null, null, null);
        }
        ArrayList<ASTNodes.Statement> statements = getLocalScopeStatements(ctx.function_scope().children);

        symbolTable.popScope();
        return new ASTNodes.FunctionNode(functionSymbol, parameters, exception, statements);
    }

    private Function setFunctionScope(MainAntlrParser.Function_declarationContext ctx, ASTNodes.AccessModifier accessModifier, ASTNodes.Modifier modifier) {
        String functionName = ctx.IDENTIFIER().getText();
        Scope currentScope = symbolTable.getCurrentScope();
        checkAlreadyDeclared("Function", functionName, currentScope);
        Function functionSymbol = new Function(currentScope, new HashMap<>(), functionName, null, accessModifier, modifier);
        Type returnType = getInvalidType(ctx.type());
        functionSymbol.setType(returnType);
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
        Type parameterType = getInvalidType(ctx.type());
        parameterSymbol.setType(parameterType);
        symbolTable.addSymbol(parameterSymbol);
        return new ASTNodes.ParameterNode(parameterSymbol);
    }

    // ########ANTLR########
    // function_call: identifier PAREN_OPEN function_args? PAREN_CLOSE;
    public ASTNodes.Node visitFunction_call(MainAntlrParser.Function_callContext ctx) {
        ASTNodes.NestedIdentifierNode nestedIdentifier = (ASTNodes.NestedIdentifierNode) visit(ctx.identifier());
        ArrayList<ASTNodes.Expression> argumentExpressions = ctx.function_args() == null ? new ArrayList<>() : getExpressions(ctx.function_args().children);
        return new ASTNodes.FunctionCallNode(new InvalidFunction(symbolTable.getCurrentScope(), null, String.join(".", nestedIdentifier.nestedIdentifier()), null, null, null), argumentExpressions);
    }

    // ########ANTLR########
    // function_arg: expressions;
    public ASTNodes.Node visitFunction_arg(MainAntlrParser.Function_argContext ctx) {
        return visit(ctx.expressions());
    }

    // ########ANTLR########
    // variable_declaration: (primitive_type | reference_type) IDENTIFIER;
    public ASTNodes.Node visitVariable_declaration(MainAntlrParser.Variable_declarationContext ctx) {
        String variableName = ctx.IDENTIFIER().getText();
        Scope currentScope = symbolTable.getCurrentScope();
        checkAlreadyDeclared("Variable", variableName, currentScope);
        Function enclosingFunction = getEnclosingFunction(currentScope);
        if (enclosingFunction != null) {
            checkAlreadyDeclared("Variable", variableName, enclosingFunction);
        }
        Variable variableSymbol = new Variable(variableName, null, currentScope);
        Type variableType = null;

        if (ctx.primitive_type() != null) {
            variableType = getInvalidType(ctx.primitive_type());
        } else {
            variableType = getInvalidType(ctx.reference_type());
        }
        variableSymbol.setType(variableType);
        symbolTable.addSymbol(variableSymbol);
        return new ASTNodes.VariableNode(variableSymbol);
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

    // ########ANTLR########
    // assignment: (variable_declaration | identifier) EQUALS expressions;
    public ASTNodes.Node visitAssignment(MainAntlrParser.AssignmentContext ctx) {
        ASTNodes.Expression expression = (ASTNodes.Expression) visit(ctx.expressions());
        if (ctx.variable_declaration() != null) {
            ASTNodes.VariableNode variable = (ASTNodes.VariableNode) visit(ctx.variable_declaration());
            variable.variableSymbol().setInitialExpression(expression);
            return new ASTNodes.VariableNode(variable.variableSymbol());
        } else {
            ASTNodes.NestedIdentifierNode variableName = (ASTNodes.NestedIdentifierNode) visit(ctx.identifier());
            
            return new ASTNodes.VariableAssignmentNode(new InvalidVariable(String.join(".", variableName.nestedIdentifier()), new InvalidType(""), symbolTable.getCurrentScope()), expression);
        }
    }

    // ########ANTLR########
    // expressions: expression (expression_operator expression)?;
    public ASTNodes.Node visitExpressions(MainAntlrParser.ExpressionsContext ctx) {
        if (ctx.expression().size() == 2) {
            return new ASTNodes.ComparisonNode((ASTNodes.Expression) visit(ctx.expression().get(0)), ASTNodes.ComparisonOperator.fromTokenName(getTokenName(ctx.expression_operator().getStart())), (ASTNodes.Expression) visit(ctx.expression().get(1)));
        } else {
            return visit(ctx.expression().get(0));
        }
    }

    // ########ANTLR########
    // expression: function_call | IDENTIFIER | STRING | CHARACTER| FLOAT | DECIMAL | INTEGER | LONG | FALSE | TRUE
    // | identifier | casting | expression expression_concatinator expression | PAREN_OPEN expression PAREN_CLOSE
    // | array_expression | instantiation | access_attribute | access_index | (numerical_prefix | logical_prefix) expressions | expression expression_suffix;
    public ASTNodes.Node visitExpression(MainAntlrParser.ExpressionContext ctx) {
        if (ctx.function_call() != null) {
            return visit(ctx.function_call());
        } else if (ctx.IDENTIFIER() != null) {
            return new ASTNodes.VariableCallNode(new InvalidVariable(ctx.IDENTIFIER().getText(), new InvalidType(""), symbolTable.getCurrentScope()));
        } else if (ctx.STRING() != null) {
            String string = ctx.STRING().getText();
            if (string.startsWith("\"") && string.endsWith("\"")) {
                string = string.substring(1, string.length() - 1);
            }
            return new ASTNodes.ValueNode<>(string, BuiltInType.STRING);
        } else if (ctx.CHARACTER() != null) {
            return new ASTNodes.ValueNode<>(ctx.CHARACTER().getText().charAt(1), BuiltInType.CHAR);
        } else if (ctx.FLOAT() != null) {
            return new ASTNodes.ValueNode<>(Float.parseFloat(ctx.FLOAT().getText()), BuiltInType.FLOAT);
        } else if (ctx.DECIMAL() != null) {
            return new ASTNodes.ValueNode<>(Double.parseDouble(ctx.DECIMAL().getText()), BuiltInType.DOUBLE);
        } else if (ctx.INTEGER() != null) {
            return new ASTNodes.ValueNode<>(Integer.parseInt(ctx.INTEGER().getText()), BuiltInType.INT);
        } else if (ctx.LONG() != null) {
            String longToParse = ctx.LONG().getText();
            if (longToParse.endsWith("L") || longToParse.endsWith("l")) {
                longToParse = longToParse.substring(0, longToParse.length() - 1);
            }
            return new ASTNodes.ValueNode<>(Long.parseLong(longToParse), BuiltInType.LONG);
        } else if (ctx.FALSE() != null) {
            return new ASTNodes.ValueNode<>(false, BuiltInType.BOOLEAN);
        } else if (ctx.TRUE() != null) {
            return new ASTNodes.ValueNode<>(true, BuiltInType.BOOLEAN);
        } else if (ctx.identifier() != null) {
            return visit(ctx.identifier());
        } else if (ctx.casting() != null) {
            return visit(ctx.casting());
        } else if (ctx.expression_concatinator() != null) {
            ASTNodes.Expression leftExpression = (ASTNodes.Expression) visit(ctx.expression().get(0));
            ASTNodes.Expression rightExpression = (ASTNodes.Expression) visit(ctx.expression().get(1));
            String tokenName = getTokenName(ctx.expression_concatinator().getStart());
            return new ASTNodes.InfixNode(leftExpression, ASTNodes.BinaryOperator.fromTokenName(tokenName), rightExpression);
        } else if (ctx.PAREN_OPEN() != null && ctx.PAREN_CLOSE() != null && !ctx.expression().isEmpty()) {
            return new ASTNodes.ParenthesesNode((ASTNodes.Expression) visit(ctx.expression().get(0)));
        } else if (ctx.array_expression() != null) {
            return visit(ctx.array_expression());
        } else if (ctx.instantiation() != null) {
            return visit(ctx.instantiation());
        } else if (ctx.access_index() != null) {
            return visit(ctx.access_index());
        } else if (ctx.numerical_prefix() != null) {
            String tokenName = getTokenName(ctx.numerical_prefix().getStart());
            ASTNodes.PrefixOperator prefixOperator = ASTNodes.PrefixOperator.fromTokenName(tokenName);
            return new ASTNodes.UnaryPrefixNode(prefixOperator, (ASTNodes.Expression) visit(ctx.expressions()));
        } else if (ctx.logical_prefix() != null) {
            String tokenName = getTokenName(ctx.logical_prefix().getStart());
            ASTNodes.PrefixOperator prefixOperator = ASTNodes.PrefixOperator.fromTokenName(tokenName);
            return new ASTNodes.UnaryPrefixNode(prefixOperator, (ASTNodes.Expression) visit(ctx.expressions()));
        } else if (ctx.expression_suffix() != null) {
            String tokenName = getTokenName(ctx.expression_suffix().getStart());
            ASTNodes.SuffixOperator suffixOperator = ASTNodes.SuffixOperator.fromTokenName(tokenName);
            return new ASTNodes.UnarySuffixNode(suffixOperator, (ASTNodes.Expression) visit(ctx.expression().get(0)));
        } else if (ctx.expression() != null && ctx.expression().size() == 2) {
            String tokenName = getTokenName(ctx.expression_operator().getStart());
            return new ASTNodes.ComparisonNode((ASTNodes.Expression) visit(ctx.expression().get(0)), ASTNodes.BinaryOperator.fromTokenName(tokenName), (ASTNodes.Expression) visit(ctx.expression().get(1)));
        } else {
            return visit(ctx.expression().get(0));
        }
    }

    private static String getTokenName(Token token) {
        return org.cmjava2023.generated_from_antlr.MainAntlrLexer.VOCABULARY.getSymbolicName(token.getType());
    }

    // ########ANTLR########
    // casting: PAREN_OPEN type PAREN_CLOSE expressions;
    public ASTNodes.Node visitCasting(MainAntlrParser.CastingContext ctx) {
        Type castType = getInvalidType(ctx.type());

        return new ASTNodes.CastNode(castType, (ASTNodes.Expression) visit(ctx.expressions()), symbolTable.getCurrentScope());
    }

    // ########ANTLR########
    //access_index: IDENTIFIER (BRACKET_OPEN INTEGER BRACKET_CLOSE)+;
    public ASTNodes.Node visitAccess_index(MainAntlrParser.Access_indexContext ctx) {
        String variableName = ctx.IDENTIFIER().getText();

        Symbol variableSymbol = symbolTable.getCurrentScope().resolve(variableName);

        if (variableSymbol instanceof Variable variable) {
            return new ASTNodes.ArrayAccessNode(variable, getArrayIntegers(ctx.INTEGER()));
        }

        errors.add(String.format("Variable %s is not declared or not an Array", variableSymbol));

        return new ASTNodes.ArrayAccessNode(null, null);
    }


    // ########ANTLR########
    // array_expression: CURLY_OPEN (STRING | (expressions (COMMA expressions)*)) CURLY_CLOSE;
    public ASTNodes.Node visitArray_expression(MainAntlrParser.Array_expressionContext ctx) {
        ArrayList<ASTNodes.Expression> expressions = getExpressions(ctx.children);
        return new ASTNodes.ArrayInstantiationWithValuesNode(expressions);
    }

    // ########ANTLR########
    // instantiation: INSTANCE_KEYWORD (type (BRACKET_OPEN INTEGER BRACKET_CLOSE)+ | type);
    public ASTNodes.Node visitInstantiation(MainAntlrParser.InstantiationContext ctx) {
        Type objectType = getInvalidType(ctx.type());

        if (ctx.INTEGER().isEmpty()) {
            return new ASTNodes.ObjectInstantiationNode(objectType);
        } else {
            return new ASTNodes.ArrayInstantiationNode(objectType, getArrayIntegers(ctx.INTEGER()));
        }
    }

    // ########ANTLR########
    // (BRACKET_OPEN INTEGER BRACKET_CLOSE)+
    private static ArrayList<Integer> getArrayIntegers(List<TerminalNode> integerTerminalNodes) {
        ArrayList<Integer> dimensionSizes = new ArrayList<>();

        for (TerminalNode integerTerminalNode : integerTerminalNodes) {
            dimensionSizes.add(Integer.parseInt(integerTerminalNode.getText()));
        }
        return dimensionSizes;
    }

    @Nullable
    private Type getInvalidType(ParserRuleContext ctx) {
        ASTNodes.Type type = (ASTNodes.Type) visit(ctx);

        Type castType = null;

        if (type instanceof ASTNodes.ArrayTypeNode arrayType) {
            castType = new InvalidType(arrayType.type() + "[]".repeat(arrayType.dimensions()));
        } else if (type instanceof ASTNodes.TypeNode baseType) {
            castType = new InvalidType(baseType.type());
        }
        return castType;
    }

    // ########ANTLR########
    // block_scope: if_statement | if_else_statement | while_loop | do_while_loop | for_loop | switch_statement;
    public ASTNodes.Node visitBlock_scope(MainAntlrParser.Block_scopeContext ctx) {
        if (ctx.if_block() != null) {
            return new ASTNodes.IfBlockNode(getIfNodes(ctx.if_block().if_statement()), ctx.if_block().else_statement() != null ? (ASTNodes.ElseNode) visit(ctx.if_block().else_statement()) : null);
        } else if (ctx.while_loop() != null) {
            return visit(ctx.while_loop());
        } else if (ctx.do_while_loop() != null) {
            return visit(ctx.do_while_loop());
        } else if (ctx.for_loop() != null) {
            return visit(ctx.for_loop());
        } else {
            return visit(ctx.switch_statement());
        }
    }

    // ########ANTLR########
    // if_statement: IF_KEYWORD PAREN_OPEN expressions PAREN_CLOSE CURLY_OPEN function_scope CURLY_CLOSE;
    // function_scope: ((expressions | assignment | variable_declaration | return_statement) SEMICOLON | block_scope)*;
    public ASTNodes.Node visitIf_statement(MainAntlrParser.If_statementContext ctx) {
        ArrayList<ASTNodes.Statement> statements = getLocalScopeStatements(ctx.function_scope().children);
        return new ASTNodes.IfNode((ASTNodes.Expression) visit(ctx.expressions()), statements);
    }

    // ########ANTLR########
    // else_statement: ELSE_KEYWORD CURLY_OPEN function_scope CURLY_CLOSE;
    // function_scope: ((expressions | assignment | variable_declaration | return_statement) SEMICOLON | block_scope)*;
    public ASTNodes.Node visitElse_statement(MainAntlrParser.Else_statementContext ctx) {
        return new ASTNodes.ElseNode(getLocalScopeStatements(ctx.function_scope().children));
    }

    private void setLocalScope() {
        LocalScope elseScope = new LocalScope(symbolTable.getCurrentScope(), new HashMap<>());
        symbolTable.getCurrentScope().addChildScope(elseScope);
        symbolTable.setScope(elseScope);
    }

    // ########ANTLR########
    // return_statement: RETURN_KEYWORD expressions;
    public ASTNodes.Node visitReturn_statement(MainAntlrParser.Return_statementContext ctx) {
        if (ctx.expressions() == null) {
            return new ASTNodes.ReturnNode(null);
        } else {
            return new ASTNodes.ReturnNode((ASTNodes.Expression) visit(ctx.expressions()));
        }
    }

    public ASTNodes.Node visitType(MainAntlrParser.TypeContext ctx) {
        if (ctx.primitive_type() != null) {
            return visit(ctx.primitive_type());
        } else if (ctx.array_type() != null) {
            return visit(ctx.array_type());
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
        ASTNodes.TypeNode type = null;

        if (ctx.primitive_type() != null) {
            type = (ASTNodes.TypeNode) visit(ctx.primitive_type());
        } else if (ctx.class_type() != null) {
            type = (ASTNodes.TypeNode) visit(ctx.class_type());
        } else {
            type = (ASTNodes.TypeNode) visit(ctx.type_variable());
        }

        return new ASTNodes.ArrayTypeNode(type.type(), ctx.BRACKET_OPEN().size());

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

    // ########ANTLR########
    // for_loop: FOR_KEYWORD PAREN_OPEN ((for_init SEMICOLON for_termination SEMICOLON for_update) | for_each) PAREN_CLOSE CURLY_OPEN function_scope CURLY_CLOSE;
    // for_each: variable_declaration COLON identifier;
    // for_init: assignment;
    // for_termination: expressions;
    // for_update: expressions;

    public ASTNodes.Node visitFor_loop(MainAntlrParser.For_loopContext ctx) {
        List<ParseTree> forLoopParseTreeStatements = ctx.function_scope().children;
        setLocalScope();
        ArrayList<ASTNodes.Statement> statements = getStatements(forLoopParseTreeStatements);

        if (ctx.for_each() != null) {
            ASTNodes.VariableUsage loopVariable = (ASTNodes.VariableUsage) visit(ctx.for_each().variable_declaration());
            symbolTable.popScope();
            return new ASTNodes.ForEachLoopNode(loopVariable, (ASTNodes.NestedIdentifierNode) visit(ctx.for_each().identifier()), statements);
        } else {
            ASTNodes.VariableUsage loopVariable = (ASTNodes.VariableUsage) visit(ctx.for_init().assignment());
            ASTNodes.Expression termination = (ASTNodes.Expression) visit(ctx.for_termination().expressions());
            ASTNodes.Expression update = (ASTNodes.Expression) visit(ctx.for_update().expressions());
            symbolTable.popScope();
            return new ASTNodes.ForLoopNode(loopVariable, termination, update, statements);
        }
    }


    // ########ANTLR########
    // do_while_loop: DO_KEYWORD CURLY_OPEN function_scope CURLY_CLOSE WHILE_KEYWORD PAREN_OPEN expressions PAREN_CLOSE;
    public ASTNodes.Node visitDo_while_loop(MainAntlrParser.Do_while_loopContext ctx) {
        ArrayList<ASTNodes.Statement> statements = getLocalScopeStatements(ctx.function_scope().children);

        return new ASTNodes.DoWhileLoopNode((ASTNodes.Expression) visit(ctx.expressions()), statements);
    }

    // ########ANTLR########
    //while_loop: WHILE_KEYWORD PAREN_OPEN expressions PAREN_CLOSE CURLY_OPEN function_scope CURLY_CLOSE;
    public ASTNodes.Node visitWhile_loop(MainAntlrParser.While_loopContext ctx) {
        ArrayList<ASTNodes.Statement> statements = getLocalScopeStatements(ctx.function_scope().children);
        return new ASTNodes.WhileLoopNode((ASTNodes.Expression) visit(ctx.expressions()), statements);
    }

    // ########ANTLR########
    // switch_statement: SWITCH_KEYWORD PAREN_OPEN expressions PAREN_CLOSE CURLY_OPEN switch_scope CURLY_CLOSE;
    // switch_scope: ((CASE_KEYWORD (expressions)) COLON function_scope)* DEFAULT_KEYWORD COLON function_scope;
    public ASTNodes.Node visitSwitch_statement(MainAntlrParser.Switch_statementContext ctx) {
        ASTNodes.Expression switchEx = (ASTNodes.Expression) visit(ctx.expressions());
        List<MainAntlrParser.ExpressionsContext> expressions = ctx.switch_scope().expressions();
        List<MainAntlrParser.Function_scopeContext> functionScopes = ctx.switch_scope().function_scope();
        ArrayList<ASTNodes.CaseNode> caseNodes = new ArrayList<>();
        for (int i = 0; i <= expressions.size() - 1; i++) {
            caseNodes.add(new ASTNodes.CaseNode((ASTNodes.Expression) visit(expressions.get(i)), this.getLocalScopeStatements(functionScopes.get(i).children)));
        }
        ArrayList<ASTNodes.Statement> defaultStatements = this.getLocalScopeStatements(functionScopes.get(functionScopes.size() - 1).children);
        return new ASTNodes.SwitchNode(switchEx, caseNodes, defaultStatements);
    }

    private ArrayList<ASTNodes.Statement> getLocalScopeStatements(List<ParseTree> children) {
        setLocalScope();
        ArrayList<ASTNodes.Statement> statements = getStatements(children);

        symbolTable.popScope();
        return statements;
    }
}
