package cmjava2023.helloworld;

public class Main {

    public static boolean carChecker(Car car) {
        System.out.println("Check car");
    }

    public static boolean carChecker2(void car) {
        System.out.println("Check car");
    }

    public static Plane plainChecker(Plane plane) {
        System.out.println("Check plane");
    }

    public static void main(String[] args) {
        System.out.println("Hello Car!");
    }

    public class Car {
        int size = 5;
    }
}