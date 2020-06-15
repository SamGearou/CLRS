package graphalgorithms.shortestpaths;

import datastructures.Heap;
import graphalgorithms.graph.Edge;
import graphalgorithms.graph.Graph;
import graphalgorithms.graph.Vertex;

import java.util.Comparator;
import java.util.Set;

public class Dijkstra<T extends Comparable> {

    //Runtime: O(V+E)lgV, which is O(ElgV) since V is O(E) assuming a connected graph (all vertices can be reached from source vertex)
    //O(VlgV + E) running time with Fibonacci Heap implementation of min-priority queue
    public void shortestPath(Graph<T> graph, Vertex<T> src) {
        src.setDist(0);
        Set<Vertex<T>> vertices = graph.getVertices();
        Heap<Vertex<T>> pq = new Heap<>(vertices.size(), Comparator.comparingInt(Vertex::getDist));
        for (Vertex<T> vertex : vertices) {
            //need this line to reset consecutive runs of this function in Johnson's algorithm
            if (vertex != src) {
                vertex.setDist(Integer.MAX_VALUE);
            }
            pq.add(vertex);
        }
        while (!pq.isEmpty()) {
            Vertex<T> curr = pq.removeMin();
            Set<Edge<T>> neighbors = graph.getNeighbors(curr);
            for (Edge<T> edge : neighbors) {
                Vertex<T> dest = edge.getDest();
                int w = edge.getW();
                if (curr.getDist() + w < dest.getDist()) {
                    dest.setDist(curr.getDist() + w);
                    pq.decreaseKey(pq.getPos(dest), dest);
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph<String> graph = new Graph<>();
        Vertex<String> s = new Vertex<>("s");
        Vertex<String> t = new Vertex<>("t");
        Vertex<String> y = new Vertex<>("y");
        Vertex<String> x = new Vertex<>("x");
        Vertex<String> z = new Vertex<>("z");
        graph.addDirectedEdge(s, t, 10);
        graph.addDirectedEdge(s, y, 5);
        graph.addDirectedEdge(t, y, 2);
        graph.addDirectedEdge(y, t, 3);
        graph.addDirectedEdge(t, x, 1);
        graph.addDirectedEdge(y, x, 9);
        graph.addDirectedEdge(y, z, 2);
        graph.addDirectedEdge(x, z, 4);
        graph.addDirectedEdge(z, x, 6);
        graph.addDirectedEdge(z, s, 7);
        new Dijkstra().shortestPath(graph, s);
        for (Vertex<String> vertex : graph.getVertices()) {
            System.out.println(vertex);
        }
    }
}
