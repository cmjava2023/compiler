package org.cmjava2023.placeHolders.queries

import org.cmjava2023.classfilespecification.Operation
import org.cmjava2023.symboltable.BuiltInType
import org.cmjava2023.symboltable.Type

class TypeCastOpCodeQuery {
    companion object {
        fun fetch(fromType: Type, toType: Type): Operation {
            return when (fromType) {
                BuiltInType.INT -> castIntTo(toType)
                BuiltInType.LONG -> castLongTo(toType)
                BuiltInType.FLOAT -> castFloatTo(toType)
                BuiltInType.DOUBLE -> castDoubleTo(toType)
                else -> throw NotImplementedError(fromType.name)
            }
        }

        private fun castDoubleTo(type: Type) = when (type) {
            BuiltInType.INT -> Operation.D2i()
            BuiltInType.LONG -> Operation.D2l()
            BuiltInType.FLOAT -> Operation.D2f()
            else -> throw NotImplementedError(type.name)
        }

        private fun castFloatTo(type: Type) = when (type) {
            BuiltInType.INT -> Operation.F2i()
            BuiltInType.LONG -> Operation.F2l()
            BuiltInType.DOUBLE -> Operation.F2d()
            else -> throw NotImplementedError(type.name)
        }

        private fun castLongTo(type: Type) = when (type) {
            BuiltInType.INT -> Operation.L2i()
            BuiltInType.FLOAT -> Operation.L2f()
            BuiltInType.DOUBLE -> Operation.L2d()
            else -> throw NotImplementedError(type.name)
        }

        private fun castIntTo(type: Type): Operation {
            return when (type) {
                BuiltInType.BYTE -> Operation.I2b()
                BuiltInType.CHAR -> Operation.I2c()
                BuiltInType.SHORT -> Operation.I2s()
                BuiltInType.LONG -> Operation.I2l()
                BuiltInType.FLOAT -> Operation.I2f()
                BuiltInType.DOUBLE -> Operation.I2d()
                else -> throw NotImplementedError(type.name)
            }
        }
    }
}