package org.cmjava2023.placeHolders.jumps

import org.cmjava2023.placeHolders.PlaceHolder

class PlaceHolderIfElseIfsElse(val ifAndElseIfs: List<If>, val opCodesInElse: List<PlaceHolder>): PlaceHolder {
    class If(comparisonData: ComparisonData, val opCodesInBlock: List<PlaceHolder>)
}
