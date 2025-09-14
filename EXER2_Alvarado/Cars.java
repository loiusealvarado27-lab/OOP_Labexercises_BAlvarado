public class Cars {
    private String brand;
    private String color;
    private String plateNo;
    private String chassisNo;

    public Cars() {
        this.brand = "No Brand";
        this.color = "No Color";
        this.plateNo = "No PlateNumber";
        this.chassisNo = "No Chassis No Yet!";
    }

    public Cars(String brand, String color, String plateNo, String chassisNo) {
        this.brand = brand;
        this.color = color;
        this.plateNo = plateNo;
        this.chassisNo = chassisNo;
    }

    @Override
    public String toString() {
        return "Brand: " + brand +
               "\nColor: " + color +
               "\nPlate Number: " + plateNo +
               "\nChassis Number: " + chassisNo +
               "\n---------------------------";
    }
}
