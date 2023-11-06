package org.cmjava2023.classfilespecification

import org.cmjava2023.classfilespecification.constantpool.Utf8ConstantInfo

class MethodInfo(
    val accessModifiers: List<MethodAccessModifier>,
    val nameConstant: Utf8ConstantInfo,
    val methodType: Utf8ConstantInfo
)