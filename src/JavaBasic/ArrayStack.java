package JavaBasic;

import java.lang.reflect.Array;

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
        for (int i = 0; i < size-1; i++) {
            newStack[i] = currentStack[i];
        }
        size--;
        currentStack = newStack;
        return returnValue;
    }
}
