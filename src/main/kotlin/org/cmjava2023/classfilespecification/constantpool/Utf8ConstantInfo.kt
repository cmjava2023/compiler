package org.cmjava2023.classfilespecification.constantpool

import org.cmjava2023.util.ByteListUtil.Companion.toByteList

class Utf8ConstantInfo(val name: String): ConstantInfo(ConstantInfoTag.CONSTANT_Utf8) {
    private val utf8encodedName: String = String(name.toByteArray(), Charsets.UTF_8)

    fun getStringLengthBytes(): List<Byte> {
       return utf8encodedName.length.toUShort().toByteList()
    }

    fun getUtf8Bytes(): List<Byte> {
       return utf8encodedName.toByteArray().toList()
    }
}