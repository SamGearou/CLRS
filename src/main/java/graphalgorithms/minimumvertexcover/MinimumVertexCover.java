package graphalgorithms.minimumvertexcover;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinimumVertexCover {
    private BinaryTree binaryTree;
    private Map<String, Integer> dp;

    public MinimumVertexCover(String val) {
        binaryTree = new BinaryTree(val);
        dp = new HashMap<>();
    }

    public boolean isLeaf(TreeNode node) {
        return node.neighbors.isEmpty();
    }

    public int minimumVertexCover(TreeNode node, int used) {
        String key = node.val + ", " + used;
        int ans = 0;
        if (dp.containsKey(key)) {
            return dp.get(key);
        } else if (isLeaf(node)) { //is a leaf node
            ans = used;
        } else if (used == 0) { //have to select neighbors
            ans = 0;
            for (TreeNode neighbor : node.neighbors) {
                ans += minimumVertexCover(neighbor, 1);
            }
        } else { //used = 1, select minimum of using neighbors and not using neighbors
            ans = 1;
            for (TreeNode neighbor : node.neighbors) {
                ans += Math.min(minimumVertexCover(neighbor, 0), minimumVertexCover(neighbor, 1));
            }
        }
        dp.put(key, ans);
        return ans;
    }

    public static void main(String[] args) {
        MinimumVertexCover vertexCover = new MinimumVertexCover("1");
        vertexCover.binaryTree.root.neighbors.add(new TreeNode("2"));
        vertexCover.binaryTree.root.neighbors.add(new TreeNode("3"));
        vertexCover.binaryTree.root.neighbors.add(new TreeNode("4"));
        int mvc = Math.min(vertexCover.minimumVertexCover(vertexCover.binaryTree.root, 0),
                vertexCover.minimumVertexCover(vertexCover.binaryTree.root, 1));
        System.out.println(mvc);
    }

    public class BinaryTree {
        private TreeNode root;

        public BinaryTree(String val) {
            root = new TreeNode(val);
        }
    }

    public static class TreeNode {
        private String val;
        private List<TreeNode> neighbors;

        public TreeNode(String val) {
            this.val = val;
            neighbors = new ArrayList<>();
        }

        public void addNeighbor(TreeNode newNode) {
            neighbors.add(newNode);
        }
    }
}
