package org.cmjava2023.classfilespecification.attributeInfo

import org.cmjava2023.placeHolders.PlaceHolder

data class CodeAttributeInfo(val code:List<PlaceHolder>): AbstractAttributeInfo("Code")