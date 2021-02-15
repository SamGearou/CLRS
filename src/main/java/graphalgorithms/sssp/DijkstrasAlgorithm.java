package graphalgorithms.sssp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

public class DijkstrasAlgorithm {
    private Map<String, List<Edge>> graph;

    public DijkstrasAlgorithm() {
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
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
            if (a.dist == b.dist) {
                return a.vertex.compareTo(b.vertex);
            }
            return a.dist - b.dist;
        });

        pq.add(new Pair(src, 0));
        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            if (p.dist > dist.get(p.vertex)) {
                continue;
            }
            for (int i = 0; i < graph.get(p.vertex).size(); i++) {
                Edge neighbor = graph.get(p.vertex).get(i);
                if (p.dist + neighbor.weight < dist.get(neighbor.dest)) {
                    dist.put(neighbor.dest, p.dist + neighbor.weight);
                    pq.add(new Pair(neighbor.dest, dist.get(neighbor.dest)));
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

    public class Pair {
        private String vertex;
        private int dist;

        public Pair(String vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }

        @Override
        public boolean equals(Object other) {
            Pair p = (Pair) other;
            return vertex.equals(p.vertex) && dist == p.dist;
        }

        @Override
        public int hashCode() {
            return Objects.hash(vertex, dist);
        }
    }

    public static void main(String[] args) {
        String startVertex = "2";
        DijkstrasAlgorithm dijkstrasAlgorithm = new DijkstrasAlgorithm();
        dijkstrasAlgorithm.addVertex("0");
        dijkstrasAlgorithm.addVertex("1");
        dijkstrasAlgorithm.addVertex("2");
        dijkstrasAlgorithm.addVertex("3");
        dijkstrasAlgorithm.addVertex("4");
        dijkstrasAlgorithm.addDirectedEdge("0", "4", 1);
        dijkstrasAlgorithm.addDirectedEdge("1", "3", 3);
        dijkstrasAlgorithm.addDirectedEdge("1", "4", 6);
        dijkstrasAlgorithm.addDirectedEdge("2", "0", 6);
        dijkstrasAlgorithm.addDirectedEdge("2", "1", 2);
        dijkstrasAlgorithm.addDirectedEdge("2", "3", 7);
        dijkstrasAlgorithm.addDirectedEdge("3", "4", 5);
        Map<String, Integer> shortestPaths = dijkstrasAlgorithm.shortestPath(startVertex);
        for (String vertex : shortestPaths.keySet()) {
            System.out.println("Vertex: " + vertex + ", Distance from " + startVertex + ": " + shortestPaths.get(vertex));
            //output:
//            Vertex: 0, Distance from 2: 6
//            Vertex: 1, Distance from 2: 2
//            Vertex: 2, Distance from 2: 0
//            Vertex: 3, Distance from 2: 5
//            Vertex: 4, Distance from 2: 7
        }
    }
}