package org.cmjava2023

import org.cmjava2023.ast.ASTNodes
import org.cmjava2023.ast.ASTNodes.ComparisonOperator
import org.cmjava2023.ast.ASTTraverser
import org.cmjava2023.classfilespecification.OpCode
import org.cmjava2023.classfilespecification.constantpool.*
import org.cmjava2023.symboltable.Type
import org.cmjava2023.symboltable.Variable

class FunctionCodeAstTraverser : ASTTraverser<List<OpCode>>() {
    override fun visit(startNode: ASTNodes.StartNode): List<OpCode> {
        throw NotImplementedError()
    }

    override fun visit(packageNode: ASTNodes.PackageNode): List<OpCode> {
        throw NotImplementedError()
    }

    override fun visit(classNode: ASTNodes.ClassNode): List<OpCode> {
        throw NotImplementedError()
    }

    override fun visit(continueNodeNode: ASTNodes.ContinueNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(breakNode: ASTNodes.BreakNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(enumNode: ASTNodes.EnumNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(switchNode: ASTNodes.SwitchNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(caseNode: ASTNodes.CaseNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(functionNode: ASTNodes.FunctionNode): List<OpCode> {
        val opCodes = functionNode.body.flatMap { dispatch(it) }
        return if (opCodes.lastOrNull() !is OpCode.ReturnAnything) {
            opCodes.plus(OpCode.Return())
        } else {
            opCodes
        }
    }

    override fun visit(node: ASTNodes.ParameterNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(functionCallNode: ASTNodes.FunctionCallNode): List<OpCode> {
        if (functionCallNode.function.name == "System.out.println"
        ) {
            val splitName = functionCallNode.function.name.split(".")
            val className = splitName[0]
            val fieldName = splitName[1]
            val methodName = splitName[2]

            val qualifiedClassName = "java/lang/$className"

            val fieldReferenceConstantInfo = FieldReferenceConstantInfo(
                ClassConstantInfo(qualifiedClassName),
                NameAndTypeConstantInfo(fieldName, "Ljava/io/PrintStream;")
            )

            var typeCodeToPrint = "Ljava/lang/String;"
            val expr = functionCallNode.values[0]
            val opcOdesLoadingWhatToPrint = if (expr is ASTNodes.ValueNode<*>) {
                when (val value = expr.value) {
                    is String -> listOf(OpCode.LoadConstant(StringConstantInfo(value)))
                    else -> throw NotImplementedError()
                }
            } else if (expr is ASTNodes.InfixNode && expr.operator == ASTNodes.InfixOperator.PLUS) {
                visit(expr)
            } else if (expr is ASTNodes.VariableCallNode) {
                typeCodeToPrint = getCharOfTypeForTypeDescriptor(expr.symbol.type)
                visit(expr)
            } else {
                throw NotImplementedError(expr.javaClass.name)
            }
            val methodReferenceConstantInfo = MethodReferenceConstantInfo(
                ClassConstantInfo("java/io/PrintStream"),
                NameAndTypeConstantInfo(methodName, "($typeCodeToPrint)V")
            )

            return listOf(OpCode.Getstatic(fieldReferenceConstantInfo))
                .plus(opcOdesLoadingWhatToPrint)
                .plus(OpCode.Invokevirtual(methodReferenceConstantInfo))
        } else {
            throw NotImplementedError()
        }
    }
    
    private fun getCharOfTypeForTypeDescriptor(type: Type): String {
        return when (type.name) {
            "int" -> "I"
            "boolean" -> "Z"
            "float" -> "F"
            "char" -> "C"
            "byte" -> "I"
            "short" -> "I"
            "long" -> "J"
            "double" -> "D"
            else -> throw NotImplementedError(type.name)
        }
    }

    override fun visit(infixNode: ASTNodes.InfixNode): List<OpCode> {
        val leftExpression = infixNode.leftExpression
        val rightExpression = infixNode.rightExpression
        if (infixNode.operator == ASTNodes.InfixOperator.PLUS && leftExpression is ASTNodes.ValueNode<*> && leftExpression.value is String && rightExpression is ASTNodes.VariableCallNode) {
            return listOf(
                OpCode.New(ClassConstantInfo("java/lang/StringBuilder")),
                OpCode.Dup(),
                OpCode.Invokespecial(
                    MethodReferenceConstantInfo(
                        ClassConstantInfo("java/lang/StringBuilder"),
                        NameAndTypeConstantInfo("<init>", "()V")
                    )
                ),
                OpCode.LoadConstant(StringConstantInfo(leftExpression.value as String)),
                OpCode.Invokevirtual(
                    MethodReferenceConstantInfo(
                        ClassConstantInfo("java/lang/StringBuilder"),
                        NameAndTypeConstantInfo("append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;")
                    )
                )
            )
                .plus(dispatch(infixNode.rightExpression))
                .plus(
                    OpCode.Invokevirtual(
                        MethodReferenceConstantInfo(
                            ClassConstantInfo("java/lang/StringBuilder"),
                            NameAndTypeConstantInfo(
                                "append", "(" + getCharOfTypeForTypeDescriptor(rightExpression.symbol.type) + ")Ljava/lang/StringBuilder;"
                            )
                        )
                    )
                )
                .plus(
                    OpCode.Invokevirtual(
                        MethodReferenceConstantInfo(
                            ClassConstantInfo("java/lang/StringBuilder"),
                            NameAndTypeConstantInfo("toString", "()Ljava/lang/String;")
                        )
                    )
                )
        } else {
            throw NotImplementedError()
        }
    }


    override fun visit(ifNode: ASTNodes.IfNode): List<OpCode> {
        throw NotImplementedError()
    }

    override fun visit(elseNode: ASTNodes.ElseNode): List<OpCode> {
        throw NotImplementedError()
    }

    override fun visit(blockScopeNode: ASTNodes.BlockScopeNode): List<OpCode> {
        return listOf()
    }

    override fun visit(variableNode: ASTNodes.VariableNode): List<OpCode> {
        val variableSymbol = variableNode.variableSymbol
        val value = variableSymbol.initialExpression
        return assignOrDeclareVariable(variableSymbol, value)
    }

    private fun assignOrDeclareVariable(variableSymbol: Variable, value: ASTNodes.Expression): List<OpCode> {
        val opCodesLoadingExpressionValueOnStack = dispatch(value)
        val storingOpCode = when (variableSymbol.type.name) {
            "int" -> OpCode.StoreInt(variableSymbol)
            "long" -> OpCode.StoreLong(variableSymbol)
            "float" -> OpCode.StoreFloat(variableSymbol)
            "double" -> OpCode.StoreDouble(variableSymbol)
            "boolean" -> OpCode.StoreInt(variableSymbol)
            "char" -> OpCode.StoreInt(variableSymbol)
            "byte" -> OpCode.StoreInt(variableSymbol)
            "short" -> OpCode.StoreInt(variableSymbol)
            else -> throw NotImplementedError("typename:${variableSymbol.type.name}")
        }
        return opCodesLoadingExpressionValueOnStack.plus(storingOpCode)
    }

    override fun visit(variableAssignmentNode: ASTNodes.VariableAssignmentNode): List<OpCode> {
        val variableSymbol = variableAssignmentNode.variable
        val value = variableAssignmentNode.expression
        return assignOrDeclareVariable(variableSymbol, value)
    }

    override fun visit(valueNode: ASTNodes.ValueNode<*>): List<OpCode> {
        val value = valueNode.value
        return listOf(
            when (value) {
                is String -> OpCode.LoadConstant(StringConstantInfo(value))
                is Int -> OpCode.IntConstant(value)
                is Long -> OpCode.LongConstant(value)
                is Float -> OpCode.FloatConstant(value)
                is Double -> OpCode.DoubleConstant(value)
                is Boolean -> if (value) {
                    OpCode.Iconst_1()
                } else {
                    OpCode.Iconst_0()
                }

                is Char -> OpCode.IntConstant(value.code)
                is Byte -> OpCode.IntConstant(value.toInt())
                is Short -> OpCode.IntConstant(value.toInt())
                else -> throw NotImplementedError("type${value.javaClass.name}")
            }
        )
    }

    override fun visit(nestedIdentifierNode: ASTNodes.NestedIdentifierNode): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(comparisonNode: ASTNodes.ComparisonNode): List<OpCode> {
        val leftExpression = comparisonNode.leftExpression
        val rightExpression = comparisonNode.rightExpression
        val result = mutableListOf<OpCode>()
        if(leftExpression is ASTNodes.VariableCallNode && rightExpression is ASTNodes.ValueNode<*>) {
            result.addAll(visit(leftExpression))
            result.addAll(visit(rightExpression))
            if (leftExpression.symbol.type.name == "int" && rightExpression.value is Int) {
                result.add(when (comparisonNode.comparisonOperator) {
                   ComparisonOperator.BAND -> OpCode.Iand()
                   ComparisonOperator.BOR -> OpCode.Ior()
                   ComparisonOperator.BXOR -> OpCode.Ixor()
                   ComparisonOperator.BIT_SHIFT_L -> OpCode.Ishl()
                   ComparisonOperator.BIT_SHIFT_R -> OpCode.Ishr()
                   else -> throw NotImplementedError(comparisonNode.comparisonOperator.name)
                })
            } else {
                throw NotImplementedError(leftExpression.symbol.type.name + " " + rightExpression.value.javaClass.name)
            }
        } else {
            throw NotImplementedError(leftExpression.javaClass.name + " " + rightExpression.javaClass.name)
        }
        return result
    }

    override fun visit(variableCallNode: ASTNodes.VariableCallNode): List<OpCode> {
        return listOf(
            when (variableCallNode.symbol.type.name) {
                "int" -> OpCode.LoadInt(variableCallNode.symbol)
                "long" -> OpCode.LoadLong(variableCallNode.symbol)
                "float" -> OpCode.LoadFloat(variableCallNode.symbol)
                "double" -> OpCode.LoadDouble(variableCallNode.symbol)
                "boolean" -> OpCode.LoadInt(variableCallNode.symbol)
                "char" -> OpCode.LoadInt(variableCallNode.symbol)
                "byte" -> OpCode.LoadInt(variableCallNode.symbol)
                "short" -> OpCode.LoadInt(variableCallNode.symbol)
                else -> throw NotImplementedError("typename:${variableCallNode.symbol.type.name}")
            }
        )
    }

    override fun visit(returnNode: ASTNodes.ReturnNode): List<OpCode> {
        return listOf(OpCode.Return())
    }

    override fun visit(typeNode: ASTNodes.TypeNode): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(arrayTypeNode: ASTNodes.ArrayTypeNode): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(parameterAssignmentNode: ASTNodes.ParameterAssignmentNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(unaryPrefixNode: ASTNodes.UnaryPrefixNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(unarySuffixNode: ASTNodes.UnarySuffixNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(parenthesesNode: ASTNodes.ParenthesesNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(castNode: ASTNodes.CastNode): List<OpCode> {
        val result = mutableListOf<OpCode>()
        val expression = castNode.expression
        if(expression is ASTNodes.VariableCallNode) {
            result.addAll(visit(expression))
            val fromTypeName = expression.symbol.type.name
            val toTypeName = castNode.type.name
            result.add(when(fromTypeName) {
                "int" -> when (toTypeName) {
                    "byte" -> OpCode.I2b()
                    "char" -> OpCode.I2c()
                    "short" -> OpCode.I2s()
                    "long" -> OpCode.I2l()
                    "float" -> OpCode.I2f()
                    "double" -> OpCode.I2d()
                    else -> throw NotImplementedError(toTypeName)
                }
                else -> throw NotImplementedError(fromTypeName)
            })
        }
        return result
    }

    override fun visit(arrayInstantiationWithValuesNode: ASTNodes.ArrayInstantiationWithValuesNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(arrayInstantiationNode: ASTNodes.ArrayInstantiationNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(arrayAccessNode: ASTNodes.ArrayAccessNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(objectInstantiationNode: ASTNodes.ObjectInstantiationNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(forLoopNode: ASTNodes.ForLoopNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(whileLoopNode: ASTNodes.WhileLoopNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(doWhileLoopNode: ASTNodes.DoWhileLoopNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(operatorNode: ASTNodes.OperatorNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(variableFieldCallNode: ASTNodes.VariableFieldCallNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(variableFunctionCallNode: ASTNodes.VariableFunctionCallNode?): List<OpCode> {
        TODO("Not yet implemented")
    }
}