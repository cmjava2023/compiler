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
}