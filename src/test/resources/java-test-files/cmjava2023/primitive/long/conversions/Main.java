package org.cmjava2023;

public class Main {
    public static void main(String[] args) {
        long l = 10;

        double d = (double) l;

        float f = (float) l;

        int i = (int) l;

        // force load/store without index
        // by using enough local variables
        // that the index is outside the
        // range of store/load_<i> instructions
        long force_store = l;
        long force_load = force_store;
        // lconst exists in lconst_<1/0> variants,
        // use 1 to force usage of it
        long force_const = 1;

        System.out.println("l:");
        System.out.println(l);

        System.out.println("d:");
        System.out.println(d);

        System.out.println("f:");
        System.out.println(f);

        System.out.println("i:");
        System.out.println(i);

        System.out.println("force_load:");
        System.out.println(force_load);

        System.out.println("force_store:");
        System.out.println(force_store);

        System.out.println("force_const:");
        System.out.println(force_const);
    }
}
