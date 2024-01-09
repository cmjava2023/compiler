// Superclass
class Animal {
    public void makeSound() {
        System.out.println("Animal makes a sound");
    }
}

// Subclass 1
class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Dog barks");
    }
}

// Subclass 2
class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Cat meows");
    }
}

// Class demonstrating polymorphism
class PolymorphismExample {
    public void makeAnimalSound(Animal animal) {
        animal.makeSound();
    }
}

// Main class to demonstrate polymorphism
public class Main {
    public static void main(String[] args) {
        PolymorphismExample example = new PolymorphismExample();

        Animal myAnimal = new Animal();
        Dog myDog = new Dog();
        Cat myCat = new Cat();

        // Demonstrating polymorphism
        example.makeAnimalSound(myAnimal); // Calls Animal's makeSound
        example.makeAnimalSound(myDog);    // Calls Dog's makeSound (Polymorphism)
        example.makeAnimalSound(myCat);    // Calls Cat's makeSound (Polymorphism)

        // Upcasting - treating a subclass object as a superclass object
        Animal animalDog = new Dog(); // Dog treated as Animal
        Animal animalCat = new Cat(); // Cat treated as Animal

        // Polymorphic behavior with upcasting
        animalDog.makeSound(); // Calls Dog's makeSound
        animalCat.makeSound(); // Calls Cat's makeSound
    }
}
