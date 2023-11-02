package org.cmjava2023.classfilespecification

class ConstantUtf8Info(val name: String): ConstantInfo(ConstantInfoTag.CONSTANT_Utf8) {
    override fun getInfo(): List<Byte> {

        val utf8encodedName = String(name.toByteArray(), Charsets.UTF_8)

        val result = mutableListOf(utf8encodedName.length.toByte())
        result.addAll(name.toByteArray().toList())

        return result
    }

}