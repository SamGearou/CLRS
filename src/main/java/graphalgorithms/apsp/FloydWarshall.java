package graphalgorithms.apsp;

//calculates all pairs shortest paths
public class FloydWarshall {

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

    public static class AdjacencyMatrix {
        private int numVertices;
        private int[][] graph;

        public AdjacencyMatrix(int numVertices) {
            this.numVertices = numVertices;
            graph = new int[numVertices][numVertices];
            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph[i].length; j++) {
                    graph[i][j] = i == j ? 0 : Integer.MAX_VALUE;
                }
            }
        }

        public void addEdge(int src, int dest, int weight) {
            graph[src][dest] = weight;
        }

        public int[][] getGraph() {
            return graph;
        }

        public int getNumVertices() {
            return numVertices;
        }
    }

    public static void main(String[] args) {
        AdjacencyMatrix graph = new AdjacencyMatrix(5);
        FloydWarshall floydWarshall = new FloydWarshall();
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 1);
        graph.addEdge(0, 4, 3);
        graph.addEdge(1, 3, 4);
        graph.addEdge(2, 1, 1);
        graph.addEdge(2, 4, 1);
        graph.addEdge(3, 0, 1);
        graph.addEdge(3, 2, 3);
        graph.addEdge(3, 4, 5);
        int[][] allPairs = floydWarshall.allPairsShortestPath(graph);
        for (int i = 0; i < graph.numVertices; i++) {
            for (int j = 0; j < graph.numVertices; j++) {
                System.out.print("(" + i + ", " + j + "): " + allPairs[i][j] + " ");
            }
            System.out.println();
        }
    }
}