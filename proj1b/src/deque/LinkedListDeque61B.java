package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {

    private Node sentinel;
    private int size;

    private class Node {
        private T item;
        private Node next;
        private Node prev;

        public Node(T i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
        }

        public Node(T i) {
            item = i;
        }

        public Node() {
        }
    }

    public LinkedListDeque61B() {
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private Node i = sentinel.next;

        public T next() {
            if (!hasNext()) {
                return null;
            }
            T toReturn = i.item;
            i = i.next;
            return toReturn;
        }

        public boolean hasNext() {
            return i != sentinel;
        }
    }

    @Override
    public void addFirst(T x) {
        Node n = new Node(x, sentinel.next, sentinel);
        sentinel.next = n;
        n.next.prev = n;
        size++;
    }

    @Override
    public void addLast(T x) {
        Node n = new Node(x, sentinel, sentinel.prev);
        sentinel.prev = n;
        n.prev.next = n;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (Node p = sentinel.next; p != sentinel; p = p.next) {
            returnList.add(p.item);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            T removed = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size--;
            return removed;
        }
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            T removed = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size--;
            return removed;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        for (Node p = sentinel.next; p != sentinel; p = p.next) {
            if (index == 0) {
                return p.item;
            }
            index--;
        }
        return null;
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node p = sentinel.next;
        return getRecursiveHelper(p, index);
    }

    private T getRecursiveHelper(Node p, int i) {
        if (i == 0) {
            return p.item;
        } else {
            return getRecursiveHelper(p.next, i - 1);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Deque61B<?> other)) {
            return false;
        }
        if (size != other.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!this.get(i).equals(other.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return this.toList().toString();
    }
}
