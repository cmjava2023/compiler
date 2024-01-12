package org.cmjava2023.classFileModelToBytes

import org.cmjava2023.classfilespecification.constantpool.*
import org.cmjava2023.util.ByteListUtil.Companion.add

class ConstantPoolBuilder {
    private var lastOccupiedConstantPoolIndex: UShort = 0u
    private var bytesAtIndex = mutableListOf<List<Byte>>()
    
    val itemCount = (lastOccupiedConstantPoolIndex + 1u).toUShort()
    val resultBytes = bytesAtIndex.flatten()
    
    fun getIndexByResolvingOrAdding(constantPoolEntry: ConstantPoolEntry): UShort {
        return when (constantPoolEntry) {
            is ReferenceConstantPoolEntry -> addReferenceConstantInfoAndGetStartIndex(constantPoolEntry)
            is StringConstantPoolEntry -> addStringConstantInfoAndGetStartIndex(constantPoolEntry)
            is ClassConstantPoolEntry -> addClassConstantInfoAndGetStartIndex(constantPoolEntry)
            is IntegerConstantPoolEntry -> addIntegerConstantInfoAndGetStartIndex(constantPoolEntry)
            is LongConstantPoolEntry -> addLongConstantInfoAndGetStartIndex(constantPoolEntry)
            is FloatConstantPoolEntry -> addFloatConstantInfoAndGetStartIndex(constantPoolEntry)
            is DoubleConstantPoolEntry -> addDoubleConstantInfoAndGetStartIndex(constantPoolEntry)
            else -> throw NotImplementedError(constantPoolEntry.javaClass.name)
        }
    }
    
    fun addUtf8ConstantAndGetStartIndex(utf8ConstantInfo: Utf8ConstantPoolEntry): UShort {
        val dataBytes = utf8ConstantInfo.getStringLengthBytes()
            .plus(utf8ConstantInfo.getUtf8Bytes())
        return assureOnConstantPoolAndGetIndex(utf8ConstantInfo, dataBytes)
    }

    private fun addReferenceConstantInfoAndGetStartIndex(referenceConstantInfo: ReferenceConstantPoolEntry): UShort {
        val classIndex = addClassConstantInfoAndGetStartIndex(referenceConstantInfo.classConstantInfo)
        val nameAndTypeIndex = addNameAndTypeConstantInfoAndGetStartIndex(referenceConstantInfo.nameAndTypeConstantInfo)

        val dataBytes = mutableListOf<Byte>()
        dataBytes.add(classIndex)
        dataBytes.add(nameAndTypeIndex)

        return assureOnConstantPoolAndGetIndex(referenceConstantInfo, dataBytes)
    }

    private fun addStringConstantInfoAndGetStartIndex(stringConstantInfo: StringConstantPoolEntry): UShort {
        val valueIndex = addUtf8ConstantAndGetStartIndex(stringConstantInfo.value)

        val dataBytes = mutableListOf<Byte>()
        dataBytes.add(valueIndex)

        return assureOnConstantPoolAndGetIndex(stringConstantInfo, dataBytes)
    }

    private fun addClassConstantInfoAndGetStartIndex(classConstantInfo: ClassConstantPoolEntry): UShort {
        val nameIndex = addUtf8ConstantAndGetStartIndex(classConstantInfo.name)

        val dataBytes = mutableListOf<Byte>()
        dataBytes.add(nameIndex)

        return assureOnConstantPoolAndGetIndex(classConstantInfo, dataBytes)
    }

    private fun addIntegerConstantInfoAndGetStartIndex(integerConstantInfo: IntegerConstantPoolEntry): UShort {
        val dataBytes = mutableListOf<Byte>()
        dataBytes.add(integerConstantInfo.value)

        return assureOnConstantPoolAndGetIndex(integerConstantInfo, dataBytes)
    }

    private fun addLongConstantInfoAndGetStartIndex(longConstantInfo: LongConstantPoolEntry): UShort {
        val dataBytes = mutableListOf<Byte>()
        dataBytes.add(longConstantInfo.value)

        return assureOnConstantPoolAndGetIndex(longConstantInfo, dataBytes)
    }

    private fun addFloatConstantInfoAndGetStartIndex(floatConstantInfo: FloatConstantPoolEntry): UShort {
        val dataBytes = mutableListOf<Byte>()
        dataBytes.add(floatConstantInfo.value.toBits())

        return assureOnConstantPoolAndGetIndex(floatConstantInfo, dataBytes)
    }

    private fun addDoubleConstantInfoAndGetStartIndex(doubleConstantInfo: DoubleConstantPoolEntry): UShort {
        val dataBytes = mutableListOf<Byte>()
        dataBytes.add(doubleConstantInfo.value.toBits())

        return assureOnConstantPoolAndGetIndex(doubleConstantInfo, dataBytes)
    }

    private fun addNameAndTypeConstantInfoAndGetStartIndex(nameAndTypeConstantInfo: NameAndTypeConstantPoolEntry): UShort {
        val nameIndex = addUtf8ConstantAndGetStartIndex(nameAndTypeConstantInfo.name)
        val typeIndex = addUtf8ConstantAndGetStartIndex(nameAndTypeConstantInfo.type)

        val dataBytes = mutableListOf<Byte>()
        dataBytes.add(nameIndex)
        dataBytes.add(typeIndex)

        return assureOnConstantPoolAndGetIndex(nameAndTypeConstantInfo, dataBytes)
    }
    
    private fun assureOnConstantPoolAndGetIndex(constantPoolEntry: ConstantPoolEntry, dataBytes: List<Byte> = listOf()): UShort {
        val bytes = listOf(constantPoolEntry.constantPoolEntryType.tagByte).plus(dataBytes)
        val zeroBasedIndex = bytesAtIndex.indexOf(bytes)
        return if (zeroBasedIndex == -1) {
            lastOccupiedConstantPoolIndex++
            bytesAtIndex.add(bytes)
            if(constantPoolEntry is LongConstantPoolEntry || constantPoolEntry is DoubleConstantPoolEntry) {
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