// Generated from D:/Git Repos/compiler/src/main/antlr/MainAntlr.g4 by ANTLR 4.13.1

package org.cmjava2023.generated_from_antlr;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MainAntlrParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MainAntlrVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(MainAntlrParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(MainAntlrParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(MainAntlrParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#package_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackage_declaration(MainAntlrParser.Package_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#class_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_declaration(MainAntlrParser.Class_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#class_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_body(MainAntlrParser.Class_bodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#function_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_declaration(MainAntlrParser.Function_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#function_declaration_args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_declaration_args(MainAntlrParser.Function_declaration_argsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#function_declaration_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_declaration_body(MainAntlrParser.Function_declaration_bodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#function_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_call(MainAntlrParser.Function_callContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#function_args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_args(MainAntlrParser.Function_argsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#function_arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_arg(MainAntlrParser.Function_argContext ctx);
}