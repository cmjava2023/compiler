grammar MainAntlr;

@header {
package org.cmjava2023.generated_from_antlr;
}

// ----- Parser -----

// Start here
start : (statement)+;

// Statements and expressions
statement: global_scope;

global_scope: class_declaration | package_declaration SEMICOLON;

class_scope: (class_declaration | function_declaration | ((instantiation | assignment ) SEMICOLON))+;

expression: (function_call | DECIMAL | INTEGER | IDENTIFIER | STRING | potentially_nested_identifier ) SEMICOLON?;
instantiation: primitive_type potentially_nested_identifier;
assignment: instantiation EQUALS expression;

// Packages
package_declaration: PACKAGE_KEYWORD potentially_nested_identifier;

// Classes
class_declaration: access_modifier CLASS_KEYWORD IDENTIFIER CURLY_OPEN class_scope CURLY_CLOSE;

// Functions
function_declaration: access_modifier INSTANCE_MODIFIER? type IDENTIFIER PAREN_OPEN function_declaration_args PAREN_CLOSE CURLY_OPEN function_declaration_body CURLY_CLOSE;
function_declaration_args: function_declaration_arg (COMMA function_declaration_arg)*;
function_declaration_arg: type IDENTIFIER;
function_declaration_body: expression;
function_call: potentially_nested_identifier PAREN_OPEN function_args PAREN_CLOSE;
function_args: function_arg (COMMA function_arg)*;
function_arg: (expression)*;

// Names
potentially_nested_identifier: IDENTIFIER (DOT IDENTIFIER)*;
type: VOID_KEYWORD | primitive_type | array_type | reference_type;

//Types
integral_type: BYTE_KEYWORD | SHORT_KEYWORD | INT_KEYWORD | LONG_KEYWORD | CHAR_KEYWORD;
floating_point_type: FLOAT_KEYWORD | DOUBLE_KEYWORD;
numeric_type: integral_type | floating_point_type;
primitive_type: numeric_type | BOOLEAN_KEYWORD;

reference_type: class_type | type_variable | array_type;
class_type: IDENTIFIER type_arguments?;

type_arguments:DIAMOND_OPEN type_argument_list DIAMOND_CLOSE;
type_argument_list: type_argument (COMMA type_argument)*;
type_argument: reference_type | wildcard;
wildcard: EXTENDS_KEYWORD reference_type | SUPER_KEYWORD reference_type;
type_variable: IDENTIFIER;
array_type: (primitive_type | class_type |type_variable) BRACKET_OPEN BRACKET_CLOSE;

// Modifiers
access_modifier: PRIVATE_KEYWORD | PUBLIC_KEYWORD | PROTECTED_KEYWORD;

// ----- Lexer -----

// Keywords, need to be on top!
PACKAGE_KEYWORD: 'package';
CLASS_KEYWORD: 'class';

// Modifiers, need to be on top!
INSTANCE_MODIFIER: 'static';

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
PUBLIC_KEYWORD: 'public';
PRIVATE_KEYWORD: 'private';
PROTECTED_KEYWORD: 'protected';

// Names
IDENTIFIER: NAME;

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

INTEGER: DIGIT+;
DECIMAL: [1-9] DIGIT* DOT DIGIT+;

// Fragments
fragment
NAME: CHAR (DIGIT | CHAR)*; // Names must begin with a letter.
fragment
CHAR   : [a-zA-Z$_] ; // The general rules for naming variables are: Names can contain letters, digits, underscores, and dollar signs.
fragment
DIGIT  : [0-9] ;