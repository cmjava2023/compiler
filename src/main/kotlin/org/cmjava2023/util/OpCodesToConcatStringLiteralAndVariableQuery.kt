package org.cmjava2023.util

import org.cmjava2023.classfilespecification.constantpool.ConstantPoolEntry.*
import org.cmjava2023.classfilespecification.constantpool.TypeDescriptor
import org.cmjava2023.classfilespecification.opCodes.OpCode
import org.cmjava2023.classfilespecification.opCodes.OpCodeOrPlaceHolder
import org.cmjava2023.classfilespecification.opCodes.PlaceHolderLoadConstantPoolItem

class OpCodesToConcatStringLiteralAndVariableQuery {
    companion object {
        fun fetch(
            stringLiteralValue: String,
            opCodesOrPlaceHoldersToLoadRightValue: List<OpCodeOrPlaceHolder>,
            typeDescriptorOfRightValue: TypeDescriptor
        ): List<OpCodeOrPlaceHolder> {
            
            val result = mutableListOf<OpCodeOrPlaceHolder>()
            result += OpCode.New(ClassConstant.STRING_BUILDER)
            result += OpCode.Dup()
            result += OpCode.Invokespecial(MethodReferenceConstant.defaultConstructorOf(ClassConstant.STRING_BUILDER))
            result += PlaceHolderLoadConstantPoolItem(StringConstant(stringLiteralValue))
            result += OpCode.Invokevirtual(MethodReferenceConstant.STRING_BUILDER_APPEND_STRING)
            result.addAll(opCodesOrPlaceHoldersToLoadRightValue)
            result += OpCode.Invokevirtual(MethodReferenceConstant.a)
            result += OpCode.Invokevirtual(MethodReferenceConstant.STRING_BUILDER_TO_STRING)            
            return 
        }
    }
}