package org.cmjava2023.symboltable;

public class ArrayType implements Type {
    protected final Type elemType;
    protected final int numElems;

    public ArrayType(Type elemType) {
        this.elemType = elemType;
        this.numElems = -1;
    }

    public ArrayType(Type elemType, int numElems)
    {
        this.elemType = elemType;
        this.numElems = numElems;
    }

    @Override
    public String getName() {
        return toString();
    }

    @Override
    public String toString() {
        return elemType+"[]";
    }
}
