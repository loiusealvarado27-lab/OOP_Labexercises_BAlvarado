package byteaid;

public class Buyer extends User {

    public Buyer(String name) {
        super(name);
    }

    // Compile-time polymorphism: purchaseSeed methods
    public void purchaseSeed(String seedName) {
        System.out.println(name + " purchased: " + seedName);
    }

    public void purchaseSeed(String seedName, int quantity) {
        System.out.println(name + " purchased " + quantity + " " + seedName + "(s)");
    }

    // Run-time polymorphism: override performAction
    @Override
    public void performAction() {
        System.out.println(name + " is purchasing seeds or requesting trades.");
    }
}
