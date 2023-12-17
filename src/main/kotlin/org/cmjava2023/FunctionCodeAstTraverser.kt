package org.cmjava2023

import org.cmjava2023.ast.ASTNodes
import org.cmjava2023.ast.ASTTraverser
import org.cmjava2023.classfilespecification.OpCode
import org.cmjava2023.classfilespecification.constantpool.*
import org.cmjava2023.symboltable.Variable

class FunctionCodeAstTraverser : ASTTraverser<List<OpCode>>() {
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

            val expr = functionCallNode.values[0]
            val opcOdesLoadingWhatToPrint = if (expr is ASTNodes.ValueNode<*>) {
                when(val value = expr.value) {
                    is String -> listOf(OpCode.LoadConstant(StringConstantInfo(value)))
                    else -> throw NotImplementedError()
                }
            } else if (expr is ASTNodes.InfixNode && expr.operator == ASTNodes.InfixOperator.PLUS) {
                visit(expr)
            } else {
                throw NotImplementedError()
            }

            return listOf(OpCode.Getstatic(fieldReferenceConstantInfo))
                .plus(opcOdesLoadingWhatToPrint)
                .plus(OpCode.Invokevirtual(methodReferenceConstantInfo))
        } else {
            throw NotImplementedError()
        }
    }

    override fun visit(infixNode: ASTNodes.InfixNode): List<OpCode> {
        val leftExpression = infixNode.leftExpression
        if (infixNode.operator == ASTNodes.InfixOperator.PLUS && leftExpression is ASTNodes.ValueNode<*> && leftExpression.value is String) {
            return listOf(
                OpCode.New(ClassConstantInfo("java/lang/StringBuilder")),
                OpCode.Dup(),
                OpCode.Invokespecial(MethodReferenceConstantInfo(ClassConstantInfo("java/lang/StringBuilder"), NameAndTypeConstantInfo("<init>", "()V"))),
                OpCode.LoadConstant(StringConstantInfo(leftExpression.value as String)),
                OpCode.Invokevirtual(MethodReferenceConstantInfo(ClassConstantInfo("java/lang/StringBuilder"), NameAndTypeConstantInfo("append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;"))))
                .plus(dispatch(infixNode.rightExpression))
                .plus(OpCode.Invokevirtual(MethodReferenceConstantInfo(ClassConstantInfo("java/lang/StringBuilder"), NameAndTypeConstantInfo("append", "(I)Ljava/lang/StringBuilder;"))))
                .plus(OpCode.Invokevirtual(MethodReferenceConstantInfo(ClassConstantInfo("java/lang/StringBuilder"), NameAndTypeConstantInfo("toString", "()Ljava/lang/String;"))))
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
            "int" -> OpCode.StoreInt(variableSymbol)
            "long" -> OpCode.StoreLong(variableSymbol)
            "float" -> OpCode.StoreFloat(variableSymbol)
            "double" -> OpCode.StoreDouble(variableSymbol)
            "boolean" -> OpCode.StoreInt(variableSymbol)
            "char" -> OpCode.StoreInt(variableSymbol)
            "byte" -> OpCode.StoreInt(variableSymbol)
            "short" -> OpCode.StoreInt(variableSymbol)
            else -> throw NotImplementedError("typename:${variableSymbol.type.name}")
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
            is Int -> OpCode.IntConstant(value)
            is Long -> OpCode.LongConstant(value)
            is Float -> OpCode.FloatConstant(value)
            is Double -> OpCode.DoubleConstant(value)
            is Boolean -> if(value) { OpCode.Iconst_1() } else { OpCode.Iconst_0() }
            is Char ->  OpCode.IntConstant(value.code)
            is Byte ->  OpCode.IntConstant(value.toInt())
            is Short ->  OpCode.IntConstant(value.toInt())
            else -> throw NotImplementedError("type${value.javaClass.name}")
        })
    }

    override fun visit(nestedIdentifierNode: ASTNodes.NestedIdentifierNode): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(comparisonNode: ASTNodes.ComparisonNode): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(rawIdentifierNode: ASTNodes.RawIdentifierNode?): List<OpCode> {
        TODO("Not yet implemented")
    }

    override fun visit(resolvedIdentifierNode: ASTNodes.ResolvedIdentifierNode): List<OpCode> {
        return listOf(when(resolvedIdentifierNode.symbol.type.name) {
            "int" -> OpCode.LoadInt(resolvedIdentifierNode.symbol)
            "long" -> OpCode.LoadLong(resolvedIdentifierNode.symbol)
            "float" -> OpCode.LoadFloat(resolvedIdentifierNode.symbol)
            "double" -> OpCode.LoadDouble(resolvedIdentifierNode.symbol)
            "boolean" -> OpCode.LoadInt(resolvedIdentifierNode.symbol)
            "char" -> OpCode.LoadInt(resolvedIdentifierNode.symbol)
            "byte" -> OpCode.LoadInt(resolvedIdentifierNode.symbol)
            "short" -> OpCode.LoadInt(resolvedIdentifierNode.symbol)
            else -> throw NotImplementedError("typename:${resolvedIdentifierNode.symbol.type.name}")
        })
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
}