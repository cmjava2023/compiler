// Generated from D:/Desktop/Master of Science/1. Semester/Concepts of Programming Languages/GruppenProjekt/githubrepo/compiler/src/main/antlr/MainAntlr.g4 by ANTLR 4.13.1

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
	 * Enter a parse tree produced by {@link MainAntlrParser#global_scope}.
	 * @param ctx the parse tree
	 */
	void enterGlobal_scope(MainAntlrParser.Global_scopeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#global_scope}.
	 * @param ctx the parse tree
	 */
	void exitGlobal_scope(MainAntlrParser.Global_scopeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#class_scope}.
	 * @param ctx the parse tree
	 */
	void enterClass_scope(MainAntlrParser.Class_scopeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#class_scope}.
	 * @param ctx the parse tree
	 */
	void exitClass_scope(MainAntlrParser.Class_scopeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#function_scope}.
	 * @param ctx the parse tree
	 */
	void enterFunction_scope(MainAntlrParser.Function_scopeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#function_scope}.
	 * @param ctx the parse tree
	 */
	void exitFunction_scope(MainAntlrParser.Function_scopeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#block_scope}.
	 * @param ctx the parse tree
	 */
	void enterBlock_scope(MainAntlrParser.Block_scopeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#block_scope}.
	 * @param ctx the parse tree
	 */
	void exitBlock_scope(MainAntlrParser.Block_scopeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#expressions}.
	 * @param ctx the parse tree
	 */
	void enterExpressions(MainAntlrParser.ExpressionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#expressions}.
	 * @param ctx the parse tree
	 */
	void exitExpressions(MainAntlrParser.ExpressionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#variable_declaration}.
	 * @param ctx the parse tree
	 */
	void enterVariable_declaration(MainAntlrParser.Variable_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#variable_declaration}.
	 * @param ctx the parse tree
	 */
	void exitVariable_declaration(MainAntlrParser.Variable_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(MainAntlrParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(MainAntlrParser.AssignmentContext ctx);
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
	 * Enter a parse tree produced by {@link MainAntlrParser#expression_operator}.
	 * @param ctx the parse tree
	 */
	void enterExpression_operator(MainAntlrParser.Expression_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#expression_operator}.
	 * @param ctx the parse tree
	 */
	void exitExpression_operator(MainAntlrParser.Expression_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#expression_concatinator}.
	 * @param ctx the parse tree
	 */
	void enterExpression_concatinator(MainAntlrParser.Expression_concatinatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#expression_concatinator}.
	 * @param ctx the parse tree
	 */
	void exitExpression_concatinator(MainAntlrParser.Expression_concatinatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#expression_suffix}.
	 * @param ctx the parse tree
	 */
	void enterExpression_suffix(MainAntlrParser.Expression_suffixContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#expression_suffix}.
	 * @param ctx the parse tree
	 */
	void exitExpression_suffix(MainAntlrParser.Expression_suffixContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#instantiation}.
	 * @param ctx the parse tree
	 */
	void enterInstantiation(MainAntlrParser.InstantiationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#instantiation}.
	 * @param ctx the parse tree
	 */
	void exitInstantiation(MainAntlrParser.InstantiationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#access_index}.
	 * @param ctx the parse tree
	 */
	void enterAccess_index(MainAntlrParser.Access_indexContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#access_index}.
	 * @param ctx the parse tree
	 */
	void exitAccess_index(MainAntlrParser.Access_indexContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#numerical_comparison_operator}.
	 * @param ctx the parse tree
	 */
	void enterNumerical_comparison_operator(MainAntlrParser.Numerical_comparison_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#numerical_comparison_operator}.
	 * @param ctx the parse tree
	 */
	void exitNumerical_comparison_operator(MainAntlrParser.Numerical_comparison_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#numerical_prefix}.
	 * @param ctx the parse tree
	 */
	void enterNumerical_prefix(MainAntlrParser.Numerical_prefixContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#numerical_prefix}.
	 * @param ctx the parse tree
	 */
	void exitNumerical_prefix(MainAntlrParser.Numerical_prefixContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#logical_prefix}.
	 * @param ctx the parse tree
	 */
	void enterLogical_prefix(MainAntlrParser.Logical_prefixContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#logical_prefix}.
	 * @param ctx the parse tree
	 */
	void exitLogical_prefix(MainAntlrParser.Logical_prefixContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#logical_comparison_operator}.
	 * @param ctx the parse tree
	 */
	void enterLogical_comparison_operator(MainAntlrParser.Logical_comparison_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#logical_comparison_operator}.
	 * @param ctx the parse tree
	 */
	void exitLogical_comparison_operator(MainAntlrParser.Logical_comparison_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#bit_comparison_operator}.
	 * @param ctx the parse tree
	 */
	void enterBit_comparison_operator(MainAntlrParser.Bit_comparison_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#bit_comparison_operator}.
	 * @param ctx the parse tree
	 */
	void exitBit_comparison_operator(MainAntlrParser.Bit_comparison_operatorContext ctx);
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
	 * Enter a parse tree produced by {@link MainAntlrParser#enum_declaration}.
	 * @param ctx the parse tree
	 */
	void enterEnum_declaration(MainAntlrParser.Enum_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#enum_declaration}.
	 * @param ctx the parse tree
	 */
	void exitEnum_declaration(MainAntlrParser.Enum_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#if_statement}.
	 * @param ctx the parse tree
	 */
	void enterIf_statement(MainAntlrParser.If_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#if_statement}.
	 * @param ctx the parse tree
	 */
	void exitIf_statement(MainAntlrParser.If_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#else_statement}.
	 * @param ctx the parse tree
	 */
	void enterElse_statement(MainAntlrParser.Else_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#else_statement}.
	 * @param ctx the parse tree
	 */
	void exitElse_statement(MainAntlrParser.Else_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#if_else_statement}.
	 * @param ctx the parse tree
	 */
	void enterIf_else_statement(MainAntlrParser.If_else_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#if_else_statement}.
	 * @param ctx the parse tree
	 */
	void exitIf_else_statement(MainAntlrParser.If_else_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#switch_statement}.
	 * @param ctx the parse tree
	 */
	void enterSwitch_statement(MainAntlrParser.Switch_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#switch_statement}.
	 * @param ctx the parse tree
	 */
	void exitSwitch_statement(MainAntlrParser.Switch_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#switch_scope}.
	 * @param ctx the parse tree
	 */
	void enterSwitch_scope(MainAntlrParser.Switch_scopeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#switch_scope}.
	 * @param ctx the parse tree
	 */
	void exitSwitch_scope(MainAntlrParser.Switch_scopeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#return_statement}.
	 * @param ctx the parse tree
	 */
	void enterReturn_statement(MainAntlrParser.Return_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#return_statement}.
	 * @param ctx the parse tree
	 */
	void exitReturn_statement(MainAntlrParser.Return_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#break_statement}.
	 * @param ctx the parse tree
	 */
	void enterBreak_statement(MainAntlrParser.Break_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#break_statement}.
	 * @param ctx the parse tree
	 */
	void exitBreak_statement(MainAntlrParser.Break_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#continue_statement}.
	 * @param ctx the parse tree
	 */
	void enterContinue_statement(MainAntlrParser.Continue_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#continue_statement}.
	 * @param ctx the parse tree
	 */
	void exitContinue_statement(MainAntlrParser.Continue_statementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(MainAntlrParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(MainAntlrParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(MainAntlrParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(MainAntlrParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#integral_type}.
	 * @param ctx the parse tree
	 */
	void enterIntegral_type(MainAntlrParser.Integral_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#integral_type}.
	 * @param ctx the parse tree
	 */
	void exitIntegral_type(MainAntlrParser.Integral_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#floating_point_type}.
	 * @param ctx the parse tree
	 */
	void enterFloating_point_type(MainAntlrParser.Floating_point_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#floating_point_type}.
	 * @param ctx the parse tree
	 */
	void exitFloating_point_type(MainAntlrParser.Floating_point_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#numeric_type}.
	 * @param ctx the parse tree
	 */
	void enterNumeric_type(MainAntlrParser.Numeric_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#numeric_type}.
	 * @param ctx the parse tree
	 */
	void exitNumeric_type(MainAntlrParser.Numeric_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#primitive_type}.
	 * @param ctx the parse tree
	 */
	void enterPrimitive_type(MainAntlrParser.Primitive_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#primitive_type}.
	 * @param ctx the parse tree
	 */
	void exitPrimitive_type(MainAntlrParser.Primitive_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#reference_type}.
	 * @param ctx the parse tree
	 */
	void enterReference_type(MainAntlrParser.Reference_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#reference_type}.
	 * @param ctx the parse tree
	 */
	void exitReference_type(MainAntlrParser.Reference_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#class_type}.
	 * @param ctx the parse tree
	 */
	void enterClass_type(MainAntlrParser.Class_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#class_type}.
	 * @param ctx the parse tree
	 */
	void exitClass_type(MainAntlrParser.Class_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#type_arguments}.
	 * @param ctx the parse tree
	 */
	void enterType_arguments(MainAntlrParser.Type_argumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#type_arguments}.
	 * @param ctx the parse tree
	 */
	void exitType_arguments(MainAntlrParser.Type_argumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#type_argument_list}.
	 * @param ctx the parse tree
	 */
	void enterType_argument_list(MainAntlrParser.Type_argument_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#type_argument_list}.
	 * @param ctx the parse tree
	 */
	void exitType_argument_list(MainAntlrParser.Type_argument_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#type_argument}.
	 * @param ctx the parse tree
	 */
	void enterType_argument(MainAntlrParser.Type_argumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#type_argument}.
	 * @param ctx the parse tree
	 */
	void exitType_argument(MainAntlrParser.Type_argumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#wildcard}.
	 * @param ctx the parse tree
	 */
	void enterWildcard(MainAntlrParser.WildcardContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#wildcard}.
	 * @param ctx the parse tree
	 */
	void exitWildcard(MainAntlrParser.WildcardContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#type_variable}.
	 * @param ctx the parse tree
	 */
	void enterType_variable(MainAntlrParser.Type_variableContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#type_variable}.
	 * @param ctx the parse tree
	 */
	void exitType_variable(MainAntlrParser.Type_variableContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#array_type}.
	 * @param ctx the parse tree
	 */
	void enterArray_type(MainAntlrParser.Array_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#array_type}.
	 * @param ctx the parse tree
	 */
	void exitArray_type(MainAntlrParser.Array_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#array_expression}.
	 * @param ctx the parse tree
	 */
	void enterArray_expression(MainAntlrParser.Array_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#array_expression}.
	 * @param ctx the parse tree
	 */
	void exitArray_expression(MainAntlrParser.Array_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#while_loop}.
	 * @param ctx the parse tree
	 */
	void enterWhile_loop(MainAntlrParser.While_loopContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#while_loop}.
	 * @param ctx the parse tree
	 */
	void exitWhile_loop(MainAntlrParser.While_loopContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#do_while_loop}.
	 * @param ctx the parse tree
	 */
	void enterDo_while_loop(MainAntlrParser.Do_while_loopContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#do_while_loop}.
	 * @param ctx the parse tree
	 */
	void exitDo_while_loop(MainAntlrParser.Do_while_loopContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#for_loop}.
	 * @param ctx the parse tree
	 */
	void enterFor_loop(MainAntlrParser.For_loopContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#for_loop}.
	 * @param ctx the parse tree
	 */
	void exitFor_loop(MainAntlrParser.For_loopContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#for_init}.
	 * @param ctx the parse tree
	 */
	void enterFor_init(MainAntlrParser.For_initContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#for_init}.
	 * @param ctx the parse tree
	 */
	void exitFor_init(MainAntlrParser.For_initContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#for_termination}.
	 * @param ctx the parse tree
	 */
	void enterFor_termination(MainAntlrParser.For_terminationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#for_termination}.
	 * @param ctx the parse tree
	 */
	void exitFor_termination(MainAntlrParser.For_terminationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#for_update}.
	 * @param ctx the parse tree
	 */
	void enterFor_update(MainAntlrParser.For_updateContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#for_update}.
	 * @param ctx the parse tree
	 */
	void exitFor_update(MainAntlrParser.For_updateContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#casting}.
	 * @param ctx the parse tree
	 */
	void enterCasting(MainAntlrParser.CastingContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#casting}.
	 * @param ctx the parse tree
	 */
	void exitCasting(MainAntlrParser.CastingContext ctx);
	/**
	 * Enter a parse tree produced by {@link MainAntlrParser#access_modifier}.
	 * @param ctx the parse tree
	 */
	void enterAccess_modifier(MainAntlrParser.Access_modifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#access_modifier}.
	 * @param ctx the parse tree
	 */
	void exitAccess_modifier(MainAntlrParser.Access_modifierContext ctx);
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
	 * Enter a parse tree produced by {@link MainAntlrParser#function_declaration_arg}.
	 * @param ctx the parse tree
	 */
	void enterFunction_declaration_arg(MainAntlrParser.Function_declaration_argContext ctx);
	/**
	 * Exit a parse tree produced by {@link MainAntlrParser#function_declaration_arg}.
	 * @param ctx the parse tree
	 */
	void exitFunction_declaration_arg(MainAntlrParser.Function_declaration_argContext ctx);
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