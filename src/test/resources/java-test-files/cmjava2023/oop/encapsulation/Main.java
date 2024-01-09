// Example class to demonstrate encapsulation
class EncapsulatedObject {
    // Private variables
    private int privateInt;
    private String privateString;

    // Constructor
    public EncapsulatedObject(int privateInt, String privateString) {
        this.privateInt = privateInt;
        this.privateString = privateString;
    }

    // Getter for privateInt
    public int getPrivateInt() {
        return privateInt;
    }

    // Setter for privateInt
    public void setPrivateInt(int privateInt) {
        this.privateInt = privateInt;
    }

    // Getter for privateString
    public String getPrivateString() {
        return privateString;
    }

    // Setter for privateString
    public void setPrivateString(String privateString) {
        this.privateString = privateString;
    }

    // Method demonstrating internal state modification
    public void incrementInt() {
        this.privateInt++;
    }
}

// Example class with read-only fields (using only getters)
class ReadOnlyObject {
    private int readOnlyInt;
    private String readOnlyString;

    public ReadOnlyObject(int readOnlyInt, String readOnlyString) {
        this.readOnlyInt = readOnlyInt;
        this.readOnlyString = readOnlyString;
    }

    public int getReadOnlyInt() {
        return readOnlyInt;
    }

    public String getReadOnlyString() {
        return readOnlyString;
    }
}

// Example class with write-only fields (using only setters)
class WriteOnlyObject {
    private int writeOnlyInt;
    private String writeOnlyString;

    public void setWriteOnlyInt(int writeOnlyInt) {
        this.writeOnlyInt = writeOnlyInt;
    }

    public void setWriteOnlyString(String writeOnlyString) {
        this.writeOnlyString = writeOnlyString;
    }
}

// Main class to demonstrate encapsulation
public class Main {
    public static void main(String[] args) {
        EncapsulatedObject obj = new EncapsulatedObject(10, "Test");
        obj.setPrivateInt(20);
        obj.setPrivateString("Updated");
        obj.incrementInt();
        System.out.println("Int: " + obj.getPrivateInt() + ", String: " + obj.getPrivateString());

        ReadOnlyObject readOnlyObj = new ReadOnlyObject(30, "ReadOnly");
        System.out.println("Read-Only Int: " + readOnlyObj.getReadOnlyInt() + ", String: " + readOnlyObj.getReadOnlyString());

        WriteOnlyObject writeOnlyObj = new WriteOnlyObject();
        writeOnlyObj.setWriteOnlyInt(40);
        writeOnlyObj.setWriteOnlyString("WriteOnly");
        // Values of writeOnlyObj are write-only, can't be read back
    }
}
