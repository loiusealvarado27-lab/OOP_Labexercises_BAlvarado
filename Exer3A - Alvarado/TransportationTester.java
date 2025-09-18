/*
Class Hierarchy Diagram (Exer3-A)

                Transportation
        /            |               \
AirTransport    LandTransport     WaterTransport
   / |  \        /   |   |   \         /     \
Heli Plane Shuttle Truck SUV Tricycle MC Kariton Boat Ship
*/

public class TransportationTester {
    public static void main(String[] args) {
        
        Helicopter heli = new Helicopter();
        Airplane plane = new Airplane();
        SpaceShuttle shuttle = new SpaceShuttle();

        Truck truck = new Truck();
        SUV suv = new SUV();
        Tricycle tricycle = new Tricycle();
        Motorcycle motor = new Motorcycle();
        Kariton kariton = new Kariton();

        Boat boat = new Boat();
        Ship ship = new Ship();

        heli.move();
        plane.move();
        shuttle.move();

        truck.move();
        suv.move();
        tricycle.move();
        motor.move();
        kariton.move();

        boat.move();
        ship.move();
    }
}
