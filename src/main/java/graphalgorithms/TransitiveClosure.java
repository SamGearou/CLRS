package graphalgorithms;

import graphalgorithms.graph.AdjacencyMatrix;

//transitive closure: determines for all pairs of vertices i and j if there exists
//a path from i to j. If there exists a path from i to j, closure[i][j] = 1,
//otherwise closure[i][j] = 0
public class TransitiveClosure {

    //Runtime: O(V^3), same (and basically the same algorithm as) Floyd-Warshall
    public int[][] transitiveClosure(AdjacencyMatrix graph) {
        int[][] closure = graph.getGraph();
        for (int k = 0; k < closure.length; k++) {
            for (int i = 0; i < closure.length; i++) {
                for (int j = 0; j < closure.length; j++) {
                    closure[i][j] = (closure[i][j] != 0 || (closure[i][k] != 0 && closure[k][j] != 0)) ? 1 : 0;
                }
            }
        }
        return closure;
    }

    public static void main(String[] args) {
        AdjacencyMatrix graph = new AdjacencyMatrix(4);
        graph.addEdge(0, 0, 1);
        graph.addEdge(0, 1, 0);
        graph.addEdge(0, 2, 0);
        graph.addEdge(0, 3, 0);

        graph.addEdge(1, 0, 0);
        graph.addEdge(1, 1, 1);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 1);

        graph.addEdge(2, 0, 0);
        graph.addEdge(2, 1, 1);
        graph.addEdge(2, 2, 1);
        graph.addEdge(2, 3, 0);

        graph.addEdge(3, 0, 1);
        graph.addEdge(3, 1, 0);
        graph.addEdge(3, 2, 1);
        graph.addEdge(3, 3, 1);
        int[][] allPairs = new TransitiveClosure().transitiveClosure(graph);
        for (int i = 0; i < allPairs.length; i++) {
            for (int j = 0; j < allPairs.length; j++) {
                System.out.print(allPairs[i][j] + " ");
            }
            System.out.println();
        }
    }
}
