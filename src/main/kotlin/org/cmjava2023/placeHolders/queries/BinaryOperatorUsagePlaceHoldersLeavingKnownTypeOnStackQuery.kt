package org.cmjava2023.placeHolders.queries

import org.cmjava2023.ast.ASTNodes.*
import org.cmjava2023.astToClassFileData.*
import org.cmjava2023.classfilespecification.Operation
import org.cmjava2023.placeHolders.PlaceHolder
import org.cmjava2023.placeHolders.PlaceHoldersLeavingKnownTypeOnStack
import org.cmjava2023.symboltable.BuiltInType

class BinaryOperatorUsagePlaceHoldersLeavingKnownTypeOnStackQuery {
    companion object {
        fun fetch(
            infixNode: InfixNode,
            astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack: AstTraverserToGetPlaceHoldersLeavingKnownTypeOnStack
        ): PlaceHoldersLeavingKnownTypeOnStack = fetch(
            infixNode.leftExpression,
            BinaryOperator.fromInfixOperator(infixNode.operator),
            infixNode.rightExpression,
            astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack
        )

        fun fetch(
            comparisonNode: ComparisonNode, astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack: AstTraverserToGetPlaceHoldersLeavingKnownTypeOnStack
        ): PlaceHoldersLeavingKnownTypeOnStack = fetch(
            comparisonNode.leftExpression,
            BinaryOperator.fromComparisonOperator(comparisonNode.comparisonOperator),
            comparisonNode.rightExpression,
            astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack
        )

        fun fetch(
            leftExpression: Expression,
            binaryOperator: BinaryOperator,
            rightExpression: Expression,
            astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack: AstTraverserToGetPlaceHoldersLeavingKnownTypeOnStack
        ): PlaceHoldersLeavingKnownTypeOnStack {
            val leftPlaceHoldersLeavingKnownTypeOnStack = astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack.dispatch(leftExpression)
            val rightPlaceHoldersLeavingKnownTypeOnStack = astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack.dispatch(rightExpression)

            val leftType = leftPlaceHoldersLeavingKnownTypeOnStack.type
            val rightType = rightPlaceHoldersLeavingKnownTypeOnStack.type

            return when {
                leftType == BuiltInType.STRING && rightType is BuiltInType && binaryOperator == BinaryOperator.PLUS -> StringConcatPlaceHoldersLeavingKnownTypeOnStackQuery.fetch(
                    astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack.dispatch(
                        leftExpression
                    ).placeHolders, astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack.dispatch(rightExpression).placeHolders, rightType
                )
                leftType is BuiltInType && (leftType == rightType || (binaryOperator.isBitShift() && leftType == BuiltInType.LONG && rightType == BuiltInType.INT)) -> {
                    val result = mutableListOf<PlaceHolder>()
                    result += leftPlaceHoldersLeavingKnownTypeOnStack.placeHolders
                    result += rightPlaceHoldersLeavingKnownTypeOnStack.placeHolders

                    val comparisonOperator = when (leftType) {
                        BuiltInType.INT -> opCodeForIntegersWithOperator(binaryOperator)
                        BuiltInType.LONG -> opCodeForLongsWithOperator(binaryOperator)
                        BuiltInType.FLOAT -> opCodeForFloatsWithOperator(binaryOperator)
                        BuiltInType.DOUBLE -> opCodeForDoubleWithOperator(binaryOperator)
                        else -> throw NotImplementedError(leftType.javaClass.name)
                    }
                    result += comparisonOperator

                    return PlaceHoldersLeavingKnownTypeOnStack(result, leftType)
                }
                leftType != rightType && rightExpression is ValueNode<*> -> {
                    fetch(leftExpression, binaryOperator, ValueNodeTransformedToTypeQuery.fetch(rightExpression, leftType), astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack)
                }

                else -> throw NotImplementedError(leftExpression.javaClass.name + ":" + leftType.name + " " + binaryOperator.name + " " + rightExpression.javaClass.name + ":" + rightType.name)
            }
        }

        private fun opCodeForIntegersWithOperator(operator: BinaryOperator): Operation {
            @Suppress("REDUNDANT_ELSE_IN_WHEN") return when (operator) {
                BinaryOperator.PLUS -> Operation.Iadd()
                BinaryOperator.MINUS -> Operation.Isub()
                BinaryOperator.DIVISION -> Operation.Idiv()
                BinaryOperator.MULTIPLICATION -> Operation.Imul()
                BinaryOperator.MOD -> Operation.Irem()
                BinaryOperator.BITWISE_AND -> Operation.Iand()
                BinaryOperator.BITWISE_OR -> Operation.Ior()
                BinaryOperator.BITWISE_XOR -> Operation.Ixor()
                BinaryOperator.BIT_SHIFT_L -> Operation.Ishl()
                BinaryOperator.BIT_SHIFT_R -> Operation.Ishr()
                BinaryOperator.LOGICAL_SHIFT_R -> Operation.Iushr()
                else -> throw NotImplementedError(operator.name)
            }
        }

        private fun opCodeForLongsWithOperator(operator: BinaryOperator): Operation {
            @Suppress("REDUNDANT_ELSE_IN_WHEN") return when (operator) {
                BinaryOperator.PLUS -> Operation.Ladd()
                BinaryOperator.MINUS -> Operation.Lsub()
                BinaryOperator.DIVISION -> Operation.Ldiv()
                BinaryOperator.MULTIPLICATION -> Operation.Lmul()
                BinaryOperator.MOD -> Operation.Lrem()
                BinaryOperator.BITWISE_AND -> Operation.Land()
                BinaryOperator.BITWISE_OR -> Operation.Lor()
                BinaryOperator.BITWISE_XOR -> Operation.Lxor()
                BinaryOperator.BIT_SHIFT_L -> Operation.Lshl()
                BinaryOperator.BIT_SHIFT_R -> Operation.Lshr()
                BinaryOperator.LOGICAL_SHIFT_R -> Operation.Lushr()
                else -> throw NotImplementedError(operator.name)
            }
        }

        private fun opCodeForFloatsWithOperator(operator: BinaryOperator): Operation {
            return when (operator) {
                BinaryOperator.PLUS -> Operation.Fadd()
                BinaryOperator.MINUS -> Operation.Fsub()
                BinaryOperator.DIVISION -> Operation.Fdiv()
                BinaryOperator.MULTIPLICATION -> Operation.Fmul()
                BinaryOperator.MOD -> Operation.Frem()
                else -> throw NotImplementedError(operator.name)
            }
        }

        private fun opCodeForDoubleWithOperator(operator: BinaryOperator): Operation {
            return when (operator) {
                BinaryOperator.PLUS -> Operation.Dadd()
                BinaryOperator.MINUS -> Operation.Dsub()
                BinaryOperator.DIVISION -> Operation.Ddiv()
                BinaryOperator.MULTIPLICATION -> Operation.Dmul()
                BinaryOperator.MOD -> Operation.Drem()
                else -> throw NotImplementedError(operator.name)
            }
        }
    }
}