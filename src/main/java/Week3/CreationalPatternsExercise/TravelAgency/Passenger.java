package Week3.CreationalPatternsExercise.TravelAgency;

import Utils.RandomNames;
import Week3.CreationalPatternsExercise.Vehicles.VehicleFactory;

public class Passenger {
    private final String name;
    private VehicleFactory.VehicleType favoriteVehicle;

    public Passenger(String name, VehicleFactory.VehicleType favoriteVehicle) {
        this.name = name;
        this.favoriteVehicle = favoriteVehicle;
    }

    public static Passenger RandomPassenger(RandomNames randomNameGenerator) {
        return new Passenger(randomNameGenerator.getRandomNameFromJsonFile(), VehicleFactory.VehicleType.randomVehicleType());
    }

    public String getName() {
        return name;
    }

    public VehicleFactory.VehicleType getFavoriteVehicle() {
        return favoriteVehicle;
    }

    public void setFavoriteVehicle(VehicleFactory.VehicleType favoriteVehicle) {
        this.favoriteVehicle = favoriteVehicle;
    }
}
