package org.cmjava2023.classfilespecification.opCodes.queries

import org.cmjava2023.classfilespecification.constantpool.FloatConstantPoolEntry
import org.cmjava2023.classfilespecification.opCodes.PlaceHolderLoadConstantPoolItem
import org.cmjava2023.classfilespecification.opCodes.OpCode
import org.cmjava2023.classfilespecification.opCodes.OpCodeOrPlaceHolder

class OpCodeToPutFloatOnStackQuery {
    companion object {
        fun fetch(float: Float): OpCodeOrPlaceHolder {
            return when (float) {
                0f -> OpCode.Fconst_0()
                1f -> OpCode.Fconst_1()
                2f -> OpCode.Fconst_2()
                else -> PlaceHolderLoadConstantPoolItem(FloatConstantPoolEntry(float))
            }
        }
    }
}