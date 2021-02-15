package graphalgorithms.sssp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BellmanFordAlgorithm {
    private Map<String, List<Edge>> graph;

    public BellmanFordAlgorithm() {
        graph = new HashMap<>();
    }

    public void addVertex(String vertex) {
        graph.put(vertex, new ArrayList<>());
    }

    public void addUndirectedEdge(String src, String dest, int weight) {
        graph.get(src).add(new Edge(dest, weight));
        graph.get(dest).add(new Edge(src, weight));
    }

    public void addDirectedEdge(String src, String dest, int weight) {
        graph.get(src).add(new Edge(dest, weight));
    }

    public Map<String, List<Edge>> getGraph() {
        return graph;
    }

    public Map<String, Integer> shortestPath(String src) {
        Map<String, Integer> dist = new HashMap<>();
        for (String vertex : graph.keySet()) {
            dist.put(vertex, Integer.MAX_VALUE);
        }
        dist.put(src, 0);
        int V = graph.size();
        for (int i = 0; i < V - 1; i++) { //only need to relax V-1 times
            for (String vertex : graph.keySet()) {
                for (int j = 0; j < graph.get(vertex).size(); j++) {
                    Edge neighbor = graph.get(vertex).get(j);
                    if (dist.get(neighbor.dest) > (long) dist.get(vertex) + neighbor.weight) {
                        dist.put(neighbor.dest, dist.get(vertex) + neighbor.weight);
                    }
                }
            }
        }

        for (String vertex : graph.keySet()) {
            for (int j = 0; j < graph.get(vertex).size(); j++) {
                Edge neighbor = graph.get(vertex).get(j);
                if (dist.get(neighbor.dest) > (long) dist.get(vertex) + neighbor.weight) {
                    System.out.println("This graph has a negative weight cycle");
                    return new HashMap<>();
                }
            }
        }
        return dist;
    }

    public class Edge {
        private String dest;
        private int weight;

        public Edge(String dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        String startVertex = "0";
        BellmanFordAlgorithm bellmanFord = new BellmanFordAlgorithm();
        bellmanFord.addVertex("0");
        bellmanFord.addVertex("1");
        bellmanFord.addVertex("2");
        bellmanFord.addDirectedEdge("0", "1", 1000);
        bellmanFord.addDirectedEdge("1", "2", 15);
        bellmanFord.addDirectedEdge("2", "1", -42);
        bellmanFord.shortestPath(startVertex); //This graph has a negative weight cycle detected by Bellman Ford Algorithm
    }
}