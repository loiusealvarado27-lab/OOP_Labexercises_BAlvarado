public class Main {
    public static void main(String[] args) {
        User user1 = new User("Bhrent", 19, "Male");

        user1.displayUserInfo();

        user1.setName("Louise");
        user1.setAge(20);

        System.out.println("\nUpdated Information:");
        user1.displayUserInfo();
    }
}
