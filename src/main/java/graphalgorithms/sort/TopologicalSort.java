package graphalgorithms.sort;

import datastructures.LinkedList;
import graphalgorithms.Vertex;

import java.util.List;
import java.util.Map;

import static graphalgorithms.Vertex.Color;


//Alternative way to topological sort (the way not shown in this class) would be to
//run dfs on each vertex with incoming edges equal to zero
public class TopologicalSort {
    private int time = 0;

    //using two colors, WHITE = not visited, BLACK = visited
    //Baic idea: add vertex (node) to front of linkedList when it becomes 'finished'
    public LinkedList<Vertex> topologicalSort(Map<Vertex, List<Vertex>> graph) {
        time = 0;
        LinkedList<Vertex> root = new LinkedList<>();
        for (Vertex vertex : graph.keySet()) {
            if (vertex.getColor() != Color.BLACK) {
                dfsVisit(graph, vertex, root);
            }
        }
        return root;
    }

    private void dfsVisit(Map<Vertex, List<Vertex>> graph, Vertex vertex, LinkedList<Vertex> root) {
        time++;
        vertex.setDiscoverTime(time);
        vertex.setColor(Color.BLACK);
        List<Vertex> neighbors = graph.get(vertex);
        if (neighbors != null) {
            for (Vertex neighbor : neighbors) {
                if (neighbor.getColor() != Color.BLACK) {
                    dfsVisit(graph, neighbor, root);
                }
            }
        }
        time++;
        vertex.setFinishTime(time);
        root.add(vertex, 0);
    }
}
