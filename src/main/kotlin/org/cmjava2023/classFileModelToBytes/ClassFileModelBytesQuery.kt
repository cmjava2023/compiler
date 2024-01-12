package org.cmjava2023.classFileModelToBytes

import org.cmjava2023.util.ByteListUtil.Companion.add
import org.cmjava2023.classfilespecification.ClassfileModel
import org.cmjava2023.classfilespecification.constantpool.ClassConstantPoolEntry
import org.cmjava2023.classfilespecification.constantpool.ConstantPoolEntry
import org.cmjava2023.util.AccessModifierUtil.Companion.bitwiseOrCombine


class ClassFileModelBytesQuery {

    private fun addAllClassesToConstantPool(constantPoolBuilder: ConstantPoolBuilder, constantPoolEntries: List<ConstantPoolEntry>) {
        constantPoolEntries.filterIsInstance<ClassConstantPoolEntry>().forEach { constantPoolBuilder.getIndexByResolvingOrAdding(it) }
    }

    fun fetch(model: ClassfileModel): ByteArray {
        val constantPoolBuilder = ConstantPoolBuilder()
        addAllClassesToConstantPool(constantPoolBuilder, model.constantPoolEntries)
        val bytesOfMethodInfos = model.methodDefinitions.flatMap { MethodInfoBytesQuery.fetch(constantPoolBuilder, it) }

        val result: MutableList<Byte> = mutableListOf()
        result.add(MAGIC_NUMBER)
        result.add(MINOR_VERSION)
        result.add(MAJOR_VERSION)
        result.add(constantPoolBuilder.itemCount)
        result.addAll(constantPoolBuilder.resultBytes)
        result.add(model.classClassAccessModifiers.map { it.value }.bitwiseOrCombine())
        result.add(INDEX_OF_THIS_CLASS_IN_CONSTANT_POOL)
        result.add(INDEX_OF_SUPER_CLASS_IN_CONSTANT_POOL)
        result.add(model.numberOfInterfaces)
        result.addAll(model.interfaceDefinitions)
        result.add(model.numberOfFields)
        result.addAll(model.fieldDefinitions)
        result.add(model.methodDefinitions.size.toUShort())
        result.addAll(bytesOfMethodInfos)
        result.add(model.attributesCount)
        result.addAll(model.attributeDefinitions)

        return result.toByteArray()
    }

    companion object {
        const val MAGIC_NUMBER: UInt = 0xCAFEBABEu
        const val MINOR_VERSION: UShort = 0u
        const val MAJOR_VERSION: UShort = 50u

        const val INDEX_OF_THIS_CLASS_IN_CONSTANT_POOL: UShort = 2u
        const val INDEX_OF_SUPER_CLASS_IN_CONSTANT_POOL: UShort = 4u
    }
}

