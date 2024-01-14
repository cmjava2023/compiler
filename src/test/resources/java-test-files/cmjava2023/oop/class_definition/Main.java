package cmjava2023.oop.class_definition;

// Example of a basic class
class BasicClass {
    // Field declarations
    int defaultField;
    public int publicField;
    protected int protectedField;
    private int privateField;
    static int staticField;

    // Constructor
    public BasicClass() {
        // Constructor body
    }

    // Methods
    void defaultMethod() {
        // Method body
    }

    public void publicMethod() {
        // Method body
    }

    protected void protectedMethod() {
        // Method body
    }

    private void privateMethod() {
        // Method body
    }

    static void staticMethod() {
        // Method body
    }

    // Nested class
    class NestedClass {
        // Nested class body
    }
}

// Example of a final class
final class FinalClass {
    // Final class body
}

// Example of an abstract class
abstract class AbstractClass {
    // Abstract class body
}

// Example of a class with a constructor
class ConstructorClass {
    int value;

    // Constructor with parameters
    public ConstructorClass(int value) {
        this.value = value;
    }
}

// Example of a class implementing an interface
class InterfaceImplementingClass implements Runnable {
    public void run() {
        // Implementation of the Runnable interface's run method
    }
}

// Main class to demonstrate class definitions
public class Main {
    public static void main(String[] args) {
        BasicClass basic = new BasicClass();
        FinalClass finalInstance = new FinalClass();
        AbstractClass abstractInstance; // Cannot be instantiated
        ConstructorClass constructorClass = new ConstructorClass(10);
        InterfaceImplementingClass runnableClass = new InterfaceImplementingClass();

        // Using the classes...
    }
}
