// Generated from D:/Desktop/Master of Science/1. Semester/Concepts of Programming Languages/GruppenProjekt/githubrepo/compiler/src/main/antlr/MainAntlr.g4 by ANTLR 4.13.1

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
		PACKAGE_KEYWORD=1, CLASS_KEYWORD=2, INSTANCE_MODIFIER=3, BOOLEAN_KEYWORD=4, 
		BYTE_KEYWORD=5, SHORT_KEYWORD=6, INT_KEYWORD=7, LONG_KEYWORD=8, CHAR_KEYWORD=9, 
		FLOAT_KEYWORD=10, DOUBLE_KEYWORD=11, EXTENDS_KEYWORD=12, SUPER_KEYWORD=13, 
		VOID_KEYWORD=14, PUBLIC_KEYWORD=15, PRIVATE_KEYWORD=16, PROTECTED_KEYWORD=17, 
		RETURN_KEYWORD=18, IF_KEYWORD=19, ELSE_KEYWORD=20, IDENTIFIER=21, SEMICOLON=22, 
		COLON=23, COMMA=24, DOT=25, PAREN_OPEN=26, PAREN_CLOSE=27, BRACKET_OPEN=28, 
		BRACKET_CLOSE=29, CURLY_OPEN=30, CURLY_CLOSE=31, DIAMOND_OPEN=32, DIAMOND_CLOSE=33, 
		EQUALS=34, EQ=35, NEQ=36, GTE=37, LTE=38, MOD=39, LAND=40, LOR=41, WHITESPACE=42, 
		STRING=43, INTEGER=44, DECIMAL=45;
	public static final int
		RULE_start = 0, RULE_global_scope = 1, RULE_class_scope = 2, RULE_function_scope = 3, 
		RULE_block_scope = 4, RULE_expressions = 5, RULE_variable_declaration = 6, 
		RULE_assignment = 7, RULE_expression = 8, RULE_expression_operator = 9, 
		RULE_numerical_comparison_operator = 10, RULE_logical_comparison_operator = 11, 
		RULE_package_declaration = 12, RULE_class_declaration = 13, RULE_function_declaration = 14, 
		RULE_function_declaration_args = 15, RULE_function_declaration_arg = 16, 
		RULE_function_call = 17, RULE_function_args = 18, RULE_function_arg = 19, 
		RULE_if_statement = 20, RULE_else_statement = 21, RULE_if_else_statement = 22, 
		RULE_return_statement = 23, RULE_identifier = 24, RULE_type = 25, RULE_integral_type = 26, 
		RULE_floating_point_type = 27, RULE_numeric_type = 28, RULE_primitive_type = 29, 
		RULE_reference_type = 30, RULE_class_type = 31, RULE_type_arguments = 32, 
		RULE_type_argument_list = 33, RULE_type_argument = 34, RULE_wildcard = 35, 
		RULE_type_variable = 36, RULE_array_type = 37, RULE_access_modifier = 38;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "global_scope", "class_scope", "function_scope", "block_scope", 
			"expressions", "variable_declaration", "assignment", "expression", "expression_operator", 
			"numerical_comparison_operator", "logical_comparison_operator", "package_declaration", 
			"class_declaration", "function_declaration", "function_declaration_args", 
			"function_declaration_arg", "function_call", "function_args", "function_arg", 
			"if_statement", "else_statement", "if_else_statement", "return_statement", 
			"identifier", "type", "integral_type", "floating_point_type", "numeric_type", 
			"primitive_type", "reference_type", "class_type", "type_arguments", "type_argument_list", 
			"type_argument", "wildcard", "type_variable", "array_type", "access_modifier"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'package'", "'class'", "'static'", "'boolean'", "'byte'", "'short'", 
			"'int'", "'long'", "'char'", "'float'", "'double'", "'extends'", "'super'", 
			"'void'", "'public'", "'private'", "'protected'", "'return'", "'if'", 
			"'else'", null, "';'", "':'", "','", "'.'", "'('", "')'", "'['", "']'", 
			"'{'", "'}'", "'<'", "'>'", "'='", "'=='", "'!='", "'>='", "'<='", "'%'", 
			"'&&'", "'||'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PACKAGE_KEYWORD", "CLASS_KEYWORD", "INSTANCE_MODIFIER", "BOOLEAN_KEYWORD", 
			"BYTE_KEYWORD", "SHORT_KEYWORD", "INT_KEYWORD", "LONG_KEYWORD", "CHAR_KEYWORD", 
			"FLOAT_KEYWORD", "DOUBLE_KEYWORD", "EXTENDS_KEYWORD", "SUPER_KEYWORD", 
			"VOID_KEYWORD", "PUBLIC_KEYWORD", "PRIVATE_KEYWORD", "PROTECTED_KEYWORD", 
			"RETURN_KEYWORD", "IF_KEYWORD", "ELSE_KEYWORD", "IDENTIFIER", "SEMICOLON", 
			"COLON", "COMMA", "DOT", "PAREN_OPEN", "PAREN_CLOSE", "BRACKET_OPEN", 
			"BRACKET_CLOSE", "CURLY_OPEN", "CURLY_CLOSE", "DIAMOND_OPEN", "DIAMOND_CLOSE", 
			"EQUALS", "EQ", "NEQ", "GTE", "LTE", "MOD", "LAND", "LOR", "WHITESPACE", 
			"STRING", "INTEGER", "DECIMAL"
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
		public List<Global_scopeContext> global_scope() {
			return getRuleContexts(Global_scopeContext.class);
		}
		public Global_scopeContext global_scope(int i) {
			return getRuleContext(Global_scopeContext.class,i);
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
			setState(79); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(78);
				global_scope();
				}
				}
				setState(81); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 229378L) != 0) );
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
	public static class Global_scopeContext extends ParserRuleContext {
		public Class_declarationContext class_declaration() {
			return getRuleContext(Class_declarationContext.class,0);
		}
		public Package_declarationContext package_declaration() {
			return getRuleContext(Package_declarationContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(MainAntlrParser.SEMICOLON, 0); }
		public Global_scopeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_global_scope; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterGlobal_scope(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitGlobal_scope(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitGlobal_scope(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Global_scopeContext global_scope() throws RecognitionException {
		Global_scopeContext _localctx = new Global_scopeContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_global_scope);
		try {
			setState(87);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PUBLIC_KEYWORD:
			case PRIVATE_KEYWORD:
			case PROTECTED_KEYWORD:
				enterOuterAlt(_localctx, 1);
				{
				setState(83);
				class_declaration();
				}
				break;
			case PACKAGE_KEYWORD:
				enterOuterAlt(_localctx, 2);
				{
				setState(84);
				package_declaration();
				setState(85);
				match(SEMICOLON);
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
	public static class Class_scopeContext extends ParserRuleContext {
		public List<Function_declarationContext> function_declaration() {
			return getRuleContexts(Function_declarationContext.class);
		}
		public Function_declarationContext function_declaration(int i) {
			return getRuleContext(Function_declarationContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(MainAntlrParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(MainAntlrParser.SEMICOLON, i);
		}
		public List<Variable_declarationContext> variable_declaration() {
			return getRuleContexts(Variable_declarationContext.class);
		}
		public Variable_declarationContext variable_declaration(int i) {
			return getRuleContext(Variable_declarationContext.class,i);
		}
		public List<AssignmentContext> assignment() {
			return getRuleContexts(AssignmentContext.class);
		}
		public AssignmentContext assignment(int i) {
			return getRuleContext(AssignmentContext.class,i);
		}
		public Class_scopeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_scope; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterClass_scope(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitClass_scope(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitClass_scope(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Class_scopeContext class_scope() throws RecognitionException {
		Class_scopeContext _localctx = new Class_scopeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_class_scope);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2330608L) != 0)) {
				{
				setState(96);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case PUBLIC_KEYWORD:
				case PRIVATE_KEYWORD:
				case PROTECTED_KEYWORD:
					{
					setState(89);
					function_declaration();
					}
					break;
				case BOOLEAN_KEYWORD:
				case BYTE_KEYWORD:
				case SHORT_KEYWORD:
				case INT_KEYWORD:
				case LONG_KEYWORD:
				case CHAR_KEYWORD:
				case FLOAT_KEYWORD:
				case DOUBLE_KEYWORD:
				case IDENTIFIER:
					{
					{
					setState(92);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
					case 1:
						{
						setState(90);
						variable_declaration();
						}
						break;
					case 2:
						{
						setState(91);
						assignment();
						}
						break;
					}
					setState(94);
					match(SEMICOLON);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(100);
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
	public static class Function_scopeContext extends ParserRuleContext {
		public List<TerminalNode> SEMICOLON() { return getTokens(MainAntlrParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(MainAntlrParser.SEMICOLON, i);
		}
		public List<Block_scopeContext> block_scope() {
			return getRuleContexts(Block_scopeContext.class);
		}
		public Block_scopeContext block_scope(int i) {
			return getRuleContext(Block_scopeContext.class,i);
		}
		public List<ExpressionsContext> expressions() {
			return getRuleContexts(ExpressionsContext.class);
		}
		public ExpressionsContext expressions(int i) {
			return getRuleContext(ExpressionsContext.class,i);
		}
		public List<AssignmentContext> assignment() {
			return getRuleContexts(AssignmentContext.class);
		}
		public AssignmentContext assignment(int i) {
			return getRuleContext(AssignmentContext.class,i);
		}
		public List<Variable_declarationContext> variable_declaration() {
			return getRuleContexts(Variable_declarationContext.class);
		}
		public Variable_declarationContext variable_declaration(int i) {
			return getRuleContext(Variable_declarationContext.class,i);
		}
		public List<Return_statementContext> return_statement() {
			return getRuleContexts(Return_statementContext.class);
		}
		public Return_statementContext return_statement(int i) {
			return getRuleContext(Return_statementContext.class,i);
		}
		public Function_scopeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_scope; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterFunction_scope(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitFunction_scope(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitFunction_scope(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_scopeContext function_scope() throws RecognitionException {
		Function_scopeContext _localctx = new Function_scopeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_function_scope);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 61572654043120L) != 0)) {
				{
				setState(110);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case BOOLEAN_KEYWORD:
				case BYTE_KEYWORD:
				case SHORT_KEYWORD:
				case INT_KEYWORD:
				case LONG_KEYWORD:
				case CHAR_KEYWORD:
				case FLOAT_KEYWORD:
				case DOUBLE_KEYWORD:
				case RETURN_KEYWORD:
				case IDENTIFIER:
				case STRING:
				case INTEGER:
				case DECIMAL:
					{
					setState(105);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						setState(101);
						expressions();
						}
						break;
					case 2:
						{
						setState(102);
						assignment();
						}
						break;
					case 3:
						{
						setState(103);
						variable_declaration();
						}
						break;
					case 4:
						{
						setState(104);
						return_statement();
						}
						break;
					}
					setState(107);
					match(SEMICOLON);
					}
					break;
				case IF_KEYWORD:
					{
					setState(109);
					block_scope();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(114);
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
	public static class Block_scopeContext extends ParserRuleContext {
		public If_statementContext if_statement() {
			return getRuleContext(If_statementContext.class,0);
		}
		public If_else_statementContext if_else_statement() {
			return getRuleContext(If_else_statementContext.class,0);
		}
		public Block_scopeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block_scope; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterBlock_scope(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitBlock_scope(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitBlock_scope(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Block_scopeContext block_scope() throws RecognitionException {
		Block_scopeContext _localctx = new Block_scopeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_block_scope);
		try {
			setState(117);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(115);
				if_statement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(116);
				if_else_statement();
				}
				break;
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
	public static class ExpressionsContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public Expression_operatorContext expression_operator() {
			return getRuleContext(Expression_operatorContext.class,0);
		}
		public ExpressionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterExpressions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitExpressions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitExpressions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionsContext expressions() throws RecognitionException {
		ExpressionsContext _localctx = new ExpressionsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_expressions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			expression();
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4376571674624L) != 0)) {
				{
				setState(120);
				expression_operator();
				setState(121);
				expression();
				}
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
	public static class Variable_declarationContext extends ParserRuleContext {
		public Primitive_typeContext primitive_type() {
			return getRuleContext(Primitive_typeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(MainAntlrParser.IDENTIFIER, 0); }
		public Variable_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterVariable_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitVariable_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitVariable_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Variable_declarationContext variable_declaration() throws RecognitionException {
		Variable_declarationContext _localctx = new Variable_declarationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_variable_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			primitive_type();
			setState(126);
			match(IDENTIFIER);
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
	public static class AssignmentContext extends ParserRuleContext {
		public TerminalNode EQUALS() { return getToken(MainAntlrParser.EQUALS, 0); }
		public ExpressionsContext expressions() {
			return getRuleContext(ExpressionsContext.class,0);
		}
		public Variable_declarationContext variable_declaration() {
			return getRuleContext(Variable_declarationContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLEAN_KEYWORD:
			case BYTE_KEYWORD:
			case SHORT_KEYWORD:
			case INT_KEYWORD:
			case LONG_KEYWORD:
			case CHAR_KEYWORD:
			case FLOAT_KEYWORD:
			case DOUBLE_KEYWORD:
				{
				setState(128);
				variable_declaration();
				}
				break;
			case IDENTIFIER:
				{
				setState(129);
				identifier();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(132);
			match(EQUALS);
			setState(133);
			expressions();
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
		public TerminalNode DECIMAL() { return getToken(MainAntlrParser.DECIMAL, 0); }
		public TerminalNode INTEGER() { return getToken(MainAntlrParser.INTEGER, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MainAntlrParser.IDENTIFIER, 0); }
		public TerminalNode STRING() { return getToken(MainAntlrParser.STRING, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
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
		enterRule(_localctx, 16, RULE_expression);
		try {
			setState(141);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(135);
				function_call();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(136);
				match(DECIMAL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(137);
				match(INTEGER);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(138);
				match(IDENTIFIER);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(139);
				match(STRING);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(140);
				identifier();
				}
				break;
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
	public static class Expression_operatorContext extends ParserRuleContext {
		public Logical_comparison_operatorContext logical_comparison_operator() {
			return getRuleContext(Logical_comparison_operatorContext.class,0);
		}
		public Numerical_comparison_operatorContext numerical_comparison_operator() {
			return getRuleContext(Numerical_comparison_operatorContext.class,0);
		}
		public Expression_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterExpression_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitExpression_operator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitExpression_operator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression_operatorContext expression_operator() throws RecognitionException {
		Expression_operatorContext _localctx = new Expression_operatorContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_expression_operator);
		try {
			setState(145);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LAND:
			case LOR:
				enterOuterAlt(_localctx, 1);
				{
				setState(143);
				logical_comparison_operator();
				}
				break;
			case DIAMOND_OPEN:
			case DIAMOND_CLOSE:
			case EQ:
			case NEQ:
			case GTE:
			case LTE:
			case MOD:
				enterOuterAlt(_localctx, 2);
				{
				setState(144);
				numerical_comparison_operator();
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
	public static class Numerical_comparison_operatorContext extends ParserRuleContext {
		public TerminalNode DIAMOND_OPEN() { return getToken(MainAntlrParser.DIAMOND_OPEN, 0); }
		public TerminalNode DIAMOND_CLOSE() { return getToken(MainAntlrParser.DIAMOND_CLOSE, 0); }
		public TerminalNode NEQ() { return getToken(MainAntlrParser.NEQ, 0); }
		public TerminalNode EQ() { return getToken(MainAntlrParser.EQ, 0); }
		public TerminalNode LTE() { return getToken(MainAntlrParser.LTE, 0); }
		public TerminalNode GTE() { return getToken(MainAntlrParser.GTE, 0); }
		public TerminalNode MOD() { return getToken(MainAntlrParser.MOD, 0); }
		public Numerical_comparison_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numerical_comparison_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterNumerical_comparison_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitNumerical_comparison_operator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitNumerical_comparison_operator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Numerical_comparison_operatorContext numerical_comparison_operator() throws RecognitionException {
		Numerical_comparison_operatorContext _localctx = new Numerical_comparison_operatorContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_numerical_comparison_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1078036791296L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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
	public static class Logical_comparison_operatorContext extends ParserRuleContext {
		public TerminalNode LAND() { return getToken(MainAntlrParser.LAND, 0); }
		public TerminalNode LOR() { return getToken(MainAntlrParser.LOR, 0); }
		public Logical_comparison_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logical_comparison_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterLogical_comparison_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitLogical_comparison_operator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitLogical_comparison_operator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Logical_comparison_operatorContext logical_comparison_operator() throws RecognitionException {
		Logical_comparison_operatorContext _localctx = new Logical_comparison_operatorContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_logical_comparison_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			_la = _input.LA(1);
			if ( !(_la==LAND || _la==LOR) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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
	public static class Package_declarationContext extends ParserRuleContext {
		public TerminalNode PACKAGE_KEYWORD() { return getToken(MainAntlrParser.PACKAGE_KEYWORD, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
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
		enterRule(_localctx, 24, RULE_package_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			match(PACKAGE_KEYWORD);
			setState(152);
			identifier();
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
		public Access_modifierContext access_modifier() {
			return getRuleContext(Access_modifierContext.class,0);
		}
		public TerminalNode CLASS_KEYWORD() { return getToken(MainAntlrParser.CLASS_KEYWORD, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MainAntlrParser.IDENTIFIER, 0); }
		public TerminalNode CURLY_OPEN() { return getToken(MainAntlrParser.CURLY_OPEN, 0); }
		public Class_scopeContext class_scope() {
			return getRuleContext(Class_scopeContext.class,0);
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
		enterRule(_localctx, 26, RULE_class_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			access_modifier();
			setState(155);
			match(CLASS_KEYWORD);
			setState(156);
			match(IDENTIFIER);
			setState(157);
			match(CURLY_OPEN);
			setState(158);
			class_scope();
			setState(159);
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
	public static class Function_declarationContext extends ParserRuleContext {
		public Access_modifierContext access_modifier() {
			return getRuleContext(Access_modifierContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(MainAntlrParser.IDENTIFIER, 0); }
		public TerminalNode PAREN_OPEN() { return getToken(MainAntlrParser.PAREN_OPEN, 0); }
		public TerminalNode PAREN_CLOSE() { return getToken(MainAntlrParser.PAREN_CLOSE, 0); }
		public TerminalNode CURLY_OPEN() { return getToken(MainAntlrParser.CURLY_OPEN, 0); }
		public Function_scopeContext function_scope() {
			return getRuleContext(Function_scopeContext.class,0);
		}
		public TerminalNode CURLY_CLOSE() { return getToken(MainAntlrParser.CURLY_CLOSE, 0); }
		public TerminalNode INSTANCE_MODIFIER() { return getToken(MainAntlrParser.INSTANCE_MODIFIER, 0); }
		public Function_declaration_argsContext function_declaration_args() {
			return getRuleContext(Function_declaration_argsContext.class,0);
		}
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
		enterRule(_localctx, 28, RULE_function_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			access_modifier();
			setState(163);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INSTANCE_MODIFIER) {
				{
				setState(162);
				match(INSTANCE_MODIFIER);
				}
			}

			setState(165);
			type();
			setState(166);
			match(IDENTIFIER);
			setState(167);
			match(PAREN_OPEN);
			setState(169);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2117616L) != 0)) {
				{
				setState(168);
				function_declaration_args();
				}
			}

			setState(171);
			match(PAREN_CLOSE);
			setState(172);
			match(CURLY_OPEN);
			setState(173);
			function_scope();
			setState(174);
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
		public List<Function_declaration_argContext> function_declaration_arg() {
			return getRuleContexts(Function_declaration_argContext.class);
		}
		public Function_declaration_argContext function_declaration_arg(int i) {
			return getRuleContext(Function_declaration_argContext.class,i);
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
		enterRule(_localctx, 30, RULE_function_declaration_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			function_declaration_arg();
			setState(181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(177);
				match(COMMA);
				setState(178);
				function_declaration_arg();
				}
				}
				setState(183);
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
	public static class Function_declaration_argContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(MainAntlrParser.IDENTIFIER, 0); }
		public Function_declaration_argContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_declaration_arg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterFunction_declaration_arg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitFunction_declaration_arg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitFunction_declaration_arg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_declaration_argContext function_declaration_arg() throws RecognitionException {
		Function_declaration_argContext _localctx = new Function_declaration_argContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_function_declaration_arg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			type();
			setState(185);
			match(IDENTIFIER);
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
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode PAREN_OPEN() { return getToken(MainAntlrParser.PAREN_OPEN, 0); }
		public TerminalNode PAREN_CLOSE() { return getToken(MainAntlrParser.PAREN_CLOSE, 0); }
		public Function_argsContext function_args() {
			return getRuleContext(Function_argsContext.class,0);
		}
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
		enterRule(_localctx, 34, RULE_function_call);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			identifier();
			setState(188);
			match(PAREN_OPEN);
			setState(190);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 61572653252608L) != 0)) {
				{
				setState(189);
				function_args();
				}
			}

			setState(192);
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
		public List<Function_argContext> function_arg() {
			return getRuleContexts(Function_argContext.class);
		}
		public Function_argContext function_arg(int i) {
			return getRuleContext(Function_argContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MainAntlrParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MainAntlrParser.COMMA, i);
		}
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
		enterRule(_localctx, 36, RULE_function_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			function_arg();
			setState(199);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(195);
				match(COMMA);
				setState(196);
				function_arg();
				}
				}
				setState(201);
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
	public static class Function_argContext extends ParserRuleContext {
		public ExpressionsContext expressions() {
			return getRuleContext(ExpressionsContext.class,0);
		}
		public Function_argContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_arg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterFunction_arg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitFunction_arg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitFunction_arg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_argContext function_arg() throws RecognitionException {
		Function_argContext _localctx = new Function_argContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_function_arg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			expressions();
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
	public static class If_statementContext extends ParserRuleContext {
		public TerminalNode IF_KEYWORD() { return getToken(MainAntlrParser.IF_KEYWORD, 0); }
		public TerminalNode PAREN_OPEN() { return getToken(MainAntlrParser.PAREN_OPEN, 0); }
		public ExpressionsContext expressions() {
			return getRuleContext(ExpressionsContext.class,0);
		}
		public TerminalNode PAREN_CLOSE() { return getToken(MainAntlrParser.PAREN_CLOSE, 0); }
		public TerminalNode CURLY_OPEN() { return getToken(MainAntlrParser.CURLY_OPEN, 0); }
		public Function_scopeContext function_scope() {
			return getRuleContext(Function_scopeContext.class,0);
		}
		public TerminalNode CURLY_CLOSE() { return getToken(MainAntlrParser.CURLY_CLOSE, 0); }
		public If_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterIf_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitIf_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitIf_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_statementContext if_statement() throws RecognitionException {
		If_statementContext _localctx = new If_statementContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_if_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			match(IF_KEYWORD);
			setState(205);
			match(PAREN_OPEN);
			setState(206);
			expressions();
			setState(207);
			match(PAREN_CLOSE);
			setState(208);
			match(CURLY_OPEN);
			setState(209);
			function_scope();
			setState(210);
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
	public static class Else_statementContext extends ParserRuleContext {
		public TerminalNode ELSE_KEYWORD() { return getToken(MainAntlrParser.ELSE_KEYWORD, 0); }
		public TerminalNode CURLY_OPEN() { return getToken(MainAntlrParser.CURLY_OPEN, 0); }
		public Function_scopeContext function_scope() {
			return getRuleContext(Function_scopeContext.class,0);
		}
		public TerminalNode CURLY_CLOSE() { return getToken(MainAntlrParser.CURLY_CLOSE, 0); }
		public Else_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_else_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterElse_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitElse_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitElse_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Else_statementContext else_statement() throws RecognitionException {
		Else_statementContext _localctx = new Else_statementContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_else_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			match(ELSE_KEYWORD);
			setState(213);
			match(CURLY_OPEN);
			setState(214);
			function_scope();
			setState(215);
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
	public static class If_else_statementContext extends ParserRuleContext {
		public If_statementContext if_statement() {
			return getRuleContext(If_statementContext.class,0);
		}
		public Else_statementContext else_statement() {
			return getRuleContext(Else_statementContext.class,0);
		}
		public If_else_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_else_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterIf_else_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitIf_else_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitIf_else_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_else_statementContext if_else_statement() throws RecognitionException {
		If_else_statementContext _localctx = new If_else_statementContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_if_else_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(217);
			if_statement();
			setState(218);
			else_statement();
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
	public static class Return_statementContext extends ParserRuleContext {
		public TerminalNode RETURN_KEYWORD() { return getToken(MainAntlrParser.RETURN_KEYWORD, 0); }
		public ExpressionsContext expressions() {
			return getRuleContext(ExpressionsContext.class,0);
		}
		public Return_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterReturn_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitReturn_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitReturn_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Return_statementContext return_statement() throws RecognitionException {
		Return_statementContext _localctx = new Return_statementContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_return_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			match(RETURN_KEYWORD);
			setState(221);
			expressions();
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
	public static class IdentifierContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(MainAntlrParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(MainAntlrParser.IDENTIFIER, i);
		}
		public List<TerminalNode> DOT() { return getTokens(MainAntlrParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(MainAntlrParser.DOT, i);
		}
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_identifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			match(IDENTIFIER);
			setState(228);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(224);
				match(DOT);
				setState(225);
				match(IDENTIFIER);
				}
				}
				setState(230);
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
	public static class TypeContext extends ParserRuleContext {
		public TerminalNode VOID_KEYWORD() { return getToken(MainAntlrParser.VOID_KEYWORD, 0); }
		public Primitive_typeContext primitive_type() {
			return getRuleContext(Primitive_typeContext.class,0);
		}
		public Array_typeContext array_type() {
			return getRuleContext(Array_typeContext.class,0);
		}
		public Reference_typeContext reference_type() {
			return getRuleContext(Reference_typeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_type);
		try {
			setState(235);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(231);
				match(VOID_KEYWORD);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(232);
				primitive_type();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(233);
				array_type();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(234);
				reference_type();
				}
				break;
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
	public static class Integral_typeContext extends ParserRuleContext {
		public TerminalNode BYTE_KEYWORD() { return getToken(MainAntlrParser.BYTE_KEYWORD, 0); }
		public TerminalNode SHORT_KEYWORD() { return getToken(MainAntlrParser.SHORT_KEYWORD, 0); }
		public TerminalNode INT_KEYWORD() { return getToken(MainAntlrParser.INT_KEYWORD, 0); }
		public TerminalNode LONG_KEYWORD() { return getToken(MainAntlrParser.LONG_KEYWORD, 0); }
		public TerminalNode CHAR_KEYWORD() { return getToken(MainAntlrParser.CHAR_KEYWORD, 0); }
		public Integral_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integral_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterIntegral_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitIntegral_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitIntegral_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Integral_typeContext integral_type() throws RecognitionException {
		Integral_typeContext _localctx = new Integral_typeContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_integral_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 992L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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
	public static class Floating_point_typeContext extends ParserRuleContext {
		public TerminalNode FLOAT_KEYWORD() { return getToken(MainAntlrParser.FLOAT_KEYWORD, 0); }
		public TerminalNode DOUBLE_KEYWORD() { return getToken(MainAntlrParser.DOUBLE_KEYWORD, 0); }
		public Floating_point_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_floating_point_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterFloating_point_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitFloating_point_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitFloating_point_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Floating_point_typeContext floating_point_type() throws RecognitionException {
		Floating_point_typeContext _localctx = new Floating_point_typeContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_floating_point_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
			_la = _input.LA(1);
			if ( !(_la==FLOAT_KEYWORD || _la==DOUBLE_KEYWORD) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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
	public static class Numeric_typeContext extends ParserRuleContext {
		public Integral_typeContext integral_type() {
			return getRuleContext(Integral_typeContext.class,0);
		}
		public Floating_point_typeContext floating_point_type() {
			return getRuleContext(Floating_point_typeContext.class,0);
		}
		public Numeric_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numeric_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterNumeric_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitNumeric_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitNumeric_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Numeric_typeContext numeric_type() throws RecognitionException {
		Numeric_typeContext _localctx = new Numeric_typeContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_numeric_type);
		try {
			setState(243);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BYTE_KEYWORD:
			case SHORT_KEYWORD:
			case INT_KEYWORD:
			case LONG_KEYWORD:
			case CHAR_KEYWORD:
				enterOuterAlt(_localctx, 1);
				{
				setState(241);
				integral_type();
				}
				break;
			case FLOAT_KEYWORD:
			case DOUBLE_KEYWORD:
				enterOuterAlt(_localctx, 2);
				{
				setState(242);
				floating_point_type();
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
	public static class Primitive_typeContext extends ParserRuleContext {
		public Numeric_typeContext numeric_type() {
			return getRuleContext(Numeric_typeContext.class,0);
		}
		public TerminalNode BOOLEAN_KEYWORD() { return getToken(MainAntlrParser.BOOLEAN_KEYWORD, 0); }
		public Primitive_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primitive_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterPrimitive_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitPrimitive_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitPrimitive_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Primitive_typeContext primitive_type() throws RecognitionException {
		Primitive_typeContext _localctx = new Primitive_typeContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_primitive_type);
		try {
			setState(247);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BYTE_KEYWORD:
			case SHORT_KEYWORD:
			case INT_KEYWORD:
			case LONG_KEYWORD:
			case CHAR_KEYWORD:
			case FLOAT_KEYWORD:
			case DOUBLE_KEYWORD:
				enterOuterAlt(_localctx, 1);
				{
				setState(245);
				numeric_type();
				}
				break;
			case BOOLEAN_KEYWORD:
				enterOuterAlt(_localctx, 2);
				{
				setState(246);
				match(BOOLEAN_KEYWORD);
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
	public static class Reference_typeContext extends ParserRuleContext {
		public Class_typeContext class_type() {
			return getRuleContext(Class_typeContext.class,0);
		}
		public Type_variableContext type_variable() {
			return getRuleContext(Type_variableContext.class,0);
		}
		public Array_typeContext array_type() {
			return getRuleContext(Array_typeContext.class,0);
		}
		public Reference_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reference_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterReference_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitReference_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitReference_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Reference_typeContext reference_type() throws RecognitionException {
		Reference_typeContext _localctx = new Reference_typeContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_reference_type);
		try {
			setState(252);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(249);
				class_type();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(250);
				type_variable();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(251);
				array_type();
				}
				break;
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
	public static class Class_typeContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(MainAntlrParser.IDENTIFIER, 0); }
		public Type_argumentsContext type_arguments() {
			return getRuleContext(Type_argumentsContext.class,0);
		}
		public Class_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterClass_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitClass_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitClass_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Class_typeContext class_type() throws RecognitionException {
		Class_typeContext _localctx = new Class_typeContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_class_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			match(IDENTIFIER);
			setState(256);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DIAMOND_OPEN) {
				{
				setState(255);
				type_arguments();
				}
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
	public static class Type_argumentsContext extends ParserRuleContext {
		public TerminalNode DIAMOND_OPEN() { return getToken(MainAntlrParser.DIAMOND_OPEN, 0); }
		public Type_argument_listContext type_argument_list() {
			return getRuleContext(Type_argument_listContext.class,0);
		}
		public TerminalNode DIAMOND_CLOSE() { return getToken(MainAntlrParser.DIAMOND_CLOSE, 0); }
		public Type_argumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_arguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterType_arguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitType_arguments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitType_arguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_argumentsContext type_arguments() throws RecognitionException {
		Type_argumentsContext _localctx = new Type_argumentsContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_type_arguments);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(258);
			match(DIAMOND_OPEN);
			setState(259);
			type_argument_list();
			setState(260);
			match(DIAMOND_CLOSE);
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
	public static class Type_argument_listContext extends ParserRuleContext {
		public List<Type_argumentContext> type_argument() {
			return getRuleContexts(Type_argumentContext.class);
		}
		public Type_argumentContext type_argument(int i) {
			return getRuleContext(Type_argumentContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MainAntlrParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MainAntlrParser.COMMA, i);
		}
		public Type_argument_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_argument_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterType_argument_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitType_argument_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitType_argument_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_argument_listContext type_argument_list() throws RecognitionException {
		Type_argument_listContext _localctx = new Type_argument_listContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_type_argument_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			type_argument();
			setState(267);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(263);
				match(COMMA);
				setState(264);
				type_argument();
				}
				}
				setState(269);
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
	public static class Type_argumentContext extends ParserRuleContext {
		public Reference_typeContext reference_type() {
			return getRuleContext(Reference_typeContext.class,0);
		}
		public WildcardContext wildcard() {
			return getRuleContext(WildcardContext.class,0);
		}
		public Type_argumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterType_argument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitType_argument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitType_argument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_argumentContext type_argument() throws RecognitionException {
		Type_argumentContext _localctx = new Type_argumentContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_type_argument);
		try {
			setState(272);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLEAN_KEYWORD:
			case BYTE_KEYWORD:
			case SHORT_KEYWORD:
			case INT_KEYWORD:
			case LONG_KEYWORD:
			case CHAR_KEYWORD:
			case FLOAT_KEYWORD:
			case DOUBLE_KEYWORD:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(270);
				reference_type();
				}
				break;
			case EXTENDS_KEYWORD:
			case SUPER_KEYWORD:
				enterOuterAlt(_localctx, 2);
				{
				setState(271);
				wildcard();
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
	public static class WildcardContext extends ParserRuleContext {
		public TerminalNode EXTENDS_KEYWORD() { return getToken(MainAntlrParser.EXTENDS_KEYWORD, 0); }
		public Reference_typeContext reference_type() {
			return getRuleContext(Reference_typeContext.class,0);
		}
		public TerminalNode SUPER_KEYWORD() { return getToken(MainAntlrParser.SUPER_KEYWORD, 0); }
		public WildcardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wildcard; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterWildcard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitWildcard(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitWildcard(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WildcardContext wildcard() throws RecognitionException {
		WildcardContext _localctx = new WildcardContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_wildcard);
		try {
			setState(278);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EXTENDS_KEYWORD:
				enterOuterAlt(_localctx, 1);
				{
				setState(274);
				match(EXTENDS_KEYWORD);
				setState(275);
				reference_type();
				}
				break;
			case SUPER_KEYWORD:
				enterOuterAlt(_localctx, 2);
				{
				setState(276);
				match(SUPER_KEYWORD);
				setState(277);
				reference_type();
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
	public static class Type_variableContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(MainAntlrParser.IDENTIFIER, 0); }
		public Type_variableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterType_variable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitType_variable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitType_variable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_variableContext type_variable() throws RecognitionException {
		Type_variableContext _localctx = new Type_variableContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_type_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			match(IDENTIFIER);
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
	public static class Array_typeContext extends ParserRuleContext {
		public TerminalNode BRACKET_OPEN() { return getToken(MainAntlrParser.BRACKET_OPEN, 0); }
		public TerminalNode BRACKET_CLOSE() { return getToken(MainAntlrParser.BRACKET_CLOSE, 0); }
		public Primitive_typeContext primitive_type() {
			return getRuleContext(Primitive_typeContext.class,0);
		}
		public Class_typeContext class_type() {
			return getRuleContext(Class_typeContext.class,0);
		}
		public Type_variableContext type_variable() {
			return getRuleContext(Type_variableContext.class,0);
		}
		public Array_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterArray_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitArray_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitArray_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Array_typeContext array_type() throws RecognitionException {
		Array_typeContext _localctx = new Array_typeContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_array_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				{
				setState(282);
				primitive_type();
				}
				break;
			case 2:
				{
				setState(283);
				class_type();
				}
				break;
			case 3:
				{
				setState(284);
				type_variable();
				}
				break;
			}
			setState(287);
			match(BRACKET_OPEN);
			setState(288);
			match(BRACKET_CLOSE);
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
	public static class Access_modifierContext extends ParserRuleContext {
		public TerminalNode PRIVATE_KEYWORD() { return getToken(MainAntlrParser.PRIVATE_KEYWORD, 0); }
		public TerminalNode PUBLIC_KEYWORD() { return getToken(MainAntlrParser.PUBLIC_KEYWORD, 0); }
		public TerminalNode PROTECTED_KEYWORD() { return getToken(MainAntlrParser.PROTECTED_KEYWORD, 0); }
		public Access_modifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_access_modifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterAccess_modifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitAccess_modifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitAccess_modifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Access_modifierContext access_modifier() throws RecognitionException {
		Access_modifierContext _localctx = new Access_modifierContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_access_modifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(290);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 229376L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static final String _serializedATN =
		"\u0004\u0001-\u0125\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0001\u0000\u0004\u0000"+
		"P\b\u0000\u000b\u0000\f\u0000Q\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0003\u0001X\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0003"+
		"\u0002]\b\u0002\u0001\u0002\u0001\u0002\u0005\u0002a\b\u0002\n\u0002\f"+
		"\u0002d\t\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003"+
		"\u0003j\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003o\b\u0003"+
		"\n\u0003\f\u0003r\t\u0003\u0001\u0004\u0001\u0004\u0003\u0004v\b\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005|\b\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0003\u0007"+
		"\u0083\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0003\b\u008e\b\b\u0001\t\u0001\t\u0003\t\u0092"+
		"\b\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e"+
		"\u0003\u000e\u00a4\b\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0003\u000e\u00aa\b\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u00b4\b\u000f"+
		"\n\u000f\f\u000f\u00b7\t\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u00bf\b\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0005\u0012\u00c6\b\u0012\n"+
		"\u0012\f\u0012\u00c9\t\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0005\u0018\u00e3\b\u0018\n\u0018\f\u0018"+
		"\u00e6\t\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019"+
		"\u00ec\b\u0019\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001c"+
		"\u0001\u001c\u0003\u001c\u00f4\b\u001c\u0001\u001d\u0001\u001d\u0003\u001d"+
		"\u00f8\b\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u00fd\b"+
		"\u001e\u0001\u001f\u0001\u001f\u0003\u001f\u0101\b\u001f\u0001 \u0001"+
		" \u0001 \u0001 \u0001!\u0001!\u0001!\u0005!\u010a\b!\n!\f!\u010d\t!\u0001"+
		"\"\u0001\"\u0003\"\u0111\b\"\u0001#\u0001#\u0001#\u0001#\u0003#\u0117"+
		"\b#\u0001$\u0001$\u0001%\u0001%\u0001%\u0003%\u011e\b%\u0001%\u0001%\u0001"+
		"%\u0001&\u0001&\u0001&\u0000\u0000\'\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDF"+
		"HJL\u0000\u0005\u0002\u0000 !#\'\u0001\u0000()\u0001\u0000\u0005\t\u0001"+
		"\u0000\n\u000b\u0001\u0000\u000f\u0011\u0123\u0000O\u0001\u0000\u0000"+
		"\u0000\u0002W\u0001\u0000\u0000\u0000\u0004b\u0001\u0000\u0000\u0000\u0006"+
		"p\u0001\u0000\u0000\u0000\bu\u0001\u0000\u0000\u0000\nw\u0001\u0000\u0000"+
		"\u0000\f}\u0001\u0000\u0000\u0000\u000e\u0082\u0001\u0000\u0000\u0000"+
		"\u0010\u008d\u0001\u0000\u0000\u0000\u0012\u0091\u0001\u0000\u0000\u0000"+
		"\u0014\u0093\u0001\u0000\u0000\u0000\u0016\u0095\u0001\u0000\u0000\u0000"+
		"\u0018\u0097\u0001\u0000\u0000\u0000\u001a\u009a\u0001\u0000\u0000\u0000"+
		"\u001c\u00a1\u0001\u0000\u0000\u0000\u001e\u00b0\u0001\u0000\u0000\u0000"+
		" \u00b8\u0001\u0000\u0000\u0000\"\u00bb\u0001\u0000\u0000\u0000$\u00c2"+
		"\u0001\u0000\u0000\u0000&\u00ca\u0001\u0000\u0000\u0000(\u00cc\u0001\u0000"+
		"\u0000\u0000*\u00d4\u0001\u0000\u0000\u0000,\u00d9\u0001\u0000\u0000\u0000"+
		".\u00dc\u0001\u0000\u0000\u00000\u00df\u0001\u0000\u0000\u00002\u00eb"+
		"\u0001\u0000\u0000\u00004\u00ed\u0001\u0000\u0000\u00006\u00ef\u0001\u0000"+
		"\u0000\u00008\u00f3\u0001\u0000\u0000\u0000:\u00f7\u0001\u0000\u0000\u0000"+
		"<\u00fc\u0001\u0000\u0000\u0000>\u00fe\u0001\u0000\u0000\u0000@\u0102"+
		"\u0001\u0000\u0000\u0000B\u0106\u0001\u0000\u0000\u0000D\u0110\u0001\u0000"+
		"\u0000\u0000F\u0116\u0001\u0000\u0000\u0000H\u0118\u0001\u0000\u0000\u0000"+
		"J\u011d\u0001\u0000\u0000\u0000L\u0122\u0001\u0000\u0000\u0000NP\u0003"+
		"\u0002\u0001\u0000ON\u0001\u0000\u0000\u0000PQ\u0001\u0000\u0000\u0000"+
		"QO\u0001\u0000\u0000\u0000QR\u0001\u0000\u0000\u0000R\u0001\u0001\u0000"+
		"\u0000\u0000SX\u0003\u001a\r\u0000TU\u0003\u0018\f\u0000UV\u0005\u0016"+
		"\u0000\u0000VX\u0001\u0000\u0000\u0000WS\u0001\u0000\u0000\u0000WT\u0001"+
		"\u0000\u0000\u0000X\u0003\u0001\u0000\u0000\u0000Ya\u0003\u001c\u000e"+
		"\u0000Z]\u0003\f\u0006\u0000[]\u0003\u000e\u0007\u0000\\Z\u0001\u0000"+
		"\u0000\u0000\\[\u0001\u0000\u0000\u0000]^\u0001\u0000\u0000\u0000^_\u0005"+
		"\u0016\u0000\u0000_a\u0001\u0000\u0000\u0000`Y\u0001\u0000\u0000\u0000"+
		"`\\\u0001\u0000\u0000\u0000ad\u0001\u0000\u0000\u0000b`\u0001\u0000\u0000"+
		"\u0000bc\u0001\u0000\u0000\u0000c\u0005\u0001\u0000\u0000\u0000db\u0001"+
		"\u0000\u0000\u0000ej\u0003\n\u0005\u0000fj\u0003\u000e\u0007\u0000gj\u0003"+
		"\f\u0006\u0000hj\u0003.\u0017\u0000ie\u0001\u0000\u0000\u0000if\u0001"+
		"\u0000\u0000\u0000ig\u0001\u0000\u0000\u0000ih\u0001\u0000\u0000\u0000"+
		"jk\u0001\u0000\u0000\u0000kl\u0005\u0016\u0000\u0000lo\u0001\u0000\u0000"+
		"\u0000mo\u0003\b\u0004\u0000ni\u0001\u0000\u0000\u0000nm\u0001\u0000\u0000"+
		"\u0000or\u0001\u0000\u0000\u0000pn\u0001\u0000\u0000\u0000pq\u0001\u0000"+
		"\u0000\u0000q\u0007\u0001\u0000\u0000\u0000rp\u0001\u0000\u0000\u0000"+
		"sv\u0003(\u0014\u0000tv\u0003,\u0016\u0000us\u0001\u0000\u0000\u0000u"+
		"t\u0001\u0000\u0000\u0000v\t\u0001\u0000\u0000\u0000w{\u0003\u0010\b\u0000"+
		"xy\u0003\u0012\t\u0000yz\u0003\u0010\b\u0000z|\u0001\u0000\u0000\u0000"+
		"{x\u0001\u0000\u0000\u0000{|\u0001\u0000\u0000\u0000|\u000b\u0001\u0000"+
		"\u0000\u0000}~\u0003:\u001d\u0000~\u007f\u0005\u0015\u0000\u0000\u007f"+
		"\r\u0001\u0000\u0000\u0000\u0080\u0083\u0003\f\u0006\u0000\u0081\u0083"+
		"\u00030\u0018\u0000\u0082\u0080\u0001\u0000\u0000\u0000\u0082\u0081\u0001"+
		"\u0000\u0000\u0000\u0083\u0084\u0001\u0000\u0000\u0000\u0084\u0085\u0005"+
		"\"\u0000\u0000\u0085\u0086\u0003\n\u0005\u0000\u0086\u000f\u0001\u0000"+
		"\u0000\u0000\u0087\u008e\u0003\"\u0011\u0000\u0088\u008e\u0005-\u0000"+
		"\u0000\u0089\u008e\u0005,\u0000\u0000\u008a\u008e\u0005\u0015\u0000\u0000"+
		"\u008b\u008e\u0005+\u0000\u0000\u008c\u008e\u00030\u0018\u0000\u008d\u0087"+
		"\u0001\u0000\u0000\u0000\u008d\u0088\u0001\u0000\u0000\u0000\u008d\u0089"+
		"\u0001\u0000\u0000\u0000\u008d\u008a\u0001\u0000\u0000\u0000\u008d\u008b"+
		"\u0001\u0000\u0000\u0000\u008d\u008c\u0001\u0000\u0000\u0000\u008e\u0011"+
		"\u0001\u0000\u0000\u0000\u008f\u0092\u0003\u0016\u000b\u0000\u0090\u0092"+
		"\u0003\u0014\n\u0000\u0091\u008f\u0001\u0000\u0000\u0000\u0091\u0090\u0001"+
		"\u0000\u0000\u0000\u0092\u0013\u0001\u0000\u0000\u0000\u0093\u0094\u0007"+
		"\u0000\u0000\u0000\u0094\u0015\u0001\u0000\u0000\u0000\u0095\u0096\u0007"+
		"\u0001\u0000\u0000\u0096\u0017\u0001\u0000\u0000\u0000\u0097\u0098\u0005"+
		"\u0001\u0000\u0000\u0098\u0099\u00030\u0018\u0000\u0099\u0019\u0001\u0000"+
		"\u0000\u0000\u009a\u009b\u0003L&\u0000\u009b\u009c\u0005\u0002\u0000\u0000"+
		"\u009c\u009d\u0005\u0015\u0000\u0000\u009d\u009e\u0005\u001e\u0000\u0000"+
		"\u009e\u009f\u0003\u0004\u0002\u0000\u009f\u00a0\u0005\u001f\u0000\u0000"+
		"\u00a0\u001b\u0001\u0000\u0000\u0000\u00a1\u00a3\u0003L&\u0000\u00a2\u00a4"+
		"\u0005\u0003\u0000\u0000\u00a3\u00a2\u0001\u0000\u0000\u0000\u00a3\u00a4"+
		"\u0001\u0000\u0000\u0000\u00a4\u00a5\u0001\u0000\u0000\u0000\u00a5\u00a6"+
		"\u00032\u0019\u0000\u00a6\u00a7\u0005\u0015\u0000\u0000\u00a7\u00a9\u0005"+
		"\u001a\u0000\u0000\u00a8\u00aa\u0003\u001e\u000f\u0000\u00a9\u00a8\u0001"+
		"\u0000\u0000\u0000\u00a9\u00aa\u0001\u0000\u0000\u0000\u00aa\u00ab\u0001"+
		"\u0000\u0000\u0000\u00ab\u00ac\u0005\u001b\u0000\u0000\u00ac\u00ad\u0005"+
		"\u001e\u0000\u0000\u00ad\u00ae\u0003\u0006\u0003\u0000\u00ae\u00af\u0005"+
		"\u001f\u0000\u0000\u00af\u001d\u0001\u0000\u0000\u0000\u00b0\u00b5\u0003"+
		" \u0010\u0000\u00b1\u00b2\u0005\u0018\u0000\u0000\u00b2\u00b4\u0003 \u0010"+
		"\u0000\u00b3\u00b1\u0001\u0000\u0000\u0000\u00b4\u00b7\u0001\u0000\u0000"+
		"\u0000\u00b5\u00b3\u0001\u0000\u0000\u0000\u00b5\u00b6\u0001\u0000\u0000"+
		"\u0000\u00b6\u001f\u0001\u0000\u0000\u0000\u00b7\u00b5\u0001\u0000\u0000"+
		"\u0000\u00b8\u00b9\u00032\u0019\u0000\u00b9\u00ba\u0005\u0015\u0000\u0000"+
		"\u00ba!\u0001\u0000\u0000\u0000\u00bb\u00bc\u00030\u0018\u0000\u00bc\u00be"+
		"\u0005\u001a\u0000\u0000\u00bd\u00bf\u0003$\u0012\u0000\u00be\u00bd\u0001"+
		"\u0000\u0000\u0000\u00be\u00bf\u0001\u0000\u0000\u0000\u00bf\u00c0\u0001"+
		"\u0000\u0000\u0000\u00c0\u00c1\u0005\u001b\u0000\u0000\u00c1#\u0001\u0000"+
		"\u0000\u0000\u00c2\u00c7\u0003&\u0013\u0000\u00c3\u00c4\u0005\u0018\u0000"+
		"\u0000\u00c4\u00c6\u0003&\u0013\u0000\u00c5\u00c3\u0001\u0000\u0000\u0000"+
		"\u00c6\u00c9\u0001\u0000\u0000\u0000\u00c7\u00c5\u0001\u0000\u0000\u0000"+
		"\u00c7\u00c8\u0001\u0000\u0000\u0000\u00c8%\u0001\u0000\u0000\u0000\u00c9"+
		"\u00c7\u0001\u0000\u0000\u0000\u00ca\u00cb\u0003\n\u0005\u0000\u00cb\'"+
		"\u0001\u0000\u0000\u0000\u00cc\u00cd\u0005\u0013\u0000\u0000\u00cd\u00ce"+
		"\u0005\u001a\u0000\u0000\u00ce\u00cf\u0003\n\u0005\u0000\u00cf\u00d0\u0005"+
		"\u001b\u0000\u0000\u00d0\u00d1\u0005\u001e\u0000\u0000\u00d1\u00d2\u0003"+
		"\u0006\u0003\u0000\u00d2\u00d3\u0005\u001f\u0000\u0000\u00d3)\u0001\u0000"+
		"\u0000\u0000\u00d4\u00d5\u0005\u0014\u0000\u0000\u00d5\u00d6\u0005\u001e"+
		"\u0000\u0000\u00d6\u00d7\u0003\u0006\u0003\u0000\u00d7\u00d8\u0005\u001f"+
		"\u0000\u0000\u00d8+\u0001\u0000\u0000\u0000\u00d9\u00da\u0003(\u0014\u0000"+
		"\u00da\u00db\u0003*\u0015\u0000\u00db-\u0001\u0000\u0000\u0000\u00dc\u00dd"+
		"\u0005\u0012\u0000\u0000\u00dd\u00de\u0003\n\u0005\u0000\u00de/\u0001"+
		"\u0000\u0000\u0000\u00df\u00e4\u0005\u0015\u0000\u0000\u00e0\u00e1\u0005"+
		"\u0019\u0000\u0000\u00e1\u00e3\u0005\u0015\u0000\u0000\u00e2\u00e0\u0001"+
		"\u0000\u0000\u0000\u00e3\u00e6\u0001\u0000\u0000\u0000\u00e4\u00e2\u0001"+
		"\u0000\u0000\u0000\u00e4\u00e5\u0001\u0000\u0000\u0000\u00e51\u0001\u0000"+
		"\u0000\u0000\u00e6\u00e4\u0001\u0000\u0000\u0000\u00e7\u00ec\u0005\u000e"+
		"\u0000\u0000\u00e8\u00ec\u0003:\u001d\u0000\u00e9\u00ec\u0003J%\u0000"+
		"\u00ea\u00ec\u0003<\u001e\u0000\u00eb\u00e7\u0001\u0000\u0000\u0000\u00eb"+
		"\u00e8\u0001\u0000\u0000\u0000\u00eb\u00e9\u0001\u0000\u0000\u0000\u00eb"+
		"\u00ea\u0001\u0000\u0000\u0000\u00ec3\u0001\u0000\u0000\u0000\u00ed\u00ee"+
		"\u0007\u0002\u0000\u0000\u00ee5\u0001\u0000\u0000\u0000\u00ef\u00f0\u0007"+
		"\u0003\u0000\u0000\u00f07\u0001\u0000\u0000\u0000\u00f1\u00f4\u00034\u001a"+
		"\u0000\u00f2\u00f4\u00036\u001b\u0000\u00f3\u00f1\u0001\u0000\u0000\u0000"+
		"\u00f3\u00f2\u0001\u0000\u0000\u0000\u00f49\u0001\u0000\u0000\u0000\u00f5"+
		"\u00f8\u00038\u001c\u0000\u00f6\u00f8\u0005\u0004\u0000\u0000\u00f7\u00f5"+
		"\u0001\u0000\u0000\u0000\u00f7\u00f6\u0001\u0000\u0000\u0000\u00f8;\u0001"+
		"\u0000\u0000\u0000\u00f9\u00fd\u0003>\u001f\u0000\u00fa\u00fd\u0003H$"+
		"\u0000\u00fb\u00fd\u0003J%\u0000\u00fc\u00f9\u0001\u0000\u0000\u0000\u00fc"+
		"\u00fa\u0001\u0000\u0000\u0000\u00fc\u00fb\u0001\u0000\u0000\u0000\u00fd"+
		"=\u0001\u0000\u0000\u0000\u00fe\u0100\u0005\u0015\u0000\u0000\u00ff\u0101"+
		"\u0003@ \u0000\u0100\u00ff\u0001\u0000\u0000\u0000\u0100\u0101\u0001\u0000"+
		"\u0000\u0000\u0101?\u0001\u0000\u0000\u0000\u0102\u0103\u0005 \u0000\u0000"+
		"\u0103\u0104\u0003B!\u0000\u0104\u0105\u0005!\u0000\u0000\u0105A\u0001"+
		"\u0000\u0000\u0000\u0106\u010b\u0003D\"\u0000\u0107\u0108\u0005\u0018"+
		"\u0000\u0000\u0108\u010a\u0003D\"\u0000\u0109\u0107\u0001\u0000\u0000"+
		"\u0000\u010a\u010d\u0001\u0000\u0000\u0000\u010b\u0109\u0001\u0000\u0000"+
		"\u0000\u010b\u010c\u0001\u0000\u0000\u0000\u010cC\u0001\u0000\u0000\u0000"+
		"\u010d\u010b\u0001\u0000\u0000\u0000\u010e\u0111\u0003<\u001e\u0000\u010f"+
		"\u0111\u0003F#\u0000\u0110\u010e\u0001\u0000\u0000\u0000\u0110\u010f\u0001"+
		"\u0000\u0000\u0000\u0111E\u0001\u0000\u0000\u0000\u0112\u0113\u0005\f"+
		"\u0000\u0000\u0113\u0117\u0003<\u001e\u0000\u0114\u0115\u0005\r\u0000"+
		"\u0000\u0115\u0117\u0003<\u001e\u0000\u0116\u0112\u0001\u0000\u0000\u0000"+
		"\u0116\u0114\u0001\u0000\u0000\u0000\u0117G\u0001\u0000\u0000\u0000\u0118"+
		"\u0119\u0005\u0015\u0000\u0000\u0119I\u0001\u0000\u0000\u0000\u011a\u011e"+
		"\u0003:\u001d\u0000\u011b\u011e\u0003>\u001f\u0000\u011c\u011e\u0003H"+
		"$\u0000\u011d\u011a\u0001\u0000\u0000\u0000\u011d\u011b\u0001\u0000\u0000"+
		"\u0000\u011d\u011c\u0001\u0000\u0000\u0000\u011e\u011f\u0001\u0000\u0000"+
		"\u0000\u011f\u0120\u0005\u001c\u0000\u0000\u0120\u0121\u0005\u001d\u0000"+
		"\u0000\u0121K\u0001\u0000\u0000\u0000\u0122\u0123\u0007\u0004\u0000\u0000"+
		"\u0123M\u0001\u0000\u0000\u0000\u001cQW\\`binpu{\u0082\u008d\u0091\u00a3"+
		"\u00a9\u00b5\u00be\u00c7\u00e4\u00eb\u00f3\u00f7\u00fc\u0100\u010b\u0110"+
		"\u0116\u011d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}