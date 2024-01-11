package org.cmjava2023.classfilespecification.attributeInfo

import org.cmjava2023.classfilespecification.opCodes.OpCodeOrPlaceHolder

data class CodeAttributeInfo(val code:List<OpCodeOrPlaceHolder>): AbstractAttributeInfo("Code")