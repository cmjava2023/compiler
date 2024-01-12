package org.cmjava2023.classfilespecification.constantpool

class MethodTypeDescriptor(
    returnTypeDescriptor: TypeDescriptor,
    parameterTypeDescriptors: List<TypeDescriptor>
) : TypeDescriptor("(" + parameterTypeDescriptors.joinToString(";") + ")" + returnTypeDescriptor.stringRepresentation) {
    constructor(vararg parameterTypes: TypeDescriptor): this(TypeDescriptor("V"), parameterTypes.toList())
}