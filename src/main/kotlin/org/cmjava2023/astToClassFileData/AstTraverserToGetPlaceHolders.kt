package org.cmjava2023.astToClassFileData

import org.cmjava2023.ast.ASTNodes.*
import org.cmjava2023.ast.ASTTraverser
import org.cmjava2023.classfilespecification.Operation
import org.cmjava2023.classfilespecification.constantpool.ConstantPoolEntry
import org.cmjava2023.classfilespecification.constantpool.TypeDescriptor
import org.cmjava2023.placeHolders.LoadConstantPlaceHolder
import org.cmjava2023.placeHolders.LocalVariableIndexPlaceHolder
import org.cmjava2023.placeHolders.PlaceHolder
import org.cmjava2023.placeHolders.jumps.IfElseIfsElsePlaceHolder
import org.cmjava2023.placeHolders.jumps.JumpOffsetPlaceHolder
import org.cmjava2023.placeHolders.jumps.JumpOffsetPlaceHolder.JumpTargetIfFalse
import org.cmjava2023.placeHolders.jumps.LoopPlaceHolder
import org.cmjava2023.placeHolders.queries.AssignOrDeclareVariablePlaceHoldersQuery
import org.cmjava2023.placeHolders.queries.JumpIfComparisonPlaceHoldersQuery
import org.cmjava2023.placeHolders.queries.SystemOutPrintlnPlaceHoldersQuery
import org.cmjava2023.symboltable.ArrayType
import org.cmjava2023.symboltable.BuiltInType

class AstTraverserToGetPlaceHolders : ASTTraverser<List<PlaceHolder>>() {
    private lateinit var astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack: AstTraverserToGetPlaceHoldersLeavingKnownTypeOnStack
    fun init(astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack: AstTraverserToGetPlaceHoldersLeavingKnownTypeOnStack) {
        this.astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack = astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack
    }

    override fun defaultValue(node: Node): List<PlaceHolder> = astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack.dispatch(node).placeHolders
    
    private fun visitBooleanExpressionForJumpPlaceHolders(booleanExpression: Expression, jumpTargetIfFalse: JumpTargetIfFalse): List<PlaceHolder> {
        return when (booleanExpression) {
            is ComparisonNode -> {
                JumpIfComparisonPlaceHoldersQuery.fetch(
                    booleanExpression.leftExpression,
                    booleanExpression.operator as ComparisonOperator,
                    booleanExpression.rightExpression,
                    jumpTargetIfFalse,
                    astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack
                )
            }

            is VariableCallNode -> {
                val placeHoldersLeavingKnownTypeOnStack = astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack.visit(booleanExpression)
                when (placeHoldersLeavingKnownTypeOnStack.type) {
                    BuiltInType.BOOLEAN -> {
                        val jumpOffsetPlaceHolder = JumpOffsetPlaceHolder.JumpIfEqualToZeroWhichMeansFalse()
                        jumpOffsetPlaceHolder.jumpTargetIfFalse = jumpTargetIfFalse
                        placeHoldersLeavingKnownTypeOnStack.placeHolders.plus(jumpOffsetPlaceHolder)
                    }

                    else -> throw NotImplementedError(placeHoldersLeavingKnownTypeOnStack.type.name)
                }
            }

            else -> {
                throw NotImplementedError(booleanExpression.javaClass.name)
            }
        }
    }

    override fun visit(ifNode: IfNode): MutableList<PlaceHolder> {
        return visitBooleanExpressionForJumpPlaceHolders(ifNode.expression, JumpTargetIfFalse.NEXT).plus(ifNode.statements.visitALl()).toMutableList()
    }

    override fun visit(ifBlockNode: IfBlockNode): List<PlaceHolder> {
        val ifNode = ifBlockNode.ifNodes.first()
        val elseIfNodes = ifBlockNode.ifNodes.drop(1)
        val hasElse = ifBlockNode.elseNode != null
        val numberOfBranches = 1 + elseIfNodes.size + if (hasElse) { 1 } else { 0 }

        val ifPlaceHolders = visit(ifNode).withJumpToEndIf(numberOfBranches != 1)
        val elseIfsPlaceHolders = elseIfNodes.mapIndexed { index, it -> visit(it).withJumpToEndIf(hasElse || index != elseIfNodes.lastIndex) }
        val elsePlaceHolders = ifBlockNode.elseNode?.statements?.visitALl() ?: listOf()

        return listOf(IfElseIfsElsePlaceHolder(ifPlaceHolders, elseIfsPlaceHolders, elsePlaceHolders))
    }

    private fun ArrayList<Statement>.visitALl(): List<PlaceHolder> {
        return this.flatMap { dispatch(it) }
    }

    private fun MutableList<PlaceHolder>.withJumpToEndIf(anotherBranchExists: Boolean): List<PlaceHolder> {
        if(anotherBranchExists) {
            this += JumpOffsetPlaceHolder.Jump(JumpTargetIfFalse.END)
        }
        return this
    }

    override fun visit(whileLoopNode: WhileLoopNode): List<PlaceHolder> {
        val jumpPlaceHolders = visitBooleanExpressionForJumpPlaceHolders(whileLoopNode.expression, JumpTargetIfFalse.END)
        val bodyPlaceHolders = whileLoopNode.body.visitALl()
        val loopingJump = JumpOffsetPlaceHolder.Jump(JumpTargetIfFalse.START)
        return listOf(LoopPlaceHolder(jumpPlaceHolders + bodyPlaceHolders + loopingJump))
    }
    
    override fun visit(continueNode: ContinueNode): List<PlaceHolder> {
        return listOf(JumpOffsetPlaceHolder.Jump(JumpTargetIfFalse.START))
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
        val arrayType = (arrayInstantiationNode.type as ArrayType).arrayType
        result.add(
            Operation.Multianewarray(
                ConstantPoolEntry.ClassConstant(
                    TypeDescriptor.createForBuildInType(arrayType).asArrayOfDimension(numberOfDimensions).stringRepresentation
                ),
                numberOfDimensions.toUByte()
            )
        )
        return result
    }
}