package graphalgorithms.minimumspanningtree;

import java.util.UUID;

public class Vertex<T> implements Comparable<Vertex<T>> {
    private T data;
    private UUID key;
    private int dist; //used in Prim's algorithm to determine min distance from this vertex to a vertex in the MST
    private Vertex<T> parent; //used in Prim's algorithm to keep track of MST
    private boolean visited; //used in Prim's to keep track of what vertices have been seen

    public Vertex(T data) {
        this.data = data;
        key = UUID.randomUUID();
        dist = Integer.MAX_VALUE;
        parent = null;
        visited = false;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public Vertex<T> getParent() {
        return parent;
    }

    public void setParent(Vertex<T> parent) {
        this.parent = parent;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public UUID getKey() {
        return key;
    }

    public void setKey(UUID key) {
        this.key = key;
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
    public String toString() {
        return "Vertex{" +
                "data=" + data +
                '}';
    }

    @Override
    public int compareTo(Vertex<T> other) {
        return key.compareTo(other.key);
    }
}
