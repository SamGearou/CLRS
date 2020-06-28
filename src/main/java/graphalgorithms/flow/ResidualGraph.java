package graphalgorithms.flow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ResidualGraph<T> {
    private Map<Vertex<T>, List<Edge<T>>> graph;
    private Set<Vertex<T>> vertices;
    private Set<Edge<T>> edges;

    public ResidualGraph() {
        graph = new HashMap<>();
        vertices = new HashSet<>();
        edges = new HashSet<>();
    }

    public Map<Vertex<T>, List<Edge<T>>> getGraph() {
        return graph;
    }

    public Set<Edge<T>> getEdges() {
        return edges;
    }

    public Set<Vertex<T>> getVertices() {
        return vertices;
    }

    public void addUndirectedEdge(Vertex<T> src, Vertex<T> dest, int capacity) {
        graph.computeIfAbsent(src, k -> new ArrayList<>());
        graph.get(src).add(new Edge(src, dest, capacity));
        vertices.add(src);
        vertices.add(dest);
    }

    @Override
    public String toString() {
        return "ResidualGraph{" +
                "vertices=" + vertices +
                ", edges=" + edges +
                '}';
    }

    public class Vertex<T> {
        private T data;

        public Vertex(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "data=" + data +
                    '}';
        }
    }

    public class Edge<T> {
        private Vertex<T> src;
        private Vertex<T> dest;
        private int flow;
        private int capacity;

        public Edge(Vertex<T> src, Vertex<T> dest, int capacity) {
            this.src = src;
            this.dest = dest;
            flow = 0;
            this.capacity = capacity;
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

        public int getFlow() {
            return flow;
        }

        public void setFlow(int flow) {
            this.flow = flow;
        }

        public int getCapacity() {
            return capacity;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }
    }
}
