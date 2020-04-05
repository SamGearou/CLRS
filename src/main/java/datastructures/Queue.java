package datastructures;

public class Queue<T extends Object> {
    T[] queue;
    int start;
    int end;
    int count;

    public Queue(int size) {
        queue = (T[]) new Object[size];
        start = 0;
        end = 0;
        count = 0;
    }

    public boolean enqueue(T element) {
        if (isFull()) {
            return false;
        } else {
            queue[end] = element;
            end = (end + 1) % queue.length;
        }
        count++;
        return true;
    }

    public T dequeue() {
        if (isEmpty()) {
            return null;
        } else {
            T temp = queue[start];
            queue[start] = null;
            start = (start + 1) % queue.length;
            count--;
            return temp;
        }
    }

    public boolean isFull() {
        return count >= queue.length;
    }

    public boolean isEmpty() {
        return count == 0;
    }
}
