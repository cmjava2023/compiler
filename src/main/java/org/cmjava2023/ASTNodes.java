package org.cmjava2023;

import java.util.ArrayList;

public class ASTNodes {

    public enum Modifier {
        PACKAGE,
        PUBLIC,
        PRIVATE,
        PROTECTED,
        STATIC,
        FINAL,
        ABSTRACT,
        TRANSIENT,
        SYNCHRONIZED,
        VOLATILE
    }

    public interface Node{}
    public interface Statement extends Node {}

    public record StartNode (ArrayList<Statement> body) implements Node {}
    public record PackageNode (String identifier) implements Node, Statement {}
    public record ClassNode (String identifier, ArrayList<Modifier> modifier, ArrayList<Statement> body) implements Node, Statement {}
    public record FunctionNode (String identifier, ArrayList<Modifier> modifier, String returnType, ParameterNode parameters, ArrayList<Statement> body) implements Node, Statement {}
    public record ParametersNode (ArrayList<ParameterNode> parameters) implements Node {}
    public record ParameterNode (String type, String identifier) implements Node {}
    public record FunctionCallNode(String identifier, ArrayList<ValueNode> values) implements Node, Statement {}
    public record ValueNode(String value) implements Node {}
}
