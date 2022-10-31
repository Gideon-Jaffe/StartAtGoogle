package Week3.CreationalPatternsExercise.Vehicles;

import Week3.CreationalPatternsExercise.TravelAgency.Passenger;

public interface Vehicle {
    public boolean transport(Passenger passenger);

    public boolean isAvailable();
}

