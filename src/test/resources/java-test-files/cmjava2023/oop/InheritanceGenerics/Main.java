package cmjava2023.oop.InheritanceGenerics;

// Generic class
class Animal<T> {
    T name;

    // Constructor
    public Animal(T name) {
        this.name = name;
    }

    void eat() {
        System.out.println(name + " is eating.");
    }
}

// Child class using generics
class Dog<T> extends Animal<T> {
    // Constructor calling super class constructor
    public Dog(T name) {
        super(name);
    }

    void bark() {
        System.out.println(name + " is barking.");
    }
}

// Example usage
public class Main {
    public static void main(String[] args) {
        Dog<String> myDog = new Dog<>("Buddy");
        myDog.eat();  // Calls the eat() method of the Animal class
        myDog.bark(); // Calls the bark() method of the Dog class
    }
}
