package org.cmjava2023.classFileModelToBytes.jumps

import org.cmjava2023.classfilespecification.constantpool.ConstantInfo
import org.cmjava2023.classfilespecification.opCodes.OpCode
import org.cmjava2023.classfilespecification.opCodes.OpCodeOrPlaceHolder

class NumberOfBytesForOpCodeOrPlaceHolderQuery {
    companion object {
        private const val OPCODE_VALUE_SIZE_BYTES = UByte.SIZE_BYTES
        private const val CONSTANT_POOL_INDEX_SIZE_BYTES = UShort.SIZE_BYTES
        
        fun fetch(opCodeOrPlaceHolder: OpCodeOrPlaceHolder): Int {
            return when(opCodeOrPlaceHolder) {
                is OpCode -> OPCODE_VALUE_SIZE_BYTES + opCodeOrPlaceHolder.values.sumOf {
                    when (it) {
                        is UShort -> UShort.SIZE_BYTES
                        is UByte -> UByte.SIZE_BYTES
                        is Byte -> Byte.SIZE_BYTES
                        is Short -> Short.SIZE_BYTES
                        is ConstantInfo -> CONSTANT_POOL_INDEX_SIZE_BYTES
                        is OpCode.ArrayType -> UByte.SIZE_BYTES
                        else -> throw NotImplementedError(it.javaClass.name)
                    }
                }
                else -> throw NotImplementedError(opCodeOrPlaceHolder.javaClass.name)
            }
        }
    }
}