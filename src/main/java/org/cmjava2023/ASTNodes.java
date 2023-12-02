package org.cmjava2023;

import org.cmjava2023.symboltable.Clazz;
import org.cmjava2023.symboltable.Function;
import org.cmjava2023.symboltable.Parameter;
import org.cmjava2023.symboltable.Variable;

public class ASTNodes {

    public enum AccessModifier {
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
        LOR,        //  LOR '||'
        DIAMOND_OPEN, // DIAMOND_CLOSE '<'
        DIAMOND_CLOSE // DIAMOND_CLOSE '>'
    }

    public interface Node {
    }

    public interface Statement extends Node {
    }

    public interface Callable extends Node {
    }

    public interface Expression extends Node {
    }

    public interface Condition extends Node {
    }

    public interface Identifier extends Node {
    }

    public interface Type extends Node {
    }

    // start-> StartNode
    public record StartNode(Statement[] body) implements Node {
    }

    // package_declaration -> PackageNode
    public record PackageNode(
            String[] nestedIdentifier) implements Node, Statement {
    } //Maybe save this nested identifier into the symbol table ?

    // class_declaration -> ClassNode
    // class_scope[function_declaration (->FunctionNode<statement>), variable_declaration(->VariableNode<Statement>), assignment(-> AssignmentNode<statement>) -> Statement[] body
    public record ClassNode(Clazz classSymbol,
                            Statement[] body) implements Node, Statement {
    }

    // function_declaration -> FunctionNode
    // type -> Identifier -> SymbolRef
    // Function_declaration_args-> ParameterNode[]
    // function_scope [expressions(-> ExpressionNode<Statement>/ComparisonNode<Statement>) , assignment (-> AssignmentNode<Statement>), variable_declaration (-> VariableNode<Statement>), return_statement (->ExpressionNode<Statement>), block_scope (->BlockScopeNode<Statement>] ->Statement[] body
    public record FunctionNode(Function functionSymbol,
                               ParameterNode[] parameters,
                               Statement[] body) implements Node, Callable, Statement {
    }

    //Function_declaration_arg[type(->symbolTable), IDENTIFIER (->symbolTable)-> ParameterNode
    public record ParameterNode(Parameter parameterSymbol) implements Node {
    }

    // function_call -> CallNode
    public record FunctionCallNode(String[] nestedIdentifier,
                                   Expression[] values) implements Node, Statement, Callable {
    }

    // if_statement -> IfNode
    //else_statement [expressions (->ComparisonNode<Expression>/ExpressionNode<Expression>), function_scope (->Statement[])]->IfNode
    public record IfNode(Expression expression,
                         Statement[] statements) implements Node, Condition, Statement {
    }

    //else_statement -> ElseNode
    //else_statement [function_scope (->Statement[])]->ElseNode
    public record ElseNode(
            Statement[] statements) implements Node, Condition, Statement {
    }

    // block_scope -> BlockScopeNode
    // block_scope [if_statement (-> IfNode<Condition>)/if_else_statement(->IfNode<Condition>, ElseNode<Condition>)]-> Condition[] conditions
    public record BlockScopeNode(
            Condition[] conditions) implements Node, Statement {
    }

    // variable_declaration -> VariableNode
    // variable_declaration [primitive_type(->symbolTable), potentially_nested_identifier (->Identifier symbolRef)] -> VariableNode
    // assignment[variable_declaration(->PotentiallyNestedIdentifierNode<Identifier>), potentially_nested_identifier (-> PotentiallyNestedIdentifierNode<Identifier>), expressions (->ComparisonNode<Expression>)] -> VariableNode
    public record VariableNode(Variable variableSymbol,
                               Expression value) implements Node, Callable, Statement {
    }

    public record VariableAssigmentNode(String[] variableName,
                                        Expression value) implements Node, Callable, Statement {
    }

    // Just a proper value.
    // | DECIMAL | INTEGER | IDENTIFIER | STRING |
    public record ValueNode(String value) implements Node, Expression {
    }

    // potentially_nested_identifier -> PotentiallyNestedIdentifierNode
    // potentially_nested_identifier[Identifier (->String[] nested_identifier ) -> PotentiallyNestedIdentifierNode
    public record NestedIdentifierNode(
            String[] nestedIdentifier) implements Node, Callable, Expression, Identifier {
    }

    // (expressions) -> ComparisonNode
    // expressions[expression (-> ExpressionNode), expression_operator(->Operators), expression (->Expression)-> ComparisonNode
    // NOTE: This will only be the case if the expressions is a Comparison. If it is a single expression, this is not applicable, rather use a simple ExpressionNode for this then.
    public record ComparisonNode(Expression expression1, Operators operator,
                                 Expression expression2) implements Node, Expression, Callable {
    }

    // expression -> ExpressionNode
    // (expressions) -> ExpressionNode
    // expression[function_call(->CallNode<Callable>), potentially_nested_identifier(->PotentiallyNestedIdentifierNode<Callable>), DECIMAL+INTEGER+IDENTIFIER+STRING (->VariableNode<Callable>)] -> ExpressionNode
    // NOTE: When expressions only got one Argument, then this ExpressionNode will cover this case.
    public record ExpressionNode(
            Callable callable) implements Node, Expression, Statement {
    }

    public record IdentifierNode(
            String name) implements Node, Callable, Expression, Identifier {
    }

    // return_statement ->ReturnNode
    // return_statement[ expressions(ComparisonNode<Expression>/ExpressionNode<Expression>)]->ReturnNode;
    public record ReturnNode(Expression value) implements Node, Statement {
    }

    public record TypeNode(String type) implements Node, Type {
    }

    public record ArrayTypeNode(String type) implements Node, Type {
    }
}
