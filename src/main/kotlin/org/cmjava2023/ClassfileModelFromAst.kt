package org.cmjava2023

import org.cmjava2023.ast.ASTNodes
import org.cmjava2023.classfilespecification.*
import org.cmjava2023.classfilespecification.attributeInfo.CodeAttributeInfo
import org.cmjava2023.classfilespecification.constantpool.*
import java.util.ArrayList

class ClassfileModelFromAst {
    companion object {
        private const val PACKAGE_DELIMITER_IN_CLASS_FILES = "/"
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
        val packageNameWithDelimiterForClassFile = parseTopLevelStatementsAndGetPackageName(ast)

        methodInfos.add(createDefaultConstructorMethodInfo())

        return ClassfileModel(
            packageNameWithDelimiterForClassFile,
            constantInfos,
            classAccessModifiers,
            0u,
            0u,
            listOf(),
            0u,
            listOf(),
            methodInfos,
            0u,
            listOf()
        )
    }

    private fun createDefaultConstructorMethodInfo() = MethodInfo(
        listOf(MethodAccessModifier.ACC_PUBLIC),
        "<init>",
        "()V",
        listOf(
            CodeAttributeInfo(
                listOf(
                    OpCode.Aload_0(),
                    OpCode.Invokespecial(
                        MethodReferenceConstantInfo(
                            ClassConstantInfo("java/lang/Object"),
                            NameAndTypeConstantInfo("<init>", "()V")
                        )
                    ),
                    OpCode.Return()
                )
            )
        )
    )

    private fun parseTopLevelStatementsAndGetPackageName(ast: ASTNodes.StartNode): String {
        val packages = mutableListOf<String>()
        var className = ""

        for (statement in ast.body) {
            if (statement is ASTNodes.PackageNode) {
                packages.addAll(statement.nestedIdentifier)

            } else if (statement is ASTNodes.ClassNode) {
                className = statement.classSymbol.name
                classAccessModifiers.add(ClassAccessModifier.fromASTModifier(statement.classSymbol.accessModifier))
                parseStatements(statement.body.toList())
            }
        }
        if (!classAccessModifiers.contains(ClassAccessModifier.ACC_FINAL)) {
            classAccessModifiers.add(ClassAccessModifier.ACC_SUPER)
        }

        constantInfos.add(ClassConstantInfo(packages.plus(className).joinToString(PACKAGE_DELIMITER_IN_CLASS_FILES)))
        constantInfos.add(ClassConstantInfo("java/lang/Object"))

        return packages.joinToString(PACKAGE_DELIMITER_IN_CLASS_FILES)
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

    private fun parseStatementsInCode(statements: List<ASTNodes.Statement>): List<OpCode> {
        val result = mutableListOf<OpCode>()
        for (statement in statements) {
            when (statement) {
                is ASTNodes.FunctionCallNode -> {
                    if (statement.nestedIdentifier[0] == "System") {
                        if (statement.nestedIdentifier[1] == "out") {
                            if (statement.nestedIdentifier[2] == "println") {
                                val className = statement.nestedIdentifier[0]
                                val fieldName = statement.nestedIdentifier[1]
                                val methodName = statement.nestedIdentifier[2]

                                val qualifiedClassName = "java/lang/$className"

                                val fieldReferenceConstantInfo = FieldReferenceConstantInfo(
                                    ClassConstantInfo(qualifiedClassName),
                                    NameAndTypeConstantInfo(fieldName, "Ljava/io/PrintStream;")
                                )
                                val methodReferenceConstantInfo = MethodReferenceConstantInfo(
                                    ClassConstantInfo("java/io/PrintStream"),
                                    NameAndTypeConstantInfo(methodName, "(Ljava/lang/String;)V")
                                )

                                var whatToPrint: String

                                val expr = statement.values[0]
                                if (expr is ASTNodes.ValueNode) {
                                    whatToPrint = expr.value
                                } else {
                                    throw NotImplementedError()
                                }

                                result.addAll(
                                    listOf(
                                        OpCode.Getstatic(fieldReferenceConstantInfo),
                                        OpCode.LoadConstant(StringConstantInfo(whatToPrint)),
                                        OpCode.Invokevirtual(methodReferenceConstantInfo),
                                        OpCode.Return()
                                    )
                                )

                            }
                        }
                    }
                }
            }
        }

        return result
    }

    private fun parseFunctionNode(functionNode: ASTNodes.FunctionNode) {
        val methodModifiers = listOf(
            MethodAccessModifier.fromASTAccessModifier(functionNode.functionSymbol.accessModifier),
            MethodAccessModifier.fromASTModifier(functionNode.functionSymbol.instanceModifier)
        )
        methodInfos.add(
            MethodInfo(
                methodModifiers,
                functionNode.functionSymbol.name,
                createMethodTypeDescriptor(functionNode),
                listOf(CodeAttributeInfo(parseStatementsInCode(functionNode.body.toList()))),
            )
        )
    }

    private fun createMethodTypeDescriptor(functionNode: ASTNodes.FunctionNode): String {
        val returnCode = if (functionNode.functionSymbol.type.name == "void") {
            "V"
        } else {
            throw NotImplementedError()
        }
        val parameterTypeCodes = parseParameterTypeCodes(functionNode.parameters.toCollection(ArrayList()))
        return "(${parameterTypeCodes.joinToString("") { "$it;" }})$returnCode"
    }

    private fun parseParameterTypeCodes(parameters: ArrayList<ASTNodes.ParameterNode>): List<String> {
        val result = mutableListOf<String>()
        for (parameter in parameters) {
            if (parameter.parameterSymbol.type.name == "String[]") {
                result.add("[Ljava/lang/String")
            } else {
                throw NotImplementedError()
            }
        }

        return result
    }
}
