package graphalgorithms.minimumspanningtree;

import java.util.UUID;

public class Edge<T> implements Comparable {
    private Vertex<T> src;
    private Vertex<T> dest;
    private int w;
    private UUID key;

    public Edge(Vertex<T> src, Vertex<T> dest, int w) {
        this.src = src;
        this.dest = dest;
        this.w = w;
        key = UUID.randomUUID();
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Edge<T> other = (Edge<T>) obj;
        return key.equals(other.key);
    }

    @Override
    public int compareTo(Object other) {
        return w - ((Edge) other).w;
    }

    public Vertex<T> getSrc() {
        return src;
    }

    public void setSrc(Vertex<T> src) {
        this.src = src;
    }

    public Vertex<T> getDest() {
        return dest;
    }

    public void setDest(Vertex<T> dest) {
        this.dest = dest;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public UUID getKey() {
        return key;
    }

    public void setKey(UUID key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "src=" + src +
                ", dest=" + dest +
                ", w=" + w +
                '}';
    }
}
