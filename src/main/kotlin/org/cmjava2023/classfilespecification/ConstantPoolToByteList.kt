package org.cmjava2023.classfilespecification

import org.cmjava2023.ByteListUtil.Companion.toByteList

class ConstantPoolToByteList {
    companion object {
        fun mapToByteList(constantPool: List<ConstantInfo>): Pair<List<Byte>, Short> {
            val result = mutableListOf<Byte>()
            var constantPoolIndex: Short = 0
            for (constantInfo in constantPool) {
                when(constantInfo) {
                    is ConstantClassInfo -> {
                        result.addAll(constantInfo.nameConstant.getByteArray())
                        constantPoolIndex++

                        result.add(constantInfo.tag.value)
                        result.addAll(constantPoolIndex.toByteList())
                        constantPoolIndex++
                    }
                }
            }

            return Pair(result, (constantPoolIndex + 1).toShort())
        }
    }
}