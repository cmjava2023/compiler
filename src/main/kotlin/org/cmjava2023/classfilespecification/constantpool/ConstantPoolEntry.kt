package org.cmjava2023.classfilespecification.constantpool

import org.cmjava2023.symboltable.BuiltInType
import org.cmjava2023.util.ByteListUtil.Companion.toByteList

abstract class ConstantPoolEntry(val tagByte: Byte) {    
    class Utf8Constant(content: String): ConstantPoolEntry(0x01) {
        private val utf8encodedContent: String = String(content.toByteArray(), Charsets.UTF_8)
        fun stringLengthInBytes(): List<Byte> = utf8encodedContent.length.toUShort().toByteList()
        fun contentInBytes(): List<Byte> = utf8encodedContent.toByteArray().toList()
    }
    class IntegerConstant(val value: Int) : ConstantPoolEntry(0x03)
    class FloatConstant(val value: Float) : ConstantPoolEntry(0x04)
    class LongConstant(val value: Long) : ConstantPoolEntry(0x05)
    class DoubleConstant(val value: Double): ConstantPoolEntry(0x06) {}
    class ClassConstant(name: String): ConstantPoolEntry(0X07) {
        val name = Utf8Constant(name)
        companion object {
            const val STRING_CLASS_CLASSNAME = "java/lang/String"
            const val OBJECT_CLASS_CLASSNAME = "java/lang/Object"
            const val STRING_BUILDER_CLASSNAME = "java/lang/StringBuilder"
            const val PRINT_STREAM_CLASSNAME = "java/io/PrintStream"
            const val INPUT_STREAM_CLASSNAME = "java/io/InputStream"
            val STRING = ClassConstant(STRING_CLASS_CLASSNAME)
            val OBJECT = ClassConstant(OBJECT_CLASS_CLASSNAME)
            val STRING_BUILDER = ClassConstant(STRING_BUILDER_CLASSNAME)
            val PRINT_STREAM = ClassConstant(PRINT_STREAM_CLASSNAME)
            val INPUT_STREAM = ClassConstant(INPUT_STREAM_CLASSNAME)
            val SYSTEM = ClassConstant("java/lang/System")
        }
    }
    class StringConstant(value: String): ConstantPoolEntry(0X08) {
        val value = Utf8Constant(value)
    }
    class NameAndTypeConstant(name: String, type: TypeDescriptor): ConstantPoolEntry(0X0C) {
        val name = Utf8Constant(name)
        val type = Utf8Constant(type.stringRepresentation)
    }
    
    class FieldReferenceConstant(
        classConstant: ClassConstant,
        fieldName: String,
        fieldType: TypeDescriptor
    ): ReferenceConstant(0X09, classConstant, NameAndTypeConstant(fieldName, fieldType)) {
        companion object {
            val SYSTEM_IN = FieldReferenceConstant(ClassConstant.SYSTEM, "in", TypeDescriptor.createForClassName(ClassConstant.INPUT_STREAM_CLASSNAME))
            val SYSTEM_OUT = FieldReferenceConstant(ClassConstant.SYSTEM, "out", TypeDescriptor.createForClassName(ClassConstant.PRINT_STREAM_CLASSNAME))
        }
    }
    
    class MethodReferenceConstant(
        classConstant: ClassConstant,
        methodName: String,
        methodType: MethodTypeDescriptor
    ): ReferenceConstant(0X0A, classConstant, NameAndTypeConstant(methodName, methodType)) {
        companion object {
            private const val CONSTRUCTOR_NAME = "<init>"
            private const val STRING_BUILDER_METHOD_NAME_APPEND = "append"
            private const val STRING_BUILDER_METHOD_NAME_TO_STRING = "toString"

            val STRING_BUILDER_TO_STRING =  MethodReferenceConstant(ClassConstant.STRING_BUILDER, STRING_BUILDER_METHOD_NAME_TO_STRING, MethodTypeDescriptor.voidWithParameters().returning(TypeDescriptor.STRING))
            val INPUT_STREAM_READ = MethodReferenceConstant(ClassConstant.INPUT_STREAM, "read", MethodTypeDescriptor.voidWithParameters().returning(TypeDescriptor.createForBuildInType(BuiltInType.INT)))
            fun printStreamPrintlnFor(parameterTypeDescriptor: TypeDescriptor) = MethodReferenceConstant(ClassConstant.PRINT_STREAM, "println", MethodTypeDescriptor.voidWithParameters(parameterTypeDescriptor))
            fun stringBuilderAppendFor(type: TypeDescriptor) =
                MethodReferenceConstant(ClassConstant.STRING_BUILDER, STRING_BUILDER_METHOD_NAME_APPEND, MethodTypeDescriptor.voidWithParameters(type).returning(TypeDescriptor.createForClassName(ClassConstant.STRING_BUILDER_CLASSNAME)))
            fun defaultConstructorOf(className: String) = MethodReferenceConstant(ClassConstant(className), CONSTRUCTOR_NAME, MethodTypeDescriptor.voidWithParameters())
            fun defaultConstructorOf(classConstant: ClassConstant) = MethodReferenceConstant(classConstant, CONSTRUCTOR_NAME, MethodTypeDescriptor.voidWithParameters())
        }
    }
    
    abstract class ReferenceConstant(
        tagByte: Byte,
        val classConstantInfo: ClassConstant,
        val nameAndTypeConstantInfo: NameAndTypeConstant
    ): ConstantPoolEntry(tagByte)
}