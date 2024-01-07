package org.cmjava2023.util

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
        val bytes = pollMultiple(Short.SIZE_BYTES)
        return  ((bytes[0].toInt() and 0xff shl 8) or
                (bytes[1].toInt() and 0xff)).toShort()
    }

    fun dequeue4ByteInt(): Int {
        val bytes = pollMultiple(Int.SIZE_BYTES)
        return (bytes[0].toInt() and 0xff shl 24) or
                (bytes[1].toInt() and 0xff shl 16) or
                (bytes[2].toInt() and 0xff shl 8) or
                (bytes[3].toInt() and 0xff)
    }

    fun dequeue8ByteLong(): Long {
        val bytes = pollMultiple(Long.SIZE_BYTES)
        return (bytes[0].toLong() and 0xff shl 56) or
                (bytes[1].toLong() and 0xff shl 48) or
                (bytes[2].toLong() and 0xff shl 40) or
                (bytes[3].toLong() and 0xff shl 32) or
                (bytes[0].toLong() and 0xff shl 24) or
                (bytes[1].toLong() and 0xff shl 16) or
                (bytes[2].toLong() and 0xff shl 8) or
                (bytes[3].toLong() and 0xff)
    }
}