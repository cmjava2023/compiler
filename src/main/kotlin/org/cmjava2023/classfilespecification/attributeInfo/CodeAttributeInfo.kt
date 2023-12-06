package org.cmjava2023.classfilespecification.attributeInfo

import org.cmjava2023.classfilespecification.OpCode

data class CodeAttributeInfo(val code:List<OpCode>): AbstractAttributeInfo("Code")