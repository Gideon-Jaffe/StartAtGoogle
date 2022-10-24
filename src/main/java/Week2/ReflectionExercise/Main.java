package Week2.ReflectionExercise;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        goodConstructor obj1 = new goodConstructor("hello");
        badConstructor obj2 = new badConstructor("hello", 1);

        checkForConstructorWithString(obj1).ifPresent(obj -> System.out.println("Successfully created an object of class GoodConstructor"));
        checkForConstructorWithString(obj2).ifPresent(obj -> System.out.println("Successfully created an object of class badConstructor"));

    }

    public static Optional<Object> checkForConstructorWithString(Object o) {
        Class<?> objectClass = o.getClass();
        try {
            Constructor<?> constructor = objectClass.getDeclaredConstructor(String.class);
            Object newObject = constructor.newInstance("Test String");
            return Optional.of(newObject);
        } catch (NoSuchMethodException e) {
            return Optional.empty();
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}

