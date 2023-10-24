grammar MainAntlr;

@header {
package org.cmjava2023.generated_from_antlr;
}

start   : (SCOPE_START
            | SCOPE_END
            | OPEN_PARENTHESIS
            | CLOSE_PARENTHESIS
            | OPEN_BRACKET
            | CLOSE_BRACKET
            | INVERTED_COMMAS
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
          )+;

SCOPE_START: '{';
SCOPE_END: '}';
OPEN_PARENTHESIS: '(';
CLOSE_PARENTHESIS: ')';
OPEN_BRACKET: '[';
CLOSE_BRACKET: ']';
INVERTED_COMMAS: '"';

DOT: '.';
EXCLAMATION_MARK: '!';
ASSIGNMENT: '=';
RETURN: 'return';
STATEMENT_END: ';';

MATH_DASH_OPERATOR: '-' | '+';

COMPARISON_OPERATOR : '<' | '>' | '<=' | '>=' | '==' | '<>';

IF: 'if';
ELSE: 'else';

IDENTIFIER: NAME;
INTEGER_CONSTANT: DIGIT+;

WHITESPACE  : [ \t\n]+ -> skip;

fragment
NAME: CHAR [a-zA-Z0-9]*;
fragment
CHAR   : [a-zA-Z] ;
fragment
DIGIT  : [0-9] ;