package org.cmjava2023.classfilespecification

import org.cmjava2023.ByteListUtil.Companion.toByteList

class ConstantPoolToByteList {
    companion object {
        fun mapToByteList(constantPool: List<ConstantInfo>): Pair<List<Byte>, Short> {
            val result = mutableListOf<Byte>()
            var constantPoolIndex: Short = 1
            for (constantInfo in constantPool) {
                when(constantInfo) {
                    is ClassConstantInfo -> {
                        result.add(constantInfo.tag.value)
                        result.addAll((constantPoolIndex + 1).toShort().toByteList())
                        constantPoolIndex++

                        result.addAll(constantInfo.nameConstant.getByteArray())
                        constantPoolIndex++
                    }
                }
            }

            return Pair(result, constantPoolIndex)
        }
    }
}