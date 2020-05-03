package datastructures.disjointsets;

public class SetList<E> {
    private ListNode<E> head;
    private ListNode<E> tail;

    public SetList() {
        head = null;
        tail = null;
    }

    public void makeSet(E val) {
        ListNode<E> node = new ListNode<>(val);
        SetList<E> set = new SetList<>();
        set.head = node;
        set.tail = node;
        node.set = set;
    }

    public ListNode<E> findSet(ListNode<E> node) {
        if (node.set != null) {
            return node.set.head;
        }
        return null;
    }

    //assumes x and y are in different sets
    public SetList<E> union(ListNode<E> x, ListNode<E> y) {
        if (x == null || x.set == null) {
            return y.set;
        } else if (y == null || y.set == null) {
            return x.set;
        }
        ListNode<E> xTail = x.set.tail;
        ListNode<E> yHead = y.set.head;
        xTail.next = yHead;
        x.set.tail = y.set.tail;
        while (yHead != null) {
            yHead.set = x.set;
            yHead = yHead.next;
        }
        return x.set;
    }

    public class ListNode<E> {
        private E val;
        private SetList<E> set;
        private ListNode<E> next;

        public ListNode(E val) {
            this.val = val;
            next = null;
            set = null;
        }
    }
}
