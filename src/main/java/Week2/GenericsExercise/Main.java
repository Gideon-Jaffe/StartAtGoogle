package Week2.GenericsExercise;

import java.util.concurrent.Callable;

public class Main {
    public static void main(String[] args) {
        Counter myCounter = new Counter();
        System.out.println("----------Retry 1----------");
        System.out.println(retry(myCounter, 10, 20, 50));
        System.out.println("----------Retry 2----------");
        System.out.println(retry(myCounter, 40, 20));
        System.out.println("----------Retry 3----------");
        System.out.println(retry(myCounter, 40));
    }

    public static <T> T retry(Callable<T> action, T expectedResult, int numberOfRetries, int sleepBetweenTries) {
        T lastResult = null;
        try {
            while (numberOfRetries > 0) {
                lastResult = action.call();
                if (lastResult == expectedResult) {
                    return lastResult;
                } else {
                    Thread.sleep(sleepBetweenTries);
                    numberOfRetries--;
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to sleep in between calls", e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to call T.call", e);
        }
        return lastResult;
    }

    public static <T> T retry(Callable<T> action, T expectedResult, int numberOfRetries) {
        return retry(action, expectedResult, numberOfRetries, 100);
    }

    public static <T> T retry(Callable<T> action, T expectedResult) {
        return retry(action, expectedResult, 15, 100);
    }
}

