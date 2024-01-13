package org.cmjava2023.placeHolders.queries

import org.cmjava2023.ast.ASTNodes
import org.cmjava2023.astToClassFileModel.AstTraverserToGetPlaceHoldersLeavingKnownTypeOnStack
import org.cmjava2023.classfilespecification.Operation
import org.cmjava2023.classfilespecification.constantpool.ConstantPoolEntry
import org.cmjava2023.placeHolders.PlaceHolder

class SystemOutPrintlnPlaceHoldersQuery {
    companion object {
        fun fetch(functionCallNode: ASTNodes.FunctionCallNode, astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack: AstTraverserToGetPlaceHoldersLeavingKnownTypeOnStack): List<PlaceHolder> {
            val firstArgumentExpressions= functionCallNode.argumentExpressions.single()
            val placeHoldersLeavingKnownTypeOnStack = astTraverserToGetPlaceHoldersLeavingKnownTypeOnStack.dispatch(firstArgumentExpressions)

            val result = mutableListOf<PlaceHolder>()
            result += Operation.Getstatic(ConstantPoolEntry.FieldReferenceConstant.SYSTEM_OUT)
            result.addAll(placeHoldersLeavingKnownTypeOnStack.placeHolders)
            result += Operation.Invokevirtual(ConstantPoolEntry.MethodReferenceConstant.stringBuilderToStringFor(placeHoldersLeavingKnownTypeOnStack.type))
            return result
        }
    }
}