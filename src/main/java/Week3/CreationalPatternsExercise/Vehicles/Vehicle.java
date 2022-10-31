package Week3.CreationalPatternsExercise.Vehicles;

import Week3.CreationalPatternsExercise.TravelAgency.Passenger;

public interface Vehicle {
    boolean transport(Passenger passenger);

    boolean isAvailable();
}

