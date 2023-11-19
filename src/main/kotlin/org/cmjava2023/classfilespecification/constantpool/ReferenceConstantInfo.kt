package org.cmjava2023.classfilespecification.constantpool

abstract class ReferenceConstantInfo(
    constantInfoTag: ConstantInfoTag,
    val classConstantInfo: ClassConstantInfo,
    val nameAndTypeConstantInfo: NameAndTypeConstantInfo
): ConstantInfo(constantInfoTag)