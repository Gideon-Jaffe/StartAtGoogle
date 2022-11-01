package Week3.CreationalPatternsExercise.Agency.Vehicles;

import Week3.CreationalPatternsExercise.Agency.TravelAgency.Passenger;

public class Bus extends AbstractVehicle {
    @Override
    public boolean transport(Passenger passenger) {
        if (!setTaken()) {
            return false;
        }
        System.out.println("Bus started transporting - " + passenger);
        Utils.ThreadUtils.sleep();
        System.out.println("--Bus finished transporting - " + passenger);
        this.isAvailable.set(true);
        return true;
    }
}

