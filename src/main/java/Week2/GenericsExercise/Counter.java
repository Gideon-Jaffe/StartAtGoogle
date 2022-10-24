package Week2.GenericsExercise;

import java.util.concurrent.Callable;

public class Counter implements Callable<Integer> {
    int counter;

    public Counter() {
        this.counter = 0;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println(counter++);
        if (counter == 50) throw new RuntimeException("I am throwing Exception");
        return counter-1;
    }
}
