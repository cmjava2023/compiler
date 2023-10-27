grammar MainAntlr;

@header {
package org.cmjava2023.generated_from_antlr;
}

start   : statement (statement)*;

tokens:  (SCOPE_START
                              | SCOPE_END
                              | OPEN_PARENTHESIS
                              | CLOSE_PARENTHESIS
                              | OPEN_BRACKET
                              | CLOSE_BRACKET
                              | QUOTATION_MARK
                              | DOT
                              | EXCLAMATION_MARK
                              | ASSIGNMENT
                              | RETURN
                              | STATEMENT_END
                              | MATH_DASH_OPERATOR
                              | COMPARISON_OPERATOR
                              | IF
                              | ELSE
                              | IDENTIFIER
                              | INTEGER_CONSTANT
                              | ACCESS_MODIFIER
                              | CLASS_KEYWORD
                              | WHITESPACE
                              | FUNCTION_RETURNTYPE
                              | INSTANCE_MODIFIER
                              | PRIMITIVE_DATATYPE
                              | ACCESS_MODIFIER)+;

// Parser rules
statement: ((package_definition  | class_definition) (STATEMENT_END | EOF));
package_definition: PACKAGE_KEYWORD PACKAGE_NAME;
class_definition: ACCESS_MODIFIER CLASS_KEYWORD IDENTIFIER SCOPE_START class_body SCOPE_END;
class_body: function_definition;
function_definition: ACCESS_MODIFIER INSTANCE_MODIFIER FUNCTION_RETURNTYPE IDENTIFIER parameter SCOPE_START function_body SCOPE_END;
function_body: java_print_ln;
string_definition: QUOTATION_MARK (STRING_CONTENT) QUOTATION_MARK ;
print_statement_content: QUOTATION_MARK ('Hello world!') QUOTATION_MARK; // TODO: später verbessern, bitte nicht hardcoden
parameter: OPEN_PARENTHESIS (JAVA_STRING_ARGS) CLOSE_PARENTHESIS;

// Lexer rules

// TODO: später verbessern, bitte nicht hardcoden
java_print_ln: JAVA_PRINT_LN OPEN_PARENTHESIS print_statement_content CLOSE_PARENTHESIS STATEMENT_END;
JAVA_PRINT_LN: 'System.out.println';
JAVA_STRING_ARGS: 'String[] args';

SCOPE_START: '{';
SCOPE_END: '}';
OPEN_PARENTHESIS: '(';
CLOSE_PARENTHESIS: ')';
OPEN_BRACKET: '[';
CLOSE_BRACKET: ']';
QUOTATION_MARK: '"';
DOT: '.';
EXCLAMATION_MARK: '!';
ASSIGNMENT: '=';
RETURN: 'return';
STATEMENT_END: ';';
ACCESS_MODIFIER: 'public' | 'private' | 'protected';
PRIMITIVE_DATATYPE: 'boolean' | 'char' | 'double' | 'float' | 'long' | 'int' |'short' | 'byte';
CLASS_DATATYPE: 'String';
PACKAGE_KEYWORD: 'package';
CLASS_KEYWORD: 'class';
INSTANCE_MODIFIER: 'static';
FUNCTION_RETURNTYPE: PRIMITIVE_DATATYPE | 'void' | CLASS_DATATYPE;
MATH_DASH_OPERATOR: '-' | '+';
COMPARISON_OPERATOR : '<' | '>' | '<=' | '>=' | '==' | '<>';
IF: 'if';
ELSE: 'else';
IDENTIFIER: NAME;
ANYTHING: ANY_TEXT;
INTEGER_CONSTANT: DIGIT+;
PACKAGE_NAME: NAME ([/.]{1} NAME*)*;
STRING_CONTENT: (CHAR)+;
WHITESPACE  : (' ' | '\t' | '\r' | '\n') -> skip;
fragment
ANY_TEXT: [a-zA-Z0-9*]+;
fragment
NAME: CHAR [a-zA-Z0-9]*;
fragment
CHAR   : [a-zA-Z] ;
fragment
DIGIT  : [0-9] ;