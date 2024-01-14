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
            jumpTarget: JumpOffsetPlaceHolder.JumpTarget,
            astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack: AstTraverserToGetPlaceHoldersLeavingKnownTypeOnStack,
            jumpWhen: Boolean
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

                    val jumpOffsetPlaceHolderForIntegersWithOperator = jumpOffsetPlaceHolderForIntegersWithOperator(comparisonOperator, jumpWhen)
                    jumpOffsetPlaceHolderForIntegersWithOperator.jumpTarget = jumpTarget

                    result += jumpOffsetPlaceHolderForIntegersWithOperator

                    return result
                }

                leftType != rightType && rightExpression is ValueNode<*> -> {
                    fetch(
                        leftExpression,
                        comparisonOperator,
                        ValueNodeTransformedToTypeQuery.fetch(rightExpression, leftType),
                        jumpTarget,
                        astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack,
                        jumpWhen
                    )
                }

                else -> throw NotImplementedError(leftExpression.javaClass.name + ":" + leftType.name + " " + comparisonOperator.name + " " + rightExpression.javaClass.name + ":" + rightType.name)
            }
        }

        private val mapOperatorToLogicalOpposite = mapOf(
            ComparisonOperator.EQUALS to ComparisonOperator.NOT_EQUALS,
            ComparisonOperator.NOT_EQUALS to ComparisonOperator.EQUALS,
            ComparisonOperator.GREATER_THAN_OR_EQUALS to ComparisonOperator.LESS_THAN,
            ComparisonOperator.LESS_THAN_OR_EQUALS to ComparisonOperator.GREATER_THAN,
            ComparisonOperator.LESS_THAN to ComparisonOperator.GREATER_THAN_OR_EQUALS,
            ComparisonOperator.GREATER_THAN to ComparisonOperator.LESS_THAN_OR_EQUALS
        )

        private fun jumpOffsetPlaceHolderForIntegersWithOperator(
            comparisonOperator: ComparisonOperator,
            jumpWhen: Boolean
        ): JumpOffsetPlaceHolder {
            val evaluatedOperator = if (jumpWhen) {
                comparisonOperator
            } else {
                mapOperatorToLogicalOpposite[comparisonOperator]                
            }
            return when (evaluatedOperator) {
                ComparisonOperator.EQUALS -> JumpOffsetPlaceHolder.JumpIfIntegersAreEqual()
                ComparisonOperator.NOT_EQUALS -> JumpOffsetPlaceHolder.JumpIfIntegersAreNotEqual()
                ComparisonOperator.GREATER_THAN_OR_EQUALS -> JumpOffsetPlaceHolder.JumpIfIntegerAGreaterThanOrEqualToB()
                ComparisonOperator.LESS_THAN_OR_EQUALS -> JumpOffsetPlaceHolder.JumpIfIntegerALessThanOrEqualToB()
                ComparisonOperator.LESS_THAN -> JumpOffsetPlaceHolder.JumpIfIntegerALessThanB()
                ComparisonOperator.GREATER_THAN -> JumpOffsetPlaceHolder.JumpIfIntegerAGreaterThanB()
                else -> throw NotImplementedError(comparisonOperator.name)
            }
        }
    }
}