package cmjava2023.helloworld;

// Parent class
class Animal {
    String name;

    // Constructor
    public Animal(String name) {
        this.name = name;
    }

    void eat() {
        System.out.println(name + " is eating.");
    }
}

// Child class
class Dog extends Animal {
    // Constructor calling super class constructor
    public Dog(String name) {
        super(name);
    }

    void bark() {
        System.out.println(name + " is barking.");
    }
}

// Example usage
public class Main {
    public static void main(String[] args) {
        Dog myDog = new Dog("Buddy");
        myDog.eat();  // Calls the eat() method of the Animal class
        myDog.bark(); // Calls the bark() method of the Dog class
    }
}
