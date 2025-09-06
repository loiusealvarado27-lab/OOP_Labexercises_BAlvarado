import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Exer1_Declarative {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date");

        // Declaratively filter words starting with 'a' and convert to uppercase
        List<String> result = words.stream()
                                   .filter(w -> w.startsWith("a"))
                                   .map(String::toUpperCase)
                                   .collect(Collectors.toList());

        System.out.println("Declarative Programming Result: " + result);
    }
}
