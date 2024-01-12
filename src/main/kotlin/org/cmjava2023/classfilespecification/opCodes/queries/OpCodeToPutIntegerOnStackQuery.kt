package org.cmjava2023.classfilespecification.opCodes.queries

import org.cmjava2023.classfilespecification.constantpool.IntegerConstantPoolEntry
import org.cmjava2023.classfilespecification.opCodes.OpCode
import org.cmjava2023.classfilespecification.opCodes.OpCodeOrPlaceHolder
import org.cmjava2023.classfilespecification.opCodes.PlaceHolderLoadConstantPoolItem

class OpCodeToPutIntegerOnStackQuery {
    companion object {
        fun fetch(int: Int): OpCodeOrPlaceHolder {
            return when (int) {
                -1 -> OpCode.Iconst_m1()
                0 -> OpCode.Iconst_0()
                1 -> OpCode.Iconst_1()
                2 -> OpCode.Iconst_2()
                3 -> OpCode.Iconst_3()
                4 -> OpCode.Iconst_4()
                5 -> OpCode.Iconst_5()
                else -> if (int >= Byte.MIN_VALUE && int <= Byte.MAX_VALUE) {
                    OpCode.Bipush(int.toByte())
                } else if (int >= Short.MIN_VALUE && int <= Short.MAX_VALUE) {
                    OpCode.Sipush(int.toShort())
                } else {
                    PlaceHolderLoadConstantPoolItem(IntegerConstantPoolEntry(int))
                }
            }
        }
    }
}