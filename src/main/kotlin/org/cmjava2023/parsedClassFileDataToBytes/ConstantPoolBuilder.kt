package org.cmjava2023.parsedClassFileDataToBytes

import org.cmjava2023.classfilespecification.constantpool.ConstantPoolEntry
import org.cmjava2023.classfilespecification.constantpool.ConstantPoolEntry.*
import org.cmjava2023.util.ByteListUtil.Companion.add

class ConstantPoolBuilder {
    private var lastOccupiedConstantPoolIndex: UShort = 0u
    private var bytesAtIndex = mutableListOf<List<Byte>>()
    
    val itemCount = (lastOccupiedConstantPoolIndex + 1u).toUShort()
    val resultBytes = bytesAtIndex.flatten()
    
    fun getIndexByResolvingOrAdding(constantPoolEntry: ConstantPoolEntry): UShort {
        return when (constantPoolEntry) {
            is ReferenceConstant -> addReferenceConstantAndGetStartIndex(constantPoolEntry)
            is StringConstant -> addStringConstantAndGetStartIndex(constantPoolEntry)
            is ClassConstant -> addClassConstantAndGetStartIndex(constantPoolEntry)
            is IntegerConstant -> addIntegerConstantAndGetStartIndex(constantPoolEntry)
            is LongConstant -> addLongConstantAndGetStartIndex(constantPoolEntry)
            is FloatConstant -> addFloatConstantAndGetStartIndex(constantPoolEntry)
            is DoubleConstant -> addDoubleConstantAndGetStartIndex(constantPoolEntry)
            else -> throw NotImplementedError(constantPoolEntry.javaClass.name)
        }
    }
    
    fun addUtf8ConstantAndGetStartIndex(utf8Constant: Utf8Constant): UShort {
        val dataBytes = utf8Constant.stringLengthInBytes
            .plus(utf8Constant.contentInBytes)
        return assureOnConstantPoolAndGetIndex(utf8Constant, dataBytes)
    }

    private fun addReferenceConstantAndGetStartIndex(referenceConstant: ReferenceConstant): UShort {
        val classIndex = addClassConstantAndGetStartIndex(referenceConstant.classConstantInfo)
        val nameAndTypeIndex = addNameAndTypeConstantInfoAndGetStartIndex(referenceConstant.nameAndTypeConstantInfo)

        val dataBytes = mutableListOf<Byte>()
        dataBytes.add(classIndex)
        dataBytes.add(nameAndTypeIndex)

        return assureOnConstantPoolAndGetIndex(referenceConstant, dataBytes)
    }

    private fun addStringConstantAndGetStartIndex(stringConstant: StringConstant): UShort {
        val valueIndex = addUtf8ConstantAndGetStartIndex(stringConstant.value)

        val dataBytes = mutableListOf<Byte>()
        dataBytes.add(valueIndex)

        return assureOnConstantPoolAndGetIndex(stringConstant, dataBytes)
    }

    private fun addClassConstantAndGetStartIndex(classConstant: ClassConstant): UShort {
        val nameIndex = addUtf8ConstantAndGetStartIndex(classConstant.name)

        val dataBytes = mutableListOf<Byte>()
        dataBytes.add(nameIndex)

        return assureOnConstantPoolAndGetIndex(classConstant, dataBytes)
    }

    private fun addIntegerConstantAndGetStartIndex(integerConstant: IntegerConstant): UShort {
        val dataBytes = mutableListOf<Byte>()
        dataBytes.add(integerConstant.value)

        return assureOnConstantPoolAndGetIndex(integerConstant, dataBytes)
    }

    private fun addLongConstantAndGetStartIndex(longConstant: LongConstant): UShort {
        val dataBytes = mutableListOf<Byte>()
        dataBytes.add(longConstant.value)

        return assureOnConstantPoolAndGetIndex(longConstant, dataBytes)
    }

    private fun addFloatConstantAndGetStartIndex(floatConstant: FloatConstant): UShort {
        val dataBytes = mutableListOf<Byte>()
        dataBytes.add(floatConstant.value.toBits())

        return assureOnConstantPoolAndGetIndex(floatConstant, dataBytes)
    }

    private fun addDoubleConstantAndGetStartIndex(doubleConstant: DoubleConstant): UShort {
        val dataBytes = mutableListOf<Byte>()
        dataBytes.add(doubleConstant.value.toBits())

        return assureOnConstantPoolAndGetIndex(doubleConstant, dataBytes)
    }

    private fun addNameAndTypeConstantInfoAndGetStartIndex(nameAndTypeConstant: NameAndTypeConstant): UShort {
        val nameIndex = addUtf8ConstantAndGetStartIndex(nameAndTypeConstant.name)
        val typeIndex = addUtf8ConstantAndGetStartIndex(nameAndTypeConstant.type)

        val dataBytes = mutableListOf<Byte>()
        dataBytes.add(nameIndex)
        dataBytes.add(typeIndex)

        return assureOnConstantPoolAndGetIndex(nameAndTypeConstant, dataBytes)
    }
    
    private fun assureOnConstantPoolAndGetIndex(constantPoolEntry: ConstantPoolEntry, dataBytes: List<Byte> = listOf()): UShort {
        val bytes = listOf(constantPoolEntry.tagByte).plus(dataBytes)
        val zeroBasedIndex = bytesAtIndex.indexOf(bytes)
        return if (zeroBasedIndex == -1) {
            lastOccupiedConstantPoolIndex++
            bytesAtIndex.add(bytes)
            if(constantPoolEntry is LongConstant || constantPoolEntry is DoubleConstant) {
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