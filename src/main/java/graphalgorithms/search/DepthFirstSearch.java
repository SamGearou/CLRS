package graphalgorithms.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DepthFirstSearch {
    private Map<String, List<String>> graph;
    private Set<String> visited;

    public DepthFirstSearch() {
        graph = new HashMap<>();
        visited = new HashSet<>();
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

    public void dfs(String src) {
        visited.add(src);
        System.out.println(src);
        for (int i = 0; i < graph.get(src).size(); i++) {
            String neighbor = graph.get(src).get(i);
            if (!visited.contains(neighbor)) {
                dfs(neighbor);
            }
        }
    }

    public static void main(String[] args) {
        DepthFirstSearch depthFirstSearch = new DepthFirstSearch();
        depthFirstSearch.addVertex("0");
        depthFirstSearch.addVertex("1");
        depthFirstSearch.addVertex("2");
        depthFirstSearch.addVertex("3");
        depthFirstSearch.addVertex("4");
        depthFirstSearch.addVertex("5");
        depthFirstSearch.addUndirectedEdge("0", "1");
        depthFirstSearch.addUndirectedEdge("1", "2");
        depthFirstSearch.addUndirectedEdge("1", "3");
        depthFirstSearch.addUndirectedEdge("1", "4");
        depthFirstSearch.addUndirectedEdge("1", "5");
        depthFirstSearch.addUndirectedEdge("4", "5");
        depthFirstSearch.addUndirectedEdge("4", "4");

        depthFirstSearch.dfs("0");
    }
}
