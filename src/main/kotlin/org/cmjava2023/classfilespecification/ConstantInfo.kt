package org.cmjava2023.classfilespecification

abstract class ConstantInfo(val tag: ConstantInfoTag){
    abstract fun getInfo(): List<Byte>
}
