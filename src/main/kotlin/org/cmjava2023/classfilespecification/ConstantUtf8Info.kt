package org.cmjava2023.classfilespecification

import org.cmjava2023.ByteListUtil.Companion.toByteList

class ConstantUtf8Info(private val name: String): ConstantInfo(ConstantInfoTag.CONSTANT_Utf8) {
    fun getByteArray(): List<Byte> {

        val utf8encodedName = String(name.toByteArray(), Charsets.UTF_8)

        val result = mutableListOf(this.tag.value)
        result.addAll(utf8encodedName.length.toShort().toByteList())
        result.addAll(name.toByteArray().toList())

        return result
    }

}