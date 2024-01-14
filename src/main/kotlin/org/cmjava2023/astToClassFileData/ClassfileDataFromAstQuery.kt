package org.cmjava2023.astToClassFileData

import org.cmjava2023.ast.ASTNodes
import org.cmjava2023.classFileDataToBytes.LocalVariableIndexAssigner
import org.cmjava2023.classfilespecification.*
import org.cmjava2023.classfilespecification.attributeInfo.CodeAttributeInfo
import org.cmjava2023.classfilespecification.constantpool.ConstantPoolEntry
import org.cmjava2023.classfilespecification.constantpool.MethodTypeDescriptor
import org.cmjava2023.placeHolders.queries.LoadVariableOperationQuery
import org.cmjava2023.placeHolders.queries.StoreVariableOperationQuery

class ClassfileDataFromAstQuery {
    companion object {
        private const val PACKAGE_DELIMITER_IN_CLASS_FILES = "/"
    }

    private val constantPoolEntries = mutableListOf<ConstantPoolEntry>()
    private val methodInfos = mutableListOf<MethodInfo>()
    private val classAccessModifiers = mutableListOf<ClassAccessModifier>()

    fun fetch(startNode: ASTNodes.StartNode): ClassfileData {
        val packageNameWithDelimiterForClassFile = parseAndGetPackageName(startNode)

        methodInfos += MethodInfo(
            listOf(MethodAccessModifier.ACC_PUBLIC),
            "<init>",
            MethodTypeDescriptor.voidWithParameters(),
            listOf(
                CodeAttributeInfo(
                    listOf(
                        Operation.Aload_0(),
                        Operation.Invokespecial(ConstantPoolEntry.MethodReferenceConstant.defaultConstructorOf(ConstantPoolEntry.ClassConstant.OBJECT_CLASS_CLASSNAME)),
                        Operation.Return()
                    ),
                    1u
                )
            )
        )

        return ClassfileData(
            packageNameWithDelimiterForClassFile,
            constantPoolEntries,
            classAccessModifiers,
            methodInfos
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

        val methodTypeDescriptor = MethodTypeDescriptor.forFunctionNode(functionNode)
        val localVariableIndexAssigner = LocalVariableIndexAssigner(methodTypeDescriptor)
        val loadVariableOperationQuery = LoadVariableOperationQuery(localVariableIndexAssigner)
        val storeVariableOperationQuery = StoreVariableOperationQuery(localVariableIndexAssigner)
        
        val astTraverserToGetPlaceHolders = AstTraverserToGetPlaceHolders(storeVariableOperationQuery)
        val astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack = AstTraverserToGetPlaceHoldersLeavingKnownTypeOnStack(loadVariableOperationQuery)
        astTraverserToGetPlaceHolders.init(astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack)
        astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack.init(astTraverserToGetPlaceHolders)

        val placeHolders = astTraverserToGetPlaceHolders.visit(functionNode)
        
        methodInfos.add(
            MethodInfo(
                methodModifiers,
                functionNode.functionSymbol.name,
                methodTypeDescriptor,
                listOf(
                    CodeAttributeInfo(placeHolders, localVariableIndexAssigner.maxLocalVariableSize()),
                )
            )
        )
    }
}
