package Week3.CreationalPatternsExercise.Vehicles;

import Week3.CreationalPatternsExercise.TravelAgency.Passenger;

public class Plane extends AbstractVehicle {
    @Override
    public boolean transport(Passenger passenger) {
        boolean isSuccessful = this.isAvailable.compareAndSet(true, false);
        if (!isSuccessful) {
            return false;
        }
        System.out.println("Plane transporting - " + passenger.getName());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("--Taxi finished transporting - " + passenger.getName());
        this.isAvailable.set(true);
        return true;
    }
}

