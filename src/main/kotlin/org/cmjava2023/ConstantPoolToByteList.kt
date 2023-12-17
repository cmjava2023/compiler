package org.cmjava2023

import org.cmjava2023.util.ByteListUtil.Companion.add
import org.cmjava2023.classfilespecification.MethodInfo
import org.cmjava2023.classfilespecification.OpCode
import org.cmjava2023.classfilespecification.attributeInfo.CodeAttributeInfo
import org.cmjava2023.classfilespecification.constantpool.*
import org.cmjava2023.util.AccessModifierUtil.Companion.bitwiseOrCombine

class ConstantPoolToByteList {

    data class ClassFileBytes(
        val constantPoolBytes: List<Byte>,
        val constantPoolItemCount: UShort,
        val methodInfosBytes: List<Byte>,
        val methodInfoCount: UShort
    )

    companion object {
        private var lastOccupiedConstantPoolIndex: UShort = 0u
        private var constantPoolBytes = mutableListOf<Byte>()
        private val methodInfosBytes = mutableListOf<Byte>()

        fun mapToClassFileBytes(constantPool: List<ConstantInfo>, methodInfos: List<MethodInfo>): ClassFileBytes {
            constantPoolBytes = mutableListOf()
            lastOccupiedConstantPoolIndex = 0u
            for (constantInfo in constantPool) {
                when (constantInfo) {
                    is ClassConstantInfo -> addClassConstantInfoAndGetStartIndex(constantInfo)
                }
            }
            for (methodInfo in methodInfos) {
                addMethodInfo(methodInfo)
            }

            return ClassFileBytes(
                constantPoolBytes,
                (lastOccupiedConstantPoolIndex + 1u).toUShort(),
                methodInfosBytes,
                methodInfos.size.toUShort()
            )
        }

        private fun addMethodInfo(methodInfo: MethodInfo) {
            methodInfosBytes.add(
                methodInfo.accessModifiers.map { it.value }.bitwiseOrCombine()
            )
            val methodNameIndex = addUtf8ConstantAndGetStartIndex(methodInfo.name)
            methodInfosBytes.add(methodNameIndex)

            val methodTypeIndex = addUtf8ConstantAndGetStartIndex(methodInfo.typeDescriptor)
            methodInfosBytes.add(methodTypeIndex)

            methodInfosBytes.add(methodInfo.attributes.size.toUShort())

            val codeAttributeInfo = methodInfo.attributes.filterIsInstance<CodeAttributeInfo>().singleOrNull()
            if (codeAttributeInfo != null) {
                val codeAttributeNameIndex = addUtf8ConstantAndGetStartIndex(codeAttributeInfo.name)
                addCodeAttributeBytesToMethodInfoBytes(codeAttributeInfo.code, codeAttributeNameIndex)
            } else {
                throw NotImplementedError()
            }
        }

        private fun addCodeAttributeBytesToMethodInfoBytes(code: List<OpCode>, codeAttributeNameIndex: UShort) {
            val attributeBytesCountedForLength = mutableListOf<Byte>()

            val maxStackSize: UShort = 2u
            attributeBytesCountedForLength.add(maxStackSize)

            val maxLocalVarSize: UShort = 1u
            attributeBytesCountedForLength.add(maxLocalVarSize)

            val codeBytes = code.flatMap { constructBytesOfOpcode(it) }
            val sizeOfCode = codeBytes.size.toUInt()
            attributeBytesCountedForLength.add(sizeOfCode)
            attributeBytesCountedForLength.addAll(codeBytes)

            val exceptionTableLength: UShort = 0u
            attributeBytesCountedForLength.add(exceptionTableLength)
            val attributesCount: UShort = 0u
            attributeBytesCountedForLength.add(attributesCount)

            methodInfosBytes.add(codeAttributeNameIndex)
            val sizeOfAttribute = attributeBytesCountedForLength.size.toUInt()
            methodInfosBytes.add(sizeOfAttribute)
            methodInfosBytes.addAll(attributeBytesCountedForLength)
        }

        private fun addUtf8ConstantAndGetStartIndex(utf8ConstantInfo: Utf8ConstantInfo): UShort {
            val startIndex = startConstantInfoWithTagAndGetIndex(utf8ConstantInfo.tag)
            constantPoolBytes.addAll(utf8ConstantInfo.getStringLengthBytes())
            constantPoolBytes.addAll(utf8ConstantInfo.getUtf8Bytes())
            return startIndex
        }

        private fun startConstantInfoWithTagAndGetIndex(constantInfoTag: ConstantInfoTag): UShort {
            constantPoolBytes.add(constantInfoTag.value)
            lastOccupiedConstantPoolIndex++
            return lastOccupiedConstantPoolIndex
        }

        private fun addClassConstantInfoAndGetStartIndex(constantInfo: ClassConstantInfo): UShort {
            val nameIndex = addUtf8ConstantAndGetStartIndex(constantInfo.name)

            val startIndex = startConstantInfoWithTagAndGetIndex(constantInfo.tag)
            constantPoolBytes.add(nameIndex)

            return startIndex
        }

        private fun addStringConstantInfoAndGetStartIndex(constantInfo: StringConstantInfo): UShort {
            val valueIndex = addUtf8ConstantAndGetStartIndex(constantInfo.value)

            val startIndex = startConstantInfoWithTagAndGetIndex(constantInfo.tag)
            constantPoolBytes.add(valueIndex)

            return startIndex
        }

        private fun addNameAndTypeConstantInfoAndGetStartIndex(constantInfo: NameAndTypeConstantInfo): UShort {
            val nameIndex = addUtf8ConstantAndGetStartIndex(constantInfo.name)
            val typeIndex = addUtf8ConstantAndGetStartIndex(constantInfo.type)

            val startIndex = startConstantInfoWithTagAndGetIndex(constantInfo.tag)
            constantPoolBytes.add(nameIndex)
            constantPoolBytes.add(typeIndex)

            return startIndex
        }

        private fun addReferenceConstantInfoAndGetStartIndex(constantInfo: ReferenceConstantInfo): UShort {
            val classIndex = addClassConstantInfoAndGetStartIndex(constantInfo.classConstantInfo)
            val nameAndTypeIndex = addNameAndTypeConstantInfoAndGetStartIndex(constantInfo.nameAndTypeConstantInfo)

            val startIndex = startConstantInfoWithTagAndGetIndex(constantInfo.tag)
            constantPoolBytes.add(classIndex)
            constantPoolBytes.add(nameAndTypeIndex)

            return startIndex
        }

        private fun addConstantInfo(constantInfo: ConstantInfo): UShort {
            return when (constantInfo) {
                is ReferenceConstantInfo -> addReferenceConstantInfoAndGetStartIndex(constantInfo)
                is StringConstantInfo -> addStringConstantInfoAndGetStartIndex(constantInfo)
                is ClassConstantInfo -> addClassConstantInfoAndGetStartIndex(constantInfo)
                else -> throw NotImplementedError(constantInfo.javaClass.name)
            }
        }

        private fun constructBytesOfOpcode(opCode: OpCode): List<Byte> {
            var resultOpCode = opCode
            val result = mutableListOf<Byte>()
            if (opCode is OpCode.LoadConstant) {
                val index = addConstantInfo(opCode.values.first() as ConstantInfo)
                resultOpCode = if (index <= UByte.MAX_VALUE) {
                    OpCode.Ldc(index.toUByte())
                } else {
                    OpCode.Ldc_w(index)
                }
            }
            result.add(resultOpCode.opCodeValue)
            for (value in resultOpCode.values) {
                when (value) {
                    is UShort -> result.add(value)
                    is UByte -> result.add(value)
                    is Byte -> result.add(value)
                    is ConstantInfo -> result.add(addConstantInfo(value))
                    else -> throw NotImplementedError()
                }
            }
            return result
        }
    }


}