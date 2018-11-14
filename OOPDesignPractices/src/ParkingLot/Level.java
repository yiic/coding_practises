package ParkingLot;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Level {
    private final List<ParkingSpot> spots;

    Level(int numOfSpots) {
        List<ParkingSpot> list = new ArrayList<ParkingSpot>();

        int i = 0;
        for (; i < numOfSpots / 2; i++) {
            list.add(new ParkingSpot(VehicleSize.Compact));
        }
        for (; i < numOfSpots; i++) {
            list.add(new ParkingSpot(VehicleSize.Large));
        }

        //return a list that is read-only to "spots", expose "spots" so that someone cannot change it by mistake
        //don't use final because final means reference cannot be changed, but the data inside can still be changed. ex: array
        spots = Collections.unmodifiableList(list);
    }

    boolean hasSpot(Vehicle v) {
        for (ParkingSpot s : spots) {
            if (s.emptyAndFit(v)) {
                return true;
            }
        }
        return false;
    }

    boolean park(Vehicle v) {
        for (ParkingSpot s : spots) {
            if (s.emptyAndFit(v)) {
                s.park(v);
                return true;
            }
        }
        return false;
    }

    boolean leave(Vehicle v) {
        for (ParkingSpot s : spots) {
            if (s.getCurrentVehicle() == v) {
                s.leave();
                return true;
            }
        }
        return false;
    }


}
