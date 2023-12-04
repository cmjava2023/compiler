package org.cmjava2023;

public class Main {
    public static void main(String[] args) {
        int i = 10;
        // i2b
        byte b = (byte) i;
        // i2c
        char c = (char) i;
        // i2d
        double d = (double) i;
        // i2f
        float f = (float) i;
        // i2l
        long l = (long) i;
        // i2s
        short s = (short) i;
        // iload without index
        int force_load = s;

        System.out.println("i:");
        System.out.println(i);

        System.out.println("b:");
        System.out.println(b);

        System.out.println("c:");
        System.out.println(c);

        System.out.println("d:");
        System.out.println(d);

        System.out.println("f:");
        System.out.println(f);

        System.out.println("l:");
        System.out.println(l);

        System.out.println("s:");
        System.out.println(s);

        System.out.println("force_load:");
        System.out.println(force_load);
    }
}
