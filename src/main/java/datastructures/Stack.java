package datastructures;

public class Stack<T extends Object> {
    private T[] stack;
    private int peek;

    public Stack(int size) {
        stack = (T[]) new Object[size];
        peek = 0;
    }

    public boolean push(T element) {
        if (isFull()) {
            return false;
        } else {
            stack[peek++] = element;
        }
        return true;
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        } else {
            T temp = stack[peek];
            stack[peek--] = null;
            return temp;
        }
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return stack[peek - 1];
    }

    public boolean isFull() {
        return peek >= stack.length;
    }

    public boolean isEmpty() {
        return peek == 0;
    }
}
