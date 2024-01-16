package org.cmjava2023.placeHolders.queries

import org.cmjava2023.astToClassFileData.LocalVariableIndexAssigner
import org.cmjava2023.classfilespecification.Operation
import org.cmjava2023.symboltable.ArrayType
import org.cmjava2023.symboltable.BuiltInType
import org.cmjava2023.symboltable.Variable

class StoreVariableOperationQuery(private val localVariableIndexAssigner: LocalVariableIndexAssigner) {
    fun fetch(variable: Variable): Operation {
        val index = localVariableIndexAssigner.getLocalVariableIndexOfVariable(variable)
        return when (variable.type) {
            is ArrayType -> Operation.Astore(index)
            BuiltInType.INT -> storeInteger(index)
            BuiltInType.LONG -> storeLong(index)
            BuiltInType.FLOAT -> storeFloat(index)
            BuiltInType.DOUBLE -> storeDouble(index)
            BuiltInType.BOOLEAN -> storeInteger(index)
            BuiltInType.CHAR -> storeInteger(index)
            BuiltInType.BYTE -> storeInteger(index)
            BuiltInType.SHORT -> storeInteger(index)
            else -> throw NotImplementedError(variable.type.name)
        }
    }


    fun createIncreaseIntegerOperation(variable: Variable, value: Byte): List<Operation> {
        return when (variable.type) {
            BuiltInType.INT -> listOf(Operation.Iinc(localVariableIndexAssigner.getLocalVariableIndexOfVariable(variable), value))
            else -> throw NotImplementedError(variable.type.name)
        }
    }

    private fun storeInteger(index: UByte): Operation {
        return when (index) {
            0.toUByte() -> Operation.Istore_0()
            1.toUByte() -> Operation.Istore_1()
            2.toUByte() -> Operation.Istore_2()
            3.toUByte() -> Operation.Istore_3()
            else -> Operation.Istore(index)
        }
    }

    private fun storeLong(index: UByte): Operation {
        return when (index) {
            0.toUByte() -> Operation.Lstore_0()
            1.toUByte() -> Operation.Lstore_1()
            2.toUByte() -> Operation.Lstore_2()
            3.toUByte() -> Operation.Lstore_3()
            else -> Operation.Lstore(index)
        }
    }

    private fun storeFloat(index: UByte): Operation {
        return when (index) {
            0.toUByte() -> Operation.Fstore_0()
            1.toUByte() -> Operation.Fstore_1()
            2.toUByte() -> Operation.Fstore_2()
            3.toUByte() -> Operation.Fstore_3()
            else -> Operation.Fstore(index)
        }
    }

    private fun storeDouble(index: UByte): Operation {
        return when (index) {
            0.toUByte() -> Operation.Dstore_0()
            1.toUByte() -> Operation.Dstore_1()
            2.toUByte() -> Operation.Dstore_2()
            3.toUByte() -> Operation.Dstore_3()
            else -> Operation.Dstore(index)
        }
    }
}