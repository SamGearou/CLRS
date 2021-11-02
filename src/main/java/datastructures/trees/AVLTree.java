package datastructures.trees;

public class AVLTree {
    private TreeNode root;

    public AVLTree() {
        this.root = null;
    }

    public void printTree(TreeNode node) {
        if (node != null) {
            printTree(node.left);
            System.out.println(node);
            printTree(node.right);
        }
    }

    public void insert(int val) {
        TreeNode newNode = new TreeNode(val);
        if (root == null) {
            root = newNode;
        } else {
            TreeNode curr = root;
            TreeNode prev = null;
            while (curr != null) {
                prev = curr;
                if (val <= curr.val) {
                    curr = curr.left;
                } else {
                    curr = curr.right;
                }
            }
            if (val <= prev.val) {
                prev.left = newNode;
            } else {
                prev.right = newNode;
            }
            newNode.parent = prev;
            fixUp(newNode.parent);
        }
    }

    public void delete(TreeNode node) {
        if (root != null) {
            if (node.left == null) {
                transplant(node, node.right);
                fixUp(node.parent);
            } else if (node.right == null) {
                transplant(node, node.left);
                fixUp(node.parent);
            } else {
                TreeNode succ = succ(node);
                TreeNode fixNode = null;
                if (succ != node.right) {
                    fixNode = succ.parent;
                    transplant(succ, succ.right);
                    succ.right = node.right;
                    succ.right.parent = succ;
                }
                transplant(node, succ);
                succ.left = node.left;
                succ.left.parent = succ;
                fixNode = fixNode == null ? succ : fixNode;
                fixUp(fixNode);
            }
        }
    }

    public void transplant(TreeNode u, TreeNode v) {
        if (u.parent == null) {
            root = v;
        } else if (u.parent.left == u) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        if (v != null) {
            v.parent = u.parent;
        }
    }

    public TreeNode succ(TreeNode node) {
        if (root != null) {
            if (node.right == null) {
                TreeNode parent = node.parent;
                while (parent != null && parent.right == node) {
                    node = parent;
                    parent = parent.parent;
                }
                return parent;
            } else {
                node = node.right;
                while (node.left != null) {
                    node = node.left;
                }
                return node;
            }
        }
        return null;
    }

    public void fixUp(TreeNode node) {
        while (node != null) {
            updateHeight(node);
            if (getHeight(node.right) - getHeight(node.left) < -1) { // left leaning
                if (getHeight(node.left.right) - getHeight(node.left.left) > 0) { // left rotation
                    node.left = leftRotate(node.left);
                    updateHeight(node.left);
                    updateHeight(node.left.left);
                }
                rightRotate(node);
                updateHeight(node);
                updateHeight(node.parent);
            } else if (getHeight(node.right) - getHeight(node.left) > 1) { // right leaning
                if (getHeight(node.right.right) - getHeight(node.right.left) <= -1) { // right rotation
                    node.right = rightRotate(node.right);
                    updateHeight(node.right);
                    updateHeight(node.right.right);
                }
                leftRotate(node);
                updateHeight(node);
                updateHeight(node.parent);
            }
            node = node.parent;
        }
    }

    public void updateHeight(TreeNode node) {
        if (node != null) {
            node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        }
    }

    public int getHeight(TreeNode node) {
        return node == null ? 0 : node.height;
    }

    public TreeNode leftRotate(TreeNode a) {
        if (root == a) {
            root = a.right;
        }
        TreeNode b = a.right;
        a.right = b.left;
        if (b.left != null) {
            b.left.parent = a;
        }
        b.left = a;
        b.parent = a.parent;
        if (a.parent != null) {
            if (a.parent.left == a) {
                a.parent.left = b;
            } else {
                a.parent.right = b;
            }
        }
        a.parent = b;
        return b;
    }

    public TreeNode rightRotate(TreeNode a) {
        if (root == a) {
            root = a.left;
        }
        TreeNode b = a.left;
        a.left = b.right;
        if (b.right != null) {
            b.right.parent = a;
        }
        b.right = a;
        b.parent = a.parent;
        if (a.parent != null) {
            if (a.parent.left == a) {
                a.parent.left = b;
            } else {
                a.parent.right = b;
            }
        }
        a.parent = b;
        return b;
    }

    public class TreeNode {
        private TreeNode left;
        private TreeNode right;
        private TreeNode parent;
        private int val;
        private int height;

        public TreeNode(int val) {
            left = null;
            right = null;
            parent = null;
            this.val = val;
            this.height = 1;
        }

        public String toString() {
            return "val: " + val + " height: " + height + " parent: " + parent;
        }
    }

    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        avlTree.insert(5);
        avlTree.insert(3);
        avlTree.insert(5);
        avlTree.printTree(avlTree.root);
    }
}