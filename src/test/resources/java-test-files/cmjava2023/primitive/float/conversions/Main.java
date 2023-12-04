package org.cmjava2023;

public class Main {
    public static void main(String[] args) {
        float f = 10;

        double d = (double) f;

        long l = (long) f;

        int i = (int) f;

        // force load/store without index
        // by using enough local variables
        // that the index is outside the
        // range of store/load_<i> instructions
        float force_store = f;
        float force_load = force_store;
        // lconst exists in lconst_<1/0> variants,
        // use 1 to force usage of it
        float force_const = 1;

        System.out.println("f:");
        System.out.println(f);

        System.out.println("d:");
        System.out.println(d);

        System.out.println("l:");
        System.out.println(l);

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
