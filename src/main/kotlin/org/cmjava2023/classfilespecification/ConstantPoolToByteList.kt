package org.cmjava2023.classfilespecification

import org.cmjava2023.ByteListUtil.Companion.add
import org.cmjava2023.classfilespecification.constantpool.ClassConstantInfo
import org.cmjava2023.classfilespecification.constantpool.ConstantInfo
import kotlin.experimental.or

class ConstantPoolToByteList {

    data class ClassFileBytes(
            val constantPoolBytes: List<Byte>,
            val constantPoolItemCount: Short,
            val methodInfosBytes: List<Byte>,
            val methodInfoCount: Short
    )

    companion object {
        fun mapToByteList(constantPool: List<ConstantInfo>, methodInfos: List<MethodInfo>): ClassFileBytes {
            val constantPoolBytes = mutableListOf<Byte>()
            var constantPoolIndex: Short = 1
            for (constantInfo in constantPool) {
                when(constantInfo) {
                    is ClassConstantInfo -> {
                        constantPoolBytes.add(constantInfo.tag.value)
                        constantPoolBytes.add((constantPoolIndex + 1).toShort())
                        constantPoolIndex++

                        constantPoolBytes.addAll(constantInfo.nameConstant.getByteArray())
                        constantPoolIndex++
                    }
                }
            }

            val methodInfosBytes = mutableListOf<Byte>()
            for (methodInfo in methodInfos) {
                methodInfosBytes.add(
                    methodInfo.accessModifiers.map { it.value }.reduce { combinedFlag, flag -> combinedFlag or flag }
                )
                constantPoolBytes.addAll(methodInfo.nameConstant.getByteArray())
                methodInfosBytes.add(constantPoolIndex)
                constantPoolIndex++

                constantPoolBytes.addAll(methodInfo.methodType.getByteArray())
                methodInfosBytes.add(constantPoolIndex)
                constantPoolIndex++
                methodInfosBytes.add((0).toShort())
            }

            return ClassFileBytes(
                constantPoolBytes,
                constantPoolIndex,
                methodInfosBytes,
                methodInfos.size.toShort()
            )
        }
    }
}