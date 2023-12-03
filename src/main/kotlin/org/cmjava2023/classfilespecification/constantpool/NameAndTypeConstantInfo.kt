package org.cmjava2023.classfilespecification.constantpool

class NameAndTypeConstantInfo(name: String, type: String): ConstantInfo(ConstantInfoTag.CONSTANT_NameAndType) {
    val name = Utf8ConstantInfo(name)
    val type = Utf8ConstantInfo(type)
}