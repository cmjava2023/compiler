package cmjava2023.primitive.doubles.conversions;

public class Main {
    public static void main(String[] args) {
        double d = 10.0;

        float f = (float) d;

        int i = (int) d;

        long l = (long) d;

        // force load/store without index
        // by using enough local variables
        // that the index is outside the
        // range of store/load_<i> instructions
        double force_store = d;
        double force_load = force_store;
        // dconst exists in dconst_<1/0> variants,
        // use 1 to force usage of it
        double force_const = 1.0;

        System.out.println("d:");
        System.out.println(d);

        System.out.println("f:");
        System.out.println(f);

        System.out.println("k:");
        System.out.println(i);

        System.out.println("l:");
        System.out.println(l);

        System.out.println("force_load:");
        System.out.println(force_load);

        System.out.println("force_store:");
        System.out.println(force_store);

        System.out.println("force_const:");
        System.out.println(force_const);
    }
}

