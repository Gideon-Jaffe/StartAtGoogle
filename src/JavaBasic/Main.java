package JavaBasic;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        //testLoops();

        //testUsers();

        testStack();
    }

    private static void testLoops() {
        int[] numbers = new int[40];
        for (int i = 0; i < 40; i++)
        {
            numbers[i] = i;
        }
        printNumbersDivisible_For(numbers, 5);
        printNumbersDivisible_ForEach(numbers, 5);
        printNumbersDivisible_While(numbers, 5);
    }

    private static void testUsers() {
        Map usersMap = User.createRandomUserMap(10);
        User tempUser = User.createRandomUser();
        usersMap.put(tempUser.Id, tempUser);
        ArrayList<User> usersAsList = new ArrayList<User>(usersMap.values());
        User gotUser = User.GetUserFromList(usersAsList, tempUser.Id);
        System.out.println(gotUser);

        int amountActivated = User.amountUsersActivated(usersAsList);
        System.out.println(amountActivated + " Users Activated");
    }

    private static void testStack() {
        ArrayStack stack = new ArrayStack(Integer.class);
        for (int i = 0; i < 10; i++) {
            int toPush = ThreadLocalRandom.current().nextInt();
            System.out.print(toPush + ", ");
            stack.push(toPush);
        }

        System.out.println();

        for (int i = 0; i < 10; i++) {
            System.out.print(stack.pop() + ", ");
        }


    }
    public static void printNumbersDivisible_For(int[] numbersToPrint, int divisor) {
        for (int index =0; index < numbersToPrint.length; index++) {
            if (numbersToPrint[index]%divisor == 0) {
                System.out.println(numbersToPrint[index]);
            }
        }
    }

    public static void printNumbersDivisible_ForEach(int[] numbersToPrint, int divisor) {
        for (int currentNumber : numbersToPrint) {
            if (currentNumber % divisor == 0) {
                System.out.println(currentNumber);
            }
        }
    }

    public static void printNumbersDivisible_While(int[] numbersToPrint, int divisor) {
        int index = 0;
        while (index < numbersToPrint.length) {
            if (numbersToPrint[index]%divisor == 0) {
                System.out.println(numbersToPrint[index]);
            }
            index++;
        }
    }

}