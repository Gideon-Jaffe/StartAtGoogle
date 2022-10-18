package JavaBasic;

public class Main {
    public static void main(String[] args) {
        testLoops();

        User.test();

        ArrayStack.test();
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