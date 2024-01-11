package org.cmjava2023.classFileModelToBytes

import org.cmjava2023.classFileModelToBytes.jumps.IfElseIfsElseBytesQuery
import org.cmjava2023.classFileModelToBytes.jumps.WhileBytesQuery
import org.cmjava2023.classfilespecification.constantpool.ConstantInfo
import org.cmjava2023.classfilespecification.opCodes.*
import org.cmjava2023.classfilespecification.opCodes.jumps.PlaceHolderIfElseIfsElse
import org.cmjava2023.classfilespecification.opCodes.jumps.PlaceHolderWhile
import org.cmjava2023.util.ByteListUtil.Companion.add

class OpCodeOrPlaceHolderBytesQuery {
    companion object {
        fun fetch(constantPoolBuilder: ConstantPoolBuilder, localVariableIndexAssigner: LocalVariableIndexAssigner, opCodeOrPlaceHolder: OpCodeOrPlaceHolder): List<Byte> {
            return when(opCodeOrPlaceHolder) {
                is OpCode -> getFinalOpCodeBytes(constantPoolBuilder, opCodeOrPlaceHolder)
                is PlaceHolderLoadingConstantInfo -> getFinalOpCodeBytes(constantPoolBuilder, opCodeOrPlaceHolder.toFinalOpCode(constantPoolBuilder))
                is PlaceHolderUsingLocalVariableIndex -> getFinalOpCodeBytes(constantPoolBuilder, opCodeOrPlaceHolder.toFinalOpCode(localVariableIndexAssigner))
                is PlaceHolderIfElseIfsElse -> IfElseIfsElseBytesQuery.fetch(constantPoolBuilder, localVariableIndexAssigner, opCodeOrPlaceHolder)
                is PlaceHolderWhile -> WhileBytesQuery.fetch(constantPoolBuilder, localVariableIndexAssigner, opCodeOrPlaceHolder)
                else -> throw NotImplementedError(opCodeOrPlaceHolder.javaClass.name)
            }
        }
        
        private fun getFinalOpCodeBytes(constantPoolBuilder: ConstantPoolBuilder, opCode: OpCode): List<Byte> {
            val result = mutableListOf<Byte>()
            result.add(opCode.opCodeValue)
            for (value in opCode.values) {
                when (value) {
                    is UShort -> result.add(value)
                    is UByte -> result.add(value)
                    is Byte -> result.add(value)
                    is Short -> result.add(value)
                    is ConstantInfo -> result.add(constantPoolBuilder.getIndexByResolvingOrAdding(value))
                    is OpCode.ArrayType -> result.add(value.code)
                    else -> throw NotImplementedError(value.javaClass.name)
                }
            }
            return result
        }
    }
}