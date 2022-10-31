package Week3.CreationalPatternsExercise.Vehicles;

import Week3.CreationalPatternsExercise.TravelAgency.Passenger;

public class Boat extends AbstractVehicle {
    @Override
    public boolean transport(Passenger passenger) {
        if (!setTaken()) {
            return false;
        }
        System.out.println("Boat starting transporting - " + passenger);
        Utils.ThreadUtils.sleep();
        System.out.println("--Boat finished transporting - " + passenger);
        this.isAvailable.set(true);
        return true;
    }
}
