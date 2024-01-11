package org.cmjava2023.classFileModelToBytes

import org.cmjava2023.symboltable.BuiltInType
import org.cmjava2023.symboltable.GlobalScope
import org.cmjava2023.symboltable.InvalidType
import org.cmjava2023.symboltable.InvalidVariable
import org.cmjava2023.symboltable.Variable

class LocalVariableIndexAssigner(typeDescriptorOfMethod: String) {
    private val localVariables: MutableList<Variable> = mutableListOf()
    val maxLocalVariableSize: UShort = if (localVariables.isEmpty()) {
        1
    } else {
        localVariables.size
    }.toUShort()
    
    init {
        addMethodParametersToLocalVariables(typeDescriptorOfMethod)
    }

    fun getLocalVariableIndexOfVariable(localVariable: Variable): UByte {
        var index = localVariables.indexOf(localVariable)
        if (index == -1) {
            index = localVariables.size
            localVariables.add(localVariable)
            if (localVariable.type == BuiltInType.DOUBLE || localVariable.type == BuiltInType.LONG) {
                localVariables.add(
                    InvalidVariable(
                        "secondPlaceForLongOrDouble",
                        InvalidType(""),
                        GlobalScope(null, java.util.HashMap())
                    )
                )
            }
        }
        return index.toUByte()
    }

    private fun addMethodParametersToLocalVariables(typeDescriptorOfMethod: String) {
        typeDescriptorOfMethod
            .substringBeforeLast(")")
            .removePrefix("(")
            .removeSuffix(")")
            .split(';')
            .filter { it.isNotEmpty() }
            .forEachIndexed { index, type ->
                localVariables.add(
                    Variable(
                        "Method Parameter $index",
                        InvalidType(type),
                        GlobalScope(null, HashMap())
                    )
                )
            }
    }
    
}