package org.cmjava2023

import org.cmjava2023.classfilespecification.ConstantInfo

data class ClassfileModel(
        val constantPool: List<ConstantInfo>,
        val classAccessModifiers: Short,
        val constantPoolIndex: Short,
        val superClassIndexInConstantPool: Short,
        val numberOfInterfaces: Short,
        val interfaceDefinitions: List<Byte>,  // Array of own class?
        val numberOfFields: Short,
        val fieldDefinitions: List<Byte>,  // Array of own class?
        val numberOfMethods: Short,
        val methodDefinitions: List<Byte>,  // Array of own class?
        val attributesCount: Short,
        val attributeDefinitions: List<Byte>
)
