package org.cmjava2023.ast;

import org.cmjava2023.symboltable.*;
import org.jetbrains.annotations.Nullable;

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
    //expression_operator
    // bit_comparison_operator
    public enum ComparisonOperator implements Operator {
        EQ,          //  EQ '=='
        NEQ,       //  NEQ '!='
        GTE,   //  GTE '>='
        LTE,      //  LTE '<='
        MOD,          //  MOD '%'
        LAND,      //  LAND '&&'
        LOR,        //  LOR '||'
        DIAMOND_OPEN, // DIAMOND_CLOSE '<'
        DIAMOND_CLOSE, // DIAMOND_CLOSE '>'
        BAND, // BAND: '&';
        BOR, // BOR: '|';
        BXOR, // BXOR: '^';
        BIT_SHIFT_L, // BIT_SHIFT_L: '<<';
        BIT_SHIFT_R, // BIT_SHIFT_R: '>>';
        LOGICAL_SHIFT_R // BIT_SHIFT_R: '>>>';
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
    }

    // class_declaration -> ClassNode
    public record ClassNode(Clazz classSymbol,
                            ArrayList<Statement> body) implements Node, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // enum_declaration -> EnumNode
    public record EnumNode(Clazz enumClass,
                           ArrayList<Variable> constants) implements Node, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // function_declaration -> FunctionNode
    public record FunctionNode(Function functionSymbol,
                               ArrayList<ParameterNode> parameters,
                                @Nullable Clazz exception,
                               ArrayList<Statement> body) implements Node, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    //Function_declaration_arg-> ParameterNode
    public record ParameterNode(Parameter parameterSymbol) implements Node {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // function_call -> FunctionCallNode
    public record FunctionCallNode(Function function,
                                   ArrayList<Expression> argumentExpressions) implements Node, Statement, Expression {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // continue_statement->ContinueNode
    public record ContinueNode() implements Node, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // break_statement -> BreakNode
    public record BreakNode() implements Node, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    public record IfBlockNode(ArrayList<IfNode> ifNodes, ElseNode elseNode) implements Node, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // if_statement -> IfNode
    public record IfNode(Expression expression,
                         ArrayList<Statement> statements) implements Node, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // else_statement -> ElseNode
    public record ElseNode(
            ArrayList<Statement> statements) implements Node, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // variable_declaration -> VariableNode
    public record VariableNode(
            Variable variableSymbol) implements Node, Statement, VariableUsage {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // assignment -> (VariableAssignmentNode)
    // Note: The field variable can be Null, if this variable is not declared.
    public record VariableAssignmentNode(Variable variable,
                                         Expression expression) implements Node, Statement, VariableUsage {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // assignment -> (ParameterAssignmentNode)
    public record ParameterAssignmentNode(Parameter variable,
                                          Expression value) implements Node, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // {Any primitive type} -> ValueNode
    public record ValueNode<T>(T value, BuiltInType builtInType) implements Node, Expression {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // For concatinating Expressions.
    // expression expression_concatinator expression
    public record InfixNode(Expression leftExpression, InfixOperator operator,
                            Expression rightExpression) implements Node, Expression {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // To give an expression a prefix.
    // (numerical_prefix | logical_prefix) expressions
    public record UnaryPrefixNode(PrefixOperator operator,
                                  Expression variableCallNode) implements Node, Expression {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // To give an expression a suffix.
    // It is also an expression, because it can stay alone! i++ or i--
    // and is not an assignment. Also, there is no other case then an increment or decrement to use this.
    // Thus, this node could potentially also be called Increment/Decrement Node
    //expression expression_suffix
    public record UnarySuffixNode(SuffixOperator operator,
                                  Expression variableCallNode) implements Node, Expression, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // PAREN_OPEN expression PAREN_CLOSE -> ParenthesesNode
    // used to give individual expressions their scope.
    public record ParenthesesNode(
            Expression Expression) implements Node, Expression {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // casting -> CastNode
    public record CastNode(org.cmjava2023.symboltable.Type type,
                           Expression expression,
                           Scope scope) implements Node, Expression {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // array_expression -> ArrayInstantiationNode
    public record ArrayInstantiationWithValuesNode(
            ArrayList<Expression> expressions) implements Node, Expression {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // instantiation->(ArrayInstantiationNode)
    public record ArrayInstantiationNode(org.cmjava2023.symboltable.Type type,
                                         ArrayList<Integer> dimensionSizes) implements Node, Expression {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // both array and elementIndicesAccessed can be null if trying to access this doesnt work.
    // access_index -> ArrayAccessNode
    public record ArrayAccessNode(Variable array,
                                  ArrayList<Integer> elementIndicesAccessed) implements Node, Expression {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // instantiation -> (ObjectInstantiationNode)
    public record ObjectInstantiationNode(
            org.cmjava2023.symboltable.Type type) implements Node, Expression {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // potentially_nested_identifier -> PotentiallyNestedIdentifierNode
    public record NestedIdentifierNode(
            ArrayList<String> nestedIdentifier) implements Node, Expression {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // (expressions) -> ComparisonNode
    // expressions[expression (-> ExpressionNode), expression_operator(->Operators), expression (->Expression)-> ComparisonNode
    // NOTE: This will only be the case if the expressions is a Comparison. If it is a single expression, this is not applicable, rather use a simple ExpressionNode for this then.
    public record ComparisonNode(Expression leftExpression,
                                 ComparisonOperator comparisonOperator,
                                 Expression rightExpression) implements Node, Expression {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    public record VariableCallNode(
            Variable symbol) implements Node, Expression {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    public record ParameterCallNode(
            Parameter parameter) implements Node, Expression {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    public record VariableFieldCallNode(
            Variable variable, Variable field) implements Node, Expression {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    public record VariableFunctionCallNode(
            Variable variable, Function function) implements Node, Expression {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // ATTENTION! This can also have no Expression, thus being null
    // return_statement ->ReturnNode
    // expressions->Expression;
    public record ReturnNode(@Nullable Expression value) implements Node, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // for_loop -> (ForLoopNode)
    public record ForLoopNode(VariableUsage loopVariable,
                              Expression termination,
                              Expression increment,
                              ArrayList<Statement> body) implements Node, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // for_loop -> (ForEachLoopNode)
    public record ForEachLoopNode(VariableUsage variableDeclaration,
                              NestedIdentifierNode values,
                              ArrayList<Statement> body) implements Node, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // while_loop -> WhileLoopNode
    public record WhileLoopNode(Expression expression,
                                ArrayList<Statement> body) implements Node, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // do_while_loop -> DoWhileLoopNode
    public record DoWhileLoopNode(Expression expression,
                                  ArrayList<Statement> body) implements Node, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // switch_statement ->SwitchNode
    public record SwitchNode(Expression switchEx, ArrayList<CaseNode> caseNodes,
                             ArrayList<Statement> defaultStatements) implements Node, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    //switch_scope-> [caseNode]
    public record CaseNode(Expression caseEx,
                           ArrayList<Statement> body) implements Node {
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

    public record ArrayTypeNode(String type,
                                int dimensions) implements Node, Type {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }

        @Override
        public String getType() {
            return type;
        }
    }

    // It is just a helper class for the symbol tables. Nothing which stays in the AST.
    public record OperatorNode(Operator operator) implements Node {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }
}
