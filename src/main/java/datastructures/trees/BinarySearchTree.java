package datastructures.trees;

public class BinarySearchTree<T extends Comparable> {
    private BinaryNode<T> root;

    public BinarySearchTree() {
        root = null;
    }

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

    public void transplant(BinaryNode<T> curr, BinaryNode<T> replace) {
        if (curr.parent == null) {
            root = null;
        } else if (curr == curr.parent.left) {
            curr.parent.left = replace;
        } else {
            curr.parent.right = replace;
        }
        if (replace != null) {
            replace.parent = curr.parent;
        }
    }

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


    public class BinaryNode<T extends Comparable> {
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
    }
}
