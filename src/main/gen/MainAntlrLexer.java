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
		PACKAGE_KEYWORD=1, CLASS=2, ACCESS_MODIFIER=3, INSTANCE_MODIFIER=4, TYPE=5, 
		TYPE_ADDITION=6, IDENTIFIER=7, POTENTIALLY_NESTED_IDENTIFIER=8, SEMICOLON=9, 
		COLON=10, COMMA=11, DOT=12, PAREN_OPEN=13, PAREN_CLOSE=14, BRACKET_OPEN=15, 
		BRACKET_CLOSE=16, CURLY_OPEN=17, CURLY_CLOSE=18, WHITESPACE=19, STRING=20;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"PACKAGE_KEYWORD", "CLASS", "ACCESS_MODIFIER", "INSTANCE_MODIFIER", "TYPE", 
			"TYPE_ADDITION", "IDENTIFIER", "POTENTIALLY_NESTED_IDENTIFIER", "SEMICOLON", 
			"COLON", "COMMA", "DOT", "PAREN_OPEN", "PAREN_CLOSE", "BRACKET_OPEN", 
			"BRACKET_CLOSE", "CURLY_OPEN", "CURLY_CLOSE", "WHITESPACE", "STRING", 
			"NAME", "CHAR", "DIGIT"
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
		"\u0004\u0000\u0014\u00a8\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002T\b\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004"+
		"g\b\u0004\u0001\u0004\u0003\u0004j\b\u0004\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0005\u0007u\b\u0007\n\u0007\f\u0007x\t\u0007\u0001\b\u0001\b\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001"+
		"\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u0010"+
		"\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0005\u0013"+
		"\u0096\b\u0013\n\u0013\f\u0013\u0099\t\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0005\u0014\u00a0\b\u0014\n\u0014\f\u0014"+
		"\u00a3\t\u0014\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0000\u0000"+
		"\u0017\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006"+
		"\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e"+
		"\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0000+\u0000-\u0000"+
		"\u0001\u0000\u0004\u0003\u0000\t\n\r\r  \u0001\u0000\"\"\u0004\u0000$"+
		"$AZ__az\u0001\u000009\u00ad\u0000\u0001\u0001\u0000\u0000\u0000\u0000"+
		"\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000"+
		"\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b"+
		"\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001"+
		"\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001"+
		"\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001"+
		"\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001"+
		"\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001"+
		"\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000"+
		"\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000"+
		"\u0001/\u0001\u0000\u0000\u0000\u00037\u0001\u0000\u0000\u0000\u0005S"+
		"\u0001\u0000\u0000\u0000\u0007U\u0001\u0000\u0000\u0000\tf\u0001\u0000"+
		"\u0000\u0000\u000bk\u0001\u0000\u0000\u0000\rn\u0001\u0000\u0000\u0000"+
		"\u000fp\u0001\u0000\u0000\u0000\u0011y\u0001\u0000\u0000\u0000\u0013{"+
		"\u0001\u0000\u0000\u0000\u0015}\u0001\u0000\u0000\u0000\u0017\u007f\u0001"+
		"\u0000\u0000\u0000\u0019\u0081\u0001\u0000\u0000\u0000\u001b\u0083\u0001"+
		"\u0000\u0000\u0000\u001d\u0085\u0001\u0000\u0000\u0000\u001f\u0087\u0001"+
		"\u0000\u0000\u0000!\u0089\u0001\u0000\u0000\u0000#\u008b\u0001\u0000\u0000"+
		"\u0000%\u008d\u0001\u0000\u0000\u0000\'\u0091\u0001\u0000\u0000\u0000"+
		")\u009c\u0001\u0000\u0000\u0000+\u00a4\u0001\u0000\u0000\u0000-\u00a6"+
		"\u0001\u0000\u0000\u0000/0\u0005p\u0000\u000001\u0005a\u0000\u000012\u0005"+
		"c\u0000\u000023\u0005k\u0000\u000034\u0005a\u0000\u000045\u0005g\u0000"+
		"\u000056\u0005e\u0000\u00006\u0002\u0001\u0000\u0000\u000078\u0005c\u0000"+
		"\u000089\u0005l\u0000\u00009:\u0005a\u0000\u0000:;\u0005s\u0000\u0000"+
		";<\u0005s\u0000\u0000<\u0004\u0001\u0000\u0000\u0000=>\u0005p\u0000\u0000"+
		">?\u0005u\u0000\u0000?@\u0005b\u0000\u0000@A\u0005l\u0000\u0000AB\u0005"+
		"i\u0000\u0000BT\u0005c\u0000\u0000CD\u0005p\u0000\u0000DE\u0005r\u0000"+
		"\u0000EF\u0005i\u0000\u0000FG\u0005v\u0000\u0000GH\u0005a\u0000\u0000"+
		"HI\u0005t\u0000\u0000IT\u0005e\u0000\u0000JK\u0005p\u0000\u0000KL\u0005"+
		"r\u0000\u0000LM\u0005o\u0000\u0000MN\u0005t\u0000\u0000NO\u0005e\u0000"+
		"\u0000OP\u0005c\u0000\u0000PQ\u0005t\u0000\u0000QR\u0005e\u0000\u0000"+
		"RT\u0005d\u0000\u0000S=\u0001\u0000\u0000\u0000SC\u0001\u0000\u0000\u0000"+
		"SJ\u0001\u0000\u0000\u0000T\u0006\u0001\u0000\u0000\u0000UV\u0005s\u0000"+
		"\u0000VW\u0005t\u0000\u0000WX\u0005a\u0000\u0000XY\u0005t\u0000\u0000"+
		"YZ\u0005i\u0000\u0000Z[\u0005c\u0000\u0000[\b\u0001\u0000\u0000\u0000"+
		"\\]\u0005v\u0000\u0000]^\u0005o\u0000\u0000^_\u0005i\u0000\u0000_g\u0005"+
		"d\u0000\u0000`a\u0005S\u0000\u0000ab\u0005t\u0000\u0000bc\u0005r\u0000"+
		"\u0000cd\u0005i\u0000\u0000de\u0005n\u0000\u0000eg\u0005g\u0000\u0000"+
		"f\\\u0001\u0000\u0000\u0000f`\u0001\u0000\u0000\u0000gi\u0001\u0000\u0000"+
		"\u0000hj\u0003\u000b\u0005\u0000ih\u0001\u0000\u0000\u0000ij\u0001\u0000"+
		"\u0000\u0000j\n\u0001\u0000\u0000\u0000kl\u0005[\u0000\u0000lm\u0005]"+
		"\u0000\u0000m\f\u0001\u0000\u0000\u0000no\u0003)\u0014\u0000o\u000e\u0001"+
		"\u0000\u0000\u0000pv\u0003\r\u0006\u0000qr\u0003\u0017\u000b\u0000rs\u0003"+
		"\r\u0006\u0000su\u0001\u0000\u0000\u0000tq\u0001\u0000\u0000\u0000ux\u0001"+
		"\u0000\u0000\u0000vt\u0001\u0000\u0000\u0000vw\u0001\u0000\u0000\u0000"+
		"w\u0010\u0001\u0000\u0000\u0000xv\u0001\u0000\u0000\u0000yz\u0005;\u0000"+
		"\u0000z\u0012\u0001\u0000\u0000\u0000{|\u0005:\u0000\u0000|\u0014\u0001"+
		"\u0000\u0000\u0000}~\u0005,\u0000\u0000~\u0016\u0001\u0000\u0000\u0000"+
		"\u007f\u0080\u0005.\u0000\u0000\u0080\u0018\u0001\u0000\u0000\u0000\u0081"+
		"\u0082\u0005(\u0000\u0000\u0082\u001a\u0001\u0000\u0000\u0000\u0083\u0084"+
		"\u0005)\u0000\u0000\u0084\u001c\u0001\u0000\u0000\u0000\u0085\u0086\u0005"+
		"[\u0000\u0000\u0086\u001e\u0001\u0000\u0000\u0000\u0087\u0088\u0005]\u0000"+
		"\u0000\u0088 \u0001\u0000\u0000\u0000\u0089\u008a\u0005{\u0000\u0000\u008a"+
		"\"\u0001\u0000\u0000\u0000\u008b\u008c\u0005}\u0000\u0000\u008c$\u0001"+
		"\u0000\u0000\u0000\u008d\u008e\u0007\u0000\u0000\u0000\u008e\u008f\u0001"+
		"\u0000\u0000\u0000\u008f\u0090\u0006\u0012\u0000\u0000\u0090&\u0001\u0000"+
		"\u0000\u0000\u0091\u0097\u0005\"\u0000\u0000\u0092\u0096\b\u0001\u0000"+
		"\u0000\u0093\u0094\u0005/\u0000\u0000\u0094\u0096\u0005\"\u0000\u0000"+
		"\u0095\u0092\u0001\u0000\u0000\u0000\u0095\u0093\u0001\u0000\u0000\u0000"+
		"\u0096\u0099\u0001\u0000\u0000\u0000\u0097\u0095\u0001\u0000\u0000\u0000"+
		"\u0097\u0098\u0001\u0000\u0000\u0000\u0098\u009a\u0001\u0000\u0000\u0000"+
		"\u0099\u0097\u0001\u0000\u0000\u0000\u009a\u009b\u0005\"\u0000\u0000\u009b"+
		"(\u0001\u0000\u0000\u0000\u009c\u00a1\u0003+\u0015\u0000\u009d\u00a0\u0003"+
		"-\u0016\u0000\u009e\u00a0\u0003+\u0015\u0000\u009f\u009d\u0001\u0000\u0000"+
		"\u0000\u009f\u009e\u0001\u0000\u0000\u0000\u00a0\u00a3\u0001\u0000\u0000"+
		"\u0000\u00a1\u009f\u0001\u0000\u0000\u0000\u00a1\u00a2\u0001\u0000\u0000"+
		"\u0000\u00a2*\u0001\u0000\u0000\u0000\u00a3\u00a1\u0001\u0000\u0000\u0000"+
		"\u00a4\u00a5\u0007\u0002\u0000\u0000\u00a5,\u0001\u0000\u0000\u0000\u00a6"+
		"\u00a7\u0007\u0003\u0000\u0000\u00a7.\u0001\u0000\u0000\u0000\t\u0000"+
		"Sfiv\u0095\u0097\u009f\u00a1\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}