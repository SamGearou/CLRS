package graphalgorithms.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConnectedComponents {
    private Map<String, List<String>> graph;
    private Set<String> visited;

    public ConnectedComponents() {
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

    public Set<String> getVisited() {
        return visited;
    }

    public void dfs(String src) {
        visited.add(src);
        for (int i = 0; i < graph.get(src).size(); i++) {
            String neighbor = graph.get(src).get(i);
            if (!visited.contains(neighbor)) {
                dfs(neighbor);
            }
        }
    }

    public static void main(String[] args) {
        ConnectedComponents connectedComponents = new ConnectedComponents();
        connectedComponents.addVertex("0");
        connectedComponents.addVertex("1");
        connectedComponents.addVertex("2");
        connectedComponents.addVertex("3");
        connectedComponents.addVertex("4");
        connectedComponents.addVertex("5");
        connectedComponents.addVertex("6");
        connectedComponents.addVertex("7");
        connectedComponents.addVertex("8");
        connectedComponents.addUndirectedEdge("0", "1");
        connectedComponents.addUndirectedEdge("1", "2");
        connectedComponents.addUndirectedEdge("1", "3");
        connectedComponents.addUndirectedEdge("2", "3");
        connectedComponents.addUndirectedEdge("3", "4");
        connectedComponents.addUndirectedEdge("7", "6");
        connectedComponents.addUndirectedEdge("6", "8");
        int numComponents = 0;
        for (String vertex : connectedComponents.getGraph().keySet()) {
            if (!connectedComponents.getVisited().contains(vertex)) {
                numComponents++;
                connectedComponents.dfs(vertex);
            }
        }
        System.out.println(numComponents); //3 connected component
    }
}
