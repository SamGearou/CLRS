package graphalgorithms.shortestpaths;

import graphalgorithms.graph.Edge;
import graphalgorithms.graph.Graph;
import graphalgorithms.graph.Vertex;

import java.util.HashMap;
import java.util.Set;

public class Johnson {

    //Faster than Floyd Warshall and matrix multiplication with sparse graphs (graphs with not very many edges)
    //Runtime: O(VElgV) with binary heap with Dijkstra's
    //Better Runtime: O(V^2lgV + VE) with Fibonacci heap with Dijkstra's
    public int[][] allPairs(Graph<Integer> graph) {
        int numVertices = graph.getVertices().size();
        Set<Vertex<Integer>> vertices = graph.getVertices();
        Set<Edge<Integer>> edges = graph.getEdges();
        int[][] allPairs = new int[numVertices + 1][numVertices + 1];
        Vertex<Integer> s = new Vertex<>(0);
        graph.addVertex(s);
        for (Vertex<Integer> vertex : vertices) {
            //careful here, could be ConcurrentModificationException when 's' or 'vertex' is not in vertex set already
            graph.addDirectedEdge(s, vertex, 0);
        }
        if (new BellmanFord().shortestPath(graph, s) == false) {
            throw new IllegalArgumentException("Graph has a negative edge weight cycle");
        }
        HashMap<Vertex<Integer>, Integer> nonNegWeights = new HashMap<>();
        //save new vertex weights from bellman ford
        for (Vertex<Integer> vertex : vertices) {
            nonNegWeights.put(vertex, vertex.getDist());
        }
        //update edges to have non-negative weight
        for (Edge<Integer> edge : edges) {
            Vertex<Integer> src = edge.getSrc();
            Vertex<Integer> dest = edge.getDest();
            int w = edge.getW();
            edge.setW(w + nonNegWeights.get(src) - nonNegWeights.get(dest));
        }
        //populate shortest distances
        for (Vertex<Integer> src : vertices) {
            new Dijkstra().shortestPath(graph, src);
            for (Vertex<Integer> dest : vertices) {
                allPairs[src.getData()][dest.getData()] = dest.getDist() + nonNegWeights.get(dest) - nonNegWeights.get(src);
            }
        }
        return allPairs;
    }

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>();
        Vertex<Integer> one = new Vertex<>(1);
        Vertex<Integer> two = new Vertex<>(2);
        Vertex<Integer> three = new Vertex<>(3);
        Vertex<Integer> four = new Vertex<>(4);
        Vertex<Integer> five = new Vertex<>(5);
        graph.addDirectedEdge(one, two, 3);
        graph.addDirectedEdge(one, three, 8);
        graph.addDirectedEdge(one, five, -4);
        graph.addDirectedEdge(two, five, 7);
        graph.addDirectedEdge(two, four, 1);
        graph.addDirectedEdge(three, two, 4);
        graph.addDirectedEdge(four, three, -5);
        graph.addDirectedEdge(four, one, 2);
        graph.addDirectedEdge(five, four, 6);
        int[][] allPairs = new Johnson().allPairs(graph);
        for (int i = 1; i < allPairs.length; i++) {
            for (int j = 1; j < allPairs[i].length; j++) {
                System.out.println(i + ", " + j + ": " + allPairs[i][j]);
            }
        }
    }
}
