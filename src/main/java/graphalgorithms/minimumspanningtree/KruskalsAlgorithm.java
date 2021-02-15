package graphalgorithms.minimumspanningtree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KruskalsAlgorithm {
    private Map<String, List<Edge>> graph;

    public KruskalsAlgorithm() {
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

    public int mst() {
        int mstWeight = 0;
        DisjointSet disjointSet = new DisjointSet(graph);
        List<Edge> edgeList = getEdgeList(graph);
        Collections.sort(edgeList);
        for (Edge edge : edgeList) {
            String src = edge.src;
            String dest = edge.dest;
            int weight = edge.weight;
            if (!disjointSet.findSet(src).equals(disjointSet.findSet(dest))) {
                disjointSet.union(src, dest);
                mstWeight += weight;
            }
        }
        return mstWeight;
    }

    public List<Edge> getEdgeList(Map<String, List<Edge>> graph) {
        List<Edge> edgeList = new ArrayList<>();
        for (String vertex : graph.keySet()) {
            for (int i = 0; i < graph.get(vertex).size(); i++) {
                Edge edge = graph.get(vertex).get(i);
                edgeList.add(edge);
            }
        }
        return edgeList;
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

    public class DisjointSet {
        private Map<String, String> parent;
        private Map<String, Integer> rank;

        public DisjointSet(Map<String, List<Edge>> graph) {
            parent = new HashMap<>();
            rank = new HashMap<>();
            for (String vertex : graph.keySet()) {
                parent.put(vertex, vertex);
                rank.put(vertex, 0);
            }
        }

        public String findSet(String vertex) {
            if (!vertex.equals(parent.get(vertex))) {
                parent.put(vertex, findSet(parent.get(vertex)));
            }
            return parent.get(vertex);
        }

        public String union(String src, String dest) {
            return link(findSet(src), findSet(dest));
        }

        public String link(String src, String dest) {
            if (rank.get(src) < rank.get(dest)) {
                parent.put(src, dest);
                return dest;
            } else {
                if (rank.get(src).equals(rank.get(dest))) {
                    rank.put(src, rank.get(src) + 1);
                }
                parent.put(dest, src);
                return src;
            }
        }
    }

    public static void main(String[] args) {
        KruskalsAlgorithm kruskalsAlgorithm = new KruskalsAlgorithm();
        kruskalsAlgorithm.addVertex("0");
        kruskalsAlgorithm.addVertex("1");
        kruskalsAlgorithm.addVertex("2");
        kruskalsAlgorithm.addVertex("3");
        kruskalsAlgorithm.addVertex("4");
        kruskalsAlgorithm.addUndirectedEdge("0", "1", 4);
        kruskalsAlgorithm.addUndirectedEdge("0", "2", 4);
        kruskalsAlgorithm.addUndirectedEdge("0", "3", 6);
        kruskalsAlgorithm.addUndirectedEdge("0", "4", 6);
        kruskalsAlgorithm.addUndirectedEdge("1", "2", 2);
        kruskalsAlgorithm.addUndirectedEdge("2", "3", 8);
        kruskalsAlgorithm.addUndirectedEdge("3", "4", 9);

        System.out.println(kruskalsAlgorithm.mst()); //ans = 18
    }
}