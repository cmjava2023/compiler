package org.cmjava2023.classfilespecification.opCodes.queries

import org.cmjava2023.classfilespecification.constantpool.DoubleConstantPoolEntry
import org.cmjava2023.classfilespecification.opCodes.OpCode
import org.cmjava2023.classfilespecification.opCodes.OpCodeOrPlaceHolder

class OpCodeToPutDoubleOnStackQuery {
    companion object {
        fun fetch(double: Double): OpCodeOrPlaceHolder {
            return when (double) {
                0.0 -> OpCode.Dconst_0()
                1.0 -> OpCode.Dconst_1()
                else -> OpCode.Ldc2_w(DoubleConstantPoolEntry(double))
            }
        }
    }
}