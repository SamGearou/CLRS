package graphalgorithms;

import datastructures.LinkedList;

import java.util.List;
import java.util.Map;

import static graphalgorithms.DFS.Node;
import static graphalgorithms.DFS.Node.Color;

//Alternative way to topological sort (the way not shown in this class) would be to
//run dfs on each vertex with incoming edges equal to zero
public class TopologicalSort {
    private int time = 0;

    //using two colors, WHITE = not visited, BLACK = visited
    //Baic idea: add vertex (node) to front of linkedList when it becomes 'finished'
    public LinkedList<Node> topologicalSort(Map<Node, List<Node>> graph) {
        time = 0;
        LinkedList<Node> root = new LinkedList<>();
        for (Node node : graph.keySet()) {
            if (node.getColor() != Color.BLACK) {
                dfsVisit(graph, node, root);
            }
        }
        return root;
    }

    private void dfsVisit(Map<Node, List<Node>> graph, Node node, LinkedList<Node> root) {
        time++;
        node.setDiscoverTime(time);
        node.setColor(Color.BLACK);
        List<Node> neighbors = graph.get(node);
        if (neighbors != null) {
            for (Node neighbor : neighbors) {
                if (neighbor.getColor() != Color.BLACK) {
                    dfsVisit(graph, neighbor, root);
                }
            }
        }
        time++;
        node.setFinishTime(time);
        root.add(node, 0);
    }
}
