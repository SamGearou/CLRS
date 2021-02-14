package graphalgorithms.check;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphCheck {
    private Map<String, List<String>> graph;
    private Map<String, String> parent;
    private Map<String, Integer> states;

    public GraphCheck() {
        graph = new HashMap<>();
        parent = new HashMap<>();
        states = new HashMap<>(); //null = UNVISITED, 0 = EXPLORED, 1 = VISITED
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

    public Map<String, Integer> getStates() {
        return states;
    }

    /**
     * Categorizes vertex states of a graph into three categories:
     * 1) EXPLORED - vertex that has been visited but not yet complete
     * 2) VISITED - vertex that has been visited and complete
     * 3) UNVISITED - vertex that has not been explored
     * <p>
     * This algorithm then categorizes edges of a graph into three categories:
     * 1) Tree Edge: The edge traversed by DFS from a vertex with a state currently EXPLORED to a vertex with state UNVISITED
     * 2) Back Edge: Edge that is part of a cycle, i.e., an edge from a vertex with state EXPLORED to a vertex with state EXPLORED
     * 3) Forward/Cross Edge: edges from vertex with state EXPLORED to vertex with state VISITED
     */
    public void graphCheck(String src) {
        states.put(src, 0);
        for (int i = 0; i < graph.get(src).size(); i++) {
            String neighbor = graph.get(src).get(i);
            if (!states.containsKey(neighbor)) { //EXPLORED --> UNVISITED
                parent.put(neighbor, src);
                graphCheck(neighbor);
            } else if (states.get(neighbor) == 0) { //EXPLORED --> EXPLORED, cycle!
                //check to make sure cycle has length 3 or more
                if (!neighbor.equals(parent.get(src))) {
                    //There is a cycle!
                    System.out.println("Cycle Edge: " + src + " --> " + neighbor);
                } else {
                    //Undirected edge!
                    System.out.println("Undirected Edge: " + src + " --> " + neighbor);
                }
            } else if (states.get(neighbor) == 1) { //EXPLORED --> VISITED
                //Forward/Cross edge!
                System.out.println("Forward/Cross Edge: " + src + " --> " + neighbor);
            }
        }
        states.put(src, 1);
    }

    public static void main(String[] args) {
        GraphCheck graphCheck = new GraphCheck();
        graphCheck.addVertex("0");
        graphCheck.addVertex("1");
        graphCheck.addVertex("2");
        graphCheck.addVertex("3");
        graphCheck.addVertex("4");
        graphCheck.addVertex("5");
        graphCheck.addVertex("6");
        graphCheck.addVertex("7");
        graphCheck.addVertex("8");
        graphCheck.addUndirectedEdge("0", "1");
        graphCheck.addUndirectedEdge("1", "2");
        graphCheck.addUndirectedEdge("1", "3");
        graphCheck.addUndirectedEdge("2", "3");
        graphCheck.addUndirectedEdge("3", "4");
        graphCheck.addUndirectedEdge("7", "6");
        graphCheck.addUndirectedEdge("6", "8");
        int component = 0;
        for (String vertex : graphCheck.getGraph().keySet()) {
            if (!graphCheck.getStates().containsKey(vertex)) {
                System.out.println(String.format("Component %s", component++));
                graphCheck.graphCheck(vertex);
            }
        }
    }
}
