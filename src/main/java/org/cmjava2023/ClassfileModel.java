package org.cmjava2023;

public record ClassfileModel(
    short sizeOfConstantPool,
    byte[] constantPool,                    // Array of own class?
    short classAccessModifiers,
    short constantPoolIndex,
    short superClassIndexInConstantPool,
    short numberOfInterfaces,
    byte[] interfaceDefinitions,            // Array of own class?
    short numberOfFields,
    byte[] fieldDefinitions,                // Array of own class?
    short numberOfMethods,
    byte[] methodDefinitions,               // Array of own class?
    short attributesCount,
    byte[] attributeDefinitions             // Array of own class?
){}
