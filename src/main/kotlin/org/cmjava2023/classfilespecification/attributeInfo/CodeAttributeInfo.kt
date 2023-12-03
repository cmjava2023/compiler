package org.cmjava2023.classfilespecification.attributeInfo

import org.cmjava2023.classfilespecification.codeParts.FunctionCallCodePart

data class CodeAttributeInfo(val functionCallCodeParts:List<FunctionCallCodePart>): AbstractAttributeInfo("Code")