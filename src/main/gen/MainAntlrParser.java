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
		SCOPE_START=1, SCOPE_END=2, OPEN_PARENTHESIS=3, CLOSE_PARENTHESIS=4, OPEN_BRACKET=5, 
		CLOSE_BRACKET=6, INVERTED_COMMAS=7, DOT=8, EXCLAMATION_MARK=9, ASSIGNMENT=10, 
		RETURN=11, STATEMENT_END=12, MATH_DASH_OPERATOR=13, COMPARISON_OPERATOR=14, 
		IF=15, ELSE=16, IDENTIFIER=17, INTEGER_CONSTANT=18, WHITESPACE=19;
	public static final int
		RULE_start = 0;
	private static String[] makeRuleNames() {
		return new String[] {
			"start"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "'}'", "'('", "')'", "'['", "']'", "'\"'", "'.'", "'!'", 
			"'='", "'return'", "';'", null, null, "'if'", "'else'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SCOPE_START", "SCOPE_END", "OPEN_PARENTHESIS", "CLOSE_PARENTHESIS", 
			"OPEN_BRACKET", "CLOSE_BRACKET", "INVERTED_COMMAS", "DOT", "EXCLAMATION_MARK", 
			"ASSIGNMENT", "RETURN", "STATEMENT_END", "MATH_DASH_OPERATOR", "COMPARISON_OPERATOR", 
			"IF", "ELSE", "IDENTIFIER", "INTEGER_CONSTANT", "WHITESPACE"
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
		public List<TerminalNode> SCOPE_START() { return getTokens(MainAntlrParser.SCOPE_START); }
		public TerminalNode SCOPE_START(int i) {
			return getToken(MainAntlrParser.SCOPE_START, i);
		}
		public List<TerminalNode> SCOPE_END() { return getTokens(MainAntlrParser.SCOPE_END); }
		public TerminalNode SCOPE_END(int i) {
			return getToken(MainAntlrParser.SCOPE_END, i);
		}
		public List<TerminalNode> OPEN_PARENTHESIS() { return getTokens(MainAntlrParser.OPEN_PARENTHESIS); }
		public TerminalNode OPEN_PARENTHESIS(int i) {
			return getToken(MainAntlrParser.OPEN_PARENTHESIS, i);
		}
		public List<TerminalNode> CLOSE_PARENTHESIS() { return getTokens(MainAntlrParser.CLOSE_PARENTHESIS); }
		public TerminalNode CLOSE_PARENTHESIS(int i) {
			return getToken(MainAntlrParser.CLOSE_PARENTHESIS, i);
		}
		public List<TerminalNode> OPEN_BRACKET() { return getTokens(MainAntlrParser.OPEN_BRACKET); }
		public TerminalNode OPEN_BRACKET(int i) {
			return getToken(MainAntlrParser.OPEN_BRACKET, i);
		}
		public List<TerminalNode> CLOSE_BRACKET() { return getTokens(MainAntlrParser.CLOSE_BRACKET); }
		public TerminalNode CLOSE_BRACKET(int i) {
			return getToken(MainAntlrParser.CLOSE_BRACKET, i);
		}
		public List<TerminalNode> INVERTED_COMMAS() { return getTokens(MainAntlrParser.INVERTED_COMMAS); }
		public TerminalNode INVERTED_COMMAS(int i) {
			return getToken(MainAntlrParser.INVERTED_COMMAS, i);
		}
		public List<TerminalNode> DOT() { return getTokens(MainAntlrParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(MainAntlrParser.DOT, i);
		}
		public List<TerminalNode> EXCLAMATION_MARK() { return getTokens(MainAntlrParser.EXCLAMATION_MARK); }
		public TerminalNode EXCLAMATION_MARK(int i) {
			return getToken(MainAntlrParser.EXCLAMATION_MARK, i);
		}
		public List<TerminalNode> ASSIGNMENT() { return getTokens(MainAntlrParser.ASSIGNMENT); }
		public TerminalNode ASSIGNMENT(int i) {
			return getToken(MainAntlrParser.ASSIGNMENT, i);
		}
		public List<TerminalNode> RETURN() { return getTokens(MainAntlrParser.RETURN); }
		public TerminalNode RETURN(int i) {
			return getToken(MainAntlrParser.RETURN, i);
		}
		public List<TerminalNode> STATEMENT_END() { return getTokens(MainAntlrParser.STATEMENT_END); }
		public TerminalNode STATEMENT_END(int i) {
			return getToken(MainAntlrParser.STATEMENT_END, i);
		}
		public List<TerminalNode> MATH_DASH_OPERATOR() { return getTokens(MainAntlrParser.MATH_DASH_OPERATOR); }
		public TerminalNode MATH_DASH_OPERATOR(int i) {
			return getToken(MainAntlrParser.MATH_DASH_OPERATOR, i);
		}
		public List<TerminalNode> COMPARISON_OPERATOR() { return getTokens(MainAntlrParser.COMPARISON_OPERATOR); }
		public TerminalNode COMPARISON_OPERATOR(int i) {
			return getToken(MainAntlrParser.COMPARISON_OPERATOR, i);
		}
		public List<TerminalNode> IF() { return getTokens(MainAntlrParser.IF); }
		public TerminalNode IF(int i) {
			return getToken(MainAntlrParser.IF, i);
		}
		public List<TerminalNode> ELSE() { return getTokens(MainAntlrParser.ELSE); }
		public TerminalNode ELSE(int i) {
			return getToken(MainAntlrParser.ELSE, i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(MainAntlrParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(MainAntlrParser.IDENTIFIER, i);
		}
		public List<TerminalNode> INTEGER_CONSTANT() { return getTokens(MainAntlrParser.INTEGER_CONSTANT); }
		public TerminalNode INTEGER_CONSTANT(int i) {
			return getToken(MainAntlrParser.INTEGER_CONSTANT, i);
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
			setState(3); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(2);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 524286L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(5); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 524286L) != 0) );
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
		"\u0004\u0001\u0013\b\u0002\u0000\u0007\u0000\u0001\u0000\u0004\u0000\u0004"+
		"\b\u0000\u000b\u0000\f\u0000\u0005\u0001\u0000\u0000\u0000\u0001\u0000"+
		"\u0000\u0001\u0001\u0000\u0001\u0012\u0007\u0000\u0003\u0001\u0000\u0000"+
		"\u0000\u0002\u0004\u0007\u0000\u0000\u0000\u0003\u0002\u0001\u0000\u0000"+
		"\u0000\u0004\u0005\u0001\u0000\u0000\u0000\u0005\u0003\u0001\u0000\u0000"+
		"\u0000\u0005\u0006\u0001\u0000\u0000\u0000\u0006\u0001\u0001\u0000\u0000"+
		"\u0000\u0001\u0005";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}