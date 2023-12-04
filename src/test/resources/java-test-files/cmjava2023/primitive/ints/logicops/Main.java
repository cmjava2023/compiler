package cmjava2023.primitive;

public class Main {
    public static void main(String[] args) {
        int i = 4;
        // bipush
        int eight = 8;
        int sipush = 257;
        // iand
        int i2 = i & 2;
        // ior
        int i3 = i | 2;
        // ishl
        int i4 = eight << 1;
        // ishr
        int i5 = eight >> 1;
        // iushr
        int i6 = eight >>> 1;
        // ixor
        int i7 = i2 ^ 2 ;

        System.out.println("i:");
        System.out.println(i);

        System.out.println("eight:");
        System.out.println(eight);

        System.out.println("i2:");
        System.out.println(i2);

        System.out.println("i3:");
        System.out.println(i3);

        System.out.println("i4:");
        System.out.println(i4);

        System.out.println("i5:");
        System.out.println(i5);

        System.out.println("i6:");
        System.out.println(i6);

        System.out.println("i7:");
        System.out.println(i7);
    }
}

