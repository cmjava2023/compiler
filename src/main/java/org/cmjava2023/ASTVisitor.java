package org.cmjava2023;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.cmjava2023.generated_from_antlr.MainAntlrLexer;
import org.cmjava2023.generated_from_antlr.MainAntlrParser;
import org.cmjava2023.generated_from_antlr.MainAntlrBaseVisitor;
import org.cmjava2023.symboltable.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ASTVisitor extends MainAntlrBaseVisitor<ASTNodes.Node> {
    public final SymbolTable symbolTable = new SymbolTable();

    // ########ANTLR########
    // start : (global_scope)+;
    // global_scope: class_declaration | package_declaration SEMICOLON;
    public ASTNodes.Node visitStart(MainAntlrParser.StartContext ctx) {
        ArrayList<ASTNodes.Statement> statementList = new ArrayList<>();
        for (ParseTree tree : ctx.children) {// global_scope: class_declaration | package_declaration SEMICOLON;
            // NOTE: tree.getChild(0) will always get the first one and leave the semicolon out. Thus it will only view class_declaration OR package_declaration.
            statementList.add((ASTNodes.Statement) visit(tree.getChild(0)));// And visit those children ==>
            // class_declaration: access_modifier CLASS_KEYWORD IDENTIFIER CURLY_OPEN class_scope CURLY_CLOSE;
            // package_declaration: PACKAGE_KEYWORD potentially_nested_identifier;
        }
        return new ASTNodes.StartNode(statementList);
    }

    // ########ANTLR########
    // package_declaration: PACKAGE_KEYWORD potentially_nested_identifier;
    public ASTNodes.Node visitPackage_declaration(MainAntlrParser.Package_declarationContext ctx) {
        return new ASTNodes.PackageNode((ASTNodes.PotentiallyNestedIdentifierNode) visit(ctx.potentially_nested_identifier())); // visit the nested Identifier
    }

    // ########ANTLR########
    // potentially_nested_identifier: IDENTIFIER (DOT IDENTIFIER)*;
    public ASTNodes.Node visitPotentially_nested_identifier(MainAntlrParser.Potentially_nested_identifierContext ctx){
        ArrayList<ASTNodes.Identifier> Lidentifier= new ArrayList<ASTNodes.Identifier>();
        for(TerminalNode identifier : ctx.IDENTIFIER()){ // go through each identifier
            Lidentifier.add( new ASTNodes.IdentifierNode(identifier.getText())); //put them into a list
        }
        return new ASTNodes.PotentiallyNestedIdentifierNode(Lidentifier);// create the potentially nested Identifier out of this list.
    }

    // ########ANTLR########
    // class_declaration: access_modifier CLASS_KEYWORD IDENTIFIER CURLY_OPEN class_scope CURLY_CLOSE;
    // class_scope: (function_declaration | ((variable_declaration | assignment ) SEMICOLON))*;
    public ASTNodes.Node visitClass_declaration(MainAntlrParser.Class_declarationContext ctx) {
        ArrayList<ASTNodes.Statement> statementList = new ArrayList<>();
        Clazz currentClass = setClassScope(ctx);
        for (ParseTree tree : ctx.class_scope().children) { // class_scope: (function_declaration | ((variable_declaration | assignment ) SEMICOLON))*;
            if(!tree.getText().equals(";")){// But not the semicolon!
                statementList.add((ASTNodes.Statement) visit(tree)); // And visit those children ==>
                // function_declaration: access_modifier INSTANCE_MODIFIER? type IDENTIFIER PAREN_OPEN function_declaration_args PAREN_CLOSE CURLY_OPEN function_scope CURLY_CLOSE;
                // variable_declaration: primitive_type potentially_nested_identifier;
                // assignment: (variable_declaration | potentially_nested_identifier) EQUALS expressions;
            }
        }
        ASTNodes.Access_Modifier access_modifier= ASTNodes.Access_Modifier.valueOf(ctx.access_modifier().getText().toUpperCase());
        ArrayList<ASTNodes.Modifier> modifiers = new ArrayList<>();
        symbolTable.popScope();
        return new ASTNodes.ClassNode(new ASTNodes.IdentifierNode(ctx.IDENTIFIER().getText()), access_modifier, modifiers, statementList, currentClass);
    }

    private Clazz setClassScope(MainAntlrParser.Class_declarationContext ctx) {
        Clazz currentClass = new Clazz(symbolTable.getCurrentScope(), new HashMap<>(), ctx.IDENTIFIER().getText(), null, null);
        currentClass.setType(currentClass);
        symbolTable.addSymbol(currentClass);
        symbolTable.setScope(currentClass);

        return currentClass;
    }

    // ########ANTLR########
    // function_declaration: access_modifier INSTANCE_MODIFIER? type IDENTIFIER PAREN_OPEN function_declaration_args PAREN_CLOSE CURLY_OPEN function_scope CURLY_CLOSE;
    // function_scope: ((expressions | assignment | variable_declaration | return_statement) SEMICOLON | block_scope)*;
    // function_declaration_args: function_declaration_arg (COMMA function_declaration_arg)*;
    public ASTNodes.Node visitFunction_declaration(MainAntlrParser.Function_declarationContext ctx) {
        Function currentFunction = setFunctionScope(ctx);

        ArrayList<ASTNodes.ParameterNode> parameters = new ArrayList<>();
        if(ctx.function_declaration_args()!=null) { // If there is even any function_declaration_args
            for (ParseTree tree : ctx.function_declaration_args().children) {// function_declaration_args: function_declaration_arg (COMMA function_declaration_arg)*;
                if(!tree.getText().equals(";")) { // Do not take the semicolon!
                    parameters.add((ASTNodes.ParameterNode) visit(tree)); // And visit those children ==>
                    // function_declaration_arg: (type IDENTIFIER)*;
                }
            }
        }

        LocalScope functionBody = new LocalScope(symbolTable.getCurrentScope(), new HashMap<>());
        symbolTable.getCurrentScope().addChildScope(functionBody);
        symbolTable.setScope(functionBody);

        ArrayList<ASTNodes.Statement> statementList = new ArrayList<>();
        for (ParseTree tree : ctx.function_scope().children) { // function_scope: ((expressions | assignment | variable_declaration | return_statement) SEMICOLON | block_scope)*;
            if(!tree.getText().equals(";")) {// But not the semicolon!
                statementList.add((ASTNodes.Statement) visit(tree.getChild(0))); // And visit those Children ==>
                // expressions: expression (expression_operator expression)?;
                // variable_declaration: primitive_type potentially_nested_identifier;
                // assignment: (variable_declaration | potentially_nested_identifier) EQUALS expressions;
                // return_statement: RETURN_KEYWORD expressions;
                // block_scope: if_statement | if_else_statement;
            }
        }

        symbolTable.popScope();

        ASTNodes.Access_Modifier access_modifier= ASTNodes.Access_Modifier.valueOf(ctx.access_modifier().getText().toUpperCase());
        ArrayList<ASTNodes.Modifier> modifiers = new ArrayList<>();
        if(ctx.INSTANCE_MODIFIER()!=null) {
            modifiers.add(ASTNodes.Modifier.valueOf(ctx.INSTANCE_MODIFIER().getText().toUpperCase())); // TODO Only one modifier currently possible. But more to follow.
        }

        // TODO SYMBOL link the identifier to a symbol ref and put the type into the symboltable
        // ctx.type().getText() --> You need to traverse it down.
        ASTNodes.IdentifierNode identidier=new ASTNodes.IdentifierNode(ctx.IDENTIFIER().getText());
        symbolTable.popScope();
        return new ASTNodes.FunctionNode(identidier, access_modifier, modifiers, parameters, statementList, currentFunction);
    }

    private Function setFunctionScope(MainAntlrParser.Function_declarationContext ctx) {
        Function currentFunction = new Function(symbolTable.getCurrentScope(), new HashMap<>(), ctx.IDENTIFIER().getText(), null);
        setType(ctx.type(), currentFunction);
        symbolTable.addSymbol(currentFunction);
        symbolTable.setScope(currentFunction);

        return currentFunction;
    }

    // ########ANTLR########
    // function_declaration_arg: type IDENTIFIER;
    public ASTNodes.Node visitFunction_declaration_arg(MainAntlrParser.Function_declaration_argContext ctx) {
        Variable parameterSymbol = new Variable(ctx.IDENTIFIER().getText(), null, symbolTable.getCurrentScope());
        setType(ctx.type(), parameterSymbol);
        symbolTable.addSymbol(parameterSymbol);
        return new ASTNodes.ParameterNode(new ASTNodes.IdentifierNode(ctx.IDENTIFIER().getText()), parameterSymbol);
    }

    // ########ANTLR########
    // function_call: potentially_nested_identifier PAREN_OPEN function_args? PAREN_CLOSE;
    // function_args: function_arg (COMMA function_arg)*;
    public ASTNodes.Node visitFunction_call(MainAntlrParser.Function_callContext ctx) {
        ASTNodes.Node nestedIdentifier = visit(ctx.potentially_nested_identifier());
        ArrayList<ASTNodes.Expression> args_expression = new ArrayList<>();
        if(ctx.function_args()!=null) { //If there is any function_args
            for (ParseTree tree : ctx.function_args().children) { // function_args: function_arg (COMMA function_arg)*;
                if(!tree.getText().equals(",")) { // Do not take the comma
                    args_expression.add((ASTNodes.Expression) visit(tree));// Visit those children ==>
                    //function_arg: expressions;
                }
            }
        }
        return new ASTNodes.CallNode((ASTNodes.Identifier) nestedIdentifier, args_expression);
    }

    // ########ANTLR########
    // function_arg: expressions;
    public ASTNodes.Node visitFunction_arg(MainAntlrParser.Function_argContext ctx) {
        return visit(ctx.expressions());
    }

    // ########ANTLR########
    // variable_declaration: primitive_type potentially_nested_identifier;
    public ASTNodes.Node visitVariable_declaration(MainAntlrParser.Variable_declarationContext ctx) {
        // TODO lexer/parser can a variable be nested when it gets declared?
        // TODO SYMBOL Wait for lexer/parser and then resolve and reference the type to the symbol table!
        ASTNodes.PotentiallyNestedIdentifierNode name = (ASTNodes.PotentiallyNestedIdentifierNode) visit(ctx.potentially_nested_identifier());
        ASTNodes.IdentifierNode identifierName =  (ASTNodes.IdentifierNode) name.nested_identifier().get(name.nested_identifier().size() - 1);
        Variable variableSymbol = new Variable(identifierName.symbolRef(), null, symbolTable.getCurrentScope());
        setType(ctx.primitive_type(), variableSymbol);
        symbolTable.addSymbol(variableSymbol);
        return new ASTNodes.VariableNode(name, null, variableSymbol); // No value, because it is a declaration.
    }

    private void setType(ParserRuleContext ctx, Symbol symbol) {
        ASTNodes.Type type = (ASTNodes.Type) visit(ctx);
        if (type instanceof ASTNodes.ArrayTypeNode){
            ASTNodes.ArrayTypeNode typeBase = (ASTNodes.ArrayTypeNode) type;
            Symbol typeSymbol = symbolTable.getCurrentScope().resolve(typeBase.type());
            if (typeSymbol == null) {
                symbol.setType(new InvalidType(typeBase.type()+"[]"));
            } else {
                symbol.setType(new ArrayType(typeSymbol.getType()));
            }
        }
        else{
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
    // assignment: (variable_declaration | potentially_nested_identifier) EQUALS expressions;
    public ASTNodes.Node visitAssignment(MainAntlrParser.AssignmentContext ctx) {
        ASTNodes.Expression expression = (ASTNodes.Expression) visit(ctx.expressions());

        if(ctx.variable_declaration()!=null){
            ASTNodes.VariableNode variable = (ASTNodes.VariableNode) visit(ctx.variable_declaration());
            return new ASTNodes.VariableNode(variable.symbolRef(), expression, variable.symbol());
        }
        else{
            ASTNodes.PotentiallyNestedIdentifierNode name = (ASTNodes.PotentiallyNestedIdentifierNode) visit(ctx.potentially_nested_identifier());
            ASTNodes.IdentifierNode identifierName =  (ASTNodes.IdentifierNode) name.nested_identifier().get(name.nested_identifier().size() - 1);
            Symbol variable = symbolTable.getCurrentScope().resolve(identifierName.symbolRef());
            if (variable == null){
                System.out.println("Variable not declared " + identifierName);
            }
            return new ASTNodes.VariableNode(identifierName,  expression, variable);
        }
    }

    // ########ANTLR########
    // expressions: expression (expression_operator expression)?;
    public ASTNodes.Node visitExpressions(MainAntlrParser.ExpressionsContext ctx){
        ArrayList<MainAntlrParser.ExpressionContext> lctx= (ArrayList<MainAntlrParser.ExpressionContext>) ctx.expression();
        if(lctx.size()>1) {//There is more than one element in there. then it is a comparison!
            if (ctx.expression_operator().children.get(0) instanceof TerminalNode terminalNode) {
                int tokenType = terminalNode.getSymbol().getType();
                String tokenName = MainAntlrLexer.VOCABULARY.getSymbolicName(tokenType);
                ASTNodes.Operators operator = ASTNodes.Operators.valueOf(tokenName);
                return new ASTNodes.ComparisonNode((ASTNodes.ExpressionNode)visit(lctx.get(0)), operator, (ASTNodes.ExpressionNode)visit(lctx.get(1)));
            } else {
                throw new IllegalArgumentException("Expected a TerminalNode");
            }
        }
        else{ // its a single expression
            return visit(lctx.get(0));
        }
    }

    // ########ANTLR########
    // expression: function_call | DECIMAL | INTEGER | IDENTIFIER | STRING | potentially_nested_identifier;
    public ASTNodes.Node visitExpression(MainAntlrParser.ExpressionContext ctx) {
        return ctx.function_call() != null ? visit(ctx.function_call())
                : ctx.potentially_nested_identifier() != null ? visit(ctx.potentially_nested_identifier())
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
        ArrayList<ASTNodes.Condition> conditions= new ArrayList<ASTNodes.Condition>();
        if(ctx.if_statement()!=null){//if_statement: IF_KEYWORD PAREN_OPEN expressions PAREN_CLOSE CURLY_OPEN function_scope CURLY_CLOSE;
            conditions.add((ASTNodes.Condition) visit(ctx.if_statement()));
        }
        else{    //if_else_statement: if_statement else_statement;
            conditions.add((ASTNodes.Condition) visit(ctx.if_else_statement().if_statement()));
            conditions.add((ASTNodes.Condition) visit(ctx.if_else_statement().else_statement()));
        }
        return new ASTNodes.BlockScopeNode(conditions);
    }

    // ########ANTLR########
    // if_statement: IF_KEYWORD PAREN_OPEN expressions PAREN_CLOSE CURLY_OPEN function_scope CURLY_CLOSE;
    // function_scope: ((expressions | assignment | variable_declaration | return_statement) SEMICOLON | block_scope)*;
    public  ASTNodes.Node visitIf_statement(MainAntlrParser.If_statementContext ctx){
        ArrayList<ASTNodes.Statement> statementList = new ArrayList<>();
        LocalScope ifScope = new LocalScope(symbolTable.getCurrentScope(), new HashMap<>());
        symbolTable.getCurrentScope().addChildScope(ifScope);
        symbolTable.setScope(ifScope);
        for (ParseTree tree : ctx.function_scope().children) { // function_scope: ((expressions | assignment | variable_declaration | return_statement) SEMICOLON | block_scope)*;
            if(!tree.getText().equals(";")) {// But not the semicolon!
                statementList.add((ASTNodes.Statement) visit(tree)); // And visit those Children ==>
                // expressions: expression (expression_operator expression)?;
                // variable_declaration: primitive_type potentially_nested_identifier;
                // assignment: (variable_declaration | potentially_nested_identifier) EQUALS expressions;
                // return_statement: RETURN_KEYWORD expressions;
                // block_scope: if_statement | if_else_statement;
            }
        }
        //Expressions
        ASTNodes.Expression expression= (ASTNodes.Expression) visit(ctx.expressions());
        symbolTable.popScope();
        return new ASTNodes.IfNode(expression, statementList);
    }

    // ########ANTLR########
    // else_statement: ELSE_KEYWORD CURLY_OPEN function_scope CURLY_CLOSE;
    // function_scope: ((expressions | assignment | variable_declaration | return_statement) SEMICOLON | block_scope)*;
    public  ASTNodes.Node visitElse_statement(MainAntlrParser.Else_statementContext ctx){
        ArrayList<ASTNodes.Statement> statementList = new ArrayList<>();
        LocalScope elseScope = new LocalScope(symbolTable.getCurrentScope(), new HashMap<>());
        symbolTable.getCurrentScope().addChildScope(elseScope);
        symbolTable.setScope(elseScope);
        for (ParseTree tree : ctx.function_scope().children) { // function_scope: ((expressions | assignment | variable_declaration | return_statement) SEMICOLON | block_scope)*;
            if(!tree.getText().equals(";")) {// But not the semicolon!
                statementList.add((ASTNodes.Statement) visit(tree)); // And visit those Children ==>
                // expressions: expression (expression_operator expression)?;
                // variable_declaration: primitive_type potentially_nested_identifier;
                // assignment: (variable_declaration | potentially_nested_identifier) EQUALS expressions;
                // return_statement: RETURN_KEYWORD expressions;
                // block_scope: if_statement | if_else_statement;
            }
        }
        symbolTable.popScope();
        return new ASTNodes.ElseNode( statementList);
    }

    // ########ANTLR########
    // return_statement: RETURN_KEYWORD expressions;
    public ASTNodes.Node visitReturn_statement(MainAntlrParser.Return_statementContext ctx){
        return new ASTNodes.ReturnNode((ASTNodes.Expression) visit(ctx.expressions()));
    }

    public ASTNodes.Node visitType(MainAntlrParser.TypeContext ctx) {
        if (ctx.primitive_type() != null){
            return visit(ctx.primitive_type());
        }
        else if (ctx.array_type() != null){
            ASTNodes.TypeNode arrayType = (ASTNodes.TypeNode) visit(ctx.array_type());
            return new ASTNodes.ArrayTypeNode(arrayType.type());
        }
        else if (ctx.reference_type() != null){
            return visit(ctx.reference_type());
        }
        else{
            return new ASTNodes.TypeNode(ctx.VOID_KEYWORD().getText());
        }
    }

    public ASTNodes.Node visitPrimitive_type(MainAntlrParser.Primitive_typeContext ctx) {
        if (ctx.numeric_type() != null){
            return visit(ctx.numeric_type());
        }
        else{
            return new ASTNodes.TypeNode(ctx.BOOLEAN_KEYWORD().getText());
        }
    }
    public ASTNodes.Node visitNumeric_type(MainAntlrParser.Numeric_typeContext ctx) {
        if (ctx.integral_type() != null){
            return visit(ctx.integral_type());
        }
        else{
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
        if (ctx.primitive_type() != null){
            return visit(ctx.primitive_type());
        }
        else if (ctx.class_type() != null){
            return visit(ctx.class_type());
        }
        else{
            return visit(ctx.type_variable());
        }
    }
    public ASTNodes.Node visitClass_type(MainAntlrParser.Class_typeContext ctx) {
        return new ASTNodes.TypeNode(ctx.IDENTIFIER().getText());
    }

    public ASTNodes.Node visitReference_type(MainAntlrParser.Reference_typeContext ctx) {
        if (ctx.class_type() != null){
            return visit(ctx.class_type());
        }
        else if (ctx.type_variable() != null){
            return visit(ctx.type_variable());
        }
        else{
            return visit(ctx.array_type());
        }
    }
    public ASTNodes.Node visitType_variable(MainAntlrParser.Type_variableContext ctx) {
        return new ASTNodes.TypeNode(ctx.IDENTIFIER().getText());
    }
}
