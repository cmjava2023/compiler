package org.cmjava2023.symboltable;

public class InvalidType implements Type{
    private final String name;

    public InvalidType(String name) {
        this.name = name;
    }
    @Override
    public String getName() {
        return name;
    }
}
