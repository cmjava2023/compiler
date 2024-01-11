package org.cmjava2023.classfilespecification.opCodes

import org.cmjava2023.classFileModelToBytes.ConstantPoolBuilder
import org.cmjava2023.classfilespecification.constantpool.ConstantInfo

class PlaceHolderLoadingConstantInfo(val constantInfo: ConstantInfo) : PlaceHolder {    
    fun toFinalOpCode(constantPoolBuilder: ConstantPoolBuilder): OpCode {
        val index = constantPoolBuilder.getIndexByResolvingOrAdding(constantInfo)
        return if (index <= UByte.MAX_VALUE) {
            OpCode.Ldc(index.toUByte())
        } else {
            OpCode.Ldc_w(index)
        }
    }
}

