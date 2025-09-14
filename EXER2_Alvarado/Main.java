import java.util.Random;

public class Main {
    public static void main(String[] args) {
        String[] brands = {"Toyota", "Honda", "Ford", "Chevrolet", "BMW", "Mercedes", "Nissan", "Hyundai", "Kia", "Mazda"};
        String[] colors = {"Red", "Blue", "Black", "White", "Silver"};
        String[] plates = {"ABC123", "XYZ456", "DEF789", "GHI321", "JKL654", "MNO987", "PQR159", "STU753", "VWX852", "YZA456"};
        String[] chassis = {"CH001", "CH002", "CH003", "CH004", "CH005", "CH006", "CH007", "CH008", "CH009", "CH010"};

        Cars[] cars = new Cars[10]; 
        Random rand = new Random();

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Cars(
                brands[rand.nextInt(brands.length)],  
                colors[rand.nextInt(colors.length)],  
                plates[i],                            
                chassis[i]                            
            );
        }

        for (Cars c : cars) {
            System.out.println(c);
        }
    }
}
