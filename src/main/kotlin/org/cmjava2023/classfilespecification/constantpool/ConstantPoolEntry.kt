package org.cmjava2023.classfilespecification.constantpool

import org.cmjava2023.symboltable.BuiltInType
import org.cmjava2023.util.ByteListUtil.Companion.toByteList

abstract class ConstantPoolEntry(val tagByte: Byte) {    
    class Utf8Constant(content: String): ConstantPoolEntry(0x01) {
        private val utf8encodedContent: String = String(content.toByteArray(), Charsets.UTF_8)
        val stringLengthInBytes: List<Byte> = utf8encodedContent.length.toUShort().toByteList()
        val contentInBytes: List<Byte> = utf8encodedContent.toByteArray().toList()
    }
    class IntegerConstant(val value: Int) : ConstantPoolEntry(0x03)
    class FloatConstant(val value: Float) : ConstantPoolEntry(0x04)
    class LongConstant(val value: Long) : ConstantPoolEntry(0x05)
    class DoubleConstant(val value: Double): ConstantPoolEntry(0x06)
    class ClassConstant(name: TypeDescriptor): ConstantPoolEntry(0X07) {
        val name = Utf8Constant(name.stringRepresentation)
        companion object {
            val STRING = ClassConstant(TypeDescriptor.STRING)
            val OBJECT = ClassConstant(TypeDescriptor.OBJECT)
            val STRING_BUILDER = ClassConstant(TypeDescriptor.createForClassName("java/lang/StringBuilder"))  
            val SYSTEM = ClassConstant(TypeDescriptor.createForClassName("java/lang/System"))
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
            val SYSTEM_IN = FieldReferenceConstant(ClassConstant.SYSTEM, "in", TypeDescriptor.createForClassName("java/io/InputStream"))
            val SYSTEM_OUT = FieldReferenceConstant(ClassConstant.SYSTEM, "out", TypeDescriptor.createForClassName("java/io/PrintStream"))
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

            val STRING_BUILDER_TO_STRING =  MethodReferenceConstant(ClassConstant.STRING_BUILDER, STRING_BUILDER_METHOD_NAME_TO_STRING, MethodTypeDescriptor.voidWithParameters(TypeDescriptor.STRING))
            val SYSTEM_IN_READ = MethodReferenceConstant(ClassConstant.SYSTEM, "read", MethodTypeDescriptor.voidWithParameters().returning(TypeDescriptor.forBuildInType(BuiltInType.INT)))
            fun stringBuilderAppendFor(type: TypeDescriptor) =
                MethodReferenceConstant(ClassConstant.STRING_BUILDER, STRING_BUILDER_METHOD_NAME_APPEND, MethodTypeDescriptor.voidWithParameters(type))
            fun stringBuilderToStringFor(type: org.cmjava2023.symboltable.Type) =
                MethodReferenceConstant(ClassConstant.STRING_BUILDER, STRING_BUILDER_METHOD_NAME_TO_STRING, MethodTypeDescriptor.voidWithParameters(TypeDescriptor.forBuildInType(type)).returning(TypeDescriptor.STRING))
            fun defaultConstructorOf(typeDescriptor: TypeDescriptor) = MethodReferenceConstant(ClassConstant(typeDescriptor), CONSTRUCTOR_NAME, MethodTypeDescriptor.voidWithParameters())
            fun defaultConstructorOf(classConstant: ClassConstant) = MethodReferenceConstant(classConstant, CONSTRUCTOR_NAME, MethodTypeDescriptor.voidWithParameters())
        }
    }
    
    abstract class ReferenceConstant(
        tagByte: Byte,
        val classConstantInfo: ClassConstant,
        val nameAndTypeConstantInfo: NameAndTypeConstant
    ): ConstantPoolEntry(tagByte)
}