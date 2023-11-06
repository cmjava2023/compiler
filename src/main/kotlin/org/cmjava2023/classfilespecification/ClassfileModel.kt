package org.cmjava2023.classfilespecification

data class ClassfileModel(
        val constantPool: List<ConstantInfo>,
        val classClassAccessModifiers: List<ClassAccessModifier>,
        val constantPoolIndex: Short,
        val numberOfInterfaces: Short,
        val interfaceDefinitions: List<Byte>,  // Array of own class?
        val numberOfFields: Short,
        val fieldDefinitions: List<Byte>,  // Array of own class?
        val methodDefinitions: List<MethodInfo>,
        val attributesCount: Short,
        val attributeDefinitions: List<Byte>
)
