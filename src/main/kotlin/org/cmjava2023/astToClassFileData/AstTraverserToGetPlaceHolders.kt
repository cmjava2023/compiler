package org.cmjava2023.astToClassFileData

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
        val variableSymbol = (unarySuffixNode.variableCallNode() as VariableCallNode).symbol
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

    override fun visit(arrayInstantiationNode: ArrayInstantiationNode): List<PlaceHolder> {
        val result = mutableListOf<PlaceHolder>()
        for (dimensionSize in arrayInstantiationNode.dimensionSizes) {
            result.add(LoadConstantPlaceHolder.IntegerConstant(dimensionSize))
        }
        val numberOfDimensions = arrayInstantiationNode.dimensionSizes.size
        result.add(
            Operation.Multianewarray(
                ConstantPoolEntry.ClassConstant.arrayWithDimension( TypeDescriptor.createForBuildInType(arrayInstantiationNode.type).stringRepresentation, numberOfDimensions),
                numberOfDimensions.toUByte()
            )
        )
        return result
    }
}