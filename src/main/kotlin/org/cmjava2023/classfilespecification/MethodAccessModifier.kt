package org.cmjava2023.classfilespecification

import org.cmjava2023.ast.ASTNodes

@Suppress("unused")
enum class MethodAccessModifier(val value:UShort) {
    ACC_PUBLIC(0x0001u),
    ACC_PRIVATE(0x0002u),
    ACC_PROTECTED(0x0004u),
    ACC_STATIC(0x0008u),
    ACC_FINAL(0x0010u),
    ACC_SYNCHRONIZED(0x0020u),
    ACC_BRIDGE(0x0040u),
    ACC_VARARGS(0x0080u),
    ACC_NATIVE(0x0100u),
    ACC_ABSTRACT(0x0400u),
    ACC_STRICT(0x0800u),
    ACC_SYNTHETIC(0x1000u);

    companion object {
        fun fromASTModifier(astModifier: ASTNodes.Modifier): MethodAccessModifier {
            return when(astModifier) {
                ASTNodes.Modifier.STATIC -> ACC_STATIC
                else -> throw NotImplementedError()
            }
        }
        fun fromASTAccessModifier(astModifier: ASTNodes.AccessModifier): MethodAccessModifier {
            return when(astModifier) {
                ASTNodes.AccessModifier.PUBLIC -> ACC_PUBLIC
                else -> throw NotImplementedError()
            }
        }
    }
}
