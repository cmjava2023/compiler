package org.cmjava2023

import org.cmjava2023.classfilespecification.*
import org.cmjava2023.classfilespecification.constantpool.ClassConstantInfo
import org.cmjava2023.classfilespecification.constantpool.ConstantInfo
import org.cmjava2023.classfilespecification.constantpool.Utf8ConstantInfo
import java.util.ArrayList

class ClassfileModelFromAst {
    companion object {
        private const val PACKAGE_DELIMITER_IN_CLASS_FILES = "/"
        private const val PACKAGE_DELIMITER_IN_JAVA_FILES = "."
    }

    private val constantInfos = mutableListOf<ConstantInfo>()
    private val methodInfos = mutableListOf<MethodInfo>()
    private val classAccessModifiers = mutableListOf<ClassAccessModifier>()

    private fun resetFields() {
        constantInfos.clear()
        methodInfos.clear()
        classAccessModifiers.clear()
    }

    fun generate(ast: ASTNodes.StartNode): ClassfileModel {
        resetFields()
        parseTopLevelStatements(ast)

        return ClassfileModel(
                constantInfos,
            classAccessModifiers,
                0,
                0,
                listOf(),
                0,
                listOf(),
                methodInfos,
                0,
                listOf()
        )
    }

    private fun parseTopLevelStatements(ast: ASTNodes.StartNode) {
        var fullyQualifiedClassName = ""

        for (statement in ast.body) {
            if (statement is ASTNodes.PackageNode) {
                fullyQualifiedClassName += statement.identifier.replace(
                    PACKAGE_DELIMITER_IN_JAVA_FILES,
                    PACKAGE_DELIMITER_IN_CLASS_FILES
                )
            } else if (statement is ASTNodes.ClassNode) {
                fullyQualifiedClassName += PACKAGE_DELIMITER_IN_CLASS_FILES + statement.identifier
                for (modifierNode in statement.modifier) {
                    classAccessModifiers.add(ClassAccessModifier.fromASTModifier(modifierNode))
                }
                parseStatements(statement.body)
            }
        }
        if (!classAccessModifiers.contains(ClassAccessModifier.ACC_FINAL)) {
            classAccessModifiers.add(ClassAccessModifier.ACC_SUPER)
        }

        constantInfos.add(ClassConstantInfo(Utf8ConstantInfo(fullyQualifiedClassName)))
        constantInfos.add(ClassConstantInfo(Utf8ConstantInfo("java/lang/Object")))
    }

    private fun parseStatements(statements: List<ASTNodes.Statement>) {
        for (statement in statements) {
            when (statement) {
                is ASTNodes.FunctionNode -> parseFunctionNode(statement)
                is ASTNodes.FunctionCallNode -> parseFunctionCallNode(statement)
                else -> throw NotImplementedError()
            }
        }

        return
    }

    private fun parseFunctionCallNode(functionCallNode: ASTNodes.FunctionCallNode) {
        TODO("Not yet implemented")
    }

    private fun parseFunctionNode(functionNode: ASTNodes.FunctionNode) {
        val returnCode = if (functionNode.returnType == "void") { "V" } else { throw  NotImplementedError() }
        val parameterCodes = parseParameterCodes(functionNode.parameters)
        methodInfos.add(
            MethodInfo(
                functionNode.modifier.map { MethodAccessModifier.fromASTModifier(it) },
                Utf8ConstantInfo(functionNode.identifier),
                Utf8ConstantInfo("(${parameterCodes.joinToString("") { "$it;" }})$returnCode")
            )
        )
        parseStatements(functionNode.body)
    }

    private fun parseParameterCodes(parameters: ArrayList<ASTNodes.ParameterNode>): List<String> {
        val result = mutableListOf<String>()
        for (parameter in parameters) {
            if (parameter.type == "String[]") {
                result.add("[Ljava/lang/String")
            } else {
                throw NotImplementedError()
            }
        }

        return result
    }
}