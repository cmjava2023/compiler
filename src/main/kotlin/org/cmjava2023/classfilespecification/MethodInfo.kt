package org.cmjava2023.classfilespecification

import org.cmjava2023.classfilespecification.attributeInfo.AbstractAttributeInfo
import org.cmjava2023.classfilespecification.constantpool.Utf8ConstantPoolEntry

class MethodInfo(
    val accessModifiers: List<MethodAccessModifier>,
    name: String,
    typeDescriptor: String,
    val attributes: List<AbstractAttributeInfo>
) {
    val name = Utf8ConstantPoolEntry(name)
    val typeDescriptor = Utf8ConstantPoolEntry(typeDescriptor)
}