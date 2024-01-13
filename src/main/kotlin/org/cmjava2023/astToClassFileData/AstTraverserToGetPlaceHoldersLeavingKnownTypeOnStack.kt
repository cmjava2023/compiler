package org.cmjava2023.astToClassFileData

import org.cmjava2023.ast.ASTNodes.*
import org.cmjava2023.ast.ASTNodes.VariableCallNode
import org.cmjava2023.ast.ASTTraverser
import org.cmjava2023.classfilespecification.Operation
import org.cmjava2023.placeHolders.LoadConstantPlaceHolder
import org.cmjava2023.placeHolders.LocalVariableIndexPlaceHolder
import org.cmjava2023.placeHolders.PlaceHolder
import org.cmjava2023.placeHolders.PlaceHoldersLeavingKnownTypeOnStack
import org.cmjava2023.placeHolders.queries.BinaryOperatorUsagePlaceHoldersLeavingKnownTypeOnStackQuery
import org.cmjava2023.placeHolders.queries.SystemInReadPlaceHoldersLeavingKnownTypeOnStackQuery
import org.cmjava2023.placeHolders.queries.TypeCastOpCodeQuery
import org.cmjava2023.symboltable.ArrayType
import org.cmjava2023.symboltable.BuiltInType

class AstTraverserToGetPlaceHoldersLeavingKnownTypeOnStack : ASTTraverser<PlaceHoldersLeavingKnownTypeOnStack>() {
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

        return PlaceHoldersLeavingKnownTypeOnStack(result, arrayType)
    }

    override fun visit(infixNode: InfixNode): PlaceHoldersLeavingKnownTypeOnStack {
        return BinaryOperatorUsagePlaceHoldersLeavingKnownTypeOnStackQuery.fetch(infixNode, this)
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

    override fun visit(comparisonNode: ComparisonNode): PlaceHoldersLeavingKnownTypeOnStack {
        return BinaryOperatorUsagePlaceHoldersLeavingKnownTypeOnStackQuery.fetch(comparisonNode, this)
    }

    override fun visit(valueNode: ValueNode<*>): PlaceHoldersLeavingKnownTypeOnStack {
        return PlaceHoldersLeavingKnownTypeOnStack(listOf(LoadConstantPlaceHolder.fromValue(valueNode.value)), valueNode.builtInType)
    }

    override fun visit(variableCallNode: VariableCallNode): PlaceHoldersLeavingKnownTypeOnStack {
        val variableType = variableCallNode.symbol.type
        return PlaceHoldersLeavingKnownTypeOnStack(listOf(
            when (variableType) {
                BuiltInType.INT -> LocalVariableIndexPlaceHolder.LoadInt(variableCallNode.symbol)
                BuiltInType.LONG -> LocalVariableIndexPlaceHolder.LoadLong(variableCallNode.symbol)
                BuiltInType.FLOAT -> LocalVariableIndexPlaceHolder.LoadFloat(variableCallNode.symbol)
                BuiltInType.DOUBLE -> LocalVariableIndexPlaceHolder.LoadDouble(variableCallNode.symbol)
                BuiltInType.BOOLEAN -> LocalVariableIndexPlaceHolder.LoadInt(variableCallNode.symbol)
                BuiltInType.CHAR -> LocalVariableIndexPlaceHolder.LoadInt(variableCallNode.symbol)
                BuiltInType.BYTE -> LocalVariableIndexPlaceHolder.LoadInt(variableCallNode.symbol)
                BuiltInType.SHORT -> LocalVariableIndexPlaceHolder.LoadInt(variableCallNode.symbol)
                else -> throw NotImplementedError(variableCallNode.symbol.type.name)
            }),
            variableType
        )
    }

    override fun visit(unaryPrefixNode: UnaryPrefixNode): PlaceHoldersLeavingKnownTypeOnStack {
        val loadVariablePlaceHoldersLeavingKnownTypeOnStack = dispatch(unaryPrefixNode.variableCallNode() as VariableCallNode)
        
        return PlaceHoldersLeavingKnownTypeOnStack(loadVariablePlaceHoldersLeavingKnownTypeOnStack.placeHolders.plus(
            when (unaryPrefixNode.operator) {
                PrefixOperator.MINUS -> when (val type = TypeOfExpressionQuery.fetch(unaryPrefixNode.variableCallNode())) {
                    BuiltInType.INT -> Operation.Ineg()
                    BuiltInType.LONG -> Operation.Lneg()
                    BuiltInType.FLOAT -> Operation.Fneg()
                    BuiltInType.DOUBLE -> Operation.Dneg()
                    else -> throw NotImplementedError(type.name)
                }
                else -> throw NotImplementedError(unaryPrefixNode.operator.name)
            }
        ),
            loadVariablePlaceHoldersLeavingKnownTypeOnStack.type
        )
    }
}