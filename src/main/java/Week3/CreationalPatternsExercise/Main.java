package Week3.CreationalPatternsExercise;

import Utils.DoubleRandomNames;
import Utils.RandomNames;
import Week3.CreationalPatternsExercise.TravelAgency.Passenger;
import Week3.CreationalPatternsExercise.TravelAgency.TravelAgency;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        RandomNames randomNameGenerator = new DoubleRandomNames("src/main/resources/first_names.json", "src/main/resources/last_names.json");
        TravelAgency agency = TravelAgency.createDefaultAgency();
        List<Passenger> passengerList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            passengerList.add(Passenger.RandomPassenger(randomNameGenerator));
        }
        agency.assignVehicles(passengerList);
    }
}


