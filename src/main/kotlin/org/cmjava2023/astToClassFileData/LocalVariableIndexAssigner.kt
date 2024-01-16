package org.cmjava2023.astToClassFileData

import org.cmjava2023.classfilespecification.constantpool.MethodTypeDescriptor
import org.cmjava2023.symboltable.*

class LocalVariableIndexAssigner(methodTypeDescriptor: MethodTypeDescriptor) {
    private val localVariables: MutableList<Variable> = mutableListOf()
    fun maxLocalVariableSize(): UShort = if (localVariables.isEmpty()) {
        1
    } else {
        localVariables.size
    }.toUShort()
    
    init {
        addMethodParametersToLocalVariables(methodTypeDescriptor)
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

    private fun addMethodParametersToLocalVariables(methodTypeDescriptor: MethodTypeDescriptor) {
        methodTypeDescriptor.parameterTypeDescriptors
            .forEachIndexed { index, typeDescriptor ->
                localVariables.add(
                    Variable(
                        "Method Parameter $index",
                        InvalidType(typeDescriptor.stringRepresentation),
                        GlobalScope(null, HashMap())
                    )
                )
            }
    }
    
}