package org.cmjava2023.astToClassFileData

import org.cmjava2023.ast.ASTNodes
import org.cmjava2023.classfilespecification.*
import org.cmjava2023.classfilespecification.attributeInfo.CodeAttributeInfo
import org.cmjava2023.classfilespecification.constantpool.*

class ClassfileDataFromAstQuery {
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

    fun fetch(startNode: ASTNodes.StartNode): ClassfileData {
        resetFields()
        val packageNameWithDelimiterForClassFile = parseAndGetPackageName(startNode)

        methodInfos += MethodInfo.DEFAULT_CONSTRUCTOR

        return ClassfileData(
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

    private fun parseAndGetPackageName(startNode: ASTNodes.StartNode): String {
        val packages = mutableListOf<String>()
        var className = ""

        for (statement in startNode.body) {
            when (statement) {
                is ASTNodes.PackageNode -> packages.addAll(statement.nestedIdentifier)
                is ASTNodes.ClassNode -> {
                    className = statement.classSymbol.name
                    classAccessModifiers.add(
                        ClassAccessModifier.fromASTModifier(
                            statement.classSymbol.accessModifier
                        )
                    )
                    parseStatements(statement.body.toList())
                }

                else -> throw NotImplementedError(statement.javaClass.name)
            }
        }
        if (!classAccessModifiers.contains(ClassAccessModifier.ACC_FINAL)) {
            classAccessModifiers.add(ClassAccessModifier.ACC_SUPER)
        }

        constantPoolEntries += ConstantPoolEntry.ClassConstant(
            packages.plus(className)
                .joinToString(PACKAGE_DELIMITER_IN_CLASS_FILES)
        )
        constantPoolEntries += ConstantPoolEntry.ClassConstant.OBJECT

        return packages.joinToString(PACKAGE_DELIMITER_IN_CLASS_FILES)
    }

    private fun parseStatements(statements: List<ASTNodes.Statement>) {
        for (statement in statements) {
            when (statement) {
                is ASTNodes.FunctionNode -> parseFunctionNode(statement)
                else -> throw NotImplementedError(statement.javaClass.name)
            }
        }
    }

    private fun parseFunctionNode(functionNode: ASTNodes.FunctionNode) {
        val methodModifiers = listOf(
            MethodAccessModifier.fromASTAccessModifier(functionNode.functionSymbol.accessModifier),
            MethodAccessModifier.fromASTModifier(functionNode.functionSymbol.instanceModifier)
        )

        val astTraverserToGetPlaceHolders = AstTraverserToGetPlaceHolders()
        val astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack = AstTraverserToGetPlaceHoldersLeavingKnownTypeOnStack()
        astTraverserToGetPlaceHolders.init(astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack)
        astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack.init(astTraverserToGetPlaceHolders)

        methodInfos.add(
            MethodInfo(
                methodModifiers,
                functionNode.functionSymbol.name,
                MethodTypeDescriptor.forFunctionNode(functionNode),
                listOf(CodeAttributeInfo(astTraverserToGetPlaceHolders.visit(functionNode))),
            )
        )
    }
}
