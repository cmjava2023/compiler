package org.cmjava2023.util

import kotlin.experimental.or

class AccessModifierUtil {
    companion object {
        fun Iterable<Short>.bitwiseOrCombine(): Short{
            return this.reduce { combinedFlag, flag -> combinedFlag or flag }
        }
    }
}