package org.cmjava2023

class ClassfileModelFromAst {

    fun generate(ast: ASTNodes.StartNode): ClassfileModel {

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