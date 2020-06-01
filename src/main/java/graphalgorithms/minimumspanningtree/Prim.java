package graphalgorithms.minimumspanningtree;

import datastructures.Heap;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Prim {

    //keeps track of parent links so you can trace MST
    //If you wanted to return list of edges, keep track of min edge added in the for-loop
    //O(ElogV)
    //O(E + VlogV) using a Fibonacci Heap
    public void findMST(Graph<String> graph, Vertex<String> src) {
        Set<Vertex<String>> vertices = graph.getVertices();
        Heap<Vertex<String>> pq = new Heap<>(vertices.size(), Comparator.comparingInt(Vertex::getDist));
        HashSet<Edge<String>> mst = new HashSet<>();
        src.setDist(0);
        for (Vertex<String> v : vertices) {
            pq.add(v);
        }
        while (!pq.isEmpty()) {
            Vertex<String> curr = pq.removeMin();
            System.out.println(curr);
            curr.setVisited(true);
            Set<Edge<String>> neighbors = graph.getNeighbors(curr);
            if (neighbors != null) {
                for (Edge<String> edge : neighbors) {
                    Vertex<String> dest = edge.getDest();
                    int weight = edge.getW();
                    if (!dest.isVisited() && weight < dest.getDist()) {
                        dest.setDist(weight);
                        dest.setParent(curr);
                        pq.decreaseKey(pq.getPos(dest), dest);
                    }
                }
            }
        }
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
    }
}
