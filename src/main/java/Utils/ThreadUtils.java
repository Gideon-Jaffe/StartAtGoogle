package Utils;

import java.util.concurrent.ThreadLocalRandom;

public class ThreadUtils {
    public static void sleep() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextLong(1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e + " cant sleep");
        }
    }
}
