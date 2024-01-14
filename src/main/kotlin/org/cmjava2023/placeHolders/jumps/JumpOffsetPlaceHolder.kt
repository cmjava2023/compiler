package org.cmjava2023.placeHolders.jumps

import org.cmjava2023.classfilespecification.Operation
import org.cmjava2023.parsedClassFileDataToBytes.PlaceHolderBytesQuery
import org.cmjava2023.placeHolders.PlaceHolder


abstract class JumpOffsetPlaceHolder : PlaceHolder, PlaceHolderBytesQuery.ByteOrJumpOffsetPlaceHolder {
    abstract fun toFinalOpCode(jumpOffset: Short): Operation
    var jumpTargetIfFalse: JumpTargetIfFalse = JumpTargetIfFalse.NONE
    companion object {
        const val SIZE_IN_BYTES = 3
    }
    enum class JumpTargetIfFalse {
        START,
        NEXT,
        END,
        NONE
    }
    class Jump() : JumpOffsetPlaceHolder() {
        constructor(jumpTargetIfFalse: JumpTargetIfFalse): this() {
            this.jumpTargetIfFalse = jumpTargetIfFalse
        }
        override fun toFinalOpCode(jumpOffset: Short): Operation {
            return Operation.Goto(jumpOffset)
        }
    }

    class JumpIfEqualToZeroWhichMeansFalse : JumpOffsetPlaceHolder() {
        override fun toFinalOpCode(jumpOffset: Short): Operation {
            return Operation.Ifeq(jumpOffset)
        }
    }
    class JumpIfNotEqualToZeroWhichMeansTrue : JumpOffsetPlaceHolder() {
        override fun toFinalOpCode(jumpOffset: Short): Operation {
            return Operation.Ifne(jumpOffset)
        }
    }
    class JumpIfLessThanZero : JumpOffsetPlaceHolder() {
        override fun toFinalOpCode(jumpOffset: Short): Operation {
            return Operation.Iflt(jumpOffset)
        }
    }
    class JumpIfLessThanOrEqualToZero : JumpOffsetPlaceHolder() {
        override fun toFinalOpCode(jumpOffset: Short): Operation {
            return Operation.Ifle(jumpOffset)
        }
    }
    class JumpIfGreaterThanZero : JumpOffsetPlaceHolder() {
        override fun toFinalOpCode(jumpOffset: Short): Operation {
            return Operation.Ifgt(jumpOffset)
        }
    }
    class JumpIfGreaterThanOrEqualToZero : JumpOffsetPlaceHolder() {
        override fun toFinalOpCode(jumpOffset: Short): Operation {
            return Operation.Ifge(jumpOffset)
        }
    }
    class JumpIfIntegersAreEqual : JumpOffsetPlaceHolder() {
        override fun toFinalOpCode(jumpOffset: Short): Operation {
            return Operation.If_icmpeq(jumpOffset)
        }
    }
    class JumpIfIntegersAreNotEqual : JumpOffsetPlaceHolder() {
        override fun toFinalOpCode(jumpOffset: Short): Operation {
            return Operation.If_icmpne(jumpOffset)
        }
    }
    class JumpIfIntegerALessThanB : JumpOffsetPlaceHolder() {
        override fun toFinalOpCode(jumpOffset: Short): Operation {
            return Operation.If_icmplt(jumpOffset)
        }
    }
    class JumpIfIntegerALessThanOrEqualToB : JumpOffsetPlaceHolder() {
        override fun toFinalOpCode(jumpOffset: Short): Operation {
            return Operation.If_icmple(jumpOffset)
        }
    }
    class JumpIfIntegerAGreaterThanB : JumpOffsetPlaceHolder() {
        override fun toFinalOpCode(jumpOffset: Short): Operation {
            return Operation.If_icmpgt(jumpOffset)
        }
    }
    class JumpIfIntegerAGreaterThanOrEqualToB : JumpOffsetPlaceHolder() {
        override fun toFinalOpCode(jumpOffset: Short): Operation {
            return Operation.If_icmpge(jumpOffset)
        }
    }
}