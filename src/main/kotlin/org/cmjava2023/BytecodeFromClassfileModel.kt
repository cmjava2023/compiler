package org.cmjava2023.org.cmjava2023

import org.cmjava2023.ByteListUtil.Companion.add
import org.cmjava2023.classfilespecification.ClassfileModel
import org.cmjava2023.classfilespecification.ConstantPoolToByteList
import kotlin.experimental.or


class BytecodeFromClassfileModel {

    fun generate(model: ClassfileModel): ByteArray {

        val result: MutableList<Byte> = mutableListOf()
        val classFileBytes = ConstantPoolToByteList.mapToByteList(model.constantPool, model.methodDefinitions)

        val indexOfThisClassInConstantPool = 1.toShort()
        val indexOfSuperClassInConstantPool = 3.toShort()

        result.add(magicNumber)
        result.add(minorVersion)
        result.add(majorVersion)
        result.add(classFileBytes.constantPoolItemCount)
        result.addAll(classFileBytes.constantPoolBytes)
        result.add(model.classClassAccessModifiers.map { it.value }.reduce { combinedFlag, flag -> combinedFlag or flag })
        result.add(indexOfThisClassInConstantPool)
        result.add(indexOfSuperClassInConstantPool)
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
        const val magicNumber: UInt = 0xCAFEBABEu
        const val minorVersion: Short = 0x0
        const val majorVersion: Short = 0x00000034
    }
}

