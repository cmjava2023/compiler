package cmjava2023.primitive.doubles.conversions;

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
Start: packagecmjava2023.primitive.doubles.conversions;publicclassMain{publicstaticvoidmain(String[]args){doubled=10.0;floatf=(float)d;inti=(int)d;longl=(long)d;doubleforce_store=d;doubleforce_load=force_store;doubleforce_const=1.0;System.out.println("d:");System.out.println(d);System.out.println("f:");System.out.println(f);System.out.println("k:");System.out.println(i);System.out.println("l:");System.out.println(l);System.out.println("force_load:");System.out.println(force_load);System.out.println("force_store:");System.out.println(force_store);System.out.println("force_const:");System.out.println(force_const);}}
|- Global_scope: packagecmjava2023.primitive.doubles.conversions;
|  |- Package_declaration: packagecmjava2023.primitive.doubles.conversions
|  |  |- package
|  |  L  Identifier: cmjava2023.primitive.doubles.conversions
|  |     |- cmjava2023
|  |     |- .
|  |     |- primitive
|  |     |- .
|  |     |- doubles
|  |     |- .
|  |     L  conversions
|  L  ;
L  Global_scope: publicclassMain{publicstaticvoidmain(String[]args){doubled=10.0;floatf=(float)d;inti=(int)d;longl=(long)d;doubleforce_store=d;doubleforce_load=force_store;doubleforce_const=1.0;System.out.println("d:");System.out.println(d);System.out.println("f:");System.out.println(f);System.out.println("k:");System.out.println(i);System.out.println("l:");System.out.println(l);System.out.println("force_load:");System.out.println(force_load);System.out.println("force_store:");System.out.println(force_store);System.out.println("force_const:");System.out.println(force_const);}}
   L  Class_declaration: publicclassMain{publicstaticvoidmain(String[]args){doubled=10.0;floatf=(float)d;inti=(int)d;longl=(long)d;doubleforce_store=d;doubleforce_load=force_store;doubleforce_const=1.0;System.out.println("d:");System.out.println(d);System.out.println("f:");System.out.println(f);System.out.println("k:");System.out.println(i);System.out.println("l:");System.out.println(l);System.out.println("force_load:");System.out.println(force_load);System.out.println("force_store:");System.out.println(force_store);System.out.println("force_const:");System.out.println(force_const);}}
      |- Access_modifier: public
      |  L  public
      |- class
      |- Main
      |- {
      |- Class_scope: publicstaticvoidmain(String[]args){doubled=10.0;floatf=(float)d;inti=(int)d;longl=(long)d;doubleforce_store=d;doubleforce_load=force_store;doubleforce_const=1.0;System.out.println("d:");System.out.println(d);System.out.println("f:");System.out.println(f);System.out.println("k:");System.out.println(i);System.out.println("l:");System.out.println(l);System.out.println("force_load:");System.out.println(force_load);System.out.println("force_store:");System.out.println(force_store);System.out.println("force_const:");System.out.println(force_const);}
      |  L  Function_declaration: publicstaticvoidmain(String[]args){doubled=10.0;floatf=(float)d;inti=(int)d;longl=(long)d;doubleforce_store=d;doubleforce_load=force_store;doubleforce_const=1.0;System.out.println("d:");System.out.println(d);System.out.println("f:");System.out.println(f);System.out.println("k:");System.out.println(i);System.out.println("l:");System.out.println(l);System.out.println("force_load:");System.out.println(force_load);System.out.println("force_store:");System.out.println(force_store);System.out.println("force_const:");System.out.println(force_const);}
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
      |     |- Function_scope: doubled=10.0;floatf=(float)d;inti=(int)d;longl=(long)d;doubleforce_store=d;doubleforce_load=force_store;doubleforce_const=1.0;System.out.println("d:");System.out.println(d);System.out.println("f:");System.out.println(f);System.out.println("k:");System.out.println(i);System.out.println("l:");System.out.println(l);System.out.println("force_load:");System.out.println(force_load);System.out.println("force_store:");System.out.println(force_store);System.out.println("force_const:");System.out.println(force_const);
      |     |  |- Assignment: doubled=10.0
      |     |  |  |- Variable_declaration: doubled
      |     |  |  |  |- Primitive_type: double
      |     |  |  |  |  L  Numeric_type: double
      |     |  |  |  |     L  Floating_point_type: double
      |     |  |  |  |        L  double
      |     |  |  |  L  d
      |     |  |  |- =
      |     |  |  L  Expressions: 10.0
      |     |  |     L  Expression: 10.0
      |     |  |        L  10.0
      |     |  |- ;
      |     |  |- Assignment: floatf=(float)d
      |     |  |  |- Variable_declaration: floatf
      |     |  |  |  |- Primitive_type: float
      |     |  |  |  |  L  Numeric_type: float
      |     |  |  |  |     L  Floating_point_type: float
      |     |  |  |  |        L  float
      |     |  |  |  L  f
      |     |  |  |- =
      |     |  |  L  Expressions: (float)d
      |     |  |     L  Expression: (float)d
      |     |  |        L  Casting: (float)d
      |     |  |           |- (
      |     |  |           |- Type: float
      |     |  |           |  L  Primitive_type: float
      |     |  |           |     L  Numeric_type: float
      |     |  |           |        L  Floating_point_type: float
      |     |  |           |           L  float
      |     |  |           |- )
      |     |  |           L  Expressions: d
      |     |  |              L  Expression: d
      |     |  |                 L  d
      |     |  |- ;
      |     |  |- Assignment: inti=(int)d
      |     |  |  |- Variable_declaration: inti
      |     |  |  |  |- Primitive_type: int
      |     |  |  |  |  L  Numeric_type: int
      |     |  |  |  |     L  Integral_type: int
      |     |  |  |  |        L  int
      |     |  |  |  L  i
      |     |  |  |- =
      |     |  |  L  Expressions: (int)d
      |     |  |     L  Expression: (int)d
      |     |  |        L  Casting: (int)d
      |     |  |           |- (
      |     |  |           |- Type: int
      |     |  |           |  L  Primitive_type: int
      |     |  |           |     L  Numeric_type: int
      |     |  |           |        L  Integral_type: int
      |     |  |           |           L  int
      |     |  |           |- )
      |     |  |           L  Expressions: d
      |     |  |              L  Expression: d
      |     |  |                 L  d
      |     |  |- ;
      |     |  |- Assignment: longl=(long)d
      |     |  |  |- Variable_declaration: longl
      |     |  |  |  |- Primitive_type: long
      |     |  |  |  |  L  Numeric_type: long
      |     |  |  |  |     L  Integral_type: long
      |     |  |  |  |        L  long
      |     |  |  |  L  l
      |     |  |  |- =
      |     |  |  L  Expressions: (long)d
      |     |  |     L  Expression: (long)d
      |     |  |        L  Casting: (long)d
      |     |  |           |- (
      |     |  |           |- Type: long
      |     |  |           |  L  Primitive_type: long
      |     |  |           |     L  Numeric_type: long
      |     |  |           |        L  Integral_type: long
      |     |  |           |           L  long
      |     |  |           |- )
      |     |  |           L  Expressions: d
      |     |  |              L  Expression: d
      |     |  |                 L  d
      |     |  |- ;
      |     |  |- Assignment: doubleforce_store=d
      |     |  |  |- Variable_declaration: doubleforce_store
      |     |  |  |  |- Primitive_type: double
      |     |  |  |  |  L  Numeric_type: double
      |     |  |  |  |     L  Floating_point_type: double
      |     |  |  |  |        L  double
      |     |  |  |  L  force_store
      |     |  |  |- =
      |     |  |  L  Expressions: d
      |     |  |     L  Expression: d
      |     |  |        L  d
      |     |  |- ;
      |     |  |- Assignment: doubleforce_load=force_store
      |     |  |  |- Variable_declaration: doubleforce_load
      |     |  |  |  |- Primitive_type: double
      |     |  |  |  |  L  Numeric_type: double
      |     |  |  |  |     L  Floating_point_type: double
      |     |  |  |  |        L  double
      |     |  |  |  L  force_load
      |     |  |  |- =
      |     |  |  L  Expressions: force_store
      |     |  |     L  Expression: force_store
      |     |  |        L  force_store
      |     |  |- ;
      |     |  |- Assignment: doubleforce_const=1.0
      |     |  |  |- Variable_declaration: doubleforce_const
      |     |  |  |  |- Primitive_type: double
      |     |  |  |  |  L  Numeric_type: double
      |     |  |  |  |     L  Floating_point_type: double
      |     |  |  |  |        L  double
      |     |  |  |  L  force_const
      |     |  |  |- =
      |     |  |  L  Expressions: 1.0
      |     |  |     L  Expression: 1.0
      |     |  |        L  1.0
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
      |     |  |- Expressions: System.out.println("k:")
      |     |  |  L  Expression: System.out.println("k:")
      |     |  |     L  Function_call: System.out.println("k:")
      |     |  |        |- Identifier: System.out.println
      |     |  |        |  |- System
      |     |  |        |  |- .
      |     |  |        |  |- out
      |     |  |        |  |- .
      |     |  |        |  L  println
      |     |  |        |- (
      |     |  |        |- Function_args: "k:"
      |     |  |        |  L  Function_arg: "k:"
      |     |  |        |     L  Expressions: "k:"
      |     |  |        |        L  Expression: "k:"
      |     |  |        |           L  "k:"
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

        String actual = new ParseTreePrinter().print(tree);
        assertEquals(expected, actual);
    }
}
