// Superclass
class Superclass {
    // Method to be overridden
    public void display() {
        System.out.println("Display method in Superclass");
    }

    // Final method cannot be overridden
    public final void finalMethod() {
        System.out.println("Final method in Superclass");
    }

    // Static method cannot be overridden, it hides the superclass method
    public static void staticMethod() {
        System.out.println("Static method in Superclass");
    }

    // Private methods cannot be overridden
    private void privateMethod() {
        System.out.println("Private method in Superclass");
    }
}

// Subclass
class Subclass extends Superclass {
    // Overriding display method
    @Override
    public void display() {
        System.out.println("Display method in Subclass");
    }

    // Attempting to override final method will cause a compile-time error
    // Uncommenting the below method will cause an error
    // public void finalMethod() { ... }

    // Static method - method hiding
    public static void staticMethod() {
        System.out.println("Static method in Subclass");
    }

    // Cannot override private methods, but can define a new one
    private void privateMethod() {
        System.out.println("Private method in Subclass");
    }
}

// Another subclass demonstrating use of super
class AnotherSubclass extends Superclass {
    @Override
    public void display() {
        super.display(); // Calling the superclass method
        System.out.println("Additional behavior in AnotherSubclass");
    }
}

// Main class to demonstrate method overriding
public class Main {
    public static void main(String[] args) {
        Superclass superclass = new Superclass();
        superclass.display();

        Subclass subclass = new Subclass();
        subclass.display();

        AnotherSubclass anotherSubclass = new AnotherSubclass();
        anotherSubclass.display();

        // Static method called using class reference
        Superclass.staticMethod();
        Subclass.staticMethod();
    }
}
