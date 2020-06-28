package datastructures.disjointsets;

/**
 * LinkedListed representation of Disjoint Set data structure
 *
 * @param <E> the data that is stored in each Node
 */
public class SetList<E> {
    private Node<E> head;
    private Node<E> tail;

    public SetList() {
        head = null;
        tail = null;
    }

    /**
     * Creates a set with one element
     *
     * @param val the value contained in the Node
     * @return Node<E>
     */
    public Node<E> makeSet(E val) {
        Node<E> node = new Node<>(val);
        this.head = node;
        this.tail = node;
        node.set = this;
        return node;
    }

    /**
     * @param node Node<E>
     * @return Node<E>
     * @pre Node is non-null
     */
    public Node<E> findSet(Node<E> node) {
        if (node != null && node.set != null) {
            return node.set.head;
        }
        return null;
    }

    /**
     * @param x Node<E>
     * @param y Node<E>
     * @return SetList<E>, the representative of disjoint set x.set
     * @pre x and y are not in the same set
     */
    public SetList<E> union(Node<E> x, Node<E> y) {
        if (x == null || x.set == null) {
            return y.set;
        } else if (y == null || y.set == null) {
            return x.set;
        }
        Node<E> xTail = x.set.tail;
        Node<E> yHead = y.set.head;
        xTail.next = yHead;
        x.set.tail = y.set.tail;
        while (yHead != null) {
            yHead.set = x.set;
            yHead = yHead.next;
        }
        return x.set;
    }

    /**
     * The representative (and first element) Node of the disjoint set
     *
     * @return Node<E>
     */
    public Node<E> getHead() {
        return head;
    }

    /**
     * The last element in the disjoint set
     *
     * @return Node<E>
     */
    public Node<E> getTail() {
        return tail;
    }

    /**
     * Class that represents an element in a disjoint set
     *
     * @param <E> the data contained in the element
     */
    public static class Node<E> {
        private E val;
        private SetList<E> set;
        private Node<E> next;

        public Node(E val) {
            this.val = val;
            next = null;
            set = null;
        }

        public E getVal() {
            return val;
        }

        public SetList<E> getSet() {
            return set;
        }

        public Node<E> getNext() {
            return next;
        }
    }
}
