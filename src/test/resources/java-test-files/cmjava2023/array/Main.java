package cmjava2023.array;

public class Main {
    public static void main(String[] args) {
        String[] o = {"Hello World"};

        byte[] by = {10, 11};

        boolean[] boo = {true, false};

        char[] c = {10, 11};

        double[] d = {10, 11};

        float[] f = {10, 11};

        int[] i = {10, 11};

        long[] l = {10, 11};

        int[][] i2 = new int[10][10];

        short[] s = {10, 11};

        System.out.println("o:");
        System.out.println(o[0]);

        System.out.println("by:");
        System.out.println(by[0]);

        System.out.println("boo:");
        System.out.println(boo[0]);

        System.out.println("c:");
        System.out.println(c[0]);

        System.out.println("d:");
        System.out.println(d[0]);

        System.out.println("f:");
        System.out.println(f[0]);

        System.out.println("i:");
        System.out.println(i[0]);

        System.out.println("l:");
        System.out.println(l[0]);

        System.out.println("i2:");
        // default value int == 0,
        // i.e. this should print 0
        System.out.println(i2[0][0]);

        System.out.println("s:");
        System.out.println(s[0]);

        System.out.println("len:");
        System.out.println(i2.length);
    }
}
