// Generated from D:/Desktop/Master of Science/1. Semester/Concepts of Programming Languages/GruppenProjekt/githubrepo/compiler/src/main/antlr/MainAntlr.g4 by ANTLR 4.13.1

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
	 * Visit a parse tree produced by {@link MainAntlrParser#global_scope}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobal_scope(MainAntlrParser.Global_scopeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#class_scope}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_scope(MainAntlrParser.Class_scopeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#function_scope}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_scope(MainAntlrParser.Function_scopeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#block_scope}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock_scope(MainAntlrParser.Block_scopeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#expressions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressions(MainAntlrParser.ExpressionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#variable_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_declaration(MainAntlrParser.Variable_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(MainAntlrParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(MainAntlrParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#expression_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression_operator(MainAntlrParser.Expression_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#numerical_comparison_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumerical_comparison_operator(MainAntlrParser.Numerical_comparison_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#logical_comparison_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogical_comparison_operator(MainAntlrParser.Logical_comparison_operatorContext ctx);
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
	 * Visit a parse tree produced by {@link MainAntlrParser#function_declaration_arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_declaration_arg(MainAntlrParser.Function_declaration_argContext ctx);
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
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#if_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_statement(MainAntlrParser.If_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#else_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElse_statement(MainAntlrParser.Else_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#if_else_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_else_statement(MainAntlrParser.If_else_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#return_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_statement(MainAntlrParser.Return_statementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#potentially_nested_identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPotentially_nested_identifier(MainAntlrParser.Potentially_nested_identifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(MainAntlrParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#integral_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegral_type(MainAntlrParser.Integral_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#floating_point_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloating_point_type(MainAntlrParser.Floating_point_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#numeric_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumeric_type(MainAntlrParser.Numeric_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#primitive_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitive_type(MainAntlrParser.Primitive_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#reference_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReference_type(MainAntlrParser.Reference_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#class_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_type(MainAntlrParser.Class_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#type_arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_arguments(MainAntlrParser.Type_argumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#type_argument_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_argument_list(MainAntlrParser.Type_argument_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#type_argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_argument(MainAntlrParser.Type_argumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#wildcard}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWildcard(MainAntlrParser.WildcardContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#type_variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_variable(MainAntlrParser.Type_variableContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#array_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray_type(MainAntlrParser.Array_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MainAntlrParser#access_modifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccess_modifier(MainAntlrParser.Access_modifierContext ctx);
}