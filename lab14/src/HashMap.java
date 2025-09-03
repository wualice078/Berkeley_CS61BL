import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class HashMap<K, V> implements Map61BL<K, V> {

    private LinkedList<Entry<K, V>>[] buckets;
    private int capacity = 16;
    private final int initialCapacity;
    private int size;
    private double loadFactor = 0.75;

    public int capacity() {
        return capacity;
    }

    public HashMap(){
        initialCapacity = 16;
        capacity = initialCapacity;
        buckets = (LinkedList<Entry<K, V>>[]) new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<Entry<K, V>>();
        }
    }

    public HashMap(int capacity) {
        initialCapacity = capacity;
        this.capacity = initialCapacity;
        buckets = (LinkedList<Entry<K, V>>[]) new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<Entry<K, V>>();
        }
    }

    public HashMap(int capacity, double loadFactor) {
        initialCapacity = capacity;
        this.capacity = initialCapacity;
        this.loadFactor = loadFactor;
        buckets = (LinkedList<Entry<K, V>>[]) new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<Entry<K, V>>();
        }
    }

    @Override
    public void clear() {
        capacity = initialCapacity;
        buckets = (LinkedList<Entry<K, V>>[]) new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<Entry<K, V>>();
        }
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        int bucket = Math.floorMod(key.hashCode(), capacity);
        for(Entry<K, V> entry : buckets[bucket]) {
            if(entry.key.equals(key))
                return true;
        }
        return false;
    }

    @Override
    public V get(K key) {
        int bucket = Math.floorMod(key.hashCode(), capacity);
        for(Entry<K, V> entry : buckets[bucket]) {
            if(entry.key.equals(key))
                return entry.value;
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        int bucket = Math.floorMod(key.hashCode(), capacity);
        for(Entry<K, V> entry : buckets[bucket]) {
            if(entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        buckets[bucket].add(new Entry(key, value));
        size++;
        if((double) size/capacity > loadFactor) {
            resize();
        }
    }

    private void resize() {
        capacity *= 2;
        HashMap<K, V> newHash = new HashMap(capacity);

        for(int i = 0; i < buckets.length; i++) {
            for(Entry<K, V> entry : buckets[i])
                newHash.put(entry.key, entry.value);
        }
        buckets = newHash.buckets;
    }

    @Override
    public V remove(K key) {
        int bucket = Math.floorMod(key.hashCode(), capacity);
        V val = get(key);
        if(val != null) {
            buckets[bucket].remove(new Entry(key, val));
            size--;
            return val;
        }
        return null;
    }

    @Override
    public boolean remove(K key, V value) {
        int bucket = Math.floorMod(key.hashCode(), capacity);
        boolean contains = buckets[bucket].remove(new Entry(key, value));
        if(contains) {
            size--;
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<K> iterator() {
        return new HashMapIterator();
    }

    private class HashMapIterator implements Iterator<K>{
        private int i;
        private Iterator<Entry<K, V>> bucketsItr = buckets[i].iterator();

        @Override
        public boolean hasNext() {
            if(!bucketsItr.hasNext()) {
                i = advanceToNextBucket(i);
                if(i == capacity)
                    return false;
                bucketsItr = buckets[i].iterator();
                return true;
            }
            return true;
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return bucketsItr.next().key;
        }

        private int advanceToNextBucket(int i) {
            i++;
            while(i < capacity && buckets[i].isEmpty())
                i++;
            return i;
        }
    }

    private static class Entry<K, V> {

        private K key;
        private V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /* Returns true if this key matches with the OTHER's key. */
        public boolean keyEquals(Entry other) {
            return key.equals(other.key);
        }

        /* Returns true if both the KEY and the VALUE match. */
        @Override
        public boolean equals(Object other) {
            return (other instanceof Entry
                    && key.equals(((Entry) other).key)
                    && value.equals(((Entry) other).value));
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }
}
