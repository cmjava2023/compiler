package org.cmjava2023.classfilespecification.constantpool

class NameAndTypeConstantInfo(val name: Utf8ConstantInfo, val type: Utf8ConstantInfo): ConstantInfo(ConstantInfoTag.CONSTANT_NameAndType)