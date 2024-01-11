package cmjava2023.bytecodeTestUtil

import org.cmjava2023.classfilespecification.constantpool.ConstantInfo
import org.cmjava2023.classfilespecification.opCodes.OpCode
import org.cmjava2023.util.BytesInHexQueue
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.jvm.jvmErasure

class NextOpCodeFromByteQueueQuery {
    class ParsedOpCode(val name: String, vararg val parameterValues: Any)
    
    companion object {
        private val opCodeValueToClassMap: Map<UByte, KClass<*>> = OpCode.classToOpCodeValueMap.entries.associate { Pair(it.value, it.key) }

        fun fetch(bytesInHexQueue: BytesInHexQueue, constantPoolItems: List<String>): ParsedOpCode {
            val opCodeKClass = opCodeValueToClassMap[bytesInHexQueue.dequeueUByte()]!!
            val opCodeName = opCodeKClass.java.name
            val constructor = opCodeKClass.constructors.single()
            val arguments = mutableListOf<Any>()
            for (parameter in constructor.parameters) {
                if (parameter.type.jvmErasure.isSubclassOf(ConstantInfo::class) || opCodeKClass.isSubclassOf(OpCode.Ldc_w::class)) {
                    val index = bytesInHexQueue.dequeue2ByteUShort().toInt()
                    arguments.add(constantPoolItems[index])
                } else if (opCodeKClass.isSubclassOf(OpCode.Ldc::class)) {
                    val index = bytesInHexQueue.dequeueUByte().toInt()
                    arguments.add(constantPoolItems[index])
                } else {
                    try {
                        when (parameter.type.jvmErasure) {
                            UByte::class -> arguments.add(bytesInHexQueue.dequeueUByte())
                            Byte::class -> arguments.add(bytesInHexQueue.dequeueByte())
                            UShort::class -> arguments.add(bytesInHexQueue.dequeue2ByteUShort())
                            Short::class -> arguments.add(bytesInHexQueue.dequeue2ByteShort())
                            Int::class -> arguments.add(bytesInHexQueue.dequeue4ByteInt())
                            Long::class -> arguments.add(bytesInHexQueue.dequeue8ByteLong())
                            OpCode.ArrayType::class -> {
                                val typeCode = bytesInHexQueue.dequeueUByte()
                                arguments.add(OpCode.ArrayType.entries.first { it.code == typeCode })
                            }

                            else -> throw NotImplementedError(parameter.type.jvmErasure.toString())
                        }
                    } catch (n: NumberFormatException) {
                        throw Exception(opCodeName)
                    }
                }
            }

            return ParsedOpCode(opCodeName, *arguments.toTypedArray())
        }
    }
}