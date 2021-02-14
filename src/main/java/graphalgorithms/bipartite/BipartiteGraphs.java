package graphalgorithms.bipartite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BipartiteGraphs {
    private Map<String, List<String>> graph;

    public BipartiteGraphs() {
        graph = new HashMap<>();
    }

    public void addVertex(String vertex) {
        graph.put(vertex, new ArrayList<>());
    }

    public void addUndirectedEdge(String src, String dest) {
        graph.get(src).add(dest);
        graph.get(dest).add(src);
    }

    public Map<String, List<String>> getGraph() {
        return graph;
    }

    /**
     * Checks to see if a graph is bipartite (2-colorable) using a modified Breadth-First Search
     */
    public boolean isBipartiteGraph(String src) {
        HashMap<String, Integer> color = new HashMap<>();
        Queue<String> q = new LinkedList<>();
        q.add(src);
        color.put(src, 0);
        while (!q.isEmpty()) {
            String currVertex = q.poll();
            for (int i = 0; i < graph.get(currVertex).size(); i++) {
                String neighbor = graph.get(currVertex).get(i);
                if (!color.containsKey(neighbor)) {
                    color.put(neighbor, 1 - color.get(currVertex));
                    q.add(neighbor);
                } else if (color.get(currVertex).equals(color.get(neighbor))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        BipartiteGraphs bipartiteGraphs = new BipartiteGraphs();
        bipartiteGraphs.addVertex("0");
        bipartiteGraphs.addVertex("1");
        bipartiteGraphs.addVertex("2");
        bipartiteGraphs.addVertex("3");
        bipartiteGraphs.addVertex("4");
        bipartiteGraphs.addVertex("5");
        bipartiteGraphs.addUndirectedEdge("0", "1");
        bipartiteGraphs.addUndirectedEdge("1", "2");
        bipartiteGraphs.addUndirectedEdge("1", "3");
        bipartiteGraphs.addUndirectedEdge("1", "4");
        bipartiteGraphs.addUndirectedEdge("1", "5");
        bipartiteGraphs.addUndirectedEdge("4", "5");
        bipartiteGraphs.addUndirectedEdge("4", "4");

        System.out.println(bipartiteGraphs.isBipartiteGraph("0"));
    }
}