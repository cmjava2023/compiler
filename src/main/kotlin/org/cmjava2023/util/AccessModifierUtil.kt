package org.cmjava2023.util

class AccessModifierUtil {
    companion object {
        fun Iterable<UShort>.bitwiseOrCombine(): UShort{
            return this.reduce { combinedFlag, flag -> combinedFlag or flag }
        }
    }
}