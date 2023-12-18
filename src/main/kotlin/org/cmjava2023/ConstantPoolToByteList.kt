package org.cmjava2023

import org.cmjava2023.util.ByteListUtil.Companion.add
import org.cmjava2023.classfilespecification.MethodInfo
import org.cmjava2023.classfilespecification.OpCode
import org.cmjava2023.classfilespecification.attributeInfo.CodeAttributeInfo
import org.cmjava2023.classfilespecification.constantpool.*
import org.cmjava2023.symboltable.Variable
import org.cmjava2023.util.AccessModifierUtil.Companion.bitwiseOrCombine

class ConstantPoolToByteList {

    data class ClassFileBytes(
        val constantPoolBytes: List<Byte>,
        val constantPoolItemCount: UShort,
        val methodInfosBytes: List<Byte>,
        val methodInfoCount: UShort
    )
    
    private var lastOccupiedConstantPoolIndex: UShort = 0u
    private var constantPoolBytes = mutableListOf<Byte>()
    private val methodInfosBytes = mutableListOf<Byte>()

    fun mapToClassFileBytes(constantPool: List<ConstantInfo>, methodInfos: List<MethodInfo>): ClassFileBytes {
        constantPoolBytes = mutableListOf()
        lastOccupiedConstantPoolIndex = 0u
        for (constantInfo in constantPool) {
            when (constantInfo) {
                is ClassConstantInfo -> addClassConstantInfoAndGetStartIndex(constantInfo)
            }
        }
        for (methodInfo in methodInfos) {
            addMethodInfo(methodInfo)
        }

        return ClassFileBytes(
            constantPoolBytes,
            (lastOccupiedConstantPoolIndex + 1u).toUShort(),
            methodInfosBytes,
            methodInfos.size.toUShort()
        )
    }

    private fun addMethodInfo(methodInfo: MethodInfo) {
        methodInfosBytes.add(
            methodInfo.accessModifiers.map { it.value }.bitwiseOrCombine()
        )
        val methodNameIndex = addUtf8ConstantAndGetStartIndex(methodInfo.name)
        methodInfosBytes.add(methodNameIndex)

        val methodTypeIndex = addUtf8ConstantAndGetStartIndex(methodInfo.typeDescriptor)
        methodInfosBytes.add(methodTypeIndex)

        methodInfosBytes.add(methodInfo.attributes.size.toUShort())

        val codeAttributeInfo = methodInfo.attributes.filterIsInstance<CodeAttributeInfo>().singleOrNull()
        if (codeAttributeInfo != null) {
            val codeAttributeNameIndex = addUtf8ConstantAndGetStartIndex(codeAttributeInfo.name)
            addCodeAttributeBytesToMethodInfoBytes(codeAttributeInfo.code, codeAttributeNameIndex)
        } else {
            throw NotImplementedError()
        }
    }

    private fun addCodeAttributeBytesToMethodInfoBytes(code: List<OpCode>, codeAttributeNameIndex: UShort) {
        val attributeBytesCountedForLength = mutableListOf<Byte>()
        
        val localVariables = mutableListOf<Variable>()

        val maxStackSize: UShort = 2u
        attributeBytesCountedForLength.add(maxStackSize)

        val maxLocalVarSize: UShort = 1u
        attributeBytesCountedForLength.add(maxLocalVarSize)

        val codeBytes = code.flatMap { constructBytesOfOpcode(it, localVariables) }
        val sizeOfCode = codeBytes.size.toUInt()
        attributeBytesCountedForLength.add(sizeOfCode)
        attributeBytesCountedForLength.addAll(codeBytes)

        val exceptionTableLength: UShort = 0u
        attributeBytesCountedForLength.add(exceptionTableLength)
        val attributesCount: UShort = 0u
        attributeBytesCountedForLength.add(attributesCount)

        methodInfosBytes.add(codeAttributeNameIndex)
        val sizeOfAttribute = attributeBytesCountedForLength.size.toUInt()
        methodInfosBytes.add(sizeOfAttribute)
        methodInfosBytes.addAll(attributeBytesCountedForLength)
    }

    private fun addUtf8ConstantAndGetStartIndex(utf8ConstantInfo: Utf8ConstantInfo): UShort {
        val startIndex = startConstantInfoWithTagAndGetIndex(utf8ConstantInfo.tag)
        constantPoolBytes.addAll(utf8ConstantInfo.getStringLengthBytes())
        constantPoolBytes.addAll(utf8ConstantInfo.getUtf8Bytes())
        return startIndex
    }

    private fun startConstantInfoWithTagAndGetIndex(constantInfoTag: ConstantInfoTag): UShort {
        constantPoolBytes.add(constantInfoTag.value)
        lastOccupiedConstantPoolIndex++
        return lastOccupiedConstantPoolIndex
    }

    private fun addClassConstantInfoAndGetStartIndex(constantInfo: ClassConstantInfo): UShort {
        val nameIndex = addUtf8ConstantAndGetStartIndex(constantInfo.name)

        val startIndex = startConstantInfoWithTagAndGetIndex(constantInfo.tag)
        constantPoolBytes.add(nameIndex)

        return startIndex
    }

    private fun addStringConstantInfoAndGetStartIndex(constantInfo: StringConstantInfo): UShort {
        val valueIndex = addUtf8ConstantAndGetStartIndex(constantInfo.value)

        val startIndex = startConstantInfoWithTagAndGetIndex(constantInfo.tag)
        constantPoolBytes.add(valueIndex)

        return startIndex
    }

    private fun addIntegerConstantInfoAndGetStartIndex(constantInfo: IntegerConstantInfo): UShort {
        val startIndex = startConstantInfoWithTagAndGetIndex(constantInfo.tag)
        constantPoolBytes.add(constantInfo.value)

        return startIndex
    }

    private fun addLongConstantInfoAndGetStartIndex(constantInfo: LongConstantInfo): UShort {
        val startIndex = startConstantInfoWithTagAndGetIndex(constantInfo.tag)
        constantPoolBytes.add(constantInfo.value)

        return startIndex
    }

    private fun addFloatConstantInfoAndGetStartIndex(constantInfo: FloatConstantInfo): UShort {
        val startIndex = startConstantInfoWithTagAndGetIndex(constantInfo.tag)
        constantPoolBytes.add(constantInfo.value.toBits())

        return startIndex
    }

    private fun addDoubleConstantInfoAndGetStartIndex(constantInfo: DoubleConstantInfo): UShort {
        val startIndex = startConstantInfoWithTagAndGetIndex(constantInfo.tag)
        constantPoolBytes.add(constantInfo.value.toBits())

        return startIndex
    }

    private fun addNameAndTypeConstantInfoAndGetStartIndex(constantInfo: NameAndTypeConstantInfo): UShort {
        val nameIndex = addUtf8ConstantAndGetStartIndex(constantInfo.name)
        val typeIndex = addUtf8ConstantAndGetStartIndex(constantInfo.type)

        val startIndex = startConstantInfoWithTagAndGetIndex(constantInfo.tag)
        constantPoolBytes.add(nameIndex)
        constantPoolBytes.add(typeIndex)

        return startIndex
    }

    private fun addReferenceConstantInfoAndGetStartIndex(constantInfo: ReferenceConstantInfo): UShort {
        val classIndex = addClassConstantInfoAndGetStartIndex(constantInfo.classConstantInfo)
        val nameAndTypeIndex = addNameAndTypeConstantInfoAndGetStartIndex(constantInfo.nameAndTypeConstantInfo)

        val startIndex = startConstantInfoWithTagAndGetIndex(constantInfo.tag)
        constantPoolBytes.add(classIndex)
        constantPoolBytes.add(nameAndTypeIndex)

        return startIndex
    }

    private fun addConstantInfo(constantInfo: ConstantInfo): UShort {
        return when (constantInfo) {
            is ReferenceConstantInfo -> addReferenceConstantInfoAndGetStartIndex(constantInfo)
            is StringConstantInfo -> addStringConstantInfoAndGetStartIndex(constantInfo)
            is ClassConstantInfo -> addClassConstantInfoAndGetStartIndex(constantInfo)
            is IntegerConstantInfo -> addIntegerConstantInfoAndGetStartIndex(constantInfo)
            is LongConstantInfo -> addLongConstantInfoAndGetStartIndex(constantInfo)
            is FloatConstantInfo -> addFloatConstantInfoAndGetStartIndex(constantInfo)
            is DoubleConstantInfo -> addDoubleConstantInfoAndGetStartIndex(constantInfo)
            else -> throw NotImplementedError(constantInfo.javaClass.name)
        }
    }
    
    private fun getNumberOfVariable(localVariables: MutableList<Variable>, localVariable: Variable): UByte {
        var index = localVariables.indexOf(localVariable)
        if (index == -1)  {
            index = localVariables.size
            localVariables.add(localVariable)
        }
        return index.toUByte()
    }
    
    private fun resolveMultiplePossibleOpCode(opCode: OpCode.MultiplePossibleOpcode, localVariables: MutableList<Variable>): OpCode {
        val transformedExceptLoadConstant = when(opCode) {
            is OpCode.LoadConstant -> opCode
            is OpCode.IntConstant -> transformIntConstant(opCode)
            is OpCode.LongConstant -> transformLongConstant(opCode)
            is OpCode.FloatConstant -> transformFloatConstant(opCode)
            is OpCode.DoubleConstant -> transformDoubleConstant(opCode)
            is OpCode.StoreInt -> transformStoreInt(opCode, localVariables)
            is OpCode.StoreLong -> transformStoreLong(opCode, localVariables)
            is OpCode.StoreFloat -> transformStoreFloat(opCode, localVariables)
            is OpCode.StoreDouble -> transformStoreDouble(opCode, localVariables)
            is OpCode.LoadInt -> transformLoadInt(opCode, localVariables)
            is OpCode.LoadLong -> transformLoadLong(opCode, localVariables)
            is OpCode.LoadFloat -> transformLoadFloat(opCode, localVariables)
            is OpCode.LoadDouble -> transformLoadDouble(opCode, localVariables)
            else -> throw NotImplementedError(opCode.javaClass.name)
        }
        
        return if (transformedExceptLoadConstant is OpCode.LoadConstant) {
            val index = addConstantInfo(transformedExceptLoadConstant.constantInfo)
            if (index <= UByte.MAX_VALUE) {
                OpCode.Ldc(index.toUByte())
            } else {
                OpCode.Ldc_w(index)
            }
        } else {
            transformedExceptLoadConstant
        }
    }

    private fun transformLoadDouble(opCode: OpCode.LoadDouble, localVariables: MutableList<Variable>): OpCode {
        return when (val index = getNumberOfVariable(localVariables, opCode.variableSymbol)) {
            0.toUByte() -> OpCode.Dload_0()
            1.toUByte() -> OpCode.Dload_1()
            2.toUByte() -> OpCode.Dload_2()
            3.toUByte() -> OpCode.Dload_3()
            else -> OpCode.Dload(index)
        }
    }

    private fun transformLoadFloat(opCode: OpCode.LoadFloat, localVariables: MutableList<Variable>): OpCode {
        return when (val index = getNumberOfVariable(localVariables, opCode.variableSymbol)) {
            0.toUByte() -> OpCode.Fload_0()
            1.toUByte() -> OpCode.Fload_1()
            2.toUByte() -> OpCode.Fload_2()
            3.toUByte() -> OpCode.Fload_3()
            else -> OpCode.Fload(index)
        }
    }

    private fun transformLoadLong(opCode: OpCode.LoadLong, localVariables: MutableList<Variable>): OpCode {
        return when (val index = getNumberOfVariable(localVariables, opCode.variableSymbol)) {
            0.toUByte() -> OpCode.Lload_0()
            1.toUByte() -> OpCode.Lload_1()
            2.toUByte() -> OpCode.Lload_2()
            3.toUByte() -> OpCode.Lload_3()
            else -> OpCode.Lload(index)
        }
    }

    private fun transformLoadInt(opCode: OpCode.LoadInt, localVariables: MutableList<Variable>): OpCode {
        return when (val index = getNumberOfVariable(localVariables, opCode.variableSymbol)) {
            0.toUByte() -> OpCode.Iload_0()
            1.toUByte() -> OpCode.Iload_1()
            2.toUByte() -> OpCode.Iload_2()
            3.toUByte() -> OpCode.Iload_3()
            else -> OpCode.Iload(index)
        }
    }

    private fun transformStoreDouble(opCode: OpCode.StoreDouble, localVariables: MutableList<Variable>): OpCode {
        return when (val index = getNumberOfVariable(localVariables, opCode.variableSymbol)) {
            0.toUByte() -> OpCode.Dstore_0()
            1.toUByte() -> OpCode.Dstore_1()
            2.toUByte() -> OpCode.Dstore_2()
            3.toUByte() -> OpCode.Dstore_3()
            else -> OpCode.Dstore(index)
        }
    }

    private fun transformStoreFloat(opCode: OpCode.StoreFloat, localVariables: MutableList<Variable>): OpCode {
        return when (val index = getNumberOfVariable(localVariables, opCode.variableSymbol)) {
            0.toUByte() -> OpCode.Fstore_0()
            1.toUByte() -> OpCode.Fstore_1()
            2.toUByte() -> OpCode.Fstore_2()
            3.toUByte() -> OpCode.Fstore_3()
            else -> OpCode.Fstore(index)
        }
    }

    private fun transformStoreLong(opCode: OpCode.StoreLong, localVariables: MutableList<Variable>): OpCode {
        return when (val index = getNumberOfVariable(localVariables, opCode.variableSymbol)) {
            0.toUByte() -> OpCode.Lstore_0()
            1.toUByte() -> OpCode.Lstore_1()
            2.toUByte() -> OpCode.Lstore_2()
            3.toUByte() -> OpCode.Lstore_3()
            else -> OpCode.Lstore(index)
        }
    }

    private fun transformStoreInt(opCode: OpCode.StoreInt, localVariables: MutableList<Variable>): OpCode {
        return when (val index = getNumberOfVariable(localVariables, opCode.variableSymbol)) {
            0.toUByte() -> OpCode.Istore_0()
            1.toUByte() -> OpCode.Istore_1()
            2.toUByte() -> OpCode.Istore_2()
            3.toUByte() -> OpCode.Istore_3()
            else -> OpCode.Istore(index)
        }
    }

    private fun transformDoubleConstant(opCode: OpCode.DoubleConstant): OpCode = when (opCode.double) {
        0.0 -> OpCode.Dconst_0()
        1.0 -> OpCode.Dconst_1()
        else -> OpCode.LoadConstant(DoubleConstantInfo(opCode.double))
    }

    private fun transformFloatConstant(opCode: OpCode.FloatConstant): OpCode = when (opCode.float) {
        0f -> OpCode.Fconst_0()
        1f -> OpCode.Fconst_1()
        2f -> OpCode.Fconst_2()
        else -> OpCode.LoadConstant(FloatConstantInfo(opCode.float))
    }

    private fun transformIntConstant(opCode: OpCode.IntConstant) = when (opCode.int) {
        -1 -> OpCode.Iconst_m1()
        0 -> OpCode.Iconst_0()
        1 -> OpCode.Iconst_1()
        2 -> OpCode.Iconst_2()
        3 -> OpCode.Iconst_3()
        4 -> OpCode.Iconst_4()
        5 -> OpCode.Iconst_5()
        else -> if (opCode.int >= Byte.MIN_VALUE && opCode.int <= Byte.MAX_VALUE) {
            OpCode.Bipush(opCode.int.toByte())
        } else if (opCode.int >= Short.MIN_VALUE && opCode.int <= Short.MAX_VALUE) {
            OpCode.Sipush(opCode.int.toShort())
        } else {
            OpCode.LoadConstant(IntegerConstantInfo(opCode.int))
        }
    }

    private fun transformLongConstant(opCode: OpCode.LongConstant) = when (opCode.long) {
        0L -> OpCode.Lconst_0()
        1L -> OpCode.Lconst_1()
        else -> OpCode.LoadConstant(LongConstantInfo(opCode.long))
    }

    private fun constructBytesOfOpcode(opCode: OpCode, localVariables: MutableList<Variable>): List<Byte> {
        var resultOpCode = opCode
        val result = mutableListOf<Byte>()
        if (opCode is OpCode.MultiplePossibleOpcode) {
            resultOpCode = resolveMultiplePossibleOpCode(opCode, localVariables)
        }
        result.add(resultOpCode.opCodeValue)
        for (value in resultOpCode.values) {
            when (value) {
                is UShort -> result.add(value)
                is UByte -> result.add(value)
                is Byte -> result.add(value)
                is Short -> result.add(value)
                is ConstantInfo -> result.add(addConstantInfo(value))
                else -> throw NotImplementedError(value.javaClass.name)
            }
        }
        return result
    }
}