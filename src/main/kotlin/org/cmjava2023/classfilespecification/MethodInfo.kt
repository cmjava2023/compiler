package org.cmjava2023.classfilespecification

import org.cmjava2023.classfilespecification.attributeInfo.AbstractAttributeInfo
import org.cmjava2023.classfilespecification.attributeInfo.CodeAttributeInfo
import org.cmjava2023.classfilespecification.constantpool.ConstantPoolEntry
import org.cmjava2023.classfilespecification.constantpool.MethodTypeDescriptor

class MethodInfo(
    val accessModifiers: List<MethodAccessModifier>,
    name: String,
    val typeDescriptor: MethodTypeDescriptor,
    val attributes: List<AbstractAttributeInfo>
) {
    val name = ConstantPoolEntry.Utf8Constant(name)

    companion object {
        val DEFAULT_CONSTRUCTOR = MethodInfo(
            listOf(MethodAccessModifier.ACC_PUBLIC),
            "<init>",
            MethodTypeDescriptor.voidWithParameters(),
            listOf(
                CodeAttributeInfo(
                    listOf(
                        Operation.Aload_0(),
                        Operation.Invokespecial(ConstantPoolEntry.MethodReferenceConstant.defaultConstructorOf(ConstantPoolEntry.ClassConstant.OBJECT_CLASS_CLASSNAME)),
                        Operation.Return()
                    )
                )
            )
        )
    }
}