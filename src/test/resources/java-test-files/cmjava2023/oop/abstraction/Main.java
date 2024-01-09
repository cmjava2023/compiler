// Abstract class with abstract and concrete methods
abstract class AbstractVehicle {
    private String brand;

    // Constructor
    public AbstractVehicle(String brand) {
        this.brand = brand;
    }

    // Abstract method
    public abstract void startEngine();

    // Concrete method
    public void displayBrand() {
        System.out.println("Brand: " + brand);
    }
}

// Concrete class extending abstract class
class Car extends AbstractVehicle {
    public Car(String brand) {
        super(brand);
    }

    // Implementation of abstract method
    @Override
    public void startEngine() {
        System.out.println("Car engine started.");
    }
}

// Interface with multiple methods
interface Movable {
    void move();
    void stop();
}

// Interface extending another interface
interface Operable extends Movable {
    void operate();
}

// Class implementing a single interface
class Bicycle implements Movable {
    public void move() {
        System.out.println("Bicycle is moving.");
    }

    public void stop() {
        System.out.println("Bicycle stopped.");
    }
}

// Class implementing an extended interface
class Robot implements Operable {
    public void move() {
        System.out.println("Robot is moving.");
    }

    public void stop() {
        System.out.println("Robot stopped.");
    }

    public void operate() {
        System.out.println("Robot is operating.");
    }
}

// Main class to demonstrate abstraction
public class Main {
    public static void main(String[] args) {
        Car car = new Car("Tesla");
        car.startEngine();
        car.displayBrand();

        Bicycle bike = new Bicycle();
        bike.move();
        bike.stop();

        Robot robot = new Robot();
        robot.move();
        robot.operate();
        robot.stop();
    }
}
