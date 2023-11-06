package org.cmjava2023.classfilespecification

class MethodInfo(
        val accessModifiers: List<MethodAccessModifier>,
        val nameConstant: Utf8ConstantInfo,
        val methodType: Utf8ConstantInfo
)