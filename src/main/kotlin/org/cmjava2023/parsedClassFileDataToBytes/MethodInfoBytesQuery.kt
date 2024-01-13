package org.cmjava2023.parsedClassFileDataToBytes

import org.cmjava2023.classfilespecification.MethodInfo
import org.cmjava2023.classfilespecification.attributeInfo.CodeAttributeInfo
import org.cmjava2023.classfilespecification.constantpool.ConstantPoolEntry
import org.cmjava2023.util.AccessModifierUtil.Companion.bitwiseOrCombine
import org.cmjava2023.util.ByteListUtil.Companion.add

class MethodInfoBytesQuery {
    companion object {
        fun fetch(constantPoolBuilder: ConstantPoolBuilder, methodInfo: MethodInfo): List<Byte> {
            val bytesOfMethodInfo = mutableListOf<Byte>()
            bytesOfMethodInfo.add(
                methodInfo.accessModifiers.map { it.value }.bitwiseOrCombine()
            )
            val methodNameIndex = constantPoolBuilder.addUtf8ConstantAndGetStartIndex(methodInfo.name)
            bytesOfMethodInfo.add(methodNameIndex)

            val methodTypeIndex = constantPoolBuilder.addUtf8ConstantAndGetStartIndex(ConstantPoolEntry.Utf8Constant(methodInfo.typeDescriptor.stringRepresentation))
            bytesOfMethodInfo.add(methodTypeIndex)
            bytesOfMethodInfo.add(methodInfo.attributes.size.toUShort())

            val codeAttributeInfo = methodInfo.attributes.filterIsInstance<CodeAttributeInfo>().singleOrNull()
            if (codeAttributeInfo != null) {
                val codeAttributeNameIndex = constantPoolBuilder.addUtf8ConstantAndGetStartIndex(codeAttributeInfo.name)
                bytesOfMethodInfo.addAll(CodeAttributeBytesQuery.fetch(constantPoolBuilder, codeAttributeInfo.code, codeAttributeNameIndex, methodInfo.typeDescriptor))
            } else {
                throw NotImplementedError()
            }

            return bytesOfMethodInfo
        }

    }    
}