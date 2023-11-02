package org.cmjava2023

class ClassfileModelFromAst {



    fun generate(ast: ASTNodes.StartNode): ClassfileModel {
        var fullyQualifiedClassName = ""

        for (statement in ast.body ) {
            if (statement is ASTNodes.PackageNode) {
                fullyQualifiedClassName += statement.identifier
            } else if (statement is ASTNodes.ClassNode) {
                fullyQualifiedClassName += "." + statement.identifier
            }
        }

        val model = ClassfileModel(
            0,
            listOf(),
            0,
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

        return model
    }
}