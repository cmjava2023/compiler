package org.cmjava2023.classfilespecification.opCodes.queries

import org.cmjava2023.classfilespecification.constantpool.LongConstantInfo
import org.cmjava2023.classfilespecification.opCodes.OpCode
import org.cmjava2023.classfilespecification.opCodes.OpCodeOrPlaceHolder

class OpCodeToPutLongOnStackQuery {
    companion object {
        fun fetch(long: Long): OpCodeOrPlaceHolder {
            return when (long) {
                0L -> OpCode.Lconst_0()
                1L -> OpCode.Lconst_1()
                else -> OpCode.Ldc2_w(LongConstantInfo(long))
            }
        }
    }
}