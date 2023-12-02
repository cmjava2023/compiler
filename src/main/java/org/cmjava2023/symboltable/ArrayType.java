package org.cmjava2023.symboltable;

public class ArrayType implements Type {
    protected final Type arrayType;
    protected final int maxArraySize;

    public ArrayType(Type arrayType) {
        this.arrayType = arrayType;
        this.maxArraySize = -1;
    }

    public ArrayType(Type arrayType, int maxArraySize)
    {
        this.arrayType = arrayType;
        this.maxArraySize = maxArraySize;
    }

    @Override
    public String getName() {
        return toString();
    }

    @Override
    public String toString() {
        return arrayType.getName() +"[]";
    }
}
