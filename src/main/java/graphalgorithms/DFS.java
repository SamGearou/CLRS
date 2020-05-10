package graphalgorithms;

import java.util.List;
import java.util.Map;

public class DFS {
    private int time = 0;

    //recursive DFS with discovery and finish times
    //colors are used if you wanted to create a dfs-forest (a set of disjoint dfs-trees), the colors ensure
    //that a node only belongs to one dfs-tree (hence, the collection of dfs-trees are disjoint)
    public Map<Node, List<Node>> dfs(Map<Node, List<Node>> graph) {
        time = 0;
        for (Node node : graph.keySet()) {
            if (node.color == Node.Color.WHITE) {
                dfsVisit(graph, node);
            }
        }
        return graph;
    }

    private void dfsVisit(Map<Node, List<Node>> graph, Node node) {
        time++;
        node.discoverTime = time;
        node.color = Node.Color.GREY;
        List<Node> neighbors = graph.get(node);
        if (neighbors != null) {
            for (Node neighbor : neighbors) {
                if (neighbor.color == Node.Color.WHITE) {
                    neighbor.parent = node;
                    dfsVisit(graph, neighbor);
                }
            }
        }
        node.color = Node.Color.BLACK;
        time++;
        node.finishTime = time;
    }

    public static class Node {
        private int v;
        private Node parent;
        private int discoverTime;
        private int finishTime;
        private Color color;

        public enum Color {
            WHITE, GREY, BLACK;
        }

        public Node(int v) {
            this.v = v;
            parent = null;
            discoverTime = -1;
            finishTime = -1;
            color = Color.WHITE;
        }

        public int getV() {
            return v;
        }

        public void setV(int v) {
            this.v = v;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public int getDiscoverTime() {
            return discoverTime;
        }

        public void setDiscoverTime(int discoverTime) {
            this.discoverTime = discoverTime;
        }

        public int getFinishTime() {
            return finishTime;
        }

        public void setFinishTime(int finishTime) {
            this.finishTime = finishTime;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }
    }
}
