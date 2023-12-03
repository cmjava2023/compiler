package org.cmjava2023.classfilespecification.constantpool

class ClassConstantInfo(name: String): ConstantInfo(ConstantInfoTag.CONSTANT_Class) {
    val name = Utf8ConstantInfo(name)
}