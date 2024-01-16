package org.cmjava2023.placeHolders.queries

import org.cmjava2023.astToClassFileData.ConstantPoolBuilder
import org.cmjava2023.classfilespecification.constantpool.ConstantPoolEntry
import org.cmjava2023.classfilespecification.Operation
import org.cmjava2023.placeHolders.PlaceHolder

class LoadConstantOperationQuery(val constantPoolBuilder: ConstantPoolBuilder) {
    fun fetch(value: Any): PlaceHolder {
        return when (value) {
            is String -> constantFromConstantPool(ConstantPoolEntry.StringConstant(value))
            is Boolean -> if (value) { Operation.Iconst_1() } else { Operation.Iconst_0() }
            is Char -> integerConstant(value.code)
            is Byte -> integerConstant(value.toInt())
            is Short -> integerConstant(value.toInt())
            is Int -> integerConstant(value)
            is Long -> longConstant(value)
            is Float -> floatConstant(value)
            is Double -> doubleConstant(value)
            is ConstantPoolEntry -> constantFromConstantPool(value)
            else -> throw NotImplementedError(value.javaClass.name)
        }
    }

    private fun constantFromConstantPool(constantPoolEntry: ConstantPoolEntry): Operation {
        val index = constantPoolBuilder.getIndexByResolvingOrAdding(constantPoolEntry)
        return if (index <= UByte.MAX_VALUE) {
            Operation.Ldc(index.toUByte())
        } else {
            Operation.Ldc_w(index)
        }
    }

    private fun integerConstant(int: Int): Operation {
        return when (int) {
            -1 -> Operation.Iconst_m1()
            0 -> Operation.Iconst_0()
            1 -> Operation.Iconst_1()
            2 -> Operation.Iconst_2()
            3 -> Operation.Iconst_3()
            4 -> Operation.Iconst_4()
            5 -> Operation.Iconst_5()
            else -> if (int >= Byte.MIN_VALUE && int <= Byte.MAX_VALUE) {
                Operation.Bipush(int.toByte())
            } else if (int >= Short.MIN_VALUE && int <= Short.MAX_VALUE) {
                Operation.Sipush(int.toShort())
            } else {
                constantFromConstantPool(ConstantPoolEntry.IntegerConstant(int))
            }
        }
    }

    private fun longConstant(long: Long): Operation {
        return when (long) {
            0L -> Operation.Lconst_0()
            1L -> Operation.Lconst_1()
            else -> Operation.Ldc2_w(constantPoolBuilder.getIndexByResolvingOrAdding(ConstantPoolEntry.LongConstant(long)))
        }
    }

    private fun floatConstant(float: Float): Operation {
        return when (float) {
            0f -> Operation.Fconst_0()
            1f -> Operation.Fconst_1()
            2f -> Operation.Fconst_2()
            else -> constantFromConstantPool(ConstantPoolEntry.FloatConstant(float))
        }
    }

    private fun doubleConstant(double: Double): Operation {
        return when (double) {
            0.0 -> Operation.Dconst_0()
            1.0 -> Operation.Dconst_1()
            else -> Operation.Ldc2_w(constantPoolBuilder.getIndexByResolvingOrAdding(ConstantPoolEntry.DoubleConstant(double)))
        }
    }
}