package org.cmjava2023.classfilespecification.codeParts

import org.cmjava2023.classfilespecification.constantpool.FieldReferenceConstantInfo
import org.cmjava2023.classfilespecification.constantpool.MethodReferenceConstantInfo
import org.cmjava2023.classfilespecification.constantpool.StringConstantInfo

data class FunctionCallCodePart(
    val fieldReferenceConstantInfo: FieldReferenceConstantInfo,
    val methodReferenceConstantInfo: MethodReferenceConstantInfo,
    val arguments: List<StringConstantInfo>
)