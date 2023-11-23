package org.cmjava2023;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.cmjava2023.generated_from_antlr.MainAntlrLexer;
import org.cmjava2023.generated_from_antlr.MainAntlrParser;
import org.cmjava2023.generated_from_antlr.MainAntlrBaseVisitor;

import java.util.ArrayList;

public class ASTVisitor extends MainAntlrBaseVisitor<ASTNodes.Node> {

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
        return new ASTNodes.ClassNode(new ASTNodes.IdentifierNode(ctx.IDENTIFIER().getText()), access_modifier, modifiers, statementList);
    }

    // ########ANTLR########
    // function_declaration: access_modifier INSTANCE_MODIFIER? type IDENTIFIER PAREN_OPEN function_declaration_args PAREN_CLOSE CURLY_OPEN function_scope CURLY_CLOSE;
    // function_scope: ((expressions | assignment | variable_declaration | return_statement) SEMICOLON | block_scope)*;
    // function_declaration_args: function_declaration_arg (COMMA function_declaration_arg)*;
    public ASTNodes.Node visitFunction_declaration(MainAntlrParser.Function_declarationContext ctx) {
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

        ASTNodes.Access_Modifier access_modifier= ASTNodes.Access_Modifier.valueOf(ctx.access_modifier().getText().toUpperCase());
        ArrayList<ASTNodes.Modifier> modifiers = new ArrayList<>();
        if(ctx.INSTANCE_MODIFIER()!=null) {
            modifiers.add(ASTNodes.Modifier.valueOf(ctx.INSTANCE_MODIFIER().getText().toUpperCase())); // TODO Only one modifier currently possible. But more to follow.
        }
        ArrayList<ASTNodes.ParameterNode> parameters = new ArrayList<>();
        if(ctx.function_declaration_args()!=null) { // If there is even any function_declaration_args
            for (ParseTree tree : ctx.function_declaration_args().children) {// function_declaration_args: function_declaration_arg (COMMA function_declaration_arg)*;
                if(!tree.getText().equals(";")) { // Do not take the semicolon!
                    parameters.add((ASTNodes.ParameterNode) visit(tree)); // And visit those children ==>
                    // function_declaration_arg: (type IDENTIFIER)*;
                }
            }
        }

        // TODO SYMBOL link the identifier to a symbol ref and put the type into the symboltable
        // ctx.type().getText() --> You need to traverse it down.
        ASTNodes.IdentifierNode identidier=new ASTNodes.IdentifierNode(ctx.IDENTIFIER().getText());
        return new ASTNodes.FunctionNode(identidier, access_modifier, modifiers, parameters, statementList);
    }

    // ########ANTLR########
    // function_declaration_arg: type IDENTIFIER;
    public ASTNodes.Node visitFunction_declaration_arg(MainAntlrParser.Function_declaration_argContext ctx) {
        // TODO SYMBOL link the identifier to a symboltable and put the type into the reference then.
        // ctx.type() --> You need to traverse here.
        return new ASTNodes.ParameterNode(new ASTNodes.IdentifierNode(ctx.IDENTIFIER().getText()));
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
        return new ASTNodes.VariableNode((ASTNodes.PotentiallyNestedIdentifierNode) visit(ctx.potentially_nested_identifier()), null); // No value, because it is a declaration.
    }

    // ########ANTLR########
    // assignment: (variable_declaration | potentially_nested_identifier) EQUALS expressions;
    public ASTNodes.Node visitAssignment(MainAntlrParser.AssignmentContext ctx) {
        ASTNodes.Node identifier= ctx.potentially_nested_identifier()!=null? visit(ctx.potentially_nested_identifier()) : null;
        ASTNodes.Node expression= visit(ctx.expressions());

        if(ctx.variable_declaration()!=null){
            identifier= visit(ctx.variable_declaration().potentially_nested_identifier());
        }// Else the identifier isnt null! Thus, always has a value.

        return new ASTNodes.VariableNode((ASTNodes.Identifier) identifier, (ASTNodes.Expression) expression);
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
        return new ASTNodes.IfNode(expression, statementList);
    }

    // ########ANTLR########
    // else_statement: ELSE_KEYWORD CURLY_OPEN function_scope CURLY_CLOSE;
    // function_scope: ((expressions | assignment | variable_declaration | return_statement) SEMICOLON | block_scope)*;
    public  ASTNodes.Node visitElse_statement(MainAntlrParser.Else_statementContext ctx){
        ArrayList<ASTNodes.Statement> statementList = new ArrayList<>();
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
        return new ASTNodes.ElseNode( statementList);
    }

    // ########ANTLR########
    // return_statement: RETURN_KEYWORD expressions;
    public ASTNodes.Node visitReturn_statement(MainAntlrParser.Return_statementContext ctx){
        return new ASTNodes.ReturnNode((ASTNodes.Expression) visit(ctx.expressions()));
    }
}
