package cmjava2023.primitive.floats.mathops;

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
Start: packagecmjava2023.primitive.floats.mathops;publicclassMain{publicstaticvoidmain(String[]args){floatf=10;floatf2=f+f;floatf3=f2/2;floatf4=f2*2;floatf5=-f2;floatf6=f2%10;floatf7=f-f;System.out.println("f:");System.out.println(f);System.out.println("f2:");System.out.println(f2);System.out.println("f3:");System.out.println(f3);System.out.println("f4:");System.out.println(f4);System.out.println("f5:");System.out.println(f5);System.out.println("f6:");System.out.println(f6);System.out.println("f7:");System.out.println(f7);}}
|- Global_scope: packagecmjava2023.primitive.floats.mathops;
|  |- Package_declaration: packagecmjava2023.primitive.floats.mathops
|  |  |- package
|  |  L  Identifier: cmjava2023.primitive.floats.mathops
|  |     |- cmjava2023
|  |     |- .
|  |     |- primitive
|  |     |- .
|  |     |- floats
|  |     |- .
|  |     L  mathops
|  L  ;
L  Global_scope: publicclassMain{publicstaticvoidmain(String[]args){floatf=10;floatf2=f+f;floatf3=f2/2;floatf4=f2*2;floatf5=-f2;floatf6=f2%10;floatf7=f-f;System.out.println("f:");System.out.println(f);System.out.println("f2:");System.out.println(f2);System.out.println("f3:");System.out.println(f3);System.out.println("f4:");System.out.println(f4);System.out.println("f5:");System.out.println(f5);System.out.println("f6:");System.out.println(f6);System.out.println("f7:");System.out.println(f7);}}
   L  Class_declaration: publicclassMain{publicstaticvoidmain(String[]args){floatf=10;floatf2=f+f;floatf3=f2/2;floatf4=f2*2;floatf5=-f2;floatf6=f2%10;floatf7=f-f;System.out.println("f:");System.out.println(f);System.out.println("f2:");System.out.println(f2);System.out.println("f3:");System.out.println(f3);System.out.println("f4:");System.out.println(f4);System.out.println("f5:");System.out.println(f5);System.out.println("f6:");System.out.println(f6);System.out.println("f7:");System.out.println(f7);}}
      |- Access_modifier: public
      |  L  public
      |- class
      |- Main
      |- {
      |- Class_scope: publicstaticvoidmain(String[]args){floatf=10;floatf2=f+f;floatf3=f2/2;floatf4=f2*2;floatf5=-f2;floatf6=f2%10;floatf7=f-f;System.out.println("f:");System.out.println(f);System.out.println("f2:");System.out.println(f2);System.out.println("f3:");System.out.println(f3);System.out.println("f4:");System.out.println(f4);System.out.println("f5:");System.out.println(f5);System.out.println("f6:");System.out.println(f6);System.out.println("f7:");System.out.println(f7);}
      |  L  Function_declaration: publicstaticvoidmain(String[]args){floatf=10;floatf2=f+f;floatf3=f2/2;floatf4=f2*2;floatf5=-f2;floatf6=f2%10;floatf7=f-f;System.out.println("f:");System.out.println(f);System.out.println("f2:");System.out.println(f2);System.out.println("f3:");System.out.println(f3);System.out.println("f4:");System.out.println(f4);System.out.println("f5:");System.out.println(f5);System.out.println("f6:");System.out.println(f6);System.out.println("f7:");System.out.println(f7);}
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
      |     |- Function_scope: floatf=10;floatf2=f+f;floatf3=f2/2;floatf4=f2*2;floatf5=-f2;floatf6=f2%10;floatf7=f-f;System.out.println("f:");System.out.println(f);System.out.println("f2:");System.out.println(f2);System.out.println("f3:");System.out.println(f3);System.out.println("f4:");System.out.println(f4);System.out.println("f5:");System.out.println(f5);System.out.println("f6:");System.out.println(f6);System.out.println("f7:");System.out.println(f7);
      |     |  |- Assignment: floatf=10
      |     |  |  |- Variable_declaration: floatf
      |     |  |  |  |- Primitive_type: float
      |     |  |  |  |  L  Numeric_type: float
      |     |  |  |  |     L  Floating_point_type: float
      |     |  |  |  |        L  float
      |     |  |  |  L  f
      |     |  |  |- =
      |     |  |  L  Expressions: 10
      |     |  |     L  Expression: 10
      |     |  |        L  10
      |     |  |- ;
      |     |  |- Assignment: floatf2=f+f
      |     |  |  |- Variable_declaration: floatf2
      |     |  |  |  |- Primitive_type: float
      |     |  |  |  |  L  Numeric_type: float
      |     |  |  |  |     L  Floating_point_type: float
      |     |  |  |  |        L  float
      |     |  |  |  L  f2
      |     |  |  |- =
      |     |  |  L  Expressions: f+f
      |     |  |     L  Expression: f+f
      |     |  |        |- Expression: f
      |     |  |        |  L  f
      |     |  |        |- Expression_concatinator: +
      |     |  |        |  L  +
      |     |  |        L  Expression: f
      |     |  |           L  f
      |     |  |- ;
      |     |  |- Assignment: floatf3=f2/2
      |     |  |  |- Variable_declaration: floatf3
      |     |  |  |  |- Primitive_type: float
      |     |  |  |  |  L  Numeric_type: float
      |     |  |  |  |     L  Floating_point_type: float
      |     |  |  |  |        L  float
      |     |  |  |  L  f3
      |     |  |  |- =
      |     |  |  L  Expressions: f2/2
      |     |  |     L  Expression: f2/2
      |     |  |        |- Expression: f2
      |     |  |        |  L  f2
      |     |  |        |- Expression_concatinator: /
      |     |  |        |  L  /
      |     |  |        L  Expression: 2
      |     |  |           L  2
      |     |  |- ;
      |     |  |- Assignment: floatf4=f2*2
      |     |  |  |- Variable_declaration: floatf4
      |     |  |  |  |- Primitive_type: float
      |     |  |  |  |  L  Numeric_type: float
      |     |  |  |  |     L  Floating_point_type: float
      |     |  |  |  |        L  float
      |     |  |  |  L  f4
      |     |  |  |- =
      |     |  |  L  Expressions: f2*2
      |     |  |     L  Expression: f2*2
      |     |  |        |- Expression: f2
      |     |  |        |  L  f2
      |     |  |        |- Expression_concatinator: *
      |     |  |        |  L  *
      |     |  |        L  Expression: 2
      |     |  |           L  2
      |     |  |- ;
      |     |  |- Assignment: floatf5=-f2
      |     |  |  |- Variable_declaration: floatf5
      |     |  |  |  |- Primitive_type: float
      |     |  |  |  |  L  Numeric_type: float
      |     |  |  |  |     L  Floating_point_type: float
      |     |  |  |  |        L  float
      |     |  |  |  L  f5
      |     |  |  |- =
      |     |  |  L  Expressions: -f2
      |     |  |     L  Expression: -f2
      |     |  |        |- Numerical_prefix: -
      |     |  |        |  L  -
      |     |  |        L  Expressions: f2
      |     |  |           L  Expression: f2
      |     |  |              L  f2
      |     |  |- ;
      |     |  |- Assignment: floatf6=f2%10
      |     |  |  |- Variable_declaration: floatf6
      |     |  |  |  |- Primitive_type: float
      |     |  |  |  |  L  Numeric_type: float
      |     |  |  |  |     L  Floating_point_type: float
      |     |  |  |  |        L  float
      |     |  |  |  L  f6
      |     |  |  |- =
      |     |  |  L  Expressions: f2%10
      |     |  |     L  Expression: f2%10
      |     |  |        |- Expression: f2
      |     |  |        |  L  f2
      |     |  |        |- Expression_concatinator: %
      |     |  |        |  L  %
      |     |  |        L  Expression: 10
      |     |  |           L  10
      |     |  |- ;
      |     |  |- Assignment: floatf7=f-f
      |     |  |  |- Variable_declaration: floatf7
      |     |  |  |  |- Primitive_type: float
      |     |  |  |  |  L  Numeric_type: float
      |     |  |  |  |     L  Floating_point_type: float
      |     |  |  |  |        L  float
      |     |  |  |  L  f7
      |     |  |  |- =
      |     |  |  L  Expressions: f-f
      |     |  |     L  Expression: f-f
      |     |  |        |- Expression: f
      |     |  |        |  L  f
      |     |  |        |- Expression_concatinator: -
      |     |  |        |  L  -
      |     |  |        L  Expression: f
      |     |  |           L  f
      |     |  |- ;
      |     |  |- Expressions: System.out.println("f:")
      |     |  |  L  Expression: System.out.println("f:")
      |     |  |     L  Function_call: System.out.println("f:")
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: "f:"
      |     |  |        |  L  Function_arg: "f:"
      |     |  |        |     L  Expressions: "f:"
      |     |  |        |        L  Expression: "f:"
      |     |  |        |           L  "f:"
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println(f)
      |     |  |  L  Expression: System.out.println(f)
      |     |  |     L  Function_call: System.out.println(f)
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: f
      |     |  |        |  L  Function_arg: f
      |     |  |        |     L  Expressions: f
      |     |  |        |        L  Expression: f
      |     |  |        |           L  f
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println("f2:")
      |     |  |  L  Expression: System.out.println("f2:")
      |     |  |     L  Function_call: System.out.println("f2:")
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: "f2:"
      |     |  |        |  L  Function_arg: "f2:"
      |     |  |        |     L  Expressions: "f2:"
      |     |  |        |        L  Expression: "f2:"
      |     |  |        |           L  "f2:"
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println(f2)
      |     |  |  L  Expression: System.out.println(f2)
      |     |  |     L  Function_call: System.out.println(f2)
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: f2
      |     |  |        |  L  Function_arg: f2
      |     |  |        |     L  Expressions: f2
      |     |  |        |        L  Expression: f2
      |     |  |        |           L  f2
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println("f3:")
      |     |  |  L  Expression: System.out.println("f3:")
      |     |  |     L  Function_call: System.out.println("f3:")
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: "f3:"
      |     |  |        |  L  Function_arg: "f3:"
      |     |  |        |     L  Expressions: "f3:"
      |     |  |        |        L  Expression: "f3:"
      |     |  |        |           L  "f3:"
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println(f3)
      |     |  |  L  Expression: System.out.println(f3)
      |     |  |     L  Function_call: System.out.println(f3)
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: f3
      |     |  |        |  L  Function_arg: f3
      |     |  |        |     L  Expressions: f3
      |     |  |        |        L  Expression: f3
      |     |  |        |           L  f3
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println("f4:")
      |     |  |  L  Expression: System.out.println("f4:")
      |     |  |     L  Function_call: System.out.println("f4:")
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: "f4:"
      |     |  |        |  L  Function_arg: "f4:"
      |     |  |        |     L  Expressions: "f4:"
      |     |  |        |        L  Expression: "f4:"
      |     |  |        |           L  "f4:"
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println(f4)
      |     |  |  L  Expression: System.out.println(f4)
      |     |  |     L  Function_call: System.out.println(f4)
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: f4
      |     |  |        |  L  Function_arg: f4
      |     |  |        |     L  Expressions: f4
      |     |  |        |        L  Expression: f4
      |     |  |        |           L  f4
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println("f5:")
      |     |  |  L  Expression: System.out.println("f5:")
      |     |  |     L  Function_call: System.out.println("f5:")
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: "f5:"
      |     |  |        |  L  Function_arg: "f5:"
      |     |  |        |     L  Expressions: "f5:"
      |     |  |        |        L  Expression: "f5:"
      |     |  |        |           L  "f5:"
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println(f5)
      |     |  |  L  Expression: System.out.println(f5)
      |     |  |     L  Function_call: System.out.println(f5)
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: f5
      |     |  |        |  L  Function_arg: f5
      |     |  |        |     L  Expressions: f5
      |     |  |        |        L  Expression: f5
      |     |  |        |           L  f5
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println("f6:")
      |     |  |  L  Expression: System.out.println("f6:")
      |     |  |     L  Function_call: System.out.println("f6:")
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: "f6:"
      |     |  |        |  L  Function_arg: "f6:"
      |     |  |        |     L  Expressions: "f6:"
      |     |  |        |        L  Expression: "f6:"
      |     |  |        |           L  "f6:"
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println(f6)
      |     |  |  L  Expression: System.out.println(f6)
      |     |  |     L  Function_call: System.out.println(f6)
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: f6
      |     |  |        |  L  Function_arg: f6
      |     |  |        |     L  Expressions: f6
      |     |  |        |        L  Expression: f6
      |     |  |        |           L  f6
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println("f7:")
      |     |  |  L  Expression: System.out.println("f7:")
      |     |  |     L  Function_call: System.out.println("f7:")
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: "f7:"
      |     |  |        |  L  Function_arg: "f7:"
      |     |  |        |     L  Expressions: "f7:"
      |     |  |        |        L  Expression: "f7:"
      |     |  |        |           L  "f7:"
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println(f7)
      |     |  |  L  Expression: System.out.println(f7)
      |     |  |     L  Function_call: System.out.println(f7)
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: f7
      |     |  |        |  L  Function_arg: f7
      |     |  |        |     L  Expressions: f7
      |     |  |        |        L  Expression: f7
      |     |  |        |           L  f7
      |     |  |        L  )
      |     |  L  ;
      |     L  }
      L  }
                        """;

        assertEquals(expected, new ParseTreePrinter().print(tree));
    }
}
