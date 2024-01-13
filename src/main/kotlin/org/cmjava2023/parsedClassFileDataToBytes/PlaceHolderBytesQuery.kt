package org.cmjava2023.parsedClassFileDataToBytes

import org.cmjava2023.parsedClassFileDataToBytes.jumps.IfElseIfsElseBytesQuery
import org.cmjava2023.parsedClassFileDataToBytes.jumps.WhileBytesQuery
import org.cmjava2023.classfilespecification.Operation
import org.cmjava2023.classfilespecification.constantpool.ConstantPoolEntry
import org.cmjava2023.placeHolders.jumps.PlaceHolderIfElseIfsElse
import org.cmjava2023.placeHolders.jumps.PlaceHolderWhile
import org.cmjava2023.placeHolders.LoadConstantPlaceHolder
import org.cmjava2023.placeHolders.LocalVariableIndexPlaceHolder
import org.cmjava2023.placeHolders.PlaceHolder
import org.cmjava2023.util.ByteListUtil.Companion.add

class PlaceHolderBytesQuery {
    companion object {
        fun fetch(constantPoolBuilder: ConstantPoolBuilder, localVariableIndexAssigner: LocalVariableIndexAssigner, PlaceHolder: PlaceHolder): List<Byte> {
            return when(PlaceHolder) {
                is Operation -> getFinalOpCodeBytes(constantPoolBuilder, PlaceHolder)
                is LoadConstantPlaceHolder -> getFinalOpCodeBytes(constantPoolBuilder, PlaceHolder.toFinalOpCode(constantPoolBuilder))
                is LocalVariableIndexPlaceHolder -> getFinalOpCodeBytes(constantPoolBuilder, PlaceHolder.toFinalOpCode(localVariableIndexAssigner))
                is PlaceHolderIfElseIfsElse -> IfElseIfsElseBytesQuery.fetch(constantPoolBuilder, localVariableIndexAssigner, PlaceHolder)
                is PlaceHolderWhile -> WhileBytesQuery.fetch(constantPoolBuilder, localVariableIndexAssigner, PlaceHolder)
                else -> throw NotImplementedError(PlaceHolder.javaClass.name)
            }
        }
        
        private fun getFinalOpCodeBytes(constantPoolBuilder: ConstantPoolBuilder, operation: Operation): List<Byte> {
            val result = mutableListOf<Byte>()
            result.add(operation.opCodeValue)
            for (value in operation.operands) {
                when (value) {
                    is UShort -> result.add(value)
                    is UByte -> result.add(value)
                    is Byte -> result.add(value)
                    is Short -> result.add(value)
                    is ConstantPoolEntry -> result.add(constantPoolBuilder.getIndexByResolvingOrAdding(value))
                    is Operation.ArrayType -> result.add(value.code)
                    else -> throw NotImplementedError(value.javaClass.name)
                }
            }
            return result
        }
    }
}