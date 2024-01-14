package org.cmjava2023.parsedClassFileDataToBytes

import org.cmjava2023.classfilespecification.Operation
import org.cmjava2023.placeHolders.LoadConstantPlaceHolder
import org.cmjava2023.placeHolders.LocalVariableIndexPlaceHolder
import org.cmjava2023.placeHolders.PlaceHolder
import org.cmjava2023.placeHolders.jumps.IfElseIfsElsePlaceHolder
import org.cmjava2023.placeHolders.jumps.JumpOffsetPlaceHolder
import org.cmjava2023.placeHolders.jumps.LoopPlaceHolder

class PlaceHolderBytesQuery(
    private val constantPoolBuilder: ConstantPoolBuilder,
    private val localVariableIndexAssigner: LocalVariableIndexAssigner
) {
    fun fetch(placeHolder: PlaceHolder): List<Byte> {
        return when (placeHolder) {
            is Operation -> placeHolder.toBytes(constantPoolBuilder)
            is LoadConstantPlaceHolder -> placeHolder.toFinalOpCode(constantPoolBuilder).toBytes(constantPoolBuilder)
            is LocalVariableIndexPlaceHolder -> placeHolder.toFinalOpCode(localVariableIndexAssigner).toBytes(constantPoolBuilder)
            is IfElseIfsElsePlaceHolder -> resolveIfBlock(placeHolder)
            is LoopPlaceHolder -> {
                val content = placeHolder.placeHolders.resolveExceptJumpOffsetPlaceHolders()
                content.resolve(NumberOfBytes(content.numberOfBytes()))
            }
            else -> throw NotImplementedError(placeHolder.javaClass.name)
        }
    }

    class NumberOfBytes private constructor (private val before: Int, private val inside: Int, private val after: Int) {
        constructor(inside: Int, numberOfBytesAfterIf: Int): this(0, inside, numberOfBytesAfterIf)
        constructor(inside: Int): this(0, inside, 0)
        fun createNext(inside: Int): NumberOfBytes {
            return NumberOfBytes(
                before + this.inside,
                inside,
                after - this.inside
            )
        }
        
        private fun bytesLeftInside(currentIndex: Int): Int = inside - currentIndex
        
        fun resolveJumpOffset(currentIndex: Int, jumpTargetIfFalse: JumpOffsetPlaceHolder.JumpTargetIfFalse): Short {
            return when(jumpTargetIfFalse) {
                JumpOffsetPlaceHolder.JumpTargetIfFalse.START -> (-(before + currentIndex)).toShort()
                JumpOffsetPlaceHolder.JumpTargetIfFalse.NEXT -> bytesLeftInside(currentIndex).toShort()
                JumpOffsetPlaceHolder.JumpTargetIfFalse.END -> (after + bytesLeftInside(currentIndex)).toShort()
                else -> throw NotImplementedError(jumpTargetIfFalse.name)
            }
        }
    }
    
    private fun resolveIfBlock(
        ifElseIfsElsePlaceHolder: IfElseIfsElsePlaceHolder
    ): List<Byte> {
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
            bytesPosition = bytesPosition.createNext(numberOfBytes)
            result += elseIfContent.resolve(bytesPosition)
        }
        
        bytesPosition = bytesPosition.createNext(numberOfBytesInElse)
        result += elseContent.resolve(bytesPosition)
        
        return result
    }
    
    interface ByteOrJumpOffsetPlaceHolder
    class ByteWrapper(val byte: Byte): ByteOrJumpOffsetPlaceHolder

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
        return (this.count { it is JumpOffsetPlaceHolder } * JumpOffsetPlaceHolder.SIZE_IN_BYTES) + this.count { it is ByteWrapper }
    }

    private fun List<ByteOrJumpOffsetPlaceHolder>.resolve(numberOfBytes: NumberOfBytes): List<Byte> {
        val result = mutableListOf<Byte>()
        var currentIndex = 0
        for(byteOrJumpOffsetPlaceHolder in this) {
            when(byteOrJumpOffsetPlaceHolder) {
                is JumpOffsetPlaceHolder -> {
                    result += byteOrJumpOffsetPlaceHolder.toFinalOpCode(numberOfBytes.resolveJumpOffset(currentIndex, byteOrJumpOffsetPlaceHolder.jumpTargetIfFalse)).toBytes(constantPoolBuilder)
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