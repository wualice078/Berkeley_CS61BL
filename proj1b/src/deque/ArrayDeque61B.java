package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {

    private static final int SIZEDOWN_LIMIT = 16;
    private int front;
    private int back;
    private T[] ad;
    private int size;

    public ArrayDeque61B() {
        ad = (T[]) new Object[8];
        front = 0;
        back = 1;
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int i = 0;

        public T next() {
            if (!hasNext()) {
                return null;
            }
            T toReturn = get(i);
            i++;
            return toReturn;
        }

        public boolean hasNext() {
            return i != size;
        }
    }

    @Override
    public void addFirst(T x) {
        if (size == ad.length) {
            sizeUp();
        }
        ad[front] = x;
        front--;
        front = Math.floorMod(front, ad.length);
        size++;
    }

    @Override
    public void addLast(T x) {
        if (size == ad.length) {
            sizeUp();
        }
        ad[back] = x;
        back++;
        back = Math.floorMod(back, ad.length);
        size++;
    }

    private void sizeUp() {
        T[] stor = (T[]) new Object[2 * ad.length];
        for (int i = 0; i < size; i++) {
            stor[i] = this.get(i);
        }
        front = stor.length - 1;
        back = size;
        this.ad = stor;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            returnList.add(this.get(i));
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
            if (size <= ad.length / 4 && ad.length >= SIZEDOWN_LIMIT) {
                sizeDown();
            }
            front++;
            front = Math.floorMod(front, ad.length);
            T lost = ad[front];
            ad[front] = null;
            size--;
            return lost;
        }
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            if (size <= ad.length / 4 && ad.length >= SIZEDOWN_LIMIT) {
                sizeDown();
            }
            back--;
            back = Math.floorMod(back, ad.length);
            T lost = ad[back];
            ad[back] = null;
            size--;
            return lost;
        }
    }

    private void sizeDown() {
        T[] stor = (T[]) new Object[ad.length / 2];
        for (int i = 0; i < size; i++) {
            stor[i] = this.get(i);
        }
        front = stor.length - 1;
        back = size;
        this.ad = stor;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            return null;
        } else {
            int i = Math.floorMod(index + front + 1, ad.length);
            return ad[i];
        }
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
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
