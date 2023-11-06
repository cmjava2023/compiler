package org.cmjava2023.classfilespecification.constantpool

import org.cmjava2023.ByteListUtil.Companion.toByteList

class Utf8ConstantInfo(private val name: String): ConstantInfo(ConstantInfoTag.CONSTANT_Utf8) {
    fun getByteArray(): List<Byte> {

        val utf8encodedName = String(name.toByteArray(), Charsets.UTF_8)

        val result = mutableListOf(this.tag.value)
        result.addAll(utf8encodedName.length.toShort().toByteList())
        result.addAll(name.toByteArray().toList())

        return result
    }

}