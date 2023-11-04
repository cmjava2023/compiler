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

        val modifiers = mutableListOf<AccessModifier>()
        for (statement in ast.body) {
            if (statement is ASTNodes.PackageNode) {
                fullyQualifiedClassName += statement.identifier.replace(".", PACKAGE_DELIMITER_IN_CLASS_FILES)
            } else if (statement is ASTNodes.ClassNode) {
                fullyQualifiedClassName += PACKAGE_DELIMITER_IN_CLASS_FILES + statement.identifier
                for(modifierNode in statement.modifier) {
                    modifiers.add(AccessModifier.fromASTModifier(modifierNode))
                }
            }
        }
        if (!modifiers.contains(AccessModifier.ACC_FINAL)) {
            modifiers.add(AccessModifier.ACC_SUPER)
        }

        val constantClassInfo = ConstantClassInfo(ConstantUtf8Info(fullyQualifiedClassName))
        val objectClassInfo = ConstantClassInfo(ConstantUtf8Info("java/lang/Object"))

        return ClassfileModel(
                listOf(constantClassInfo, objectClassInfo),
                modifiers,
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