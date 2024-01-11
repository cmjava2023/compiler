package org.cmjava2023.classfilespecification.opCodes

interface OpCodeOrPlaceHolder {
    class NotAllFinalOpCodesError(opCodeOrPlaceHolders: List<OpCodeOrPlaceHolder>) :
        Error("not final opCodes:" + opCodeOrPlaceHolders.filter { it !is OpCode }.joinToString { it.javaClass.name })

    companion object {
        fun List<OpCodeOrPlaceHolder>.assureAllAreFinalOpCodes(): List<OpCode> {
            return if (this.all { it is OpCode }) {
                this.filterIsInstance<OpCode>()
            } else {
                throw NotAllFinalOpCodesError(this)
            }
        }
    }
}