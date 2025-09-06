import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Exer1_Functional {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Using functional style to filter even numbers and square them
        List<Integer> result = numbers.stream()
                                      .filter(n -> n % 2 == 0)
                                      .map(n -> n * n)
                                      .collect(Collectors.toList());

        System.out.println("Functional Programming Result: " + result);
    }
}
