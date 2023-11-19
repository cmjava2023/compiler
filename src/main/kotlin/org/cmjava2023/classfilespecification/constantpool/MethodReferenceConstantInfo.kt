package org.cmjava2023.classfilespecification.constantpool

class MethodReferenceConstantInfo(
    classConstantInfo: ClassConstantInfo,
    nameAndTypeConstantInfo: NameAndTypeConstantInfo
): ReferenceConstantInfo(ConstantInfoTag.CONSTANT_Methodref, classConstantInfo, nameAndTypeConstantInfo)