package org.cmjava2023.placeHolders.queries

import org.cmjava2023.ast.ASTNodes.*
import org.cmjava2023.astToClassFileModel.BinaryOperator
import org.cmjava2023.astToClassFileModel.AstTraverserToGetPlaceHolders
import org.cmjava2023.astToClassFileModel.TypeOfExpressionQuery
import org.cmjava2023.classfilespecification.Operation
import org.cmjava2023.placeHolders.PlaceHolder
import org.cmjava2023.placeHolders.PlaceHoldersLeavingKnownTypeOnStack
import org.cmjava2023.symboltable.BuiltInType

class BinaryOperatorUsagePlaceHoldersLeavingKnownTypeOnStackQuery {
    companion object {
        fun fetch(infixNode: InfixNode, astTraverserToGetPlaceHolders: AstTraverserToGetPlaceHolders): PlaceHoldersLeavingKnownTypeOnStack = fetch(infixNode.leftExpression, BinaryOperator.fromInfixOperator(infixNode.operator), infixNode.rightExpression, astTraverserToGetPlaceHolders)
        fun fetch(comparisonNode: ComparisonNode, astTraverserToGetPlaceHolders: AstTraverserToGetPlaceHolders): PlaceHoldersLeavingKnownTypeOnStack = fetch(comparisonNode.leftExpression, BinaryOperator.fromComparisonOperator(comparisonNode.comparisonOperator), comparisonNode.rightExpression, astTraverserToGetPlaceHolders)
        
        fun fetch(leftExpression: Expression, binaryOperator: BinaryOperator, rightExpression: Expression, astTraverserToGetPlaceHolders: AstTraverserToGetPlaceHolders): PlaceHoldersLeavingKnownTypeOnStack {
            val typeOfLeftExpression = TypeOfExpressionQuery.fetch(leftExpression)
            val typeOfRightExpression = TypeOfExpressionQuery.fetch(rightExpression)

            return when {
                typeOfLeftExpression == BuiltInType.STRING && typeOfRightExpression is BuiltInType && binaryOperator == BinaryOperator.PLUS ->
                    StringConcatPlaceHoldersLeavingKnownTypeOnStackQuery.fetch(astTraverserToGetPlaceHolders.dispatch(leftExpression), typeOfRightExpression)
                typeOfLeftExpression is BuiltInType && typeOfLeftExpression == typeOfRightExpression -> {
                    val result = mutableListOf<PlaceHolder>()
                    result += astTraverserToGetPlaceHolders.dispatch(leftExpression) 
                    result += astTraverserToGetPlaceHolders.dispatch(rightExpression) 
                        
                    val comparisonOperator = when(typeOfLeftExpression) {
                        BuiltInType.INT -> opCodeForIntegersWithOperator(binaryOperator)
                        BuiltInType.LONG -> opCodeForLongsWithOperator(binaryOperator)
                        BuiltInType.FLOAT -> opCodeForFloatsWithOperator(binaryOperator)
                        BuiltInType.DOUBLE -> opCodeForDoubleWithOperator(binaryOperator)
                        else -> throw NotImplementedError(typeOfLeftExpression.javaClass.name)
                    }
                    result += comparisonOperator

                    return PlaceHoldersLeavingKnownTypeOnStack(result, typeOfLeftExpression)
                }
                else -> throw NotImplementedError(binaryOperator.name)
            }
        }

        private fun opCodeForIntegersWithOperator(operator: BinaryOperator): Operation {
            @Suppress("REDUNDANT_ELSE_IN_WHEN")
            return when (operator) {
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
            @Suppress("REDUNDANT_ELSE_IN_WHEN")
            return when (operator) {
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