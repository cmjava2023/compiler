package org.cmjava2023

import org.cmjava2023.ast.ASTNodes
import org.cmjava2023.ast.ASTTraverser
import org.cmjava2023.classfilespecification.OpCode
import org.cmjava2023.classfilespecification.constantpool.*
import org.cmjava2023.symboltable.Scope
import org.cmjava2023.symboltable.Type
import org.cmjava2023.symboltable.Variable

class FunctionCodeAstTraverser() : ASTTraverser<List<OpCode>>() {
    private lateinit var currentScope: Scope

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
        currentScope = functionNode.functionSymbol.scope
        return functionNode.body.flatMap { dispatch(it) }
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
            if (expr is ASTNodes.StringNode) {
                whatToPrint = expr.value
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
        val value = variableNode.value
        return assignOrDeclareVariable(variableSymbol, value)
    }

    private fun assignOrDeclareVariable(variableSymbol: Variable, value: ASTNodes.Expression): List<OpCode> {
        val opCodesLoadingExpressionValueOnStack = if (value is ASTNodes.Value) { generateTypeSpecificLoadingOpCodes(value, variableSymbol.type) } else { dispatch(value) }
        val storingOpCode = when(variableSymbol.type.name) {
            "double" -> OpCode.StoreDouble(variableSymbol)
            else -> throw NotImplementedError()
        }
        return opCodesLoadingExpressionValueOnStack.plus(storingOpCode)
    }

    override fun visit(variableAssigmentNode: ASTNodes.VariableAssigmentNode): List<OpCode> {
        val variableSymbol = variableAssigmentNode.variable
        val value = variableAssigmentNode.value
        return assignOrDeclareVariable(variableSymbol, value)
    }

    private fun generateTypeSpecificLoadingOpCodes(valueNode: ASTNodes.Value, type: Type): List<OpCode> {
        return listOf(when (valueNode) {
            is ASTNodes.StringNode -> OpCode.LoadConstant(StringConstantInfo(valueNode.value))
            is ASTNodes.DecimalNode -> if(type.name == "double") { OpCode.LoadDouble(valueNode.value) } else { throw NotImplementedError() }
            else -> throw NotImplementedError()
        })
    }

    override fun visit(nestedIdentifierNode: ASTNodes.NestedIdentifierNode): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(comparisonNode: ASTNodes.ComparisonNode): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(expressionNode: ASTNodes.ExpressionNode): List<OpCode> {
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

    override fun visit(functionCallNode: List<OpCode>?): List<OpCode> {
        TODO("Not yet implemented")
    }
}