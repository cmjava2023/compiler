package cmjava2023.primitive.longs.logicops;

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
Start: packagecmjava2023.primitive.longs.logicops;publicclassMain{publicstaticvoidmain(String[]args){longl=4;longeight=8;longl2=l&2;longl3=l|2;longl4=eight<<1;longl5=eight>>1;longl6=eight>>>1;longl7=l2^2;System.out.println("l:");System.out.println(l);System.out.println("eight:");System.out.println(eight);System.out.println("l2:");System.out.println(l2);System.out.println("l3:");System.out.println(l3);System.out.println("l4:");System.out.println(l4);System.out.println("l5:");System.out.println(l5);System.out.println("l6:");System.out.println(l6);System.out.println("l7:");System.out.println(l7);}}
|- Global_scope: packagecmjava2023.primitive.longs.logicops;
|  |- Package_declaration: packagecmjava2023.primitive.longs.logicops
|  |  |- package
|  |  L  Identifier: cmjava2023.primitive.longs.logicops
|  |     |- cmjava2023
|  |     |- .
|  |     |- primitive
|  |     |- .
|  |     |- longs
|  |     |- .
|  |     L  logicops
|  L  ;
L  Global_scope: publicclassMain{publicstaticvoidmain(String[]args){longl=4;longeight=8;longl2=l&2;longl3=l|2;longl4=eight<<1;longl5=eight>>1;longl6=eight>>>1;longl7=l2^2;System.out.println("l:");System.out.println(l);System.out.println("eight:");System.out.println(eight);System.out.println("l2:");System.out.println(l2);System.out.println("l3:");System.out.println(l3);System.out.println("l4:");System.out.println(l4);System.out.println("l5:");System.out.println(l5);System.out.println("l6:");System.out.println(l6);System.out.println("l7:");System.out.println(l7);}}
   L  Class_declaration: publicclassMain{publicstaticvoidmain(String[]args){longl=4;longeight=8;longl2=l&2;longl3=l|2;longl4=eight<<1;longl5=eight>>1;longl6=eight>>>1;longl7=l2^2;System.out.println("l:");System.out.println(l);System.out.println("eight:");System.out.println(eight);System.out.println("l2:");System.out.println(l2);System.out.println("l3:");System.out.println(l3);System.out.println("l4:");System.out.println(l4);System.out.println("l5:");System.out.println(l5);System.out.println("l6:");System.out.println(l6);System.out.println("l7:");System.out.println(l7);}}
      |- Access_modifier: public
      |  L  public
      |- class
      |- Main
      |- {
      |- Class_scope: publicstaticvoidmain(String[]args){longl=4;longeight=8;longl2=l&2;longl3=l|2;longl4=eight<<1;longl5=eight>>1;longl6=eight>>>1;longl7=l2^2;System.out.println("l:");System.out.println(l);System.out.println("eight:");System.out.println(eight);System.out.println("l2:");System.out.println(l2);System.out.println("l3:");System.out.println(l3);System.out.println("l4:");System.out.println(l4);System.out.println("l5:");System.out.println(l5);System.out.println("l6:");System.out.println(l6);System.out.println("l7:");System.out.println(l7);}
      |  L  Function_declaration: publicstaticvoidmain(String[]args){longl=4;longeight=8;longl2=l&2;longl3=l|2;longl4=eight<<1;longl5=eight>>1;longl6=eight>>>1;longl7=l2^2;System.out.println("l:");System.out.println(l);System.out.println("eight:");System.out.println(eight);System.out.println("l2:");System.out.println(l2);System.out.println("l3:");System.out.println(l3);System.out.println("l4:");System.out.println(l4);System.out.println("l5:");System.out.println(l5);System.out.println("l6:");System.out.println(l6);System.out.println("l7:");System.out.println(l7);}
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
      |     |- Function_scope: longl=4;longeight=8;longl2=l&2;longl3=l|2;longl4=eight<<1;longl5=eight>>1;longl6=eight>>>1;longl7=l2^2;System.out.println("l:");System.out.println(l);System.out.println("eight:");System.out.println(eight);System.out.println("l2:");System.out.println(l2);System.out.println("l3:");System.out.println(l3);System.out.println("l4:");System.out.println(l4);System.out.println("l5:");System.out.println(l5);System.out.println("l6:");System.out.println(l6);System.out.println("l7:");System.out.println(l7);
      |     |  |- Assignment: longl=4
      |     |  |  |- Variable_declaration: longl
      |     |  |  |  |- Primitive_type: long
      |     |  |  |  |  L  Numeric_type: long
      |     |  |  |  |     L  Integral_type: long
      |     |  |  |  |        L  long
      |     |  |  |  L  l
      |     |  |  |- =
      |     |  |  L  Expressions: 4
      |     |  |     L  Expression: 4
      |     |  |        L  4
      |     |  |- ;
      |     |  |- Assignment: longeight=8
      |     |  |  |- Variable_declaration: longeight
      |     |  |  |  |- Primitive_type: long
      |     |  |  |  |  L  Numeric_type: long
      |     |  |  |  |     L  Integral_type: long
      |     |  |  |  |        L  long
      |     |  |  |  L  eight
      |     |  |  |- =
      |     |  |  L  Expressions: 8
      |     |  |     L  Expression: 8
      |     |  |        L  8
      |     |  |- ;
      |     |  |- Assignment: longl2=l&2
      |     |  |  |- Variable_declaration: longl2
      |     |  |  |  |- Primitive_type: long
      |     |  |  |  |  L  Numeric_type: long
      |     |  |  |  |     L  Integral_type: long
      |     |  |  |  |        L  long
      |     |  |  |  L  l2
      |     |  |  |- =
      |     |  |  L  Expressions: l&2
      |     |  |     |- Expression: l
      |     |  |     |  L  l
      |     |  |     |- Expression_operator: &
      |     |  |     |  L  Bit_comparison_operator: &
      |     |  |     |     L  &
      |     |  |     L  Expression: 2
      |     |  |        L  2
      |     |  |- ;
      |     |  |- Assignment: longl3=l|2
      |     |  |  |- Variable_declaration: longl3
      |     |  |  |  |- Primitive_type: long
      |     |  |  |  |  L  Numeric_type: long
      |     |  |  |  |     L  Integral_type: long
      |     |  |  |  |        L  long
      |     |  |  |  L  l3
      |     |  |  |- =
      |     |  |  L  Expressions: l|2
      |     |  |     |- Expression: l
      |     |  |     |  L  l
      |     |  |     |- Expression_operator: |
      |     |  |     |  L  Bit_comparison_operator: |
      |     |  |     |     L  |
      |     |  |     L  Expression: 2
      |     |  |        L  2
      |     |  |- ;
      |     |  |- Assignment: longl4=eight<<1
      |     |  |  |- Variable_declaration: longl4
      |     |  |  |  |- Primitive_type: long
      |     |  |  |  |  L  Numeric_type: long
      |     |  |  |  |     L  Integral_type: long
      |     |  |  |  |        L  long
      |     |  |  |  L  l4
      |     |  |  |- =
      |     |  |  L  Expressions: eight<<1
      |     |  |     |- Expression: eight
      |     |  |     |  L  eight
      |     |  |     |- Expression_operator: <<
      |     |  |     |  L  Bit_comparison_operator: <<
      |     |  |     |     L  <<
      |     |  |     L  Expression: 1
      |     |  |        L  1
      |     |  |- ;
      |     |  |- Assignment: longl5=eight>>1
      |     |  |  |- Variable_declaration: longl5
      |     |  |  |  |- Primitive_type: long
      |     |  |  |  |  L  Numeric_type: long
      |     |  |  |  |     L  Integral_type: long
      |     |  |  |  |        L  long
      |     |  |  |  L  l5
      |     |  |  |- =
      |     |  |  L  Expressions: eight>>1
      |     |  |     |- Expression: eight
      |     |  |     |  L  eight
      |     |  |     |- Expression_operator: >>
      |     |  |     |  L  Bit_comparison_operator: >>
      |     |  |     |     L  >>
      |     |  |     L  Expression: 1
      |     |  |        L  1
      |     |  |- ;
      |     |  |- Assignment: longl6=eight>>>1
      |     |  |  |- Variable_declaration: longl6
      |     |  |  |  |- Primitive_type: long
      |     |  |  |  |  L  Numeric_type: long
      |     |  |  |  |     L  Integral_type: long
      |     |  |  |  |        L  long
      |     |  |  |  L  l6
      |     |  |  |- =
      |     |  |  L  Expressions: eight>>>1
      |     |  |     |- Expression: eight
      |     |  |     |  L  eight
      |     |  |     |- Expression_operator: >>>
      |     |  |     |  L  Bit_comparison_operator: >>>
      |     |  |     |     L  >>>
      |     |  |     L  Expression: 1
      |     |  |        L  1
      |     |  |- ;
      |     |  |- Assignment: longl7=l2^2
      |     |  |  |- Variable_declaration: longl7
      |     |  |  |  |- Primitive_type: long
      |     |  |  |  |  L  Numeric_type: long
      |     |  |  |  |     L  Integral_type: long
      |     |  |  |  |        L  long
      |     |  |  |  L  l7
      |     |  |  |- =
      |     |  |  L  Expressions: l2^2
      |     |  |     |- Expression: l2
      |     |  |     |  L  l2
      |     |  |     |- Expression_operator: ^
      |     |  |     |  L  Bit_comparison_operator: ^
      |     |  |     |     L  ^
      |     |  |     L  Expression: 2
      |     |  |        L  2
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
      |     |  |- Expressions: System.out.println("eight:")
      |     |  |  L  Expression: System.out.println("eight:")
      |     |  |     L  Function_call: System.out.println("eight:")
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: "eight:"
      |     |  |        |  L  Function_arg: "eight:"
      |     |  |        |     L  Expressions: "eight:"
      |     |  |        |        L  Expression: "eight:"
      |     |  |        |           L  "eight:"
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println(eight)
      |     |  |  L  Expression: System.out.println(eight)
      |     |  |     L  Function_call: System.out.println(eight)
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: eight
      |     |  |        |  L  Function_arg: eight
      |     |  |        |     L  Expressions: eight
      |     |  |        |        L  Expression: eight
      |     |  |        |           L  eight
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
