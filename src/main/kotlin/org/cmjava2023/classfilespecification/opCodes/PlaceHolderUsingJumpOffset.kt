package org.cmjava2023.classfilespecification.opCodes

interface PlaceHolderUsingJumpOffset: PlaceHolder {
    class Continue: PlaceHolderUsingJumpOffset
    class Break: PlaceHolderUsingJumpOffset
}