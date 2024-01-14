package cmjava2023.oop.overloading;

// Class to demonstrate method overloading
class OverloadingExample {

    // Method overloading by changing the number of parameters
    void display(int a) {
        System.out.println("Single parameter: " + a);
    }

    void display(int a, int b) {
        System.out.println("Two parameters: " + a + ", " + b);
    }

    // Method overloading by changing the type of parameters
    void display(String str) {
        System.out.println("String parameter: " + str);
    }

    void display(int a, String str) {
        System.out.println("Int and String parameters: " + a + ", " + str);
    }

    // Overloading with varargs
    void display(String... strings) {
        System.out.println("Varargs (String): " + String.join(", ", strings));
    }

    void display(int... numbers) {
        System.out.print("Varargs (int): ");
        for (int number : numbers) {
            System.out.print(number + " ");
        }
        System.out.println();
    }

    // Overloading with different access modifiers
    private void display(boolean flag) {
        System.out.println("Private method with boolean: " + flag);
    }

    protected void display(double d) {
        System.out.println("Protected method with double: " + d);
    }
}

// Main class to demonstrate method overloading
public class Main {
    public static void main(String[] args) {
        OverloadingExample example = new OverloadingExample();

        example.display(1);
        example.display(1, 2);
        example.display("Hello");
        example.display(10, "Overloading");
        example.display("Vararg1", "Vararg2", "Vararg3");
        example.display(3, 4, 5, 6);
        example.display(3.14);
        example.display(true);
    }
}
