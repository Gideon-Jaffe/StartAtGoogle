package Week3.CreationalPatternsExercise.Vehicles;

import Week3.CreationalPatternsExercise.TravelAgency.Passenger;

public class Bus extends AbstractVehicle {
    @Override
    public boolean transport(Passenger passenger) {
        if (!setTaken()) {
            return false;
        }
        System.out.println("Bus started transporting - " + passenger.getName());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("--Bus finished transporting - " + passenger.getName());
        this.isAvailable.set(true);
        return true;
    }
}

