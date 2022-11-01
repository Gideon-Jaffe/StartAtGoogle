package Week3.CreationalPatternsExercise.Agency.Vehicles;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractVehicle implements Vehicle {
    protected AtomicBoolean isAvailable = new AtomicBoolean(true);

    @Override
    public boolean isAvailable() {
        return isAvailable.get();
    }

    protected boolean setTaken() {
        return this.isAvailable.compareAndSet(true, false);
    }
}
