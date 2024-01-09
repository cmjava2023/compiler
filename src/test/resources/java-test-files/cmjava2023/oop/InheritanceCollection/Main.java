// Abstract class with generics
abstract class Shape<T> {
    T dimension;

    public Shape(T dimension) {
        this.dimension = dimension;
    }

    // Abstract method
    abstract void calculateArea();

    // Non-abstract method
    void displayInfo() {
        System.out.println("This is a shape.");
    }
}

// Concrete subclass extending the abstract class
final class Circle extends Shape<Double> {
    // Static variable
    private static final double PI = 3.14159;

    public Circle(Double radius) {
        super(radius);
    }

    // Implementation of the abstract method
    @Override
    void calculateArea() {
        double radius = dimension;
        double area = PI * radius * radius;
        System.out.println("Area of the circle: " + area);
    }

    // Static method
    static void printStaticInfo() {
        System.out.println("This is a static method in the Circle class.");
    }
}

// Example usage
public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle(5.0);
        circle.calculateArea();  // Calls the overridden method in the Circle class
        circle.displayInfo();    // Calls the non-abstract method in the abstract class
        Circle.printStaticInfo(); // Calls the static method in the Circle class
    }
}
