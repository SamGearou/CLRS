package graphalgorithms.minimumspanningtree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph<T extends Comparable> {
    private Map<Vertex<T>, HashSet<Edge<T>>> graph;
    private Set<Vertex<T>> vertices;
    private Set<Edge<T>> edges;

    public Graph() {
        graph = new HashMap<>();
        vertices = new HashSet<>();
        edges = new HashSet<>();
    }

    public void addDirectedEdge(Vertex<T> src, Vertex<T> dest, int weight) {
        HashSet<Edge<T>> neighbors = graph.getOrDefault(src, new HashSet<>());
        Edge<T> edge = new Edge(src, dest, weight);
        neighbors.add(edge);
        graph.put(src, neighbors);
        edges.add(edge);
    }

    public void addUndirectedEdge(Vertex<T> src, Vertex<T> dest, int weight) {
        addDirectedEdge(src, dest, weight);
        addDirectedEdge(dest, src, weight);
    }

    public void deleteEdge(Vertex<T> src, Vertex<T> dest) {
        HashSet<Edge<T>> neighbors = graph.getOrDefault(src, new HashSet<>());
        for (Edge<T> e : neighbors) {
            if (e.getDest().equals(dest)) {
                neighbors.remove(e);
                edges.remove(e);
                break;
            }
        }
    }

    public void addVertex(Vertex<T> vertex) {
        graph.put(vertex, new HashSet<>());
        vertices.add(vertex);
    }

    public void deleteVertex(Vertex<T> vertex) {
        graph.remove(vertex);
        for (Vertex<T> key : graph.keySet()) {
            HashSet<Edge<T>> neighbor = graph.get(key);
            for (Edge<T> e : neighbor) {
                if (e.getDest().equals(vertex)) {
                    graph.get(key).remove(e);
                }
            }
        }
        vertices.remove(vertex);
    }

    public Set<Vertex<T>> getVertices() {
        return vertices;
    }

    public Set<Edge<T>> getEdges() {
        return edges;
    }

    public Set<Edge<T>> getNeighbors(Vertex<T> vertex) {
        return graph.get(vertex);
    }
}
