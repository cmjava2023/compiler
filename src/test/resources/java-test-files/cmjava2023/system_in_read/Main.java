package cmjava2023.system_in_read;

public class Main {
    public static void main(String[] args) throws java.io.IOException {
        System.out.println("Type one char and press ENTER: ");
        char c = (char) System.in.read();        
        System.out.println("You entered char: " + c);
    }
}