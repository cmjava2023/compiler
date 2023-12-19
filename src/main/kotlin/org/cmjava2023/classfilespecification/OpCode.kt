package org.cmjava2023.classfilespecification

import org.cmjava2023.classfilespecification.constantpool.ClassConstantInfo
import org.cmjava2023.classfilespecification.constantpool.ConstantInfo
import org.cmjava2023.classfilespecification.constantpool.FieldReferenceConstantInfo
import org.cmjava2023.classfilespecification.constantpool.MethodReferenceConstantInfo
import org.cmjava2023.symboltable.Variable
import org.cmjava2023.util.BytesInHexQueue
import kotlin.reflect.KClass

/**
 * See https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html
 * https://en.wikipedia.org/wiki/List_of_Java_bytecode_instructions
 */
@Suppress("unused")
abstract class OpCode(val opCodeValue:UByte,vararg val values: Any) {
    
    companion object {
        private val classToOpCodeValueMap = mapOf<KClass<*>, UByte>(
            Aaload::class to (0x32u).toUByte()
        )
        
        private val opCodeValueToClassMap: Map<UByte, KClass<*>> =
            classToOpCodeValueMap.entries.associate { Pair(it.value, it.key) }

        fun parseNext(bytesInHexQueue: BytesInHexQueue): OpCode {
            val kClass = opCodeValueToClassMap[bytesInHexQueue.dequeueUByte()]!!
            val constructor = kClass.constructors.single()
            val arguments = mutableListOf<Any>()
            for (parameter in constructor.parameters) {
                when(parameter.type) {
                    UByte::class -> arguments.add(bytesInHexQueue.dequeueUByte())
                    else -> throw NotImplementedError(parameter.type.toString())
                }
            }
            
            return constructor.call(arguments) as OpCode
        }
    }

    abstract class MultiplePossibleOpcode(vararg values: Any): OpCode(0xcbu, *values)

    class LoadConstant(val constantInfo: ConstantInfo): MultiplePossibleOpcode(constantInfo)
    class IntConstant(val int: Int): MultiplePossibleOpcode()
    class LongConstant(val long: Long): MultiplePossibleOpcode()
    class FloatConstant(val float: Float): MultiplePossibleOpcode()
    class DoubleConstant(val double: Double): MultiplePossibleOpcode()

    class StoreInt(val variableSymbol: Variable): MultiplePossibleOpcode()
    class StoreLong(val variableSymbol: Variable): MultiplePossibleOpcode()
    class StoreFloat(val variableSymbol: Variable): MultiplePossibleOpcode()
    class StoreDouble(val variableSymbol: Variable): MultiplePossibleOpcode()

    class LoadInt(val variableSymbol: Variable): MultiplePossibleOpcode()
    class LoadLong(val variableSymbol: Variable): MultiplePossibleOpcode()
    class LoadFloat(val variableSymbol: Variable): MultiplePossibleOpcode()
    class LoadDouble(val variableSymbol: Variable): MultiplePossibleOpcode()

    interface ReturnAnything

    class Aaload: OpCode(classToOpCodeValueMap.getValue(Aaload::class))
    class Aastore: OpCode(0x53u)
    class Aconst_null: OpCode(0x01u)
    class Aload(indexInsideLocalVariableArray: UByte): OpCode(0x19u, indexInsideLocalVariableArray)
    class Aload_0: OpCode(0x2au)
    class Aload_1: OpCode(0x2bu)
    class Aload_2: OpCode(0x2cu)
    class Aload_3: OpCode(0x2du)
    class Anewarray(classConstantInfo: ClassConstantInfo): OpCode(0xbdu, classConstantInfo)
    class Areturn: OpCode(0xb0u), ReturnAnything
    class Arraylength: OpCode(0xbeu)
    class Astore(indexInsideLocalVariableArray: UByte): OpCode(0x3au, indexInsideLocalVariableArray)
    class Astore_0: OpCode(0x4bu)
    class Astore_1: OpCode(0x4cu)
    class Astore_2: OpCode(0x4du)
    class Astore_3: OpCode(0x4eu)
    class Athrow: OpCode(0xbfu)
    class Baload: OpCode(0x33u)
    class Bastore: OpCode(0x54u)
    class Bipush(byte: Byte): OpCode(0x10u, byte)
    class D2I: OpCode(0x8eu)
    class D2f: OpCode(0x90u)
    class D2l: OpCode(0x8fu)
    class Dadd: OpCode(0x63u)
    class Daload: OpCode(0x31u)
    class Dastore: OpCode(0x52u)
    class Dcompg: OpCode(0x98u)
    class Dcompl: OpCode(0x97u)
    class Dconst_0: OpCode(0x0eu)
    class Dconst_1: OpCode(0x0fu)
    class Ddiv: OpCode(0x6fu)
    class Dload(indexInsideLocalVariableArray: UByte): OpCode(0x18u, indexInsideLocalVariableArray)
    class Dload_0: OpCode(0x26u)
    class Dload_1: OpCode(0x27u)
    class Dload_2: OpCode(0x28u)
    class Dload_3: OpCode(0x29u)
    class Dmul: OpCode(0x6bu)
    class Dneg: OpCode(0x77u)
    class Drem: OpCode(0x73u)
    class Dreturn: OpCode(0xafu), ReturnAnything
    class Dstore(indexInsideLocalVariableArray: UByte): OpCode(0x39u, indexInsideLocalVariableArray)
    class Dstore_0: OpCode(0x47u)
    class Dstore_1: OpCode(0x48u)
    class Dstore_2: OpCode(0x49u)
    class Dstore_3: OpCode(0x4au)
    class Dsub: OpCode(0x67u)
    class Dup2: OpCode(0x5cu)
    class Dup2_x1: OpCode(0x5du)
    class Dup2_x2: OpCode(0x5eu)
    class Dup: OpCode(0x59u)
    class Dup_x1: OpCode(0x5au)
    class Dup_x2: OpCode(0x5bu)
    class F2d: OpCode(0x8du)
    class F2i: OpCode(0x8bu)
    class F2l: OpCode(0x8cu)
    class Fadd: OpCode(0x62u)
    class Faload: OpCode(0x30u)
    class Fastore: OpCode(0x51u)
    class Fcmpg: OpCode(0x96u)
    class Fcmpl: OpCode(0x95u)
    class Fconst_0: OpCode(0x0bu)
    class Fconst_1: OpCode(0x0cu)
    class Fconst_2: OpCode(0x0du)
    class Fdiv: OpCode(0x6eu)
    class Fload(indexInsideLocalVariableArray: UByte): OpCode(0x17u, indexInsideLocalVariableArray)
    class Fload_0: OpCode(0x22u)
    class Fload_1: OpCode(0x23u)
    class Fload_2: OpCode(0x24u)
    class Fload_3: OpCode(0x25u)
    class Fmul: OpCode(0x6au)
    class Fneg: OpCode(0x76u)
    class Freg: OpCode(0x72u)
    class Freturn: OpCode(0xaeu), ReturnAnything
    class Fstore(indexInsideLocalVariableArray: UByte): OpCode(0x38u, indexInsideLocalVariableArray)
    class Fstore_0: OpCode(0x43u)
    class Fstore_1: OpCode(0x44u)
    class Fstore_2: OpCode(0x45u)
    class Fstore_3: OpCode(0x46u)
    class Fsub: OpCode(0x66u)
    class Getstatic(fieldReferenceConstantInfo: FieldReferenceConstantInfo): OpCode(0xb2u, fieldReferenceConstantInfo)
    class I2b: OpCode(0x91u)
    class I2c: OpCode(0x92u)
    class I2d: OpCode(0x87u)
    class I2f: OpCode(0x86u)
    class I2l: OpCode(0x85u)
    class I2s: OpCode(0x93u)
    class Iadd: OpCode(0x60u)
    class Iaload: OpCode(0x2eu)
    class Iand: OpCode(0x7eu)
    class Iastore: OpCode(0x4fu)
    class Iconst_0: OpCode(0x03u)
    class Iconst_1: OpCode(0x04u)
    class Iconst_2: OpCode(0x05u)
    class Iconst_3: OpCode(0x06u)
    class Iconst_4: OpCode(0x07u)
    class Iconst_5: OpCode(0x08u)
    class Iconst_m1: OpCode(0x02u)
    class Idiv: OpCode(0x6cu)
    class Iinc(indexInsideLocalVariableArray: UByte, incrementBy: Byte): OpCode(0x84u, indexInsideLocalVariableArray, incrementBy)
    class Iload(indexInsideLocalVariableArray: UByte): OpCode(0x15u, indexInsideLocalVariableArray)
    class Iload_0: OpCode(0x1au)
    class Iload_1: OpCode(0x1bu)
    class Iload_2: OpCode(0x1cu)
    class Iload_3: OpCode(0x1du)
    class Imul: OpCode(0x68u)
    class Ineg: OpCode(0x74u)
    class Invokespecial(methodReferenceConstantInfo: MethodReferenceConstantInfo): OpCode(0xb7u, methodReferenceConstantInfo)
    class Invokevirtual(methodReferenceConstantInfo: MethodReferenceConstantInfo): OpCode(0xb6u, methodReferenceConstantInfo)
    class Ior: OpCode(0x80u)
    class Irem: OpCode(0x70u)
    class Ireturn: OpCode(0xacu), ReturnAnything
    class Ishl: OpCode(0x78u)
    class Ishr: OpCode(0x7au)
    class Istore(indexInsideLocalVariableArray: UByte): OpCode(0x36u, indexInsideLocalVariableArray)
    class Istore_0: OpCode(0x3bu)
    class Istore_1: OpCode(0x3cu)
    class Istore_2: OpCode(0x3du)
    class Istore_3: OpCode(0x3eu)
    class Isub: OpCode(0x64u)
    class Iushr: OpCode(0x7cu)
    class Ixor: OpCode(0x82u)
    class L2d: OpCode(0x8au)
    class L2f: OpCode(0x89u)
    class L2i: OpCode(0x88u)
    class Ladd: OpCode(0x61u)
    class Laload: OpCode(0x2fu)
    class Land: OpCode(0x7fu)
    class Lastore: OpCode(0x50u)
    class Lcmp: OpCode(0x94u)
    class Lconst_0: OpCode(0x09u)
    class Lconst_1: OpCode(0x0au)
    class Ldc(indexInConstantPool: UByte): OpCode(0x12u, indexInConstantPool)
    class Ldc2_w(longOrDoubleToLoad: ConstantInfo): OpCode(0x14u, longOrDoubleToLoad)
    class Ldc_w(indexInConstantPool: UShort): OpCode(0x13u, indexInConstantPool)
    class Ldiv: OpCode(0x6du)
    class Lload(indexInsideLocalVariableArray: UByte): OpCode(0x16u, indexInsideLocalVariableArray)
    class Lload_0: OpCode(0x1eu)
    class Lload_1: OpCode(0x1fu)
    class Lload_2: OpCode(0x20u)
    class Lload_3: OpCode(0x21u)
    class Lmul: OpCode(0x69u)
    class Lneg: OpCode(0x75u)
    class Lor: OpCode(0x81u)
    class Lrem: OpCode(0x71u)
    class Lreturn: OpCode(0xadu), ReturnAnything
    class Lshl: OpCode(0x79u)
    class Lshr: OpCode(0x7bu)
    class Lstore(indexInsideLocalVariableArray: UByte): OpCode(0x37u, indexInsideLocalVariableArray)
    class Lstore_0: OpCode(0x3fu)
    class Lstore_1: OpCode(0x40u)
    class Lstore_2: OpCode(0x41u)
    class Lstore_3: OpCode(0x42u)
    class Lsub: OpCode(0x65u)
    class Lushr: OpCode(0x7du)
    class Lxor: OpCode(0x83u)
    class New(classConstantInfo: ClassConstantInfo): OpCode(0xbbu, classConstantInfo)
    enum class ArrayType(code: UByte) {
        T_BOOLEAN(4u),
        T_CHAR(5u),
        T_FLOAT(6u),
        T_DOUBLE(7u),
        T_BYTE(8u),
        T_SHORT(9u),
        T_INT(10u),
        T_LONG(11u),
    }
    class Newarray(arrayType: ArrayType): OpCode(0xbcu, arrayType)
    class Nop: OpCode(0x00u)
    class Pop2: OpCode(0x58u)
    class Pop: OpCode(0x57u)
    class Return: OpCode(0xb1u), ReturnAnything
    class Saload: OpCode(0x35u)
    class Sastore: OpCode(0x56u)
    class Sipush(valueToPush: Short): OpCode(0x11u, valueToPush)
    class Swap: OpCode(0x5fu)
}