package graphalgorithms.graph;

public class AdjacencyMatrix {
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

    public void removeEdge(int src, int dest) {
        graph[src][dest] = Integer.MAX_VALUE;
    }

    public int getEdge(int src, int dest) {
        return graph[src][dest];
    }

    public int[][] getGraph() {
        return graph;
    }

    public int getNumVertices() {
        return numVertices;
    }
}
