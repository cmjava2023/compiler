package org.cmjava2023.astToClassFileData

import org.cmjava2023.ast.ASTNodes.*
import org.cmjava2023.ast.ASTTraverser
import org.cmjava2023.classfilespecification.Operation
import org.cmjava2023.classfilespecification.constantpool.ConstantPoolEntry
import org.cmjava2023.classfilespecification.constantpool.TypeDescriptor
import org.cmjava2023.placeHolders.*
import org.cmjava2023.placeHolders.JumpOffsetPlaceHolder.JumpTarget
import org.cmjava2023.placeHolders.queries.*
import org.cmjava2023.symboltable.ArrayType
import org.cmjava2023.symboltable.BuiltInType
import org.cmjava2023.symboltable.Variable

class AstTraverserToGetPlaceHolders(
    private val storeVariableOperationQuery: StoreVariableOperationQuery
) : ASTTraverser<List<PlaceHolder>>() {
    private lateinit var astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack: AstTraverserToGetPlaceHoldersLeavingKnownTypeOnStack
    fun init(astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack: AstTraverserToGetPlaceHoldersLeavingKnownTypeOnStack) {
        this.astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack = astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack
    }

    override fun defaultValue(node: Node): List<PlaceHolder> = astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack.dispatch(node).placeHolders

    private fun createBooleanJump(type: org.cmjava2023.symboltable.Type, jumpWhen: Boolean): JumpOffsetPlaceHolder {
        return when (type) {
            BuiltInType.BOOLEAN -> {
                if (jumpWhen) {
                    JumpOffsetPlaceHolder.JumpIfNotEqualToZeroWhichMeansTrue()
                } else {
                    JumpOffsetPlaceHolder.JumpIfEqualToZeroWhichMeansFalse()
                }
            }

            else -> throw NotImplementedError(type.name)
        }
    }

    private fun visitBooleanExpressionForJumpPlaceHolders(
        booleanExpression: Expression,
        jumpTarget: JumpTarget,
        jumpWhen: Boolean
    ): List<PlaceHolder> {
        return when (booleanExpression) {
            is ComparisonNode -> {
                JumpIfComparisonPlaceHoldersQuery.fetch(
                    booleanExpression.leftExpression,
                    booleanExpression.operator as ComparisonOperator,
                    booleanExpression.rightExpression,
                    jumpTarget,
                    astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack,
                    jumpWhen
                )
            }

            is VariableCallNode -> {
                val placeHoldersLeavingKnownTypeOnStack = astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack.visit(booleanExpression)
                val jumpOffsetPlaceHolder = createBooleanJump(placeHoldersLeavingKnownTypeOnStack.type, jumpWhen)
                jumpOffsetPlaceHolder.jumpTarget = jumpTarget
                placeHoldersLeavingKnownTypeOnStack.placeHolders.plus(jumpOffsetPlaceHolder)
            }

            is ValueNode<*> -> when (val value = booleanExpression.value) {
                is Boolean -> if (jumpWhen && value) {
                    val jumpOffsetPlaceHolder = JumpOffsetPlaceHolder.Jump()
                    jumpOffsetPlaceHolder.jumpTarget = jumpTarget
                    listOf(jumpOffsetPlaceHolder)
                } else {
                    listOf()
                }
                else -> throw NotImplementedError(booleanExpression.value.javaClass.name)
            }

            else -> {
                throw NotImplementedError(booleanExpression.javaClass.name)
            }
        }
    }

    override fun visit(ifNode: IfNode): MutableList<PlaceHolder> {
        return visitBooleanExpressionForJumpPlaceHolders(ifNode.expression, JumpTarget.NEXT, false).plus(ifNode.statements.visitALl()).toMutableList()
    }

    override fun visit(ifBlockNode: IfBlockNode): List<PlaceHolder> {
        val ifNode = ifBlockNode.ifNodes.first()
        val elseIfNodes = ifBlockNode.ifNodes.drop(1)
        val hasElse = ifBlockNode.elseNode != null
        val numberOfBranches = 1 + elseIfNodes.size + if (hasElse) {
            1
        } else {
            0
        }

        val ifPlaceHolders = visit(ifNode).withJumpToEndIf(numberOfBranches != 1)
        val elseIfsPlaceHolders = elseIfNodes.mapIndexed { index, it -> visit(it).withJumpToEndIf(hasElse || index != elseIfNodes.lastIndex) }
        val elsePlaceHolders = ifBlockNode.elseNode?.statements?.visitALl() ?: listOf()

        return listOf(IfElseIfsElsePlaceHolder(ifPlaceHolders, elseIfsPlaceHolders, elsePlaceHolders))
    }

    private fun ArrayList<Statement>.visitALl(): List<PlaceHolder> {
        return this.flatMap { dispatch(it) }
    }

    private fun MutableList<PlaceHolder>.withJumpToEndIf(anotherBranchExists: Boolean): List<PlaceHolder> {
        if (anotherBranchExists) {
            this += JumpOffsetPlaceHolder.Jump(JumpTarget.END)
        }
        return this
    }

    override fun visit(whileLoopNode: WhileLoopNode): List<PlaceHolder> {
        val jumpPlaceHolders = visitBooleanExpressionForJumpPlaceHolders(whileLoopNode.expression, JumpTarget.END, false)
        val bodyPlaceHolders = whileLoopNode.body.visitALl()
        val loopingJump = JumpOffsetPlaceHolder.Jump(JumpTarget.START)
        return listOf(LoopPlaceHolder(jumpPlaceHolders + bodyPlaceHolders + loopingJump))
    }

    override fun visit(doWhileLoopNode: DoWhileLoopNode): List<PlaceHolder> {
        val bodyPlaceHolders = doWhileLoopNode.body.visitALl()
        val jumpPlaceHolders = visitBooleanExpressionForJumpPlaceHolders(doWhileLoopNode.expression, JumpTarget.START, true)
        return listOf(LoopPlaceHolder(bodyPlaceHolders + jumpPlaceHolders))
    }

    override fun visit(continueNode: ContinueNode): List<PlaceHolder> {
        return listOf(JumpOffsetPlaceHolder.Jump(JumpTarget.START))
    }

    override fun visit(breakNode: BreakNode): List<PlaceHolder> {
        return listOf(JumpOffsetPlaceHolder.Jump(JumpTarget.END))
    }

    override fun visit(functionNode: FunctionNode): List<PlaceHolder> {
        val opCodes = functionNode.body.visitALl()
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
            assignOrDeclareWithValue(variableSymbol, initialExpression)
        }
    }

    private fun assignOrDeclareWithValue(variableSymbol: Variable, initialExpression: Expression): List<PlaceHolder> {
        return AssignOrDeclareVariablePlaceHoldersQuery.fetch(variableSymbol, initialExpression, this)
            .plus(storeVariableOperationQuery.fetch(variableSymbol))
    }

    override fun visit(variableAssignmentNode: VariableAssignmentNode): List<PlaceHolder> {
        val variableSymbol = variableAssignmentNode.variable
        return assignOrDeclareWithValue(variableSymbol, variableAssignmentNode.expression)
    }

    override fun visit(returnNode: ReturnNode): List<PlaceHolder> {
        return listOf(Operation.Return())
    }

    override fun visit(unarySuffixNode: UnarySuffixNode): List<PlaceHolder> {
        val variableSymbol = (unarySuffixNode.variableCallNode() as VariableCallNode).symbol
        return storeVariableOperationQuery.createIncreaseIntegerOperation(
            variableSymbol, when (unarySuffixNode.operator) {
                SuffixOperator.INC -> 1
                SuffixOperator.DEC -> -1
                else -> throw NotImplementedError(unarySuffixNode.operator.name)
            }
        )
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