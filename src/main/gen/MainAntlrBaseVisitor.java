// Generated from D:/Git Repos/compiler/src/main/antlr/MainAntlr.g4 by ANTLR 4.13.1

package org.cmjava2023.generated_from_antlr;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link MainAntlrVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
@SuppressWarnings("CheckReturnValue")
public class MainAntlrBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements MainAntlrVisitor<T> {
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitStart(MainAntlrParser.StartContext ctx) { return visitChildren(ctx); }
}