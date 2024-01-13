package org.cmjava2023.astToClassFileModel

import org.cmjava2023.ast.ASTNodes
import org.cmjava2023.ast.ASTNodes.VariableCallNode
import org.cmjava2023.ast.ASTTraverser
import org.cmjava2023.classfilespecification.Operation
import org.cmjava2023.placeHolders.LoadConstantPlaceHolder
import org.cmjava2023.placeHolders.LocalVariableIndexPlaceHolder
import org.cmjava2023.placeHolders.PlaceHoldersLeavingKnownTypeOnStack
import org.cmjava2023.placeHolders.queries.BinaryOperatorUsagePlaceHoldersLeavingKnownTypeOnStackQuery
import org.cmjava2023.placeHolders.queries.SystemInReadPlaceHoldersLeavingKnownTypeOnStackQuery
import org.cmjava2023.symboltable.BuiltInType

class AstTraverserToGetPlaceHoldersLeavingKnownTypeOnStack : ASTTraverser<PlaceHoldersLeavingKnownTypeOnStack>() {
    private lateinit var astTraverserToGetPlaceHolders: AstTraverserToGetPlaceHolders
    
    fun init(astTraverserToGetPlaceHolders: AstTraverserToGetPlaceHolders) {
        this.astTraverserToGetPlaceHolders = astTraverserToGetPlaceHolders
    }
    
    override fun defaultValue(node: ASTNodes.Node): PlaceHoldersLeavingKnownTypeOnStack {
        throw NotImplementedError(node.javaClass.name)
    }
    
    override fun visit(functionCallNode: ASTNodes.FunctionCallNode): PlaceHoldersLeavingKnownTypeOnStack {
        return when (functionCallNode.function.name) {
            "System.in.read" -> SystemInReadPlaceHoldersLeavingKnownTypeOnStackQuery.fetch()
            else -> throw NotImplementedError(functionCallNode.function.name)
        }
    }

    override fun visit(infixNode: ASTNodes.InfixNode): PlaceHoldersLeavingKnownTypeOnStack {
        return BinaryOperatorUsagePlaceHoldersLeavingKnownTypeOnStackQuery.fetch(infixNode, astTraverserToGetPlaceHolders)
    }

    override fun visit(comparisonNode: ASTNodes.ComparisonNode): PlaceHoldersLeavingKnownTypeOnStack {
        return BinaryOperatorUsagePlaceHoldersLeavingKnownTypeOnStackQuery.fetch(comparisonNode, astTraverserToGetPlaceHolders)
    }

    override fun visit(valueNode: ASTNodes.ValueNode<*>): PlaceHoldersLeavingKnownTypeOnStack {
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

    override fun visit(unaryPrefixNode: ASTNodes.UnaryPrefixNode): PlaceHoldersLeavingKnownTypeOnStack {
        val loadVariablePlaceHoldersLeavingKnownTypeOnStack = dispatch(unaryPrefixNode.variableCallNode as VariableCallNode)
        
        return PlaceHoldersLeavingKnownTypeOnStack(loadVariablePlaceHoldersLeavingKnownTypeOnStack.placeHolders.plus(
            when (unaryPrefixNode.operator) {
                ASTNodes.PrefixOperator.MINUS -> when (val type = TypeOfExpressionQuery.fetch(unaryPrefixNode.variableCallNode)) {
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