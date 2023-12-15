package org.cmjava2023.ast;

import org.cmjava2023.symboltable.*;

import java.util.ArrayList;

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

    public interface Operator {
    }

    //Numerical and logical operators
    //expression_operator->Operators
    public enum ComparisonOperator implements Operator {
        EQ,          //  EQ '=='
        NEQ,       //  NEQ '!='
        GTE,   //  GTE '>='
        LTE,      //  LTE '<='
        MOD,          //  MOD '%'
        LAND,      //  LAND '&&'
        LOR,        //  LOR '||'
        DIAMOND_OPEN, // DIAMOND_CLOSE '<'
        DIAMOND_CLOSE // DIAMOND_CLOSE '>'
        ,
        BAND,
        BOR,
        BXOR,
        BIT_SHIFT_L,
        BIT_SHIFT_R
    }

    public enum InfixOperator implements Operator {
        PLUS, DIVISION, MULTIPLICATION, MINUS, MOD, DOT
    }

    public enum PrefixOperator implements Operator {
        PLUS, MINUS, NOT, NOTNOT
    }

    public enum SuffixOperator implements Operator {
        DEC, INC
    }

    public interface Node {
        ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor);
    }

    public interface Statement extends Node {
    }

    public interface Expression extends Node {
    }

    public interface Condition extends Node {
    }

    public interface VariableUsage extends Node {
    }

    public interface Type extends Node {
        String getType();
    }

    // start-> StartNode
    public record StartNode(ArrayList<Statement> body) implements Node {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // package_declaration -> PackageNode
    public record PackageNode(
            ArrayList<String> nestedIdentifier) implements Node, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    } //Maybe save this nested identifier into the symbol table ?

    // class_declaration -> ClassNode
    // class_scope[function_declaration (->FunctionNode<statement>), variable_declaration(->VariableNode<Statement>), assignment(-> AssignmentNode<statement>) -> Statement[] body
    public record ClassNode(Clazz classSymbol,
                            ArrayList<Statement> body) implements Node, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // function_declaration -> FunctionNode
    // type -> Identifier -> SymbolRef
    // Function_declaration_args-> ParameterNode[]
    // function_scope [expressions(-> ExpressionNode<Statement>/ComparisonNode<Statement>) , assignment (-> AssignmentNode<Statement>), variable_declaration (-> VariableNode<Statement>), return_statement (->ExpressionNode<Statement>), block_scope (->BlockScopeNode<Statement>] ->Statement[] body
    public record FunctionNode(Function functionSymbol,
                               ArrayList<ParameterNode> parameters,
                               ArrayList<Statement> body) implements Node, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    //Function_declaration_arg[type(->symbolTable), IDENTIFIER (->symbolTable)-> ParameterNode
    public record ParameterNode(Parameter parameterSymbol) implements Node {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // function_call -> CallNode
    public record RawFunctionCallNode(ArrayList<String> nestedIdentifier,
                                      ArrayList<Expression> values,
                                      Scope scope) implements Node, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    public record FunctionCallNode(Function function,
                                   ArrayList<Expression> values) implements Node, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // if_statement -> IfNode
    //else_statement [expressions (->ComparisonNode<Expression>/ExpressionNode<Expression>), function_scope (->Statement[])]->IfNode
    public record IfNode(Expression expression,
                         ArrayList<Statement> statements) implements Node, Condition, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    //else_statement -> ElseNode
    //else_statement [function_scope (->Statement[])]->ElseNode
    public record ElseNode(
            ArrayList<Statement> statements) implements Node, Condition, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // block_scope -> BlockScopeNode
    // block_scope [if_statement (-> IfNode<Condition>)/if_else_statement(->IfNode<Condition>, ElseNode<Condition>)]-> Condition[] conditions
    public record BlockScopeNode(
            ArrayList<Condition> conditions) implements Node, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // variable_declaration -> VariableNode
    // variable_declaration [primitive_type(->symbolTable), potentially_nested_identifier (->Identifier symbolRef)] -> VariableNode
    // assignment[variable_declaration(->PotentiallyNestedIdentifierNode<Identifier>), potentially_nested_identifier (-> PotentiallyNestedIdentifierNode<Identifier>), expressions (->ComparisonNode<Expression>)] -> VariableNode
    public record VariableNode(
            Variable variableSymbol) implements Node, Statement, VariableUsage {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    public record VariableAssigmentNode(Variable variable,
                                        Expression expression) implements Node, Statement, VariableUsage {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    public record ParameterAssigmentNode(Parameter variable,
                                         Expression value) implements Node, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    public record ValueNode<T>(T value) implements Node, Expression {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    public record InfixNode(Expression leftExpression, InfixOperator operator,
                            Expression rightExpression) implements Node, Expression {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    public record UnaryPrefixNode(PrefixOperator operator,
                                  Expression Expression) implements Node, Expression {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    public record UnarySuffixNode(SuffixOperator operator,
                                  Expression Expression) implements Node, Expression, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    public record ParenthesesNode(
            Expression Expression) implements Node, Expression {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    public record CastNode(org.cmjava2023.symboltable.Type type,
                           Expression expression) implements Node, Expression {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    public record ArrayInstantiationWithValuesNode(
            ArrayList<Expression> expressions) implements Node, Expression {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    public record ArrayInstantiationNode(org.cmjava2023.symboltable.Type type,
                                         ArrayList<Integer> dimensionSizes) implements Node, Expression {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    public record ArrayAccessNode(Variable array,
                                  ArrayList<Integer> elementIndicesAccessed) implements Node, Expression {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    public record ObjectInstantiationNode(
            org.cmjava2023.symboltable.Type type) implements Node, Expression {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // potentially_nested_identifier -> PotentiallyNestedIdentifierNode
    // potentially_nested_identifier[Identifier (->String[] nested_identifier ) -> PotentiallyNestedIdentifierNode
    public record NestedIdentifierNode(
            ArrayList<String> nestedIdentifier) implements Node, Expression {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // (expressions) -> ComparisonNode
    // expressions[expression (-> ExpressionNode), expression_operator(->Operators), expression (->Expression)-> ComparisonNode
    // NOTE: This will only be the case if the expressions is a Comparison. If it is a single expression, this is not applicable, rather use a simple ExpressionNode for this then.
    public record ComparisonNode(Expression expression1,
                                 ComparisonOperator comparisonOperator,
                                 Expression expression2) implements Node, Expression {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    public record IdentifierNode(
            String name) implements Node, Expression {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // return_statement ->ReturnNode
    // return_statement[ expressions(ComparisonNode<Expression>/ExpressionNode<Expression>)]->ReturnNode;
    public record ReturnNode(Expression value) implements Node, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    public record ForLoopNode(VariableUsage loopVariable,
                              Expression termination,
                              Expression increment,
                              ArrayList<Statement> body) implements Node, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    public record WhileLoopNode(Expression expression,
                                ArrayList<Statement> body) implements Node, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    public record DoWhileLoopNode(Expression expression,
                                  ArrayList<Statement> body) implements Node, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    public record TypeNode(String type) implements Node, Type {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }

        @Override
        public String getType() {
            return type;
        }
    }

    public record ArrayTypeNode(String type) implements Node, Type {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }

        @Override
        public String getType() {
            return type;
        }
    }

    public record OperatorNode(Operator operator) implements Node {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }
}
