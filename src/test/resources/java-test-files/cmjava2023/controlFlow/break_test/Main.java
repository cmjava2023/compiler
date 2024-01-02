package cmjava2023.controlFlow.break_test;

public class Main {
    public static void main(String[] args) {
        int i = 1;

        while (true) {
            System.out.println(i);

            if (i == 5) {
                break;
            }

            i++;
        }
    }
}