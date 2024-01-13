package org.cmjava2023.astToClassFileModel

import org.cmjava2023.ast.ASTNodes.*
import org.cmjava2023.ast.ASTTraverser
import org.cmjava2023.classfilespecification.Operation
import org.cmjava2023.classfilespecification.constantpool.ConstantPoolEntry
import org.cmjava2023.classfilespecification.constantpool.TypeDescriptor
import org.cmjava2023.placeHolders.JumpOffsetPlaceHolder
import org.cmjava2023.placeHolders.LoadConstantPlaceHolder
import org.cmjava2023.placeHolders.LocalVariableIndexPlaceHolder
import org.cmjava2023.placeHolders.PlaceHolder
import org.cmjava2023.placeHolders.queries.AssignOrDeclareVariablePlaceHoldersQuery
import org.cmjava2023.placeHolders.queries.SystemOutPrintlnPlaceHoldersQuery
import org.cmjava2023.placeHolders.queries.TypeCastOpCodeQuery
import org.cmjava2023.symboltable.ArrayType
import org.cmjava2023.symboltable.BuiltInType

class AstTraverserToGetPlaceHolders : ASTTraverser<List<PlaceHolder>>() {
    private lateinit var astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack: AstTraverserToGetPlaceHoldersLeavingKnownTypeOnStack
    fun init(astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack: AstTraverserToGetPlaceHoldersLeavingKnownTypeOnStack) {
        this.astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack = astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack
    }
    override fun defaultValue(node: Node): List<PlaceHolder> = astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack.dispatch(node).placeHolders

    override fun visit(continueNodeNode: ContinueNode?): List<PlaceHolder> {
        return listOf(JumpOffsetPlaceHolder.Continue())
    }

    override fun visit(functionNode: FunctionNode): List<PlaceHolder> {
        val opCodes = functionNode.body.flatMap { dispatch(it) }
        return if (opCodes.lastOrNull() !is Operation.ReturningOpCode) {
            opCodes.plus(Operation.Return())
        } else {
            opCodes
        }
    }

    override fun visit(functionCallNode: FunctionCallNode): List<PlaceHolder> {
        return when (functionCallNode.function.name) {
            "System.out.println" -> SystemOutPrintlnPlaceHoldersQuery.fetch(functionCallNode, astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack)
            else -> defaultValue(functionCallNode)
        }
    }

    override fun visit(variableNode: VariableNode): List<PlaceHolder> {
        val variableSymbol = variableNode.variableSymbol
        val initialExpression = variableSymbol.initialExpression
        return if (initialExpression == null) {
            listOf()
        } else {
            AssignOrDeclareVariablePlaceHoldersQuery.fetch(variableSymbol, initialExpression, this)
        }
    }
    override fun visit(variableAssignmentNode: VariableAssignmentNode): List<PlaceHolder> {
        val variableSymbol = variableAssignmentNode.variable
        return AssignOrDeclareVariablePlaceHoldersQuery.fetch(variableSymbol, variableAssignmentNode.expression, this)
    }

    override fun visit(returnNode: ReturnNode): List<PlaceHolder> {
        return listOf(Operation.Return())
    }

    override fun visit(unarySuffixNode: UnarySuffixNode): List<LocalVariableIndexPlaceHolder> {
        val variableSymbol = (unarySuffixNode.variableCallNode as VariableCallNode).symbol
        return when (variableSymbol.type) {
            BuiltInType.INT -> listOf(
                LocalVariableIndexPlaceHolder.IncreaseInt(
                    variableSymbol,
                    when (unarySuffixNode.operator) {
                        SuffixOperator.INC -> 1
                        SuffixOperator.DEC -> -1
                        else -> throw NotImplementedError(unarySuffixNode.operator.name)
                    }
                )
            )
            else -> throw NotImplementedError(variableSymbol.type.name)
        }
    }

    override fun visit(castNode: CastNode): List<PlaceHolder> {
        val result = mutableListOf<PlaceHolder>()
        when (val expression = castNode.expression) {
            is VariableCallNode -> {
                result.addAll(visit(expression))
                val fromType = expression.symbol.type
                val toType = castNode.type
                result.add(TypeCastOpCodeQuery.fetch(fromType, toType))
            }
            is FunctionCallNode -> return visit(expression)
            else -> throw NotImplementedError(expression.javaClass.name)
        }
        return result
    }

    fun visit(
        arrayInstantiationWithValuesNode: ArrayInstantiationWithValuesNode, arrayType: ArrayType
    ): List<PlaceHolder> {
        val result = mutableListOf<PlaceHolder>()
        val arrayLength = arrayInstantiationWithValuesNode.expressions.size
        result.add(LoadConstantPlaceHolder.IntegerConstant(arrayLength))
        val valueNodesOrNull = arrayInstantiationWithValuesNode.expressions.map {
            if (it is ValueNode<*>) {
                it
            } else {
                null
            }
        }
        if (valueNodesOrNull.none { it == null }) {
            result.add(
                when (arrayType.arrayType) {
                    BuiltInType.STRING -> Operation.Anewarray(ConstantPoolEntry.ClassConstant.STRING)
                    BuiltInType.BOOLEAN -> Operation.Newarray(Operation.ArrayType.T_BOOLEAN)
                    BuiltInType.INT -> Operation.Newarray(Operation.ArrayType.T_INT)
                    BuiltInType.BYTE -> Operation.Newarray(Operation.ArrayType.T_BYTE)
                    BuiltInType.CHAR -> Operation.Newarray(Operation.ArrayType.T_CHAR)
                    BuiltInType.SHORT -> Operation.Newarray(Operation.ArrayType.T_SHORT)
                    BuiltInType.LONG -> Operation.Newarray(Operation.ArrayType.T_LONG)
                    BuiltInType.FLOAT -> Operation.Newarray(Operation.ArrayType.T_FLOAT)
                    BuiltInType.DOUBLE -> Operation.Newarray(Operation.ArrayType.T_DOUBLE)
                    else -> throw NotImplementedError(arrayType.arrayType.name)
                }
            )
            val operationToStoreArrayElements = when (arrayType.arrayType) {
                BuiltInType.STRING -> Operation.Aastore()
                BuiltInType.BOOLEAN -> Operation.Bastore()
                BuiltInType.INT -> Operation.Iastore()
                BuiltInType.BYTE -> Operation.Bastore()
                BuiltInType.SHORT -> Operation.Sastore()
                BuiltInType.CHAR -> Operation.Castore()
                BuiltInType.LONG -> Operation.Lastore()
                BuiltInType.DOUBLE -> Operation.Dastore()
                BuiltInType.FLOAT -> Operation.Fastore()
                else -> throw NotImplementedError(arrayType.arrayType.name)
            }
            result.add(Operation.Dup())
            val valueNodes = valueNodesOrNull.requireNoNulls()
            for ((index, valueNode) in valueNodes.withIndex()) {
                result.add(LoadConstantPlaceHolder.IntegerConstant(index))
                result.addAll(dispatch(ValueNodeTransformedToTypeQuery.fetch(valueNode, arrayType.arrayType)))
                result.add(operationToStoreArrayElements)
                if (index + 1 < valueNodes.size) {
                    result.add(Operation.Dup())
                }
            }
        } else {
            throw NotImplementedError("Array element not ValueNode")
        }


        return result
    }

    override fun visit(arrayInstantiationNode: ArrayInstantiationNode): List<PlaceHolder> {
        val result = mutableListOf<PlaceHolder>()
        for (dimensionSize in arrayInstantiationNode.dimensionSizes) {
            result.add(LoadConstantPlaceHolder.IntegerConstant(dimensionSize))
        }
        val numberOfDimensions = arrayInstantiationNode.dimensionSizes.size.toUByte()
        result.add(
            Operation.Multianewarray(
                ConstantPoolEntry.ClassConstant(TypeDescriptor.forBuildInType(arrayInstantiationNode.type)),
                numberOfDimensions
            )
        )
        return result
    }

    override fun visit(arrayAccessNode: ArrayAccessNode): List<PlaceHolder> {
        val result = mutableListOf<PlaceHolder>()
        result.add(LocalVariableIndexPlaceHolder.LoadArray(arrayAccessNode.array))

        for ((index, elementIndexAccessed) in arrayAccessNode.elementIndicesAccessed.withIndex()) {
            result.add(LoadConstantPlaceHolder.IntegerConstant(elementIndexAccessed))
            if (index + 1 < arrayAccessNode.elementIndicesAccessed.size) {
                result.add(Operation.Aaload())
            }
        }

        val arrayType = (arrayAccessNode.array.type as ArrayType).arrayType
        result.add(
            when (arrayType) {
                BuiltInType.STRING -> Operation.Aaload()
                BuiltInType.BOOLEAN -> Operation.Baload()
                BuiltInType.INT -> Operation.Iaload()
                BuiltInType.BYTE -> Operation.Baload()
                BuiltInType.SHORT -> Operation.Saload()
                BuiltInType.CHAR -> Operation.Caload()
                BuiltInType.LONG -> Operation.Laload()
                BuiltInType.DOUBLE -> Operation.Daload()
                BuiltInType.FLOAT -> Operation.Faload()
                else -> throw NotImplementedError(arrayType.name)
            }
        )

        return result
    }

}