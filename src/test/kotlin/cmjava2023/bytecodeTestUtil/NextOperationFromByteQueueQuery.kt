package cmjava2023.bytecodeTestUtil

import org.cmjava2023.classfilespecification.Operation
import org.cmjava2023.classfilespecification.constantpool.ConstantPoolEntry
import org.cmjava2023.util.BytesInHexQueue
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.jvm.jvmErasure

class NextOperationFromByteQueueQuery {
    class ParsedOperation(val name: String, vararg val operands: Any)
    
    companion object {
        private val opCodeValueToClassMap: Map<UByte, KClass<*>> = Operation.classToOpCodeValueMap.entries.associate { Pair(it.value, it.key) }

        private val operationsWithFirstOperandConstantPoolIndex = listOf(
            Operation.Anewarray::class,
            Operation.Getstatic::class,
            Operation.Invokevirtual::class,
            Operation.Invokespecial::class,
            Operation.Ldc2_w::class,
            Operation.Multianewarray::class,
            Operation.New::class
        )
        
        fun fetch(bytesInHexQueue: BytesInHexQueue, constantPoolItems: List<String>): ParsedOperation {
            val opCodeKClass = opCodeValueToClassMap[bytesInHexQueue.dequeueUByte()]!!
            val opCodeName = opCodeKClass.java.name.removePrefix("org.cmjava2023.classfilespecification.Operation$")
            val constructor = opCodeKClass.constructors.single()
            val arguments = mutableListOf<Any>()
            for ((index, parameter) in constructor.parameters.withIndex()) {
                if (operationsWithFirstOperandConstantPoolIndex.contains(opCodeKClass) && index == 0) {
                    val constantPoolIndex = bytesInHexQueue.dequeue2ByteUShort().toInt()
                    arguments.add(constantPoolItems[constantPoolIndex])
                } else if (opCodeKClass.isSubclassOf(Operation.Ldc::class)) {
                    val constantPoolIndex = bytesInHexQueue.dequeueUByte().toInt()
                    arguments.add(constantPoolItems[constantPoolIndex])
                } else {
                    try {
                        when (parameter.type.jvmErasure) {
                            UByte::class -> arguments.add(bytesInHexQueue.dequeueUByte())
                            Byte::class -> arguments.add(bytesInHexQueue.dequeueByte())
                            UShort::class -> arguments.add(bytesInHexQueue.dequeue2ByteUShort())
                            Short::class -> arguments.add(bytesInHexQueue.dequeue2ByteShort())
                            Int::class -> arguments.add(bytesInHexQueue.dequeue4ByteInt())
                            Long::class -> arguments.add(bytesInHexQueue.dequeue8ByteLong())
                            Operation.ArrayType::class -> {
                                val typeCode = bytesInHexQueue.dequeueUByte()
                                arguments.add(Operation.ArrayType.entries.first { it.code == typeCode })
                            }

                            else -> throw NotImplementedError(parameter.type.jvmErasure.toString())
                        }
                    } catch (n: NumberFormatException) {
                        throw Exception(opCodeName)
                    }
                }
            }

            return ParsedOperation(opCodeName, *arguments.toTypedArray())
        }
    }
}