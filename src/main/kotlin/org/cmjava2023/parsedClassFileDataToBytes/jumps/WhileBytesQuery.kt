package org.cmjava2023.parsedClassFileDataToBytes.jumps

import org.cmjava2023.parsedClassFileDataToBytes.ConstantPoolBuilder
import org.cmjava2023.parsedClassFileDataToBytes.LocalVariableIndexAssigner
import org.cmjava2023.placeHolders.jumps.PlaceHolderWhile

class WhileBytesQuery {
    companion object {
        fun fetch(constantPoolBuilder: ConstantPoolBuilder, localVariableIndexAssigner: LocalVariableIndexAssigner, placeHolderWhileBlock: PlaceHolderWhile): List<Byte> {
            throw NotImplementedError()
        }
    }
}