package graphalgorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import static graphalgorithms.Vertex.Color;

public class StronglyConnectedComponents {

    //run dfs on graph G, pushing vertices onto stack when they are 'finished'
    //Then run dfs on transpose of G, pushing vertices from stack in decreasing finish time
    public void stronglyConnectedComponents(Map<Vertex, List<Vertex>> graph) {
        Stack<Vertex> stack = new Stack<>();
        for (Vertex vertex : graph.keySet()) {
            if (vertex.getColor() != Color.BLACK) {
                dfsVisit(graph, vertex, stack);
            }
        }
        Map<Vertex, List<Vertex>> transpose = transposeGraph(graph);
        while (!stack.isEmpty()) {
            Vertex vertex = stack.pop();
            if (vertex.getColor() != Color.WHITE) {
                dfsVisitAndPrint(transpose, vertex);
                System.out.println();
            }
        }
    }

    private void dfsVisit(Map<Vertex, List<Vertex>> graph, Vertex vertex, Stack<Vertex> stack) {
        vertex.setColor(Color.BLACK);
        List<Vertex> neighbors = graph.get(vertex);
        if (neighbors != null) {
            for (Vertex neighbor : neighbors) {
                if (neighbor.getColor() == Color.WHITE) {
                    neighbor.setParent(vertex);
                    dfsVisit(graph, neighbor, stack);
                }
            }
        }
        stack.push(vertex);
    }

    private void dfsVisitAndPrint(Map<Vertex, List<Vertex>> graph, Vertex vertex) {
        System.out.print(vertex.getV() + " ");
        vertex.setColor(Color.WHITE);
        List<Vertex> neighbors = graph.get(vertex);
        if (neighbors != null) {
            for (Vertex neighbor : neighbors) {
                if (neighbor.getColor() == Color.BLACK) {
                    neighbor.setParent(vertex);
                    dfsVisitAndPrint(graph, neighbor);
                }
            }
        }
    }

    public Map<Vertex, List<Vertex>> transposeGraph(Map<Vertex, List<Vertex>> graph) {
        Map<Vertex, List<Vertex>> transpose = new HashMap<>();
        for (Vertex vertex : graph.keySet()) {
            List<Vertex> neighbors = graph.get(vertex);
            if (neighbors != null) {
                for (Vertex neighbor : neighbors) {
                    List<Vertex> transposeNeighbors = transpose.getOrDefault(neighbor, new ArrayList<Vertex>());
                    transposeNeighbors.add(vertex);
                    transpose.put(neighbor, transposeNeighbors);
                }
            }
        }
        return transpose;
    }

    public static void main(String[] args) {
        HashMap<Vertex, List<Vertex>> graph = new HashMap<>();
        Vertex zero = new Vertex(0);
        Vertex one = new Vertex(1);
        Vertex two = new Vertex(2);
        Vertex three = new Vertex(3);
        Vertex four = new Vertex(4);
        Vertex five = new Vertex(5);
        Vertex six = new Vertex(6);
        Vertex seven = new Vertex(7);
        Vertex eight = new Vertex(8);
        graph.put(zero, List.of(three));
        graph.put(one, List.of(zero));
        graph.put(two, List.of(one));
        graph.put(three, List.of(two));
        graph.put(four, List.of(two, six));
        graph.put(five, List.of(four));
        graph.put(six, List.of(five, seven));
        graph.put(seven, List.of());
        graph.put(eight, List.of(seven));
        new StronglyConnectedComponents().stronglyConnectedComponents(graph);
    }
}
