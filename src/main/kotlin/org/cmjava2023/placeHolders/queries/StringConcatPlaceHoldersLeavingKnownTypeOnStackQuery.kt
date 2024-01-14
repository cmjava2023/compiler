package org.cmjava2023.placeHolders.queries

import org.cmjava2023.classfilespecification.constantpool.ConstantPoolEntry
import org.cmjava2023.classfilespecification.constantpool.TypeDescriptor
import org.cmjava2023.classfilespecification.Operation
import org.cmjava2023.placeHolders.PlaceHolder
import org.cmjava2023.placeHolders.PlaceHoldersLeavingKnownTypeOnStack
import org.cmjava2023.symboltable.BuiltInType

class StringConcatPlaceHoldersLeavingKnownTypeOnStackQuery {
    companion object {
        fun fetch(placeHoldersToLoadLeftString: List<PlaceHolder>, placeHoldersToLoadRightString: List<PlaceHolder>, rightExpressionType: BuiltInType): PlaceHoldersLeavingKnownTypeOnStack {
            val result = mutableListOf<PlaceHolder>()
            result += Operation.New(ConstantPoolEntry.ClassConstant.STRING_BUILDER)
            result += Operation.Dup()
            result += Operation.Invokespecial(ConstantPoolEntry.MethodReferenceConstant.defaultConstructorOf(ConstantPoolEntry.ClassConstant.STRING_BUILDER))
            result.addAll(placeHoldersToLoadLeftString)
            result += Operation.Invokevirtual(ConstantPoolEntry.MethodReferenceConstant.stringBuilderAppendFor(TypeDescriptor.STRING))
            result.addAll(placeHoldersToLoadRightString)
            result += Operation.Invokevirtual(ConstantPoolEntry.MethodReferenceConstant.stringBuilderAppendFor(TypeDescriptor.createForBuildInType(rightExpressionType)))
            result += Operation.Invokevirtual(ConstantPoolEntry.MethodReferenceConstant.STRING_BUILDER_TO_STRING)
            
            return PlaceHoldersLeavingKnownTypeOnStack(result, BuiltInType.STRING)
        }
    }
}