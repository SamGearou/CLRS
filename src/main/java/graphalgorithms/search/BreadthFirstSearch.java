package graphalgorithms.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BreadthFirstSearch {
    private Map<String, List<String>> graph;
    private Set<String> visited;

    public BreadthFirstSearch() {
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

    public void bfs(String src) {
        Queue<String> q = new LinkedList<>();
        q.add(src);
        visited.add(src);
        while (!q.isEmpty()) {
            String currVertex = q.poll();
            System.out.println(currVertex);
            for (int i = 0; i < graph.get(currVertex).size(); i++) {
                String neighbor = graph.get(currVertex).get(i);
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    q.add(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) {
        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();
        breadthFirstSearch.addVertex("0");
        breadthFirstSearch.addVertex("1");
        breadthFirstSearch.addVertex("2");
        breadthFirstSearch.addVertex("3");
        breadthFirstSearch.addVertex("4");
        breadthFirstSearch.addVertex("5");
        breadthFirstSearch.addUndirectedEdge("0", "1");
        breadthFirstSearch.addUndirectedEdge("1", "2");
        breadthFirstSearch.addUndirectedEdge("1", "3");
        breadthFirstSearch.addUndirectedEdge("1", "4");
        breadthFirstSearch.addUndirectedEdge("1", "5");
        breadthFirstSearch.addUndirectedEdge("4", "5");
        breadthFirstSearch.addUndirectedEdge("4", "4");

        breadthFirstSearch.bfs("0");
    }
}
