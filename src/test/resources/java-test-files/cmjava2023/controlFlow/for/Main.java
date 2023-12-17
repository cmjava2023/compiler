package cmjava2023.controlFlow.for;

public class Main {
    public static void main(String[] args) {
        for (int i = 1; i < 11; i++) {
            System.out.println(i);
        }

        for (int i = 0; i < 10; i += 2) {
            System.out.println(i);
        }

        int[] numbers =
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int item : numbers) {
            System.out.println(item);
        }
    }
}