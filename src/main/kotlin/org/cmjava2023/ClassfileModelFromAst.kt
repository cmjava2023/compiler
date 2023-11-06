package org.cmjava2023

import org.cmjava2023.classfilespecification.*
import java.util.ArrayList

class ClassfileModelFromAst {
    companion object {
        private const val PACKAGE_DELIMITER_IN_CLASS_FILES = "/"
        private const val PACKAGE_DELIMITER_IN_JAVA_FILES = "."
    }

    private val constantInfos = mutableListOf<ConstantInfo>()
    private val methodInfos = mutableListOf<MethodInfo>()

    private fun resetFields() {
        constantInfos.clear()
        methodInfos.clear()
    }

    fun generate(ast: ASTNodes.StartNode): ClassfileModel {
        resetFields()
        var fullyQualifiedClassName = ""
        val modifiers = mutableListOf<ClassAccessModifier>()

        for (statement in ast.body) {
            if (statement is ASTNodes.PackageNode) {
                fullyQualifiedClassName += statement.identifier.replace(PACKAGE_DELIMITER_IN_JAVA_FILES, PACKAGE_DELIMITER_IN_CLASS_FILES)
            } else if (statement is ASTNodes.ClassNode) {
                fullyQualifiedClassName += PACKAGE_DELIMITER_IN_CLASS_FILES + statement.identifier
                for(modifierNode in statement.modifier) {
                    modifiers.add(ClassAccessModifier.fromASTModifier(modifierNode))
                }
                parseStatements(statement.body)
            }
        }
        if (!modifiers.contains(ClassAccessModifier.ACC_FINAL)) {
            modifiers.add(ClassAccessModifier.ACC_SUPER)
        }

        constantInfos.add(ClassConstantInfo(Utf8ConstantInfo(fullyQualifiedClassName)))
        constantInfos.add(ClassConstantInfo(Utf8ConstantInfo("java/lang/Object")))

        return ClassfileModel(
                constantInfos,
                modifiers,
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

    private fun parseStatements(statements: List<ASTNodes.Statement>) {
        for (statement in statements) {
            when (statement) {
                is ASTNodes.FunctionNode -> parseFunctionNode(statement)
                else -> throw NotImplementedError()
            }
        }

        return
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