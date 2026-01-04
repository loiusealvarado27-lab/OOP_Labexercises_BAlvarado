package byteaid;

public class Admin extends User {

    public Admin(String name) {
        super(name);
    }

    // Compile-time polymorphism: manageUser
    public void manageUser(String userName) {
        System.out.println(name + " is managing user: " + userName);
    }

    public void manageUser(String userName, String action) {
        System.out.println(name + " is " + action + " user: " + userName);
    }

    // Run-time polymorphism: override performAction
    @Override
    public void performAction() {
        System.out.println(name + " is monitoring users and overseeing transactions.");
    }
}
