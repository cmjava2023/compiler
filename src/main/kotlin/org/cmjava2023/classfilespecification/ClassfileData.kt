package org.cmjava2023.classfilespecification

import org.cmjava2023.classfilespecification.constantpool.ConstantPoolEntry

data class ClassfileData(
    val packageNameWithDelimiterForClassFile: String,
    val constantPoolEntries: List<ConstantPoolEntry>,
    val classAccessModifiers: List<ClassAccessModifier>,
    val methodDefinitions: List<MethodInfo>
)
