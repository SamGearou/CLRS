package graphalgorithms;

import java.util.Objects;

public class Vertex {
    private int v;
    private Vertex parent;
    private int discoverTime;
    private int finishTime;
    private Color color;

    public enum Color {
        WHITE, GREY, BLACK;
    }

    public Vertex(int v) {
        this.v = v;
        parent = null;
        discoverTime = -1;
        finishTime = -1;
        color = Color.WHITE;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    public int getDiscoverTime() {
        return discoverTime;
    }

    public void setDiscoverTime(int discoverTime) {
        this.discoverTime = discoverTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(v);
    }

    @Override
    public boolean equals(Object obj) {
        return this.v == ((Vertex) obj).v;
    }
}
