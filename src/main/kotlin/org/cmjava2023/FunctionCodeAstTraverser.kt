package org.cmjava2023

import org.cmjava2023.ast.ASTNodes
import org.cmjava2023.ast.ASTNodes.ComparisonOperator
import org.cmjava2023.ast.ASTTraverser
import org.cmjava2023.classfilespecification.OpCode
import org.cmjava2023.classfilespecification.constantpool.*
import org.cmjava2023.symboltable.ArrayType
import org.cmjava2023.symboltable.BuiltIn
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

    override fun visit(parameterCallNode: ASTNodes.ParameterCallNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(functionCallNode: ASTNodes.FunctionCallNode): List<OpCode> {
        when (functionCallNode.function.name) {
            "System.out.println" -> {
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
                    typeCodeToPrint = getCharOfBuiltInForTypeDescriptor(expr.symbol.type)
                    visit(expr)
                }  else if (expr is ASTNodes.ArrayAccessNode) {
                    val arrayType = (expr.array.type as ArrayType).arrayType
                    if(arrayType.name != "String") {
                        typeCodeToPrint = getCharOfBuiltInForTypeDescriptor(arrayType)
                    }
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
            }
            "System.in.read" -> return listOf(
                OpCode.Getstatic(FieldReferenceConstantInfo(ClassConstantInfo("java/lang/System"), NameAndTypeConstantInfo("in","Ljava/io/InputStream;"))),
                OpCode.Invokevirtual(MethodReferenceConstantInfo(ClassConstantInfo("java/io/InputStream"), NameAndTypeConstantInfo("read", "()I"))),
                OpCode.I2c())
            else -> {
                throw NotImplementedError()
            }
        }
    }

    private fun getCharOfBuiltInForTypeDescriptor(type: Type): String {
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
        return if (infixNode.operator == ASTNodes.InfixOperator.PLUS && leftExpression is ASTNodes.ValueNode<*> && leftExpression.value is String && rightExpression is ASTNodes.VariableCallNode) {
            concatStringLiteralAndVariable(leftExpression, infixNode, rightExpression)
        } else {
            val leftType = getTypeNameOf(leftExpression)
            val rightType = getTypeNameOf(rightExpression)

            if (leftType == rightType || rightExpression is ASTNodes.ValueNode<*>) {
                dispatch(leftExpression)
                    .plus(if(rightExpression is ASTNodes.ValueNode<*>) { visitValueNodeThatNeedsType(rightExpression, leftType) } else {dispatch(rightExpression) })
                    .plus(when (leftType) {
                    "int" -> infixBothInt(infixNode.operator)
                    "long" -> infixBothLong(infixNode.operator)
                    "float" -> infixBothFloat(infixNode.operator)
                    "double" -> infixBothDouble(infixNode.operator)
                    else -> throw NotImplementedError(leftType)
                })
            } else {
                throw NotImplementedError("$leftType $rightType")
            }
        }
    }

    private fun getTypeNameOf(expression: ASTNodes.Expression): String {
        return when (expression) {
            is ASTNodes.VariableCallNode -> expression.symbol.type.name
            is ASTNodes.ValueNode<*> -> when (expression.value) {
                is String -> "String"
                is Int -> "int"
                is Long -> "long"
                is Float -> "float"
                is Double -> "double"
                is Boolean -> "boolean"
                is Char -> "char"
                is Byte -> "byte"
                is Short -> "short"
                else -> throw NotImplementedError("type${expression.value.javaClass.name}")
            }

            else -> throw NotImplementedError(expression.javaClass.name)
        }
    }

    private fun infixBothInt(operator: ASTNodes.InfixOperator): List<OpCode> {
        return listOf(
            when (operator) {
                ASTNodes.InfixOperator.PLUS -> OpCode.Iadd()
                ASTNodes.InfixOperator.MINUS -> OpCode.Isub()
                ASTNodes.InfixOperator.DIVISION -> OpCode.Idiv()
                ASTNodes.InfixOperator.MULTIPLICATION -> OpCode.Imul()
                ASTNodes.InfixOperator.MOD -> OpCode.Irem()
                else -> throw NotImplementedError(operator.name)
            }
        )
    }

    private fun infixBothLong(operator: ASTNodes.InfixOperator): List<OpCode> {
        return listOf(
            when (operator) {
                ASTNodes.InfixOperator.PLUS -> OpCode.Ladd()
                ASTNodes.InfixOperator.MINUS -> OpCode.Lsub()
                ASTNodes.InfixOperator.DIVISION -> OpCode.Ldiv()
                ASTNodes.InfixOperator.MULTIPLICATION -> OpCode.Lmul()
                ASTNodes.InfixOperator.MOD -> OpCode.Lrem()
                else -> throw NotImplementedError(operator.name)
            }
        )
    }

    private fun infixBothFloat(operator: ASTNodes.InfixOperator): List<OpCode> {
        return listOf(
            when (operator) {
                ASTNodes.InfixOperator.PLUS -> OpCode.Fadd()
                ASTNodes.InfixOperator.MINUS -> OpCode.Fsub()
                ASTNodes.InfixOperator.DIVISION -> OpCode.Fdiv()
                ASTNodes.InfixOperator.MULTIPLICATION -> OpCode.Fmul()
                ASTNodes.InfixOperator.MOD -> OpCode.Frem()
                else -> throw NotImplementedError(operator.name)
            }
        )
    }

    private fun infixBothDouble(operator: ASTNodes.InfixOperator): List<OpCode> {
        return listOf(
            when (operator) {
                ASTNodes.InfixOperator.PLUS -> OpCode.Dadd()
                ASTNodes.InfixOperator.MINUS -> OpCode.Dsub()
                ASTNodes.InfixOperator.DIVISION -> OpCode.Ddiv()
                ASTNodes.InfixOperator.MULTIPLICATION -> OpCode.Dmul()
                ASTNodes.InfixOperator.MOD -> OpCode.Drem()
                else -> throw NotImplementedError(operator.name)
            }
        )
    }

    private fun FunctionCodeAstTraverser.concatStringLiteralAndVariable(
        leftExpression: ASTNodes.ValueNode<*>,
        infixNode: ASTNodes.InfixNode,
        rightExpression: ASTNodes.VariableCallNode
    ): List<OpCode> {
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
                            "append",
                            "(" + getCharOfBuiltInForTypeDescriptor(rightExpression.symbol.type) + ")Ljava/lang/StringBuilder;"
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
    }


    override fun visit(ifNode: ASTNodes.IfNode): List<OpCode> {
        throw NotImplementedError()
    }

    override fun visit(variableNode: ASTNodes.VariableNode): List<OpCode> {
        val variableSymbol = variableNode.variableSymbol
        val value = variableSymbol.initialExpression
        return if(value == null) {
            listOf()
        } else {
            assignOrDeclareVariable(variableSymbol, value)
        }
    }

    private fun assignOrDeclareVariable(variableSymbol: Variable, value: ASTNodes.Expression): List<OpCode> {
        val opCodesLoadingExpressionValueOnStack = if (value is ASTNodes.ValueNode<*> && getTypeNameOf(value) != variableSymbol.type.name) {
            visitValueNodeThatNeedsType(value, variableSymbol.type.name)
        } else if (variableSymbol.type is ArrayType && value is ASTNodes.ArrayInstantiationWithValuesNode) {
            visit(value, variableSymbol.type as ArrayType)
        } else {
            dispatch(value)
        }
        val storingOpCode = storeStackInVar(variableSymbol)
        return opCodesLoadingExpressionValueOnStack.plus(storingOpCode)
    }

    private fun FunctionCodeAstTraverser.visitValueNodeThatNeedsType(
        value: ASTNodes.ValueNode<*>,
        typeName: String
    ): List<OpCode> {
        val valueAsString = value.value.toString()
        return visit(when (typeName) {
            "int" -> ASTNodes.ValueNode(valueAsString.toInt())
            "long" -> ASTNodes.ValueNode(valueAsString.toLong())
            "float" -> ASTNodes.ValueNode(valueAsString.toFloat())
            "double" -> ASTNodes.ValueNode(valueAsString.toDouble())
            "boolean" -> ASTNodes.ValueNode(valueAsString.toBoolean())
            "char" -> {
                when (value.value) {
                    is Char -> value
                    is Int -> ASTNodes.ValueNode(Char(value.value as Int))
                    else -> throw NotImplementedError(value.value.javaClass.name)
                }
            }
            "byte" -> ASTNodes.ValueNode(valueAsString.toByte())
            "short" -> ASTNodes.ValueNode(valueAsString.toShort())
            "String" -> ASTNodes.ValueNode(valueAsString)
            else -> throw NotImplementedError("typename:$typeName")
        })
    }

    private fun storeStackInVar(variableSymbol: Variable): OpCode {
        return when(variableSymbol.type) {
            is ArrayType -> OpCode.StoreArray(variableSymbol)
            is BuiltIn -> when (variableSymbol.type.name) {
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
            else -> throw NotImplementedError("typename:${variableSymbol.type.javaClass.name}")
        }
       
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
        if (leftExpression is ASTNodes.VariableCallNode && rightExpression is ASTNodes.ValueNode<*>) {
            result.addAll(visit(leftExpression))
            if(comparisonNode.comparisonOperator == ComparisonOperator.BAND || comparisonNode.comparisonOperator == ComparisonOperator.BOR || comparisonNode.comparisonOperator == ComparisonOperator.BXOR) {
                result.addAll(visitValueNodeThatNeedsType(rightExpression, leftExpression.symbol.type.name))
            } else {
                result.addAll(visit(rightExpression))
            }

            when (leftExpression.symbol.type.name) {
                "int" -> {
                    result.add(
                        when (comparisonNode.comparisonOperator) {
                            ComparisonOperator.BAND -> OpCode.Iand()
                            ComparisonOperator.BOR -> OpCode.Ior()
                            ComparisonOperator.BXOR -> OpCode.Ixor()
                            ComparisonOperator.BIT_SHIFT_L -> OpCode.Ishl()
                            ComparisonOperator.BIT_SHIFT_R -> OpCode.Ishr()
                            ComparisonOperator.LOGICAL_SHIFT_R -> OpCode.Iushr()
                            else -> throw NotImplementedError(comparisonNode.comparisonOperator.name)
                        }
                    )
                }
                "long" -> {
                    result.add(
                        when (comparisonNode.comparisonOperator) {
                            ComparisonOperator.BAND -> OpCode.Land()
                            ComparisonOperator.BOR -> OpCode.Lor()
                            ComparisonOperator.BXOR -> OpCode.Lxor()
                            ComparisonOperator.BIT_SHIFT_L -> OpCode.Lshl()
                            ComparisonOperator.BIT_SHIFT_R -> OpCode.Lshr()
                            ComparisonOperator.LOGICAL_SHIFT_R -> OpCode.Lushr()
                            else -> throw NotImplementedError(comparisonNode.comparisonOperator.name)
                        }
                    )
                }
                else -> {
                    throw NotImplementedError(leftExpression.symbol.type.name + " " + rightExpression.value.javaClass.name)
                }
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

    override fun visit(unaryPrefixNode: ASTNodes.UnaryPrefixNode): List<OpCode> {
        val typeName = getTypeNameOf(unaryPrefixNode.Expression)
        return dispatch(unaryPrefixNode.Expression).plus(
            when (typeName) {
                "int" -> when (unaryPrefixNode.operator) {
                    ASTNodes.PrefixOperator.MINUS -> OpCode.Ineg()
                    else -> throw NotImplementedError(unaryPrefixNode.operator.name)
                }
                "long" -> when (unaryPrefixNode.operator) {
                    ASTNodes.PrefixOperator.MINUS -> OpCode.Lneg()
                    else -> throw NotImplementedError(unaryPrefixNode.operator.name)
                }
                "float" -> when (unaryPrefixNode.operator) {
                    ASTNodes.PrefixOperator.MINUS -> OpCode.Fneg()
                    else -> throw NotImplementedError(unaryPrefixNode.operator.name)
                }
                "double" -> when (unaryPrefixNode.operator) {
                    ASTNodes.PrefixOperator.MINUS -> OpCode.Dneg()
                    else -> throw NotImplementedError(unaryPrefixNode.operator.name)
                }

                else -> throw NotImplementedError(typeName)
            }
        )
    }

    override fun visit(unarySuffixNode: ASTNodes.UnarySuffixNode): List<OpCode> {
        val expression = unarySuffixNode.Expression
        return if (expression is ASTNodes.VariableCallNode) {
            when (expression.symbol.type.name) {
                "int" -> listOf(when (unarySuffixNode.operator) {
                    ASTNodes.SuffixOperator.INC -> OpCode.IncreaseInt(expression.symbol, 1)
                    ASTNodes.SuffixOperator.DEC -> OpCode.IncreaseInt(expression.symbol, -1)
                    else -> throw NotImplementedError(unarySuffixNode.operator.name)
                })
                else -> throw NotImplementedError(expression.symbol.type.name)
            }
        } else throw NotImplementedError(unarySuffixNode.Expression.javaClass.name)
    }

    override fun visit(parenthesesNode: ASTNodes.ParenthesesNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(castNode: ASTNodes.CastNode): List<OpCode> {
        val result = mutableListOf<OpCode>()
        when (val expression = castNode.expression) {
            is ASTNodes.VariableCallNode -> {
                result.addAll(visit(expression))
                val fromTypeName = expression.symbol.type.name
                val toTypeName = castNode.type.name
                result.add(
                    when (fromTypeName) {
                        "int" -> when (toTypeName) {
                            "byte" -> OpCode.I2b()
                            "char" -> OpCode.I2c()
                            "short" -> OpCode.I2s()
                            "long" -> OpCode.I2l()
                            "float" -> OpCode.I2f()
                            "double" -> OpCode.I2d()
                            else -> throw NotImplementedError(toTypeName)
                        }
                        "long" -> when (toTypeName) {
                            "int" -> OpCode.L2i()
                            "float" -> OpCode.L2f()
                            "double" -> OpCode.L2d()
                            else -> throw NotImplementedError(toTypeName)
                        }
                        "float" -> when (toTypeName) {
                            "int" -> OpCode.F2i()
                            "long" -> OpCode.F2l()
                            "double" -> OpCode.F2d()
                            else -> throw NotImplementedError(toTypeName)
                        }
                        "double" -> when (toTypeName) {
                            "int" -> OpCode.D2i()
                            "long" -> OpCode.D2l()
                            "float" -> OpCode.D2f()
                            else -> throw NotImplementedError(toTypeName)
                        }
                        else -> throw NotImplementedError(fromTypeName)
                    }
                )
            }
            is ASTNodes.FunctionCallNode -> return visit(expression)
            else -> throw NotImplementedError(expression.javaClass.name)
        }
        return result
    }

    override fun visit(arrayInstantiationWithValuesNode: ASTNodes.ArrayInstantiationWithValuesNode): List<OpCode> {
        TODO("Not yet implemented")
    }
    
    fun visit(arrayInstantiationWithValuesNode: ASTNodes.ArrayInstantiationWithValuesNode, arrayType: ArrayType): List<OpCode> {        
        val result = mutableListOf<OpCode>()
        val arrayLength = arrayInstantiationWithValuesNode.expressions.size
        result.add(OpCode.IntConstant(arrayLength))
        val valueNodesOrNull = arrayInstantiationWithValuesNode.expressions.map { if(it is ASTNodes.ValueNode<*>) { it } else { null } }
        if(valueNodesOrNull.none { it == null }) {
            result.add(when(arrayType.arrayType.name) {
                "String" -> OpCode.Anewarray(ClassConstantInfo("java/lang/String"))
                "boolean" -> OpCode.Newarray(OpCode.ArrayType.T_BOOLEAN)
                "int" -> OpCode.Newarray(OpCode.ArrayType.T_INT)
                "byte" -> OpCode.Newarray(OpCode.ArrayType.T_BYTE)
                "char" -> OpCode.Newarray(OpCode.ArrayType.T_CHAR)
                "short" -> OpCode.Newarray(OpCode.ArrayType.T_SHORT)
                "long" -> OpCode.Newarray(OpCode.ArrayType.T_LONG)
                "float" -> OpCode.Newarray(OpCode.ArrayType.T_FLOAT)
                "double" -> OpCode.Newarray(OpCode.ArrayType.T_DOUBLE)
                else -> throw NotImplementedError(arrayType.arrayType.name)
            })
            val opCodeToStoreArrayElements = when(arrayType.arrayType.name) {
                "String" -> OpCode.Aastore()
                "boolean" -> OpCode.Bastore()
                "int" -> OpCode.Iastore()
                "byte" -> OpCode.Bastore()
                "short" -> OpCode.Sastore()
                "char" -> OpCode.Castore()
                "long" -> OpCode.Lastore()
                "double" -> OpCode.Dastore()
                "float" -> OpCode.Fastore()
                else -> throw NotImplementedError(arrayType.arrayType.name)
            }
            result.add(OpCode.Dup())
            val valueNodes = valueNodesOrNull.requireNoNulls()
            for ((index, valueNode) in valueNodes.withIndex()) {
                result.add(OpCode.IntConstant(index))
                result.addAll(visitValueNodeThatNeedsType(valueNode, arrayType.arrayType.name))
                result.add(opCodeToStoreArrayElements)
                if (index + 1 < valueNodes.size) {
                    result.add(OpCode.Dup())
                }
            }
        } else {
            throw NotImplementedError("Array element not ValueNode")
        }
        
        
        return result
    }

    override fun visit(arrayInstantiationNode: ASTNodes.ArrayInstantiationNode): List<OpCode> {
        val result = mutableListOf<OpCode>()
        for (dimensionSize in arrayInstantiationNode.dimensionSizes) {
            result.add(OpCode.IntConstant(dimensionSize))
        }
        val numberOfDimensions = arrayInstantiationNode.dimensionSizes.size.toUByte()
        result.add(OpCode.Multianewarray(ClassConstantInfo("[".repeat(numberOfDimensions.toInt()) + getCharOfBuiltInForTypeDescriptor(arrayInstantiationNode.type)), numberOfDimensions))
        return result
    }

    override fun visit(arrayAccessNode: ASTNodes.ArrayAccessNode): List<OpCode> {
        val result = mutableListOf<OpCode>()
        result.add(OpCode.LoadArray(arrayAccessNode.array))
        for ((index, elementIndexAccessed) in arrayAccessNode.elementIndicesAccessed.withIndex()) {
            result.add(OpCode.IntConstant(elementIndexAccessed))
            if (index + 1 < arrayAccessNode.elementIndicesAccessed.size) {
                result.add(OpCode.Aaload())                
            }
        }
        val arrayTypeName = (arrayAccessNode.array.type as ArrayType).arrayType.name
        result.add(when (arrayTypeName) {
            "String" -> OpCode.Aaload()
            "boolean" -> OpCode.Baload()
            "int" -> OpCode.Iaload()
            "byte" -> OpCode.Baload()
            "short" -> OpCode.Saload()
            "char" -> OpCode.Caload()
            "long" -> OpCode.Laload()
            "double" -> OpCode.Daload()
            "float" -> OpCode.Faload()
            else -> throw NotImplementedError(arrayTypeName)
        })
        
        return result
    }

    override fun visit(objectInstantiationNode: ASTNodes.ObjectInstantiationNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(forLoopNode: ASTNodes.ForLoopNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(forEachLoopNode: ASTNodes.ForEachLoopNode?): List<OpCode> {
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
    
    private fun createIfNode(ifNode: ASTNodes.IfNode): OpCode.If {
        val expression = ifNode.expression
        val branchingOpCode: OpCode.If
        val expressionOpCodes: List<OpCode> = 
            if(expression is ASTNodes.VariableCallNode && expression.symbol.type.name == "boolean" ) { 
                branchingOpCode = OpCode.If(OpCode.Ifeq::class)
                visit(expression)
            } else if (expression is ASTNodes.ComparisonNode && expression.leftExpression is ASTNodes.VariableCallNode) {
                val rightExpression = expression.rightExpression
                if (rightExpression is ASTNodes.ValueNode<*> && rightExpression.value is Int) {
                    branchingOpCode = when(expression.comparisonOperator) {
                        ComparisonOperator.EQ -> OpCode.If(OpCode.If_icmpeq::class)
                        ComparisonOperator.NEQ -> OpCode.If(OpCode.If_icmpne::class)
                        ComparisonOperator.GTE -> OpCode.If(OpCode.If_icmplt::class)
                        ComparisonOperator.LTE -> OpCode.If(OpCode.If_icmpgt::class)
                        ComparisonOperator.DIAMOND_OPEN -> OpCode.If(OpCode.If_icmplt::class)
                        ComparisonOperator.DIAMOND_CLOSE -> OpCode.If(OpCode.If_icmpgt::class)
                        else -> throw NotImplementedError(expression.comparisonOperator.name)
                    }
                    dispatch(expression.leftExpression).plus(visit(rightExpression))
                } else {
                    throw NotImplementedError(expression.javaClass.name)
                }
            } else {
                throw NotImplementedError(expression.javaClass.name)
            }
        branchingOpCode.expressionOpCodes.addAll(expressionOpCodes)
        branchingOpCode.opCodesInsideBlockWithoutGoto.addAll(ifNode.statements.flatMap { dispatch(it) })
        return branchingOpCode
    }


    override fun visit(elseNode: ASTNodes.ElseNode): List<OpCode> {
        return elseNode.statements.flatMap { dispatch(it) }
    }

    override fun visit(ifBlockNode: ASTNodes.IfBlockNode): List<OpCode> {
         return listOf(OpCode.IfElseIfsElseBlock(ifBlockNode.ifNodes.map(::createIfNode), if(ifBlockNode.elseNode == null) { listOf() } else { visit(ifBlockNode.elseNode) }))
    }
}