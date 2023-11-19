package org.cmjava2023;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ASTNodes {

    public enum Access_Modifier{
        DEFAULT, //  /Blank ==> Package-Private
        PUBLIC,
        PRIVATE,
        PROTECTED,
    }

    public enum Modifier {
        STATIC,
        FINAL,
        ABSTRACT,
        TRANSIENT,
        SYNCHRONIZED,
        VOLATILE,
        NATIVE,
        STRICTFP
    }

    //Numerical and logical operators
    //expression_operator->Operators
    public enum Operators {
        EQ,          //  EQ '=='
        NEQ,       //  NEQ '!='
        GTE,   //  GTE '>='
        LTE,      //  LTE '<='
        MOD,          //  MOD '%'
        LAND,      //  LAND '&&'
        LOR        //  LOR '||'
    }

    public interface Node{}
    public interface Statement extends Node {}
    public interface Callable extends Node {}
    public interface Expression extends Node {}
    public interface  Condition extends Node {}
    public interface  Identifier extends Node {}

    // start-> StartNode
    public record StartNode (ArrayList<Statement> body) implements Node {}

    // package_declaration -> PackageNode
    public record PackageNode (PotentiallyNestedIdentifierNode nested_identifier) implements Node, Statement {} //Maybe save this nested identifier into the symbol table ?

    // class_declaration -> ClassNode
    // class_scope[function_declaration (->FunctionNode<statement>), variable_declaration(->VariableNode<Statement>), assignment(-> AssignmentNode<statement>) -> ArrayList<Statement> body
    public record ClassNode (Identifier identifier_symbolRef, Access_Modifier access_modifier, ArrayList<Modifier> modifier, ArrayList<Statement> body) implements Node, Statement {}//TODO Identifier to symboltable

    // function_declaration -> FunctionNode
    // type -> String returnType_MaybeSymbolRef TODO Maybe shift this into the symbol table
    // Function_declaration_args-> ArrayList<ParameterNode>
    // function_scope [expressions(-> ExpressionNode<Statement>/ComparisonNode<Statement>) , assignment (-> AssignmentNode<Statement>), variable_declaration (-> VariableNode<Statement>), return_statement (->ExpressionNode<Statement>), block_scope (->BlockScopeNode<Statement>] ->ArrayList<Statement> body
    public record FunctionNode (Identifier identifier_symbolRef, Access_Modifier access_modifier, ArrayList<Modifier> modifier, String returnType_MaybeSymbolRef, ArrayList<ParameterNode> parameters, ArrayList<Statement> body) implements Node, Callable, Statement {} //TODO Identifier to symboltable

    //Function_declaration_arg[type(->SYMBOLTABLE), IDENTIFIER (->SYMBOLTABLE)-> ParameterNode
    // TODO lexer/parser: change IDENTIFIER* to IDENTIFIER!
    public record ParameterNode (Identifier identifier_symbolRef) implements Node {}// TODO Type and identifier to Symboltable


    // function_call -> CallNode
    public record CallNode(Identifier nested_identifier, ArrayList<Expression> values) implements Node, Statement, Callable {}// TODO Identifier to Symboltable?


    // if_statement -> IfNode
    //else_statement [expressions (->ComparisonNode<Expression>/ExpressionNode<Expression>), function_scope (->ArrayList<Statement>)]->IfNode
    public record IfNode(Expression expression, ArrayList<Statement> statements) implements  Node, Condition, Statement {}

    //else_statement -> ElseNode
    //else_statement [function_scope (->ArrayList<Statement>)]->ElseNode
    public record ElseNode(ArrayList<Statement> statements) implements Node, Condition, Statement {}

    // block_scope -> BlockScopeNode
    // block_scope [if_statement (-> IfNode<Condition>)/if_else_statement(->IfNode<Condition>, ElseNode<Condition>)]-> ArrayList<Condition> conditions
    public record BlockScopeNode(ArrayList<Condition> conditions) implements Node, Statement {}

    // variable_declaration -> VariableNode
    // variable_declaration [primitive_type(->SYMBOLTABLE), potentially_nested_identifier (->Identifier symbolRef)] -> VariableNode TODO exclude potentially_nested_identifier -> Just Identifier. Also maybe just do a ref to symboltable here.
    // TODO Also make sure the primitive Types are put into the symboltable? Because now we are using this Node to store the Value as the Identifier. -> Maybe shift to using ValueNode again, if that doesnt make sense!
    public record VariableNode ( Identifier value) implements Node, Callable, Statement {} // TODO Type and Identifier to Symboltable

    // potentially_nested_identifier -> PotentiallyNestedIdentifierNode
    // potentially_nested_identifier[Identifier (->ArrayList<Identifier> nested_identifier ) -> PotentiallyNestedIdentifierNode TODO Maybe this can be solved with symbol tables. As to somehow shift it to there. Or an ArrayList<SymbolRef>
    public record PotentiallyNestedIdentifierNode(ArrayList<Identifier> nested_identifier) implements Node, Callable, Expression, Identifier{}

    //IDENTIFIER -> IdentifierNode TODO This should be a ref to symbol tables
    public record IdentifierNode(String identifier_symbolRef) implements  Node, Callable, Expression, Identifier{}

    // assignment -> AssignmentNode
    // assignment[variable_declaration(->VariableNode<Callable>), potentially_nested_identifier (-> PotentiallyNestedIdentifierNode<Callable>), expressions (->ComparisonNode<Expression>)] -> AssignmentNode
    public record AssignmentNode(Callable variable, Expression value) implements Node, Statement {}

    // (expresions) -> ComparisonNode
    // expressions[expression (-> ExpressionNode), expression_operator(->Operators), expression (->Expression)-> ComparisonNode
    // NOTE: This will only be the case if the expressions is a Comparison. If it is a single expression, this is not applicable, rather use a simple ExpressionNode for this then.
    public record ComparisonNode(ExpressionNode expression1, Operators operator, Expression expression2) implements Node, Expression, Callable {}

    // expression -> ExpressionNode
    // (epressions) -> ExpressionNode
    // expression[function_call(->CallNode<Callable>), potentially_nested_identifier(->PotentiallyNestedIdentifierNode<Callable>), DECIMAL+INTEGER+IDENTIFIER+STRING (->VariableNode<Callable>)] -> ExpressionNode
    // NOTE: When expressions only got one Argument, then this ExpressionNode will cover this case.
    public record ExpressionNode(Callable callable) implements Node , Expression, Statement{}

    // return_statement ->ReturnNode
    // return_statement[ expressions(ComparisonNode<Expression>/ExpressionNode<Expression>)]->ReturnNode;
    public record ReturnNode(Expression value) implements Node, Statement {}
}
