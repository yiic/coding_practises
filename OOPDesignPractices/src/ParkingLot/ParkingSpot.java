package ParkingLot;

class ParkingSpot {
    private final VehicleSize spotSize;
    private Vehicle currentVehicle;

    ParkingSpot(VehicleSize spotSize) {
        this.spotSize = spotSize;
    }

    boolean emptyAndFit(Vehicle v) {
        return currentVehicle == null && v.getVehicleSize().getSize() <= spotSize.getSize();
    }

    void park(Vehicle v) {
        currentVehicle = v;
    }

    void leave() {
        currentVehicle = null;
    }

    Vehicle getCurrentVehicle() {
        return currentVehicle;
    }

}
