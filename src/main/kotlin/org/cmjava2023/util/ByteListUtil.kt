package org.cmjava2023.util

import java.nio.ByteBuffer


@Suppress("unused")
class ByteListUtil {
    companion object {
        fun MutableList<Byte>.add(item: UInt){
            this.addAll(item.toByteList())
        }
        fun MutableList<Byte>.add(item: Int){
            this.addAll(item.toByteList())
        }

        fun MutableList<Byte>.add(item: UShort){
            this.addAll(item.toByteList())
        }

        fun MutableList<Byte>.add(item: Short){
            this.addAll(item.toByteList())
        }

        fun MutableList<Byte>.add(item: UByte){
            this.addAll(item.toByteList())
        }

        fun MutableList<Byte>.add(item: Long){
            this.addAll(item.toByteList())
        }

        fun UInt.toByteList(): List<Byte> {
            return ByteBuffer.allocate(UInt.SIZE_BYTES).putInt(this.toInt()).array().toList()
        }

        fun Int.toByteList(): List<Byte> {
            return ByteBuffer.allocate(UInt.SIZE_BYTES).putInt(this).array().toList()
        }

        fun UByte.toByteList(): List<Byte> {
            return ByteBuffer.allocate(UByte.SIZE_BYTES).put(this.toByte()).array().toList()
        }

        fun UShort.toByteList(): List<Byte> {
            return ByteBuffer.allocate(Short.SIZE_BYTES).putShort(this.toShort()).array().toList()
        }

        fun Short.toByteList(): List<Byte> {
            return ByteBuffer.allocate(Short.SIZE_BYTES).putShort(this).array().toList()
        }

        fun Long.toByteList(): List<Byte> {
            return ByteBuffer.allocate(Long.SIZE_BYTES).putLong(this).array().toList()
        }
    }
}