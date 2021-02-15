package graphalgorithms.apsp;

import graphalgorithms.graph.AdjacencyMatrix;

//calculates all pairs shortest paths
public class FloydWarshall {

    //Runtime: O(V^3)
    public int[][] allPairsShortestPath(AdjacencyMatrix graph) {
        int numVertices = graph.getNumVertices();
        int[][] allPairs = graph.getGraph();
        for (int k = 0; k < numVertices; k++) {
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    if (allPairs[i][j] > (long) allPairs[i][k] + allPairs[k][j]) {
                        allPairs[i][j] = allPairs[i][k] + allPairs[k][j];
                    }
                }
            }
        }
        return allPairs;
    }

    public static void main(String[] args) {
        AdjacencyMatrix graph = new AdjacencyMatrix(5);
        graph.addEdge(0, 1, 3);
        graph.addEdge(0, 2, 8);
        graph.addEdge(0, 4, -4);
        graph.addEdge(1, 3, 1);
        graph.addEdge(1, 4, 7);
        graph.addEdge(2, 1, 4);
        graph.addEdge(3, 0, 2);
        graph.addEdge(3, 2, -5);
        graph.addEdge(4, 3, 6);
        int[][] allPairs = new FloydWarshall().allPairsShortestPath(graph);
        for (int i = 0; i < allPairs.length; i++) {
            for (int j = 0; j < allPairs.length; j++) {
                System.out.println(allPairs[i][j]);
            }
        }
    }
}
