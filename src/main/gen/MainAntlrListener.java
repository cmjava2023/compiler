// Generated from D:/Git Repos/compiler/src/main/antlr/MainAntlr.g4 by ANTLR 4.13.1

package org.cmjava2023.generated_from_antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MainAntlrParser}.
 */
public interface MainAntlrListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(MainAntlrParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(MainAntlrParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(MainAntlrParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(MainAntlrParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(MainAntlrParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(MainAntlrParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#package_declaration}.
	 * @param ctx the parse tree
	 */
	void enterPackage_declaration(MainAntlrParser.Package_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#package_declaration}.
	 * @param ctx the parse tree
	 */
	void exitPackage_declaration(MainAntlrParser.Package_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#class_declaration}.
	 * @param ctx the parse tree
	 */
	void enterClass_declaration(MainAntlrParser.Class_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#class_declaration}.
	 * @param ctx the parse tree
	 */
	void exitClass_declaration(MainAntlrParser.Class_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#class_body}.
	 * @param ctx the parse tree
	 */
	void enterClass_body(MainAntlrParser.Class_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#class_body}.
	 * @param ctx the parse tree
	 */
	void exitClass_body(MainAntlrParser.Class_bodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#function_declaration}.
	 * @param ctx the parse tree
	 */
	void enterFunction_declaration(MainAntlrParser.Function_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#function_declaration}.
	 * @param ctx the parse tree
	 */
	void exitFunction_declaration(MainAntlrParser.Function_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#function_declaration_args}.
	 * @param ctx the parse tree
	 */
	void enterFunction_declaration_args(MainAntlrParser.Function_declaration_argsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#function_declaration_args}.
	 * @param ctx the parse tree
	 */
	void exitFunction_declaration_args(MainAntlrParser.Function_declaration_argsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#function_declaration_body}.
	 * @param ctx the parse tree
	 */
	void enterFunction_declaration_body(MainAntlrParser.Function_declaration_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#function_declaration_body}.
	 * @param ctx the parse tree
	 */
	void exitFunction_declaration_body(MainAntlrParser.Function_declaration_bodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#function_call}.
	 * @param ctx the parse tree
	 */
	void enterFunction_call(MainAntlrParser.Function_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#function_call}.
	 * @param ctx the parse tree
	 */
	void exitFunction_call(MainAntlrParser.Function_callContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#function_args}.
	 * @param ctx the parse tree
	 */
	void enterFunction_args(MainAntlrParser.Function_argsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#function_args}.
	 * @param ctx the parse tree
	 */
	void exitFunction_args(MainAntlrParser.Function_argsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#function_arg}.
	 * @param ctx the parse tree
	 */
	void enterFunction_arg(MainAntlrParser.Function_argContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#function_arg}.
	 * @param ctx the parse tree
	 */
	void exitFunction_arg(MainAntlrParser.Function_argContext ctx);
}