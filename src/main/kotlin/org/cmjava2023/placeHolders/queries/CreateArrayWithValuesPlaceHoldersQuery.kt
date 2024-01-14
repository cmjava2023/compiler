package org.cmjava2023.placeHolders.queries

import org.cmjava2023.ast.ASTNodes
import org.cmjava2023.astToClassFileData.AstTraverserToGetPlaceHolders
import org.cmjava2023.astToClassFileData.ValueNodeTransformedToTypeQuery
import org.cmjava2023.classfilespecification.Operation
import org.cmjava2023.classfilespecification.constantpool.ConstantPoolEntry
import org.cmjava2023.placeHolders.LoadConstantPlaceHolder
import org.cmjava2023.placeHolders.PlaceHolder
import org.cmjava2023.symboltable.ArrayType
import org.cmjava2023.symboltable.BuiltInType

class CreateArrayWithValuesPlaceHoldersQuery {
    companion object {
        fun fetch(
            arrayInstantiationWithValuesNode: ASTNodes.ArrayInstantiationWithValuesNode,
            arrayType: ArrayType,
            astTraverserToGetPlaceHolders: AstTraverserToGetPlaceHolders
        ): List<PlaceHolder> {
            val result = mutableListOf<PlaceHolder>()
            val arrayLength = arrayInstantiationWithValuesNode.expressions.size
            result.add(LoadConstantPlaceHolder.IntegerConstant(arrayLength))
            val valueNodesOrNull = arrayInstantiationWithValuesNode.expressions.map {
                if (it is ASTNodes.ValueNode<*>) {
                    it
                } else {
                    null
                }
            }
            if (valueNodesOrNull.none { it == null }) {
                result.add(
                    when (arrayType.arrayType) {
                        BuiltInType.STRING -> Operation.Anewarray(ConstantPoolEntry.ClassConstant.STRING)
                        BuiltInType.BOOLEAN -> Operation.Newarray(Operation.ArrayType.T_BOOLEAN)
                        BuiltInType.INT -> Operation.Newarray(Operation.ArrayType.T_INT)
                        BuiltInType.BYTE -> Operation.Newarray(Operation.ArrayType.T_BYTE)
                        BuiltInType.CHAR -> Operation.Newarray(Operation.ArrayType.T_CHAR)
                        BuiltInType.SHORT -> Operation.Newarray(Operation.ArrayType.T_SHORT)
                        BuiltInType.LONG -> Operation.Newarray(Operation.ArrayType.T_LONG)
                        BuiltInType.FLOAT -> Operation.Newarray(Operation.ArrayType.T_FLOAT)
                        BuiltInType.DOUBLE -> Operation.Newarray(Operation.ArrayType.T_DOUBLE)
                        else -> throw NotImplementedError(arrayType.arrayType.name)
                    }
                )
                val operationToStoreArrayElements = when (arrayType.arrayType) {
                    BuiltInType.STRING -> Operation.Aastore()
                    BuiltInType.BOOLEAN -> Operation.Bastore()
                    BuiltInType.INT -> Operation.Iastore()
                    BuiltInType.BYTE -> Operation.Bastore()
                    BuiltInType.SHORT -> Operation.Sastore()
                    BuiltInType.CHAR -> Operation.Castore()
                    BuiltInType.LONG -> Operation.Lastore()
                    BuiltInType.DOUBLE -> Operation.Dastore()
                    BuiltInType.FLOAT -> Operation.Fastore()
                    else -> throw NotImplementedError(arrayType.arrayType.name)
                }
                result.add(Operation.Dup())
                val valueNodes = valueNodesOrNull.requireNoNulls()
                for ((index, valueNode) in valueNodes.withIndex()) {
                    result.add(LoadConstantPlaceHolder.IntegerConstant(index))
                    result.addAll(astTraverserToGetPlaceHolders.dispatch(ValueNodeTransformedToTypeQuery.fetch(valueNode, arrayType.arrayType)))
                    result.add(operationToStoreArrayElements)
                    if (index + 1 < valueNodes.size) {
                        result.add(Operation.Dup())
                    }
                }
            } else {
                throw NotImplementedError("Array element not ValueNode")
            }


            return result
        }
    }
}