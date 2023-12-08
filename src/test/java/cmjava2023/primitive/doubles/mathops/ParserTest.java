package cmjava2023.primitive.doubles.mathops;

import cmjava2023.util.TestPathsHelper;
import cmjava2023.util.treePrinter.ParseTreePrinter;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;
import org.cmjava2023.generated_from_antlr.MainAntlrParser;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(cmjava2023.util.QualifiedDisplayNameGenerator.class)
public class ParserTest {
    @Test
    public void snapshot() throws IOException {
        CharStream charStreamOfGivenFilePath = CharStreams.fromFileName(new TestPathsHelper(this).GetPathOfMainJavaTestResourceInSamePackage());
        Lexer lexer = new org.cmjava2023.generated_from_antlr.MainAntlrLexer(charStreamOfGivenFilePath);
        MainAntlrParser parser = new MainAntlrParser(new CommonTokenStream(lexer));

        ParseTree tree = parser.start();

                String expected =
                """
Start: packagecmjava2023.primitive.doubles.mathops;publicclassMain{publicstaticvoidmain(String[]args){doubled=10;doubled2=d+d;doubled3=d2/2;doubled4=d2*2;doubled5=-d2;doubled6=d2%10;doubled7=d-d;System.out.println("d:");System.out.println(d);System.out.println("d2:");System.out.println(d2);System.out.println("d3:");System.out.println(d3);System.out.println("d4:");System.out.println(d4);System.out.println("d5:");System.out.println(d5);System.out.println("d6:");System.out.println(d6);System.out.println("d7:");System.out.println(d7);}}
|- Global_scope: packagecmjava2023.primitive.doubles.mathops;
|  |- Package_declaration: packagecmjava2023.primitive.doubles.mathops
|  |  |- package
|  |  L  Identifier: cmjava2023.primitive.doubles.mathops
|  |     |- cmjava2023
|  |     |- .
|  |     |- primitive
|  |     |- .
|  |     |- doubles
|  |     |- .
|  |     L  mathops
|  L  ;
L  Global_scope: publicclassMain{publicstaticvoidmain(String[]args){doubled=10;doubled2=d+d;doubled3=d2/2;doubled4=d2*2;doubled5=-d2;doubled6=d2%10;doubled7=d-d;System.out.println("d:");System.out.println(d);System.out.println("d2:");System.out.println(d2);System.out.println("d3:");System.out.println(d3);System.out.println("d4:");System.out.println(d4);System.out.println("d5:");System.out.println(d5);System.out.println("d6:");System.out.println(d6);System.out.println("d7:");System.out.println(d7);}}
   L  Class_declaration: publicclassMain{publicstaticvoidmain(String[]args){doubled=10;doubled2=d+d;doubled3=d2/2;doubled4=d2*2;doubled5=-d2;doubled6=d2%10;doubled7=d-d;System.out.println("d:");System.out.println(d);System.out.println("d2:");System.out.println(d2);System.out.println("d3:");System.out.println(d3);System.out.println("d4:");System.out.println(d4);System.out.println("d5:");System.out.println(d5);System.out.println("d6:");System.out.println(d6);System.out.println("d7:");System.out.println(d7);}}
      |- Access_modifier: public
      |  L  public
      |- class
      |- Main
      |- {
      |- Class_scope: publicstaticvoidmain(String[]args){doubled=10;doubled2=d+d;doubled3=d2/2;doubled4=d2*2;doubled5=-d2;doubled6=d2%10;doubled7=d-d;System.out.println("d:");System.out.println(d);System.out.println("d2:");System.out.println(d2);System.out.println("d3:");System.out.println(d3);System.out.println("d4:");System.out.println(d4);System.out.println("d5:");System.out.println(d5);System.out.println("d6:");System.out.println(d6);System.out.println("d7:");System.out.println(d7);}
      |  L  Function_declaration: publicstaticvoidmain(String[]args){doubled=10;doubled2=d+d;doubled3=d2/2;doubled4=d2*2;doubled5=-d2;doubled6=d2%10;doubled7=d-d;System.out.println("d:");System.out.println(d);System.out.println("d2:");System.out.println(d2);System.out.println("d3:");System.out.println(d3);System.out.println("d4:");System.out.println(d4);System.out.println("d5:");System.out.println(d5);System.out.println("d6:");System.out.println(d6);System.out.println("d7:");System.out.println(d7);}
      |     |- Access_modifier: public
      |     |  L  public
      |     |- static
      |     |- Type: void
      |     |  L  void
      |     |- main
      |     |- (
      |     |- Function_declaration_args: String[]args
      |     |  L  Function_declaration_arg: String[]args
      |     |     |- Type: String[]
      |     |     |  L  Array_type: String[]
      |     |     |     |- Class_type: String
      |     |     |     |  L  String
      |     |     |     |- [
      |     |     |     L  ]
      |     |     L  args
      |     |- )
      |     |- {
      |     |- Function_scope: doubled=10;doubled2=d+d;doubled3=d2/2;doubled4=d2*2;doubled5=-d2;doubled6=d2%10;doubled7=d-d;System.out.println("d:");System.out.println(d);System.out.println("d2:");System.out.println(d2);System.out.println("d3:");System.out.println(d3);System.out.println("d4:");System.out.println(d4);System.out.println("d5:");System.out.println(d5);System.out.println("d6:");System.out.println(d6);System.out.println("d7:");System.out.println(d7);
      |     |  |- Assignment: doubled=10
      |     |  |  |- Variable_declaration: doubled
      |     |  |  |  |- Primitive_type: double
      |     |  |  |  |  L  Numeric_type: double
      |     |  |  |  |     L  Floating_point_type: double
      |     |  |  |  |        L  double
      |     |  |  |  L  d
      |     |  |  |- =
      |     |  |  L  Expressions: 10
      |     |  |     L  Expression: 10
      |     |  |        L  10
      |     |  |- ;
      |     |  |- Assignment: doubled2=d+d
      |     |  |  |- Variable_declaration: doubled2
      |     |  |  |  |- Primitive_type: double
      |     |  |  |  |  L  Numeric_type: double
      |     |  |  |  |     L  Floating_point_type: double
      |     |  |  |  |        L  double
      |     |  |  |  L  d2
      |     |  |  |- =
      |     |  |  L  Expressions: d+d
      |     |  |     L  Expression: d+d
      |     |  |        |- Expression: d
      |     |  |        |  L  d
      |     |  |        |- Expression_concatinator: +
      |     |  |        |  L  +
      |     |  |        L  Expression: d
      |     |  |           L  d
      |     |  |- ;
      |     |  |- Assignment: doubled3=d2/2
      |     |  |  |- Variable_declaration: doubled3
      |     |  |  |  |- Primitive_type: double
      |     |  |  |  |  L  Numeric_type: double
      |     |  |  |  |     L  Floating_point_type: double
      |     |  |  |  |        L  double
      |     |  |  |  L  d3
      |     |  |  |- =
      |     |  |  L  Expressions: d2/2
      |     |  |     L  Expression: d2/2
      |     |  |        |- Expression: d2
      |     |  |        |  L  d2
      |     |  |        |- Expression_concatinator: /
      |     |  |        |  L  /
      |     |  |        L  Expression: 2
      |     |  |           L  2
      |     |  |- ;
      |     |  |- Assignment: doubled4=d2*2
      |     |  |  |- Variable_declaration: doubled4
      |     |  |  |  |- Primitive_type: double
      |     |  |  |  |  L  Numeric_type: double
      |     |  |  |  |     L  Floating_point_type: double
      |     |  |  |  |        L  double
      |     |  |  |  L  d4
      |     |  |  |- =
      |     |  |  L  Expressions: d2*2
      |     |  |     L  Expression: d2*2
      |     |  |        |- Expression: d2
      |     |  |        |  L  d2
      |     |  |        |- Expression_concatinator: *
      |     |  |        |  L  *
      |     |  |        L  Expression: 2
      |     |  |           L  2
      |     |  |- ;
      |     |  |- Assignment: doubled5=-d2
      |     |  |  |- Variable_declaration: doubled5
      |     |  |  |  |- Primitive_type: double
      |     |  |  |  |  L  Numeric_type: double
      |     |  |  |  |     L  Floating_point_type: double
      |     |  |  |  |        L  double
      |     |  |  |  L  d5
      |     |  |  |- =
      |     |  |  L  Expressions: -d2
      |     |  |     L  Expression: -d2
      |     |  |        |- Numerical_prefix: -
      |     |  |        |  L  -
      |     |  |        L  Expression: d2
      |     |  |           L  d2
      |     |  |- ;
      |     |  |- Assignment: doubled6=d2%10
      |     |  |  |- Variable_declaration: doubled6
      |     |  |  |  |- Primitive_type: double
      |     |  |  |  |  L  Numeric_type: double
      |     |  |  |  |     L  Floating_point_type: double
      |     |  |  |  |        L  double
      |     |  |  |  L  d6
      |     |  |  |- =
      |     |  |  L  Expressions: d2%10
      |     |  |     L  Expression: d2%10
      |     |  |        |- Expression: d2
      |     |  |        |  L  d2
      |     |  |        |- Expression_concatinator: %
      |     |  |        |  L  %
      |     |  |        L  Expression: 10
      |     |  |           L  10
      |     |  |- ;
      |     |  |- Assignment: doubled7=d-d
      |     |  |  |- Variable_declaration: doubled7
      |     |  |  |  |- Primitive_type: double
      |     |  |  |  |  L  Numeric_type: double
      |     |  |  |  |     L  Floating_point_type: double
      |     |  |  |  |        L  double
      |     |  |  |  L  d7
      |     |  |  |- =
      |     |  |  L  Expressions: d-d
      |     |  |     L  Expression: d-d
      |     |  |        |- Expression: d
      |     |  |        |  L  d
      |     |  |        |- Expression_concatinator: -
      |     |  |        |  L  -
      |     |  |        L  Expression: d
      |     |  |           L  d
      |     |  |- ;
      |     |  |- Expressions: System.out.println("d:")
      |     |  |  L  Expression: System.out.println("d:")
      |     |  |     L  Function_call: System.out.println("d:")
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: "d:"
      |     |  |        |  L  Function_arg: "d:"
      |     |  |        |     L  Expressions: "d:"
      |     |  |        |        L  Expression: "d:"
      |     |  |        |           L  "d:"
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println(d)
      |     |  |  L  Expression: System.out.println(d)
      |     |  |     L  Function_call: System.out.println(d)
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: d
      |     |  |        |  L  Function_arg: d
      |     |  |        |     L  Expressions: d
      |     |  |        |        L  Expression: d
      |     |  |        |           L  d
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println("d2:")
      |     |  |  L  Expression: System.out.println("d2:")
      |     |  |     L  Function_call: System.out.println("d2:")
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: "d2:"
      |     |  |        |  L  Function_arg: "d2:"
      |     |  |        |     L  Expressions: "d2:"
      |     |  |        |        L  Expression: "d2:"
      |     |  |        |           L  "d2:"
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println(d2)
      |     |  |  L  Expression: System.out.println(d2)
      |     |  |     L  Function_call: System.out.println(d2)
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: d2
      |     |  |        |  L  Function_arg: d2
      |     |  |        |     L  Expressions: d2
      |     |  |        |        L  Expression: d2
      |     |  |        |           L  d2
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println("d3:")
      |     |  |  L  Expression: System.out.println("d3:")
      |     |  |     L  Function_call: System.out.println("d3:")
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: "d3:"
      |     |  |        |  L  Function_arg: "d3:"
      |     |  |        |     L  Expressions: "d3:"
      |     |  |        |        L  Expression: "d3:"
      |     |  |        |           L  "d3:"
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println(d3)
      |     |  |  L  Expression: System.out.println(d3)
      |     |  |     L  Function_call: System.out.println(d3)
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: d3
      |     |  |        |  L  Function_arg: d3
      |     |  |        |     L  Expressions: d3
      |     |  |        |        L  Expression: d3
      |     |  |        |           L  d3
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println("d4:")
      |     |  |  L  Expression: System.out.println("d4:")
      |     |  |     L  Function_call: System.out.println("d4:")
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: "d4:"
      |     |  |        |  L  Function_arg: "d4:"
      |     |  |        |     L  Expressions: "d4:"
      |     |  |        |        L  Expression: "d4:"
      |     |  |        |           L  "d4:"
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println(d4)
      |     |  |  L  Expression: System.out.println(d4)
      |     |  |     L  Function_call: System.out.println(d4)
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: d4
      |     |  |        |  L  Function_arg: d4
      |     |  |        |     L  Expressions: d4
      |     |  |        |        L  Expression: d4
      |     |  |        |           L  d4
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println("d5:")
      |     |  |  L  Expression: System.out.println("d5:")
      |     |  |     L  Function_call: System.out.println("d5:")
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: "d5:"
      |     |  |        |  L  Function_arg: "d5:"
      |     |  |        |     L  Expressions: "d5:"
      |     |  |        |        L  Expression: "d5:"
      |     |  |        |           L  "d5:"
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println(d5)
      |     |  |  L  Expression: System.out.println(d5)
      |     |  |     L  Function_call: System.out.println(d5)
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: d5
      |     |  |        |  L  Function_arg: d5
      |     |  |        |     L  Expressions: d5
      |     |  |        |        L  Expression: d5
      |     |  |        |           L  d5
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println("d6:")
      |     |  |  L  Expression: System.out.println("d6:")
      |     |  |     L  Function_call: System.out.println("d6:")
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: "d6:"
      |     |  |        |  L  Function_arg: "d6:"
      |     |  |        |     L  Expressions: "d6:"
      |     |  |        |        L  Expression: "d6:"
      |     |  |        |           L  "d6:"
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println(d6)
      |     |  |  L  Expression: System.out.println(d6)
      |     |  |     L  Function_call: System.out.println(d6)
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: d6
      |     |  |        |  L  Function_arg: d6
      |     |  |        |     L  Expressions: d6
      |     |  |        |        L  Expression: d6
      |     |  |        |           L  d6
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println("d7:")
      |     |  |  L  Expression: System.out.println("d7:")
      |     |  |     L  Function_call: System.out.println("d7:")
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: "d7:"
      |     |  |        |  L  Function_arg: "d7:"
      |     |  |        |     L  Expressions: "d7:"
      |     |  |        |        L  Expression: "d7:"
      |     |  |        |           L  "d7:"
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println(d7)
      |     |  |  L  Expression: System.out.println(d7)
      |     |  |     L  Function_call: System.out.println(d7)
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: d7
      |     |  |        |  L  Function_arg: d7
      |     |  |        |     L  Expressions: d7
      |     |  |        |        L  Expression: d7
      |     |  |        |           L  d7
      |     |  |        L  )
      |     |  L  ;
      |     L  }
      L  }
                        """;

        assertEquals(expected, new ParseTreePrinter().print(tree));
    }
}
