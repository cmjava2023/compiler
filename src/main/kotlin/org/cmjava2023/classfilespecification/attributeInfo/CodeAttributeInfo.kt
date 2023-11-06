package org.cmjava2023.classfilespecification.attributeInfo

import org.cmjava2023.classfilespecification.constantpool.Utf8ConstantInfo

class CodeAttributeInfo(): AbstractAttributeInfo(Utf8ConstantInfo("Code")) {
    // TODO implement code structure https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.7.3
}