package datastructures.trees;

import java.util.List;

/**
 * BST implementation
 *
 * @param <T> the data to store in each node
 */
public class BinarySearchTree<T extends Comparable> {
    private BinaryNode<T> root;

    public BinarySearchTree() {
        root = null;
    }

    /**
     * Inserts node into BST
     * Runtime: O(logn)
     *
     * @param val the value to insert
     */
    public void insert(T val) {
        BinaryNode<T> newNode = new BinaryNode<>(val);
        if (root == null) {
            root = newNode;
        } else {
            BinaryNode<T> curr = root;
            BinaryNode<T> prev = null;
            while (curr != null) {
                prev = curr;
                if (val.compareTo(curr.val) < 1) {
                    curr = curr.left;
                } else {
                    curr = curr.right;
                }
            }
            if (val.compareTo(prev.val) < 1) {
                prev.left = newNode;
            } else {
                prev.right = newNode;
            }
            newNode.parent = prev;
        }
    }

    /**
     * Used as a sub-procedure in BST delete method
     * Given two nodes curr and replace, removes curr and puts replace in its place
     * Runtime: O(1)
     *
     * @param curr    the node to remove
     * @param replace the node to replace
     */
    public void transplant(BinaryNode<T> curr, BinaryNode<T> replace) {
        if (curr.parent == null) {
            root = replace;
        } else if (curr == curr.parent.left) {
            curr.parent.left = replace;
        } else {
            curr.parent.right = replace;
        }
        if (replace != null) {
            replace.parent = curr.parent;
        }
    }

    /**
     * Deletes node in BST
     * Runtime: O(logn)
     *
     * @param curr the node to delete
     */
    public void delete(BinaryNode<T> curr) {
        //only a right child (or no children)
        if (curr.left == null) {
            transplant(curr, curr.right);
        }
        //only a left child
        else if (curr.right == null) {
            transplant(curr, curr.left);
        }
        //a left and a right child
        else {
            BinaryNode<T> succ = succ(curr);
            //successor is not curr's right child
            if (succ.parent != curr) {
                transplant(succ, succ.right);
                succ.right = curr.right;
                succ.right.parent = succ;
            }
            transplant(curr, succ);
            succ.left = curr.left;
            succ.left.parent = succ;
        }
    }

    /**
     * The successor (next largest) node in a BST
     * Runtime: O(logn)
     *
     * @param curr the node to get the successor for
     * @return BinaryNode<T>
     */
    public BinaryNode<T> succ(BinaryNode<T> curr) {
        if (root == null) {
            return null;
        } else if (curr.right == null) {
            BinaryNode<T> parent = curr.parent;
            while (parent != null && curr == parent.right) {
                curr = parent;
                parent = parent.parent;
            }
            return parent;
        } else {
            curr = curr.right;
            while (curr.left != null) {
                curr = curr.left;
            }
            return curr;
        }
    }

    /**
     * The predecessor (next smallest) node in a BST
     * Runtime: O(logn)
     *
     * @param curr the node to get the predecessor for
     * @return BinaryNode<T>
     */
    public BinaryNode<T> pred(BinaryNode<T> curr) {
        if (root == null) {
            return null;
        } else if (curr.left == null) {
            BinaryNode<T> parent = curr.parent;
            while (parent != null && curr == parent.left) {
                curr = parent;
                parent = parent.parent;
            }
            return parent;
        } else {
            curr = curr.left;
            while (curr.right != null) {
                curr = curr.right;
            }
            return curr;
        }
    }

    /**
     * Returns a preorder traversal of this BST
     * Runtime: O(n)
     *
     * @param curr the root of the BST
     * @return List<BinaryNode < T>>
     */
    public List<BinaryNode<T>> preOrder(BinaryNode<T> curr, List<BinaryNode<T>> preorder) {
        if (curr != null) {
            preorder.add(curr);
            preOrder(curr.left, preorder);
            preOrder(curr.right, preorder);
        }
        return preorder;
    }

    /**
     * Returns an inorder traversal of this BST
     * Runtime: O(n)
     *
     * @param curr the root of the BST
     * @return List<BinaryNode < T>>
     */
    public List<BinaryNode<T>> inOrder(BinaryNode<T> curr, List<BinaryNode<T>> inorder) {
        if (curr != null) {
            inOrder(curr.left, inorder);
            inorder.add(curr);
            inOrder(curr.right, inorder);
        }
        return inorder;
    }

    /**
     * Returns a postorder traversal of this BST
     * Runtime: O(n)
     *
     * @param curr the root of the BST
     * @return List<BinaryNode < T>>
     */
    public List<BinaryNode<T>> postOrder(BinaryNode<T> curr, List<BinaryNode<T>> postorder) {
        if (curr != null) {
            postOrder(curr.left, postorder);
            postOrder(curr.right, postorder);
            postorder.add(curr);
        }
        return postorder;
    }

    public BinaryNode<T> getRoot() {
        return root;
    }


    public static class BinaryNode<T extends Comparable> {
        private T val;
        private BinaryNode<T> left;
        private BinaryNode<T> right;
        private BinaryNode<T> parent;

        public BinaryNode(T val) {
            this.val = val;
            left = null;
            right = null;
            parent = null;
        }

        public T getVal() {
            return val;
        }

        public BinaryNode<T> getLeft() {
            return left;
        }

        public BinaryNode<T> getRight() {
            return right;
        }

        public BinaryNode<T> getParent() {
            return parent;
        }
    }
}
