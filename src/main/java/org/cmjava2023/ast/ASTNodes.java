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
        ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor);
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
                               ArrayList<Statement> body) implements Node, Callable, Statement {
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
                                      Scope scope) implements Node, Statement, Callable {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    public record FunctionCallNode(Function function,
                                      ArrayList<Expression> values) implements Node, Statement, Callable {
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
    public record VariableNode(Variable variableSymbol,
                               Expression value) implements Node, Callable, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    public record VariableAssigmentNode(Variable variable,
                                        Expression value) implements Node, Callable, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    public record ParameterAssigmentNode(Parameter variable,
                                        Expression value) implements Node, Callable, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // Just a proper value.
    // | DECIMAL | INTEGER | IDENTIFIER | STRING |
    public record ValueNode(String value) implements Node, Expression {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // potentially_nested_identifier -> PotentiallyNestedIdentifierNode
    // potentially_nested_identifier[Identifier (->String[] nested_identifier ) -> PotentiallyNestedIdentifierNode
    public record NestedIdentifierNode(
            ArrayList<String> nestedIdentifier) implements Node, Callable, Expression, Identifier {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // (expressions) -> ComparisonNode
    // expressions[expression (-> ExpressionNode), expression_operator(->Operators), expression (->Expression)-> ComparisonNode
    // NOTE: This will only be the case if the expressions is a Comparison. If it is a single expression, this is not applicable, rather use a simple ExpressionNode for this then.
    public record ComparisonNode(Expression expression1, Operators operator,
                                 Expression expression2) implements Node, Expression, Callable {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    // expression -> ExpressionNode
    // (expressions) -> ExpressionNode
    // expression[function_call(->CallNode<Callable>), potentially_nested_identifier(->PotentiallyNestedIdentifierNode<Callable>), DECIMAL+INTEGER+IDENTIFIER+STRING (->VariableNode<Callable>)] -> ExpressionNode
    // NOTE: When expressions only got one Argument, then this ExpressionNode will cover this case.
    public record ExpressionNode(
            Callable callable) implements Node, Expression, Statement {
        public ASTNodes.Node accept(ASTTraverser<ASTNodes.Node> visitor) {
            return visitor.visit(this);
        }
    }

    public record IdentifierNode(
            String name) implements Node, Callable, Expression, Identifier {
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
}
