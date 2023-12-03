package org.cmjava2023.classfilespecification

import org.cmjava2023.ast.ASTNodes

@Suppress("unused")
enum class ClassAccessModifier(val value:Short) {
    ACC_PUBLIC(0x0001),
    ACC_FINAL(0x0010),
    ACC_SUPER(0x0020),
    ACC_INTERFACE(0x0200),
    ACC_ABSTRACT(0x0400),
    ACC_SYNTHETIC(0x1000),
    ACC_ANNOTATION(0x2000),
    ACC_ENUM(0x4000);

    companion object {
        fun fromASTModifier(astModifier: ASTNodes.AccessModifier): ClassAccessModifier {
            return when(astModifier) {
                ASTNodes.AccessModifier.PUBLIC -> ACC_PUBLIC
                else -> throw NotImplementedError()
            }
        }
    }
}


