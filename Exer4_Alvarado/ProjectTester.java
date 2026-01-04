package byteaid;

public class ProjectTester {
    public static void main(String[] args) {
        System.out.println("=== Compile-time Polymorphism in ByteAid ===");

        Buyer buyer = new Buyer("Pedro");
        buyer.purchaseSeed("Tomato");
        buyer.purchaseSeed("Carrot", 30);

        Admin admin = new Admin("Anna");
        admin.manageUser("Pedro");
        admin.manageUser("Pedro", "deleting");

        System.out.println("\n=== Run-time Polymorphism in ByteAid ===");
        User[] users = {new Buyer("Luis"), new Admin("Rosa")};
        for (User u : users) {
            u.performAction(); // same method, different behavior
        }
    }
}
