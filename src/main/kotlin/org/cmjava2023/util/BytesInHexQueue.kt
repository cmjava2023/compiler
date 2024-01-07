package org.cmjava2023.util

import java.nio.ByteBuffer
import java.util.*

class BytesInHexQueue(bytes: ByteArray): LinkedList<Byte>(bytes.toList()) {
    
    fun dequeueHexBytes(amountOfBytes: Int): String {
        return HexFormat.of().formatHex(pollMultiple(amountOfBytes).toByteArray()).uppercase()
    }
    
    fun getSubQueue(amountOfBytes: Int): BytesInHexQueue {
        val result = BytesInHexQueue(ByteArray(0))
        for (i in 0 until amountOfBytes) {
            result.add(poll())
        }
        return result
    }
    
    private fun pollMultiple(amountOfBytes: Int): List<Byte> {
        val result = mutableListOf<Byte>()
        for (i in 0 until amountOfBytes) {
            result.add(poll())
        }
        return result
    }

    fun dequeueUByte(): UByte {
        return dequeueHexBytes(UByte.SIZE_BYTES).toUByte(16)
    }

    fun dequeueByte(): Byte {
        return poll()
    }

    fun dequeue2ByteUShort(): UShort {
        return dequeueHexBytes(UShort.SIZE_BYTES).toUShort(16)
    }

    fun dequeue2ByteShort(): Short {
        return ByteBuffer.wrap(pollMultiple(Short.SIZE_BYTES).toByteArray()).getShort()
    }

    fun dequeue4ByteInt(): Int {
        return ByteBuffer.wrap(pollMultiple(Int.SIZE_BYTES).toByteArray()).getInt()
    }

    fun dequeue8ByteLong(): Long {
        return ByteBuffer.wrap(pollMultiple(Int.SIZE_BYTES).toByteArray()).getLong()
    }
}