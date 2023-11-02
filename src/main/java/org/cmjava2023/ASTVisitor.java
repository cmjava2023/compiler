package org.cmjava2023;

import org.antlr.v4.runtime.tree.ParseTree;
import org.cmjava2023.generated_from_antlr.MainAntlrParser;
import org.cmjava2023.generated_from_antlr.MainAntlrBaseVisitor;

import java.util.ArrayList;

public class ASTVisitor extends MainAntlrBaseVisitor<ASTNodes.Node> {
    public ASTNodes.Node visitStart(MainAntlrParser.StartContext ctx) {
        ArrayList<ASTNodes.Statement> statementList = new ArrayList<>();
        for (ParseTree tree : ctx.children) {
            statementList.add((ASTNodes.Statement) visit(tree.getChild(0)));
        }

        return new ASTNodes.StartNode(statementList);
    }

    public ASTNodes.Node visitPackage_declaration(MainAntlrParser.Package_declarationContext ctx) {
        return new ASTNodes.PackageNode(ctx.POTENTIALLY_NESTED_IDENTIFIER().getText());
    }

    public ASTNodes.Node visitClass_declaration(MainAntlrParser.Class_declarationContext ctx) {
        ArrayList<ASTNodes.Statement> statementList = new ArrayList<>();
        for (ParseTree tree : ctx.class_body().children) {
            statementList.add((ASTNodes.Statement) visit(tree));
        }
        ArrayList<ASTNodes.Modifier> modifiers = new ArrayList<>();
        modifiers.add(ASTNodes.Modifier.valueOf(ctx.ACCESS_MODIFIER().getText().toUpperCase()));

        return new ASTNodes.ClassNode(ctx.IDENTIFIER().getText(), modifiers, statementList);
    }

    public ASTNodes.Node visitFunction_declaration(MainAntlrParser.Function_declarationContext ctx) {
        ArrayList<ASTNodes.Statement> statementList = new ArrayList<>();
        for (ParseTree tree : ctx.function_declaration_body().children) {
            statementList.add((ASTNodes.Statement) visit(tree.getChild(0)));
        }

        ArrayList<ASTNodes.ParameterNode> parameters = new ArrayList<>();
        for (ParseTree tree : ctx.function_declaration_args().children) {
            parameters.add((ASTNodes.ParameterNode) visit(tree));
        }

        ArrayList<ASTNodes.Modifier> modifiers = new ArrayList<>();
        modifiers.add(ASTNodes.Modifier.valueOf(ctx.ACCESS_MODIFIER().getText().toUpperCase()));
        modifiers.add(ASTNodes.Modifier.valueOf(ctx.INSTANCE_MODIFIER().getText().toUpperCase()));

        return new ASTNodes.FunctionNode(ctx.IDENTIFIER().getText(), modifiers, ctx.TYPE().getText(), parameters, statementList);
    }

    public ASTNodes.Node visitFunction_declaration_arg(MainAntlrParser.Function_declaration_argContext ctx) {
        return new ASTNodes.ParameterNode(ctx.TYPE().getText(), ctx.IDENTIFIER().getText());
    }

    public ASTNodes.Node visitFunction_call(MainAntlrParser.Function_callContext ctx) {
        ArrayList<ASTNodes.ValueNode> values = new ArrayList<>();
        for (ParseTree tree : ctx.function_args().children) {
            values.add((ASTNodes.ValueNode) visit(tree));
        }

        return new ASTNodes.FunctionCallNode(ctx.POTENTIALLY_NESTED_IDENTIFIER().getText(), values);
    }

    public ASTNodes.Node visitFunction_arg(MainAntlrParser.Function_argContext ctx) {
        return new ASTNodes.ValueNode(ctx.STRING().getText());
    }
}
