package org.cmjava2023.classFileDataToBytes

import org.cmjava2023.util.ByteListUtil.Companion.add
import org.cmjava2023.classfilespecification.ClassfileData
import org.cmjava2023.classfilespecification.constantpool.ConstantPoolEntry
import org.cmjava2023.util.AccessModifierUtil.Companion.bitwiseOrCombine


class ClassFileDataBytesQuery(private val constantPoolBuilder: ConstantPoolBuilder) {

    fun fetch(model: ClassfileData): ByteArray {
        val bytesOfMethodInfos = model.methodDefinitions.flatMap { MethodInfoBytesQuery.fetch(constantPoolBuilder, it) }

        val result: MutableList<Byte> = mutableListOf()
        result.add(MAGIC_NUMBER)
        result.add(MINOR_VERSION)
        result.add(MAJOR_VERSION)
        result.add(constantPoolBuilder.itemCount())
        result.addAll(constantPoolBuilder.resultBytes())
        result.add(model.classAccessModifiers.map { it.value }.bitwiseOrCombine())
        result.add(INDEX_OF_THIS_CLASS_IN_CONSTANT_POOL)
        result.add(INDEX_OF_SUPER_CLASS_IN_CONSTANT_POOL)
        result.add(0.toUShort()) // numberOfInterfaces
        result.add(0.toUShort()) // numberOfFields
        result.add(model.methodDefinitions.size.toUShort())
        result.addAll(bytesOfMethodInfos)
        result.add(0.toUShort()) // numberOfAttributes

        return result.toByteArray()
    }

    companion object {
        const val MAGIC_NUMBER: UInt = 0xCAFEBABEu
        const val MINOR_VERSION: UShort = 0u
        const val MAJOR_VERSION: UShort = 49u

        const val INDEX_OF_THIS_CLASS_IN_CONSTANT_POOL: UShort = 2u
        const val INDEX_OF_SUPER_CLASS_IN_CONSTANT_POOL: UShort = 4u
    }
}

