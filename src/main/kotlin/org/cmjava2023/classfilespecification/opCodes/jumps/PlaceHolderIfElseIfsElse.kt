package org.cmjava2023.classfilespecification.opCodes.jumps

import org.cmjava2023.classfilespecification.opCodes.OpCodeOrPlaceHolder
import org.cmjava2023.classfilespecification.opCodes.PlaceHolder

class PlaceHolderIfElseIfsElse(val ifAndElseIfs: List<If>, val opCodesInElse: List<OpCodeOrPlaceHolder>): PlaceHolder {
    class If(comparisonData:ComparisonData, val opCodesInBlock: List<OpCodeOrPlaceHolder>)
}
