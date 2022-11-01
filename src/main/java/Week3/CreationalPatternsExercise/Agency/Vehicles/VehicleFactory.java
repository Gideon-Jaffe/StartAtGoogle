package Week3.CreationalPatternsExercise.Agency.Vehicles;

import java.util.concurrent.ThreadLocalRandom;

public class VehicleFactory {
    public enum VehicleType {
        PLANE {
            @Override
            public Class<?> getType() {
                return Plane.class;
            }
        },
        BUS {
            @Override
            public Class<?> getType() {
                return Bus.class;
            }
        },
        TAXI {
            @Override
            public Class<?> getType() {
                return Taxi.class;
            }
        },
        BOAT {
            @Override
            public Class<?> getType() {
                return Boat.class;
            }
        };

        public abstract Class<?> getType();

        public static VehicleType randomVehicleType() {
            return VehicleType.values()[ThreadLocalRandom.current().nextInt(VehicleType.values().length)];
        }
    }

    private static VehicleFactory instance;

    public static VehicleFactory getInstance() {
        if (instance == null) {
            instance = new VehicleFactory();
        }
        return instance;
    }

    private VehicleFactory() {
    }

    public Vehicle createVehicle(VehicleType type) {
        switch (type) {
            case PLANE:
                return new Plane();
            case BUS:
                return new Bus();
            case TAXI:
                return new Taxi();
            case BOAT:
                return new Boat();
            default:
                throw new IllegalArgumentException("vehicle type not supported");
        }
    }
}


