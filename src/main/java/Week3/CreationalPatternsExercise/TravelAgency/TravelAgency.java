package Week3.CreationalPatternsExercise.TravelAgency;

import Week3.CreationalPatternsExercise.Vehicles.Vehicle;
import Week3.CreationalPatternsExercise.Vehicles.VehicleFactory;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class TravelAgency {

    private List<Vehicle> vehicles;
    private final Stream<Vehicle> availableVehicles;

    private TravelAgency(Map<VehicleFactory.VehicleType, Integer> types) {
        this.vehicles = new ArrayList<>();
        availableVehicles = vehicles.stream().filter(Vehicle::isAvailable);
        for (Map.Entry<VehicleFactory.VehicleType, Integer> entry : types.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                this.vehicles.add(VehicleFactory.getInstance().createVehicle(entry.getKey()));
            }
        }
    }

    public static TravelAgency createDefaultAgency() {
        Map<VehicleFactory.VehicleType, Integer> types = new HashMap<>();
        types.put(VehicleFactory.VehicleType.BUS, 4);
        types.put(VehicleFactory.VehicleType.TAXI, 8);
        types.put(VehicleFactory.VehicleType.BOAT, 3);
        types.put(VehicleFactory.VehicleType.PLANE, 1);
        return createAgencyFromVehicleList(types);
    }

    public static TravelAgency createAgencyFromVehicleList(Map<VehicleFactory.VehicleType, Integer> types) {
        return new TravelAgency(types);
    }

    public void getAvailableVehicle(Passenger passenger) {
        Class<?> type = passenger.getFavoriteVehicle().getType();
        Vehicle myVehicle;
        do {
            Optional<Vehicle> chosenVehicle = vehicles.stream().filter(Vehicle::isAvailable).filter(vehicle -> (type.equals(vehicle.getClass()))).findFirst();
            if (chosenVehicle.isPresent()) {
                myVehicle = chosenVehicle.get();
            } else {
                Optional<Vehicle> any = vehicles.stream().filter(Vehicle::isAvailable).findAny();
                if (any.isPresent()) {
                    myVehicle = any.get();
                } else {
                    System.out.println("no vehicle");
                    return;
                }
            }
        } while (!myVehicle.transport(passenger));
    }

    public Map<Vehicle, Passenger> assignVehicles(List<Passenger> passengers) {
        ExecutorService executorService = Executors.newFixedThreadPool(vehicles.size());
        for (Passenger passenger : passengers) {
            executorService.submit(() -> getAvailableVehicle(passenger));
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
