package org.cmjava2023;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.cmjava2023.generated_from_antlr.MainAntlrParser;

import java.util.ArrayList;
import java.util.List;

public class ASTVisitor extends MainAntlrBaseVisitor<ASTNodes.Node> {
    public ASTNodes.Node visitStart(MainAntlrParser.StartContext ctx) {
        ArrayList<ASTNodes.Statement> statementlist = new ArrayList<>();
        for (ParseTree tree : ctx.children) {
            statementlist.add((ASTNodes.Statement) visit(tree));
        }

        return new ASTNodes.StartNode(statementlist);
    }

    public ASTNodes.Node visitPackage_declaration(MainAntlrParser.Package_declarationContext ctx) {
        return new ASTNodes.PackageNode(ctx.POTENTIALLY_NESTED_IDENTIFIER().getText());
    }

    public ASTNodes.Node visitClass_declaration(MainAntlrParser.Class_declarationContext ctx) {
        ArrayList<ASTNodes.Statement> statementlist = new ArrayList<>();
        for (ParseTree tree : ctx.class_body().children) {
            statementlist.add((ASTNodes.Statement) visit(tree));
        }
        ArrayList<ASTNodes.Modifier> modifiers = new ArrayList<>();
        modifiers.add(ASTNodes.Modifier.valueOf(ctx.ACCESS_MODIFIER().getText().toUpperCase()));

        return new ASTNodes.ClassNode(ctx.IDENTIFIER().getText(), modifiers, statementlist);
    }

    public ASTNodes.Node visitFunction_declaration(MainAntlrParser.Function_declarationContext ctx) {
        ArrayList<ASTNodes.Statement> statementlist = new ArrayList<>();
        for (ParseTree tree : ctx.function_declaration_body().children) {
            statementlist.add((ASTNodes.Statement) visit(tree));
        }
        ArrayList<ASTNodes.Modifier> modifiers = new ArrayList<>();
        modifiers.add(ASTNodes.Modifier.valueOf(ctx.ACCESS_MODIFIER().getText().toUpperCase()));
        modifiers.add(ASTNodes.Modifier.valueOf(ctx.INSTANCE_MODIFIER().getText().toUpperCase()));

        return new ASTNodes.FunctionNode(ctx.IDENTIFIER().getText(), modifiers, ctx.TYPE().getText(), (ASTNodes.ParameterNode) visit(ctx.function_declaration_args()), statementlist);
    }

    public ASTNodes.Node visitFunction_declaration_args(MainAntlrParser.Function_declaration_argsContext ctx) {
        ArrayList<ASTNodes.ParameterNode> parameters = new ArrayList<>();
        List<TerminalNode> types = ctx.TYPE();
        List<TerminalNode> identifiers = ctx.IDENTIFIER();
        for (int i = 0; i < ctx.TYPE().size(); i++) {
            parameters.add(new ASTNodes.ParameterNode(types.get(i).getText(), identifiers.get(i).getText()));
        }
        return new ASTNodes.ParametersNode(parameters);
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
