package org.cmjava2023.astToClassFileModel

import org.cmjava2023.ast.ASTNodes
import org.cmjava2023.ast.ASTNodes.ComparisonOperator
import org.cmjava2023.ast.ASTTraverser
import org.cmjava2023.classfilespecification.constantpool.*
import org.cmjava2023.classfilespecification.opCodes.*
import org.cmjava2023.classfilespecification.opCodes.OpCodeOrPlaceHolder.Companion.assureAllAreFinalOpCodes
import org.cmjava2023.classfilespecification.opCodes.jumps.ComparisonData
import org.cmjava2023.classfilespecification.opCodes.jumps.ComparisonType
import org.cmjava2023.classfilespecification.opCodes.jumps.PlaceHolderIfElseIfsElse
import org.cmjava2023.classfilespecification.opCodes.jumps.PlaceHolderWhile
import org.cmjava2023.classfilespecification.opCodes.queries.*
import org.cmjava2023.symboltable.ArrayType
import org.cmjava2023.symboltable.BuiltIn
import org.cmjava2023.symboltable.BuiltInType
import org.cmjava2023.symboltable.Type
import org.cmjava2023.symboltable.Variable
import org.cmjava2023.util.OpCodesToConcatStringLiteralAndVariableQuery

class FunctionCodeAstTraverser : ASTTraverser<List<OpCodeOrPlaceHolder>>() {
    override fun visit(startNode: ASTNodes.StartNode): List<OpCodeOrPlaceHolder> {
        throw NotImplementedError()
    }

    override fun visit(packageNode: ASTNodes.PackageNode): List<OpCodeOrPlaceHolder> {
        throw NotImplementedError()
    }

    override fun visit(classNode: ASTNodes.ClassNode): List<OpCodeOrPlaceHolder> {
        throw NotImplementedError()
    }

    override fun visit(continueNodeNode: ASTNodes.ContinueNode?): List<OpCodeOrPlaceHolder> {
        return listOf(PlaceHolderUsingJumpOffset.Continue())
    }

    override fun visit(breakNode: ASTNodes.BreakNode?): List<OpCodeOrPlaceHolder> {
        TODO("Not yet implemented")
    }

    override fun visit(enumNode: ASTNodes.EnumNode?): List<OpCodeOrPlaceHolder> {
        TODO("Not yet implemented")
    }

    override fun visit(switchNode: ASTNodes.SwitchNode?): List<OpCodeOrPlaceHolder> {
        TODO("Not yet implemented")
    }

    override fun visit(caseNode: ASTNodes.CaseNode?): List<OpCodeOrPlaceHolder> {
        TODO("Not yet implemented")
    }

    override fun visit(functionNode: ASTNodes.FunctionNode): List<OpCodeOrPlaceHolder> {
        val opCodes = functionNode.body.flatMap { dispatch(it) }
        return if (opCodes.lastOrNull() !is OpCode.ReturningOpCode) {
            opCodes.plus(OpCode.Return())
        } else {
            opCodes
        }
    }

    override fun visit(node: ASTNodes.ParameterNode?): List<OpCodeOrPlaceHolder> {
        TODO("Not yet implemented")
    }

    override fun visit(parameterCallNode: ASTNodes.ParameterCallNode?): List<OpCodeOrPlaceHolder> {
        TODO("Not yet implemented")
    }

    override fun visit(functionCallNode: ASTNodes.FunctionCallNode): List<OpCodeOrPlaceHolder> {
        when (functionCallNode.function.name) {
            "System.out.println" -> {
                val fieldReferenceConstantInfo = FieldReferenceConstantPoolEntry(
                    ClassConstantPoolEntry(TypeDescriptorHelper.STRING), NameAndTypeConstantPoolEntry(
                        "out",
                        TypeDescriptorHelper.createForClassName(TypeDescriptorHelper.PRINT_STREAM)
                    )
                )

                val parameterTypeCode: String
                val expr = functionCallNode.values[0]
                val opcOdesLoadingWhatToPrint = when {
                    expr is ASTNodes.ValueNode<*> -> {
                        parameterTypeCode = TypeDescriptorHelper.map(BuiltInType.typeOf(expr.value))
                        visit(expr)
                    }

                    expr is ASTNodes.InfixNode && expr.operator == ASTNodes.InfixOperator.PLUS && expr.leftExpression is ASTNodes.ValueNode<*> && (expr.leftExpression as ASTNodes.ValueNode<*>).value is String -> {
                        parameterTypeCode = TypeDescriptorHelper.createForClassName(TypeDescriptorHelper.STRING)
                        visit(expr)
                    }
                    expr is ASTNodes.VariableCallNode -> {
                        parameterTypeCode = TypeDescriptorHelper.map(expr.symbol.type)
                        visit(expr)
                    }

                    expr is ASTNodes.ArrayAccessNode -> {
                        val arrayType = (expr.array.type as ArrayType).arrayType
                        if (arrayType == BuiltInType.STRING) {
                            throw NotImplementedError("String Arrays")
                        } else {
                            parameterTypeCode = TypeDescriptorHelper.map(arrayType)
                        }
                        visit(expr)
                    }

                    else -> {
                        throw NotImplementedError(expr.javaClass.name)
                    }
                }
                val methodReferenceConstantInfo = MethodReferenceConstantPoolEntry(
                    ClassConstantPoolEntry(TypeDescriptorHelper.PRINT_STREAM),
                    NameAndTypeConstantPoolEntry(
                        TypeDescriptorHelper.STRING,
                        TypeDescriptorHelper.createVoidMethod(parameterTypeCode)
                    )
                )

                val result = mutableListOf<OpCodeOrPlaceHolder>()
                result.add(OpCode.Getstatic(fieldReferenceConstantInfo))
                result.addAll(opcOdesLoadingWhatToPrint)
                result.add(OpCode.Invokevirtual(methodReferenceConstantInfo))
                
                return result
            }

            "System.in.read" -> return listOf(
                OpCode.Getstatic(
                    FieldReferenceConstantPoolEntry(
                        ClassConstantPoolEntry("java/lang/System"), NameAndTypeConstantPoolEntry("in", TypeDescriptorHelper.createForClassName("java/io/InputStream"))
                    )
                ), OpCode.Invokevirtual(
                    MethodReferenceConstantPoolEntry(
                        ClassConstantPoolEntry("java/io/InputStream"), NameAndTypeConstantPoolEntry("read", TypeDescriptorHelper.createMethodReturning1with2toNAsParameterTypes(TypeDescriptorHelper.map(BuiltInType.INT)))
                    )
                ), OpCode.I2c()
            )

            else -> {
                throw NotImplementedError()
            }
        }
    }

    override fun visit(infixNode: ASTNodes.InfixNode): List<OpCodeOrPlaceHolder> {
        val leftExpression = infixNode.leftExpression
        val rightExpression = infixNode.rightExpression
        return if (infixNode.operator == ASTNodes.InfixOperator.PLUS && leftExpression is ASTNodes.ValueNode<*> && leftExpression.value is String && rightExpression is ASTNodes.VariableCallNode) {
            OpCodesToConcatStringLiteralAndVariableQuery.fetch(
                leftExpression.value as String,
                visit(rightExpression),
                TypeDescriptorHelper.map(rightExpression.symbol.type)
            )
        } else {
            val leftType = getTypeOf(leftExpression)
            val rightType = getTypeOf(rightExpression)

            if (leftType is BuiltInType && leftType == rightType || rightExpression is ASTNodes.ValueNode<*>) {
                dispatch(leftExpression).plus(
                    if (rightExpression is ASTNodes.ValueNode<*>) {
                        visitValueNodeThatNeedsType(rightExpression, leftType)
                    } else {
                        dispatch(rightExpression)
                    }
                ).plus(
                    when (leftType) {
                        BuiltInType.INT -> infixBothInt(infixNode.operator)
                        BuiltInType.LONG -> infixBothLong(infixNode.operator)
                        BuiltInType.FLOAT -> infixBothFloat(infixNode.operator)
                        BuiltInType.DOUBLE -> infixBothDouble(infixNode.operator)
                        else -> throw NotImplementedError()
                    }
                )
            } else {
                throw NotImplementedError("$leftType $rightType")
            }
        }
    }

    private fun getTypeOf(expression: ASTNodes.Expression): Type {
        return when (expression) {
            is ASTNodes.VariableCallNode -> expression.symbol.type
            is ASTNodes.ValueNode<*> -> BuiltInType.typeOf(expression.value)
            else -> throw NotImplementedError(expression.javaClass.name)
        }
    }

    private fun infixBothInt(operator: ASTNodes.InfixOperator): List<OpCodeOrPlaceHolder> {
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

    private fun infixBothLong(operator: ASTNodes.InfixOperator): List<OpCodeOrPlaceHolder> {
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

    private fun infixBothFloat(operator: ASTNodes.InfixOperator): List<OpCodeOrPlaceHolder> {
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

    private fun infixBothDouble(operator: ASTNodes.InfixOperator): List<OpCodeOrPlaceHolder> {
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

    override fun visit(ifNode: ASTNodes.IfNode): List<OpCodeOrPlaceHolder> {
        throw NotImplementedError()
    }

    override fun visit(variableNode: ASTNodes.VariableNode): List<OpCodeOrPlaceHolder> {
        val variableSymbol = variableNode.variableSymbol
        val value = variableSymbol.initialExpression
        return if (value == null) {
            listOf()
        } else {
            assignOrDeclareVariable(variableSymbol, value)
        }
    }

    private fun assignOrDeclareVariable(
        variableSymbol: Variable, value: ASTNodes.Expression
    ): List<OpCodeOrPlaceHolder> {
        val opCodesLoadingExpressionValueOnStack =
            if (value is ASTNodes.ValueNode<*> && getTypeOf(value).name != variableSymbol.type.name) {
                visitValueNodeThatNeedsType(value, variableSymbol.type)
            } else if (variableSymbol.type is ArrayType && value is ASTNodes.ArrayInstantiationWithValuesNode) {
                visit(value, variableSymbol.type as ArrayType)
            } else {
                dispatch(value)
            }
        val storingOpCode = storeStackInVar(variableSymbol)
        return opCodesLoadingExpressionValueOnStack.plus(storingOpCode)
    }

    private fun FunctionCodeAstTraverser.visitValueNodeThatNeedsType(
        valueNode: ASTNodes.ValueNode<*>, typeItNeeds: Type
    ): List<OpCodeOrPlaceHolder> {
        val valueAsString = valueNode.value.toString()
        return visit(
            ASTNodes.ValueNode(
                when (typeItNeeds) {
                    BuiltInType.INT -> valueAsString.toInt()
                    BuiltInType.LONG -> valueAsString.toLong()
                    BuiltInType.FLOAT -> valueAsString.toFloat()
                    BuiltInType.DOUBLE -> valueAsString.toDouble()
                    BuiltInType.BOOLEAN -> valueAsString.toBoolean()
                    BuiltInType.CHAR -> {
                        when (valueNode.value) {
                            is Char -> valueNode.value
                            is Int -> Char(valueNode.value as Int)
                            else -> throw NotImplementedError(valueNode.value.javaClass.name)
                        }
                    }

                    BuiltInType.BYTE -> ASTNodes.ValueNode(valueAsString.toByte())
                    BuiltInType.SHORT -> ASTNodes.ValueNode(valueAsString.toShort())
                    BuiltInType.STRING -> ASTNodes.ValueNode(valueAsString)
                    else -> throw NotImplementedError(typeItNeeds.javaClass.name)
                }
            )
        )
    }

    private fun storeStackInVar(variableSymbol: Variable): PlaceHolderUsingLocalVariableIndex {
        return when (variableSymbol.type) {
            is ArrayType -> PlaceHolderUsingLocalVariableIndex.StoreArray(variableSymbol)
            is BuiltIn -> when (variableSymbol.type.name) {
                "int" -> PlaceHolderUsingLocalVariableIndex.StoreInt(variableSymbol)
                "long" -> PlaceHolderUsingLocalVariableIndex.StoreLong(variableSymbol)
                "float" -> PlaceHolderUsingLocalVariableIndex.StoreFloat(variableSymbol)
                "double" -> PlaceHolderUsingLocalVariableIndex.StoreDouble(variableSymbol)
                "boolean" -> PlaceHolderUsingLocalVariableIndex.StoreInt(variableSymbol)
                "char" -> PlaceHolderUsingLocalVariableIndex.StoreInt(variableSymbol)
                "byte" -> PlaceHolderUsingLocalVariableIndex.StoreInt(variableSymbol)
                "short" -> PlaceHolderUsingLocalVariableIndex.StoreInt(variableSymbol)
                else -> throw NotImplementedError("typename:${variableSymbol.type.name}")
            }

            else -> throw NotImplementedError("typename:${variableSymbol.type.javaClass.name}")
        }

    }

    override fun visit(variableAssignmentNode: ASTNodes.VariableAssignmentNode): List<OpCodeOrPlaceHolder> {
        val variableSymbol = variableAssignmentNode.variable
        val value = variableAssignmentNode.expression
        return assignOrDeclareVariable(variableSymbol, value)
    }

    override fun visit(valueNode: ASTNodes.ValueNode<*>): List<OpCodeOrPlaceHolder> {
        val value = valueNode.value
        return listOf(
            when (value) {
                is String -> PlaceHolderLoadConstantPoolItem(StringConstantPoolEntry(value))
                is Boolean -> OpCodeToPutBooleanOnStackQuery.fetch(value)
                is Char -> OpCodeToPutIntegerOnStackQuery.fetch(value.digitToInt())
                is Byte -> OpCodeToPutIntegerOnStackQuery.fetch(value.toInt())
                is Short -> OpCodeToPutIntegerOnStackQuery.fetch(value.toInt())
                is Int -> OpCodeToPutIntegerOnStackQuery.fetch(value)
                is Long -> OpCodeToPutLongOnStackQuery.fetch(value)
                is Float -> OpCodeToPutFloatOnStackQuery.fetch(value)
                is Double -> OpCodeToPutDoubleOnStackQuery.fetch(value)
                else -> throw NotImplementedError("type${value.javaClass.name}")
            }
        )
    }

    override fun visit(nestedIdentifierNode: ASTNodes.NestedIdentifierNode): List<OpCodeOrPlaceHolder> {
        TODO("Not yet implemented")
    }

    override fun visit(comparisonNode: ASTNodes.ComparisonNode): List<OpCodeOrPlaceHolder> {
        val leftExpression = comparisonNode.leftExpression
        val rightExpression = comparisonNode.rightExpression
        val result = mutableListOf<OpCodeOrPlaceHolder>()
        if (leftExpression is ASTNodes.VariableCallNode && rightExpression is ASTNodes.ValueNode<*>) {
            result.addAll(visit(leftExpression))
            if (comparisonNode.comparisonOperator == ComparisonOperator.BAND || comparisonNode.comparisonOperator == ComparisonOperator.BOR || comparisonNode.comparisonOperator == ComparisonOperator.BXOR) {
                result.addAll(visitValueNodeThatNeedsType(rightExpression, leftExpression.symbol.type))
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

    override fun visit(variableCallNode: ASTNodes.VariableCallNode): List<OpCodeOrPlaceHolder> {
        return listOf(
            when (variableCallNode.symbol.type.name) {
                "int" -> PlaceHolderUsingLocalVariableIndex.LoadInt(variableCallNode.symbol)
                "long" -> PlaceHolderUsingLocalVariableIndex.LoadLong(variableCallNode.symbol)
                "float" -> PlaceHolderUsingLocalVariableIndex.LoadFloat(variableCallNode.symbol)
                "double" -> PlaceHolderUsingLocalVariableIndex.LoadDouble(variableCallNode.symbol)
                "boolean" -> PlaceHolderUsingLocalVariableIndex.LoadInt(variableCallNode.symbol)
                "char" -> PlaceHolderUsingLocalVariableIndex.LoadInt(variableCallNode.symbol)
                "byte" -> PlaceHolderUsingLocalVariableIndex.LoadInt(variableCallNode.symbol)
                "short" -> PlaceHolderUsingLocalVariableIndex.LoadInt(variableCallNode.symbol)
                else -> throw NotImplementedError("typename:${variableCallNode.symbol.type.name}")
            }
        )
    }

    override fun visit(returnNode: ASTNodes.ReturnNode): List<OpCodeOrPlaceHolder> {
        return listOf(OpCode.Return())
    }

    override fun visit(typeNode: ASTNodes.TypeNode): List<OpCodeOrPlaceHolder> {
        TODO("Not yet implemented")
    }

    override fun visit(arrayTypeNode: ASTNodes.ArrayTypeNode): List<OpCodeOrPlaceHolder> {
        TODO("Not yet implemented")
    }

    override fun visit(parameterAssignmentNode: ASTNodes.ParameterAssignmentNode?): List<OpCodeOrPlaceHolder> {
        TODO("Not yet implemented")
    }

    override fun visit(unaryPrefixNode: ASTNodes.UnaryPrefixNode): List<OpCodeOrPlaceHolder> {
        val type = getTypeOf(unaryPrefixNode.Expression)
        return dispatch(unaryPrefixNode.Expression).plus(
            when (type) {
                BuiltInType.INT -> when (unaryPrefixNode.operator) {
                    ASTNodes.PrefixOperator.MINUS -> OpCode.Ineg()
                    else -> throw NotImplementedError(unaryPrefixNode.operator.name)
                }

                BuiltInType.LONG -> when (unaryPrefixNode.operator) {
                    ASTNodes.PrefixOperator.MINUS -> OpCode.Lneg()
                    else -> throw NotImplementedError(unaryPrefixNode.operator.name)
                }

                BuiltInType.FLOAT -> when (unaryPrefixNode.operator) {
                    ASTNodes.PrefixOperator.MINUS -> OpCode.Fneg()
                    else -> throw NotImplementedError(unaryPrefixNode.operator.name)
                }

                BuiltInType.DOUBLE -> when (unaryPrefixNode.operator) {
                    ASTNodes.PrefixOperator.MINUS -> OpCode.Dneg()
                    else -> throw NotImplementedError(unaryPrefixNode.operator.name)
                }

                else -> throw NotImplementedError(type.name)
            }
        )
    }

    override fun visit(unarySuffixNode: ASTNodes.UnarySuffixNode): List<PlaceHolderUsingLocalVariableIndex> {
        val expression = unarySuffixNode.Expression
        return if (expression is ASTNodes.VariableCallNode) {
            when (expression.symbol.type.name) {
                "int" -> listOf(
                    when (unarySuffixNode.operator) {
                        ASTNodes.SuffixOperator.INC -> PlaceHolderUsingLocalVariableIndex.IncreaseInt(
                            expression.symbol, 1
                        )

                        ASTNodes.SuffixOperator.DEC -> PlaceHolderUsingLocalVariableIndex.IncreaseInt(
                            expression.symbol, -1
                        )

                        else -> throw NotImplementedError(unarySuffixNode.operator.name)
                    }
                )

                else -> throw NotImplementedError(expression.symbol.type.name)
            }
        } else throw NotImplementedError(unarySuffixNode.Expression.javaClass.name)
    }

    override fun visit(parenthesesNode: ASTNodes.ParenthesesNode?): List<OpCodeOrPlaceHolder> {
        TODO("Not yet implemented")
    }

    override fun visit(castNode: ASTNodes.CastNode): List<OpCodeOrPlaceHolder> {
        val result = mutableListOf<OpCodeOrPlaceHolder>()
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

    override fun visit(arrayInstantiationWithValuesNode: ASTNodes.ArrayInstantiationWithValuesNode): List<OpCodeOrPlaceHolder> {
        TODO("Not yet implemented")
    }

    fun visit(
        arrayInstantiationWithValuesNode: ASTNodes.ArrayInstantiationWithValuesNode, arrayType: ArrayType
    ): List<OpCodeOrPlaceHolder> {
        val result = mutableListOf<OpCodeOrPlaceHolder>()
        val arrayLength = arrayInstantiationWithValuesNode.expressions.size
        result.add(OpCodeToPutIntegerOnStackQuery.fetch(arrayLength))
        val valueNodesOrNull = arrayInstantiationWithValuesNode.expressions.map {
            if (it is ASTNodes.ValueNode<*>) {
                it
            } else {
                null
            }
        }
        if (valueNodesOrNull.none { it == null }) {
            result.add(
                when (arrayType.arrayType.name) {
                    "String" -> OpCode.Anewarray(ClassConstantPoolEntry("java/lang/String"))
                    "boolean" -> OpCode.Newarray(OpCode.ArrayType.T_BOOLEAN)
                    "int" -> OpCode.Newarray(OpCode.ArrayType.T_INT)
                    "byte" -> OpCode.Newarray(OpCode.ArrayType.T_BYTE)
                    "char" -> OpCode.Newarray(OpCode.ArrayType.T_CHAR)
                    "short" -> OpCode.Newarray(OpCode.ArrayType.T_SHORT)
                    "long" -> OpCode.Newarray(OpCode.ArrayType.T_LONG)
                    "float" -> OpCode.Newarray(OpCode.ArrayType.T_FLOAT)
                    "double" -> OpCode.Newarray(OpCode.ArrayType.T_DOUBLE)
                    else -> throw NotImplementedError(arrayType.arrayType.name)
                }
            )
            val opCodeToStoreArrayElements = when (arrayType.arrayType.name) {
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
                result.add(OpCodeToPutIntegerOnStackQuery.fetch(index))
                result.addAll(visitValueNodeThatNeedsType(valueNode, arrayType.arrayType))
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

    override fun visit(arrayInstantiationNode: ASTNodes.ArrayInstantiationNode): List<OpCodeOrPlaceHolder> {
        val result = mutableListOf<OpCodeOrPlaceHolder>()
        for (dimensionSize in arrayInstantiationNode.dimensionSizes) {
            result.add(OpCodeToPutIntegerOnStackQuery.fetch(dimensionSize))
        }
        val numberOfDimensions = arrayInstantiationNode.dimensionSizes.size.toUByte()
        result.add(
            OpCode.Multianewarray(
                ClassConstantPoolEntry(
                    TypeDescriptorHelper.asArrayOfDimension(
                        TypeDescriptorHelper.map(
                            arrayInstantiationNode.type
                        ), numberOfDimensions.toInt()
                    )
                ), numberOfDimensions
            )
        )
        return result
    }

    override fun visit(arrayAccessNode: ASTNodes.ArrayAccessNode): List<OpCodeOrPlaceHolder> {
        val result = mutableListOf<OpCodeOrPlaceHolder>()
        result.add(PlaceHolderUsingLocalVariableIndex.LoadArray(arrayAccessNode.array))
        for ((index, elementIndexAccessed) in arrayAccessNode.elementIndicesAccessed.withIndex()) {
            result.add(OpCodeToPutIntegerOnStackQuery.fetch(elementIndexAccessed))
            if (index + 1 < arrayAccessNode.elementIndicesAccessed.size) {
                result.add(OpCode.Aaload())
            }
        }
        val arrayType = (arrayAccessNode.array.type as ArrayType).arrayType
        result.add(
            when (arrayType) {
                BuiltInType.STRING -> OpCode.Aaload()
                BuiltInType.BOOLEAN -> OpCode.Baload()
                BuiltInType.INT -> OpCode.Iaload()
                BuiltInType.BYTE -> OpCode.Baload()
                BuiltInType.SHORT -> OpCode.Saload()
                BuiltInType.CHAR -> OpCode.Caload()
                BuiltInType.LONG -> OpCode.Laload()
                BuiltInType.DOUBLE -> OpCode.Daload()
                BuiltInType.FLOAT -> OpCode.Faload()
                else -> throw NotImplementedError(arrayType.name)
            }
        )

        return result
    }

    override fun visit(objectInstantiationNode: ASTNodes.ObjectInstantiationNode?): List<OpCodeOrPlaceHolder> {
        TODO("Not yet implemented")
    }

    override fun visit(forLoopNode: ASTNodes.ForLoopNode?): List<OpCodeOrPlaceHolder> {
        TODO("Not yet implemented")
    }

    override fun visit(forEachLoopNode: ASTNodes.ForEachLoopNode?): List<OpCodeOrPlaceHolder> {
        TODO("Not yet implemented")
    }

    override fun visit(whileLoopNode: ASTNodes.WhileLoopNode): List<OpCodeOrPlaceHolder> {
        return listOf<OpCodeOrPlaceHolder>(
            PlaceHolderWhile(
                createComparisonData(whileLoopNode.expression),
                whileLoopNode.body.flatMap { dispatch(it) })
        )
    }

    override fun visit(doWhileLoopNode: ASTNodes.DoWhileLoopNode?): List<OpCodeOrPlaceHolder> {
        TODO("Not yet implemented")
    }

    override fun visit(operatorNode: ASTNodes.OperatorNode?): List<OpCodeOrPlaceHolder> {
        TODO("Not yet implemented")
    }

    override fun visit(variableFieldCallNode: ASTNodes.VariableFieldCallNode?): List<OpCodeOrPlaceHolder> {
        TODO("Not yet implemented")
    }

    override fun visit(variableFunctionCallNode: ASTNodes.VariableFunctionCallNode?): List<OpCodeOrPlaceHolder> {
        TODO("Not yet implemented")
    }

    private fun createComparisonData(expressionThatShouldReturnBoolean: ASTNodes.Expression): ComparisonData {
        return if (expressionThatShouldReturnBoolean is ASTNodes.VariableCallNode && expressionThatShouldReturnBoolean.symbol.type.name == "boolean") {
            val opCodesLoadingDataForComparison = visit(expressionThatShouldReturnBoolean).assureAllAreFinalOpCodes()
            ComparisonData(
                opCodesLoadingDataForComparison,
                ComparisonType.WithZeroForBooleans,
                ComparisonOperator.EQ
            )
        } else if (expressionThatShouldReturnBoolean is ASTNodes.ComparisonNode) {
            val leftExpression = expressionThatShouldReturnBoolean.leftExpression
            val rightExpression = expressionThatShouldReturnBoolean.rightExpression
            if (leftExpression is ASTNodes.VariableCallNode && rightExpression is ASTNodes.ValueNode<*> && rightExpression.value is Int) {
                val opCodesLoadingDataForComparison =
                    dispatch(expressionThatShouldReturnBoolean.leftExpression).plus(visit(rightExpression))
                        .assureAllAreFinalOpCodes()
                ComparisonData(
                    opCodesLoadingDataForComparison,
                    ComparisonType.BetweenTwoIntegers,
                    expressionThatShouldReturnBoolean.comparisonOperator
                )
            } else {
                throw NotImplementedError("left:" + leftExpression.javaClass.name + " right:" + rightExpression.javaClass.name)
            }
        } else {
            throw NotImplementedError(expressionThatShouldReturnBoolean.javaClass.name)
        }
    }

    private fun createIfNode(ifNode: ASTNodes.IfNode): PlaceHolderIfElseIfsElse.If {
        return PlaceHolderIfElseIfsElse.If(
            createComparisonData(ifNode.expression),
            ifNode.statements.flatMap { dispatch(it) })
    }


    override fun visit(elseNode: ASTNodes.ElseNode): List<OpCodeOrPlaceHolder> {
        return elseNode.statements.flatMap { dispatch(it) }
    }

    override fun visit(ifBlockNode: ASTNodes.IfBlockNode): List<OpCodeOrPlaceHolder> {
        return listOf(
            PlaceHolderIfElseIfsElse(
                ifBlockNode.ifNodes.map(::createIfNode), if (ifBlockNode.elseNode == null) {
                    listOf()
                } else {
                    visit(ifBlockNode.elseNode)
                }
            )
        )
    }
}