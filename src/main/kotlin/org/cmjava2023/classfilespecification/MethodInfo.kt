package org.cmjava2023.classfilespecification

import org.cmjava2023.classfilespecification.attributeInfo.AbstractAttributeInfo
import org.cmjava2023.classfilespecification.constantpool.ConstantPoolEntry
import org.cmjava2023.classfilespecification.constantpool.MethodTypeDescriptor

class MethodInfo(
    val accessModifiers: List<MethodAccessModifier>,
    name: String,
    val typeDescriptor: MethodTypeDescriptor,
    val attributes: List<AbstractAttributeInfo>
) {
    val name = ConstantPoolEntry.Utf8Constant(name)
}