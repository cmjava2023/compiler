package org.cmjava2023.classfilespecification

import org.cmjava2023.ASTNodes

enum class MethodAccessModifier(val value:Short) {
    ACC_PUBLIC(0x0001),
    ACC_PRIVATE(0x0002),
    ACC_PROTECTED(0x0004),
    ACC_STATIC(0x0008),
    ACC_FINAL(0x0010),
    ACC_SYNCHRONIZED(0x0020),
    ACC_BRIDGE(0x0040),
    ACC_VARARGS(0x0080),
    ACC_NATIVE(0x0100),
    ACC_ABSTRACT(0x0400),
    ACC_STRICT(0x0800),
    ACC_SYNTHETIC(0x1000);

    companion object {
        fun fromASTModifier(astModifier: ASTNodes.Modifier): MethodAccessModifier {
            return when(astModifier) {
                ASTNodes.Modifier.PUBLIC -> ACC_PUBLIC
                ASTNodes.Modifier.STATIC -> ACC_STATIC
                else -> throw NotImplementedError()
            }
        }
    }
}
