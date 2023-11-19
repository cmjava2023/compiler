package org.cmjava2023.classfilespecification

enum class OpCode(val value:UByte) {
    getstatic(0xb2u),
    loaDConstant(0x12u),
    invokevirtual(0xb6u),
    invokespecial(0xb7u),
    returnVoid(0xb1u),
    aload_0(0x2au)
}