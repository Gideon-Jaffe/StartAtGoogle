package Week3.CreationalPatternsExercise.Agency.Vehicles;

import Week3.CreationalPatternsExercise.Agency.TravelAgency.Passenger;

public interface Vehicle {
    boolean transport(Passenger passenger);

    boolean isAvailable();
}

