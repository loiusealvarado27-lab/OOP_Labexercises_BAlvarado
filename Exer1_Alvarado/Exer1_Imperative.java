import java.util.ArrayList;
import java.util.List;

public class Exer1_Imperative {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};
        List<Integer> result = new ArrayList<>();

        // Imperative style: loop to find even numbers and square them
        for (int n : numbers) {
            if (n % 2 == 0) {
                result.add(n * n);
            }
        }

        System.out.println("Imperative Programming Result: " + result);
    }
}
