package graphalgorithms.eulerian;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//runs in O(V + E) time
public class EulerianCycle {
    private Map<String, ArrayList<Edge>> graph;

    public EulerianCycle() {
        graph = new HashMap<>();
    }

    public void addVertex(String src) {
        graph.put(src, new ArrayList<>());
    }

    public void addUndirectedEdge(String src, String dest) {
        graph.get(src).add(new Edge(dest, false));
        graph.get(dest).add(new Edge(src, false));
    }

    public void eulerianCycle(String src, List<String> path) {
        List<Edge> neighbors = graph.get(src);
        for (int i = 0; i < neighbors.size(); i++) {
            Edge edge = neighbors.get(i);
            if (!edge.used) {
                edge.used = true;
                List<Edge> edgeNeighbors = graph.get(edge.vertex);
                for (int j = 0; j < edgeNeighbors.size(); j++) {
                    Edge edgeNeighbor = edgeNeighbors.get(j);
                    if (edgeNeighbor.vertex.equals(src) && !edgeNeighbor.used) {
                        edgeNeighbor.used = true;
                        break;
                    }
                }
                eulerianCycle(edge.vertex, path);
            }
        }
        path.add(src);
    }

    public class Edge {
        private String vertex;
        private boolean used;

        public Edge(String vertex, boolean used) {
            this.vertex = vertex;
            this.used = used;
        }
    }

    public static void main(String[] args) {
        EulerianCycle eulerianCycle = new EulerianCycle();
        eulerianCycle.addVertex("A");
        eulerianCycle.addVertex("B");
        eulerianCycle.addVertex("C");
        eulerianCycle.addVertex("D");
        eulerianCycle.addVertex("E");
        eulerianCycle.addVertex("F");
        eulerianCycle.addUndirectedEdge("A", "B");
        eulerianCycle.addUndirectedEdge("A", "F");
        eulerianCycle.addUndirectedEdge("B", "C");
        eulerianCycle.addUndirectedEdge("B", "D");
        eulerianCycle.addUndirectedEdge("B", "E");
        eulerianCycle.addUndirectedEdge("C", "D");
        eulerianCycle.addUndirectedEdge("C", "E");
        eulerianCycle.addUndirectedEdge("C", "F");
        eulerianCycle.addUndirectedEdge("D", "E");
        eulerianCycle.addUndirectedEdge("D", "F");
        eulerianCycle.addUndirectedEdge("E", "F");
        List<String> path = new ArrayList<>();
        eulerianCycle.eulerianCycle("A", path);
        for (String vertex : path) {
            System.out.println(vertex);
        }
    }
}
