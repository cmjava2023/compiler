package org.cmjava2023.classfilespecification.constantpool

import org.cmjava2023.symboltable.BuiltInType

open class TypeDescriptor protected constructor(val stringRepresentation: String) {
    operator fun plus(other: TypeDescriptor): TypeDescriptor = TypeDescriptor(stringRepresentation + other.stringRepresentation)
    override fun toString(): String = stringRepresentation
    fun asArrayOfDimension(numberOfDimensions: Int): TypeDescriptor = TypeDescriptor("[".repeat(numberOfDimensions) + this.stringRepresentation)

    companion object {
        val STRING = createForClassName(ConstantPoolEntry.ClassConstant.STRING_CLASS_CLASSNAME)
        val VOID = TypeDescriptor("V")
        fun createForClassName(className: String): TypeDescriptor = TypeDescriptor("L$className;")

        fun createForBuildInType(type: org.cmjava2023.symboltable.Type): TypeDescriptor {
            return when (type) {
                BuiltInType.INT -> TypeDescriptor("I")
                BuiltInType.LONG -> TypeDescriptor("J")
                BuiltInType.FLOAT -> TypeDescriptor("F")
                BuiltInType.DOUBLE -> TypeDescriptor("D")
                BuiltInType.BOOLEAN -> TypeDescriptor("Z")
                BuiltInType.CHAR -> TypeDescriptor("C")
                BuiltInType.BYTE -> TypeDescriptor("I")
                BuiltInType.SHORT -> TypeDescriptor("I")
                BuiltInType.STRING -> STRING
                else -> throw NotImplementedError(type.javaClass.name + ":" + type.name)
            }
        }
    }
}