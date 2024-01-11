package org.cmjava2023.classfilespecification

import org.cmjava2023.classfilespecification.constantpool.ConstantInfo

data class ClassfileModel(
        val packageNameWithDelimiterForClassFile: String,
        val constantInfos: List<ConstantInfo>,
        val classClassAccessModifiers: List<ClassAccessModifier>,
        val constantPoolIndex: UShort,
        val numberOfInterfaces: UShort,
        val interfaceDefinitions: List<Byte>,  // Array of own class?
        val numberOfFields: UShort,
        val fieldDefinitions: List<Byte>,  // Array of own class?
        val methodDefinitions: List<MethodInfo>,
        val attributesCount: UShort,
        val attributeDefinitions: List<Byte>
)
