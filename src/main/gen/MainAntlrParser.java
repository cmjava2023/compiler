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
		PACKAGE_KEYWORD=1, CLASS_KEYWORD=2, FALSE=3, TRUE=4, INSTANCE_MODIFIER=5, 
		BOOLEAN_KEYWORD=6, BYTE_KEYWORD=7, SHORT_KEYWORD=8, INT_KEYWORD=9, LONG_KEYWORD=10, 
		CHAR_KEYWORD=11, FLOAT_KEYWORD=12, DOUBLE_KEYWORD=13, ENUM_KEYWORD=14, 
		EXTENDS_KEYWORD=15, SUPER_KEYWORD=16, VOID_KEYWORD=17, PUBLIC_KEYWORD=18, 
		PRIVATE_KEYWORD=19, PROTECTED_KEYWORD=20, RETURN_KEYWORD=21, INSTANCE_KEYWORD=22, 
		IF_KEYWORD=23, ELSE_KEYWORD=24, FOR_KEYWORD=25, WHILE_KEYWORD=26, DO_KEYWORD=27, 
		SWITCH_KEYWORD=28, CASE_KEYWORD=29, BREAK_KEYWORD=30, CONTINUE_KEYWORD=31, 
		DEFAULT_KEYWORD=32, IDENTIFIER=33, SEMICOLON=34, COLON=35, COMMA=36, DOT=37, 
		PAREN_OPEN=38, PAREN_CLOSE=39, BRACKET_OPEN=40, BRACKET_CLOSE=41, CURLY_OPEN=42, 
		CURLY_CLOSE=43, DIAMOND_OPEN=44, DIAMOND_CLOSE=45, EQUALS=46, EQ=47, NEQ=48, 
		GTE=49, LTE=50, MOD=51, PLUS=52, MINUS=53, MULTIPLICATION=54, DIVISION=55, 
		INC=56, DEC=57, LAND=58, LOR=59, NOT=60, NOTNOT=61, BAND=62, BOR=63, BIT_SHIFT_L=64, 
		BIT_SHIFT_R=65, BXOR=66, COMMENT=67, LINE_COMMENT=68, WHITESPACE=69, STRING=70, 
		CHARACTER=71, FLOAT=72, DECIMAL=73, INTEGER=74, LONG=75, FLOAT_EXPONENT_SUFFIX=76, 
		FLOATING_POINT_SUFFIX=77, LONG_SUFFIX=78;
	public static final int
		RULE_start = 0, RULE_global_scope = 1, RULE_class_scope = 2, RULE_function_scope = 3, 
		RULE_block_scope = 4, RULE_expressions = 5, RULE_variable_declaration = 6, 
		RULE_assignment = 7, RULE_expression = 8, RULE_expression_operator = 9, 
		RULE_expression_concatinator = 10, RULE_expression_suffix = 11, RULE_instantiation = 12, 
		RULE_access_index = 13, RULE_numerical_comparison_operator = 14, RULE_numerical_prefix = 15, 
		RULE_logical_prefix = 16, RULE_logical_comparison_operator = 17, RULE_bit_comparison_operator = 18, 
		RULE_package_declaration = 19, RULE_class_declaration = 20, RULE_enum_declaration = 21, 
		RULE_if_statement = 22, RULE_else_statement = 23, RULE_if_else_statement = 24, 
		RULE_switch_statement = 25, RULE_switch_scope = 26, RULE_return_statement = 27, 
		RULE_break_statement = 28, RULE_continue_statement = 29, RULE_identifier = 30, 
		RULE_type = 31, RULE_integral_type = 32, RULE_floating_point_type = 33, 
		RULE_numeric_type = 34, RULE_primitive_type = 35, RULE_reference_type = 36, 
		RULE_class_type = 37, RULE_type_arguments = 38, RULE_type_argument_list = 39, 
		RULE_type_argument = 40, RULE_wildcard = 41, RULE_type_variable = 42, 
		RULE_array_type = 43, RULE_array_expression = 44, RULE_while_loop = 45, 
		RULE_do_while_loop = 46, RULE_for_loop = 47, RULE_for_init = 48, RULE_for_termination = 49, 
		RULE_for_update = 50, RULE_casting = 51, RULE_access_modifier = 52, RULE_function_declaration = 53, 
		RULE_function_declaration_args = 54, RULE_function_declaration_arg = 55, 
		RULE_function_call = 56, RULE_function_args = 57, RULE_function_arg = 58;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "global_scope", "class_scope", "function_scope", "block_scope", 
			"expressions", "variable_declaration", "assignment", "expression", "expression_operator", 
			"expression_concatinator", "expression_suffix", "instantiation", "access_index", 
			"numerical_comparison_operator", "numerical_prefix", "logical_prefix", 
			"logical_comparison_operator", "bit_comparison_operator", "package_declaration", 
			"class_declaration", "enum_declaration", "if_statement", "else_statement", 
			"if_else_statement", "switch_statement", "switch_scope", "return_statement", 
			"break_statement", "continue_statement", "identifier", "type", "integral_type", 
			"floating_point_type", "numeric_type", "primitive_type", "reference_type", 
			"class_type", "type_arguments", "type_argument_list", "type_argument", 
			"wildcard", "type_variable", "array_type", "array_expression", "while_loop", 
			"do_while_loop", "for_loop", "for_init", "for_termination", "for_update", 
			"casting", "access_modifier", "function_declaration", "function_declaration_args", 
			"function_declaration_arg", "function_call", "function_args", "function_arg"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'package'", "'class'", "'false'", "'true'", "'static'", "'boolean'", 
			"'byte'", "'short'", "'int'", "'long'", "'char'", "'float'", "'double'", 
			"'enum'", "'extends'", "'super'", "'void'", "'public'", "'private'", 
			"'protected'", "'return'", "'new'", "'if'", "'else'", "'for'", "'while'", 
			"'do'", "'switch'", "'case'", "'break'", "'continue'", "'default'", null, 
			"';'", "':'", "','", "'.'", "'('", "')'", "'['", "']'", "'{'", "'}'", 
			"'<'", "'>'", "'='", "'=='", "'!='", "'>='", "'<='", "'%'", "'+'", "'-'", 
			"'*'", "'/'", "'++'", "'--'", "'&&'", "'||'", "'!'", "'!!'", "'&'", "'|'", 
			null, null, "'^'", null, null, null, null, null, null, null, null, null, 
			"'e'", "'f'", "'L'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PACKAGE_KEYWORD", "CLASS_KEYWORD", "FALSE", "TRUE", "INSTANCE_MODIFIER", 
			"BOOLEAN_KEYWORD", "BYTE_KEYWORD", "SHORT_KEYWORD", "INT_KEYWORD", "LONG_KEYWORD", 
			"CHAR_KEYWORD", "FLOAT_KEYWORD", "DOUBLE_KEYWORD", "ENUM_KEYWORD", "EXTENDS_KEYWORD", 
			"SUPER_KEYWORD", "VOID_KEYWORD", "PUBLIC_KEYWORD", "PRIVATE_KEYWORD", 
			"PROTECTED_KEYWORD", "RETURN_KEYWORD", "INSTANCE_KEYWORD", "IF_KEYWORD", 
			"ELSE_KEYWORD", "FOR_KEYWORD", "WHILE_KEYWORD", "DO_KEYWORD", "SWITCH_KEYWORD", 
			"CASE_KEYWORD", "BREAK_KEYWORD", "CONTINUE_KEYWORD", "DEFAULT_KEYWORD", 
			"IDENTIFIER", "SEMICOLON", "COLON", "COMMA", "DOT", "PAREN_OPEN", "PAREN_CLOSE", 
			"BRACKET_OPEN", "BRACKET_CLOSE", "CURLY_OPEN", "CURLY_CLOSE", "DIAMOND_OPEN", 
			"DIAMOND_CLOSE", "EQUALS", "EQ", "NEQ", "GTE", "LTE", "MOD", "PLUS", 
			"MINUS", "MULTIPLICATION", "DIVISION", "INC", "DEC", "LAND", "LOR", "NOT", 
			"NOTNOT", "BAND", "BOR", "BIT_SHIFT_L", "BIT_SHIFT_R", "BXOR", "COMMENT", 
			"LINE_COMMENT", "WHITESPACE", "STRING", "CHARACTER", "FLOAT", "DECIMAL", 
			"INTEGER", "LONG", "FLOAT_EXPONENT_SUFFIX", "FLOATING_POINT_SUFFIX", 
			"LONG_SUFFIX"
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
			setState(119); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(118);
				global_scope();
				}
				}
				setState(121); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 1835010L) != 0) );
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
			setState(127);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PUBLIC_KEYWORD:
			case PRIVATE_KEYWORD:
			case PROTECTED_KEYWORD:
				enterOuterAlt(_localctx, 1);
				{
				setState(123);
				class_declaration();
				}
				break;
			case PACKAGE_KEYWORD:
				enterOuterAlt(_localctx, 2);
				{
				setState(124);
				package_declaration();
				setState(125);
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
		public List<Enum_declarationContext> enum_declaration() {
			return getRuleContexts(Enum_declarationContext.class);
		}
		public Enum_declarationContext enum_declaration(int i) {
			return getRuleContext(Enum_declarationContext.class,i);
		}
		public List<Class_declarationContext> class_declaration() {
			return getRuleContexts(Class_declarationContext.class);
		}
		public Class_declarationContext class_declaration(int i) {
			return getRuleContext(Class_declarationContext.class,i);
		}
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
			setState(140);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8591802304L) != 0)) {
				{
				setState(138);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(129);
					enum_declaration();
					}
					break;
				case 2:
					{
					setState(130);
					class_declaration();
					}
					break;
				case 3:
					{
					setState(131);
					function_declaration();
					}
					break;
				case 4:
					{
					{
					setState(134);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
					case 1:
						{
						setState(132);
						variable_declaration();
						}
						break;
					case 2:
						{
						setState(133);
						assignment();
						}
						break;
					}
					setState(136);
					match(SEMICOLON);
					}
					}
					break;
				}
				}
				setState(142);
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
		public List<Enum_declarationContext> enum_declaration() {
			return getRuleContexts(Enum_declarationContext.class);
		}
		public Enum_declarationContext enum_declaration(int i) {
			return getRuleContext(Enum_declarationContext.class,i);
		}
		public List<Block_scopeContext> block_scope() {
			return getRuleContexts(Block_scopeContext.class);
		}
		public Block_scopeContext block_scope(int i) {
			return getRuleContext(Block_scopeContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(MainAntlrParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(MainAntlrParser.SEMICOLON, i);
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
		public List<Continue_statementContext> continue_statement() {
			return getRuleContexts(Continue_statementContext.class);
		}
		public Continue_statementContext continue_statement(int i) {
			return getRuleContext(Continue_statementContext.class,i);
		}
		public List<Break_statementContext> break_statement() {
			return getRuleContexts(Break_statementContext.class);
		}
		public Break_statementContext break_statement(int i) {
			return getRuleContext(Break_statementContext.class,i);
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
			setState(157);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 3472279997956259800L) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & 63L) != 0)) {
				{
				setState(155);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ENUM_KEYWORD:
					{
					setState(143);
					enum_declaration();
					}
					break;
				case IF_KEYWORD:
				case FOR_KEYWORD:
				case WHILE_KEYWORD:
				case DO_KEYWORD:
				case SWITCH_KEYWORD:
					{
					setState(144);
					block_scope();
					}
					break;
				case FALSE:
				case TRUE:
				case BOOLEAN_KEYWORD:
				case BYTE_KEYWORD:
				case SHORT_KEYWORD:
				case INT_KEYWORD:
				case LONG_KEYWORD:
				case CHAR_KEYWORD:
				case FLOAT_KEYWORD:
				case DOUBLE_KEYWORD:
				case RETURN_KEYWORD:
				case INSTANCE_KEYWORD:
				case BREAK_KEYWORD:
				case CONTINUE_KEYWORD:
				case IDENTIFIER:
				case PAREN_OPEN:
				case CURLY_OPEN:
				case PLUS:
				case MINUS:
				case NOT:
				case NOTNOT:
				case STRING:
				case CHARACTER:
				case FLOAT:
				case DECIMAL:
				case INTEGER:
				case LONG:
					{
					setState(151);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						setState(145);
						expressions();
						}
						break;
					case 2:
						{
						setState(146);
						assignment();
						}
						break;
					case 3:
						{
						setState(147);
						variable_declaration();
						}
						break;
					case 4:
						{
						setState(148);
						return_statement();
						}
						break;
					case 5:
						{
						setState(149);
						continue_statement();
						}
						break;
					case 6:
						{
						setState(150);
						break_statement();
						}
						break;
					}
					setState(153);
					match(SEMICOLON);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(159);
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
		public While_loopContext while_loop() {
			return getRuleContext(While_loopContext.class,0);
		}
		public Do_while_loopContext do_while_loop() {
			return getRuleContext(Do_while_loopContext.class,0);
		}
		public For_loopContext for_loop() {
			return getRuleContext(For_loopContext.class,0);
		}
		public Switch_statementContext switch_statement() {
			return getRuleContext(Switch_statementContext.class,0);
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
			setState(166);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(160);
				if_statement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(161);
				if_else_statement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(162);
				while_loop();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(163);
				do_while_loop();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(164);
				for_loop();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(165);
				switch_statement();
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			expression(0);
			setState(172);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(169);
				expression_operator();
				setState(170);
				expression(0);
				}
				break;
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
		public TerminalNode IDENTIFIER() { return getToken(MainAntlrParser.IDENTIFIER, 0); }
		public Primitive_typeContext primitive_type() {
			return getRuleContext(Primitive_typeContext.class,0);
		}
		public Reference_typeContext reference_type() {
			return getRuleContext(Reference_typeContext.class,0);
		}
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
			setState(176);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(174);
				primitive_type();
				}
				break;
			case 2:
				{
				setState(175);
				reference_type();
				}
				break;
			}
			setState(178);
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
			setState(182);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(180);
				variable_declaration();
				}
				break;
			case 2:
				{
				setState(181);
				identifier();
				}
				break;
			}
			setState(184);
			match(EQUALS);
			setState(185);
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
		public TerminalNode IDENTIFIER() { return getToken(MainAntlrParser.IDENTIFIER, 0); }
		public TerminalNode STRING() { return getToken(MainAntlrParser.STRING, 0); }
		public TerminalNode CHARACTER() { return getToken(MainAntlrParser.CHARACTER, 0); }
		public TerminalNode FLOAT() { return getToken(MainAntlrParser.FLOAT, 0); }
		public TerminalNode DECIMAL() { return getToken(MainAntlrParser.DECIMAL, 0); }
		public TerminalNode INTEGER() { return getToken(MainAntlrParser.INTEGER, 0); }
		public TerminalNode LONG() { return getToken(MainAntlrParser.LONG, 0); }
		public TerminalNode FALSE() { return getToken(MainAntlrParser.FALSE, 0); }
		public TerminalNode TRUE() { return getToken(MainAntlrParser.TRUE, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public CastingContext casting() {
			return getRuleContext(CastingContext.class,0);
		}
		public TerminalNode PAREN_OPEN() { return getToken(MainAntlrParser.PAREN_OPEN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode PAREN_CLOSE() { return getToken(MainAntlrParser.PAREN_CLOSE, 0); }
		public Array_expressionContext array_expression() {
			return getRuleContext(Array_expressionContext.class,0);
		}
		public InstantiationContext instantiation() {
			return getRuleContext(InstantiationContext.class,0);
		}
		public Access_indexContext access_index() {
			return getRuleContext(Access_indexContext.class,0);
		}
		public ExpressionsContext expressions() {
			return getRuleContext(ExpressionsContext.class,0);
		}
		public Numerical_prefixContext numerical_prefix() {
			return getRuleContext(Numerical_prefixContext.class,0);
		}
		public Logical_prefixContext logical_prefix() {
			return getRuleContext(Logical_prefixContext.class,0);
		}
		public Expression_concatinatorContext expression_concatinator() {
			return getRuleContext(Expression_concatinatorContext.class,0);
		}
		public Expression_suffixContext expression_suffix() {
			return getRuleContext(Expression_suffixContext.class,0);
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
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(213);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(188);
				function_call();
				}
				break;
			case 2:
				{
				setState(189);
				match(IDENTIFIER);
				}
				break;
			case 3:
				{
				setState(190);
				match(STRING);
				}
				break;
			case 4:
				{
				setState(191);
				match(CHARACTER);
				}
				break;
			case 5:
				{
				setState(192);
				match(FLOAT);
				}
				break;
			case 6:
				{
				setState(193);
				match(DECIMAL);
				}
				break;
			case 7:
				{
				setState(194);
				match(INTEGER);
				}
				break;
			case 8:
				{
				setState(195);
				match(LONG);
				}
				break;
			case 9:
				{
				setState(196);
				match(FALSE);
				}
				break;
			case 10:
				{
				setState(197);
				match(TRUE);
				}
				break;
			case 11:
				{
				setState(198);
				identifier();
				}
				break;
			case 12:
				{
				setState(199);
				casting();
				}
				break;
			case 13:
				{
				setState(200);
				match(PAREN_OPEN);
				setState(201);
				expression(0);
				setState(202);
				match(PAREN_CLOSE);
				}
				break;
			case 14:
				{
				setState(204);
				array_expression();
				}
				break;
			case 15:
				{
				setState(205);
				instantiation();
				}
				break;
			case 16:
				{
				setState(206);
				access_index();
				}
				break;
			case 17:
				{
				setState(209);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case PLUS:
				case MINUS:
					{
					setState(207);
					numerical_prefix();
					}
					break;
				case NOT:
				case NOTNOT:
					{
					setState(208);
					logical_prefix();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(211);
				expressions();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(223);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(221);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(215);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(216);
						expression_concatinator();
						setState(217);
						expression(7);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(219);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(220);
						expression_suffix();
						}
						break;
					}
					} 
				}
				setState(225);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
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
		public Bit_comparison_operatorContext bit_comparison_operator() {
			return getRuleContext(Bit_comparison_operatorContext.class,0);
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
			setState(229);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LAND:
			case LOR:
				enterOuterAlt(_localctx, 1);
				{
				setState(226);
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
				setState(227);
				numerical_comparison_operator();
				}
				break;
			case BAND:
			case BOR:
			case BIT_SHIFT_L:
			case BIT_SHIFT_R:
			case BXOR:
				enterOuterAlt(_localctx, 3);
				{
				setState(228);
				bit_comparison_operator();
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
	public static class Expression_concatinatorContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(MainAntlrParser.PLUS, 0); }
		public TerminalNode DIVISION() { return getToken(MainAntlrParser.DIVISION, 0); }
		public TerminalNode MULTIPLICATION() { return getToken(MainAntlrParser.MULTIPLICATION, 0); }
		public TerminalNode MINUS() { return getToken(MainAntlrParser.MINUS, 0); }
		public TerminalNode MOD() { return getToken(MainAntlrParser.MOD, 0); }
		public TerminalNode DOT() { return getToken(MainAntlrParser.DOT, 0); }
		public Expression_concatinatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression_concatinator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterExpression_concatinator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitExpression_concatinator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitExpression_concatinator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression_concatinatorContext expression_concatinator() throws RecognitionException {
		Expression_concatinatorContext _localctx = new Expression_concatinatorContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_expression_concatinator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 69805931663196160L) != 0)) ) {
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
	public static class Expression_suffixContext extends ParserRuleContext {
		public TerminalNode DEC() { return getToken(MainAntlrParser.DEC, 0); }
		public TerminalNode INC() { return getToken(MainAntlrParser.INC, 0); }
		public Expression_suffixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression_suffix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterExpression_suffix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitExpression_suffix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitExpression_suffix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression_suffixContext expression_suffix() throws RecognitionException {
		Expression_suffixContext _localctx = new Expression_suffixContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_expression_suffix);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			_la = _input.LA(1);
			if ( !(_la==INC || _la==DEC) ) {
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
	public static class InstantiationContext extends ParserRuleContext {
		public TerminalNode INSTANCE_KEYWORD() { return getToken(MainAntlrParser.INSTANCE_KEYWORD, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<TerminalNode> BRACKET_OPEN() { return getTokens(MainAntlrParser.BRACKET_OPEN); }
		public TerminalNode BRACKET_OPEN(int i) {
			return getToken(MainAntlrParser.BRACKET_OPEN, i);
		}
		public List<TerminalNode> INTEGER() { return getTokens(MainAntlrParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(MainAntlrParser.INTEGER, i);
		}
		public List<TerminalNode> BRACKET_CLOSE() { return getTokens(MainAntlrParser.BRACKET_CLOSE); }
		public TerminalNode BRACKET_CLOSE(int i) {
			return getToken(MainAntlrParser.BRACKET_CLOSE, i);
		}
		public InstantiationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instantiation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterInstantiation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitInstantiation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitInstantiation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstantiationContext instantiation() throws RecognitionException {
		InstantiationContext _localctx = new InstantiationContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_instantiation);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			match(INSTANCE_KEYWORD);
			setState(245);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(236);
				type();
				setState(240); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(237);
						match(BRACKET_OPEN);
						setState(238);
						match(INTEGER);
						setState(239);
						match(BRACKET_CLOSE);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(242); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 2:
				{
				setState(244);
				type();
				}
				break;
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
	public static class Access_indexContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(MainAntlrParser.IDENTIFIER, 0); }
		public List<TerminalNode> BRACKET_OPEN() { return getTokens(MainAntlrParser.BRACKET_OPEN); }
		public TerminalNode BRACKET_OPEN(int i) {
			return getToken(MainAntlrParser.BRACKET_OPEN, i);
		}
		public List<TerminalNode> INTEGER() { return getTokens(MainAntlrParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(MainAntlrParser.INTEGER, i);
		}
		public List<TerminalNode> BRACKET_CLOSE() { return getTokens(MainAntlrParser.BRACKET_CLOSE); }
		public TerminalNode BRACKET_CLOSE(int i) {
			return getToken(MainAntlrParser.BRACKET_CLOSE, i);
		}
		public Access_indexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_access_index; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterAccess_index(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitAccess_index(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitAccess_index(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Access_indexContext access_index() throws RecognitionException {
		Access_indexContext _localctx = new Access_indexContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_access_index);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			match(IDENTIFIER);
			setState(251); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(248);
					match(BRACKET_OPEN);
					setState(249);
					match(INTEGER);
					setState(250);
					match(BRACKET_CLOSE);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(253); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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
		enterRule(_localctx, 28, RULE_numerical_comparison_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4415638697148416L) != 0)) ) {
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
	public static class Numerical_prefixContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(MainAntlrParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(MainAntlrParser.MINUS, 0); }
		public Numerical_prefixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numerical_prefix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterNumerical_prefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitNumerical_prefix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitNumerical_prefix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Numerical_prefixContext numerical_prefix() throws RecognitionException {
		Numerical_prefixContext _localctx = new Numerical_prefixContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_numerical_prefix);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			_la = _input.LA(1);
			if ( !(_la==PLUS || _la==MINUS) ) {
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
	public static class Logical_prefixContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(MainAntlrParser.NOT, 0); }
		public TerminalNode NOTNOT() { return getToken(MainAntlrParser.NOTNOT, 0); }
		public Logical_prefixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logical_prefix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterLogical_prefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitLogical_prefix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitLogical_prefix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Logical_prefixContext logical_prefix() throws RecognitionException {
		Logical_prefixContext _localctx = new Logical_prefixContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_logical_prefix);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			_la = _input.LA(1);
			if ( !(_la==NOT || _la==NOTNOT) ) {
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
		enterRule(_localctx, 34, RULE_logical_comparison_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261);
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
	public static class Bit_comparison_operatorContext extends ParserRuleContext {
		public TerminalNode BAND() { return getToken(MainAntlrParser.BAND, 0); }
		public TerminalNode BOR() { return getToken(MainAntlrParser.BOR, 0); }
		public TerminalNode BXOR() { return getToken(MainAntlrParser.BXOR, 0); }
		public TerminalNode BIT_SHIFT_L() { return getToken(MainAntlrParser.BIT_SHIFT_L, 0); }
		public TerminalNode BIT_SHIFT_R() { return getToken(MainAntlrParser.BIT_SHIFT_R, 0); }
		public Bit_comparison_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bit_comparison_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterBit_comparison_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitBit_comparison_operator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitBit_comparison_operator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Bit_comparison_operatorContext bit_comparison_operator() throws RecognitionException {
		Bit_comparison_operatorContext _localctx = new Bit_comparison_operatorContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_bit_comparison_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
			_la = _input.LA(1);
			if ( !(((((_la - 62)) & ~0x3f) == 0 && ((1L << (_la - 62)) & 31L) != 0)) ) {
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
		enterRule(_localctx, 38, RULE_package_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(265);
			match(PACKAGE_KEYWORD);
			setState(266);
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
		enterRule(_localctx, 40, RULE_class_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
			access_modifier();
			setState(269);
			match(CLASS_KEYWORD);
			setState(270);
			match(IDENTIFIER);
			setState(271);
			match(CURLY_OPEN);
			setState(272);
			class_scope();
			setState(273);
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
	public static class Enum_declarationContext extends ParserRuleContext {
		public TerminalNode ENUM_KEYWORD() { return getToken(MainAntlrParser.ENUM_KEYWORD, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(MainAntlrParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(MainAntlrParser.IDENTIFIER, i);
		}
		public TerminalNode CURLY_OPEN() { return getToken(MainAntlrParser.CURLY_OPEN, 0); }
		public TerminalNode CURLY_CLOSE() { return getToken(MainAntlrParser.CURLY_CLOSE, 0); }
		public List<TerminalNode> COMMA() { return getTokens(MainAntlrParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MainAntlrParser.COMMA, i);
		}
		public Enum_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enum_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterEnum_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitEnum_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitEnum_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Enum_declarationContext enum_declaration() throws RecognitionException {
		Enum_declarationContext _localctx = new Enum_declarationContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_enum_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275);
			match(ENUM_KEYWORD);
			setState(276);
			match(IDENTIFIER);
			setState(277);
			match(CURLY_OPEN);
			setState(278);
			match(IDENTIFIER);
			setState(283);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(279);
				match(COMMA);
				setState(280);
				match(IDENTIFIER);
				}
				}
				setState(285);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(286);
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
		enterRule(_localctx, 44, RULE_if_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
			match(IF_KEYWORD);
			setState(289);
			match(PAREN_OPEN);
			setState(290);
			expressions();
			setState(291);
			match(PAREN_CLOSE);
			setState(292);
			match(CURLY_OPEN);
			setState(293);
			function_scope();
			setState(294);
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
		enterRule(_localctx, 46, RULE_else_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(296);
			match(ELSE_KEYWORD);
			setState(297);
			match(CURLY_OPEN);
			setState(298);
			function_scope();
			setState(299);
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
		enterRule(_localctx, 48, RULE_if_else_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(301);
			if_statement();
			setState(302);
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
	public static class Switch_statementContext extends ParserRuleContext {
		public TerminalNode SWITCH_KEYWORD() { return getToken(MainAntlrParser.SWITCH_KEYWORD, 0); }
		public TerminalNode PAREN_OPEN() { return getToken(MainAntlrParser.PAREN_OPEN, 0); }
		public ExpressionsContext expressions() {
			return getRuleContext(ExpressionsContext.class,0);
		}
		public TerminalNode PAREN_CLOSE() { return getToken(MainAntlrParser.PAREN_CLOSE, 0); }
		public TerminalNode CURLY_OPEN() { return getToken(MainAntlrParser.CURLY_OPEN, 0); }
		public Switch_scopeContext switch_scope() {
			return getRuleContext(Switch_scopeContext.class,0);
		}
		public TerminalNode CURLY_CLOSE() { return getToken(MainAntlrParser.CURLY_CLOSE, 0); }
		public Switch_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switch_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterSwitch_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitSwitch_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitSwitch_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Switch_statementContext switch_statement() throws RecognitionException {
		Switch_statementContext _localctx = new Switch_statementContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_switch_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304);
			match(SWITCH_KEYWORD);
			setState(305);
			match(PAREN_OPEN);
			setState(306);
			expressions();
			setState(307);
			match(PAREN_CLOSE);
			setState(308);
			match(CURLY_OPEN);
			setState(309);
			switch_scope();
			setState(310);
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
	public static class Switch_scopeContext extends ParserRuleContext {
		public TerminalNode DEFAULT_KEYWORD() { return getToken(MainAntlrParser.DEFAULT_KEYWORD, 0); }
		public List<TerminalNode> COLON() { return getTokens(MainAntlrParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(MainAntlrParser.COLON, i);
		}
		public List<Function_scopeContext> function_scope() {
			return getRuleContexts(Function_scopeContext.class);
		}
		public Function_scopeContext function_scope(int i) {
			return getRuleContext(Function_scopeContext.class,i);
		}
		public List<TerminalNode> CASE_KEYWORD() { return getTokens(MainAntlrParser.CASE_KEYWORD); }
		public TerminalNode CASE_KEYWORD(int i) {
			return getToken(MainAntlrParser.CASE_KEYWORD, i);
		}
		public List<ExpressionsContext> expressions() {
			return getRuleContexts(ExpressionsContext.class);
		}
		public ExpressionsContext expressions(int i) {
			return getRuleContext(ExpressionsContext.class,i);
		}
		public Switch_scopeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switch_scope; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterSwitch_scope(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitSwitch_scope(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitSwitch_scope(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Switch_scopeContext switch_scope() throws RecognitionException {
		Switch_scopeContext _localctx = new Switch_scopeContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_switch_scope);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(320);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CASE_KEYWORD) {
				{
				{
				{
				setState(312);
				match(CASE_KEYWORD);
				{
				setState(313);
				expressions();
				}
				}
				setState(315);
				match(COLON);
				setState(316);
				function_scope();
				}
				}
				setState(322);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(323);
			match(DEFAULT_KEYWORD);
			setState(324);
			match(COLON);
			setState(325);
			function_scope();
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
		enterRule(_localctx, 54, RULE_return_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(327);
			match(RETURN_KEYWORD);
			setState(328);
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
	public static class Break_statementContext extends ParserRuleContext {
		public TerminalNode BREAK_KEYWORD() { return getToken(MainAntlrParser.BREAK_KEYWORD, 0); }
		public Break_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_break_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterBreak_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitBreak_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitBreak_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Break_statementContext break_statement() throws RecognitionException {
		Break_statementContext _localctx = new Break_statementContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_break_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(330);
			match(BREAK_KEYWORD);
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
	public static class Continue_statementContext extends ParserRuleContext {
		public TerminalNode CONTINUE_KEYWORD() { return getToken(MainAntlrParser.CONTINUE_KEYWORD, 0); }
		public Continue_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continue_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterContinue_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitContinue_statement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitContinue_statement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Continue_statementContext continue_statement() throws RecognitionException {
		Continue_statementContext _localctx = new Continue_statementContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_continue_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(332);
			match(CONTINUE_KEYWORD);
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
		enterRule(_localctx, 60, RULE_identifier);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(334);
			match(IDENTIFIER);
			setState(339);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(335);
					match(DOT);
					setState(336);
					match(IDENTIFIER);
					}
					} 
				}
				setState(341);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
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
		enterRule(_localctx, 62, RULE_type);
		try {
			setState(346);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(342);
				match(VOID_KEYWORD);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(343);
				primitive_type();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(344);
				array_type();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(345);
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
		enterRule(_localctx, 64, RULE_integral_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(348);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 3968L) != 0)) ) {
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
		enterRule(_localctx, 66, RULE_floating_point_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(350);
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
		enterRule(_localctx, 68, RULE_numeric_type);
		try {
			setState(354);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BYTE_KEYWORD:
			case SHORT_KEYWORD:
			case INT_KEYWORD:
			case LONG_KEYWORD:
			case CHAR_KEYWORD:
				enterOuterAlt(_localctx, 1);
				{
				setState(352);
				integral_type();
				}
				break;
			case FLOAT_KEYWORD:
			case DOUBLE_KEYWORD:
				enterOuterAlt(_localctx, 2);
				{
				setState(353);
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
		enterRule(_localctx, 70, RULE_primitive_type);
		try {
			setState(358);
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
				setState(356);
				numeric_type();
				}
				break;
			case BOOLEAN_KEYWORD:
				enterOuterAlt(_localctx, 2);
				{
				setState(357);
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
		enterRule(_localctx, 72, RULE_reference_type);
		try {
			setState(363);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(360);
				class_type();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(361);
				type_variable();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(362);
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
		enterRule(_localctx, 74, RULE_class_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(365);
			match(IDENTIFIER);
			setState(367);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				{
				setState(366);
				type_arguments();
				}
				break;
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
		enterRule(_localctx, 76, RULE_type_arguments);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(369);
			match(DIAMOND_OPEN);
			setState(370);
			type_argument_list();
			setState(371);
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
		enterRule(_localctx, 78, RULE_type_argument_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(373);
			type_argument();
			setState(378);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(374);
				match(COMMA);
				setState(375);
				type_argument();
				}
				}
				setState(380);
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
		enterRule(_localctx, 80, RULE_type_argument);
		try {
			setState(383);
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
				setState(381);
				reference_type();
				}
				break;
			case EXTENDS_KEYWORD:
			case SUPER_KEYWORD:
				enterOuterAlt(_localctx, 2);
				{
				setState(382);
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
		enterRule(_localctx, 82, RULE_wildcard);
		try {
			setState(389);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EXTENDS_KEYWORD:
				enterOuterAlt(_localctx, 1);
				{
				setState(385);
				match(EXTENDS_KEYWORD);
				setState(386);
				reference_type();
				}
				break;
			case SUPER_KEYWORD:
				enterOuterAlt(_localctx, 2);
				{
				setState(387);
				match(SUPER_KEYWORD);
				setState(388);
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
		enterRule(_localctx, 84, RULE_type_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(391);
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
		public Primitive_typeContext primitive_type() {
			return getRuleContext(Primitive_typeContext.class,0);
		}
		public Class_typeContext class_type() {
			return getRuleContext(Class_typeContext.class,0);
		}
		public Type_variableContext type_variable() {
			return getRuleContext(Type_variableContext.class,0);
		}
		public List<TerminalNode> BRACKET_OPEN() { return getTokens(MainAntlrParser.BRACKET_OPEN); }
		public TerminalNode BRACKET_OPEN(int i) {
			return getToken(MainAntlrParser.BRACKET_OPEN, i);
		}
		public List<TerminalNode> BRACKET_CLOSE() { return getTokens(MainAntlrParser.BRACKET_CLOSE); }
		public TerminalNode BRACKET_CLOSE(int i) {
			return getToken(MainAntlrParser.BRACKET_CLOSE, i);
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
		enterRule(_localctx, 86, RULE_array_type);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(396);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				{
				setState(393);
				primitive_type();
				}
				break;
			case 2:
				{
				setState(394);
				class_type();
				}
				break;
			case 3:
				{
				setState(395);
				type_variable();
				}
				break;
			}
			setState(400); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(398);
					match(BRACKET_OPEN);
					setState(399);
					match(BRACKET_CLOSE);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(402); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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
	public static class Array_expressionContext extends ParserRuleContext {
		public TerminalNode CURLY_OPEN() { return getToken(MainAntlrParser.CURLY_OPEN, 0); }
		public TerminalNode CURLY_CLOSE() { return getToken(MainAntlrParser.CURLY_CLOSE, 0); }
		public List<ExpressionsContext> expressions() {
			return getRuleContexts(ExpressionsContext.class);
		}
		public ExpressionsContext expressions(int i) {
			return getRuleContext(ExpressionsContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MainAntlrParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MainAntlrParser.COMMA, i);
		}
		public Array_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterArray_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitArray_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitArray_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Array_expressionContext array_expression() throws RecognitionException {
		Array_expressionContext _localctx = new Array_expressionContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_array_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(404);
			match(CURLY_OPEN);
			{
			{
			setState(405);
			expressions();
			setState(410);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(406);
				match(COMMA);
				setState(407);
				expressions();
				}
				}
				setState(412);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			}
			setState(413);
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
	public static class While_loopContext extends ParserRuleContext {
		public TerminalNode WHILE_KEYWORD() { return getToken(MainAntlrParser.WHILE_KEYWORD, 0); }
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
		public While_loopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_while_loop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterWhile_loop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitWhile_loop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitWhile_loop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final While_loopContext while_loop() throws RecognitionException {
		While_loopContext _localctx = new While_loopContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_while_loop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(415);
			match(WHILE_KEYWORD);
			setState(416);
			match(PAREN_OPEN);
			setState(417);
			expressions();
			setState(418);
			match(PAREN_CLOSE);
			setState(419);
			match(CURLY_OPEN);
			setState(420);
			function_scope();
			setState(421);
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
	public static class Do_while_loopContext extends ParserRuleContext {
		public TerminalNode DO_KEYWORD() { return getToken(MainAntlrParser.DO_KEYWORD, 0); }
		public TerminalNode CURLY_OPEN() { return getToken(MainAntlrParser.CURLY_OPEN, 0); }
		public Function_scopeContext function_scope() {
			return getRuleContext(Function_scopeContext.class,0);
		}
		public TerminalNode CURLY_CLOSE() { return getToken(MainAntlrParser.CURLY_CLOSE, 0); }
		public TerminalNode WHILE_KEYWORD() { return getToken(MainAntlrParser.WHILE_KEYWORD, 0); }
		public TerminalNode PAREN_OPEN() { return getToken(MainAntlrParser.PAREN_OPEN, 0); }
		public ExpressionsContext expressions() {
			return getRuleContext(ExpressionsContext.class,0);
		}
		public TerminalNode PAREN_CLOSE() { return getToken(MainAntlrParser.PAREN_CLOSE, 0); }
		public Do_while_loopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_do_while_loop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterDo_while_loop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitDo_while_loop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitDo_while_loop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Do_while_loopContext do_while_loop() throws RecognitionException {
		Do_while_loopContext _localctx = new Do_while_loopContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_do_while_loop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(423);
			match(DO_KEYWORD);
			setState(424);
			match(CURLY_OPEN);
			setState(425);
			function_scope();
			setState(426);
			match(CURLY_CLOSE);
			setState(427);
			match(WHILE_KEYWORD);
			setState(428);
			match(PAREN_OPEN);
			setState(429);
			expressions();
			setState(430);
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
	public static class For_loopContext extends ParserRuleContext {
		public TerminalNode FOR_KEYWORD() { return getToken(MainAntlrParser.FOR_KEYWORD, 0); }
		public TerminalNode PAREN_OPEN() { return getToken(MainAntlrParser.PAREN_OPEN, 0); }
		public For_initContext for_init() {
			return getRuleContext(For_initContext.class,0);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(MainAntlrParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(MainAntlrParser.SEMICOLON, i);
		}
		public For_terminationContext for_termination() {
			return getRuleContext(For_terminationContext.class,0);
		}
		public For_updateContext for_update() {
			return getRuleContext(For_updateContext.class,0);
		}
		public TerminalNode PAREN_CLOSE() { return getToken(MainAntlrParser.PAREN_CLOSE, 0); }
		public TerminalNode CURLY_OPEN() { return getToken(MainAntlrParser.CURLY_OPEN, 0); }
		public Function_scopeContext function_scope() {
			return getRuleContext(Function_scopeContext.class,0);
		}
		public TerminalNode CURLY_CLOSE() { return getToken(MainAntlrParser.CURLY_CLOSE, 0); }
		public For_loopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_loop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterFor_loop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitFor_loop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitFor_loop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final For_loopContext for_loop() throws RecognitionException {
		For_loopContext _localctx = new For_loopContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_for_loop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(432);
			match(FOR_KEYWORD);
			setState(433);
			match(PAREN_OPEN);
			setState(434);
			for_init();
			setState(435);
			match(SEMICOLON);
			setState(436);
			for_termination();
			setState(437);
			match(SEMICOLON);
			setState(438);
			for_update();
			setState(439);
			match(PAREN_CLOSE);
			setState(440);
			match(CURLY_OPEN);
			setState(441);
			function_scope();
			setState(442);
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
	public static class For_initContext extends ParserRuleContext {
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public For_initContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_init; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterFor_init(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitFor_init(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitFor_init(this);
			else return visitor.visitChildren(this);
		}
	}

	public final For_initContext for_init() throws RecognitionException {
		For_initContext _localctx = new For_initContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_for_init);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(444);
			assignment();
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
	public static class For_terminationContext extends ParserRuleContext {
		public ExpressionsContext expressions() {
			return getRuleContext(ExpressionsContext.class,0);
		}
		public For_terminationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_termination; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterFor_termination(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitFor_termination(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitFor_termination(this);
			else return visitor.visitChildren(this);
		}
	}

	public final For_terminationContext for_termination() throws RecognitionException {
		For_terminationContext _localctx = new For_terminationContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_for_termination);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(446);
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
	public static class For_updateContext extends ParserRuleContext {
		public ExpressionsContext expressions() {
			return getRuleContext(ExpressionsContext.class,0);
		}
		public For_updateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_update; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterFor_update(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitFor_update(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitFor_update(this);
			else return visitor.visitChildren(this);
		}
	}

	public final For_updateContext for_update() throws RecognitionException {
		For_updateContext _localctx = new For_updateContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_for_update);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(448);
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
	public static class CastingContext extends ParserRuleContext {
		public TerminalNode PAREN_OPEN() { return getToken(MainAntlrParser.PAREN_OPEN, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode PAREN_CLOSE() { return getToken(MainAntlrParser.PAREN_CLOSE, 0); }
		public ExpressionsContext expressions() {
			return getRuleContext(ExpressionsContext.class,0);
		}
		public CastingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_casting; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).enterCasting(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MainAntlrListener ) ((MainAntlrListener)listener).exitCasting(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MainAntlrVisitor ) return ((MainAntlrVisitor<? extends T>)visitor).visitCasting(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CastingContext casting() throws RecognitionException {
		CastingContext _localctx = new CastingContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_casting);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(450);
			match(PAREN_OPEN);
			setState(451);
			type();
			setState(452);
			match(PAREN_CLOSE);
			setState(453);
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
		enterRule(_localctx, 104, RULE_access_modifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(455);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1835008L) != 0)) ) {
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
		enterRule(_localctx, 106, RULE_function_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(457);
			access_modifier();
			setState(459);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INSTANCE_MODIFIER) {
				{
				setState(458);
				match(INSTANCE_MODIFIER);
				}
			}

			setState(461);
			type();
			setState(462);
			match(IDENTIFIER);
			setState(463);
			match(PAREN_OPEN);
			setState(465);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8590081984L) != 0)) {
				{
				setState(464);
				function_declaration_args();
				}
			}

			setState(467);
			match(PAREN_CLOSE);
			setState(468);
			match(CURLY_OPEN);
			setState(469);
			function_scope();
			setState(470);
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
		enterRule(_localctx, 108, RULE_function_declaration_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(472);
			function_declaration_arg();
			setState(477);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(473);
				match(COMMA);
				setState(474);
				function_declaration_arg();
				}
				}
				setState(479);
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
		enterRule(_localctx, 110, RULE_function_declaration_arg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(480);
			type();
			setState(481);
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
		enterRule(_localctx, 112, RULE_function_call);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(483);
			identifier();
			setState(484);
			match(PAREN_OPEN);
			setState(486);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 3472279994221199384L) != 0) || ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & 63L) != 0)) {
				{
				setState(485);
				function_args();
				}
			}

			setState(488);
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
		enterRule(_localctx, 114, RULE_function_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(490);
			function_arg();
			setState(495);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(491);
				match(COMMA);
				setState(492);
				function_arg();
				}
				}
				setState(497);
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
		enterRule(_localctx, 116, RULE_function_arg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(498);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 8:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001N\u01f5\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
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
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0002"+
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u00071\u0002"+
		"2\u00072\u00023\u00073\u00024\u00074\u00025\u00075\u00026\u00076\u0002"+
		"7\u00077\u00028\u00078\u00029\u00079\u0002:\u0007:\u0001\u0000\u0004\u0000"+
		"x\b\u0000\u000b\u0000\f\u0000y\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0003\u0001\u0080\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0003\u0002\u0087\b\u0002\u0001\u0002\u0001\u0002\u0005"+
		"\u0002\u008b\b\u0002\n\u0002\f\u0002\u008e\t\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0003\u0003\u0098\b\u0003\u0001\u0003\u0001\u0003\u0005\u0003\u009c\b"+
		"\u0003\n\u0003\f\u0003\u009f\t\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004\u00a7\b\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005\u00ad\b\u0005\u0001\u0006"+
		"\u0001\u0006\u0003\u0006\u00b1\b\u0006\u0001\u0006\u0001\u0006\u0001\u0007"+
		"\u0001\u0007\u0003\u0007\u00b7\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u00d2\b\b\u0001\b\u0001\b\u0003"+
		"\b\u00d6\b\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0005\b\u00de"+
		"\b\b\n\b\f\b\u00e1\t\b\u0001\t\u0001\t\u0001\t\u0003\t\u00e6\b\t\u0001"+
		"\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0004\f\u00f1\b\f\u000b\f\f\f\u00f2\u0001\f\u0003\f\u00f6\b\f\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0004\r\u00fc\b\r\u000b\r\f\r\u00fd\u0001\u000e"+
		"\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0011"+
		"\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0005\u0015\u011a\b\u0015\n\u0015\f\u0015\u011d\t\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0005\u001a\u013f\b\u001a\n\u001a\f\u001a\u0142\t\u001a"+
		"\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0005\u001e\u0152\b\u001e\n\u001e\f\u001e\u0155"+
		"\t\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0003\u001f\u015b"+
		"\b\u001f\u0001 \u0001 \u0001!\u0001!\u0001\"\u0001\"\u0003\"\u0163\b\""+
		"\u0001#\u0001#\u0003#\u0167\b#\u0001$\u0001$\u0001$\u0003$\u016c\b$\u0001"+
		"%\u0001%\u0003%\u0170\b%\u0001&\u0001&\u0001&\u0001&\u0001\'\u0001\'\u0001"+
		"\'\u0005\'\u0179\b\'\n\'\f\'\u017c\t\'\u0001(\u0001(\u0003(\u0180\b(\u0001"+
		")\u0001)\u0001)\u0001)\u0003)\u0186\b)\u0001*\u0001*\u0001+\u0001+\u0001"+
		"+\u0003+\u018d\b+\u0001+\u0001+\u0004+\u0191\b+\u000b+\f+\u0192\u0001"+
		",\u0001,\u0001,\u0001,\u0005,\u0199\b,\n,\f,\u019c\t,\u0001,\u0001,\u0001"+
		"-\u0001-\u0001-\u0001-\u0001-\u0001-\u0001-\u0001-\u0001.\u0001.\u0001"+
		".\u0001.\u0001.\u0001.\u0001.\u0001.\u0001.\u0001/\u0001/\u0001/\u0001"+
		"/\u0001/\u0001/\u0001/\u0001/\u0001/\u0001/\u0001/\u0001/\u00010\u0001"+
		"0\u00011\u00011\u00012\u00012\u00013\u00013\u00013\u00013\u00013\u0001"+
		"4\u00014\u00015\u00015\u00035\u01cc\b5\u00015\u00015\u00015\u00015\u0003"+
		"5\u01d2\b5\u00015\u00015\u00015\u00015\u00015\u00016\u00016\u00016\u0005"+
		"6\u01dc\b6\n6\f6\u01df\t6\u00017\u00017\u00017\u00018\u00018\u00018\u0003"+
		"8\u01e7\b8\u00018\u00018\u00019\u00019\u00019\u00059\u01ee\b9\n9\f9\u01f1"+
		"\t9\u0001:\u0001:\u0001:\u0000\u0001\u0010;\u0000\u0002\u0004\u0006\b"+
		"\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02"+
		"468:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprt\u0000\n\u0002\u0000%%37\u0001\u0000"+
		"89\u0002\u0000,-/3\u0001\u000045\u0001\u0000<=\u0001\u0000:;\u0001\u0000"+
		">B\u0001\u0000\u0007\u000b\u0001\u0000\f\r\u0001\u0000\u0012\u0014\u01ff"+
		"\u0000w\u0001\u0000\u0000\u0000\u0002\u007f\u0001\u0000\u0000\u0000\u0004"+
		"\u008c\u0001\u0000\u0000\u0000\u0006\u009d\u0001\u0000\u0000\u0000\b\u00a6"+
		"\u0001\u0000\u0000\u0000\n\u00a8\u0001\u0000\u0000\u0000\f\u00b0\u0001"+
		"\u0000\u0000\u0000\u000e\u00b6\u0001\u0000\u0000\u0000\u0010\u00d5\u0001"+
		"\u0000\u0000\u0000\u0012\u00e5\u0001\u0000\u0000\u0000\u0014\u00e7\u0001"+
		"\u0000\u0000\u0000\u0016\u00e9\u0001\u0000\u0000\u0000\u0018\u00eb\u0001"+
		"\u0000\u0000\u0000\u001a\u00f7\u0001\u0000\u0000\u0000\u001c\u00ff\u0001"+
		"\u0000\u0000\u0000\u001e\u0101\u0001\u0000\u0000\u0000 \u0103\u0001\u0000"+
		"\u0000\u0000\"\u0105\u0001\u0000\u0000\u0000$\u0107\u0001\u0000\u0000"+
		"\u0000&\u0109\u0001\u0000\u0000\u0000(\u010c\u0001\u0000\u0000\u0000*"+
		"\u0113\u0001\u0000\u0000\u0000,\u0120\u0001\u0000\u0000\u0000.\u0128\u0001"+
		"\u0000\u0000\u00000\u012d\u0001\u0000\u0000\u00002\u0130\u0001\u0000\u0000"+
		"\u00004\u0140\u0001\u0000\u0000\u00006\u0147\u0001\u0000\u0000\u00008"+
		"\u014a\u0001\u0000\u0000\u0000:\u014c\u0001\u0000\u0000\u0000<\u014e\u0001"+
		"\u0000\u0000\u0000>\u015a\u0001\u0000\u0000\u0000@\u015c\u0001\u0000\u0000"+
		"\u0000B\u015e\u0001\u0000\u0000\u0000D\u0162\u0001\u0000\u0000\u0000F"+
		"\u0166\u0001\u0000\u0000\u0000H\u016b\u0001\u0000\u0000\u0000J\u016d\u0001"+
		"\u0000\u0000\u0000L\u0171\u0001\u0000\u0000\u0000N\u0175\u0001\u0000\u0000"+
		"\u0000P\u017f\u0001\u0000\u0000\u0000R\u0185\u0001\u0000\u0000\u0000T"+
		"\u0187\u0001\u0000\u0000\u0000V\u018c\u0001\u0000\u0000\u0000X\u0194\u0001"+
		"\u0000\u0000\u0000Z\u019f\u0001\u0000\u0000\u0000\\\u01a7\u0001\u0000"+
		"\u0000\u0000^\u01b0\u0001\u0000\u0000\u0000`\u01bc\u0001\u0000\u0000\u0000"+
		"b\u01be\u0001\u0000\u0000\u0000d\u01c0\u0001\u0000\u0000\u0000f\u01c2"+
		"\u0001\u0000\u0000\u0000h\u01c7\u0001\u0000\u0000\u0000j\u01c9\u0001\u0000"+
		"\u0000\u0000l\u01d8\u0001\u0000\u0000\u0000n\u01e0\u0001\u0000\u0000\u0000"+
		"p\u01e3\u0001\u0000\u0000\u0000r\u01ea\u0001\u0000\u0000\u0000t\u01f2"+
		"\u0001\u0000\u0000\u0000vx\u0003\u0002\u0001\u0000wv\u0001\u0000\u0000"+
		"\u0000xy\u0001\u0000\u0000\u0000yw\u0001\u0000\u0000\u0000yz\u0001\u0000"+
		"\u0000\u0000z\u0001\u0001\u0000\u0000\u0000{\u0080\u0003(\u0014\u0000"+
		"|}\u0003&\u0013\u0000}~\u0005\"\u0000\u0000~\u0080\u0001\u0000\u0000\u0000"+
		"\u007f{\u0001\u0000\u0000\u0000\u007f|\u0001\u0000\u0000\u0000\u0080\u0003"+
		"\u0001\u0000\u0000\u0000\u0081\u008b\u0003*\u0015\u0000\u0082\u008b\u0003"+
		"(\u0014\u0000\u0083\u008b\u0003j5\u0000\u0084\u0087\u0003\f\u0006\u0000"+
		"\u0085\u0087\u0003\u000e\u0007\u0000\u0086\u0084\u0001\u0000\u0000\u0000"+
		"\u0086\u0085\u0001\u0000\u0000\u0000\u0087\u0088\u0001\u0000\u0000\u0000"+
		"\u0088\u0089\u0005\"\u0000\u0000\u0089\u008b\u0001\u0000\u0000\u0000\u008a"+
		"\u0081\u0001\u0000\u0000\u0000\u008a\u0082\u0001\u0000\u0000\u0000\u008a"+
		"\u0083\u0001\u0000\u0000\u0000\u008a\u0086\u0001\u0000\u0000\u0000\u008b"+
		"\u008e\u0001\u0000\u0000\u0000\u008c\u008a\u0001\u0000\u0000\u0000\u008c"+
		"\u008d\u0001\u0000\u0000\u0000\u008d\u0005\u0001\u0000\u0000\u0000\u008e"+
		"\u008c\u0001\u0000\u0000\u0000\u008f\u009c\u0003*\u0015\u0000\u0090\u009c"+
		"\u0003\b\u0004\u0000\u0091\u0098\u0003\n\u0005\u0000\u0092\u0098\u0003"+
		"\u000e\u0007\u0000\u0093\u0098\u0003\f\u0006\u0000\u0094\u0098\u00036"+
		"\u001b\u0000\u0095\u0098\u0003:\u001d\u0000\u0096\u0098\u00038\u001c\u0000"+
		"\u0097\u0091\u0001\u0000\u0000\u0000\u0097\u0092\u0001\u0000\u0000\u0000"+
		"\u0097\u0093\u0001\u0000\u0000\u0000\u0097\u0094\u0001\u0000\u0000\u0000"+
		"\u0097\u0095\u0001\u0000\u0000\u0000\u0097\u0096\u0001\u0000\u0000\u0000"+
		"\u0098\u0099\u0001\u0000\u0000\u0000\u0099\u009a\u0005\"\u0000\u0000\u009a"+
		"\u009c\u0001\u0000\u0000\u0000\u009b\u008f\u0001\u0000\u0000\u0000\u009b"+
		"\u0090\u0001\u0000\u0000\u0000\u009b\u0097\u0001\u0000\u0000\u0000\u009c"+
		"\u009f\u0001\u0000\u0000\u0000\u009d\u009b\u0001\u0000\u0000\u0000\u009d"+
		"\u009e\u0001\u0000\u0000\u0000\u009e\u0007\u0001\u0000\u0000\u0000\u009f"+
		"\u009d\u0001\u0000\u0000\u0000\u00a0\u00a7\u0003,\u0016\u0000\u00a1\u00a7"+
		"\u00030\u0018\u0000\u00a2\u00a7\u0003Z-\u0000\u00a3\u00a7\u0003\\.\u0000"+
		"\u00a4\u00a7\u0003^/\u0000\u00a5\u00a7\u00032\u0019\u0000\u00a6\u00a0"+
		"\u0001\u0000\u0000\u0000\u00a6\u00a1\u0001\u0000\u0000\u0000\u00a6\u00a2"+
		"\u0001\u0000\u0000\u0000\u00a6\u00a3\u0001\u0000\u0000\u0000\u00a6\u00a4"+
		"\u0001\u0000\u0000\u0000\u00a6\u00a5\u0001\u0000\u0000\u0000\u00a7\t\u0001"+
		"\u0000\u0000\u0000\u00a8\u00ac\u0003\u0010\b\u0000\u00a9\u00aa\u0003\u0012"+
		"\t\u0000\u00aa\u00ab\u0003\u0010\b\u0000\u00ab\u00ad\u0001\u0000\u0000"+
		"\u0000\u00ac\u00a9\u0001\u0000\u0000\u0000\u00ac\u00ad\u0001\u0000\u0000"+
		"\u0000\u00ad\u000b\u0001\u0000\u0000\u0000\u00ae\u00b1\u0003F#\u0000\u00af"+
		"\u00b1\u0003H$\u0000\u00b0\u00ae\u0001\u0000\u0000\u0000\u00b0\u00af\u0001"+
		"\u0000\u0000\u0000\u00b1\u00b2\u0001\u0000\u0000\u0000\u00b2\u00b3\u0005"+
		"!\u0000\u0000\u00b3\r\u0001\u0000\u0000\u0000\u00b4\u00b7\u0003\f\u0006"+
		"\u0000\u00b5\u00b7\u0003<\u001e\u0000\u00b6\u00b4\u0001\u0000\u0000\u0000"+
		"\u00b6\u00b5\u0001\u0000\u0000\u0000\u00b7\u00b8\u0001\u0000\u0000\u0000"+
		"\u00b8\u00b9\u0005.\u0000\u0000\u00b9\u00ba\u0003\n\u0005\u0000\u00ba"+
		"\u000f\u0001\u0000\u0000\u0000\u00bb\u00bc\u0006\b\uffff\uffff\u0000\u00bc"+
		"\u00d6\u0003p8\u0000\u00bd\u00d6\u0005!\u0000\u0000\u00be\u00d6\u0005"+
		"F\u0000\u0000\u00bf\u00d6\u0005G\u0000\u0000\u00c0\u00d6\u0005H\u0000"+
		"\u0000\u00c1\u00d6\u0005I\u0000\u0000\u00c2\u00d6\u0005J\u0000\u0000\u00c3"+
		"\u00d6\u0005K\u0000\u0000\u00c4\u00d6\u0005\u0003\u0000\u0000\u00c5\u00d6"+
		"\u0005\u0004\u0000\u0000\u00c6\u00d6\u0003<\u001e\u0000\u00c7\u00d6\u0003"+
		"f3\u0000\u00c8\u00c9\u0005&\u0000\u0000\u00c9\u00ca\u0003\u0010\b\u0000"+
		"\u00ca\u00cb\u0005\'\u0000\u0000\u00cb\u00d6\u0001\u0000\u0000\u0000\u00cc"+
		"\u00d6\u0003X,\u0000\u00cd\u00d6\u0003\u0018\f\u0000\u00ce\u00d6\u0003"+
		"\u001a\r\u0000\u00cf\u00d2\u0003\u001e\u000f\u0000\u00d0\u00d2\u0003 "+
		"\u0010\u0000\u00d1\u00cf\u0001\u0000\u0000\u0000\u00d1\u00d0\u0001\u0000"+
		"\u0000\u0000\u00d2\u00d3\u0001\u0000\u0000\u0000\u00d3\u00d4\u0003\n\u0005"+
		"\u0000\u00d4\u00d6\u0001\u0000\u0000\u0000\u00d5\u00bb\u0001\u0000\u0000"+
		"\u0000\u00d5\u00bd\u0001\u0000\u0000\u0000\u00d5\u00be\u0001\u0000\u0000"+
		"\u0000\u00d5\u00bf\u0001\u0000\u0000\u0000\u00d5\u00c0\u0001\u0000\u0000"+
		"\u0000\u00d5\u00c1\u0001\u0000\u0000\u0000\u00d5\u00c2\u0001\u0000\u0000"+
		"\u0000\u00d5\u00c3\u0001\u0000\u0000\u0000\u00d5\u00c4\u0001\u0000\u0000"+
		"\u0000\u00d5\u00c5\u0001\u0000\u0000\u0000\u00d5\u00c6\u0001\u0000\u0000"+
		"\u0000\u00d5\u00c7\u0001\u0000\u0000\u0000\u00d5\u00c8\u0001\u0000\u0000"+
		"\u0000\u00d5\u00cc\u0001\u0000\u0000\u0000\u00d5\u00cd\u0001\u0000\u0000"+
		"\u0000\u00d5\u00ce\u0001\u0000\u0000\u0000\u00d5\u00d1\u0001\u0000\u0000"+
		"\u0000\u00d6\u00df\u0001\u0000\u0000\u0000\u00d7\u00d8\n\u0006\u0000\u0000"+
		"\u00d8\u00d9\u0003\u0014\n\u0000\u00d9\u00da\u0003\u0010\b\u0007\u00da"+
		"\u00de\u0001\u0000\u0000\u0000\u00db\u00dc\n\u0001\u0000\u0000\u00dc\u00de"+
		"\u0003\u0016\u000b\u0000\u00dd\u00d7\u0001\u0000\u0000\u0000\u00dd\u00db"+
		"\u0001\u0000\u0000\u0000\u00de\u00e1\u0001\u0000\u0000\u0000\u00df\u00dd"+
		"\u0001\u0000\u0000\u0000\u00df\u00e0\u0001\u0000\u0000\u0000\u00e0\u0011"+
		"\u0001\u0000\u0000\u0000\u00e1\u00df\u0001\u0000\u0000\u0000\u00e2\u00e6"+
		"\u0003\"\u0011\u0000\u00e3\u00e6\u0003\u001c\u000e\u0000\u00e4\u00e6\u0003"+
		"$\u0012\u0000\u00e5\u00e2\u0001\u0000\u0000\u0000\u00e5\u00e3\u0001\u0000"+
		"\u0000\u0000\u00e5\u00e4\u0001\u0000\u0000\u0000\u00e6\u0013\u0001\u0000"+
		"\u0000\u0000\u00e7\u00e8\u0007\u0000\u0000\u0000\u00e8\u0015\u0001\u0000"+
		"\u0000\u0000\u00e9\u00ea\u0007\u0001\u0000\u0000\u00ea\u0017\u0001\u0000"+
		"\u0000\u0000\u00eb\u00f5\u0005\u0016\u0000\u0000\u00ec\u00f0\u0003>\u001f"+
		"\u0000\u00ed\u00ee\u0005(\u0000\u0000\u00ee\u00ef\u0005J\u0000\u0000\u00ef"+
		"\u00f1\u0005)\u0000\u0000\u00f0\u00ed\u0001\u0000\u0000\u0000\u00f1\u00f2"+
		"\u0001\u0000\u0000\u0000\u00f2\u00f0\u0001\u0000\u0000\u0000\u00f2\u00f3"+
		"\u0001\u0000\u0000\u0000\u00f3\u00f6\u0001\u0000\u0000\u0000\u00f4\u00f6"+
		"\u0003>\u001f\u0000\u00f5\u00ec\u0001\u0000\u0000\u0000\u00f5\u00f4\u0001"+
		"\u0000\u0000\u0000\u00f6\u0019\u0001\u0000\u0000\u0000\u00f7\u00fb\u0005"+
		"!\u0000\u0000\u00f8\u00f9\u0005(\u0000\u0000\u00f9\u00fa\u0005J\u0000"+
		"\u0000\u00fa\u00fc\u0005)\u0000\u0000\u00fb\u00f8\u0001\u0000\u0000\u0000"+
		"\u00fc\u00fd\u0001\u0000\u0000\u0000\u00fd\u00fb\u0001\u0000\u0000\u0000"+
		"\u00fd\u00fe\u0001\u0000\u0000\u0000\u00fe\u001b\u0001\u0000\u0000\u0000"+
		"\u00ff\u0100\u0007\u0002\u0000\u0000\u0100\u001d\u0001\u0000\u0000\u0000"+
		"\u0101\u0102\u0007\u0003\u0000\u0000\u0102\u001f\u0001\u0000\u0000\u0000"+
		"\u0103\u0104\u0007\u0004\u0000\u0000\u0104!\u0001\u0000\u0000\u0000\u0105"+
		"\u0106\u0007\u0005\u0000\u0000\u0106#\u0001\u0000\u0000\u0000\u0107\u0108"+
		"\u0007\u0006\u0000\u0000\u0108%\u0001\u0000\u0000\u0000\u0109\u010a\u0005"+
		"\u0001\u0000\u0000\u010a\u010b\u0003<\u001e\u0000\u010b\'\u0001\u0000"+
		"\u0000\u0000\u010c\u010d\u0003h4\u0000\u010d\u010e\u0005\u0002\u0000\u0000"+
		"\u010e\u010f\u0005!\u0000\u0000\u010f\u0110\u0005*\u0000\u0000\u0110\u0111"+
		"\u0003\u0004\u0002\u0000\u0111\u0112\u0005+\u0000\u0000\u0112)\u0001\u0000"+
		"\u0000\u0000\u0113\u0114\u0005\u000e\u0000\u0000\u0114\u0115\u0005!\u0000"+
		"\u0000\u0115\u0116\u0005*\u0000\u0000\u0116\u011b\u0005!\u0000\u0000\u0117"+
		"\u0118\u0005$\u0000\u0000\u0118\u011a\u0005!\u0000\u0000\u0119\u0117\u0001"+
		"\u0000\u0000\u0000\u011a\u011d\u0001\u0000\u0000\u0000\u011b\u0119\u0001"+
		"\u0000\u0000\u0000\u011b\u011c\u0001\u0000\u0000\u0000\u011c\u011e\u0001"+
		"\u0000\u0000\u0000\u011d\u011b\u0001\u0000\u0000\u0000\u011e\u011f\u0005"+
		"+\u0000\u0000\u011f+\u0001\u0000\u0000\u0000\u0120\u0121\u0005\u0017\u0000"+
		"\u0000\u0121\u0122\u0005&\u0000\u0000\u0122\u0123\u0003\n\u0005\u0000"+
		"\u0123\u0124\u0005\'\u0000\u0000\u0124\u0125\u0005*\u0000\u0000\u0125"+
		"\u0126\u0003\u0006\u0003\u0000\u0126\u0127\u0005+\u0000\u0000\u0127-\u0001"+
		"\u0000\u0000\u0000\u0128\u0129\u0005\u0018\u0000\u0000\u0129\u012a\u0005"+
		"*\u0000\u0000\u012a\u012b\u0003\u0006\u0003\u0000\u012b\u012c\u0005+\u0000"+
		"\u0000\u012c/\u0001\u0000\u0000\u0000\u012d\u012e\u0003,\u0016\u0000\u012e"+
		"\u012f\u0003.\u0017\u0000\u012f1\u0001\u0000\u0000\u0000\u0130\u0131\u0005"+
		"\u001c\u0000\u0000\u0131\u0132\u0005&\u0000\u0000\u0132\u0133\u0003\n"+
		"\u0005\u0000\u0133\u0134\u0005\'\u0000\u0000\u0134\u0135\u0005*\u0000"+
		"\u0000\u0135\u0136\u00034\u001a\u0000\u0136\u0137\u0005+\u0000\u0000\u0137"+
		"3\u0001\u0000\u0000\u0000\u0138\u0139\u0005\u001d\u0000\u0000\u0139\u013a"+
		"\u0003\n\u0005\u0000\u013a\u013b\u0001\u0000\u0000\u0000\u013b\u013c\u0005"+
		"#\u0000\u0000\u013c\u013d\u0003\u0006\u0003\u0000\u013d\u013f\u0001\u0000"+
		"\u0000\u0000\u013e\u0138\u0001\u0000\u0000\u0000\u013f\u0142\u0001\u0000"+
		"\u0000\u0000\u0140\u013e\u0001\u0000\u0000\u0000\u0140\u0141\u0001\u0000"+
		"\u0000\u0000\u0141\u0143\u0001\u0000\u0000\u0000\u0142\u0140\u0001\u0000"+
		"\u0000\u0000\u0143\u0144\u0005 \u0000\u0000\u0144\u0145\u0005#\u0000\u0000"+
		"\u0145\u0146\u0003\u0006\u0003\u0000\u01465\u0001\u0000\u0000\u0000\u0147"+
		"\u0148\u0005\u0015\u0000\u0000\u0148\u0149\u0003\n\u0005\u0000\u01497"+
		"\u0001\u0000\u0000\u0000\u014a\u014b\u0005\u001e\u0000\u0000\u014b9\u0001"+
		"\u0000\u0000\u0000\u014c\u014d\u0005\u001f\u0000\u0000\u014d;\u0001\u0000"+
		"\u0000\u0000\u014e\u0153\u0005!\u0000\u0000\u014f\u0150\u0005%\u0000\u0000"+
		"\u0150\u0152\u0005!\u0000\u0000\u0151\u014f\u0001\u0000\u0000\u0000\u0152"+
		"\u0155\u0001\u0000\u0000\u0000\u0153\u0151\u0001\u0000\u0000\u0000\u0153"+
		"\u0154\u0001\u0000\u0000\u0000\u0154=\u0001\u0000\u0000\u0000\u0155\u0153"+
		"\u0001\u0000\u0000\u0000\u0156\u015b\u0005\u0011\u0000\u0000\u0157\u015b"+
		"\u0003F#\u0000\u0158\u015b\u0003V+\u0000\u0159\u015b\u0003H$\u0000\u015a"+
		"\u0156\u0001\u0000\u0000\u0000\u015a\u0157\u0001\u0000\u0000\u0000\u015a"+
		"\u0158\u0001\u0000\u0000\u0000\u015a\u0159\u0001\u0000\u0000\u0000\u015b"+
		"?\u0001\u0000\u0000\u0000\u015c\u015d\u0007\u0007\u0000\u0000\u015dA\u0001"+
		"\u0000\u0000\u0000\u015e\u015f\u0007\b\u0000\u0000\u015fC\u0001\u0000"+
		"\u0000\u0000\u0160\u0163\u0003@ \u0000\u0161\u0163\u0003B!\u0000\u0162"+
		"\u0160\u0001\u0000\u0000\u0000\u0162\u0161\u0001\u0000\u0000\u0000\u0163"+
		"E\u0001\u0000\u0000\u0000\u0164\u0167\u0003D\"\u0000\u0165\u0167\u0005"+
		"\u0006\u0000\u0000\u0166\u0164\u0001\u0000\u0000\u0000\u0166\u0165\u0001"+
		"\u0000\u0000\u0000\u0167G\u0001\u0000\u0000\u0000\u0168\u016c\u0003J%"+
		"\u0000\u0169\u016c\u0003T*\u0000\u016a\u016c\u0003V+\u0000\u016b\u0168"+
		"\u0001\u0000\u0000\u0000\u016b\u0169\u0001\u0000\u0000\u0000\u016b\u016a"+
		"\u0001\u0000\u0000\u0000\u016cI\u0001\u0000\u0000\u0000\u016d\u016f\u0005"+
		"!\u0000\u0000\u016e\u0170\u0003L&\u0000\u016f\u016e\u0001\u0000\u0000"+
		"\u0000\u016f\u0170\u0001\u0000\u0000\u0000\u0170K\u0001\u0000\u0000\u0000"+
		"\u0171\u0172\u0005,\u0000\u0000\u0172\u0173\u0003N\'\u0000\u0173\u0174"+
		"\u0005-\u0000\u0000\u0174M\u0001\u0000\u0000\u0000\u0175\u017a\u0003P"+
		"(\u0000\u0176\u0177\u0005$\u0000\u0000\u0177\u0179\u0003P(\u0000\u0178"+
		"\u0176\u0001\u0000\u0000\u0000\u0179\u017c\u0001\u0000\u0000\u0000\u017a"+
		"\u0178\u0001\u0000\u0000\u0000\u017a\u017b\u0001\u0000\u0000\u0000\u017b"+
		"O\u0001\u0000\u0000\u0000\u017c\u017a\u0001\u0000\u0000\u0000\u017d\u0180"+
		"\u0003H$\u0000\u017e\u0180\u0003R)\u0000\u017f\u017d\u0001\u0000\u0000"+
		"\u0000\u017f\u017e\u0001\u0000\u0000\u0000\u0180Q\u0001\u0000\u0000\u0000"+
		"\u0181\u0182\u0005\u000f\u0000\u0000\u0182\u0186\u0003H$\u0000\u0183\u0184"+
		"\u0005\u0010\u0000\u0000\u0184\u0186\u0003H$\u0000\u0185\u0181\u0001\u0000"+
		"\u0000\u0000\u0185\u0183\u0001\u0000\u0000\u0000\u0186S\u0001\u0000\u0000"+
		"\u0000\u0187\u0188\u0005!\u0000\u0000\u0188U\u0001\u0000\u0000\u0000\u0189"+
		"\u018d\u0003F#\u0000\u018a\u018d\u0003J%\u0000\u018b\u018d\u0003T*\u0000"+
		"\u018c\u0189\u0001\u0000\u0000\u0000\u018c\u018a\u0001\u0000\u0000\u0000"+
		"\u018c\u018b\u0001\u0000\u0000\u0000\u018d\u0190\u0001\u0000\u0000\u0000"+
		"\u018e\u018f\u0005(\u0000\u0000\u018f\u0191\u0005)\u0000\u0000\u0190\u018e"+
		"\u0001\u0000\u0000\u0000\u0191\u0192\u0001\u0000\u0000\u0000\u0192\u0190"+
		"\u0001\u0000\u0000\u0000\u0192\u0193\u0001\u0000\u0000\u0000\u0193W\u0001"+
		"\u0000\u0000\u0000\u0194\u0195\u0005*\u0000\u0000\u0195\u019a\u0003\n"+
		"\u0005\u0000\u0196\u0197\u0005$\u0000\u0000\u0197\u0199\u0003\n\u0005"+
		"\u0000\u0198\u0196\u0001\u0000\u0000\u0000\u0199\u019c\u0001\u0000\u0000"+
		"\u0000\u019a\u0198\u0001\u0000\u0000\u0000\u019a\u019b\u0001\u0000\u0000"+
		"\u0000\u019b\u019d\u0001\u0000\u0000\u0000\u019c\u019a\u0001\u0000\u0000"+
		"\u0000\u019d\u019e\u0005+\u0000\u0000\u019eY\u0001\u0000\u0000\u0000\u019f"+
		"\u01a0\u0005\u001a\u0000\u0000\u01a0\u01a1\u0005&\u0000\u0000\u01a1\u01a2"+
		"\u0003\n\u0005\u0000\u01a2\u01a3\u0005\'\u0000\u0000\u01a3\u01a4\u0005"+
		"*\u0000\u0000\u01a4\u01a5\u0003\u0006\u0003\u0000\u01a5\u01a6\u0005+\u0000"+
		"\u0000\u01a6[\u0001\u0000\u0000\u0000\u01a7\u01a8\u0005\u001b\u0000\u0000"+
		"\u01a8\u01a9\u0005*\u0000\u0000\u01a9\u01aa\u0003\u0006\u0003\u0000\u01aa"+
		"\u01ab\u0005+\u0000\u0000\u01ab\u01ac\u0005\u001a\u0000\u0000\u01ac\u01ad"+
		"\u0005&\u0000\u0000\u01ad\u01ae\u0003\n\u0005\u0000\u01ae\u01af\u0005"+
		"\'\u0000\u0000\u01af]\u0001\u0000\u0000\u0000\u01b0\u01b1\u0005\u0019"+
		"\u0000\u0000\u01b1\u01b2\u0005&\u0000\u0000\u01b2\u01b3\u0003`0\u0000"+
		"\u01b3\u01b4\u0005\"\u0000\u0000\u01b4\u01b5\u0003b1\u0000\u01b5\u01b6"+
		"\u0005\"\u0000\u0000\u01b6\u01b7\u0003d2\u0000\u01b7\u01b8\u0005\'\u0000"+
		"\u0000\u01b8\u01b9\u0005*\u0000\u0000\u01b9\u01ba\u0003\u0006\u0003\u0000"+
		"\u01ba\u01bb\u0005+\u0000\u0000\u01bb_\u0001\u0000\u0000\u0000\u01bc\u01bd"+
		"\u0003\u000e\u0007\u0000\u01bda\u0001\u0000\u0000\u0000\u01be\u01bf\u0003"+
		"\n\u0005\u0000\u01bfc\u0001\u0000\u0000\u0000\u01c0\u01c1\u0003\n\u0005"+
		"\u0000\u01c1e\u0001\u0000\u0000\u0000\u01c2\u01c3\u0005&\u0000\u0000\u01c3"+
		"\u01c4\u0003>\u001f\u0000\u01c4\u01c5\u0005\'\u0000\u0000\u01c5\u01c6"+
		"\u0003\n\u0005\u0000\u01c6g\u0001\u0000\u0000\u0000\u01c7\u01c8\u0007"+
		"\t\u0000\u0000\u01c8i\u0001\u0000\u0000\u0000\u01c9\u01cb\u0003h4\u0000"+
		"\u01ca\u01cc\u0005\u0005\u0000\u0000\u01cb\u01ca\u0001\u0000\u0000\u0000"+
		"\u01cb\u01cc\u0001\u0000\u0000\u0000\u01cc\u01cd\u0001\u0000\u0000\u0000"+
		"\u01cd\u01ce\u0003>\u001f\u0000\u01ce\u01cf\u0005!\u0000\u0000\u01cf\u01d1"+
		"\u0005&\u0000\u0000\u01d0\u01d2\u0003l6\u0000\u01d1\u01d0\u0001\u0000"+
		"\u0000\u0000\u01d1\u01d2\u0001\u0000\u0000\u0000\u01d2\u01d3\u0001\u0000"+
		"\u0000\u0000\u01d3\u01d4\u0005\'\u0000\u0000\u01d4\u01d5\u0005*\u0000"+
		"\u0000\u01d5\u01d6\u0003\u0006\u0003\u0000\u01d6\u01d7\u0005+\u0000\u0000"+
		"\u01d7k\u0001\u0000\u0000\u0000\u01d8\u01dd\u0003n7\u0000\u01d9\u01da"+
		"\u0005$\u0000\u0000\u01da\u01dc\u0003n7\u0000\u01db\u01d9\u0001\u0000"+
		"\u0000\u0000\u01dc\u01df\u0001\u0000\u0000\u0000\u01dd\u01db\u0001\u0000"+
		"\u0000\u0000\u01dd\u01de\u0001\u0000\u0000\u0000\u01dem\u0001\u0000\u0000"+
		"\u0000\u01df\u01dd\u0001\u0000\u0000\u0000\u01e0\u01e1\u0003>\u001f\u0000"+
		"\u01e1\u01e2\u0005!\u0000\u0000\u01e2o\u0001\u0000\u0000\u0000\u01e3\u01e4"+
		"\u0003<\u001e\u0000\u01e4\u01e6\u0005&\u0000\u0000\u01e5\u01e7\u0003r"+
		"9\u0000\u01e6\u01e5\u0001\u0000\u0000\u0000\u01e6\u01e7\u0001\u0000\u0000"+
		"\u0000\u01e7\u01e8\u0001\u0000\u0000\u0000\u01e8\u01e9\u0005\'\u0000\u0000"+
		"\u01e9q\u0001\u0000\u0000\u0000\u01ea\u01ef\u0003t:\u0000\u01eb\u01ec"+
		"\u0005$\u0000\u0000\u01ec\u01ee\u0003t:\u0000\u01ed\u01eb\u0001\u0000"+
		"\u0000\u0000\u01ee\u01f1\u0001\u0000\u0000\u0000\u01ef\u01ed\u0001\u0000"+
		"\u0000\u0000\u01ef\u01f0\u0001\u0000\u0000\u0000\u01f0s\u0001\u0000\u0000"+
		"\u0000\u01f1\u01ef\u0001\u0000\u0000\u0000\u01f2\u01f3\u0003\n\u0005\u0000"+
		"\u01f3u\u0001\u0000\u0000\u0000\'y\u007f\u0086\u008a\u008c\u0097\u009b"+
		"\u009d\u00a6\u00ac\u00b0\u00b6\u00d1\u00d5\u00dd\u00df\u00e5\u00f2\u00f5"+
		"\u00fd\u011b\u0140\u0153\u015a\u0162\u0166\u016b\u016f\u017a\u017f\u0185"+
		"\u018c\u0192\u019a\u01cb\u01d1\u01dd\u01e6\u01ef";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}