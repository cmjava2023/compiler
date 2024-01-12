package org.cmjava2023.classfilespecification.constantpool

import org.cmjava2023.util.ByteListUtil.Companion.toByteList

abstract class ConstantPoolEntry(val tagByte: Byte) {    
    class Utf8Constant(val content: String): ConstantPoolEntry(0x01) {
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
            val STRING_BUILDER = ClassConstant(TypeDescriptor("java/lang/StringBuilder"))  
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
        className: TypeDescriptor,
        fieldName: String,
        fieldType: TypeDescriptor
    ): ReferenceConstantPoolEntry(0X09, ClassConstant(className), NameAndTypeConstant(fieldName, fieldType))
    
    class MethodReferenceConstant(
        classConstant: ClassConstant,
        methodName: String,
        methodType: MethodTypeDescriptor
    ): ReferenceConstantPoolEntry(0X0A, classConstant, NameAndTypeConstant(methodName, methodType)) {
        
        constructor(
            className: TypeDescriptor,
            methodName: String,
            methodType: MethodTypeDescriptor
        ) : this(ClassConstant(className), methodName, methodType)

        companion object {
            private const val CONSTRUCTOR_NAME = "<init>"
            private const val STRING_BUILDER_APPEND = "append"
            private const val STRING_BUILDER_TO_STRING = "toString"

            val a =  MethodReferenceConstant(ClassConstant.STRING_BUILDER, STRING_BUILDER_TO_STRING, MethodTypeDescriptor(TypeDescriptor.STRING))
            val STRING_BUILDER_APPEND_STRING = stringBuilderAppendForInstanceOf(TypeDescriptor.STRING)
            fun stringBuilderAppendForInstanceOf(typeDescriptor: TypeDescriptor) = MethodReferenceConstant(ClassConstant.STRING_BUILDER, STRING_BUILDER_APPEND, MethodTypeDescriptor(TypeDescriptor.createForClassName(typeDescriptor)))
            fun stringBuilderToStringFor(type: org.cmjava2023.symboltable.Type) =
                MethodReferenceConstant(ClassConstant.STRING_BUILDER, STRING_BUILDER_TO_STRING, MethodTypeDescriptor(TypeDescriptor.STRING, TypeDescriptor.createFromSymbolTableType(type)))
            fun defaultConstructorOf(typeDescriptor: TypeDescriptor) = MethodReferenceConstant(typeDescriptor, CONSTRUCTOR_NAME, MethodTypeDescriptor())
            fun defaultConstructorOf(classConstant: ClassConstant) = MethodReferenceConstant(classConstant, CONSTRUCTOR_NAME, MethodTypeDescriptor())
        }
    }
    
    abstract class ReferenceConstantPoolEntry(
        tagByte: Byte,
        val classConstantInfo: ClassConstant,
        val nameAndTypeConstantInfo: NameAndTypeConstant
    ): ConstantPoolEntry(tagByte)
}