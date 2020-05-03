package datastructures.trees;

//Redo Leetcode 731 (My Calendar II) using a Red Black Tree
public class RedBlackTree<T extends Comparable> {
    private Node<T> SENTINEL; //used to represent all 'external' nodes
    private Node<T> root;

    public RedBlackTree() {
        root = null;
        SENTINEL = new Node(null);
        SENTINEL.color = Color.BLACK;
    }

    //assumes x.right is non-null
    private void leftRotate(Node<T> x) {
        Node<T> y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        y.left = x;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.parent = x.parent;
        x.parent = y;
    }

    //assumes x.left is non-null
    private void rightRotate(Node<T> x) {
        Node<T> y = x.left;
        x.left = y.right;
        if (y.right != null) {
            y.right.parent = x;
        }
        y.right = x;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.parent = x.parent;
        x.parent = y;
    }

    private Node<T> minimum(Node<T> x) {
        while (x.left != SENTINEL) {
            x = x.left;
        }
        return x;
    }

    private void insert(Node<T> x) {
        Node<T> curr = root;
        Node<T> prev = null;
        while (curr != null) {
            prev = curr;
            if (x.data.compareTo(curr.data) <= 0) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        if (prev == null) {
            x.parent = SENTINEL;
            root = x;
        } else {
            if (x.data.compareTo(prev.data) <= 0) {
                prev.left = x;
            } else {
                prev.right = x;
            }
        }
        x.color = Color.RED; //set the color to red when inserting
        x.left = SENTINEL;
        x.right = SENTINEL;
        insertFixup(x);
    }

    private void insertFixup(Node<T> x) {
        while (x.parent.color == Color.RED) {
            if (x.parent == x.parent.parent.left) {
                Node<T> s = x.parent.parent.right;
                if (s.color == Color.RED) {
                    s.color = Color.BLACK;
                    x.parent.color = Color.BLACK;
                    x.parent.parent.color = Color.RED;
                    x = x.parent.parent;
                } else {
                    if (x == x.parent.right) {
                        x = x.parent;
                        leftRotate(x);
                    }
                    x.parent.color = Color.BLACK;
                    x.parent.parent.color = Color.RED;
                    rightRotate(x.parent.parent);
                }
            } else {
                Node<T> s = x.parent.parent.left;
                if (s.color == Color.RED) {
                    s.color = Color.BLACK;
                    x.parent.color = Color.BLACK;
                    x.parent.parent.color = Color.RED;
                    x = x.parent.parent;
                } else {
                    if (x == x.parent.left) {
                        x = x.parent;
                        rightRotate(x);
                    }
                    x.parent.color = Color.BLACK;
                    x.parent.parent.color = Color.RED;
                    leftRotate(x.parent.parent);
                }
            }
        }
        root.color = Color.BLACK;
    }

    private void transplant(Node<T> curr, Node<T> replace) {
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

    private void delete(Node<T> z) {
        Node<T> y = z;
        Node<T> x = SENTINEL;
        Color yOriginalColor = y.color;
        if (z.left == SENTINEL) {
            x = z.right;
            transplant(z, z.right);
        } else if (z.right == SENTINEL) {
            x = z.left;
            transplant(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
            if (yOriginalColor == Color.BLACK) {
                deleteFixup(x);
            }
        }
    }

    // x is doubly black inside the outer while
    private void deleteFixup(Node<T> x) {
        while (x != root && x.color == Color.BLACK) {
            if (x == x.parent.left) {
                Node<T> w = x.parent.right;
                if (w.color == Color.RED) { // case 1
                    w.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }
                if (w.left.color == Color.BLACK && w.right.color == Color.BLACK) { // case 2
                    w.color = Color.RED;
                    x = x.parent;
                } else if (w.right.color == Color.BLACK) { // case 3
                    w.left.color = Color.BLACK;
                    w.color = Color.RED;
                    rightRotate(w);
                    w = x.parent.right;
                }
                w.color = x.parent.color;      // case 4
                x.parent.color = Color.BLACK;
                w.right.color = Color.BLACK;
                leftRotate(x.parent);
                x = root;
            } else {
                Node<T> w = x.parent.left;
                if (w.color == Color.RED) {  // case 5
                    w.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }
                if (w.right.color == Color.BLACK && w.left.color == Color.BLACK) { // case 6
                    w.color = Color.RED;
                    x = x.parent;
                } else if (w.left.color == Color.BLACK) {  // case 7
                    w.right.color = Color.BLACK;
                    w.color = Color.RED;
                    leftRotate(w);
                    w = x.parent.left;
                }
                w.color = x.parent.color;  // case 8
                x.parent.color = Color.BLACK;
                w.left.color = Color.BLACK;
                rightRotate(x.parent);
                x = root;
            }
        }
        x.color = Color.BLACK;
    }

    public class Node<T extends Comparable> {
        private Color color;
        private Node parent;
        private Node left;
        private Node right;
        private T data;

        public Node(T data) {
            this.data = data;
            parent = null;
            left = null;
            right = null;
            color = null;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }

    public enum Color {
        RED, BLACK
    }
}
