package ParkingLot;

public class Truck extends Vehicle{
    @Override
    public VehicleSize getVehicleSize() {
        return VehicleSize.Large;
    }
}
