package org.cmjava2023.classfilespecification

data class ClassfileData(
    val packageNameWithDelimiterForClassFile: String,
    val classAccessModifiers: List<ClassAccessModifier>,
    val methodDefinitions: List<MethodInfo>
)
