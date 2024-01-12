package org.cmjava2023.classfilespecification.constantpool

@Suppress("unused")
enum class ConstantPoolEntryType(val tagByte:Byte) {
    FIELD_REF(0X09),
    METHOD_REF(0X0A),
    INTERFACE_METHOD_REF(0X0B),
    NAME_AND_TYPE(0X0C),
    METHOD_HANDLE(0X0D),
    METHOD_TYPE(0X10),
    INVOKE_DYNAMIC(0X12),
}


