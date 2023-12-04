package cmjava2023.primitive;

public class Main {
    public static void main(String[] args) {
        long l = 4;
        long eight = 8;
        // land
        long l2 = l & 2;
        // lor
        long l3 = l | 2;
        // lshl
        long l4 = eight << 1;
        // lshr
        long l5 = eight >> 1;
        // lushr
        long l6 = eight >>> 1;
        // ixor
        long l7 = l2 ^ 2 ;

        System.out.println("l:");
        System.out.println(l);

        System.out.println("eight:");
        System.out.println(eight);

        System.out.println("l2:");
        System.out.println(l2);

        System.out.println("l3:");
        System.out.println(l3);

        System.out.println("l4:");
        System.out.println(l4);

        System.out.println("l5:");
        System.out.println(l5);

        System.out.println("l6:");
        System.out.println(l6);

        System.out.println("l7:");
        System.out.println(l7);
    }
}

