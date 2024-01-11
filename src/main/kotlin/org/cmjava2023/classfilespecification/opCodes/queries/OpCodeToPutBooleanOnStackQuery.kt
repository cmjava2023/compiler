package org.cmjava2023.classfilespecification.opCodes.queries

import org.cmjava2023.classfilespecification.opCodes.OpCode
import org.cmjava2023.classfilespecification.opCodes.OpCodeOrPlaceHolder

class OpCodeToPutBooleanOnStackQuery {
    companion object {
        fun fetch(boolean: Boolean): OpCodeOrPlaceHolder {
            return when (boolean) {
                true -> OpCode.Iconst_1()
                false -> OpCode.Iconst_0()
            }
        }
    }
}