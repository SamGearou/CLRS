package datastructures;

public class LinkedList<T> {
    private LinkedListNode<T> head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public void add(T data) {
        if (head != null) {
            LinkedListNode<T> curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            LinkedListNode<T> newNode = new LinkedListNode<>(data);
            curr.next = newNode;
        } else {
            head = new LinkedListNode<>(data);
        }
        size++;
    }

    public void add(T data, int pos) {
        if (head != null) {
            LinkedListNode<T> curr = head;
            LinkedListNode<T> prev = null;
            while (pos > 0) {
                prev = curr;
                curr = curr.next;
                pos--;
            }
            LinkedListNode<T> newNode = new LinkedListNode<>(data);
            if (prev == null) {
                newNode.next = head;
                head = newNode;
            } else {
                prev.next = newNode;
                newNode.next = curr;
            }
            size++;
        } else if (pos == 0) {
            head = new LinkedListNode<>(data);
            size++;
        }
    }

    public T remove(int pos) {
        if (head != null) {
            LinkedListNode<T> curr = head;
            LinkedListNode<T> prev = null;
            while (pos > 0) {
                prev = curr;
                curr = curr.next;
                pos--;
            }
            if (prev == null) {
                head = head.next;
            } else {
                T temp = curr.data;
                prev.next = curr.next;
                return temp;
            }
            size--;
        }
        return null;
    }

    public LinkedListNode<T> getHead() {
        return head;
    }

    public void setHead(LinkedListNode<T> newNode) {
        head = newNode;
    }

    public int getSize() {
        return size;
    }

    public T get(int pos) {
        if (pos < 0 || pos >= size) {
            return null;
        }
        LinkedListNode<T> curr = head;
        while (pos > 0) {
            curr = curr.next;
            pos--;
        }
        return curr.data;
    }

    public void setNext(LinkedListNode<T> newNode) {
    }

    public static class LinkedListNode<T extends Object> {
        private T data;
        private LinkedListNode<T> next;

        public LinkedListNode(T data) {
            this.data = data;
            next = null;
        }

        public T getData() {
            return data;
        }

        public void setData(T newData) {
            data = newData;
        }

        public LinkedListNode<T> getNext() {
            return next;
        }

        public void setNext(LinkedListNode<T> newNode) {
            next = newNode;
        }
    }
}
