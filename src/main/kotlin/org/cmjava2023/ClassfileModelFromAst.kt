package org.cmjava2023

import org.cmjava2023.classfilespecification.AccessModifier
import org.cmjava2023.classfilespecification.ConstantClassInfo
import org.cmjava2023.classfilespecification.ConstantUtf8Info

class ClassfileModelFromAst {
    companion object {
        private const val PACKAGE_DELIMITER_IN_CLASS_FILES = "/"
    }

    fun generate(ast: ASTNodes.StartNode): ClassfileModel {
        var fullyQualifiedClassName = ""

        for (statement in ast.body) {
            if (statement is ASTNodes.PackageNode) {
                fullyQualifiedClassName += statement.identifier
            } else if (statement is ASTNodes.ClassNode) {
                fullyQualifiedClassName += PACKAGE_DELIMITER_IN_CLASS_FILES + statement.identifier
            }
        }

        val constantClassInfo = ConstantClassInfo(ConstantUtf8Info(fullyQualifiedClassName))

        return ClassfileModel(
                listOf(constantClassInfo),
                AccessModifier.ACC_PUBLIC.value,
                0,
                0,
                0,
                listOf(),
                0,
                listOf(),
                0,
                listOf(),
                0,
                listOf()
        )
    }
}