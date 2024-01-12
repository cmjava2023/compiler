package org.cmjava2023.astToClassFileModel

import org.cmjava2023.ast.ASTNodes
import org.cmjava2023.classfilespecification.*
import org.cmjava2023.classfilespecification.attributeInfo.CodeAttributeInfo
import org.cmjava2023.classfilespecification.constantpool.*
import org.cmjava2023.classfilespecification.opCodes.OpCode
import java.util.ArrayList

class ClassfileModelFromAst {
    companion object {
        private const val PACKAGE_DELIMITER_IN_CLASS_FILES = "/"
    }

    private val constantPoolEntries = mutableListOf<ConstantPoolEntry>()
    private val methodInfos = mutableListOf<MethodInfo>()
    private val classAccessModifiers = mutableListOf<ClassAccessModifier>()

    private fun resetFields() {
        constantPoolEntries.clear()
        methodInfos.clear()
        classAccessModifiers.clear()
    }

    fun generate(ast: ASTNodes.StartNode): ClassfileModel {
        resetFields()
        val packageNameWithDelimiterForClassFile = parseTopLevelStatementsAndGetPackageName(ast)

        methodInfos.add(createDefaultConstructorMethodInfo())

        return ClassfileModel(
            packageNameWithDelimiterForClassFile,
            constantPoolEntries,
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
                        MethodReferenceConstantPoolEntry(
                            ClassConstantPoolEntry("java/lang/Object"),
                            NameAndTypeConstantPoolEntry("<init>", "()V")
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
                classAccessModifiers.add(
                    ClassAccessModifier.fromASTModifier(
                        statement.classSymbol.accessModifier
                    )
                )
                parseStatements(statement.body.toList())
            }
        }
        if (!classAccessModifiers.contains(ClassAccessModifier.ACC_FINAL)) {
            classAccessModifiers.add(ClassAccessModifier.ACC_SUPER)
        }

        constantPoolEntries.add(
            ClassConstantPoolEntry(
                packages.plus(className)
                    .joinToString(PACKAGE_DELIMITER_IN_CLASS_FILES)
            )
        )
        constantPoolEntries.add(ClassConstantPoolEntry("java/lang/Object"))

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
                listOf(CodeAttributeInfo(FunctionCodeAstTraverser().visit(functionNode))),
            )
        )
    }

    private fun createMethodTypeDescriptor(functionNode: ASTNodes.FunctionNode): String {
        val returnCode = if (functionNode.functionSymbol.type.name == "void") {
            "V"
        } else {
            throw NotImplementedError()
        }
        val parameterTypeCodes = parseParameterTypeCodes(
            functionNode.parameters.toCollection(ArrayList())
        )
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
