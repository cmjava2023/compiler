package org.cmjava2023

import org.cmjava2023.ByteListUtil.Companion.add
import org.cmjava2023.classfilespecification.MethodInfo
import org.cmjava2023.classfilespecification.OpCode
import org.cmjava2023.classfilespecification.attributeInfo.CodeAttributeInfo
import org.cmjava2023.classfilespecification.constantpool.*
import kotlin.experimental.or

class ConstantPoolToByteList {

    data class ClassFileBytes(
            val constantPoolBytes: List<Byte>,
            val constantPoolItemCount: Short,
            val methodInfosBytes: List<Byte>,
            val methodInfoCount: Short
    )

    companion object {
        private var lastOccupiedConstantPoolIndex: Short = 0
        private var constantPoolBytes = mutableListOf<Byte>()
        
        fun mapToByteList(constantPool: List<ConstantInfo>, methodInfos: List<MethodInfo>): ClassFileBytes {
            constantPoolBytes = mutableListOf()
            lastOccupiedConstantPoolIndex = 0
            for (constantInfo in constantPool) {
                when(constantInfo) {
                    is ClassConstantInfo -> addClassConstantInfoAndGetStartIndex(constantInfo)
                }
            }

            val methodInfosBytes = mutableListOf<Byte>()
            for (methodInfo in methodInfos) {
                methodInfosBytes.add(
                    methodInfo.accessModifiers.map { it.value }.reduce { combinedFlag, flag -> combinedFlag or flag }
                )
                val methodNameIndex = addUtf8ConstantAndGetStartIndex(methodInfo.nameConstant)
                methodInfosBytes.add(methodNameIndex)

                val methodTypeIndex = addUtf8ConstantAndGetStartIndex(methodInfo.methodType)
                methodInfosBytes.add(methodTypeIndex)

                methodInfosBytes.add(methodInfo.attributes.size.toShort())

                val singleAttribute = methodInfo.attributes.singleOrNull()
                if (singleAttribute is CodeAttributeInfo) {
                    val attributeNameIndex = addUtf8ConstantAndGetStartIndex(singleAttribute.name)
                    methodInfosBytes.add(attributeNameIndex)

                    val attributeLength = 21u
                    methodInfosBytes.add(attributeLength)

                    val functionCallCodePart = singleAttribute.functionCallCodeParts.singleOrNull() ?: throw NotImplementedError()

                    val maxStackSize: Short = 2
                    val maxLocalVarSize: Short = 1
                    val sizeOfCode = 9u

                    methodInfosBytes.add(maxStackSize)
                    methodInfosBytes.add(maxLocalVarSize)
                    methodInfosBytes.add(sizeOfCode)

                    methodInfosBytes.add(OpCode.getstatic.value)
                    methodInfosBytes.add(addReferenceConstantInfoAndGetStartIndex(functionCallCodePart.fieldReferenceConstantInfo))

                    methodInfosBytes.add(OpCode.loaDConstant.value) //ldc only takes a byte sized index, ldc_w is used when a byte is not sufficient for index
                    methodInfosBytes.add(addStringConstantInfoAndGetStartIndex(functionCallCodePart.arguments.single()).toUByte())

                    methodInfosBytes.add(OpCode.invokevirtual.value)
                    methodInfosBytes.add(addReferenceConstantInfoAndGetStartIndex(functionCallCodePart.methodReferenceConstantInfo))

                    methodInfosBytes.add(OpCode.returnVoid.value)

                    val exceptionTableLength: Short = 0
                    methodInfosBytes.add(exceptionTableLength)

                    val attributesCount: Short = 0
                    methodInfosBytes.add(attributesCount)
                } else if (singleAttribute != null) {
                    throw NotImplementedError()
                }
            }

            return ClassFileBytes(
                constantPoolBytes,
                (lastOccupiedConstantPoolIndex + 1).toShort(),
                methodInfosBytes,
                methodInfos.size.toShort()
            )
        }

        private fun addUtf8ConstantAndGetStartIndex(utf8ConstantInfo: Utf8ConstantInfo): Short {
            val startIndex = startConstantInfoWithTagAndGetIndex(utf8ConstantInfo.tag)
            constantPoolBytes.addAll(utf8ConstantInfo.getStringLengthBytes())
            constantPoolBytes.addAll(utf8ConstantInfo.getUtf8Bytes())
            return startIndex
        }

        private fun startConstantInfoWithTagAndGetIndex(constantInfoTag: ConstantInfoTag): Short {
            constantPoolBytes.add(constantInfoTag.value)
            lastOccupiedConstantPoolIndex++
            return lastOccupiedConstantPoolIndex
        }

        private fun addClassConstantInfoAndGetStartIndex(constantInfo: ClassConstantInfo): Short {
            val nameIndex = addUtf8ConstantAndGetStartIndex(constantInfo.nameConstant)

            val startIndex = startConstantInfoWithTagAndGetIndex(constantInfo.tag)
            constantPoolBytes.add(nameIndex)
            
            return startIndex
        }

        private fun addStringConstantInfoAndGetStartIndex(constantInfo: StringConstantInfo): Short {
            val valueIndex = addUtf8ConstantAndGetStartIndex(constantInfo.valueConstantInfo)

            val startIndex = startConstantInfoWithTagAndGetIndex(constantInfo.tag)
            constantPoolBytes.add(valueIndex)

            return startIndex
        }
        
        private fun addNameAndTypeConstantInfoAndGetStartIndex(constantInfo: NameAndTypeConstantInfo): Short {
            val nameIndex = addUtf8ConstantAndGetStartIndex(constantInfo.name)
            val typeIndex = addUtf8ConstantAndGetStartIndex(constantInfo.type)

            val startIndex = startConstantInfoWithTagAndGetIndex(constantInfo.tag)
            constantPoolBytes.add(nameIndex)
            constantPoolBytes.add(typeIndex)

            return startIndex
        }

        private fun addReferenceConstantInfoAndGetStartIndex(constantInfo: ReferenceConstantInfo): Short {
            val classIndex = addClassConstantInfoAndGetStartIndex(constantInfo.classConstantInfo)
            val nameAndTypeIndex = addNameAndTypeConstantInfoAndGetStartIndex(constantInfo.nameAndTypeConstantInfo)

            val startIndex = startConstantInfoWithTagAndGetIndex(constantInfo.tag)
            constantPoolBytes.add(classIndex)
            constantPoolBytes.add(nameAndTypeIndex)

            return startIndex
        }
    }
}