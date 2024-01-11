package org.cmjava2023.util

import org.cmjava2023.classfilespecification.constantpool.*
import org.cmjava2023.classfilespecification.constantpool.ConstantPoolConstants.*
import org.cmjava2023.classfilespecification.constantpool.ConstantPoolConstants.Companion.QUALIFIED_CLASSNAME_OF_STRING
import org.cmjava2023.classfilespecification.opCodes.OpCode
import org.cmjava2023.classfilespecification.opCodes.OpCodeOrPlaceHolder
import org.cmjava2023.classfilespecification.opCodes.PlaceHolderLoadingConstantInfo

class OpCodesToConcatStringLiteralAndVariableQuery {
    companion object {
        fun fetch(
            stringLiteralValue: String,
            opCodesOrPlaceHoldersOfRightExpression: List<OpCodeOrPlaceHolder>,
            constantPoolTypeOfRightExpression: String
        ): List<OpCodeOrPlaceHolder> {
            val stringBuilderClassConstantInfo = ClassConstantInfo(StringBuilder.QUALIFIED_CLASSNAME)

            return listOf(
                OpCode.New(stringBuilderClassConstantInfo),
                OpCode.Dup(),
                OpCode.Invokespecial(
                    MethodReferenceConstantInfo(
                        stringBuilderClassConstantInfo,
                        NameAndTypeConstantInfo(ConstantPoolConstants.CONSTRUCTOR_NAME, Type.createVoidMethod())
                    )
                ),
                PlaceHolderLoadingConstantInfo(StringConstantInfo(stringLiteralValue)),
                OpCode.Invokevirtual(
                    MethodReferenceConstantInfo(
                        stringBuilderClassConstantInfo,
                        NameAndTypeConstantInfo(StringBuilder.Method.APPEND,  Type.createMethodReturning1with2toNAsParameterTypes(StringBuilder.QUALIFIED_CLASSNAME, Type.createForQualifiedClassName(QUALIFIED_CLASSNAME_OF_STRING)))
                    )
                )
            )
                .plus(opCodesOrPlaceHoldersOfRightExpression)
                .plus(
                    OpCode.Invokevirtual(
                        MethodReferenceConstantInfo(
                            stringBuilderClassConstantInfo,
                            NameAndTypeConstantInfo(
                                StringBuilder.Method.TO_STRING,
                                Type.createMethodReturning1with2toNAsParameterTypes(Type.createForQualifiedClassName(QUALIFIED_CLASSNAME_OF_STRING), constantPoolTypeOfRightExpression)
                            )
                        )
                    )
                )
                .plus(
                    OpCode.Invokevirtual(
                        MethodReferenceConstantInfo(
                            stringBuilderClassConstantInfo,
                            NameAndTypeConstantInfo(StringBuilder.Method.TO_STRING, Type.createMethodReturning1with2toNAsParameterTypes(Type.createForQualifiedClassName(QUALIFIED_CLASSNAME_OF_STRING)))
                        )
                    )
                )
        }
    }
}