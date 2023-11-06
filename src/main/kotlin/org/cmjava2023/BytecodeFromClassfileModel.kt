package org.cmjava2023

import org.cmjava2023.ByteListUtil.Companion.add
import org.cmjava2023.classfilespecification.ClassfileModel
import kotlin.experimental.or


class BytecodeFromClassfileModel {

    fun generate(model: ClassfileModel): ByteArray {
        val classFileBytes = ConstantPoolToByteList.mapToByteList(model.constantPool, model.methodDefinitions)

        val result: MutableList<Byte> = mutableListOf()
        result.add(MAGIC_NUMBER)
        result.add(MINOR_VERSION)
        result.add(MAJOR_VERSION)
        result.add(classFileBytes.constantPoolItemCount)
        result.addAll(classFileBytes.constantPoolBytes)
        result.add(model.classClassAccessModifiers.map { it.value }.reduce { combinedFlag, flag -> combinedFlag or flag })
        result.add(INDEX_OF_THIS_CLASS_IN_CONSTANT_POOL)
        result.add(INDEX_OF_SUPER_CLASS_IN_CONSTANT_POOL)
        result.add(model.numberOfInterfaces)
        result.addAll(model.interfaceDefinitions)
        result.add(model.numberOfFields)
        result.addAll(model.fieldDefinitions)
        result.add(classFileBytes.methodInfoCount)
        result.addAll(classFileBytes.methodInfosBytes)
        result.add(model.attributesCount)
        result.addAll(model.attributeDefinitions)

        return result.toByteArray()
    }

    companion object {
        const val MAGIC_NUMBER: UInt = 0xCAFEBABEu
        const val MINOR_VERSION: Short = 0x0
        const val MAJOR_VERSION: Short = 0x00000034

        const val INDEX_OF_THIS_CLASS_IN_CONSTANT_POOL:Short = 1
        const val INDEX_OF_SUPER_CLASS_IN_CONSTANT_POOL:Short = 3
    }
}

