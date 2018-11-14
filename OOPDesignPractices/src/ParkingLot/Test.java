package ParkingLot;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot(4, 10);

        // create a list of vehicles
        List<Vehicle> vlist = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            final Vehicle v = i % 2 == 0 ? new Car() : new Truck();
            vlist.add(v);
            boolean hasSpot = lot.hasSpot(v);

            if (i < 40) {
                assert hasSpot;
                assert lot.park(v);
            } else {
                assert !hasSpot;
                assert !lot.park(v);
            }
        }
        assert vlist.size() == 50;
        int i = 0;
        for (Vehicle v : vlist) {
            assert i >= 40 || lot.leave(v);
            i++;
        }
    }
}
