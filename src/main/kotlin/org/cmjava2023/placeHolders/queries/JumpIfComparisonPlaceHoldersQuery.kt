package org.cmjava2023.placeHolders.queries

import org.cmjava2023.ast.ASTNodes.*
import org.cmjava2023.astToClassFileData.AstTraverserToGetPlaceHoldersLeavingKnownTypeOnStack
import org.cmjava2023.astToClassFileData.ValueNodeTransformedToTypeQuery
import org.cmjava2023.placeHolders.PlaceHolder
import org.cmjava2023.placeHolders.jumps.JumpOffsetPlaceHolder
import org.cmjava2023.symboltable.BuiltInType

class JumpIfComparisonPlaceHoldersQuery {
    companion object {
        fun fetch(
            leftExpression: Expression,
            comparisonOperator: ComparisonOperator,
            rightExpression: Expression,
            jumpTargetIfFalse: JumpOffsetPlaceHolder.JumpTargetIfFalse,
            astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack: AstTraverserToGetPlaceHoldersLeavingKnownTypeOnStack
        ): List<PlaceHolder> {
            val leftPlaceHoldersLeavingKnownTypeOnStack = astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack.dispatch(leftExpression)
            val rightPlaceHoldersLeavingKnownTypeOnStack = astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack.dispatch(rightExpression)

            val leftType = leftPlaceHoldersLeavingKnownTypeOnStack.type
            val rightType = rightPlaceHoldersLeavingKnownTypeOnStack.type

            return when {
                leftType == BuiltInType.INT && rightType == BuiltInType.INT -> {
                    val result = mutableListOf<PlaceHolder>()
                    result += leftPlaceHoldersLeavingKnownTypeOnStack.placeHolders
                    result += rightPlaceHoldersLeavingKnownTypeOnStack.placeHolders
                    
                    val jumpOffsetPlaceHolderForIntegersWithOperator = jumpOffsetPlaceHolderForIntegersWithOperator(comparisonOperator)
                    jumpOffsetPlaceHolderForIntegersWithOperator.jumpTargetIfFalse = jumpTargetIfFalse

                    result += jumpOffsetPlaceHolderForIntegersWithOperator

                    return result
                }
                leftType != rightType && rightExpression is ValueNode<*> -> {
                    fetch(leftExpression, comparisonOperator, ValueNodeTransformedToTypeQuery.fetch(rightExpression, leftType), jumpTargetIfFalse, astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack)
                }

                else -> throw NotImplementedError(leftExpression.javaClass.name + ":" + leftType.name + " " + comparisonOperator.name + " " + rightExpression.javaClass.name + ":" + rightType.name)
            }
        }

        private fun jumpOffsetPlaceHolderForIntegersWithOperator(comparisonOperator: ComparisonOperator): JumpOffsetPlaceHolder {
            @Suppress("REDUNDANT_ELSE_IN_WHEN") return when (comparisonOperator) {
                ComparisonOperator.EQUALS -> JumpOffsetPlaceHolder.JumpIfIntegersAreNotEqual()
                ComparisonOperator.NOT_EQUALS -> JumpOffsetPlaceHolder.JumpIfIntegersAreEqual()
                ComparisonOperator.GREATER_THAN_OR_EQUALS -> JumpOffsetPlaceHolder.JumpIfIntegerALessThanB()
                ComparisonOperator.LESS_THAN_OR_EQUALS -> JumpOffsetPlaceHolder.JumpIfIntegerAGreaterThanB()
                ComparisonOperator.LESS_THAN -> JumpOffsetPlaceHolder.JumpIfIntegerAGreaterThanOrEqualToB()
                ComparisonOperator.GREATER_THAN -> JumpOffsetPlaceHolder.JumpIfIntegerALessThanOrEqualToB()
                else -> throw NotImplementedError(comparisonOperator.name)
            }
        }
    }
}