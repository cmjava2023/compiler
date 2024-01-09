// Base class
class Vehicle {
    protected String brand;

    // Vehicle class constructor
    public Vehicle(String brand) {
        this.brand = brand;
    }

    // Method in the base class
    public void honk() {
        System.out.println("Vehicle honks");
    }

    // Method to be overridden
    public void displayInfo() {
        System.out.println("Vehicle brand: " + brand);
    }
}

// Derived class extending Vehicle
class Car extends Vehicle {
    private String model;

    // Car class constructor
    public Car(String brand, String model) {
        super(brand); // Call to the superclass (Vehicle) constructor
        this.model = model;
    }

    // Overriding the displayInfo method from Vehicle
    @Override
    public void displayInfo() {
        super.displayInfo(); // Call to the superclass (Vehicle) method
        System.out.println("Car model: " + model);
    }
}

// Another derived class
class ElectricCar extends Car {
    private int batteryCapacity;

    // ElectricCar class constructor
    public ElectricCar(String brand, String model, int batteryCapacity) {
        super(brand, model);
        this.batteryCapacity = batteryCapacity;
    }

    // Overriding the displayInfo method again
    @Override
    public void displayInfo() {
        super.displayInfo(); // Call to the superclass (Car) method
        System.out.println("Battery capacity: " + batteryCapacity);
    }
}

// Abstract class
abstract class AbstractVehicle {
    // Abstract method
    public abstract void start();
}

// Concrete class extending abstract class
class Motorbike extends AbstractVehicle {
    @Override
    public void start() {
        System.out.println("Motorbike starts");
    }
}

// Interface
interface Amphibious {
    void swim();
}

// Class implementing an interface and extending a class
class AmphibiousCar extends Car implements Amphibious {
    public AmphibiousCar(String brand, String model) {
        super(brand, model);
    }

    public void swim() {
        System.out.println("AmphibiousCar swims");
    }
}

// Main class to demonstrate inheritance
public class Main {
    public static void main(String[] args) {
        Car car = new Car("Toyota", "Corolla");
        car.displayInfo();

        ElectricCar eCar = new ElectricCar("Tesla", "Model S", 85);
        eCar.displayInfo();

        Motorbike motorbike = new Motorbike();
        motorbike.start();

        AmphibiousCar amphibiousCar = new AmphibiousCar("Amphi", "DualWay");
        amphibiousCar.displayInfo();
        amphibiousCar.swim();
    }
}
