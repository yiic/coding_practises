package ParkingLot;

public class Car extends Vehicle{
    @Override
    public VehicleSize getVehicleSize() {
        return VehicleSize.Compact;
    }
}
