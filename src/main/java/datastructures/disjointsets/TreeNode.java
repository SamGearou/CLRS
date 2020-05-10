package datastructures.disjointsets;

//Leetcode 721 (Accounts Merge) - re-implement with union-find with path compression
//Leetcode 684 (Redundant Connection) - re-implement with union-find with path compression
/**
 *  Alone, union by rank yields a running time of O(mlogn) for a sequence of m operations and n makeSet operations (m <= n)
 * When we use both union by rank and path compression, the worst-case running
 * time is O(m * a(n)), where a(n) is a very slowly growing function
 *
 * In any conceivable application of a disjoint-set data structure,
 * a(n) <= 4; thus, we can view the running time as linear in m in all practical situations.
 * Strictly speaking, however, it is superlinear.
 */
public class TreeNode<T extends Comparable> {
    private T data;
    private TreeNode<T> parent;
    private int rank; //upper bound on the height of the TreeNode

    public TreeNode(T data) {
        this.data = data;
        makeSet(this);
    }

    /**
     * Creates a set with one element, x
     * The amortized cost of each makeSet operation is O(1)
     * @param node Element to add to empty set
     * @return node
     */
    public void makeSet(TreeNode<T> node) {
        node.parent = node;
        node.rank = 0;
    }

    /**
     * @param node finds the representative element of the set that x belongs to (if it exists)
     * The amortized cost of each findSet operation is O(a(n))
     * @return representative element
     */
    public TreeNode<T> findSet(TreeNode<T> node) {
        if (node != node.parent) {
            node.parent = findSet(node.parent);
        }
        return node.parent;
    }


    /**
     * Combines two disjoint sets into one (note this method assumes the sets are disjoint)
     * The amortized cost of each union operation is O(a(n))
     * @param x first set
     * @param y second set
     * @return new set
     */
    public TreeNode<T> union(TreeNode<T> x, TreeNode<T> y) {
        return link(findSet(x), findSet(y));
    }

    /**
     * Helper method for #union to combine two disjoint sets
     * The amortized cost of each link operation is O(a(n))
     * @param x first set
     * @param y second set
     * @return root of new set
     */
    public TreeNode<T> link(TreeNode<T> x, TreeNode<T> y) {
        if (y.rank < x.rank) {
            y.parent = x;
            return x;
        } else {
            if (x.rank == y.rank) {
                y.rank += 1;
            }
            x.parent = y;
            return y;
        }
    }
}
