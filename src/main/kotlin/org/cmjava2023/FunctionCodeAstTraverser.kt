package org.cmjava2023

import org.cmjava2023.ast.ASTNodes
import org.cmjava2023.ast.ASTTraverser
import org.cmjava2023.classfilespecification.OpCode
import org.cmjava2023.classfilespecification.constantpool.*
import org.cmjava2023.symboltable.Variable

class FunctionCodeAstTraverser() : ASTTraverser<List<OpCode>>() {
    override fun visit(startNode: ASTNodes.StartNode): List<OpCode> {
        throw NotImplementedError()
    }

    override fun visit(packageNode: ASTNodes.PackageNode): List<OpCode> {
        throw NotImplementedError()
    }

    override fun visit(classNode: ASTNodes.ClassNode): List<OpCode> {
        throw NotImplementedError()
    }

    override fun visit(functionNode: ASTNodes.FunctionNode): List<OpCode> {
        val opCodes = functionNode.body.flatMap { dispatch(it) }
        return if (opCodes.lastOrNull() !is OpCode.ReturnAnything) {
            opCodes.plus(OpCode.Return())
        } else {
            opCodes
        }
    }

    override fun visit(node: ASTNodes.ParameterNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(rawFunctionCallNode: ASTNodes.RawFunctionCallNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(functionCallNode: ASTNodes.FunctionCallNode): List<OpCode> {
        if (functionCallNode.function.name == "System.out.println"
        ) {
            val splitName = functionCallNode.function.name.split(".")
            val className = splitName[0]
            val fieldName = splitName[1]
            val methodName = splitName[2]

            val qualifiedClassName = "java/lang/$className"

            val fieldReferenceConstantInfo = FieldReferenceConstantInfo(
                ClassConstantInfo(qualifiedClassName),
                NameAndTypeConstantInfo(fieldName, "Ljava/io/PrintStream;")
            )
            val methodReferenceConstantInfo = MethodReferenceConstantInfo(
                ClassConstantInfo("java/io/PrintStream"),
                NameAndTypeConstantInfo(methodName, "(Ljava/lang/String;)V")
            )

            val whatToPrint: String

            val expr = functionCallNode.values[0]
            if (expr is ASTNodes.ValueNode<*>) {
                when(val value = expr.value) {
                    is String -> whatToPrint = value
                    else -> throw NotImplementedError()
                }
            } else {
                throw NotImplementedError()
            }

            return listOf(
                OpCode.Getstatic(fieldReferenceConstantInfo),
                OpCode.LoadConstant(StringConstantInfo(whatToPrint)),
                OpCode.Invokevirtual(methodReferenceConstantInfo)
            )
        } else {
            throw NotImplementedError()
        }
    }

    override fun visit(ifNode: ASTNodes.IfNode): List<OpCode> {
        throw NotImplementedError()
    }

    override fun visit(elseNode: ASTNodes.ElseNode): List<OpCode> {
        throw NotImplementedError()
    }

    override fun visit(blockScopeNode: ASTNodes.BlockScopeNode): List<OpCode> {
        return listOf()
    }
    override fun visit(variableNode: ASTNodes.VariableNode): List<OpCode> {
        val variableSymbol = variableNode.variableSymbol
        val value = variableSymbol.initialExpression
        return assignOrDeclareVariable(variableSymbol, value)
    }

    private fun assignOrDeclareVariable(variableSymbol: Variable, value: ASTNodes.Expression): List<OpCode> {
        val opCodesLoadingExpressionValueOnStack = dispatch(value)
        val storingOpCode = when(variableSymbol.type.name) {
            "double" -> OpCode.StoreDouble(variableSymbol)
            else -> throw NotImplementedError()
        }
        return opCodesLoadingExpressionValueOnStack.plus(storingOpCode)
    }

    override fun visit(variableAssigmentNode: ASTNodes.VariableAssigmentNode): List<OpCode> {
        val variableSymbol = variableAssigmentNode.variable
        val value = variableAssigmentNode.expression
        return assignOrDeclareVariable(variableSymbol, value)
    }

    override fun visit(valueNode: ASTNodes.ValueNode<*>): List<OpCode> {
        val value = valueNode.value
        return listOf(when (value) {
            is String -> OpCode.LoadConstant(StringConstantInfo(value))
            is Double -> OpCode.LoadDouble(value)
            else -> throw NotImplementedError()
        })
    }

    override fun visit(nestedIdentifierNode: ASTNodes.NestedIdentifierNode): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(comparisonNode: ASTNodes.ComparisonNode): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(identifierNode: ASTNodes.IdentifierNode): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(returnNode: ASTNodes.ReturnNode): List<OpCode> {
        return listOf(OpCode.Return())
    }

    override fun visit(typeNode: ASTNodes.TypeNode): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(arrayTypeNode: ASTNodes.ArrayTypeNode): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(parameterAssigmentNode: ASTNodes.ParameterAssigmentNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(infixNode: ASTNodes.InfixNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(unaryPrefixNode: ASTNodes.UnaryPrefixNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(unarySuffixNode: ASTNodes.UnarySuffixNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(parenthesesNode: ASTNodes.ParenthesesNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(castNode: ASTNodes.CastNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(arrayInstantiationWithValuesNode: ASTNodes.ArrayInstantiationWithValuesNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(arrayInstantiationNode: ASTNodes.ArrayInstantiationNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(arrayAccessNode: ASTNodes.ArrayAccessNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(objectInstantiationNode: ASTNodes.ObjectInstantiationNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(forLoopNode: ASTNodes.ForLoopNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(whileLoopNode: ASTNodes.WhileLoopNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(doWhileLoopNode: ASTNodes.DoWhileLoopNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(operatorNode: ASTNodes.OperatorNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(functionCallNode: List<OpCode>?): List<OpCode> {
        TODO("Not yet implemented")
    }
}