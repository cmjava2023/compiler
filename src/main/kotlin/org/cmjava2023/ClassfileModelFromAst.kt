package org.cmjava2023

import org.cmjava2023.classfilespecification.*
import org.cmjava2023.classfilespecification.attributeInfo.CodeAttributeInfo
import org.cmjava2023.classfilespecification.codeParts.FunctionCallCodePart
import org.cmjava2023.classfilespecification.constantpool.*
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
        var className = ""

        for (statement in ast.body) {
            if (statement is ASTNodes.PackageNode) {
                fullyQualifiedClassName += statement.identifier.replace(
                    PACKAGE_DELIMITER_IN_JAVA_FILES,
                    PACKAGE_DELIMITER_IN_CLASS_FILES
                )
            } else if (statement is ASTNodes.ClassNode) {
                className = statement.identifier
                fullyQualifiedClassName += PACKAGE_DELIMITER_IN_CLASS_FILES + className
                for (modifierNode in statement.modifier) {
                    classAccessModifiers.add(ClassAccessModifier.fromASTModifier(modifierNode))
                }
                parseStatements(statement.body)
            }
        }
        if (!classAccessModifiers.contains(ClassAccessModifier.ACC_FINAL)) {
            classAccessModifiers.add(ClassAccessModifier.ACC_SUPER)
        }

        constantInfos.add(ClassConstantInfo(Utf8ConstantInfo(className))) // TODO decide whether to put test files into package folders to be able to support package again
        constantInfos.add(ClassConstantInfo(Utf8ConstantInfo("java/lang/Object")))
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

    private fun parseStatementsInCode(statements: List<ASTNodes.Statement>): List<FunctionCallCodePart> {
        val result = mutableListOf<FunctionCallCodePart>()
        for (statement in statements) {
            when (statement) {
                is ASTNodes.FunctionCallNode -> {
                    if (statement.identifier == "System.out.println") {
                        val identifierElements = statement.identifier.split(".")
                        val className = identifierElements.first()
                        val fieldName = identifierElements[1]
                        val methodName = identifierElements.last()

                        val qualifiedClassName = "java/lang/$className"

                        val fieldReferenceConstantInfo = FieldReferenceConstantInfo(ClassConstantInfo(Utf8ConstantInfo(qualifiedClassName)), NameAndTypeConstantInfo(Utf8ConstantInfo(fieldName), Utf8ConstantInfo("Ljava/io/PrintStream;")))
                        val methodReferenceConstantInfo = MethodReferenceConstantInfo(ClassConstantInfo(Utf8ConstantInfo("java/io/PrintStream")), NameAndTypeConstantInfo(Utf8ConstantInfo(methodName), Utf8ConstantInfo("(Ljava/lang/String;)V")))

                        val whatToPrint = statement.values.single().value.removePrefix("\"").removeSuffix("\"") // TODO should be already removed in ast

                        result.add(FunctionCallCodePart(fieldReferenceConstantInfo, methodReferenceConstantInfo, listOf(StringConstantInfo(Utf8ConstantInfo(whatToPrint)))))
                    }
                }
            }
        }

        return result
    }

    private fun parseFunctionNode(functionNode: ASTNodes.FunctionNode) {
        val returnCode = if (functionNode.returnType == "void") { "V" } else { throw  NotImplementedError() }
        val parameterCodes = parseParameterCodes(functionNode.parameters)

        methodInfos.add(
            MethodInfo(
                functionNode.modifier.map { MethodAccessModifier.fromASTModifier(it) },
                Utf8ConstantInfo(functionNode.identifier),
                Utf8ConstantInfo("(${parameterCodes.joinToString("") { "$it;" }})$returnCode"),
                listOf(CodeAttributeInfo(parseStatementsInCode(functionNode.body)))
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