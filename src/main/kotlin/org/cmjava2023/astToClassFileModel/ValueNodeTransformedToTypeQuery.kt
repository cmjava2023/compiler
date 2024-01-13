package org.cmjava2023.astToClassFileModel

import org.cmjava2023.ast.ASTNodes
import org.cmjava2023.symboltable.BuiltInType
import org.cmjava2023.symboltable.Type

class ValueNodeTransformedToTypeQuery {
    companion object {
        fun fetch(valueNode: ASTNodes.ValueNode<*>, typeItNeeds: Type): ASTNodes.ValueNode<*> {
            val valueAsString = valueNode.value.toString()
            return ASTNodes.ValueNode(
                    when (typeItNeeds) {
                        BuiltInType.INT -> valueAsString.toInt()
                        BuiltInType.LONG -> valueAsString.toLong()
                        BuiltInType.FLOAT -> valueAsString.toFloat()
                        BuiltInType.DOUBLE -> valueAsString.toDouble()
                        BuiltInType.BOOLEAN -> valueAsString.toBoolean()
                        BuiltInType.CHAR -> {
                            when (valueNode.value) {
                                is Char -> valueNode.value
                                is Int -> Char(valueNode.value as Int)
                                else -> throw NotImplementedError(valueNode.value.javaClass.name)
                            }
                        }
                        BuiltInType.BYTE -> valueAsString.toByte()
                        BuiltInType.SHORT -> valueAsString.toShort()
                        BuiltInType.STRING -> valueAsString
                        else -> throw NotImplementedError(typeItNeeds.javaClass.name)
                    },
                typeItNeeds as BuiltInType
            )
        }
    }
}