package graphalgorithms.closure;

//transitive closure: determines for all pairs of vertices i and j if there exists
//a path from i to j. If there exists a path from i to j, closure[i][j] = 1,
//otherwise closure[i][j] = 0
public class TransitiveClosure {

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

    public static class AdjacencyMatrix {
        private int[][] graph;

        public AdjacencyMatrix(int numVertices) {
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