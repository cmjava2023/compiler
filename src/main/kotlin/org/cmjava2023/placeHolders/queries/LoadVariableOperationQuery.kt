package org.cmjava2023.placeHolders.queries

import org.cmjava2023.astToClassFileData.LocalVariableIndexAssigner
import org.cmjava2023.classfilespecification.Operation
import org.cmjava2023.symboltable.ArrayType
import org.cmjava2023.symboltable.BuiltInType
import org.cmjava2023.symboltable.Variable

class LoadVariableOperationQuery(private val localVariableIndexAssigner: LocalVariableIndexAssigner) {

    fun fetch(variable: Variable): Operation {
        val index = localVariableIndexAssigner.getLocalVariableIndexOfVariable(variable)
        return when (variable.type) {
            is ArrayType -> loadArray(index)
            BuiltInType.INT -> loadInteger(index)
            BuiltInType.LONG -> loadLong(index)
            BuiltInType.FLOAT -> loadFloat(index)
            BuiltInType.DOUBLE -> loadDouble(index)
            BuiltInType.BOOLEAN -> loadInteger(index)
            BuiltInType.CHAR -> loadInteger(index)
            BuiltInType.BYTE -> loadInteger(index)
            BuiltInType.SHORT -> loadInteger(index)
            else -> throw NotImplementedError(variable.type.name)
        }
    }
            
    private fun loadInteger(index: UByte): Operation {
        return when (index) {
            0.toUByte() -> Operation.Iload_0()
            1.toUByte() -> Operation.Iload_1()
            2.toUByte() -> Operation.Iload_2()
            3.toUByte() -> Operation.Iload_3()
            else -> Operation.Iload(index)
        }
    }

    private fun loadLong(index: UByte): Operation {
        return when (index) {
            0.toUByte() -> Operation.Lload_0()
            1.toUByte() -> Operation.Lload_1()
            2.toUByte() -> Operation.Lload_2()
            3.toUByte() -> Operation.Lload_3()
            else -> Operation.Lload(index)
        }
    }

    private fun loadFloat(index: UByte): Operation {
        return when (index) {
            0.toUByte() -> Operation.Fload_0()
            1.toUByte() -> Operation.Fload_1()
            2.toUByte() -> Operation.Fload_2()
            3.toUByte() -> Operation.Fload_3()
            else -> Operation.Fload(index)
        }
    }

    private fun loadDouble(index: UByte): Operation {
        return when (index) {
            0.toUByte() -> Operation.Dload_0()
            1.toUByte() -> Operation.Dload_1()
            2.toUByte() -> Operation.Dload_2()
            3.toUByte() -> Operation.Dload_3()
            else -> Operation.Dload(index)
        }
    }

    private fun loadArray(index: UByte): Operation {
        return when (index) {
            0.toUByte() -> Operation.Aload_0()
            1.toUByte() -> Operation.Aload_1()
            2.toUByte() -> Operation.Aload_2()
            3.toUByte() -> Operation.Aload_3()
            else -> Operation.Aload(index)
        }
    }
}