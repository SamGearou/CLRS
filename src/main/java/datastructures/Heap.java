package datastructures;

public class Heap<T extends Comparable<T>> {
    private T[] data;
    private int count;

    public Heap(int heapSize) {
        data = (T[]) new Comparable[heapSize];
        count = 0;
    }

    //This procedure runs in O(n) (with some math this can be proven)
    //buildHeap(T[] arr){
    //call minHeapifyUp(index, arr) that will take an extra parameter that is the array you want to heapify
    //}

    public void add(T element) {
        data[count] = element;
        minHeapifyUp(count);
        count++;
    }

    public void minHeapifyUp(int index) {
        T child = data[index];
        T parent = data[getParent(index)];
        if (parent.compareTo(child) > 0) {
            swap(index, getParent(index));
            minHeapifyUp(getParent(index));
        }
    }

    public void minHeapifyDown(int index) {
        int leftChildIndex = getLeftChild(index);
        int rightChildIndex = getRightChild(index);
        int smallest = index;
        if (leftChildIndex < count && data[smallest].compareTo(data[leftChildIndex]) > 0) {
            smallest = leftChildIndex;
        }
        if (rightChildIndex < count && data[smallest].compareTo(data[rightChildIndex]) > 0) {
            smallest = rightChildIndex;
        }
        if (smallest != index) {
            swap(index, smallest);
            minHeapifyDown(smallest);
        }
    }

    public void decreaseKey(int index, T newKey) {
        if (newKey.compareTo(data[index]) == 0) {
            throw new IllegalArgumentException("new key must be equal to or smaller than old key");
        }
        data[index] = newKey;
        minHeapifyUp(index);
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public T getMin() {
        return data[0];
    }

    public T removeMin() {
        T temp = data[0];
        swap(0, --count);
        data[count] = null;
        minHeapifyDown(0);
        return temp;
    }

    public int getParent(int i) {
        return (i - 1) / 2;
    }

    public int getLeftChild(int i) {
        return 2 * i + 1;
    }

    public int getRightChild(int i) {
        return 2 * i + 2;
    }

    public void swap(int a, int b) {
        T temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<count; i++) {
            sb.append(data[i] + ",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
