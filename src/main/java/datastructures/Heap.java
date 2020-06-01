package datastructures;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Heap<T extends Comparable<T>> {
    private T[] data;
    private int count;
    private Comparator<? super T> comparator;
    private Map<T, Integer> map; //map for speeding up decreaseKey operation

    public Heap(int heapSize) {
        data = (T[]) new Comparable[heapSize];
        count = 0;
        comparator = null;
        map = new HashMap<>();
    }

    public Heap(int heapSize, Comparator<? super T> comparator) {
        data = (T[]) new Comparable[heapSize];
        count = 0;
        this.comparator = comparator;
        map = new HashMap<>();
    }

    //This procedure runs in O(n) (with some math this can be proven)
    //buildHeap(T[] arr){
    //call minHeapifyUp(index, arr) that will take an extra parameter that is the array you want to heapify
    //}

    public void add(T element) {
        data[count] = element;
        map.put(element, count);
        if (comparator == null) {
            minHeapifyUp(count);
        } else {
            minHeapifyUp(count, comparator);
        }
        count++;
    }

    public void minHeapifyUp(int index) {
        T child = data[index];
        T parent = data[getParent(index)];
        if (parent.compareTo(child) > 0) {
            swap(index, getParent(index));
            if (comparator == null) {
                minHeapifyUp(getParent(index));
            } else {
                minHeapifyUp(getParent(index), comparator);
            }
        }
    }

    public void minHeapifyUp(int index, Comparator<? super T> comparator) {
        T child = data[index];
        T parent = data[getParent(index)];
        if (comparator.compare(parent, child) > 0) {
            swap(index, getParent(index));
            minHeapifyUp(getParent(index), comparator);
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
            if (comparator == null) {
                minHeapifyDown(smallest);
            } else {
                minHeapifyDown(smallest, comparator);
            }

        }
    }

    public void minHeapifyDown(int index, Comparator<? super T> comparator) {
        int leftChildIndex = getLeftChild(index);
        int rightChildIndex = getRightChild(index);
        int smallest = index;
        if (leftChildIndex < count && comparator.compare(data[smallest], data[leftChildIndex]) > 0) {
            smallest = leftChildIndex;
        }
        if (rightChildIndex < count && comparator.compare(data[smallest], data[rightChildIndex]) > 0) {
            smallest = rightChildIndex;
        }
        if (smallest != index) {
            swap(index, smallest);
            minHeapifyDown(smallest, comparator);
        }
    }

    public void decreaseKey(int index, T newKey) {
        if (comparator != null) {
            decreaseKey(index, newKey, comparator);
            return;
        }
        if (newKey.compareTo(data[index]) > 0) {
            throw new IllegalArgumentException("new key must be equal to or smaller than old key");
        }
        map.remove(data[index]);
        data[index] = newKey;
        map.put(newKey, index);
        if (comparator == null) {
            minHeapifyUp(index);
        } else {
            minHeapifyUp(index, comparator);
        }
    }

    public void decreaseKey(int index, T newKey, Comparator<? super T> comparator) {
        if (comparator.compare(newKey, data[index]) > 0) {
            throw new IllegalArgumentException("new key must be equal to or smaller than old key");
        }
        map.remove(data[index]);
        data[index] = newKey;
        map.put(newKey, index);
        minHeapifyUp(index, comparator);
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public T peek() {
        return data[0];
    }

    public T removeMin() {
        T temp = data[0];
        swap(0, --count);
        map.remove(data[count]);
        data[count] = null;
        if (comparator == null) {
            minHeapifyDown(0);
        } else {
            minHeapifyDown(0, comparator);
        }
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
        map.put(data[a], a);
        map.put(data[b], b);
    }

    public int getPos(T element) {
        return map.get(element);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(data[i] + ",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<>(10, (o1, o2) -> o1 - o2);
        heap.add(10);
        heap.add(8);
        heap.add(6);
        heap.add(4);
        heap.add(2);
        heap.add(1);
        heap.add(3);
        heap.add(5);
        heap.add(7);
        heap.add(9);
        heap.decreaseKey(heap.map.get(9), -1);
        heap.decreaseKey(heap.map.get(8), -2);
        System.out.println(heap.removeMin());
        System.out.println(heap.removeMin());
        System.out.println(heap.removeMin());
        System.out.println(heap.removeMin());
        System.out.println(heap.removeMin());
        System.out.println(heap.removeMin());
        System.out.println(heap.removeMin());
        System.out.println(heap.removeMin());
        System.out.println(heap.removeMin());
        System.out.println(heap.removeMin());
        System.out.println("SEPARATE");
        for (int key : heap.map.keySet()) {
            System.out.println(key + " " + heap.map.get(key));
        }
    }
}
