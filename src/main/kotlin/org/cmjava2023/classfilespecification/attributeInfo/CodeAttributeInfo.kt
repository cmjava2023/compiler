package org.cmjava2023.classfilespecification.attributeInfo

import org.cmjava2023.classfilespecification.codeParts.FunctionCallCodePart
import org.cmjava2023.classfilespecification.constantpool.Utf8ConstantInfo

data class CodeAttributeInfo(val functionCallCodeParts:List<FunctionCallCodePart>): AbstractAttributeInfo(Utf8ConstantInfo("Code"))