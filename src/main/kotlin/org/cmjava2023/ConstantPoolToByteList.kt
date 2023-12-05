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
            val constantPoolItemCount: Short,
            val methodInfosBytes: List<Byte>,
            val methodInfoCount: Short
    )

    companion object {
        private var lastOccupiedConstantPoolIndex: Short = 0
        private var constantPoolBytes = mutableListOf<Byte>()
        private val methodInfosBytes = mutableListOf<Byte>()
        
        fun mapToClassFileBytes(constantPool: List<ConstantInfo>, methodInfos: List<MethodInfo>): ClassFileBytes {
            constantPoolBytes = mutableListOf()
            lastOccupiedConstantPoolIndex = 0
            for (constantInfo in constantPool) {
                when(constantInfo) {
                    is ClassConstantInfo -> addClassConstantInfoAndGetStartIndex(constantInfo)
                }
            }
            for (methodInfo in methodInfos) {
                addMethodInfo(methodInfo)
            }

            return ClassFileBytes(
                constantPoolBytes,
                (lastOccupiedConstantPoolIndex + 1).toShort(),
                methodInfosBytes,
                methodInfos.size.toShort()
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

            methodInfosBytes.add(methodInfo.attributes.size.toShort())

            val codeAttributeInfo = methodInfo.attributes.filterIsInstance<CodeAttributeInfo>().singleOrNull()
            if (codeAttributeInfo != null) {
                val codeAttributeNameIndex = addUtf8ConstantAndGetStartIndex(codeAttributeInfo.name)
                val code: List<OpCode>

                if (methodInfo.name.name == "<init>") {
                    code = listOf(
                        OpCode.Aload_0(),
                        OpCode.InvokeSpecial(addReferenceConstantInfoAndGetStartIndex(
                            MethodReferenceConstantInfo(
                                ClassConstantInfo("java/lang/Object"),
                                NameAndTypeConstantInfo("<init>", "()V")
                            )
                        )),
                        OpCode.Return()
                    )
                } else {
                    val functionCallCodePart =
                        codeAttributeInfo.functionCallCodeParts.singleOrNull() ?: throw NotImplementedError()

                    code = listOf(
                        OpCode.GetStatic(addReferenceConstantInfoAndGetStartIndex(functionCallCodePart.fieldReferenceConstantInfo)),
                        OpCode.LoaDConstant(addStringConstantInfoAndGetStartIndex(functionCallCodePart.arguments.single()).toUByte()),
                        OpCode.InvokeVirtual(addReferenceConstantInfoAndGetStartIndex(functionCallCodePart.methodReferenceConstantInfo)),
                        OpCode.Return()
                    )
                }

                addCodeAttributeBytesToMethodInfoBytes(code, codeAttributeNameIndex)
            } else {
                throw NotImplementedError()
            }
        }

        private fun addCodeAttributeBytesToMethodInfoBytes(code: List<OpCode>, codeAttributeNameIndex: Short) {
            val attributeBytesCountedForLength = mutableListOf<Byte>()

            val maxStackSize: Short = 2
            attributeBytesCountedForLength.add(maxStackSize)

            val maxLocalVarSize: Short = 1
            attributeBytesCountedForLength.add(maxLocalVarSize)

            val codeBytes = code.flatMap { it.toBytes() }
            val sizeOfCode = codeBytes.size.toUInt()
            attributeBytesCountedForLength.add(sizeOfCode)
            attributeBytesCountedForLength.addAll(codeBytes)

            val exceptionTableLength: Short = 0
            attributeBytesCountedForLength.add(exceptionTableLength)
            val attributesCount: Short = 0
            attributeBytesCountedForLength.add(attributesCount)

            methodInfosBytes.add(codeAttributeNameIndex)
            val sizeOfAttribute = attributeBytesCountedForLength.size.toUInt()
            methodInfosBytes.add(sizeOfAttribute)
            methodInfosBytes.addAll(attributeBytesCountedForLength)
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
            val nameIndex = addUtf8ConstantAndGetStartIndex(constantInfo.name)

            val startIndex = startConstantInfoWithTagAndGetIndex(constantInfo.tag)
            constantPoolBytes.add(nameIndex)
            
            return startIndex
        }

        private fun addStringConstantInfoAndGetStartIndex(constantInfo: StringConstantInfo): Short {
            val valueIndex = addUtf8ConstantAndGetStartIndex(constantInfo.value)

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