package cmjava2023.primitive.longs.mathops;

import cmjava2023.util.TestPathsHelper;
import cmjava2023.util.TreePrinter;
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
Start: packagecmjava2023.primitive.longs.mathops;publicclassMain{publicstaticvoidmain(String[]args){longl=10;longl2=l+l;longl3=l2/2;longl4=l2*2;longl5=-l2;longl6=l2%10;longl7=l-l;System.out.println("l:");System.out.println(l);System.out.println("l2:");System.out.println(l2);System.out.println("l3:");System.out.println(l3);System.out.println("l4:");System.out.println(l4);System.out.println("l5:");System.out.println(l5);System.out.println("l6:");System.out.println(l6);System.out.println("l7:");System.out.println(l7);}}
|- Global_scope: packagecmjava2023.primitive.longs.mathops;
|  |- Package_declaration: packagecmjava2023.primitive.longs.mathops
|  |  |- package
|  |  L  Identifier: cmjava2023.primitive.longs.mathops
|  |     |- cmjava2023
|  |     |- .
|  |     |- primitive
|  |     |- .
|  |     |- longs
|  |     |- .
|  |     L  mathops
|  L  ;
L  Global_scope: publicclassMain{publicstaticvoidmain(String[]args){longl=10;longl2=l+l;longl3=l2/2;longl4=l2*2;longl5=-l2;longl6=l2%10;longl7=l-l;System.out.println("l:");System.out.println(l);System.out.println("l2:");System.out.println(l2);System.out.println("l3:");System.out.println(l3);System.out.println("l4:");System.out.println(l4);System.out.println("l5:");System.out.println(l5);System.out.println("l6:");System.out.println(l6);System.out.println("l7:");System.out.println(l7);}}
   L  Class_declaration: publicclassMain{publicstaticvoidmain(String[]args){longl=10;longl2=l+l;longl3=l2/2;longl4=l2*2;longl5=-l2;longl6=l2%10;longl7=l-l;System.out.println("l:");System.out.println(l);System.out.println("l2:");System.out.println(l2);System.out.println("l3:");System.out.println(l3);System.out.println("l4:");System.out.println(l4);System.out.println("l5:");System.out.println(l5);System.out.println("l6:");System.out.println(l6);System.out.println("l7:");System.out.println(l7);}}
      |- Access_modifier: public
      |  L  public
      |- class
      |- Main
      |- {
      |- Class_scope: publicstaticvoidmain(String[]args){longl=10;longl2=l+l;longl3=l2/2;longl4=l2*2;longl5=-l2;longl6=l2%10;longl7=l-l;System.out.println("l:");System.out.println(l);System.out.println("l2:");System.out.println(l2);System.out.println("l3:");System.out.println(l3);System.out.println("l4:");System.out.println(l4);System.out.println("l5:");System.out.println(l5);System.out.println("l6:");System.out.println(l6);System.out.println("l7:");System.out.println(l7);}
      |  L  Function_declaration: publicstaticvoidmain(String[]args){longl=10;longl2=l+l;longl3=l2/2;longl4=l2*2;longl5=-l2;longl6=l2%10;longl7=l-l;System.out.println("l:");System.out.println(l);System.out.println("l2:");System.out.println(l2);System.out.println("l3:");System.out.println(l3);System.out.println("l4:");System.out.println(l4);System.out.println("l5:");System.out.println(l5);System.out.println("l6:");System.out.println(l6);System.out.println("l7:");System.out.println(l7);}
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
      |     |- Function_scope: longl=10;longl2=l+l;longl3=l2/2;longl4=l2*2;longl5=-l2;longl6=l2%10;longl7=l-l;System.out.println("l:");System.out.println(l);System.out.println("l2:");System.out.println(l2);System.out.println("l3:");System.out.println(l3);System.out.println("l4:");System.out.println(l4);System.out.println("l5:");System.out.println(l5);System.out.println("l6:");System.out.println(l6);System.out.println("l7:");System.out.println(l7);
      |     |  |- Assignment: longl=10
      |     |  |  |- Variable_declaration: longl
      |     |  |  |  |- Primitive_type: long
      |     |  |  |  |  L  Numeric_type: long
      |     |  |  |  |     L  Integral_type: long
      |     |  |  |  |        L  long
      |     |  |  |  L  l
      |     |  |  |- =
      |     |  |  L  Expressions: 10
      |     |  |     L  Expression: 10
      |     |  |        L  10
      |     |  |- ;
      |     |  |- Assignment: longl2=l+l
      |     |  |  |- Variable_declaration: longl2
      |     |  |  |  |- Primitive_type: long
      |     |  |  |  |  L  Numeric_type: long
      |     |  |  |  |     L  Integral_type: long
      |     |  |  |  |        L  long
      |     |  |  |  L  l2
      |     |  |  |- =
      |     |  |  L  Expressions: l+l
      |     |  |     L  Expression: l+l
      |     |  |        |- Expression: l
      |     |  |        |  L  l
      |     |  |        |- Expression_concatinator: +
      |     |  |        |  L  +
      |     |  |        L  Expression: l
      |     |  |           L  l
      |     |  |- ;
      |     |  |- Assignment: longl3=l2/2
      |     |  |  |- Variable_declaration: longl3
      |     |  |  |  |- Primitive_type: long
      |     |  |  |  |  L  Numeric_type: long
      |     |  |  |  |     L  Integral_type: long
      |     |  |  |  |        L  long
      |     |  |  |  L  l3
      |     |  |  |- =
      |     |  |  L  Expressions: l2/2
      |     |  |     L  Expression: l2/2
      |     |  |        |- Expression: l2
      |     |  |        |  L  l2
      |     |  |        |- Expression_concatinator: /
      |     |  |        |  L  /
      |     |  |        L  Expression: 2
      |     |  |           L  2
      |     |  |- ;
      |     |  |- Assignment: longl4=l2*2
      |     |  |  |- Variable_declaration: longl4
      |     |  |  |  |- Primitive_type: long
      |     |  |  |  |  L  Numeric_type: long
      |     |  |  |  |     L  Integral_type: long
      |     |  |  |  |        L  long
      |     |  |  |  L  l4
      |     |  |  |- =
      |     |  |  L  Expressions: l2*2
      |     |  |     L  Expression: l2*2
      |     |  |        |- Expression: l2
      |     |  |        |  L  l2
      |     |  |        |- Expression_concatinator: *
      |     |  |        |  L  *
      |     |  |        L  Expression: 2
      |     |  |           L  2
      |     |  |- ;
      |     |  |- Assignment: longl5=-l2
      |     |  |  |- Variable_declaration: longl5
      |     |  |  |  |- Primitive_type: long
      |     |  |  |  |  L  Numeric_type: long
      |     |  |  |  |     L  Integral_type: long
      |     |  |  |  |        L  long
      |     |  |  |  L  l5
      |     |  |  |- =
      |     |  |  L  Expressions: -l2
      |     |  |     L  Expression: -l2
      |     |  |        |- Numerical_prefix: -
      |     |  |        |  L  -
      |     |  |        L  Expression: l2
      |     |  |           L  l2
      |     |  |- ;
      |     |  |- Assignment: longl6=l2%10
      |     |  |  |- Variable_declaration: longl6
      |     |  |  |  |- Primitive_type: long
      |     |  |  |  |  L  Numeric_type: long
      |     |  |  |  |     L  Integral_type: long
      |     |  |  |  |        L  long
      |     |  |  |  L  l6
      |     |  |  |- =
      |     |  |  L  Expressions: l2%10
      |     |  |     L  Expression: l2%10
      |     |  |        |- Expression: l2
      |     |  |        |  L  l2
      |     |  |        |- Expression_concatinator: %
      |     |  |        |  L  %
      |     |  |        L  Expression: 10
      |     |  |           L  10
      |     |  |- ;
      |     |  |- Assignment: longl7=l-l
      |     |  |  |- Variable_declaration: longl7
      |     |  |  |  |- Primitive_type: long
      |     |  |  |  |  L  Numeric_type: long
      |     |  |  |  |     L  Integral_type: long
      |     |  |  |  |        L  long
      |     |  |  |  L  l7
      |     |  |  |- =
      |     |  |  L  Expressions: l-l
      |     |  |     L  Expression: l-l
      |     |  |        |- Expression: l
      |     |  |        |  L  l
      |     |  |        |- Expression_concatinator: -
      |     |  |        |  L  -
      |     |  |        L  Expression: l
      |     |  |           L  l
      |     |  |- ;
      |     |  |- Expressions: System.out.println("l:")
      |     |  |  L  Expression: System.out.println("l:")
      |     |  |     L  Function_call: System.out.println("l:")
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: "l:"
      |     |  |        |  L  Function_arg: "l:"
      |     |  |        |     L  Expressions: "l:"
      |     |  |        |        L  Expression: "l:"
      |     |  |        |           L  "l:"
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println(l)
      |     |  |  L  Expression: System.out.println(l)
      |     |  |     L  Function_call: System.out.println(l)
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: l
      |     |  |        |  L  Function_arg: l
      |     |  |        |     L  Expressions: l
      |     |  |        |        L  Expression: l
      |     |  |        |           L  l
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println("l2:")
      |     |  |  L  Expression: System.out.println("l2:")
      |     |  |     L  Function_call: System.out.println("l2:")
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: "l2:"
      |     |  |        |  L  Function_arg: "l2:"
      |     |  |        |     L  Expressions: "l2:"
      |     |  |        |        L  Expression: "l2:"
      |     |  |        |           L  "l2:"
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println(l2)
      |     |  |  L  Expression: System.out.println(l2)
      |     |  |     L  Function_call: System.out.println(l2)
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: l2
      |     |  |        |  L  Function_arg: l2
      |     |  |        |     L  Expressions: l2
      |     |  |        |        L  Expression: l2
      |     |  |        |           L  l2
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println("l3:")
      |     |  |  L  Expression: System.out.println("l3:")
      |     |  |     L  Function_call: System.out.println("l3:")
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: "l3:"
      |     |  |        |  L  Function_arg: "l3:"
      |     |  |        |     L  Expressions: "l3:"
      |     |  |        |        L  Expression: "l3:"
      |     |  |        |           L  "l3:"
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println(l3)
      |     |  |  L  Expression: System.out.println(l3)
      |     |  |     L  Function_call: System.out.println(l3)
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: l3
      |     |  |        |  L  Function_arg: l3
      |     |  |        |     L  Expressions: l3
      |     |  |        |        L  Expression: l3
      |     |  |        |           L  l3
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println("l4:")
      |     |  |  L  Expression: System.out.println("l4:")
      |     |  |     L  Function_call: System.out.println("l4:")
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: "l4:"
      |     |  |        |  L  Function_arg: "l4:"
      |     |  |        |     L  Expressions: "l4:"
      |     |  |        |        L  Expression: "l4:"
      |     |  |        |           L  "l4:"
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println(l4)
      |     |  |  L  Expression: System.out.println(l4)
      |     |  |     L  Function_call: System.out.println(l4)
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: l4
      |     |  |        |  L  Function_arg: l4
      |     |  |        |     L  Expressions: l4
      |     |  |        |        L  Expression: l4
      |     |  |        |           L  l4
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println("l5:")
      |     |  |  L  Expression: System.out.println("l5:")
      |     |  |     L  Function_call: System.out.println("l5:")
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: "l5:"
      |     |  |        |  L  Function_arg: "l5:"
      |     |  |        |     L  Expressions: "l5:"
      |     |  |        |        L  Expression: "l5:"
      |     |  |        |           L  "l5:"
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println(l5)
      |     |  |  L  Expression: System.out.println(l5)
      |     |  |     L  Function_call: System.out.println(l5)
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: l5
      |     |  |        |  L  Function_arg: l5
      |     |  |        |     L  Expressions: l5
      |     |  |        |        L  Expression: l5
      |     |  |        |           L  l5
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println("l6:")
      |     |  |  L  Expression: System.out.println("l6:")
      |     |  |     L  Function_call: System.out.println("l6:")
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: "l6:"
      |     |  |        |  L  Function_arg: "l6:"
      |     |  |        |     L  Expressions: "l6:"
      |     |  |        |        L  Expression: "l6:"
      |     |  |        |           L  "l6:"
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println(l6)
      |     |  |  L  Expression: System.out.println(l6)
      |     |  |     L  Function_call: System.out.println(l6)
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: l6
      |     |  |        |  L  Function_arg: l6
      |     |  |        |     L  Expressions: l6
      |     |  |        |        L  Expression: l6
      |     |  |        |           L  l6
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println("l7:")
      |     |  |  L  Expression: System.out.println("l7:")
      |     |  |     L  Function_call: System.out.println("l7:")
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: "l7:"
      |     |  |        |  L  Function_arg: "l7:"
      |     |  |        |     L  Expressions: "l7:"
      |     |  |        |        L  Expression: "l7:"
      |     |  |        |           L  "l7:"
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println(l7)
      |     |  |  L  Expression: System.out.println(l7)
      |     |  |     L  Function_call: System.out.println(l7)
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: l7
      |     |  |        |  L  Function_arg: l7
      |     |  |        |     L  Expressions: l7
      |     |  |        |        L  Expression: l7
      |     |  |        |           L  l7
      |     |  |        L  )
      |     |  L  ;
      |     L  }
      L  }
                        """;

        assertEquals(expected, new TreePrinter().printParseTree(tree));
    }
}
