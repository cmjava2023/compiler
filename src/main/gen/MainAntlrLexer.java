// Generated from D:/Git Repos/compiler/src/main/antlr/MainAntlr.g4 by ANTLR 4.13.1

package org.cmjava2023.generated_from_antlr;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class MainAntlrLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SCOPE_START=1, SCOPE_END=2, OPEN_PARENTHESIS=3, CLOSE_PARENTHESIS=4, OPEN_BRACKET=5, 
		CLOSE_BRACKET=6, INVERTED_COMMAS=7, DOT=8, EXCLAMATION_MARK=9, ASSIGNMENT=10, 
		RETURN=11, STATEMENT_END=12, MATH_DASH_OPERATOR=13, COMPARISON_OPERATOR=14, 
		IF=15, ELSE=16, IDENTIFIER=17, INTEGER_CONSTANT=18, WHITESPACE=19;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"SCOPE_START", "SCOPE_END", "OPEN_PARENTHESIS", "CLOSE_PARENTHESIS", 
			"OPEN_BRACKET", "CLOSE_BRACKET", "INVERTED_COMMAS", "DOT", "EXCLAMATION_MARK", 
			"ASSIGNMENT", "RETURN", "STATEMENT_END", "MATH_DASH_OPERATOR", "COMPARISON_OPERATOR", 
			"IF", "ELSE", "IDENTIFIER", "INTEGER_CONSTANT", "WHITESPACE", "NAME", 
			"CHAR", "DIGIT"
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


	public MainAntlrLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MainAntlr.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0013x\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001"+
		"\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001"+
		"\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\rV\b\r\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0004\u0011c\b"+
		"\u0011\u000b\u0011\f\u0011d\u0001\u0012\u0004\u0012h\b\u0012\u000b\u0012"+
		"\f\u0012i\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0005\u0013"+
		"p\b\u0013\n\u0013\f\u0013s\t\u0013\u0001\u0014\u0001\u0014\u0001\u0015"+
		"\u0001\u0015\u0000\u0000\u0016\u0001\u0001\u0003\u0002\u0005\u0003\u0007"+
		"\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b"+
		"\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013"+
		"\'\u0000)\u0000+\u0000\u0001\u0000\u0006\u0002\u0000++--\u0002\u0000<"+
		"<>>\u0002\u0000\t\n  \u0003\u000009AZaz\u0002\u0000AZaz\u0001\u000009"+
		"{\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000"+
		"\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000"+
		"\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000"+
		"\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019"+
		"\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d"+
		"\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001"+
		"\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000"+
		"\u0000\u0001-\u0001\u0000\u0000\u0000\u0003/\u0001\u0000\u0000\u0000\u0005"+
		"1\u0001\u0000\u0000\u0000\u00073\u0001\u0000\u0000\u0000\t5\u0001\u0000"+
		"\u0000\u0000\u000b7\u0001\u0000\u0000\u0000\r9\u0001\u0000\u0000\u0000"+
		"\u000f;\u0001\u0000\u0000\u0000\u0011=\u0001\u0000\u0000\u0000\u0013?"+
		"\u0001\u0000\u0000\u0000\u0015A\u0001\u0000\u0000\u0000\u0017H\u0001\u0000"+
		"\u0000\u0000\u0019J\u0001\u0000\u0000\u0000\u001bU\u0001\u0000\u0000\u0000"+
		"\u001dW\u0001\u0000\u0000\u0000\u001fZ\u0001\u0000\u0000\u0000!_\u0001"+
		"\u0000\u0000\u0000#b\u0001\u0000\u0000\u0000%g\u0001\u0000\u0000\u0000"+
		"\'m\u0001\u0000\u0000\u0000)t\u0001\u0000\u0000\u0000+v\u0001\u0000\u0000"+
		"\u0000-.\u0005{\u0000\u0000.\u0002\u0001\u0000\u0000\u0000/0\u0005}\u0000"+
		"\u00000\u0004\u0001\u0000\u0000\u000012\u0005(\u0000\u00002\u0006\u0001"+
		"\u0000\u0000\u000034\u0005)\u0000\u00004\b\u0001\u0000\u0000\u000056\u0005"+
		"[\u0000\u00006\n\u0001\u0000\u0000\u000078\u0005]\u0000\u00008\f\u0001"+
		"\u0000\u0000\u00009:\u0005\"\u0000\u0000:\u000e\u0001\u0000\u0000\u0000"+
		";<\u0005.\u0000\u0000<\u0010\u0001\u0000\u0000\u0000=>\u0005!\u0000\u0000"+
		">\u0012\u0001\u0000\u0000\u0000?@\u0005=\u0000\u0000@\u0014\u0001\u0000"+
		"\u0000\u0000AB\u0005r\u0000\u0000BC\u0005e\u0000\u0000CD\u0005t\u0000"+
		"\u0000DE\u0005u\u0000\u0000EF\u0005r\u0000\u0000FG\u0005n\u0000\u0000"+
		"G\u0016\u0001\u0000\u0000\u0000HI\u0005;\u0000\u0000I\u0018\u0001\u0000"+
		"\u0000\u0000JK\u0007\u0000\u0000\u0000K\u001a\u0001\u0000\u0000\u0000"+
		"LV\u0007\u0001\u0000\u0000MN\u0005<\u0000\u0000NV\u0005=\u0000\u0000O"+
		"P\u0005>\u0000\u0000PV\u0005=\u0000\u0000QR\u0005=\u0000\u0000RV\u0005"+
		"=\u0000\u0000ST\u0005<\u0000\u0000TV\u0005>\u0000\u0000UL\u0001\u0000"+
		"\u0000\u0000UM\u0001\u0000\u0000\u0000UO\u0001\u0000\u0000\u0000UQ\u0001"+
		"\u0000\u0000\u0000US\u0001\u0000\u0000\u0000V\u001c\u0001\u0000\u0000"+
		"\u0000WX\u0005i\u0000\u0000XY\u0005f\u0000\u0000Y\u001e\u0001\u0000\u0000"+
		"\u0000Z[\u0005e\u0000\u0000[\\\u0005l\u0000\u0000\\]\u0005s\u0000\u0000"+
		"]^\u0005e\u0000\u0000^ \u0001\u0000\u0000\u0000_`\u0003\'\u0013\u0000"+
		"`\"\u0001\u0000\u0000\u0000ac\u0003+\u0015\u0000ba\u0001\u0000\u0000\u0000"+
		"cd\u0001\u0000\u0000\u0000db\u0001\u0000\u0000\u0000de\u0001\u0000\u0000"+
		"\u0000e$\u0001\u0000\u0000\u0000fh\u0007\u0002\u0000\u0000gf\u0001\u0000"+
		"\u0000\u0000hi\u0001\u0000\u0000\u0000ig\u0001\u0000\u0000\u0000ij\u0001"+
		"\u0000\u0000\u0000jk\u0001\u0000\u0000\u0000kl\u0006\u0012\u0000\u0000"+
		"l&\u0001\u0000\u0000\u0000mq\u0003)\u0014\u0000np\u0007\u0003\u0000\u0000"+
		"on\u0001\u0000\u0000\u0000ps\u0001\u0000\u0000\u0000qo\u0001\u0000\u0000"+
		"\u0000qr\u0001\u0000\u0000\u0000r(\u0001\u0000\u0000\u0000sq\u0001\u0000"+
		"\u0000\u0000tu\u0007\u0004\u0000\u0000u*\u0001\u0000\u0000\u0000vw\u0007"+
		"\u0005\u0000\u0000w,\u0001\u0000\u0000\u0000\u0005\u0000Udiq\u0001\u0006"+
		"\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}