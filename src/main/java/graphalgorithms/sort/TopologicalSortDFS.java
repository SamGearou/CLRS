package graphalgorithms.sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TopologicalSortDFS {
    private Map<String, List<String>> graph;
    private Set<String> visited;
    private List<String> sortedVertices;

    public TopologicalSortDFS() {
        graph = new HashMap<>();
        visited = new HashSet<>();
        sortedVertices = new ArrayList<>();
    }

    public void addVertex(String vertex) {
        graph.put(vertex, new ArrayList<>());
    }

    public void addDirectedEdge(String src, String dest) {
        graph.get(src).add(dest);
    }

    public Map<String, List<String>> getGraph() {
        return graph;
    }

    public Set<String> getVisited() {
        return visited;
    }

    public void topologicalSortDfs(String src) {
        visited.add(src);
        for (int i = 0; i < graph.get(src).size(); i++) {
            String neighbor = graph.get(src).get(i);
            if (!visited.contains(neighbor)) {
                topologicalSortDfs(neighbor);
            }
        }
        sortedVertices.add(src);
    }

    public static void main(String[] args) {
        TopologicalSortDFS topologicalSortDFS = new TopologicalSortDFS();
        topologicalSortDFS.addVertex("0");
        topologicalSortDFS.addVertex("1");
        topologicalSortDFS.addVertex("2");
        topologicalSortDFS.addVertex("3");
        topologicalSortDFS.addVertex("4");
        topologicalSortDFS.addVertex("5");
        topologicalSortDFS.addVertex("6");
        topologicalSortDFS.addVertex("7");
        topologicalSortDFS.addDirectedEdge("0", "1");
        topologicalSortDFS.addDirectedEdge("0", "2");
        topologicalSortDFS.addDirectedEdge("1", "2");
        topologicalSortDFS.addDirectedEdge("1", "3");
        topologicalSortDFS.addDirectedEdge("2", "3");
        topologicalSortDFS.addDirectedEdge("2", "5");
        topologicalSortDFS.addDirectedEdge("3", "4");
        topologicalSortDFS.addDirectedEdge("7", "6");
        for (String vertex : topologicalSortDFS.getGraph().keySet()) {
            if (!topologicalSortDFS.getVisited().contains(vertex)) {
                topologicalSortDFS.topologicalSortDfs(vertex);
            }
        }
        for (int i = topologicalSortDFS.sortedVertices.size() - 1; i >= 0; i--) { //print topological ordering starting from last element in the list, 7, 6, 0, 1, 2, 5, 3, 4
            System.out.println("Vertex: " + topologicalSortDFS.sortedVertices.get(i));
        }
    }
}