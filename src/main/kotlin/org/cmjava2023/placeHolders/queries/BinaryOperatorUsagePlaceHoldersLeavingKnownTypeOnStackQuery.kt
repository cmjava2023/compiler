package org.cmjava2023.placeHolders.queries

import org.cmjava2023.ast.ASTNodes.*
import org.cmjava2023.astToClassFileData.AstTraverserToGetPlaceHoldersLeavingKnownTypeOnStack
import org.cmjava2023.astToClassFileData.ValueNodeTransformedToTypeQuery
import org.cmjava2023.classfilespecification.Operation
import org.cmjava2023.placeHolders.PlaceHolder
import org.cmjava2023.placeHolders.PlaceHoldersLeavingKnownTypeOnStack
import org.cmjava2023.symboltable.BuiltInType

class BinaryOperatorUsagePlaceHoldersLeavingKnownTypeOnStackQuery {
    companion object {
        fun fetch(
            leftExpression: Expression,
            binaryOperator
            : BinaryOperator,
            rightExpression: Expression,
            astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack: AstTraverserToGetPlaceHoldersLeavingKnownTypeOnStack
        ): PlaceHoldersLeavingKnownTypeOnStack {
            val leftPlaceHoldersLeavingKnownTypeOnStack = astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack.dispatch(leftExpression)
            val rightPlaceHoldersLeavingKnownTypeOnStack = astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack.dispatch(rightExpression)

            val leftType = leftPlaceHoldersLeavingKnownTypeOnStack.type
            val rightType = rightPlaceHoldersLeavingKnownTypeOnStack.type

            return when {
                leftType == BuiltInType.STRING && rightType is BuiltInType && binaryOperator == MathOperator.PLUS -> StringConcatPlaceHoldersLeavingKnownTypeOnStackQuery.fetch(
                    astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack.dispatch(
                        leftExpression
                    ).placeHolders, astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack.dispatch(rightExpression).placeHolders, rightType
                )
                leftType is BuiltInType && (leftType == rightType || (binaryOperator is MathOperator && binaryOperator.isBitShift && leftType == BuiltInType.LONG && rightType == BuiltInType.INT)) -> {
                    val result = mutableListOf<PlaceHolder>()
                    result += leftPlaceHoldersLeavingKnownTypeOnStack.placeHolders
                    result += rightPlaceHoldersLeavingKnownTypeOnStack.placeHolders

                    val binaryOperation = when (leftType) {
                        BuiltInType.INT -> binaryOperationForIntegers(binaryOperator)
                        BuiltInType.LONG -> binaryOperationForLongs(binaryOperator)
                        BuiltInType.FLOAT -> binaryOperationForFloats(binaryOperator)
                        BuiltInType.DOUBLE -> binaryOperationForDoubles(binaryOperator)
                        else -> throw NotImplementedError(leftType.javaClass.name)
                    }
                    result += binaryOperation

                    return PlaceHoldersLeavingKnownTypeOnStack(result, leftType)
                }
                leftType != rightType && rightExpression is ValueNode<*> -> {
                    fetch(leftExpression, binaryOperator, ValueNodeTransformedToTypeQuery.fetch(rightExpression, leftType), astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack)
                }

                else -> throw NotImplementedError(leftExpression.javaClass.name + ":" + leftType.name + " " + binaryOperator.toString() + " " + rightExpression.javaClass.name + ":" + rightType.name)
            }
        }

        private fun binaryOperationForIntegers(operator: BinaryOperator): Operation {
            return when (operator) {
                MathOperator.PLUS -> Operation.Iadd()
                MathOperator.MINUS -> Operation.Isub()
                MathOperator.DIVISION -> Operation.Idiv()
                MathOperator.MULTIPLICATION -> Operation.Imul()
                MathOperator.MOD -> Operation.Irem()
                MathOperator.BITWISE_AND -> Operation.Iand()
                MathOperator.BITWISE_OR -> Operation.Ior()
                MathOperator.BITWISE_XOR -> Operation.Ixor()
                MathOperator.BIT_SHIFT_L -> Operation.Ishl()
                MathOperator.BIT_SHIFT_R -> Operation.Ishr()
                MathOperator.LOGICAL_SHIFT_R -> Operation.Iushr()
                else -> throw NotImplementedError(operator.toString())
            }
        }

        private fun binaryOperationForLongs(operator: BinaryOperator): Operation {
            return when (operator) {
                MathOperator.PLUS -> Operation.Ladd()
                MathOperator.MINUS -> Operation.Lsub()
                MathOperator.DIVISION -> Operation.Ldiv()
                MathOperator.MULTIPLICATION -> Operation.Lmul()
                MathOperator.MOD -> Operation.Lrem()
                MathOperator.BITWISE_AND -> Operation.Land()
                MathOperator.BITWISE_OR -> Operation.Lor()
                MathOperator.BITWISE_XOR -> Operation.Lxor()
                MathOperator.BIT_SHIFT_L -> Operation.Lshl()
                MathOperator.BIT_SHIFT_R -> Operation.Lshr()
                MathOperator.LOGICAL_SHIFT_R -> Operation.Lushr()
                else -> throw NotImplementedError(operator.toString())
            }
        }

        private fun binaryOperationForFloats(operator: BinaryOperator): Operation {
            return when (operator) {
                MathOperator.PLUS -> Operation.Fadd()
                MathOperator.MINUS -> Operation.Fsub()
                MathOperator.DIVISION -> Operation.Fdiv()
                MathOperator.MULTIPLICATION -> Operation.Fmul()
                MathOperator.MOD -> Operation.Frem()
                else -> throw NotImplementedError(operator.toString())
            }
        }

        private fun binaryOperationForDoubles(operator: BinaryOperator): Operation {
            return when (operator) {
                MathOperator.PLUS -> Operation.Dadd()
                MathOperator.MINUS -> Operation.Dsub()
                MathOperator.DIVISION -> Operation.Ddiv()
                MathOperator.MULTIPLICATION -> Operation.Dmul()
                MathOperator.MOD -> Operation.Drem()
                else -> throw NotImplementedError(operator.toString())
            }
        }
    }
}