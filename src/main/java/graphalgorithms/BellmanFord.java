package graphalgorithms;

import graphalgorithms.minimumspanningtree.Edge;
import graphalgorithms.minimumspanningtree.Graph;
import graphalgorithms.minimumspanningtree.Vertex;

public class BellmanFord {

    //Running time: O(V * E)
    //Can detect negative edge-weight cycles reachable from src
    //Returns true when src cannot reach a negative edge-weight cycle, false otherwise
    public boolean shortestPath(Graph<String> graph, Vertex<String> src) {
        src.setDist(0);
        int numVertices = graph.getVertices().size();
        for (int i = 0; i < numVertices - 1; i++) {
            for (Edge<String> edge : graph.getEdges()) {
                Vertex<String> s = edge.getSrc();
                Vertex<String> e = edge.getDest();
                int w = edge.getW();
                if (e.getDist() > (long) s.getDist() + w) {
                    e.setDist(s.getDist() + w);
                    e.setParent(s);
                }
            }
        }
        for (Edge<String> edge : graph.getEdges()) {
            Vertex<String> s = edge.getSrc();
            Vertex<String> e = edge.getDest();
            int w = edge.getW();
            if (e.getDist() > (long) s.getDist() + w) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Graph<String> graph = new Graph();
        Vertex<String> s = new Vertex<>("s");
        Vertex<String> t = new Vertex<>("t");
        Vertex<String> y = new Vertex<>("y");
        Vertex<String> z = new Vertex<>("z");
        Vertex<String> x = new Vertex<>("x");
        graph.addDirectedEdge(s, t, 6);
        graph.addDirectedEdge(s, y, 7);
        graph.addDirectedEdge(t, x, 5);
        graph.addDirectedEdge(t, y, 8);
        graph.addDirectedEdge(y, z, 9);
        graph.addDirectedEdge(y, x, -3);
        graph.addDirectedEdge(z, x, 7);
        graph.addDirectedEdge(z, s, 2);
        graph.addDirectedEdge(x, t, -2);
        graph.addDirectedEdge(t, z, -4);
        System.out.println(new BellmanFord().shortestPath(graph, s));
        for (Vertex<String> vertex : graph.getVertices()) {
            System.out.println(vertex.getData() + ": " + vertex.getDist());
        }
    }
}
