
package org.cmjava2023

import org.cmjava2023.ast.ASTNodes
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
        val packageNameWithDelimiterForClassFile = parseTopLevelStatementsAndGetPackageName(ast)

        methodInfos.add(MethodInfo(
            listOf(MethodAccessModifier.ACC_PUBLIC),
            Utf8ConstantInfo("<init>"),
            Utf8ConstantInfo("()V"),
            listOf(CodeAttributeInfo(listOf()))
        ))

        return ClassfileModel(
            packageNameWithDelimiterForClassFile,
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

    private fun parseTopLevelStatementsAndGetPackageName(ast: ASTNodes.StartNode): String {
        var className = ""
        var packageNameWithDelimiterForClassFile = ""

        for (statement in ast.body) {
            if (statement is ASTNodes.PackageNode) {
                var firstElement = true
                for (element in statement.nestedIdentifier) {
                    if (firstElement) {
                        packageNameWithDelimiterForClassFile += element
                        firstElement = false
                    } else {
                        packageNameWithDelimiterForClassFile += PACKAGE_DELIMITER_IN_CLASS_FILES + element
                    }
                }

            } else if (statement is ASTNodes.ClassNode) {
                className = statement.classSymbol.name
                classAccessModifiers.add(ClassAccessModifier.fromASTModifier(statement.classSymbol.accessModifier))
                parseStatements(statement.body.toList())
            }
        }
        if (!classAccessModifiers.contains(ClassAccessModifier.ACC_FINAL)) {
            classAccessModifiers.add(ClassAccessModifier.ACC_SUPER)
        }

        constantInfos.add(ClassConstantInfo(Utf8ConstantInfo(packageNameWithDelimiterForClassFile + PACKAGE_DELIMITER_IN_CLASS_FILES + className)))
        constantInfos.add(ClassConstantInfo(Utf8ConstantInfo("java/lang/Object")))

        return packageNameWithDelimiterForClassFile
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
                    if (statement.nestedIdentifier[0] == "System") {
                        if (statement.nestedIdentifier[1] == "out") {
                            if (statement.nestedIdentifier[2] == "println") {
                                val className = statement.nestedIdentifier[0]
                                val fieldName = statement.nestedIdentifier[1]
                                val methodName = statement.nestedIdentifier[2]

                                val qualifiedClassName = "java/lang/$className"

                                val fieldReferenceConstantInfo = FieldReferenceConstantInfo(ClassConstantInfo(Utf8ConstantInfo(qualifiedClassName)), NameAndTypeConstantInfo(Utf8ConstantInfo(fieldName), Utf8ConstantInfo("Ljava/io/PrintStream;"))) // TODO should ast split field reference and call on field in multiple expressions?
                                val methodReferenceConstantInfo = MethodReferenceConstantInfo(ClassConstantInfo(Utf8ConstantInfo("java/io/PrintStream")), NameAndTypeConstantInfo(Utf8ConstantInfo(methodName), Utf8ConstantInfo("(Ljava/lang/String;)V")))

                                var whatToPrint: String

                                val expr = statement.values[0]
                                if(expr is ASTNodes.ValueNode){
                                    whatToPrint = expr.value.removePrefix("\"").removeSuffix("\"") // TODO should be already removed in ast
                                } else {
                                    throw NotImplementedError()
                                }

                                result.add(FunctionCallCodePart(fieldReferenceConstantInfo, methodReferenceConstantInfo, listOf(StringConstantInfo(Utf8ConstantInfo(whatToPrint)))))

                            }
                        }
                        }
                }
            }
        }

        return result
    }

    private fun parseFunctionNode(functionNode: ASTNodes.FunctionNode) {
        val returnCode = if (functionNode.functionSymbol.type.name == "void") { "V" } else {throw  NotImplementedError() }
        val parameterCodes = parseParameterCodes(functionNode.parameters.toCollection(ArrayList()))
        val methodModifiers = listOf(
            MethodAccessModifier.fromASTAccessModifier(functionNode.functionSymbol.accessModifier),
            MethodAccessModifier.fromASTModifier(functionNode.functionSymbol.instanceModifier)
        )
        methodInfos.add(
            MethodInfo(
                 methodModifiers,
                Utf8ConstantInfo(functionNode.functionSymbol.name),
                Utf8ConstantInfo("(${parameterCodes.joinToString("") { "$it;" }})$returnCode"),
                listOf(CodeAttributeInfo(parseStatementsInCode(functionNode.body.toList()))),
            )
        )
    }

    private fun parseParameterCodes(parameters: ArrayList<ASTNodes.ParameterNode>): List<String> {
        val result = mutableListOf<String>()
        for (parameter in parameters) {
            if (true) { // TODO parameter.parameterSymbol.name == "String[]"
                result.add("[Ljava/lang/String")
            } else {
                throw NotImplementedError()
            }
        }

        return result
    }
}
