package org.cmjava2023.placeHolders.queries

import org.cmjava2023.ast.ASTNodes
import org.cmjava2023.astToClassFileData.AstTraverserToGetPlaceHoldersLeavingKnownTypeOnStack
import org.cmjava2023.classfilespecification.Operation
import org.cmjava2023.classfilespecification.constantpool.ConstantPoolEntry
import org.cmjava2023.classfilespecification.constantpool.TypeDescriptor
import org.cmjava2023.placeHolders.PlaceHolder

class SystemOutPrintlnPlaceHoldersQuery {
    companion object {
        fun fetch(functionCallNode: ASTNodes.FunctionCallNode, astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack: AstTraverserToGetPlaceHoldersLeavingKnownTypeOnStack): List<PlaceHolder> {
            val loadArgumentPlaceHoldersLeavingKnownTypeOnStack = astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack.dispatch(functionCallNode.argumentExpressions()
                .single())
            val result = mutableListOf<PlaceHolder>()
            result += Operation.Getstatic(ConstantPoolEntry.FieldReferenceConstant.SYSTEM_OUT)
            result += loadArgumentPlaceHoldersLeavingKnownTypeOnStack.placeHolders
            result += Operation.Invokevirtual(ConstantPoolEntry.MethodReferenceConstant.printStreamPrintlnFor(TypeDescriptor.createForBuildInType(loadArgumentPlaceHoldersLeavingKnownTypeOnStack.type)))
            return result
        }
    }
}