package graphalgorithms.flow;

/**
 * Flow Network assumption:
 * directed graph
 * if (u,v) is not an edge in G, then c(u,v) = 0 (c = capacity function)
 * if (u,v) is an edge in G, then (v,u) is not an edge in G
 * c(u,v) >= 0 for each edge (u,v) in G
 * Antiparallel edge - (u,v) is an element of E and (v,u) is an element of E
 * Augmenting Path - a path of edges in the residual graph with unused capacity
 * from the source s to the sink t
 * Augmenting the flow - update the flow values of the edges along the augmenting path
 * Residual edge - backwards edge in the residual graph
 * Residual graph - the graph which also contains residual edges
 */
public class MaximumFlow {

    /**
     * Finds the max flow of the graph using Edmonds-Karp algorithm
     *
     * @param graph Residual Graph
     * @return int, the max flow
     */
    public int maximumFlow(ResidualGraph<String> graph) {
        return 0;
    }

    public int bfs() {
        return 0;
    }
}
