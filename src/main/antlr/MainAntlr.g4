grammar MainAntlr;

@header {
package org.cmjava2023.generated_from_antlr;
}

// ----- Parser -----

// Start here
start : (global_scope)+;

// function_scope and expressions
global_scope: class_declaration | package_declaration SEMICOLON;

class_scope: (enum_declaration | class_declaration | function_declaration | ((variable_declaration | assignment ) SEMICOLON))*;

function_scope: ( enum_declaration | block_scope | (expressions | assignment | variable_declaration | return_statement | continue_statement | break_statement) SEMICOLON)*;

block_scope: if_statement | if_else_statement | while_loop | do_while_loop | for_loop | switch_statement;

expressions: expression (expression_operator expression)?;
variable_declaration: (primitive_type | reference_type) IDENTIFIER;
assignment: (variable_declaration | identifier) EQUALS expressions;

expression: function_call | IDENTIFIER | STRING | CHARACTER| FLOAT | DECIMAL | INTEGER | LONG | FALSE | TRUE | identifier | casting | expression (expression_concatinator|expression_operator) expression | PAREN_OPEN expression PAREN_CLOSE | array_expression | instantiation | access_index | (numerical_prefix | logical_prefix) expressions | expression expression_suffix;

expression_operator: logical_comparison_operator | numerical_comparison_operator | bit_comparison_operator;
expression_concatinator: PLUS | DIVISION | MULTIPLICATION | MINUS | MOD | DOT| PLUS EQUALS | MINUS EQUALS;
expression_suffix: DEC | INC;

instantiation: INSTANCE_KEYWORD (type (BRACKET_OPEN INTEGER BRACKET_CLOSE)+ | type);

access_index: IDENTIFIER (BRACKET_OPEN INTEGER BRACKET_CLOSE)+;
numerical_comparison_operator: DIAMOND_OPEN | DIAMOND_CLOSE | NEQ | EQ | LTE | GTE | MOD;
numerical_prefix: PLUS | MINUS;
logical_prefix: NOT | NOTNOT;
logical_comparison_operator: LAND | LOR;
bit_comparison_operator: BAND | BOR | BXOR | LOGICAL_SHIFT_R | BIT_SHIFT_L | BIT_SHIFT_R;

// Packages
package_declaration: PACKAGE_KEYWORD identifier;

// Classes
class_declaration: access_modifier CLASS_KEYWORD IDENTIFIER CURLY_OPEN class_scope CURLY_CLOSE;

// Enums
enum_declaration: ENUM_KEYWORD IDENTIFIER CURLY_OPEN IDENTIFIER (COMMA IDENTIFIER)* CURLY_CLOSE;

// Conditionals
if_statement: IF_KEYWORD PAREN_OPEN expressions PAREN_CLOSE CURLY_OPEN function_scope CURLY_CLOSE;
else_statement: ELSE_KEYWORD CURLY_OPEN function_scope CURLY_CLOSE;
if_else_statement: if_statement else_statement;
switch_statement: SWITCH_KEYWORD PAREN_OPEN expressions PAREN_CLOSE CURLY_OPEN switch_scope CURLY_CLOSE;
switch_scope: ((CASE_KEYWORD (expressions)) COLON function_scope)* DEFAULT_KEYWORD COLON function_scope;

return_statement: RETURN_KEYWORD (expressions)?;
break_statement: BREAK_KEYWORD;
continue_statement: CONTINUE_KEYWORD;

// Names
identifier: IDENTIFIER (DOT IDENTIFIER)*;
type: VOID_KEYWORD | primitive_type | array_type | reference_type;

// Types
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
array_type: (primitive_type | class_type |type_variable) (BRACKET_OPEN BRACKET_CLOSE)+;
array_expression: CURLY_OPEN ( (expressions (COMMA expressions)*)) CURLY_CLOSE;

// Control flow
while_loop: WHILE_KEYWORD PAREN_OPEN expressions PAREN_CLOSE CURLY_OPEN function_scope CURLY_CLOSE;
do_while_loop: DO_KEYWORD CURLY_OPEN function_scope CURLY_CLOSE WHILE_KEYWORD PAREN_OPEN expressions PAREN_CLOSE (SEMICOLON)?;
for_loop: FOR_KEYWORD PAREN_OPEN ((for_init SEMICOLON for_termination SEMICOLON for_update) | for_each) PAREN_CLOSE CURLY_OPEN function_scope CURLY_CLOSE;
for_each: variable_declaration COLON identifier;
for_init: assignment;
for_termination: expressions;
for_update: expressions;

// Casting
casting: PAREN_OPEN type PAREN_CLOSE expressions;

// Modifiers
access_modifier: PRIVATE_KEYWORD | PUBLIC_KEYWORD | PROTECTED_KEYWORD;

// Functions
function_declaration: access_modifier INSTANCE_MODIFIER? type IDENTIFIER PAREN_OPEN function_declaration_args? PAREN_CLOSE (THROWS_KEYWORD identifier)? CURLY_OPEN function_scope CURLY_CLOSE;
function_declaration_args: function_declaration_arg (COMMA function_declaration_arg)*;
function_declaration_arg: type IDENTIFIER;
function_call: identifier PAREN_OPEN function_args? PAREN_CLOSE;
function_args: function_arg (COMMA function_arg)*;
function_arg: expressions;

// ----- Lexer -----

// Keywords, need to be on top!
PACKAGE_KEYWORD: 'package';
CLASS_KEYWORD: 'class';

// Constants
FALSE: 'false';
TRUE: 'true';

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
ENUM_KEYWORD: 'enum';
EXTENDS_KEYWORD: 'extends';
SUPER_KEYWORD: 'super';
VOID_KEYWORD: 'void';
PUBLIC_KEYWORD: 'public';
PRIVATE_KEYWORD: 'private';
PROTECTED_KEYWORD: 'protected';
RETURN_KEYWORD: 'return';
INSTANCE_KEYWORD: 'new';
THROWS_KEYWORD: 'throws';

IF_KEYWORD: 'if';
ELSE_KEYWORD: 'else';

FOR_KEYWORD: 'for';
WHILE_KEYWORD: 'while';
DO_KEYWORD: 'do';
SWITCH_KEYWORD: 'switch';
CASE_KEYWORD: 'case';
BREAK_KEYWORD: 'break';
CONTINUE_KEYWORD: 'continue';
DEFAULT_KEYWORD: 'default';

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

// Numerical Operators
EQ: '==';
NEQ: '!=';
GTE: '>=';
LTE: '<=';
MOD: '%';
PLUS: '+';
MINUS: '-';
MULTIPLICATION: '*';
DIVISION: '/';
INC: '++';
DEC: '--';

// Logical Operators
LAND: '&&';
LOR: '||';
NOT: '!';
NOTNOT: '!!';

// Bit Operators
BAND: '&';
BOR: '|';
BIT_SHIFT_L: '<<';
BIT_SHIFT_R: '>>';
BXOR: '^';
LOGICAL_SHIFT_R: '>>>';

// Comments
COMMENT : '/*' .*? '*/' -> skip;
LINE_COMMENT : '//' ~[\r\n]* -> skip;

// Misc
WHITESPACE  : (' ' | '\t' | '\r' | '\n') -> skip;
STRING : '"' (~'"'|'/"')* '"'; // Danke https://stackoverflow.com/a/36615281
CHARACTER : '\'' (~'"'|'/"')* '\''; // Danke https://stackoverflow.com/a/36615281

FLOAT: (((INTEGER DOT INTEGER) | (DOT INTEGER)| (INTEGER DOT) | INTEGER | (INTEGER DOT )? INTEGER FLOAT_EXPONENT_SUFFIX (PLUS | MINUS)? INTEGER) FLOATING_POINT_SUFFIX);
DECIMAL: ([1-9] DIGIT* | [0]) DOT INTEGER;
INTEGER: DIGIT+;

LONG: INTEGER LONG_SUFFIX;

FLOAT_EXPONENT_SUFFIX: 'e';
FLOATING_POINT_SUFFIX: 'f';
LONG_SUFFIX: 'L';

// Fragments
fragment
NAME: CHAR (DIGIT | CHAR)*; // Names must begin with a letter.
fragment
CHAR   : [a-zA-Z$_] ; // The general rules for naming variables are: Names can contain letters, digits, underscores, and dollar signs.
fragment
DIGIT  : [0-9] ;