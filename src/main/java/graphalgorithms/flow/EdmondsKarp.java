package graphalgorithms.flow;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class EdmondsKarp {
    private Map<String, Map<String, Edge>> graph;

    public EdmondsKarp() {
        graph = new HashMap<>();
    }

    public Map<String, Map<String, Edge>> getGraph() {
        return graph;
    }

    public void addVertex(String vertex) {
        graph.put(vertex, new HashMap<>());
    }

    public void addDirectedEdge(String src, String dest, int weight) {
        graph.get(src).put(dest, new Edge(weight, 0));
    }

    public void addUndirectedEdge(String src, String dest, int weight) {
        graph.get(src).put(dest, new Edge(weight, 0));
        graph.get(dest).put(src, new Edge(0, 0)); //backward edge
    }

    public int maxFlow(String s, String t) {
        int maxFlow = 0;
        while (true) {
            Queue<String> q = new LinkedList<>();
            Map<String, Integer> dist = new HashMap<>();
            Map<String, String> parents = new HashMap<>();
            q.add(s);
            dist.put(s, 0);
            while (!q.isEmpty()) {
                String currVertex = q.poll();
                if (currVertex.equals(t)) {
                    break;
                }
                Map<String, Edge> neighbors = graph.getOrDefault(currVertex, new HashMap<>());
                for (String neighbor : neighbors.keySet()) {
                    if (!dist.containsKey(neighbor) && neighbors.get(neighbor).flow < neighbors.get(neighbor).capacity) {
                        dist.put(neighbor, dist.get(currVertex) + 1);
                        q.add(neighbor);
                        parents.put(neighbor, currVertex);
                    }
                }
            }
            int flow = augmentPath(t, parents, Integer.MAX_VALUE);
            if (flow == Integer.MAX_VALUE) {
                break;
            }
            maxFlow += flow;
        }
        return maxFlow;
    }

    public int augmentPath(String t, Map<String, String> parents, int minFlow) {
        if (!parents.containsKey(t)) {
            return minFlow;
        }
        String parent = parents.get(t);
        int residual = graph.get(parent).get(t).capacity - graph.get(parent).get(t).flow;
        int minimum = augmentPath(parent, parents, Math.min(minFlow, residual));
        graph.get(parent).get(t).flow += minimum;
        if (graph.get(t).get(parent).flow < minimum) {
            graph.get(t).get(parent).capacity += (minimum - graph.get(t).get(parent).flow);
            graph.get(t).get(parent).flow = 0;
        } else {
            graph.get(t).get(parent).flow -= minimum;
        }
        return minimum;
    }

    public class Edge {
        private int capacity;
        private int flow;

        public Edge(int capacity, int flow) {
            this.capacity = capacity;
            this.flow = flow;
        }
    }

    public static void main(String[] args) {
        EdmondsKarp edmondsKarp = new EdmondsKarp();
        edmondsKarp.addVertex("0");
        edmondsKarp.addVertex("1");
        edmondsKarp.addVertex("2");
        edmondsKarp.addVertex("3");
        edmondsKarp.addVertex("4");
        edmondsKarp.addUndirectedEdge("0", "2", 100);
        edmondsKarp.addUndirectedEdge("0", "3", 50);
        edmondsKarp.addUndirectedEdge("2", "1", 5);
        edmondsKarp.addUndirectedEdge("2", "4", 5);
        edmondsKarp.addUndirectedEdge("3", "4", 100);
        edmondsKarp.addUndirectedEdge("4", "1", 125);
        System.out.println(edmondsKarp.maxFlow("0", "1")); //60
    }
}