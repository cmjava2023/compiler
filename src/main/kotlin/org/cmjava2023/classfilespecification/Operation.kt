package org.cmjava2023.classfilespecification

import org.cmjava2023.classfilespecification.constantpool.ConstantPoolEntry
import org.cmjava2023.parsedClassFileDataToBytes.ConstantPoolBuilder
import org.cmjava2023.placeHolders.PlaceHolder
import org.cmjava2023.util.ByteListUtil.Companion.add
import kotlin.reflect.KClass

/**
 * See https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html
 * https://en.wikipedia.org/wiki/List_of_Java_bytecode_instructions
 */
@Suppress("unused", "ClassName", "SpellCheckingInspection")
abstract class Operation(vararg val operands: Any): PlaceHolder {

    private val opCodeValue:UByte = classToOpCodeValueMap.getValue(this::class)

    fun toBytes(constantPoolBuilder: ConstantPoolBuilder): List<Byte> {
        val result = mutableListOf<Byte>()
        result.add(opCodeValue)
        for (value in operands) {
            when (value) {
                is UShort -> result.add(value)
                is UByte -> result.add(value)
                is Byte -> result.add(value)
                is Short -> result.add(value)
                is ConstantPoolEntry -> result.add(constantPoolBuilder.getIndexByResolvingOrAdding(value))
                is ArrayType -> result.add(value.code)
                else -> throw NotImplementedError(value.javaClass.name)
            }
        }
        return result
    }

    interface ReturningOpCode
    interface OpCodeWithConstantPoolIndexParameter

    class Aaload: Operation()
    class Aastore: Operation()
    class Aconst_null: Operation()
    class Aload(indexInsideLocalVariableArray: UByte): Operation(indexInsideLocalVariableArray)
    class Aload_0: Operation()
    class Aload_1: Operation()
    class Aload_2: Operation()
    class Aload_3: Operation()
    class Anewarray(classConstantInfo: ConstantPoolEntry.ClassConstant): Operation(classConstantInfo)
    class Areturn: Operation(), ReturningOpCode
    class Arraylength: Operation()
    class Astore(indexInsideLocalVariableArray: UByte): Operation(indexInsideLocalVariableArray)
    class Astore_0: Operation()
    class Astore_1: Operation()
    class Astore_2: Operation()
    class Astore_3: Operation()
    class Athrow: Operation()
    class Baload: Operation()
    class Bastore: Operation()
    class Bipush(byte: Byte): Operation(byte)
    class Caload: Operation()
    class Castore: Operation()
    class D2i: Operation()
    class D2f: Operation()
    class D2l: Operation()
    class Dadd: Operation()
    class Daload: Operation()
    class Dastore: Operation()
    class Dcompg: Operation()
    class Dcompl: Operation()
    class Dconst_0: Operation()
    class Dconst_1: Operation()
    class Ddiv: Operation()
    class Dload(indexInsideLocalVariableArray: UByte): Operation(indexInsideLocalVariableArray)
    class Dload_0: Operation()
    class Dload_1: Operation()
    class Dload_2: Operation()
    class Dload_3: Operation()
    class Dmul: Operation()
    class Dneg: Operation()
    class Drem: Operation()
    class Dreturn: Operation(), ReturningOpCode
    class Dstore(indexInsideLocalVariableArray: UByte): Operation(indexInsideLocalVariableArray)
    class Dstore_0: Operation()
    class Dstore_1: Operation()
    class Dstore_2: Operation()
    class Dstore_3: Operation()
    class Dsub: Operation()
    class Dup2: Operation()
    class Dup2_x1: Operation()
    class Dup2_x2: Operation()
    class Dup: Operation()
    class Dup_x1: Operation()
    class Dup_x2: Operation()
    class F2d: Operation()
    class F2i: Operation()
    class F2l: Operation()
    class Fadd: Operation()
    class Faload: Operation()
    class Fastore: Operation()
    class Fcmpg: Operation()
    class Fcmpl: Operation()
    class Fconst_0: Operation()
    class Fconst_1: Operation()
    class Fconst_2: Operation()
    class Fdiv: Operation()
    class Fload(indexInsideLocalVariableArray: UByte): Operation(indexInsideLocalVariableArray)
    class Fload_0: Operation()
    class Fload_1: Operation()
    class Fload_2: Operation()
    class Fload_3: Operation()
    class Fmul: Operation()
    class Fneg: Operation()
    class Frem: Operation()
    class Freturn: Operation(), ReturningOpCode
    class Fstore(indexInsideLocalVariableArray: UByte): Operation(indexInsideLocalVariableArray)
    class Fstore_0: Operation()
    class Fstore_1: Operation()
    class Fstore_2: Operation()
    class Fstore_3: Operation()
    class Fsub: Operation()
    class Getstatic(fieldReferenceConstantInfo: ConstantPoolEntry.FieldReferenceConstant): Operation(fieldReferenceConstantInfo)
    class Goto(offset: Short): Operation(offset)
    class I2b: Operation()
    class I2c: Operation()
    class I2d: Operation()
    class I2f: Operation()
    class I2l: Operation()
    class I2s: Operation()
    class Iadd: Operation()
    class Iaload: Operation()
    class Iand: Operation()
    class Iastore: Operation()
    class Iconst_0: Operation()
    class Iconst_1: Operation()
    class Iconst_2: Operation()
    class Iconst_3: Operation()
    class Iconst_4: Operation()
    class Iconst_5: Operation()
    class Iconst_m1: Operation()
    class Idiv: Operation()
    class Ifeq(opCodeAddressOffset: Short): Operation(opCodeAddressOffset)
    class Ifne(opCodeAddressOffset: Short): Operation(opCodeAddressOffset)
    class Iflt(opCodeAddressOffset: Short): Operation(opCodeAddressOffset)
    class Ifge(opCodeAddressOffset: Short): Operation(opCodeAddressOffset)
    class Ifgt(opCodeAddressOffset: Short): Operation(opCodeAddressOffset)
    class Ifle(opCodeAddressOffset: Short): Operation(opCodeAddressOffset)
    class If_icmpeq(opCodeAddressOffset: Short): Operation(opCodeAddressOffset)
    class If_icmpne(opCodeAddressOffset: Short): Operation(opCodeAddressOffset)
    class If_icmplt(opCodeAddressOffset: Short): Operation(opCodeAddressOffset)
    class If_icmpge(opCodeAddressOffset: Short): Operation(opCodeAddressOffset)
    class If_icmpgt(opCodeAddressOffset: Short): Operation(opCodeAddressOffset)
    class If_icmple(opCodeAddressOffset: Short): Operation(opCodeAddressOffset)
    class Iinc(indexInsideLocalVariableArray: UByte, incrementBy: Byte): Operation(indexInsideLocalVariableArray, incrementBy)
    class Iload(indexInsideLocalVariableArray: UByte): Operation(indexInsideLocalVariableArray)
    class Iload_0: Operation()
    class Iload_1: Operation()
    class Iload_2: Operation()
    class Iload_3: Operation()
    class Imul: Operation()
    class Ineg: Operation()
    class Invokespecial(methodReferenceConstantInfo: ConstantPoolEntry.MethodReferenceConstant): Operation(methodReferenceConstantInfo)
    class Invokevirtual(methodReferenceConstantInfo: ConstantPoolEntry.MethodReferenceConstant): Operation(methodReferenceConstantInfo)
    class Ior: Operation()
    class Irem: Operation()
    class Ireturn: Operation(), ReturningOpCode
    class Ishl: Operation()
    class Ishr: Operation()
    class Istore(indexInsideLocalVariableArray: UByte): Operation(indexInsideLocalVariableArray)
    class Istore_0: Operation()
    class Istore_1: Operation()
    class Istore_2: Operation()
    class Istore_3: Operation()
    class Isub: Operation()
    class Iushr: Operation()
    class Ixor: Operation()
    class L2d: Operation()
    class L2f: Operation()
    class L2i: Operation()
    class Ladd: Operation()
    class Laload: Operation()
    class Land: Operation()
    class Lastore: Operation()
    class Lcmp: Operation()
    class Lconst_0: Operation()
    class Lconst_1: Operation()
    class Ldc(indexInConstantPool: UByte): Operation(indexInConstantPool), OpCodeWithConstantPoolIndexParameter
    class Ldc2_w(longOrDoubleToLoad: ConstantPoolEntry): Operation(longOrDoubleToLoad)
    class Ldc_w(indexInConstantPool: UShort): Operation(indexInConstantPool), OpCodeWithConstantPoolIndexParameter
    class Ldiv: Operation()
    class Lload(indexInsideLocalVariableArray: UByte): Operation(indexInsideLocalVariableArray)
    class Lload_0: Operation()
    class Lload_1: Operation()
    class Lload_2: Operation()
    class Lload_3: Operation()
    class Lmul: Operation()
    class Lneg: Operation()
    class Lor: Operation()
    class Lrem: Operation()
    class Lreturn: Operation(), ReturningOpCode
    class Lshl: Operation()
    class Lshr: Operation()
    class Lstore(indexInsideLocalVariableArray: UByte): Operation(indexInsideLocalVariableArray)
    class Lstore_0: Operation()
    class Lstore_1: Operation()
    class Lstore_2: Operation()
    class Lstore_3: Operation()
    class Lsub: Operation()
    class Lushr: Operation()
    class Lxor: Operation()
    class Multianewarray(classConstantInfo: ConstantPoolEntry.ClassConstant, dimensions: UByte): Operation(classConstantInfo, dimensions)
    class New(classConstantInfo: ConstantPoolEntry.ClassConstant): Operation(classConstantInfo)
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
    class Newarray(arrayType: ArrayType): Operation(arrayType)
    class Nop: Operation()
    class Pop2: Operation()
    class Pop: Operation()
    class Return: Operation(), ReturningOpCode
    class Saload: Operation()
    class Sastore: Operation()
    class Sipush(valueToPush: Short): Operation(valueToPush)
    class Swap: Operation()

    companion object {
        val classToOpCodeValueMap = mapOf<KClass<*>, UByte>(
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
    }
}