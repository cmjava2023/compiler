package org.cmjava2023.placeHolders

import org.cmjava2023.classFileDataToBytes.ConstantPoolBuilder
import org.cmjava2023.classfilespecification.constantpool.ConstantPoolEntry
import org.cmjava2023.classfilespecification.Operation

interface LoadConstantPlaceHolder : PlaceHolder {
    fun toFinalOpCode(constantPoolBuilder: ConstantPoolBuilder): Operation
    
    companion object {
        fun fromValue(value: Any): LoadConstantPlaceHolder {
            return when (value) {
                is String -> ConstantFromConstantPool(ConstantPoolEntry.StringConstant(value))
                is Boolean -> BooleanConstant(value)
                is Char -> IntegerConstant(value.code)
                is Byte -> IntegerConstant(value.toInt())
                is Short -> IntegerConstant(value.toInt())
                is Int -> IntegerConstant(value)
                is Long -> LongConstant(value)
                is Float -> FloatConstant(value)
                is Double -> DoubleConstant(value)
                else -> throw NotImplementedError(value.javaClass.name)
            }
        }
    }

    class ConstantFromConstantPool(private val constantPoolEntry: ConstantPoolEntry) : LoadConstantPlaceHolder {
        override fun toFinalOpCode(constantPoolBuilder: ConstantPoolBuilder): Operation {
            val index = constantPoolBuilder.getIndexByResolvingOrAdding(constantPoolEntry)
            return if (index <= UByte.MAX_VALUE) {
                Operation.Ldc(index.toUByte())
            } else {
                Operation.Ldc_w(index)
            }
        }
    }

    class BooleanConstant(private val boolean: Boolean) : LoadConstantPlaceHolder {
        override fun toFinalOpCode(constantPoolBuilder: ConstantPoolBuilder): Operation {
            return when (boolean) {
                true -> Operation.Iconst_1()
                false -> Operation.Iconst_0()
            }
        }
    }

    class IntegerConstant(val int: Int) : LoadConstantPlaceHolder {
        override fun toFinalOpCode(constantPoolBuilder: ConstantPoolBuilder): Operation {
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
                    ConstantFromConstantPool(ConstantPoolEntry.IntegerConstant(int)).toFinalOpCode(constantPoolBuilder)
                }
            }
        }
    }

    class LongConstant(val long: Long) : LoadConstantPlaceHolder {
        override fun toFinalOpCode(constantPoolBuilder: ConstantPoolBuilder): Operation {
            return when (long) {
                0L -> Operation.Lconst_0()
                1L -> Operation.Lconst_1()
                else -> Operation.Ldc2_w(ConstantPoolEntry.LongConstant(long))
            }
        }
    }

    class FloatConstant(val float: Float) : LoadConstantPlaceHolder {
        override fun toFinalOpCode(constantPoolBuilder: ConstantPoolBuilder): Operation {
            return when (float) {
                0f -> Operation.Fconst_0()
                1f -> Operation.Fconst_1()
                2f -> Operation.Fconst_2()
                else -> ConstantFromConstantPool(ConstantPoolEntry.FloatConstant(float)).toFinalOpCode(constantPoolBuilder)
            }
        }
    }

    class DoubleConstant(private val double: Double) : LoadConstantPlaceHolder {
        override fun toFinalOpCode(constantPoolBuilder: ConstantPoolBuilder): Operation {
            return when (double) {
                0.0 -> Operation.Dconst_0()
                1.0 -> Operation.Dconst_1()
                else -> Operation.Ldc2_w(ConstantPoolEntry.DoubleConstant(double))
            }
        }
    }
}

