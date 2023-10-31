grammar MainAntlr;

@header {
package org.cmjava2023.generated_from_antlr;
}

// ----- Parser -----

// Start here
start : statement*;

// Statements and expressions
statement: (package_declaration SEMICOLON) | class_declaration;
expression: (function_call) SEMICOLON;

// Packages
package_declaration: PACKAGE_KEYWORD POTENTIALLY_NESTED_IDENTIFIER;

// Classes
class_declaration: ACCESS_MODIFIER CLASS IDENTIFIER CURLY_OPEN class_body CURLY_CLOSE;
class_body: function_declaration;

// Functions
function_declaration: ACCESS_MODIFIER INSTANCE_MODIFIER? TYPE IDENTIFIER PAREN_OPEN function_declaration_args PAREN_CLOSE CURLY_OPEN function_declaration_body CURLY_CLOSE;
function_declaration_args: TYPE IDENTIFIER (COMMA TYPE IDENTIFIER)*;
function_declaration_body: expression;
function_call: POTENTIALLY_NESTED_IDENTIFIER PAREN_OPEN function_args PAREN_CLOSE;
function_args: STRING;

// ----- Lexer -----

// Keywords, need to be on top!
PACKAGE_KEYWORD: 'package';
CLASS: 'class';

// Modifiers, need to be on top!
ACCESS_MODIFIER: 'public' | 'private' | 'protected';
INSTANCE_MODIFIER: 'static';

// Types
TYPE: ('void' | 'String') TYPE_ADDITION?;
TYPE_ADDITION: '[]';

// Names
IDENTIFIER: NAME;
POTENTIALLY_NESTED_IDENTIFIER: IDENTIFIER (DOT IDENTIFIER)*;

// Punctuation
SEMICOLON: ';';
COLON: ':';
COMMA: ',';
DOT: '.';

// Brackets
PAREN_OPEN: '(';
PAREN_CLOSE: ')';
BRACKET_OPEN: '[';
BRACKET_CLOSE: ']';
CURLY_OPEN: '{';
CURLY_CLOSE: '}';

// Misc
WHITESPACE  : (' ' | '\t' | '\r' | '\n') -> skip;
STRING : '"' (~'"'|'/"')* '"'; // Danke https://stackoverflow.com/a/36615281

// Fragments
fragment
NAME: CHAR (DIGIT | CHAR)*; // Names must begin with a letter.
fragment
CHAR   : [a-zA-Z$_] ; // The general rules for naming variables are: Names can contain letters, digits, underscores, and dollar signs.
fragment
DIGIT  : [0-9] ;