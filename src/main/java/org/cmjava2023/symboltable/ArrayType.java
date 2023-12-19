package org.cmjava2023.symboltable;

public class ArrayType implements Type {
    protected final Type arrayType;
    protected int dimensions;

    public ArrayType(Type arrayType) {
        this.arrayType = arrayType;
        this.dimensions = -1;
    }

    public ArrayType(Type arrayType, int dimensions) {
        this.arrayType = arrayType;
        this.dimensions = dimensions;
    }

    @Override
    public String getName() {
        return toString();
    }

    @Override
    public String toString() {
        return arrayType.getName() + "[]".repeat(dimensions);
    }
}
