package cmjava2023.controlFlow.return_test;

public class Main {
    public static void main(String[] args) {
        int result = addNumbers(5, 7);
        System.out.println(result);

        printMessage("Hello, World!", true);
        printMessage("Hello, World!", false);
    }

    public static int addNumbers(int a, int b) {
        int sum = a + b;
        return sum;
    }

    public static void printMessage(String message, boolean shouldPrint) {
        if (shouldPrint) {
            System.out.println(message);
            return;
        }

        System.out.println("I will not print this");
    }
}