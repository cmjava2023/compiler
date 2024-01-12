package org.cmjava2023.classfilespecification.constantpool

import org.cmjava2023.symboltable.BuiltInType

open class TypeDescriptor(val stringRepresentation: String) {
    operator fun plus(other: TypeDescriptor): TypeDescriptor = TypeDescriptor(stringRepresentation + other.stringRepresentation)
    override fun toString(): String = stringRepresentation
    fun asArrayOfDimension(numberOfDimensions: Int): TypeDescriptor = TypeDescriptor("[".repeat(numberOfDimensions) + this.stringRepresentation)

    companion object {
        val STRING = createForClassName("java/lang/String")
        val PRINT_STREAM = createForClassName("java/io/PrintStream")        
        fun createForClassName(className: TypeDescriptor): TypeDescriptor = TypeDescriptor("L$className;")

        fun createFromSymbolTableType(type: org.cmjava2023.symboltable.Type): TypeDescriptor {
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
                else -> throw NotImplementedError(type.name)
            }
        }
    }
}