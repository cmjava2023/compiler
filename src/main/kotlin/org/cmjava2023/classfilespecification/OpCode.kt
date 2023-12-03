package org.cmjava2023.classfilespecification

import org.cmjava2023.util.ByteListUtil.Companion.add

abstract class OpCode(val opCodeValue:UByte) {
    fun toBytes(): List<Byte> {
        val result = mutableListOf<Byte>()
        result.add(opCodeValue)
        result.addAll(getBytesAfterOpCode())
        return result
    }

    protected abstract fun getBytesAfterOpCode(): List<Byte>

    open class OpCodeWithIndices(opCodeValue: UByte, private vararg val indices: Short): OpCode(opCodeValue) {
        override fun getBytesAfterOpCode(): List<Byte> {
            val result = mutableListOf<Byte>()
            indices.forEach { result.add(it) }
            return result
        }
    }

    class ReturnVoid: OpCodeWithIndices(0xb1u)
    class Aload_0: OpCodeWithIndices(0x2au)
    class GetStatic(referenceIndex: Short): OpCodeWithIndices(0xb2u, referenceIndex)
    class InvokeVirtual(referenceIndex: Short): OpCodeWithIndices(0xb6u, referenceIndex)
    class InvokeSpecial(referenceIndex: Short): OpCodeWithIndices(0xb7u, referenceIndex)
    class LoaDConstant(private val constantIndex: UByte): OpCode(0x12u) {
        override fun getBytesAfterOpCode(): List<Byte> {
            val result = mutableListOf<Byte>()
            result.add(constantIndex)
            return result
        }

    }
}