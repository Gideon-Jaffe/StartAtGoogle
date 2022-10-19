package JavaBasic;

import java.lang.reflect.Array;
import java.util.concurrent.ThreadLocalRandom;

public class ArrayStack<E> {
    E[] currentStack;

    Class<E> datatype;

    int size;

    public ArrayStack(Class<E>datatype) {
        this.datatype = datatype;
        this.currentStack = (E[])Array.newInstance(this.datatype, 0);
        this.size = 0;
    }

    public void push(E item) {
        E[] newStack = (E[])Array.newInstance(datatype, size+1);
        System.arraycopy(currentStack, 0, newStack, 0, size);
        newStack[size] = item;
        currentStack = newStack;
        size++;
    }

    public E pop() {
        if (size == 0) return null;
        E returnValue = currentStack[size-1];
        E[] newStack = (E[])Array.newInstance(datatype, size-1);
        System.arraycopy(currentStack, 0, newStack, 0, size - 1);
        size--;
        currentStack = newStack;
        return returnValue;
    }

    public static void test() {
        ArrayStack<Integer> stack = new ArrayStack<>(Integer.class);
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
}
