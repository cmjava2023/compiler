package org.cmjava2023.classfilespecification.constantpool

import org.cmjava2023.ByteListUtil.Companion.toByteList

class Utf8ConstantInfo(name: String): ConstantInfo(ConstantInfoTag.CONSTANT_Utf8) {
    private val utf8encodedName: String

    init {
        utf8encodedName = String(name.toByteArray(), Charsets.UTF_8)
    }

    fun getStringLengthBytes(): List<Byte> {
       return utf8encodedName.length.toShort().toByteList()
    }

    fun getUtf8Bytes(): List<Byte> {
       return utf8encodedName.toByteArray().toList()
    }
}