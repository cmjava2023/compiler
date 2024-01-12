package org.cmjava2023.classfilespecification.opCodes

import org.cmjava2023.classFileModelToBytes.ConstantPoolBuilder
import org.cmjava2023.classfilespecification.constantpool.ConstantPoolEntry

class PlaceHolderLoadConstantPoolItem(val constantPoolEntry: ConstantPoolEntry) : PlaceHolder {    
    fun toFinalOpCode(constantPoolBuilder: ConstantPoolBuilder): OpCode {
        val index = constantPoolBuilder.getIndexByResolvingOrAdding(constantPoolEntry)
        return if (index <= UByte.MAX_VALUE) {
            OpCode.Ldc(index.toUByte())
        } else {
            OpCode.Ldc_w(index)
        }
    }
}

