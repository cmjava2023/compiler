package org.cmjava2023.org.cmjava2023

import org.cmjava2023.ClassfileModel
import java.nio.ByteBuffer


class BytecodeFromClassfileModel {

    fun generate(model: ClassfileModel): ByteArray {

        val result: MutableList<Byte> = mutableListOf()

        result.add(magicNumber)
        result.add(minorVersion)
        result.add(majorVersion)
        result.add(model.sizeOfConstantPool)
        result.addAll(model.constantPool)
        result.add(model.classAccessModifiers)
        result.addAll(model.constantPool)
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

        fun MutableList<Byte>.add(item: UInt){
            this.addAll(ByteBuffer.allocate(UInt.SIZE_BYTES).putInt(item.toInt()).array().toList())
        }

        fun MutableList<Byte>.add(item: Short){
            this.addAll(ByteBuffer.allocate(Short.SIZE_BYTES).putShort(item).array().toList())
        }
    }
}

