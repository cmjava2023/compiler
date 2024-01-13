package org.cmjava2023.parsedClassFileDataToBytes

import org.cmjava2023.classfilespecification.constantpool.MethodTypeDescriptor
import org.cmjava2023.placeHolders.PlaceHolder
import org.cmjava2023.util.ByteListUtil.Companion.add

class CodeAttributeBytesQuery {
    companion object {
        fun fetch(
            constantPoolBuilder: ConstantPoolBuilder,
            code: List<PlaceHolder>,
            codeAttributeNameIndex: UShort,
            methodTypeDescriptor: MethodTypeDescriptor
        ): List<Byte> {
            val attributeBytesCountedForLength = mutableListOf<Byte>()

            val maxStackSize: UShort = 5u
            attributeBytesCountedForLength.add(maxStackSize)

            val localVariableIndexAssigner = LocalVariableIndexAssigner(methodTypeDescriptor)
            val codeBytes = code.flatMap { PlaceHolderBytesQuery.fetch(constantPoolBuilder, localVariableIndexAssigner, it) }
            attributeBytesCountedForLength.add(localVariableIndexAssigner.maxLocalVariableSize)

            val sizeOfCode = codeBytes.size.toUInt()
            attributeBytesCountedForLength.add(sizeOfCode)
            attributeBytesCountedForLength.addAll(codeBytes)

            val exceptionTableLength: UShort = 0u
            attributeBytesCountedForLength.add(exceptionTableLength)
            val attributesCount: UShort = 0u
            attributeBytesCountedForLength.add(attributesCount)

            val result = mutableListOf<Byte>()
            result.add(codeAttributeNameIndex)
            val sizeOfAttribute = attributeBytesCountedForLength.size.toUInt()
            result.add(sizeOfAttribute)
            result.addAll(attributeBytesCountedForLength)
            return result
        }
    }
}