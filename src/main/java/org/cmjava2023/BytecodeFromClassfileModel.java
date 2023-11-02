package org.cmjava2023;

import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HexFormat;
import java.util.List;

public class BytecodeFromClassfileModel {

    static final int magicNumber = 0xCAFEBABE;
    static final short minorVersion = 0x0;
    static final short majorVersion = 0x00000034;

    public Byte[] Generate(ClassfileModel model){


        var array = new ArrayList<Byte>(toObjects(ByteBuffer.allocate(4).putInt(magicNumber).array()));
        array.addAll(toObjects(ByteBuffer.allocate(2).putShort(minorVersion).array()));
        array.addAll(toObjects(ByteBuffer.allocate(2).putShort(majorVersion).array()));

        // TODO: Fix java
        var result = new Byte[array.size()];
        array.toArray(result);
        return result;
    }

    List<Byte> toObjects(byte[] bytesPrim) {

        var bytes = new ArrayList<Byte>();
        for(var item : bytesPrim){
            bytes.add(item);
        }
        return bytes;
    }
}
