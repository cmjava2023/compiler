package cmjava2023.primitive.ints.logicops;

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
Start: packagecmjava2023.primitive.ints.logicops;publicclassMain{publicstaticvoidmain(String[]args){inti=4;inteight=8;intsipush=257;inti2=i&2;inti3=i|2;inti4=eight<<1;inti5=eight>>1;inti6=eight>>>1;inti7=i2^2;System.out.println("i:");System.out.println(i);System.out.println("eight:");System.out.println(eight);System.out.println("i2:");System.out.println(i2);System.out.println("i3:");System.out.println(i3);System.out.println("i4:");System.out.println(i4);System.out.println("i5:");System.out.println(i5);System.out.println("i6:");System.out.println(i6);System.out.println("i7:");System.out.println(i7);}}
|- Global_scope: packagecmjava2023.primitive.ints.logicops;
|  |- Package_declaration: packagecmjava2023.primitive.ints.logicops
|  |  |- package
|  |  L  Identifier: cmjava2023.primitive.ints.logicops
|  |     |- cmjava2023
|  |     |- .
|  |     |- primitive
|  |     |- .
|  |     |- ints
|  |     |- .
|  |     L  logicops
|  L  ;
L  Global_scope: publicclassMain{publicstaticvoidmain(String[]args){inti=4;inteight=8;intsipush=257;inti2=i&2;inti3=i|2;inti4=eight<<1;inti5=eight>>1;inti6=eight>>>1;inti7=i2^2;System.out.println("i:");System.out.println(i);System.out.println("eight:");System.out.println(eight);System.out.println("i2:");System.out.println(i2);System.out.println("i3:");System.out.println(i3);System.out.println("i4:");System.out.println(i4);System.out.println("i5:");System.out.println(i5);System.out.println("i6:");System.out.println(i6);System.out.println("i7:");System.out.println(i7);}}
   L  Class_declaration: publicclassMain{publicstaticvoidmain(String[]args){inti=4;inteight=8;intsipush=257;inti2=i&2;inti3=i|2;inti4=eight<<1;inti5=eight>>1;inti6=eight>>>1;inti7=i2^2;System.out.println("i:");System.out.println(i);System.out.println("eight:");System.out.println(eight);System.out.println("i2:");System.out.println(i2);System.out.println("i3:");System.out.println(i3);System.out.println("i4:");System.out.println(i4);System.out.println("i5:");System.out.println(i5);System.out.println("i6:");System.out.println(i6);System.out.println("i7:");System.out.println(i7);}}
      |- Access_modifier: public
      |  L  public
      |- class
      |- Main
      |- {
      |- Class_scope: publicstaticvoidmain(String[]args){inti=4;inteight=8;intsipush=257;inti2=i&2;inti3=i|2;inti4=eight<<1;inti5=eight>>1;inti6=eight>>>1;inti7=i2^2;System.out.println("i:");System.out.println(i);System.out.println("eight:");System.out.println(eight);System.out.println("i2:");System.out.println(i2);System.out.println("i3:");System.out.println(i3);System.out.println("i4:");System.out.println(i4);System.out.println("i5:");System.out.println(i5);System.out.println("i6:");System.out.println(i6);System.out.println("i7:");System.out.println(i7);}
      |  L  Function_declaration: publicstaticvoidmain(String[]args){inti=4;inteight=8;intsipush=257;inti2=i&2;inti3=i|2;inti4=eight<<1;inti5=eight>>1;inti6=eight>>>1;inti7=i2^2;System.out.println("i:");System.out.println(i);System.out.println("eight:");System.out.println(eight);System.out.println("i2:");System.out.println(i2);System.out.println("i3:");System.out.println(i3);System.out.println("i4:");System.out.println(i4);System.out.println("i5:");System.out.println(i5);System.out.println("i6:");System.out.println(i6);System.out.println("i7:");System.out.println(i7);}
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
      |     |- Function_scope: inti=4;inteight=8;intsipush=257;inti2=i&2;inti3=i|2;inti4=eight<<1;inti5=eight>>1;inti6=eight>>>1;inti7=i2^2;System.out.println("i:");System.out.println(i);System.out.println("eight:");System.out.println(eight);System.out.println("i2:");System.out.println(i2);System.out.println("i3:");System.out.println(i3);System.out.println("i4:");System.out.println(i4);System.out.println("i5:");System.out.println(i5);System.out.println("i6:");System.out.println(i6);System.out.println("i7:");System.out.println(i7);
      |     |  |- Assignment: inti=4
      |     |  |  |- Variable_declaration: inti
      |     |  |  |  |- Primitive_type: int
      |     |  |  |  |  L  Numeric_type: int
      |     |  |  |  |     L  Integral_type: int
      |     |  |  |  |        L  int
      |     |  |  |  L  i
      |     |  |  |- =
      |     |  |  L  Expressions: 4
      |     |  |     L  Expression: 4
      |     |  |        L  4
      |     |  |- ;
      |     |  |- Assignment: inteight=8
      |     |  |  |- Variable_declaration: inteight
      |     |  |  |  |- Primitive_type: int
      |     |  |  |  |  L  Numeric_type: int
      |     |  |  |  |     L  Integral_type: int
      |     |  |  |  |        L  int
      |     |  |  |  L  eight
      |     |  |  |- =
      |     |  |  L  Expressions: 8
      |     |  |     L  Expression: 8
      |     |  |        L  8
      |     |  |- ;
      |     |  |- Assignment: intsipush=257
      |     |  |  |- Variable_declaration: intsipush
      |     |  |  |  |- Primitive_type: int
      |     |  |  |  |  L  Numeric_type: int
      |     |  |  |  |     L  Integral_type: int
      |     |  |  |  |        L  int
      |     |  |  |  L  sipush
      |     |  |  |- =
      |     |  |  L  Expressions: 257
      |     |  |     L  Expression: 257
      |     |  |        L  257
      |     |  |- ;
      |     |  |- Assignment: inti2=i&2
      |     |  |  |- Variable_declaration: inti2
      |     |  |  |  |- Primitive_type: int
      |     |  |  |  |  L  Numeric_type: int
      |     |  |  |  |     L  Integral_type: int
      |     |  |  |  |        L  int
      |     |  |  |  L  i2
      |     |  |  |- =
      |     |  |  L  Expressions: i&2
      |     |  |     |- Expression: i
      |     |  |     |  L  i
      |     |  |     |- Expression_operator: &
      |     |  |     |  L  Bit_comparison_operator: &
      |     |  |     |     L  &
      |     |  |     L  Expression: 2
      |     |  |        L  2
      |     |  |- ;
      |     |  |- Assignment: inti3=i|2
      |     |  |  |- Variable_declaration: inti3
      |     |  |  |  |- Primitive_type: int
      |     |  |  |  |  L  Numeric_type: int
      |     |  |  |  |     L  Integral_type: int
      |     |  |  |  |        L  int
      |     |  |  |  L  i3
      |     |  |  |- =
      |     |  |  L  Expressions: i|2
      |     |  |     |- Expression: i
      |     |  |     |  L  i
      |     |  |     |- Expression_operator: |
      |     |  |     |  L  Bit_comparison_operator: |
      |     |  |     |     L  |
      |     |  |     L  Expression: 2
      |     |  |        L  2
      |     |  |- ;
      |     |  |- Assignment: inti4=eight<<1
      |     |  |  |- Variable_declaration: inti4
      |     |  |  |  |- Primitive_type: int
      |     |  |  |  |  L  Numeric_type: int
      |     |  |  |  |     L  Integral_type: int
      |     |  |  |  |        L  int
      |     |  |  |  L  i4
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
      |     |  |- Assignment: inti5=eight>>1
      |     |  |  |- Variable_declaration: inti5
      |     |  |  |  |- Primitive_type: int
      |     |  |  |  |  L  Numeric_type: int
      |     |  |  |  |     L  Integral_type: int
      |     |  |  |  |        L  int
      |     |  |  |  L  i5
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
      |     |  |- Assignment: inti6=eight>>>1
      |     |  |  |- Variable_declaration: inti6
      |     |  |  |  |- Primitive_type: int
      |     |  |  |  |  L  Numeric_type: int
      |     |  |  |  |     L  Integral_type: int
      |     |  |  |  |        L  int
      |     |  |  |  L  i6
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
      |     |  |- Assignment: inti7=i2^2
      |     |  |  |- Variable_declaration: inti7
      |     |  |  |  |- Primitive_type: int
      |     |  |  |  |  L  Numeric_type: int
      |     |  |  |  |     L  Integral_type: int
      |     |  |  |  |        L  int
      |     |  |  |  L  i7
      |     |  |  |- =
      |     |  |  L  Expressions: i2^2
      |     |  |     |- Expression: i2
      |     |  |     |  L  i2
      |     |  |     |- Expression_operator: ^
      |     |  |     |  L  Bit_comparison_operator: ^
      |     |  |     |     L  ^
      |     |  |     L  Expression: 2
      |     |  |        L  2
      |     |  |- ;
      |     |  |- Expressions: System.out.println("i:")
      |     |  |  L  Expression: System.out.println("i:")
      |     |  |     L  Function_call: System.out.println("i:")
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: "i:"
      |     |  |        |  L  Function_arg: "i:"
      |     |  |        |     L  Expressions: "i:"
      |     |  |        |        L  Expression: "i:"
      |     |  |        |           L  "i:"
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println(i)
      |     |  |  L  Expression: System.out.println(i)
      |     |  |     L  Function_call: System.out.println(i)
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: i
      |     |  |        |  L  Function_arg: i
      |     |  |        |     L  Expressions: i
      |     |  |        |        L  Expression: i
      |     |  |        |           L  i
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
      |     |  |- Expressions: System.out.println("i2:")
      |     |  |  L  Expression: System.out.println("i2:")
      |     |  |     L  Function_call: System.out.println("i2:")
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: "i2:"
      |     |  |        |  L  Function_arg: "i2:"
      |     |  |        |     L  Expressions: "i2:"
      |     |  |        |        L  Expression: "i2:"
      |     |  |        |           L  "i2:"
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println(i2)
      |     |  |  L  Expression: System.out.println(i2)
      |     |  |     L  Function_call: System.out.println(i2)
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: i2
      |     |  |        |  L  Function_arg: i2
      |     |  |        |     L  Expressions: i2
      |     |  |        |        L  Expression: i2
      |     |  |        |           L  i2
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println("i3:")
      |     |  |  L  Expression: System.out.println("i3:")
      |     |  |     L  Function_call: System.out.println("i3:")
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: "i3:"
      |     |  |        |  L  Function_arg: "i3:"
      |     |  |        |     L  Expressions: "i3:"
      |     |  |        |        L  Expression: "i3:"
      |     |  |        |           L  "i3:"
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println(i3)
      |     |  |  L  Expression: System.out.println(i3)
      |     |  |     L  Function_call: System.out.println(i3)
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: i3
      |     |  |        |  L  Function_arg: i3
      |     |  |        |     L  Expressions: i3
      |     |  |        |        L  Expression: i3
      |     |  |        |           L  i3
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println("i4:")
      |     |  |  L  Expression: System.out.println("i4:")
      |     |  |     L  Function_call: System.out.println("i4:")
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: "i4:"
      |     |  |        |  L  Function_arg: "i4:"
      |     |  |        |     L  Expressions: "i4:"
      |     |  |        |        L  Expression: "i4:"
      |     |  |        |           L  "i4:"
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println(i4)
      |     |  |  L  Expression: System.out.println(i4)
      |     |  |     L  Function_call: System.out.println(i4)
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: i4
      |     |  |        |  L  Function_arg: i4
      |     |  |        |     L  Expressions: i4
      |     |  |        |        L  Expression: i4
      |     |  |        |           L  i4
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println("i5:")
      |     |  |  L  Expression: System.out.println("i5:")
      |     |  |     L  Function_call: System.out.println("i5:")
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: "i5:"
      |     |  |        |  L  Function_arg: "i5:"
      |     |  |        |     L  Expressions: "i5:"
      |     |  |        |        L  Expression: "i5:"
      |     |  |        |           L  "i5:"
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println(i5)
      |     |  |  L  Expression: System.out.println(i5)
      |     |  |     L  Function_call: System.out.println(i5)
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: i5
      |     |  |        |  L  Function_arg: i5
      |     |  |        |     L  Expressions: i5
      |     |  |        |        L  Expression: i5
      |     |  |        |           L  i5
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println("i6:")
      |     |  |  L  Expression: System.out.println("i6:")
      |     |  |     L  Function_call: System.out.println("i6:")
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: "i6:"
      |     |  |        |  L  Function_arg: "i6:"
      |     |  |        |     L  Expressions: "i6:"
      |     |  |        |        L  Expression: "i6:"
      |     |  |        |           L  "i6:"
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println(i6)
      |     |  |  L  Expression: System.out.println(i6)
      |     |  |     L  Function_call: System.out.println(i6)
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: i6
      |     |  |        |  L  Function_arg: i6
      |     |  |        |     L  Expressions: i6
      |     |  |        |        L  Expression: i6
      |     |  |        |           L  i6
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println("i7:")
      |     |  |  L  Expression: System.out.println("i7:")
      |     |  |     L  Function_call: System.out.println("i7:")
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: "i7:"
      |     |  |        |  L  Function_arg: "i7:"
      |     |  |        |     L  Expressions: "i7:"
      |     |  |        |        L  Expression: "i7:"
      |     |  |        |           L  "i7:"
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println(i7)
      |     |  |  L  Expression: System.out.println(i7)
      |     |  |     L  Function_call: System.out.println(i7)
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: i7
      |     |  |        |  L  Function_arg: i7
      |     |  |        |     L  Expressions: i7
      |     |  |        |        L  Expression: i7
      |     |  |        |           L  i7
      |     |  |        L  )
      |     |  L  ;
      |     L  }
      L  }
                        """;

        assertEquals(expected, new TreePrinter().printParseTree(tree));
    }
}
