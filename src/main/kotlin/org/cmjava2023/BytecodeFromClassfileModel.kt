package org.cmjava2023.org.cmjava2023

import org.cmjava2023.ClassfileModel
import org.cmjava2023.ByteListUtil.Companion.add
import org.cmjava2023.ByteListUtil.Companion.toByteList
import org.cmjava2023.classfilespecification.ConstantPoolToByteList


class BytecodeFromClassfileModel {

    fun generate(model: ClassfileModel): ByteArray {

        val result: MutableList<Byte> = mutableListOf()
        val (constantPoolByteList, constantPoolCount) = ConstantPoolToByteList.mapToByteList(model.constantPool)

        result.add(magicNumber)
        result.add(minorVersion)
        result.add(majorVersion)
        result.add(constantPoolCount)
        result.addAll(constantPoolByteList)
        result.add(model.classAccessModifiers)
        result.addAll(2.toShort().toByteList())
        result.add(model.superClassIndexInConstantPool)
        result.add(model.numberOfInterfaces)
        result.addAll(model.interfaceDefinitions)
        result.add(model.numberOfFields)
        result.addAll(model.fieldDefinitions)
        result.add(model.numberOfMethods)
        result.addAll(model.methodDefinitions)
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

