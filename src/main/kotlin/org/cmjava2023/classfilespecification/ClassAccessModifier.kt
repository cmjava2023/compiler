package org.cmjava2023.classfilespecification

import org.cmjava2023.ast.ASTNodes

@Suppress("unused")
enum class ClassAccessModifier(val value:UShort) {
    ACC_PUBLIC(0x0001u),
    ACC_FINAL(0x0010u),
    ACC_SUPER(0x0020u),
    ACC_INTERFACE(0x0200u),
    ACC_ABSTRACT(0x0400u),
    ACC_SYNTHETIC(0x1000u),
    ACC_ANNOTATION(0x2000u),
    ACC_ENUM(0x4000u);

    companion object {
        fun fromASTModifier(astModifier: ASTNodes.AccessModifier): ClassAccessModifier {
            return when(astModifier) {
                ASTNodes.AccessModifier.PUBLIC -> ACC_PUBLIC
                else -> throw NotImplementedError()
            }
        }
    }
}


