package org.cmjava2023.classFileDataToBytes

import org.cmjava2023.classfilespecification.Operation
import org.cmjava2023.placeHolders.*

class PlaceHolderBytesQuery {

    interface ByteOrJumpOffsetPlaceHolder
    class ByteWrapper(val byte: Byte) : ByteOrJumpOffsetPlaceHolder

    class NumberOfBytes private constructor(private val before: Int, private val inside: Int, private val after: Int) {
        constructor(inside: Int, numberOfBytesAfterIf: Int) : this(0, inside, numberOfBytesAfterIf)
        constructor(inside: Int) : this(0, inside, 0)

        fun getNumberOfBytesOfNextBranch(inside: Int): NumberOfBytes {
            return NumberOfBytes(
                before + this.inside,
                inside,
                after - this.inside
            )
        }

        private fun bytesInsideRemaining(currentIndex: Int): Int = inside - currentIndex
        fun resolveJumpOffset(currentIndex: Int, jumpTarget: JumpOffsetPlaceHolder.JumpTarget): Short {
            return when (jumpTarget) {
                JumpOffsetPlaceHolder.JumpTarget.START -> (-(before + currentIndex)).toShort()
                JumpOffsetPlaceHolder.JumpTarget.NEXT -> bytesInsideRemaining(currentIndex).toShort()
                JumpOffsetPlaceHolder.JumpTarget.END -> (after + bytesInsideRemaining(currentIndex)).toShort()
                else -> throw NotImplementedError(jumpTarget.name)
            }
        }
    }
    
    companion object {
        fun fetch(placeHolder: PlaceHolder): List<Byte> {
            return when (placeHolder) {
                is Operation -> placeHolder.toBytes()
                is IfElseIfsElsePlaceHolder -> resolveIfBlock(placeHolder)
                is LoopPlaceHolder -> {
                    val content = placeHolder.placeHolders.resolveExceptJumpOffsetPlaceHolders()
                    content.resolve(NumberOfBytes(content.numberOfBytes()))
                }

                else -> throw NotImplementedError(placeHolder.javaClass.name)
            }
        }

        private fun resolveIfBlock(ifElseIfsElsePlaceHolder: IfElseIfsElsePlaceHolder): List<Byte> {
            val ifContent = ifElseIfsElsePlaceHolder.ifPlaceHolders.resolveExceptJumpOffsetPlaceHolders()
            val elseIfContents = ifElseIfsElsePlaceHolder.elseIfsPlaceHolders.map { it.resolveExceptJumpOffsetPlaceHolders() }
            val elseContent = ifElseIfsElsePlaceHolder.elsePlaceHolders.resolveExceptJumpOffsetPlaceHolders()

            val numberOfBytesInIf = ifContent.numberOfBytes()
            var numberOfBytesInAllElseIfs = 0
            val elseIfContentToNumberOfBytesMap = elseIfContents.associateWith {
                val n = it.numberOfBytes()
                numberOfBytesInAllElseIfs += n
                n
            }
            val numberOfBytesInElse = elseContent.numberOfBytes()

            var bytesPosition = NumberOfBytes(numberOfBytesInIf, numberOfBytesInAllElseIfs + numberOfBytesInElse)

            val result = mutableListOf<Byte>()
            result += ifContent.resolve(bytesPosition)

            for ((elseIfContent, numberOfBytes) in elseIfContentToNumberOfBytesMap) {
                bytesPosition = bytesPosition.getNumberOfBytesOfNextBranch(numberOfBytes)
                result += elseIfContent.resolve(bytesPosition)
            }

            bytesPosition = bytesPosition.getNumberOfBytesOfNextBranch(numberOfBytesInElse)
            result += elseContent.resolve(bytesPosition)

            return result
        }

        private fun List<PlaceHolder>.resolveExceptJumpOffsetPlaceHolders(): List<ByteOrJumpOffsetPlaceHolder> {
            return this.flatMap {
                if (it is JumpOffsetPlaceHolder) {
                    listOf<ByteOrJumpOffsetPlaceHolder>(it)
                } else {
                    fetch(it).map { b -> ByteWrapper(b) }
                }
            }
        }

        private fun List<ByteOrJumpOffsetPlaceHolder>.numberOfBytes(): Int {
            val numberOfJumpPlaceHolders = this.count { it is JumpOffsetPlaceHolder }
            val sizeOfByteWrappers = this.size - numberOfJumpPlaceHolders
            return (numberOfJumpPlaceHolders * JumpOffsetPlaceHolder.SIZE_IN_BYTES) + sizeOfByteWrappers
        }

        private fun List<ByteOrJumpOffsetPlaceHolder>.resolve(numberOfBytes: NumberOfBytes): List<Byte> {
            val result = mutableListOf<Byte>()
            var currentIndex = 0
            for (byteOrJumpOffsetPlaceHolder in this) {
                when (byteOrJumpOffsetPlaceHolder) {
                    is JumpOffsetPlaceHolder -> {
                        result += byteOrJumpOffsetPlaceHolder.toFinalOpCode(
                            numberOfBytes.resolveJumpOffset(
                                currentIndex,
                                byteOrJumpOffsetPlaceHolder.jumpTarget
                            )
                        ).toBytes()
                        currentIndex += JumpOffsetPlaceHolder.SIZE_IN_BYTES
                    }

                    is ByteWrapper -> {
                        result += byteOrJumpOffsetPlaceHolder.byte
                        currentIndex += 1
                    }
                }
            }
            return result
        }
    }
}