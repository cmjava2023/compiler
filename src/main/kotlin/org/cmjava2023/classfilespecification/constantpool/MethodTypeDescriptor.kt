package org.cmjava2023.classfilespecification.constantpool

import org.cmjava2023.ast.ASTNodes
import org.cmjava2023.ast.ASTNodes.FunctionNode
import org.cmjava2023.symboltable.BuiltInType
import java.util.ArrayList

class MethodTypeDescriptor private constructor(
    returnTypeDescriptor: TypeDescriptor,
    val parameterTypeDescriptors: List<TypeDescriptor>
) : TypeDescriptor("(" + parameterTypeDescriptors.joinToString(";") + ")" + returnTypeDescriptor.stringRepresentation) {
    fun returning(returnTypeDescriptor: TypeDescriptor): MethodTypeDescriptor = MethodTypeDescriptor(returnTypeDescriptor, this.parameterTypeDescriptors)
    
    companion object {
       fun voidWithParameters(vararg parameterTypeDescriptors: TypeDescriptor): MethodTypeDescriptor = MethodTypeDescriptor(VOID, parameterTypeDescriptors.toList())
        fun forFunctionNode(functionNode: FunctionNode): MethodTypeDescriptor {
            val returnTypeDescriptor = when (functionNode.functionSymbol.type) {
                BuiltInType.VOID -> VOID
                else -> throw NotImplementedError()
            }
            return MethodTypeDescriptor(returnTypeDescriptor, parseParameterTypeDescriptors(
                functionNode.parameters.toCollection(ArrayList())
            ))
        }

        private fun parseParameterTypeDescriptors(parameters: ArrayList<ASTNodes.ParameterNode>): List<TypeDescriptor> {
            val result = mutableListOf<TypeDescriptor>()
            for (parameter in parameters) {
                when (parameter.parameterSymbol.type.name) {
                    "String[]" -> result += STRING.asArrayOfDimension(1)
                    else -> throw NotImplementedError()
                }
            }

            return result
        }
    }
}