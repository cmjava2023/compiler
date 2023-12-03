package org.cmjava2023.classfilespecification.attributeInfo

import org.cmjava2023.classfilespecification.constantpool.Utf8ConstantInfo

abstract class AbstractAttributeInfo(name: String) {
    val name = Utf8ConstantInfo(name)
}