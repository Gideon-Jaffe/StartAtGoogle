package Week3.CreationalPatternsExercise.Agency.Vehicles;

import Week3.CreationalPatternsExercise.Agency.TravelAgency.Passenger;

public class Plane extends AbstractVehicle {
    @Override
    public boolean transport(Passenger passenger) {
        if (!setTaken()) {
            return false;
        }
        System.out.println("Plane transporting - " + passenger);
        Utils.ThreadUtils.sleep();
        System.out.println("--Taxi finished transporting - " + passenger);
        this.isAvailable.set(true);
        return true;
    }
}

