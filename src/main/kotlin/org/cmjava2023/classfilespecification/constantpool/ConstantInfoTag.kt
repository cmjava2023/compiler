package org.cmjava2023.classfilespecification.constantpool

@Suppress("unused")
enum class ConstantInfoTag(val value:Byte) {
    CONSTANT_Utf8(0x01),
    CONSTANT_Integer(0x03),
    CONSTANT_Float(0x04),
    CONSTANT_Long(0x05),
    CONSTANT_Double(0x06),
    CONSTANT_Class(0x07),
    CONSTANT_String(0x08),
    CONSTANT_Fieldref(0x09),
    CONSTANT_Methodref(0x0A),
    CONSTANT_InterfaceMethodref(0x0B),
    CONSTANT_NameAndType(0x0C),
    CONSTANT_MethodHandle(0x0D),
    CONSTANT_MethodType(0x10),
    CONSTANT_InvokeDynamic(0x12),
}


