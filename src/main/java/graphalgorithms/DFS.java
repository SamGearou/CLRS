package graphalgorithms;

import java.util.List;
import java.util.Map;

public class DFS {
    private int time = 0;

    //recursive DFS with discovery and finish times
    //colors are used if you wanted to create a dfs-forest (a set of disjoint dfs-trees), the colors ensure
    //that a node only belongs to one dfs-tree (hence, the collection of dfs-trees are disjoint)
    public Map<Vertex, List<Vertex>> dfs(Map<Vertex, List<Vertex>> graph) {
        time = 0;
        for (Vertex vertex : graph.keySet()) {
            if (vertex.getColor() == Vertex.Color.WHITE) {
                dfsVisit(graph, vertex);
            }
        }
        return graph;
    }

    private void dfsVisit(Map<Vertex, List<Vertex>> graph, Vertex vertex) {
        time++;
        vertex.setDiscoverTime(time);
        vertex.setColor(Vertex.Color.GREY);
        List<Vertex> neighbors = graph.get(vertex);
        if (neighbors != null) {
            for (Vertex neighbor : neighbors) {
                if (neighbor.getColor() == Vertex.Color.WHITE) {
                    neighbor.setParent(vertex);
                    dfsVisit(graph, neighbor);
                }
            }
        }
        vertex.setColor(Vertex.Color.BLACK);
        time++;
        vertex.setFinishTime(time);
    }
}
