package org.cmjava2023.astToClassFileData

import org.cmjava2023.ast.ASTNodes

enum class BinaryOperator {
    PLUS,
    MINUS,
    DIVISION,
    MULTIPLICATION,
    MOD,
    BITWISE_AND,
    BITWISE_OR,
    BITWISE_XOR,
    BIT_SHIFT_L,
    BIT_SHIFT_R,
    LOGICAL_SHIFT_R;

    fun isBitShift(): Boolean {
        return this == BIT_SHIFT_L || this == BIT_SHIFT_R || this == LOGICAL_SHIFT_R
    }

    companion object {
        fun fromInfixOperator(infixOperator: ASTNodes.InfixOperator): BinaryOperator {
            return when(infixOperator) {
                ASTNodes.InfixOperator.PLUS -> PLUS
                ASTNodes.InfixOperator.MINUS -> MINUS
                ASTNodes.InfixOperator.DIVISION -> DIVISION
                ASTNodes.InfixOperator.MULTIPLICATION -> MULTIPLICATION
                ASTNodes.InfixOperator.MOD -> MOD
                else -> throw NotImplementedError(infixOperator.name)
            }
        }
        fun fromComparisonOperator(comparisonOperator: ASTNodes.ComparisonOperator): BinaryOperator {
            return when(comparisonOperator) {
                ASTNodes.ComparisonOperator.BAND -> BITWISE_AND
                ASTNodes.ComparisonOperator.BOR -> BITWISE_OR
                ASTNodes.ComparisonOperator.BXOR -> BITWISE_XOR
                ASTNodes.ComparisonOperator.BIT_SHIFT_L -> BIT_SHIFT_L
                ASTNodes.ComparisonOperator.BIT_SHIFT_R -> BIT_SHIFT_R
                ASTNodes.ComparisonOperator.LOGICAL_SHIFT_R -> LOGICAL_SHIFT_R
                else -> throw NotImplementedError(comparisonOperator.name)
            }
        }
    }
}