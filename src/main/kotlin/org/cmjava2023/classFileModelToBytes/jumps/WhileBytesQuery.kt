package org.cmjava2023.classFileModelToBytes.jumps

import org.cmjava2023.classFileModelToBytes.ConstantPoolBuilder
import org.cmjava2023.classFileModelToBytes.LocalVariableIndexAssigner
import org.cmjava2023.classfilespecification.opCodes.jumps.PlaceHolderWhile

class WhileBytesQuery {
    companion object {
        fun fetch(constantPoolBuilder: ConstantPoolBuilder, localVariableIndexAssigner: LocalVariableIndexAssigner, placeHolderWhileBlock: PlaceHolderWhile): List<Byte> {
            return listOf()
        }
    }
}