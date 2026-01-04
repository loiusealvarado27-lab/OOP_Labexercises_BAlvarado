package byteaid;

public class User {
    protected String name;

    public User(String name) {
        this.name = name;
    }

    // Run-time polymorphism: method to override in child classes
    public void performAction() {
        System.out.println(name + " is performing an action in ByteAid.");
    }
}
