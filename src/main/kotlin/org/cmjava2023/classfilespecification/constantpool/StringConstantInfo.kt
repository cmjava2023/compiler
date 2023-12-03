package org.cmjava2023.classfilespecification.constantpool

class StringConstantInfo(value: String): ConstantInfo(ConstantInfoTag.CONSTANT_String) {
    val value = Utf8ConstantInfo(value)
}