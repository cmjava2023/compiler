grammar MainAntlr;

@header {
package org.cmjava2023.generated_from_antlr;
}

// ----- Parser -----

// Start here
start : (statement)+;

// Statements and expressions
statement: class_declaration | (package_declaration SEMICOLON);
expression: function_call SEMICOLON;

// Packages
package_declaration:  PACKAGE_KEYWORD (potentially_nested_identifier);

// Classes
class_declaration: ACCESS_MODIFIER CLASS_KEYWORD IDENTIFIER CURLY_OPEN class_body CURLY_CLOSE;
class_body: function_declaration;

// Functions
function_declaration: ACCESS_MODIFIER INSTANCE_MODIFIER? type IDENTIFIER PAREN_OPEN function_declaration_args PAREN_CLOSE CURLY_OPEN function_declaration_body CURLY_CLOSE;
function_declaration_args: function_declaration_arg (COMMA function_declaration_arg)*;
function_declaration_arg: type IDENTIFIER;
function_declaration_body: expression;
function_call: potentially_nested_identifier PAREN_OPEN function_args PAREN_CLOSE;
function_args: function_arg (COMMA function_arg)*;
function_arg: STRING | IDENTIFIER | potentially_nested_identifier;

// Names
potentially_nested_identifier: IDENTIFIER (DOT IDENTIFIER)*;
type: VOID_KEYWORD | PRIMITIVE_TYPE | ARRAY_TYPE | REFERENCE_TYPE;
// ----- Lexer -----

// Keywords, need to be on top!
PACKAGE_KEYWORD: 'package';
CLASS_KEYWORD: 'class';

// Modifiers, need to be on top!
ACCESS_MODIFIER: 'public' | 'private' | 'protected';
INSTANCE_MODIFIER: 'static';

// Types REFERENCE_TYPE LAST!!!
//TYPE: PSEUDO_TYPE | PRIMITIVE_TYPE | REFERENCE_TYPE;

//POTENTIALLY_NESTED_IDENTIFIER: IDENTIFIER (DOT IDENTIFIER)*;

BOOLEAN_KEYWORD: 'boolean';
BYTE_KEYWORD: 'byte';
SHORT_KEYWORD: 'short';
INT_KEYWORD: 'int';
LONG_KEYWORD: 'long';
CHAR_KEYWORD: 'char';
FLOAT_KEYWORD: 'float';
DOUBLE_KEYWORD: 'double';
EXTENDS_KEYWORD: 'extends';
SUPER_KEYWORD: 'super';
VOID_KEYWORD: 'void';

IDENTIFIER: NAME;

// Names

ARRAY_TYPE: (PRIMITIVE_TYPE | CLASS_TYPE | TYPE_VARIABLE) BRACKET_OPEN BRACKET_CLOSE;
PRIMITIVE_TYPE: NUMERIC_TYPE | BOOLEAN_KEYWORD;
NUMERIC_TYPE: INTEGRAL_TYPE | FLOATING_POINT_TYPE;
INTEGRAL_TYPE: BYTE_KEYWORD | SHORT_KEYWORD | INT_KEYWORD | LONG_KEYWORD | CHAR_KEYWORD;
FLOATING_POINT_TYPE: FLOAT_KEYWORD | DOUBLE_KEYWORD;
TYPE_VARIABLE: IDENTIFIER;
CLASS_TYPE: IDENTIFIER TYPE_ARGUMENTS?;
TYPE_ARGUMENTS: DIAMOND_OPEN TYPE_ARGUMENT_LIST DIAMOND_CLOSE;
TYPE_ARGUMENT_LIST: TYPE_ARGUMENT (COMMA TYPE_ARGUMENT)*;
TYPE_ARGUMENT: REFERENCE_TYPE | WILDCARD;
WILDCARD: EXTENDS_KEYWORD REFERENCE_TYPE | SUPER_KEYWORD REFERENCE_TYPE;
REFERENCE_TYPE: CLASS_TYPE | TYPE_VARIABLE | ARRAY_TYPE;

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
DIAMOND_OPEN: '<';
DIAMOND_CLOSE: '>';
EQUALS: '=';

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