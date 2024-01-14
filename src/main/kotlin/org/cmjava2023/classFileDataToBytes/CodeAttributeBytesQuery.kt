package org.cmjava2023.classFileDataToBytes

import org.cmjava2023.classfilespecification.attributeInfo.CodeAttributeInfo
import org.cmjava2023.util.ByteListUtil.Companion.add

class CodeAttributeBytesQuery {
    companion object {
        fun fetch(
            constantPoolBuilder: ConstantPoolBuilder,
            codeAttributeInfo: CodeAttributeInfo,
            codeAttributeNameIndex: UShort
        ): List<Byte> {
            val attributeBytesCountedForLength = mutableListOf<Byte>()

            val maxStackSize: UShort = 5u
            attributeBytesCountedForLength.add(maxStackSize)
            attributeBytesCountedForLength.add(codeAttributeInfo.maxLocalVariables)
            val placeHolderBytesQuery = PlaceHolderBytesQuery(constantPoolBuilder)
            val codeBytes = codeAttributeInfo.code.flatMap { placeHolderBytesQuery.fetch(it) }
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