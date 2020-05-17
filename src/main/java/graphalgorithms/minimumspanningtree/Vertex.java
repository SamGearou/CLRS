package graphalgorithms.minimumspanningtree;

import java.util.UUID;

public class Vertex<T> implements Comparable {
    private T data;
    private UUID key;

    public Vertex(T data) {
        this.data = data;
        key = UUID.randomUUID();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Vertex<T> other = (Vertex<T>) obj;
        return key.equals(other.key);
    }

    @Override
    public int compareTo(Object other) {
        return key.compareTo(((Vertex<T>) other).key);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "data=" + data +
                '}';
    }
}
