package org.cmjava2023.classfilespecification

import org.cmjava2023.classfilespecification.constantpool.ClassConstantInfo
import org.cmjava2023.classfilespecification.constantpool.ConstantInfo
import org.cmjava2023.classfilespecification.constantpool.FieldReferenceConstantInfo
import org.cmjava2023.classfilespecification.constantpool.MethodReferenceConstantInfo
import org.cmjava2023.symboltable.Variable
import org.cmjava2023.util.BytesInHexQueue
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.jvm.jvmErasure

/**
 * See https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html
 * https://en.wikipedia.org/wiki/List_of_Java_bytecode_instructions
 */
@Suppress("unused")
abstract class OpCode(vararg val values: Any) {

    val opCodeValue:UByte = if (this is OpCodeToTransform) { 0xcbu } else { classToOpCodeValueMap.getValue(this::class) }
    
    companion object {
        private val classToOpCodeValueMap = mapOf<KClass<*>, UByte>(
            Aaload::class to (0x32u).toUByte(),
            Aastore::class to (0x53u).toUByte(),
            Aconst_null::class to (0x01u).toUByte(),
            Aload::class to (0x19u).toUByte(),
            Aload::class to (0x19u).toUByte(),
            Aload_0::class to (0x2au).toUByte(),
            Aload_1::class to (0x2bu).toUByte(),
            Aload_2::class to (0x2cu).toUByte(),
            Aload_3::class to (0x2du).toUByte(),
            Anewarray::class to (0xbdu).toUByte(),
            Areturn::class to (0xb0u).toUByte(),
            Arraylength::class to (0xbeu).toUByte(),
            Astore::class to (0x3au).toUByte(),
            Astore_0::class to (0x4bu).toUByte(),
            Astore_1::class to (0x4cu).toUByte(),
            Astore_2::class to (0x4du).toUByte(),
            Astore_3::class to (0x4eu).toUByte(),
            Athrow::class to (0xbfu).toUByte(),
            Baload::class to (0x33u).toUByte(),
            Bastore::class to (0x54u).toUByte(),
            Bipush::class to (0x10u).toUByte(),
            Caload::class to (0x34u).toUByte(),
            Castore::class to (0x55u).toUByte(),
            D2i::class to (0x8eu).toUByte(),
            D2f::class to (0x90u).toUByte(),
            D2l::class to (0x8fu).toUByte(),
            Dadd::class to (0x63u).toUByte(),
            Daload::class to (0x31u).toUByte(),
            Dastore::class to (0x52u).toUByte(),
            Dcompg::class to (0x98u).toUByte(),
            Dcompl::class to (0x97u).toUByte(),
            Dconst_0::class to (0x0eu).toUByte(),
            Dconst_1::class to (0x0fu).toUByte(),
            Ddiv::class to (0x6fu).toUByte(),
            Dload::class to (0x18u).toUByte(),
            Dload_0::class to (0x26u).toUByte(),
            Dload_1::class to (0x27u).toUByte(),
            Dload_2::class to (0x28u).toUByte(),
            Dload_3::class to (0x29u).toUByte(),
            Dmul::class to (0x6bu).toUByte(),
            Dneg::class to (0x77u).toUByte(),
            Drem::class to (0x73u).toUByte(),
            Dreturn::class to (0xafu).toUByte(),
            Dstore::class to (0x39u).toUByte(),
            Dstore_0::class to (0x47u).toUByte(),
            Dstore_1::class to (0x48u).toUByte(),
            Dstore_2::class to (0x49u).toUByte(),
            Dstore_3::class to (0x4au).toUByte(),
            Dsub::class to (0x67u).toUByte(),
            Dup2::class to (0x5cu).toUByte(),
            Dup2_x1::class to (0x5du).toUByte(),
            Dup2_x2::class to (0x5eu).toUByte(),
            Dup::class to (0x59u).toUByte(),
            Dup_x1::class to (0x5au).toUByte(),
            Dup_x2::class to (0x5bu).toUByte(),
            F2d::class to (0x8du).toUByte(),
            F2i::class to (0x8bu).toUByte(),
            F2l::class to (0x8cu).toUByte(),
            Fadd::class to (0x62u).toUByte(),
            Faload::class to (0x30u).toUByte(),
            Fastore::class to (0x51u).toUByte(),
            Fcmpg::class to (0x96u).toUByte(),
            Fcmpl::class to (0x95u).toUByte(),
            Fconst_0::class to (0x0bu).toUByte(),
            Fconst_1::class to (0x0cu).toUByte(),
            Fconst_2::class to (0x0du).toUByte(),
            Fdiv::class to (0x6eu).toUByte(),
            Fload::class to (0x17u).toUByte(),
            Fload_0::class to (0x22u).toUByte(),
            Fload_1::class to (0x23u).toUByte(),
            Fload_2::class to (0x24u).toUByte(),
            Fload_3::class to (0x25u).toUByte(),
            Fmul::class to (0x6au).toUByte(),
            Fneg::class to (0x76u).toUByte(),
            Frem::class to (0x72u).toUByte(),
            Freturn::class to (0xaeu).toUByte(),
            Fstore::class to (0x38u).toUByte(),
            Fstore_0::class to (0x43u).toUByte(),
            Fstore_1::class to (0x44u).toUByte(),
            Fstore_2::class to (0x45u).toUByte(),
            Fstore_3::class to (0x46u).toUByte(),
            Fsub::class to (0x66u).toUByte(),
            Getstatic::class to (0xb2u).toUByte(),
            Goto::class to (0xa7u).toUByte(),
            I2b::class to (0x91u).toUByte(),
            I2c::class to (0x92u).toUByte(),
            I2d::class to (0x87u).toUByte(),
            I2f::class to (0x86u).toUByte(),
            I2l::class to (0x85u).toUByte(),
            I2s::class to (0x93u).toUByte(),
            Iadd::class to (0x60u).toUByte(),
            Iaload::class to (0x2eu).toUByte(),
            Iand::class to (0x7eu).toUByte(),
            Iastore::class to (0x4fu).toUByte(),
            Iconst_0::class to (0x03u).toUByte(),
            Iconst_1::class to (0x04u).toUByte(),
            Iconst_2::class to (0x05u).toUByte(),
            Iconst_3::class to (0x06u).toUByte(),
            Iconst_4::class to (0x07u).toUByte(),
            Iconst_5::class to (0x08u).toUByte(),
            Iconst_m1::class to (0x02u).toUByte(),
            Idiv::class to (0x6cu).toUByte(),
            Ifeq::class to (0x99u).toUByte(),
            Ifne::class to (0x9au).toUByte(),
            Iflt::class to (0x9bu).toUByte(),
            Ifge::class to (0x9cu).toUByte(),
            Ifgt::class to (0x9du).toUByte(),
            Ifle::class to (0x9eu).toUByte(),
            If_icmpeq::class to (0x9fu).toUByte(),
            If_icmpne::class to (0xa0u).toUByte(),
            If_icmplt::class to (0xa1u).toUByte(),
            If_icmpge::class to (0xa2u).toUByte(),
            If_icmpgt::class to (0xa3u).toUByte(),
            If_icmple::class to (0xa4u).toUByte(),
            Iinc::class to (0x84u).toUByte(),
            Iload::class to (0x15u).toUByte(),
            Iload_0::class to (0x1au).toUByte(),
            Iload_1::class to (0x1bu).toUByte(),
            Iload_2::class to (0x1cu).toUByte(),
            Iload_3::class to (0x1du).toUByte(),
            Imul::class to (0x68u).toUByte(),
            Ineg::class to (0x74u).toUByte(),
            Invokespecial::class to (0xb7u).toUByte(),
            Invokevirtual::class to (0xb6u).toUByte(),
            Ior::class to (0x80u).toUByte(),
            Irem::class to (0x70u).toUByte(),
            Ireturn::class to (0xacu).toUByte(),
            Ishl::class to (0x78u).toUByte(),
            Ishr::class to (0x7au).toUByte(),
            Istore::class to (0x36u).toUByte(),
            Istore_0::class to (0x3bu).toUByte(),
            Istore_1::class to (0x3cu).toUByte(),
            Istore_2::class to (0x3du).toUByte(),
            Istore_3::class to (0x3eu).toUByte(),
            Isub::class to (0x64u).toUByte(),
            Iushr::class to (0x7cu).toUByte(),
            Ixor::class to (0x82u).toUByte(),
            L2d::class to (0x8au).toUByte(),
            L2f::class to (0x89u).toUByte(),
            L2i::class to (0x88u).toUByte(),
            Ladd::class to (0x61u).toUByte(),
            Laload::class to (0x2fu).toUByte(),
            Land::class to (0x7fu).toUByte(),
            Lastore::class to (0x50u).toUByte(),
            Lcmp::class to (0x94u).toUByte(),
            Lconst_0::class to (0x09u).toUByte(),
            Lconst_1::class to (0x0au).toUByte(),
            Ldc2_w::class to (0x14u).toUByte(),
            Ldc::class to (0x12u).toUByte(),
            Ldc_w::class to (0x13u).toUByte(),
            Ldiv::class to (0x6du).toUByte(),
            Lload::class to (0x16u).toUByte(),
            Lload_0::class to (0x1eu).toUByte(),
            Lload_1::class to (0x1fu).toUByte(),
            Lload_2::class to (0x20u).toUByte(),
            Lload_3::class to (0x21u).toUByte(),
            Lmul::class to (0x69u).toUByte(),
            Lneg::class to (0x75u).toUByte(),
            Lor::class to (0x81u).toUByte(),
            Lrem::class to (0x71u).toUByte(),
            Lreturn::class to (0xadu).toUByte(),
            Lshl::class to (0x79u).toUByte(),
            Lshr::class to (0x7bu).toUByte(),
            Lstore::class to (0x37u).toUByte(),
            Lstore_0::class to (0x3fu).toUByte(),
            Lstore_1::class to (0x40u).toUByte(),
            Lstore_2::class to (0x41u).toUByte(),
            Lstore_3::class to (0x42u).toUByte(),
            Lsub::class to (0x65u).toUByte(),
            Lushr::class to (0x7du).toUByte(),
            Lxor::class to (0x83u).toUByte(),
            Multianewarray::class to (0xc5u).toUByte(),
            New::class to (0xbbu).toUByte(),
            Newarray::class to (0xbcu).toUByte(),
            Nop::class to (0x00u).toUByte(),
            Pop2::class to (0x58u).toUByte(),
            Pop::class to (0x57u).toUByte(),
            Return::class to (0xb1u).toUByte(),
            Saload::class to (0x35u).toUByte(),
            Sastore::class to (0x56u).toUByte(),
            Sipush::class to (0x11u).toUByte(),
            Swap::class to (0x5fu).toUByte(),
        )
        
        private val opCodeValueToClassMap: Map<UByte, KClass<*>> =
            classToOpCodeValueMap.entries.associate { Pair(it.value, it.key) }

        fun parseNext(bytesInHexQueue: BytesInHexQueue, constantPoolItems: List<String>): OpCode {
            val kClass = opCodeValueToClassMap[bytesInHexQueue.dequeueUByte()]!!
            val constructor = kClass.constructors.single()
            val arguments = mutableListOf<Any>()
            var haveArgumentTypesChanged = false
            for (parameter in constructor.parameters) {
                if (parameter.type.jvmErasure.isSubclassOf(ConstantInfo::class) || kClass.isSubclassOf(Ldc_w::class)) {
                    val index = bytesInHexQueue.dequeue2ByteUShort().toInt()
                    arguments.add(constantPoolItems[index])
                    haveArgumentTypesChanged = true
                } else if (kClass.isSubclassOf(Ldc::class)) {
                    val index = bytesInHexQueue.dequeueUByte().toInt()
                    arguments.add(constantPoolItems[index])
                    haveArgumentTypesChanged = true                    
                } else {
                    try {
                        when(parameter.type.jvmErasure) {
                            UByte::class -> arguments.add(bytesInHexQueue.dequeueUByte())
                            Byte::class -> arguments.add(bytesInHexQueue.dequeueByte())
                            UShort::class -> arguments.add(bytesInHexQueue.dequeue2ByteUShort())
                            Short::class -> arguments.add(bytesInHexQueue.dequeue2ByteShort())
                            Int::class -> arguments.add(bytesInHexQueue.dequeue4ByteInt())
                            Long::class -> arguments.add(bytesInHexQueue.dequeue8ByteLong())
                            ArrayType::class -> {
                                val typeCode = bytesInHexQueue.dequeueUByte()
                                arguments.add(OpCode.ArrayType.entries.first { it.code == typeCode })
                            } 
                            else -> throw NotImplementedError(parameter.type.jvmErasure.toString())
                        }
                    } catch (n: NumberFormatException) {
                        throw Exception(kClass.java.name, n)
                    }
                }
            }
            
            return if(haveArgumentTypesChanged) {
                OpCodeParsedFromClassFile(kClass, *arguments.toTypedArray())
            } else{
                constructor.call(*arguments.toTypedArray()) as OpCode
            }
        }
    }
    
    abstract class OpCodeToTransform(vararg values: Any): OpCode(*values)
    
    interface OpCodeWithIndexToResolve
    
    class IfElseIfsElseBlock(val ifAndElseIfs: List<If>, val opCodesInElse: List<OpCode>): OpCodeToTransform()
    class TransformedOpCode(val bytes: List<Byte>): OpCodeToTransform()
    
    class If(val opCodeClass: KClass<*>) {
        val expressionOpCodes: MutableList<OpCode> = mutableListOf()
        val opCodesInsideBlockWithoutGoto: MutableList<OpCode> = mutableListOf()
    }

    class OpCodeParsedFromClassFile(val opCodeClass: KClass<*>, vararg values: Any): OpCodeToTransform(*values)

    class LoadConstant(val constantInfo: ConstantInfo): OpCodeToTransform(constantInfo)
    class IntConstant(val int: Int): OpCodeToTransform()
    class LongConstant(val long: Long): OpCodeToTransform()
    class FloatConstant(val float: Float): OpCodeToTransform()
    class DoubleConstant(val double: Double): OpCodeToTransform()

    class StoreInt(val variableSymbol: Variable): OpCodeToTransform()
    class StoreLong(val variableSymbol: Variable): OpCodeToTransform()
    class StoreFloat(val variableSymbol: Variable): OpCodeToTransform()
    class StoreDouble(val variableSymbol: Variable): OpCodeToTransform()
    class StoreArray(val variableSymbol: Variable): OpCodeToTransform()

    class LoadInt(val variableSymbol: Variable): OpCodeToTransform()
    class LoadLong(val variableSymbol: Variable): OpCodeToTransform()
    class LoadFloat(val variableSymbol: Variable): OpCodeToTransform()
    class LoadDouble(val variableSymbol: Variable): OpCodeToTransform()
    class LoadArray(val variableSymbol: Variable): OpCodeToTransform()
    
    class IncreaseInt(val variableSymbol: Variable, val byteToIncreaseBy: Byte): OpCodeToTransform()

    interface ReturnAnything

    class Aaload: OpCode()
    class Aastore: OpCode()
    class Aconst_null: OpCode()
    class Aload(indexInsideLocalVariableArray: UByte): OpCode(indexInsideLocalVariableArray)
    class Aload_0: OpCode()
    class Aload_1: OpCode()
    class Aload_2: OpCode()
    class Aload_3: OpCode()
    class Anewarray(classConstantInfo: ClassConstantInfo): OpCode(classConstantInfo)
    class Areturn: OpCode(), ReturnAnything
    class Arraylength: OpCode()
    class Astore(indexInsideLocalVariableArray: UByte): OpCode(indexInsideLocalVariableArray)
    class Astore_0: OpCode()
    class Astore_1: OpCode()
    class Astore_2: OpCode()
    class Astore_3: OpCode()
    class Athrow: OpCode()
    class Baload: OpCode()
    class Bastore: OpCode()
    class Bipush(byte: Byte): OpCode(byte)
    class Caload: OpCode()
    class Castore: OpCode()
    class D2i: OpCode()
    class D2f: OpCode()
    class D2l: OpCode()
    class Dadd: OpCode()
    class Daload: OpCode()
    class Dastore: OpCode()
    class Dcompg: OpCode()
    class Dcompl: OpCode()
    class Dconst_0: OpCode()
    class Dconst_1: OpCode()
    class Ddiv: OpCode()
    class Dload(indexInsideLocalVariableArray: UByte): OpCode(indexInsideLocalVariableArray)
    class Dload_0: OpCode()
    class Dload_1: OpCode()
    class Dload_2: OpCode()
    class Dload_3: OpCode()
    class Dmul: OpCode()
    class Dneg: OpCode()
    class Drem: OpCode()
    class Dreturn: OpCode(), ReturnAnything
    class Dstore(indexInsideLocalVariableArray: UByte): OpCode(indexInsideLocalVariableArray)
    class Dstore_0: OpCode()
    class Dstore_1: OpCode()
    class Dstore_2: OpCode()
    class Dstore_3: OpCode()
    class Dsub: OpCode()
    class Dup2: OpCode()
    class Dup2_x1: OpCode()
    class Dup2_x2: OpCode()
    class Dup: OpCode()
    class Dup_x1: OpCode()
    class Dup_x2: OpCode()
    class F2d: OpCode()
    class F2i: OpCode()
    class F2l: OpCode()
    class Fadd: OpCode()
    class Faload: OpCode()
    class Fastore: OpCode()
    class Fcmpg: OpCode()
    class Fcmpl: OpCode()
    class Fconst_0: OpCode()
    class Fconst_1: OpCode()
    class Fconst_2: OpCode()
    class Fdiv: OpCode()
    class Fload(indexInsideLocalVariableArray: UByte): OpCode(indexInsideLocalVariableArray)
    class Fload_0: OpCode()
    class Fload_1: OpCode()
    class Fload_2: OpCode()
    class Fload_3: OpCode()
    class Fmul: OpCode()
    class Fneg: OpCode()
    class Frem: OpCode()
    class Freturn: OpCode(), ReturnAnything
    class Fstore(indexInsideLocalVariableArray: UByte): OpCode(indexInsideLocalVariableArray)
    class Fstore_0: OpCode()
    class Fstore_1: OpCode()
    class Fstore_2: OpCode()
    class Fstore_3: OpCode()
    class Fsub: OpCode()
    class Getstatic(fieldReferenceConstantInfo: FieldReferenceConstantInfo): OpCode(fieldReferenceConstantInfo)
    class Goto(offset: Short): OpCode(offset)
    class I2b: OpCode()
    class I2c: OpCode()
    class I2d: OpCode()
    class I2f: OpCode()
    class I2l: OpCode()
    class I2s: OpCode()
    class Iadd: OpCode()
    class Iaload: OpCode()
    class Iand: OpCode()
    class Iastore: OpCode()
    class Iconst_0: OpCode()
    class Iconst_1: OpCode()
    class Iconst_2: OpCode()
    class Iconst_3: OpCode()
    class Iconst_4: OpCode()
    class Iconst_5: OpCode()
    class Iconst_m1: OpCode()
    class Idiv: OpCode()
    class Ifeq(opCodeAddressOffset: Short): OpCode(opCodeAddressOffset)
    class Ifne(opCodeAddressOffset: Short): OpCode(opCodeAddressOffset)
    class Iflt(opCodeAddressOffset: Short): OpCode(opCodeAddressOffset)
    class Ifge(opCodeAddressOffset: Short): OpCode(opCodeAddressOffset)
    class Ifgt(opCodeAddressOffset: Short): OpCode(opCodeAddressOffset)
    class Ifle(opCodeAddressOffset: Short): OpCode(opCodeAddressOffset)
    class If_icmpeq(opCodeAddressOffset: Short): OpCode(opCodeAddressOffset)
    class If_icmpne(opCodeAddressOffset: Short): OpCode(opCodeAddressOffset)
    class If_icmplt(opCodeAddressOffset: Short): OpCode(opCodeAddressOffset)
    class If_icmpge(opCodeAddressOffset: Short): OpCode(opCodeAddressOffset)
    class If_icmpgt(opCodeAddressOffset: Short): OpCode(opCodeAddressOffset)
    class If_icmple(opCodeAddressOffset: Short): OpCode(opCodeAddressOffset)
    class Iinc(indexInsideLocalVariableArray: UByte, incrementBy: Byte): OpCode(indexInsideLocalVariableArray, incrementBy)
    class Iload(indexInsideLocalVariableArray: UByte): OpCode(indexInsideLocalVariableArray)
    class Iload_0: OpCode()
    class Iload_1: OpCode()
    class Iload_2: OpCode()
    class Iload_3: OpCode()
    class Imul: OpCode()
    class Ineg: OpCode()
    class Invokespecial(methodReferenceConstantInfo: MethodReferenceConstantInfo): OpCode(methodReferenceConstantInfo)
    class Invokevirtual(methodReferenceConstantInfo: MethodReferenceConstantInfo): OpCode(methodReferenceConstantInfo)
    class Ior: OpCode()
    class Irem: OpCode()
    class Ireturn: OpCode(), ReturnAnything
    class Ishl: OpCode()
    class Ishr: OpCode()
    class Istore(indexInsideLocalVariableArray: UByte): OpCode(indexInsideLocalVariableArray)
    class Istore_0: OpCode()
    class Istore_1: OpCode()
    class Istore_2: OpCode()
    class Istore_3: OpCode()
    class Isub: OpCode()
    class Iushr: OpCode()
    class Ixor: OpCode()
    class L2d: OpCode()
    class L2f: OpCode()
    class L2i: OpCode()
    class Ladd: OpCode()
    class Laload: OpCode()
    class Land: OpCode()
    class Lastore: OpCode()
    class Lcmp: OpCode()
    class Lconst_0: OpCode()
    class Lconst_1: OpCode()
    class Ldc(indexInConstantPool: UByte): OpCode(indexInConstantPool), OpCodeWithIndexToResolve
    class Ldc2_w(longOrDoubleToLoad: ConstantInfo): OpCode(longOrDoubleToLoad)
    class Ldc_w(indexInConstantPool: UShort): OpCode(indexInConstantPool), OpCodeWithIndexToResolve
    class Ldiv: OpCode()
    class Lload(indexInsideLocalVariableArray: UByte): OpCode(indexInsideLocalVariableArray)
    class Lload_0: OpCode()
    class Lload_1: OpCode()
    class Lload_2: OpCode()
    class Lload_3: OpCode()
    class Lmul: OpCode()
    class Lneg: OpCode()
    class Lor: OpCode()
    class Lrem: OpCode()
    class Lreturn: OpCode(), ReturnAnything
    class Lshl: OpCode()
    class Lshr: OpCode()
    class Lstore(indexInsideLocalVariableArray: UByte): OpCode(indexInsideLocalVariableArray)
    class Lstore_0: OpCode()
    class Lstore_1: OpCode()
    class Lstore_2: OpCode()
    class Lstore_3: OpCode()
    class Lsub: OpCode()
    class Lushr: OpCode()
    class Lxor: OpCode()
    class Multianewarray(classConstantInfo: ClassConstantInfo, dimensions: UByte): OpCode(classConstantInfo, dimensions)
    class New(classConstantInfo: ClassConstantInfo): OpCode(classConstantInfo)
    enum class ArrayType(val code: UByte) {
        T_BOOLEAN(4u),
        T_CHAR(5u),
        T_FLOAT(6u),
        T_DOUBLE(7u),
        T_BYTE(8u),
        T_SHORT(9u),
        T_INT(10u),
        T_LONG(11u),
    }
    class Newarray(arrayType: ArrayType): OpCode(arrayType)
    class Nop: OpCode()
    class Pop2: OpCode()
    class Pop: OpCode()
    class Return: OpCode(), ReturnAnything
    class Saload: OpCode()
    class Sastore: OpCode()
    class Sipush(valueToPush: Short): OpCode(valueToPush)
    class Swap: OpCode()
}