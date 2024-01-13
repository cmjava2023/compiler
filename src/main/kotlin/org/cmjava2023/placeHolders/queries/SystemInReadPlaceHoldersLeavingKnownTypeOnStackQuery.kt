package org.cmjava2023.placeHolders.queries

import org.cmjava2023.classfilespecification.constantpool.ConstantPoolEntry
import org.cmjava2023.classfilespecification.Operation
import org.cmjava2023.placeHolders.PlaceHoldersLeavingKnownTypeOnStack
import org.cmjava2023.symboltable.BuiltInType

class SystemInReadPlaceHoldersLeavingKnownTypeOnStackQuery {
    companion object {
        fun fetch(): PlaceHoldersLeavingKnownTypeOnStack {
            val result = mutableListOf<Operation>()
            result += Operation.Getstatic(ConstantPoolEntry.FieldReferenceConstant.SYSTEM_IN)
            result += Operation.Invokevirtual(ConstantPoolEntry.MethodReferenceConstant.SYSTEM_IN_READ)
            result += Operation.I2c()
            return PlaceHoldersLeavingKnownTypeOnStack(result, BuiltInType.INT)
        }
    }
}