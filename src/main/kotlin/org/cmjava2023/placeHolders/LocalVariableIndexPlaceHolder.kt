package org.cmjava2023.placeHolders

import org.cmjava2023.classFileDataToBytes.LocalVariableIndexAssigner
import org.cmjava2023.classfilespecification.Operation
import org.cmjava2023.symboltable.ArrayType
import org.cmjava2023.symboltable.BuiltInType
import org.cmjava2023.symboltable.Variable

abstract class LocalVariableIndexPlaceHolder(private val variableSymbol: Variable) : PlaceHolder {
    companion object {
        fun createToStoreVariable(variableSymbol: Variable): LocalVariableIndexPlaceHolder {
            return when (val typeOfVariableSymbol = variableSymbol.type) {
                is ArrayType -> StoreArray(variableSymbol)
                BuiltInType.INT -> StoreInt(variableSymbol)
                BuiltInType.LONG -> StoreLong(variableSymbol)
                BuiltInType.FLOAT -> StoreFloat(variableSymbol)
                BuiltInType.DOUBLE -> StoreDouble(variableSymbol)
                BuiltInType.BOOLEAN -> StoreInt(variableSymbol)
                BuiltInType.CHAR -> StoreInt(variableSymbol)
                BuiltInType.BYTE -> StoreInt(variableSymbol)
                BuiltInType.SHORT -> StoreInt(variableSymbol)
                else -> throw NotImplementedError(typeOfVariableSymbol.name)
            }
        }
    }
    
    fun toFinalOpCode(localVariableIndexAssigner: LocalVariableIndexAssigner): Operation {
        return toFinalOpCode(localVariableIndexAssigner.getLocalVariableIndexOfVariable(variableSymbol))
    }

    abstract fun toFinalOpCode(index: UByte): Operation

    class StoreInt(variableSymbol: Variable) : LocalVariableIndexPlaceHolder(variableSymbol) {
        override fun toFinalOpCode(index: UByte): Operation {
            return when (index) {
                0.toUByte() -> Operation.Istore_0()
                1.toUByte() -> Operation.Istore_1()
                2.toUByte() -> Operation.Istore_2()
                3.toUByte() -> Operation.Istore_3()
                else -> Operation.Istore(index)
            }
        }
    }

    class StoreLong(variableSymbol: Variable) : LocalVariableIndexPlaceHolder(variableSymbol) {
        override fun toFinalOpCode(index: UByte): Operation {
            return when (index) {
                0.toUByte() -> Operation.Lstore_0()
                1.toUByte() -> Operation.Lstore_1()
                2.toUByte() -> Operation.Lstore_2()
                3.toUByte() -> Operation.Lstore_3()
                else -> Operation.Lstore(index)
            }
        }
    }

    class StoreFloat(variableSymbol: Variable) : LocalVariableIndexPlaceHolder(variableSymbol) {
        override fun toFinalOpCode(index: UByte): Operation {
            return when (index) {
                0.toUByte() -> Operation.Fstore_0()
                1.toUByte() -> Operation.Fstore_1()
                2.toUByte() -> Operation.Fstore_2()
                3.toUByte() -> Operation.Fstore_3()
                else -> Operation.Fstore(index)
            }
        }
    }

    class StoreDouble(variableSymbol: Variable) : LocalVariableIndexPlaceHolder(variableSymbol) {
        override fun toFinalOpCode(index: UByte): Operation {
            return when (index) {
                0.toUByte() -> Operation.Dstore_0()
                1.toUByte() -> Operation.Dstore_1()
                2.toUByte() -> Operation.Dstore_2()
                3.toUByte() -> Operation.Dstore_3()
                else -> Operation.Dstore(index)
            }
        }
    }

    class StoreArray(variableSymbol: Variable) : LocalVariableIndexPlaceHolder(variableSymbol) {
        override fun toFinalOpCode(index: UByte): Operation {
            return Operation.Astore(index)
        }
    }
    
    class LoadInt(variableSymbol: Variable) : LocalVariableIndexPlaceHolder(variableSymbol) {
        override fun toFinalOpCode(index: UByte): Operation {
            return when (index) {
                0.toUByte() -> Operation.Iload_0()
                1.toUByte() -> Operation.Iload_1()
                2.toUByte() -> Operation.Iload_2()
                3.toUByte() -> Operation.Iload_3()
                else -> Operation.Iload(index)
            }
        }
    }

    class LoadLong(variableSymbol: Variable) : LocalVariableIndexPlaceHolder(variableSymbol) {
        override fun toFinalOpCode(index: UByte): Operation {
            return when (index) {
                0.toUByte() -> Operation.Lload_0()
                1.toUByte() -> Operation.Lload_1()
                2.toUByte() -> Operation.Lload_2()
                3.toUByte() -> Operation.Lload_3()
                else -> Operation.Lload(index)
            }
        }
    }

    class LoadFloat(variableSymbol: Variable) : LocalVariableIndexPlaceHolder(variableSymbol) {
        override fun toFinalOpCode(index: UByte): Operation {
            return when (index) {
                0.toUByte() -> Operation.Fload_0()
                1.toUByte() -> Operation.Fload_1()
                2.toUByte() -> Operation.Fload_2()
                3.toUByte() -> Operation.Fload_3()
                else -> Operation.Fload(index)
            }
        }
    }

    class LoadDouble(variableSymbol: Variable) : LocalVariableIndexPlaceHolder(variableSymbol) {
        override fun toFinalOpCode(index: UByte): Operation {
            return when (index) {
                0.toUByte() -> Operation.Dload_0()
                1.toUByte() -> Operation.Dload_1()
                2.toUByte() -> Operation.Dload_2()
                3.toUByte() -> Operation.Dload_3()
                else -> Operation.Dload(index)
            }
        }
    }

    class LoadArray(variableSymbol: Variable) : LocalVariableIndexPlaceHolder(variableSymbol) {
        override fun toFinalOpCode(index: UByte): Operation {
            return when (index) {
                0.toUByte() -> Operation.Aload_0()
                1.toUByte() -> Operation.Aload_1()
                2.toUByte() -> Operation.Aload_2()
                3.toUByte() -> Operation.Aload_3()
                else -> Operation.Aload(index)
            }
        }
    }

    class IncreaseInt(variableSymbol: Variable, private val byteToIncreaseBy: Byte) : LocalVariableIndexPlaceHolder(variableSymbol) {
        override fun toFinalOpCode(index: UByte): Operation {
            return Operation.Iinc(index, byteToIncreaseBy)
        }
    }
}