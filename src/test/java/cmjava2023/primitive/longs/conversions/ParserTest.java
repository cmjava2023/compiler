package cmjava2023.primitive.longs.conversions;

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
Start: packagecmjava2023.primitive.longs.conversions;publicclassMain{publicstaticvoidmain(String[]args){longl=10;doubled=(double)l;floatf=(float)l;inti=(int)l;longforce_store=l;longforce_load=force_store;longforce_const=1;System.out.println("l:");System.out.println(l);System.out.println("d:");System.out.println(d);System.out.println("f:");System.out.println(f);System.out.println("i:");System.out.println(i);System.out.println("force_load:");System.out.println(force_load);System.out.println("force_store:");System.out.println(force_store);System.out.println("force_const:");System.out.println(force_const);}}
|- Global_scope: packagecmjava2023.primitive.longs.conversions;
|  |- Package_declaration: packagecmjava2023.primitive.longs.conversions
|  |  |- package
|  |  L  Identifier: cmjava2023.primitive.longs.conversions
|  |     |- cmjava2023
|  |     |- .
|  |     |- primitive
|  |     |- .
|  |     |- longs
|  |     |- .
|  |     L  conversions
|  L  ;
L  Global_scope: publicclassMain{publicstaticvoidmain(String[]args){longl=10;doubled=(double)l;floatf=(float)l;inti=(int)l;longforce_store=l;longforce_load=force_store;longforce_const=1;System.out.println("l:");System.out.println(l);System.out.println("d:");System.out.println(d);System.out.println("f:");System.out.println(f);System.out.println("i:");System.out.println(i);System.out.println("force_load:");System.out.println(force_load);System.out.println("force_store:");System.out.println(force_store);System.out.println("force_const:");System.out.println(force_const);}}
   L  Class_declaration: publicclassMain{publicstaticvoidmain(String[]args){longl=10;doubled=(double)l;floatf=(float)l;inti=(int)l;longforce_store=l;longforce_load=force_store;longforce_const=1;System.out.println("l:");System.out.println(l);System.out.println("d:");System.out.println(d);System.out.println("f:");System.out.println(f);System.out.println("i:");System.out.println(i);System.out.println("force_load:");System.out.println(force_load);System.out.println("force_store:");System.out.println(force_store);System.out.println("force_const:");System.out.println(force_const);}}
      |- Access_modifier: public
      |  L  public
      |- class
      |- Main
      |- {
      |- Class_scope: publicstaticvoidmain(String[]args){longl=10;doubled=(double)l;floatf=(float)l;inti=(int)l;longforce_store=l;longforce_load=force_store;longforce_const=1;System.out.println("l:");System.out.println(l);System.out.println("d:");System.out.println(d);System.out.println("f:");System.out.println(f);System.out.println("i:");System.out.println(i);System.out.println("force_load:");System.out.println(force_load);System.out.println("force_store:");System.out.println(force_store);System.out.println("force_const:");System.out.println(force_const);}
      |  L  Function_declaration: publicstaticvoidmain(String[]args){longl=10;doubled=(double)l;floatf=(float)l;inti=(int)l;longforce_store=l;longforce_load=force_store;longforce_const=1;System.out.println("l:");System.out.println(l);System.out.println("d:");System.out.println(d);System.out.println("f:");System.out.println(f);System.out.println("i:");System.out.println(i);System.out.println("force_load:");System.out.println(force_load);System.out.println("force_store:");System.out.println(force_store);System.out.println("force_const:");System.out.println(force_const);}
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
      |     |- Function_scope: longl=10;doubled=(double)l;floatf=(float)l;inti=(int)l;longforce_store=l;longforce_load=force_store;longforce_const=1;System.out.println("l:");System.out.println(l);System.out.println("d:");System.out.println(d);System.out.println("f:");System.out.println(f);System.out.println("i:");System.out.println(i);System.out.println("force_load:");System.out.println(force_load);System.out.println("force_store:");System.out.println(force_store);System.out.println("force_const:");System.out.println(force_const);
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
      |     |  |- Assignment: doubled=(double)l
      |     |  |  |- Variable_declaration: doubled
      |     |  |  |  |- Primitive_type: double
      |     |  |  |  |  L  Numeric_type: double
      |     |  |  |  |     L  Floating_point_type: double
      |     |  |  |  |        L  double
      |     |  |  |  L  d
      |     |  |  |- =
      |     |  |  L  Expressions: (double)l
      |     |  |     L  Expression: (double)l
      |     |  |        L  Casting: (double)l
      |     |  |           |- (
      |     |  |           |- Type: double
      |     |  |           |  L  Primitive_type: double
      |     |  |           |     L  Numeric_type: double
      |     |  |           |        L  Floating_point_type: double
      |     |  |           |           L  double
      |     |  |           |- )
      |     |  |           L  Expression: l
      |     |  |              L  l
      |     |  |- ;
      |     |  |- Assignment: floatf=(float)l
      |     |  |  |- Variable_declaration: floatf
      |     |  |  |  |- Primitive_type: float
      |     |  |  |  |  L  Numeric_type: float
      |     |  |  |  |     L  Floating_point_type: float
      |     |  |  |  |        L  float
      |     |  |  |  L  f
      |     |  |  |- =
      |     |  |  L  Expressions: (float)l
      |     |  |     L  Expression: (float)l
      |     |  |        L  Casting: (float)l
      |     |  |           |- (
      |     |  |           |- Type: float
      |     |  |           |  L  Primitive_type: float
      |     |  |           |     L  Numeric_type: float
      |     |  |           |        L  Floating_point_type: float
      |     |  |           |           L  float
      |     |  |           |- )
      |     |  |           L  Expression: l
      |     |  |              L  l
      |     |  |- ;
      |     |  |- Assignment: inti=(int)l
      |     |  |  |- Variable_declaration: inti
      |     |  |  |  |- Primitive_type: int
      |     |  |  |  |  L  Numeric_type: int
      |     |  |  |  |     L  Integral_type: int
      |     |  |  |  |        L  int
      |     |  |  |  L  i
      |     |  |  |- =
      |     |  |  L  Expressions: (int)l
      |     |  |     L  Expression: (int)l
      |     |  |        L  Casting: (int)l
      |     |  |           |- (
      |     |  |           |- Type: int
      |     |  |           |  L  Primitive_type: int
      |     |  |           |     L  Numeric_type: int
      |     |  |           |        L  Integral_type: int
      |     |  |           |           L  int
      |     |  |           |- )
      |     |  |           L  Expression: l
      |     |  |              L  l
      |     |  |- ;
      |     |  |- Assignment: longforce_store=l
      |     |  |  |- Variable_declaration: longforce_store
      |     |  |  |  |- Primitive_type: long
      |     |  |  |  |  L  Numeric_type: long
      |     |  |  |  |     L  Integral_type: long
      |     |  |  |  |        L  long
      |     |  |  |  L  force_store
      |     |  |  |- =
      |     |  |  L  Expressions: l
      |     |  |     L  Expression: l
      |     |  |        L  l
      |     |  |- ;
      |     |  |- Assignment: longforce_load=force_store
      |     |  |  |- Variable_declaration: longforce_load
      |     |  |  |  |- Primitive_type: long
      |     |  |  |  |  L  Numeric_type: long
      |     |  |  |  |     L  Integral_type: long
      |     |  |  |  |        L  long
      |     |  |  |  L  force_load
      |     |  |  |- =
      |     |  |  L  Expressions: force_store
      |     |  |     L  Expression: force_store
      |     |  |        L  force_store
      |     |  |- ;
      |     |  |- Assignment: longforce_const=1
      |     |  |  |- Variable_declaration: longforce_const
      |     |  |  |  |- Primitive_type: long
      |     |  |  |  |  L  Numeric_type: long
      |     |  |  |  |     L  Integral_type: long
      |     |  |  |  |        L  long
      |     |  |  |  L  force_const
      |     |  |  |- =
      |     |  |  L  Expressions: 1
      |     |  |     L  Expression: 1
      |     |  |        L  1
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
      |     |  |- Expressions: System.out.println("force_load:")
      |     |  |  L  Expression: System.out.println("force_load:")
      |     |  |     L  Function_call: System.out.println("force_load:")
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: "force_load:"
      |     |  |        |  L  Function_arg: "force_load:"
      |     |  |        |     L  Expressions: "force_load:"
      |     |  |        |        L  Expression: "force_load:"
      |     |  |        |           L  "force_load:"
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println(force_load)
      |     |  |  L  Expression: System.out.println(force_load)
      |     |  |     L  Function_call: System.out.println(force_load)
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: force_load
      |     |  |        |  L  Function_arg: force_load
      |     |  |        |     L  Expressions: force_load
      |     |  |        |        L  Expression: force_load
      |     |  |        |           L  force_load
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println("force_store:")
      |     |  |  L  Expression: System.out.println("force_store:")
      |     |  |     L  Function_call: System.out.println("force_store:")
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: "force_store:"
      |     |  |        |  L  Function_arg: "force_store:"
      |     |  |        |     L  Expressions: "force_store:"
      |     |  |        |        L  Expression: "force_store:"
      |     |  |        |           L  "force_store:"
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println(force_store)
      |     |  |  L  Expression: System.out.println(force_store)
      |     |  |     L  Function_call: System.out.println(force_store)
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: force_store
      |     |  |        |  L  Function_arg: force_store
      |     |  |        |     L  Expressions: force_store
      |     |  |        |        L  Expression: force_store
      |     |  |        |           L  force_store
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println("force_const:")
      |     |  |  L  Expression: System.out.println("force_const:")
      |     |  |     L  Function_call: System.out.println("force_const:")
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: "force_const:"
      |     |  |        |  L  Function_arg: "force_const:"
      |     |  |        |     L  Expressions: "force_const:"
      |     |  |        |        L  Expression: "force_const:"
      |     |  |        |           L  "force_const:"
      |     |  |        L  )
      |     |  |- ;
      |     |  |- Expressions: System.out.println(force_const)
      |     |  |  L  Expression: System.out.println(force_const)
      |     |  |     L  Function_call: System.out.println(force_const)
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: force_const
      |     |  |        |  L  Function_arg: force_const
      |     |  |        |     L  Expressions: force_const
      |     |  |        |        L  Expression: force_const
      |     |  |        |           L  force_const
      |     |  |        L  )
      |     |  L  ;
      |     L  }
      L  }
                        """;

        assertEquals(expected, new TreePrinter().printParseTree(tree));
    }
}
