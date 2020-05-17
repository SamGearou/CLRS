package graphalgorithms.minimumspanningtree;

import datastructures.disjointsets.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Kruskal {
    private HashMap<Vertex<String>, TreeNode<Vertex<String>>> disjointSet;

    public Kruskal() {
        disjointSet = new HashMap<>();
    }

    public List<Edge<String>> findMST(Graph<String> graph) {
        List<Edge<String>> mst = new ArrayList<>();
        Set<Vertex<String>> vertices = graph.getVertices();
        List<Edge<String>> edges = new ArrayList<>(graph.getEdges());
        for (Vertex<String> vertex : vertices) {
            TreeNode<Vertex<String>> set = new TreeNode<>(vertex);
            disjointSet.put(vertex, set);
        }
        //sort edges by weight
        Collections.sort(edges, Comparator.comparingInt(Edge::getW));
        for (Edge<String> edge : edges) {
            TreeNode<Vertex<String>> src = disjointSet.get(edge.getSrc());
            TreeNode<Vertex<String>> dest = disjointSet.get(edge.getDest());
            if (src.findSet(src) != dest.findSet(dest)) {
                mst.add(edge);
                src.union(src, dest);
            }
        }
        return mst;
    }

    public static void main(String[] args) {
        Graph<String> graph = new Graph<>();
        Vertex<String> a = new Vertex<>("a");
        Vertex<String> b = new Vertex<>("b");
        Vertex<String> c = new Vertex<>("c");
        Vertex<String> d = new Vertex<>("d");
        Vertex<String> e = new Vertex<>("e");
        Vertex<String> f = new Vertex<>("f");
        Vertex<String> g = new Vertex<>("g");
        Vertex<String> h = new Vertex<>("h");
        Vertex<String> i = new Vertex<>("i");
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addVertex(e);
        graph.addVertex(f);
        graph.addVertex(g);
        graph.addVertex(h);
        graph.addVertex(i);
        graph.addUndirectedEdge(a, b, 4);
        graph.addUndirectedEdge(a, h, 8);
        graph.addUndirectedEdge(b, h, 11);
        graph.addUndirectedEdge(h, i, 7);
        graph.addUndirectedEdge(b, c, 8);
        graph.addUndirectedEdge(h, g, 1);
        graph.addUndirectedEdge(i, c, 2);
        graph.addUndirectedEdge(i, g, 6);
        graph.addUndirectedEdge(g, f, 2);
        graph.addUndirectedEdge(c, f, 4);
        graph.addUndirectedEdge(c, d, 7);
        graph.addUndirectedEdge(d, f, 14);
        graph.addUndirectedEdge(d, e, 9);
        graph.addUndirectedEdge(f, e, 10);
        List<Edge<String>> mst = new Kruskal().findMST(graph);
        for (Edge<String> edge : mst) {
            System.out.println(edge.getW());
        }
    }
}
