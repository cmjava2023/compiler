package cmjava2023.notSupported.interfaces;

// Interfaces
interface Printable {
    void print();
}

interface Scanable {
    void scan();
}

// Klasse implementiert mehrere Interfaces
class MultiFunctionDevice implements Printable, Scanable {
    @Override
    public void print() {
        System.out.println("Printing...");
    }

    @Override
    public void scan() {
        System.out.println("Scanning...");
    }
}

// Beispielverwendung
public class Main {
    public static void main(String[] args) {
        MultiFunctionDevice device = new MultiFunctionDevice();
        device.print(); // Ruft die print-Methode des Printable-Interfaces auf
        device.scan();  // Ruft die scan-Methode des Scanable-Interfaces auf
    }
}
