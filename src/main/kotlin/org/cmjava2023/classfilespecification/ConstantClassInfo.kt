package org.cmjava2023.classfilespecification

class ConstantClassInfo(val nameConstant: ConstantUtf8Info): ConstantInfo(ConstantInfoTag.CONSTANT_Class) {
    override fun getInfo(): List<Byte> {
        TODO("Not yet implemented")     // nameConstant müsste Index zum ConstantPool Eintrag für den Namen sein. Index ist hier noch nicht bekannt

    }

}