package org.cmjava2023.classfilespecification.attributeInfo

import org.cmjava2023.classfilespecification.constantpool.Utf8ConstantPoolEntry

abstract class AbstractAttributeInfo(name: String) {
    val name = Utf8ConstantPoolEntry(name)
}