package org.cmjava2023.astToClassFileData

import org.cmjava2023.ast.ASTNodes
import org.cmjava2023.symboltable.Type

class TypeOfExpressionQuery {
    companion object {
        fun fetch(node: ASTNodes.Node): Type {
            return when(node) {
                is ASTNodes.CastNode-> node.type
                is ASTNodes.ValueNode<*> -> node.builtInType
                is ASTNodes.VariableCallNode -> node.symbol.type
                else -> throw NotImplementedError(node.javaClass.name)
            }
        }
    }
}