package org.cmjava2023.classfilespecification.attributeInfo

import org.cmjava2023.classfilespecification.constantpool.ConstantPoolEntry

abstract class AbstractAttributeInfo(name: String) {
    val name = ConstantPoolEntry.Utf8Constant(name)
}