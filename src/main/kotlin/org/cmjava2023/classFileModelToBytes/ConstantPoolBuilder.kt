package org.cmjava2023.classFileModelToBytes

import org.cmjava2023.classfilespecification.constantpool.*
import org.cmjava2023.util.ByteListUtil.Companion.add

class ConstantPoolBuilder {
    private var lastOccupiedConstantPoolIndex: UShort = 0u
    private var bytesAtIndex = mutableListOf<List<Byte>>()
    
    val itemCount = (lastOccupiedConstantPoolIndex + 1u).toUShort()
    val resultBytes = bytesAtIndex.flatten()
    
    fun getIndexByResolvingOrAdding(constantInfo: ConstantInfo): UShort {
        return when (constantInfo) {
            is ReferenceConstantInfo -> addReferenceConstantInfoAndGetStartIndex(constantInfo)
            is StringConstantInfo -> addStringConstantInfoAndGetStartIndex(constantInfo)
            is ClassConstantInfo -> addClassConstantInfoAndGetStartIndex(constantInfo)
            is IntegerConstantInfo -> addIntegerConstantInfoAndGetStartIndex(constantInfo)
            is LongConstantInfo -> addLongConstantInfoAndGetStartIndex(constantInfo)
            is FloatConstantInfo -> addFloatConstantInfoAndGetStartIndex(constantInfo)
            is DoubleConstantInfo -> addDoubleConstantInfoAndGetStartIndex(constantInfo)
            else -> throw NotImplementedError(constantInfo.javaClass.name)
        }
    }
    
    fun addUtf8ConstantAndGetStartIndex(utf8ConstantInfo: Utf8ConstantInfo): UShort {
        val dataBytes = utf8ConstantInfo.getStringLengthBytes()
            .plus(utf8ConstantInfo.getUtf8Bytes())
        return assureOnConstantPoolAndGetIndex(utf8ConstantInfo, dataBytes)
    }

    private fun addReferenceConstantInfoAndGetStartIndex(referenceConstantInfo: ReferenceConstantInfo): UShort {
        val classIndex = addClassConstantInfoAndGetStartIndex(referenceConstantInfo.classConstantInfo)
        val nameAndTypeIndex = addNameAndTypeConstantInfoAndGetStartIndex(referenceConstantInfo.nameAndTypeConstantInfo)

        val dataBytes = mutableListOf<Byte>()
        dataBytes.add(classIndex)
        dataBytes.add(nameAndTypeIndex)

        return assureOnConstantPoolAndGetIndex(referenceConstantInfo, dataBytes)
    }

    private fun addStringConstantInfoAndGetStartIndex(stringConstantInfo: StringConstantInfo): UShort {
        val valueIndex = addUtf8ConstantAndGetStartIndex(stringConstantInfo.value)

        val dataBytes = mutableListOf<Byte>()
        dataBytes.add(valueIndex)

        return assureOnConstantPoolAndGetIndex(stringConstantInfo, dataBytes)
    }

    private fun addClassConstantInfoAndGetStartIndex(classConstantInfo: ClassConstantInfo): UShort {
        val nameIndex = addUtf8ConstantAndGetStartIndex(classConstantInfo.name)

        val dataBytes = mutableListOf<Byte>()
        dataBytes.add(nameIndex)

        return assureOnConstantPoolAndGetIndex(classConstantInfo, dataBytes)
    }

    private fun addIntegerConstantInfoAndGetStartIndex(integerConstantInfo: IntegerConstantInfo): UShort {
        val dataBytes = mutableListOf<Byte>()
        dataBytes.add(integerConstantInfo.value)

        return assureOnConstantPoolAndGetIndex(integerConstantInfo, dataBytes)
    }

    private fun addLongConstantInfoAndGetStartIndex(longConstantInfo: LongConstantInfo): UShort {
        val dataBytes = mutableListOf<Byte>()
        dataBytes.add(longConstantInfo.value)

        return assureOnConstantPoolAndGetIndex(longConstantInfo, dataBytes)
    }

    private fun addFloatConstantInfoAndGetStartIndex(floatConstantInfo: FloatConstantInfo): UShort {
        val dataBytes = mutableListOf<Byte>()
        dataBytes.add(floatConstantInfo.value.toBits())

        return assureOnConstantPoolAndGetIndex(floatConstantInfo, dataBytes)
    }

    private fun addDoubleConstantInfoAndGetStartIndex(doubleConstantInfo: DoubleConstantInfo): UShort {
        val dataBytes = mutableListOf<Byte>()
        dataBytes.add(doubleConstantInfo.value.toBits())

        return assureOnConstantPoolAndGetIndex(doubleConstantInfo, dataBytes)
    }

    private fun addNameAndTypeConstantInfoAndGetStartIndex(nameAndTypeConstantInfo: NameAndTypeConstantInfo): UShort {
        val nameIndex = addUtf8ConstantAndGetStartIndex(nameAndTypeConstantInfo.name)
        val typeIndex = addUtf8ConstantAndGetStartIndex(nameAndTypeConstantInfo.type)

        val dataBytes = mutableListOf<Byte>()
        dataBytes.add(nameIndex)
        dataBytes.add(typeIndex)

        return assureOnConstantPoolAndGetIndex(nameAndTypeConstantInfo, dataBytes)
    }
    
    private fun assureOnConstantPoolAndGetIndex(constantInfo: ConstantInfo, dataBytes: List<Byte> = listOf()): UShort {
        val bytes = listOf(constantInfo.tag.value).plus(dataBytes)
        val zeroBasedIndex = bytesAtIndex.indexOf(bytes)
        return if (zeroBasedIndex == -1) {
            lastOccupiedConstantPoolIndex++
            bytesAtIndex.add(bytes)
            if(constantInfo is LongConstantInfo || constantInfo is DoubleConstantInfo) {
                val index = lastOccupiedConstantPoolIndex
                lastOccupiedConstantPoolIndex++
                bytesAtIndex.add(listOf())
                index
            } else {
                lastOccupiedConstantPoolIndex
            }
        } else {
            (zeroBasedIndex + 1).toUShort()
        }
    }
}