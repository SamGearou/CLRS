package graphalgorithms.minimumspanningtree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class PrimsAlgorithm {
    private Map<String, List<Edge>> graph;

    public PrimsAlgorithm() {
        graph = new HashMap<>();
    }

    public void addUndirectedEdge(String src, String dest, int weight) {
        graph.get(src).add(new Edge(src, dest, weight));
        graph.get(dest).add(new Edge(dest, src, weight));
    }

    public void addVertex(String vertex) {
        graph.put(vertex, new ArrayList<>());
    }

    public Map<String, List<Edge>> getGraph() {
        return graph;
    }

    public int mst(String src) {
        HashSet<String> visited = new HashSet<>();
        int mstWeight = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < graph.get(src).size(); i++) {
            pq.add(graph.get(src).get(i));
        }
        visited.add(src);
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (!visited.contains(edge.dest)) { //If  vertex has not been taken already
                visited.add(edge.dest);
                mstWeight += edge.weight;
                for (int i = 0; i < graph.get(edge.dest).size(); i++) {
                    if (!visited.contains(graph.get(edge.dest).get(i).dest)) { //If vertex has not been taken already
                        pq.add(graph.get(edge.dest).get(i));
                    }
                }
            }
        }
        return mstWeight;
    }

    public class Edge implements Comparable<Edge> {
        private String src;
        private String dest;
        private int weight;

        public Edge(String src, String dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            if (weight < other.weight) {
                return -1;
            } else {
                if (!src.equals(other.src)) {
                    return src.compareTo(other.src);
                } else {
                    return dest.compareTo(other.dest);
                }
            }
        }
    }

    public static void main(String[] args) {
        PrimsAlgorithm primsAlgorithm = new PrimsAlgorithm();
        primsAlgorithm.addVertex("0");
        primsAlgorithm.addVertex("1");
        primsAlgorithm.addVertex("2");
        primsAlgorithm.addVertex("3");
        primsAlgorithm.addVertex("4");
        primsAlgorithm.addUndirectedEdge("0", "1", 4);
        primsAlgorithm.addUndirectedEdge("0", "2", 4);
        primsAlgorithm.addUndirectedEdge("0", "3", 6);
        primsAlgorithm.addUndirectedEdge("0", "4", 6);
        primsAlgorithm.addUndirectedEdge("1", "2", 2);
        primsAlgorithm.addUndirectedEdge("2", "3", 8);
        primsAlgorithm.addUndirectedEdge("3", "4", 9);

        System.out.println(primsAlgorithm.mst("0")); //ans = 18
    }
}