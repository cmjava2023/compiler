package org.cmjava2023.parsedClassFileDataToBytes.jumps

import org.cmjava2023.classfilespecification.constantpool.ConstantPoolEntry
import org.cmjava2023.classfilespecification.Operation
import org.cmjava2023.placeHolders.PlaceHolder

class NumberOfBytesForPlaceHolderQuery {
    companion object {
        private const val OPCODE_VALUE_SIZE_BYTES = UByte.SIZE_BYTES
        private const val CONSTANT_POOL_INDEX_SIZE_BYTES = UShort.SIZE_BYTES
        
        fun fetch(placeHolder: PlaceHolder): Int {
            return when(placeHolder) {
                is Operation -> OPCODE_VALUE_SIZE_BYTES + placeHolder.operands.sumOf {
                    when (it) {
                        is UShort -> UShort.SIZE_BYTES
                        is UByte -> UByte.SIZE_BYTES
                        is Byte -> Byte.SIZE_BYTES
                        is Short -> Short.SIZE_BYTES
                        is ConstantPoolEntry -> CONSTANT_POOL_INDEX_SIZE_BYTES
                        is Operation.ArrayType -> UByte.SIZE_BYTES
                        else -> throw NotImplementedError(it.javaClass.name)
                    }
                }
                else -> throw NotImplementedError(placeHolder.javaClass.name)
            }
        }
    }
}