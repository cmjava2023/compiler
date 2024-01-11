package org.cmjava2023.classfilespecification.opCodes

import org.cmjava2023.classFileModelToBytes.LocalVariableIndexAssigner
import org.cmjava2023.symboltable.Variable

abstract class PlaceHolderUsingLocalVariableIndex(val variableSymbol: Variable) : PlaceHolder {
    fun toFinalOpCode(localVariableIndexAssigner: LocalVariableIndexAssigner): OpCode {
        return toFinalOpCode(localVariableIndexAssigner.getLocalVariableIndexOfVariable(variableSymbol))
    }

    abstract fun toFinalOpCode(index: UByte): OpCode

    class StoreInt(variableSymbol: Variable) : PlaceHolderUsingLocalVariableIndex(variableSymbol) {
        override fun toFinalOpCode(index: UByte): OpCode {
            return when (index) {
                0.toUByte() -> OpCode.Istore_0()
                1.toUByte() -> OpCode.Istore_1()
                2.toUByte() -> OpCode.Istore_2()
                3.toUByte() -> OpCode.Istore_3()
                else -> OpCode.Istore(index)
            }
        }
    }

    class StoreLong(variableSymbol: Variable) : PlaceHolderUsingLocalVariableIndex(variableSymbol) {
        override fun toFinalOpCode(index: UByte): OpCode {
            return when (index) {
                0.toUByte() -> OpCode.Lstore_0()
                1.toUByte() -> OpCode.Lstore_1()
                2.toUByte() -> OpCode.Lstore_2()
                3.toUByte() -> OpCode.Lstore_3()
                else -> OpCode.Lstore(index)
            }
        }
    }

    class StoreFloat(variableSymbol: Variable) : PlaceHolderUsingLocalVariableIndex(variableSymbol) {
        override fun toFinalOpCode(index: UByte): OpCode {
            return when (index) {
                0.toUByte() -> OpCode.Fstore_0()
                1.toUByte() -> OpCode.Fstore_1()
                2.toUByte() -> OpCode.Fstore_2()
                3.toUByte() -> OpCode.Fstore_3()
                else -> OpCode.Fstore(index)
            }
        }
    }

    class StoreDouble(variableSymbol: Variable) : PlaceHolderUsingLocalVariableIndex(variableSymbol) {
        override fun toFinalOpCode(index: UByte): OpCode {
            return when (index) {
                0.toUByte() -> OpCode.Dstore_0()
                1.toUByte() -> OpCode.Dstore_1()
                2.toUByte() -> OpCode.Dstore_2()
                3.toUByte() -> OpCode.Dstore_3()
                else -> OpCode.Dstore(index)
            }
        }
    }

    class StoreArray(variableSymbol: Variable) : PlaceHolderUsingLocalVariableIndex(variableSymbol) {
        override fun toFinalOpCode(index: UByte): OpCode {
            return OpCode.Astore(index)
        }
    }
    
    class LoadInt(variableSymbol: Variable) : PlaceHolderUsingLocalVariableIndex(variableSymbol) {
        override fun toFinalOpCode(index: UByte): OpCode {
            return when (index) {
                0.toUByte() -> OpCode.Iload_0()
                1.toUByte() -> OpCode.Iload_1()
                2.toUByte() -> OpCode.Iload_2()
                3.toUByte() -> OpCode.Iload_3()
                else -> OpCode.Iload(index)
            }
        }
    }

    class LoadLong(variableSymbol: Variable) : PlaceHolderUsingLocalVariableIndex(variableSymbol) {
        override fun toFinalOpCode(index: UByte): OpCode {
            return when (index) {
                0.toUByte() -> OpCode.Lload_0()
                1.toUByte() -> OpCode.Lload_1()
                2.toUByte() -> OpCode.Lload_2()
                3.toUByte() -> OpCode.Lload_3()
                else -> OpCode.Lload(index)
            }
        }
    }

    class LoadFloat(variableSymbol: Variable) : PlaceHolderUsingLocalVariableIndex(variableSymbol) {
        override fun toFinalOpCode(index: UByte): OpCode {
            return when (index) {
                0.toUByte() -> OpCode.Fload_0()
                1.toUByte() -> OpCode.Fload_1()
                2.toUByte() -> OpCode.Fload_2()
                3.toUByte() -> OpCode.Fload_3()
                else -> OpCode.Fload(index)
            }
        }
    }

    class LoadDouble(variableSymbol: Variable) : PlaceHolderUsingLocalVariableIndex(variableSymbol) {
        override fun toFinalOpCode(index: UByte): OpCode {
            return when (index) {
                0.toUByte() -> OpCode.Dload_0()
                1.toUByte() -> OpCode.Dload_1()
                2.toUByte() -> OpCode.Dload_2()
                3.toUByte() -> OpCode.Dload_3()
                else -> OpCode.Dload(index)
            }
        }
    }

    class LoadArray(variableSymbol: Variable) : PlaceHolderUsingLocalVariableIndex(variableSymbol) {
        override fun toFinalOpCode(index: UByte): OpCode {
            return when (index) {
                0.toUByte() -> OpCode.Aload_0()
                1.toUByte() -> OpCode.Aload_1()
                2.toUByte() -> OpCode.Aload_2()
                3.toUByte() -> OpCode.Aload_3()
                else -> OpCode.Aload(index)
            }
        }
    }

    class IncreaseInt(variableSymbol: Variable, private val byteToIncreaseBy: Byte) : PlaceHolderUsingLocalVariableIndex(variableSymbol) {
        override fun toFinalOpCode(index: UByte): OpCode {
            return OpCode.Iinc(index, byteToIncreaseBy)
        }
    }
}