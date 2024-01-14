package org.cmjava2023.astToClassFileData

import org.cmjava2023.ast.ASTNodes.*
import org.cmjava2023.ast.ASTTraverser
import org.cmjava2023.classfilespecification.Operation
import org.cmjava2023.placeHolders.LoadConstantPlaceHolder
import org.cmjava2023.placeHolders.PlaceHolder
import org.cmjava2023.placeHolders.PlaceHoldersLeavingKnownTypeOnStack
import org.cmjava2023.placeHolders.queries.*
import org.cmjava2023.symboltable.ArrayType
import org.cmjava2023.symboltable.BuiltInType

class AstTraverserToGetPlaceHoldersLeavingKnownTypeOnStack(
    private val loadVariableOperationQuery: LoadVariableOperationQuery
) : ASTTraverser<PlaceHoldersLeavingKnownTypeOnStack>() {
    private lateinit var astTraverserToGetPlaceHolders: AstTraverserToGetPlaceHolders

    fun init(astTraverserToGetPlaceHolders: AstTraverserToGetPlaceHolders) {
        this.astTraverserToGetPlaceHolders = astTraverserToGetPlaceHolders
    }

    override fun defaultValue(node: Node): PlaceHoldersLeavingKnownTypeOnStack {
        throw NotImplementedError(node.javaClass.name)
    }

    override fun visit(functionCallNode: FunctionCallNode): PlaceHoldersLeavingKnownTypeOnStack {
        return when (functionCallNode.function.name) {
            "System.in.read" -> SystemInReadPlaceHoldersLeavingKnownTypeOnStackQuery.fetch()
            else -> throw NotImplementedError(functionCallNode.function.name)
        }
    }

    override fun visit(arrayAccessNode: ArrayAccessNode): PlaceHoldersLeavingKnownTypeOnStack {
        val result = mutableListOf<PlaceHolder>()
        result += loadVariableOperationQuery.fetch(arrayAccessNode.array)


        for ((index, elementIndexAccessed) in arrayAccessNode.elementIndicesAccessed.withIndex()) {
            result.add(LoadConstantPlaceHolder.IntegerConstant(elementIndexAccessed))
            if (index + 1 < arrayAccessNode.elementIndicesAccessed.size) {
                result.add(Operation.Aaload())
            }
        }
        val arrayType = (arrayAccessNode.array.type as ArrayType).arrayType
        result += when (arrayType) {
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

        return PlaceHoldersLeavingKnownTypeOnStack(result, arrayType)
    }

    override fun visit(infixNode: InfixNode): PlaceHoldersLeavingKnownTypeOnStack {
        return BinaryOperatorUsagePlaceHoldersLeavingKnownTypeOnStackQuery.fetch(infixNode.leftExpression, infixNode.operator, infixNode.rightExpression, this)
    }

    override fun visit(comparisonNode: ComparisonNode): PlaceHoldersLeavingKnownTypeOnStack {
        return BinaryOperatorUsagePlaceHoldersLeavingKnownTypeOnStackQuery.fetch(comparisonNode.leftExpression, comparisonNode.operator, comparisonNode.rightExpression, this)
    }

    override fun visit(castNode: CastNode): PlaceHoldersLeavingKnownTypeOnStack {
        val result = mutableListOf<PlaceHolder>()
        when (val expression = castNode.expression) {
            is VariableCallNode -> {
                result.addAll(visit(expression).placeHolders)
                val fromType = expression.symbol.type
                val toType = castNode.type
                result.add(TypeCastOpCodeQuery.fetch(fromType, toType))
            }
            is FunctionCallNode -> return visit(expression)
            else -> throw NotImplementedError(expression.javaClass.name)
        }
        return PlaceHoldersLeavingKnownTypeOnStack(result, castNode.type)
    }

    override fun visit(valueNode: ValueNode<*>): PlaceHoldersLeavingKnownTypeOnStack {
        return PlaceHoldersLeavingKnownTypeOnStack(listOf(LoadConstantPlaceHolder.fromValue(valueNode.value)), valueNode.builtInType)
    }

    override fun visit(variableCallNode: VariableCallNode): PlaceHoldersLeavingKnownTypeOnStack {
        return PlaceHoldersLeavingKnownTypeOnStack(
            listOf(loadVariableOperationQuery.fetch(variableCallNode.symbol)),
            variableCallNode.symbol.type
        )
    }

    override fun visit(unaryPrefixNode: UnaryPrefixNode): PlaceHoldersLeavingKnownTypeOnStack {
        val loadVariablePlaceHoldersLeavingKnownTypeOnStack = dispatch(unaryPrefixNode.variableCallNode() as VariableCallNode)

        return PlaceHoldersLeavingKnownTypeOnStack(
            loadVariablePlaceHoldersLeavingKnownTypeOnStack.placeHolders.plus(
                when (unaryPrefixNode.operator) {
                    PrefixOperator.MINUS -> when (loadVariablePlaceHoldersLeavingKnownTypeOnStack.type) {
                        BuiltInType.INT -> Operation.Ineg()
                        BuiltInType.LONG -> Operation.Lneg()
                        BuiltInType.FLOAT -> Operation.Fneg()
                        BuiltInType.DOUBLE -> Operation.Dneg()
                        else -> throw NotImplementedError(loadVariablePlaceHoldersLeavingKnownTypeOnStack.type.name)
                    }

                    else -> throw NotImplementedError(unaryPrefixNode.operator.name)
                }
            ),
            loadVariablePlaceHoldersLeavingKnownTypeOnStack.type
        )
    }
}