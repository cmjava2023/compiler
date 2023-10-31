// Generated from D:/Git Repos/compiler/src/main/antlr/MainAntlr.g4 by ANTLR 4.13.1

package org.cmjava2023.generated_from_antlr;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class MainAntlrParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PACKAGE_KEYWORD=1, CLASS=2, ACCESS_MODIFIER=3, INSTANCE_MODIFIER=4, TYPE=5, 
		TYPE_ADDITION=6, IDENTIFIER=7, POTENTIALLY_NESTED_IDENTIFIER=8, SEMICOLON=9, 
		COLON=10, COMMA=11, DOT=12, PAREN_OPEN=13, PAREN_CLOSE=14, BRACKET_OPEN=15, 
		BRACKET_CLOSE=16, CURLY_OPEN=17, CURLY_CLOSE=18, WHITESPACE=19, STRING=20;
	public static final int
		RULE_start = 0, RULE_statement = 1, RULE_expression = 2, RULE_package_declaration = 3, 
		RULE_class_declaration = 4, RULE_class_body = 5, RULE_function_declaration = 6, 
		RULE_function_declaration_args = 7, RULE_function_declaration_body = 8, 
		RULE_function_call = 9, RULE_function_args = 10;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "statement", "expression", "package_declaration", "class_declaration", 
			"class_body", "function_declaration", "function_declaration_args", "function_declaration_body", 
			"function_call", "function_args"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'package'", "'class'", null, "'static'", null, "'[]'", null, null, 
			"';'", "':'", "','", "'.'", "'('", "')'", "'['", "']'", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PACKAGE_KEYWORD", "CLASS", "ACCESS_MODIFIER", "INSTANCE_MODIFIER", 
			"TYPE", "TYPE_ADDITION", "IDENTIFIER", "POTENTIALLY_NESTED_IDENTIFIER", 
			"SEMICOLON", "COLON", "COMMA", "DOT", "PAREN_OPEN", "PAREN_CLOSE", "BRACKET_OPEN", 
			"BRACKET_CLOSE", "CURLY_OPEN", "CURLY_CLOSE", "WHITESPACE", "STRING"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MainAntlr.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MainAntlrParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StartContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PACKAGE_KEYWORD || _la==ACCESS_MODIFIER) {
				{
				{
				setState(22);
				statement();
				}
				}
				setState(27);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public Package_declarationContext package_declaration() {
			return getRuleContext(Package_declarationContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(MainAntlrParser.SEMICOLON, 0); }
		public Class_declarationContext class_declaration() {
			return getRuleContext(Class_declarationContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(32);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PACKAGE_KEYWORD:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(28);
				package_declaration();
				setState(29);
				match(SEMICOLON);
				}
				}
				break;
			case ACCESS_MODIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(31);
				class_declaration();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public Function_callContext function_call() {
			return getRuleContext(Function_callContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(MainAntlrParser.SEMICOLON, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			function_call();
			setState(35);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Package_declarationContext extends ParserRuleContext {
		public TerminalNode PACKAGE_KEYWORD() { return getToken(MainAntlrParser.PACKAGE_KEYWORD, 0); }
		public TerminalNode POTENTIALLY_NESTED_IDENTIFIER() { return getToken(MainAntlrParser.POTENTIALLY_NESTED_IDENTIFIER, 0); }
		public Package_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_package_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterPackage_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitPackage_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitPackage_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Package_declarationContext package_declaration() throws RecognitionException {
		Package_declarationContext _localctx = new Package_declarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_package_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			match(PACKAGE_KEYWORD);
			setState(38);
			match(POTENTIALLY_NESTED_IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Class_declarationContext extends ParserRuleContext {
		public TerminalNode ACCESS_MODIFIER() { return getToken(MainAntlrParser.ACCESS_MODIFIER, 0); }
		public TerminalNode CLASS() { return getToken(MainAntlrParser.CLASS, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MainAntlrParser.IDENTIFIER, 0); }
		public TerminalNode CURLY_OPEN() { return getToken(MainAntlrParser.CURLY_OPEN, 0); }
		public Class_bodyContext class_body() {
			return getRuleContext(Class_bodyContext.class,0);
		}
		public TerminalNode CURLY_CLOSE() { return getToken(MainAntlrParser.CURLY_CLOSE, 0); }
		public Class_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterClass_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitClass_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitClass_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Class_declarationContext class_declaration() throws RecognitionException {
		Class_declarationContext _localctx = new Class_declarationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_class_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			match(ACCESS_MODIFIER);
			setState(41);
			match(CLASS);
			setState(42);
			match(IDENTIFIER);
			setState(43);
			match(CURLY_OPEN);
			setState(44);
			class_body();
			setState(45);
			match(CURLY_CLOSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Class_bodyContext extends ParserRuleContext {
		public Function_declarationContext function_declaration() {
			return getRuleContext(Function_declarationContext.class,0);
		}
		public Class_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterClass_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitClass_body(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitClass_body(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Class_bodyContext class_body() throws RecognitionException {
		Class_bodyContext _localctx = new Class_bodyContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_class_body);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			function_declaration();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Function_declarationContext extends ParserRuleContext {
		public TerminalNode ACCESS_MODIFIER() { return getToken(MainAntlrParser.ACCESS_MODIFIER, 0); }
		public TerminalNode TYPE() { return getToken(MainAntlrParser.TYPE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MainAntlrParser.IDENTIFIER, 0); }
		public TerminalNode PAREN_OPEN() { return getToken(MainAntlrParser.PAREN_OPEN, 0); }
		public Function_declaration_argsContext function_declaration_args() {
			return getRuleContext(Function_declaration_argsContext.class,0);
		}
		public TerminalNode PAREN_CLOSE() { return getToken(MainAntlrParser.PAREN_CLOSE, 0); }
		public TerminalNode CURLY_OPEN() { return getToken(MainAntlrParser.CURLY_OPEN, 0); }
		public Function_declaration_bodyContext function_declaration_body() {
			return getRuleContext(Function_declaration_bodyContext.class,0);
		}
		public TerminalNode CURLY_CLOSE() { return getToken(MainAntlrParser.CURLY_CLOSE, 0); }
		public TerminalNode INSTANCE_MODIFIER() { return getToken(MainAntlrParser.INSTANCE_MODIFIER, 0); }
		public Function_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterFunction_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitFunction_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitFunction_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_declarationContext function_declaration() throws RecognitionException {
		Function_declarationContext _localctx = new Function_declarationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_function_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			match(ACCESS_MODIFIER);
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INSTANCE_MODIFIER) {
				{
				setState(50);
				match(INSTANCE_MODIFIER);
				}
			}

			setState(53);
			match(TYPE);
			setState(54);
			match(IDENTIFIER);
			setState(55);
			match(PAREN_OPEN);
			setState(56);
			function_declaration_args();
			setState(57);
			match(PAREN_CLOSE);
			setState(58);
			match(CURLY_OPEN);
			setState(59);
			function_declaration_body();
			setState(60);
			match(CURLY_CLOSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Function_declaration_argsContext extends ParserRuleContext {
		public List<TerminalNode> TYPE() { return getTokens(MainAntlrParser.TYPE); }
		public TerminalNode TYPE(int i) {
			return getToken(MainAntlrParser.TYPE, i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(MainAntlrParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(MainAntlrParser.IDENTIFIER, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MainAntlrParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MainAntlrParser.COMMA, i);
		}
		public Function_declaration_argsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_declaration_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterFunction_declaration_args(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitFunction_declaration_args(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitFunction_declaration_args(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_declaration_argsContext function_declaration_args() throws RecognitionException {
		Function_declaration_argsContext _localctx = new Function_declaration_argsContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_function_declaration_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			match(TYPE);
			setState(63);
			match(IDENTIFIER);
			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(64);
				match(COMMA);
				setState(65);
				match(TYPE);
				setState(66);
				match(IDENTIFIER);
				}
				}
				setState(71);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Function_declaration_bodyContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Function_declaration_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_declaration_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterFunction_declaration_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitFunction_declaration_body(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitFunction_declaration_body(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_declaration_bodyContext function_declaration_body() throws RecognitionException {
		Function_declaration_bodyContext _localctx = new Function_declaration_bodyContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_function_declaration_body);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Function_callContext extends ParserRuleContext {
		public TerminalNode POTENTIALLY_NESTED_IDENTIFIER() { return getToken(MainAntlrParser.POTENTIALLY_NESTED_IDENTIFIER, 0); }
		public TerminalNode PAREN_OPEN() { return getToken(MainAntlrParser.PAREN_OPEN, 0); }
		public Function_argsContext function_args() {
			return getRuleContext(Function_argsContext.class,0);
		}
		public TerminalNode PAREN_CLOSE() { return getToken(MainAntlrParser.PAREN_CLOSE, 0); }
		public Function_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterFunction_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitFunction_call(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitFunction_call(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_callContext function_call() throws RecognitionException {
		Function_callContext _localctx = new Function_callContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_function_call);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			match(POTENTIALLY_NESTED_IDENTIFIER);
			setState(75);
			match(PAREN_OPEN);
			setState(76);
			function_args();
			setState(77);
			match(PAREN_CLOSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Function_argsContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(MainAntlrParser.STRING, 0); }
		public Function_argsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterFunction_args(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitFunction_args(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitFunction_args(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_argsContext function_args() throws RecognitionException {
		Function_argsContext _localctx = new Function_argsContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_function_args);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0014R\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0001\u0000\u0005\u0000\u0018"+
		"\b\u0000\n\u0000\f\u0000\u001b\t\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0003\u0001!\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0003\u00064\b\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0005\u0007D\b\u0007\n\u0007\f\u0007G\t\u0007\u0001\b\u0001\b\u0001\t"+
		"\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0000\u0000\u000b"+
		"\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0000\u0000J\u0000"+
		"\u0019\u0001\u0000\u0000\u0000\u0002 \u0001\u0000\u0000\u0000\u0004\""+
		"\u0001\u0000\u0000\u0000\u0006%\u0001\u0000\u0000\u0000\b(\u0001\u0000"+
		"\u0000\u0000\n/\u0001\u0000\u0000\u0000\f1\u0001\u0000\u0000\u0000\u000e"+
		">\u0001\u0000\u0000\u0000\u0010H\u0001\u0000\u0000\u0000\u0012J\u0001"+
		"\u0000\u0000\u0000\u0014O\u0001\u0000\u0000\u0000\u0016\u0018\u0003\u0002"+
		"\u0001\u0000\u0017\u0016\u0001\u0000\u0000\u0000\u0018\u001b\u0001\u0000"+
		"\u0000\u0000\u0019\u0017\u0001\u0000\u0000\u0000\u0019\u001a\u0001\u0000"+
		"\u0000\u0000\u001a\u0001\u0001\u0000\u0000\u0000\u001b\u0019\u0001\u0000"+
		"\u0000\u0000\u001c\u001d\u0003\u0006\u0003\u0000\u001d\u001e\u0005\t\u0000"+
		"\u0000\u001e!\u0001\u0000\u0000\u0000\u001f!\u0003\b\u0004\u0000 \u001c"+
		"\u0001\u0000\u0000\u0000 \u001f\u0001\u0000\u0000\u0000!\u0003\u0001\u0000"+
		"\u0000\u0000\"#\u0003\u0012\t\u0000#$\u0005\t\u0000\u0000$\u0005\u0001"+
		"\u0000\u0000\u0000%&\u0005\u0001\u0000\u0000&\'\u0005\b\u0000\u0000\'"+
		"\u0007\u0001\u0000\u0000\u0000()\u0005\u0003\u0000\u0000)*\u0005\u0002"+
		"\u0000\u0000*+\u0005\u0007\u0000\u0000+,\u0005\u0011\u0000\u0000,-\u0003"+
		"\n\u0005\u0000-.\u0005\u0012\u0000\u0000.\t\u0001\u0000\u0000\u0000/0"+
		"\u0003\f\u0006\u00000\u000b\u0001\u0000\u0000\u000013\u0005\u0003\u0000"+
		"\u000024\u0005\u0004\u0000\u000032\u0001\u0000\u0000\u000034\u0001\u0000"+
		"\u0000\u000045\u0001\u0000\u0000\u000056\u0005\u0005\u0000\u000067\u0005"+
		"\u0007\u0000\u000078\u0005\r\u0000\u000089\u0003\u000e\u0007\u00009:\u0005"+
		"\u000e\u0000\u0000:;\u0005\u0011\u0000\u0000;<\u0003\u0010\b\u0000<=\u0005"+
		"\u0012\u0000\u0000=\r\u0001\u0000\u0000\u0000>?\u0005\u0005\u0000\u0000"+
		"?E\u0005\u0007\u0000\u0000@A\u0005\u000b\u0000\u0000AB\u0005\u0005\u0000"+
		"\u0000BD\u0005\u0007\u0000\u0000C@\u0001\u0000\u0000\u0000DG\u0001\u0000"+
		"\u0000\u0000EC\u0001\u0000\u0000\u0000EF\u0001\u0000\u0000\u0000F\u000f"+
		"\u0001\u0000\u0000\u0000GE\u0001\u0000\u0000\u0000HI\u0003\u0004\u0002"+
		"\u0000I\u0011\u0001\u0000\u0000\u0000JK\u0005\b\u0000\u0000KL\u0005\r"+
		"\u0000\u0000LM\u0003\u0014\n\u0000MN\u0005\u000e\u0000\u0000N\u0013\u0001"+
		"\u0000\u0000\u0000OP\u0005\u0014\u0000\u0000P\u0015\u0001\u0000\u0000"+
		"\u0000\u0004\u0019 3E";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}