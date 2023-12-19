package org.cmjava2023.util

import java.util.*

class BytesInHexQueue: LinkedList<String>() {
    fun dequeueHexBytes(amountOfBytes: Int): String {
        val result = StringBuilder()
        for (i in 0 until amountOfBytes) {
            result.append(poll())
        }
        return result.toString()
    }
    
    fun getSubQueue(amountOfBytes: Int): BytesInHexQueue {
        val result = BytesInHexQueue()
        for (i in 0 until amountOfBytes) {
            result.add(poll())
        }
        return result
    }

    fun dequeueUByte(): UByte {
        return dequeueHexBytes(1).toUByte(16)
    }

    fun dequeue2ByteShort(): Short {
        return dequeueHexBytes(2).toShort(16)
    }

    fun dequeue4ByteInt(): Int {
        return dequeueHexBytes(4).toInt(16)
    }

    fun dequeue8ByteLong(): Long {
        return dequeueHexBytes(8).toLong(16)
    }
}