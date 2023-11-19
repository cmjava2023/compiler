package org.cmjava2023.classfilespecification.constantpool

class FieldReferenceConstantInfo(
    classConstantInfo: ClassConstantInfo,
    nameAndTypeConstantInfo: NameAndTypeConstantInfo
): ReferenceConstantInfo(ConstantInfoTag.CONSTANT_Fieldref, classConstantInfo, nameAndTypeConstantInfo)