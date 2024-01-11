package org.cmjava2023.classfilespecification.constantpool

import org.cmjava2023.symboltable.BuiltInType

class ConstantPoolConstants {
    class StringBuilder {
        companion object {
            const val QUALIFIED_CLASSNAME = "java/lang/StringBuilder"
            
        }
        class Method {
            companion object {
                const val APPEND = "append"
                const val TO_STRING = "toString"
            }
        }
    }

    companion object {
        const val CONSTRUCTOR_NAME = "<init>"
        const val QUALIFIED_CLASSNAME_OF_STRING = "java/lang/String"
        const val QUALIFIED_CLASSNAME_OF_PRINT_STREAM = "java/io/PrintStream"
    }
    
    class Type {
        companion object {            
            fun createVoidMethod(vararg parameterTypes: String): String {
                return createMethodReturning1with2toNAsParameterTypes("V", *parameterTypes)
            }
            
            fun createMethodReturning1with2toNAsParameterTypes(returnType: String, vararg parameterTypes: String): String {
                return "(" + parameterTypes.joinToString(";") + ")" + returnType
            }
            fun createForQualifiedClassName(className: String): String {
                return "L$className;"
            }

            fun asArrayOfDimension(typeDescriptor: String, numberOfDimensions: Int): String {
                return "[".repeat(numberOfDimensions) + typeDescriptor
            }
            
            fun map(type: org.cmjava2023.symboltable.Type): String {
                return when (type) {
                    BuiltInType.INT -> "I"
                    BuiltInType.LONG -> "J"
                    BuiltInType.FLOAT -> "F"
                    BuiltInType.DOUBLE -> "D"
                    BuiltInType.BOOLEAN -> "Z"
                    BuiltInType.CHAR -> "C"
                    BuiltInType.BYTE -> "I"
                    BuiltInType.SHORT ->"I"
                    BuiltInType.STRING -> QUALIFIED_CLASSNAME_OF_STRING
                    else -> throw NotImplementedError(type.name)
                }
            }
        }
    }
}