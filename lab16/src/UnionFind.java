import java.util.ArrayList;
import java.util.List;

public class UnionFind {

    List<Integer> sets = new ArrayList<>();

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        for(int i = 0; i < N; i++) {
            sets.add(-1);
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        return -1 * sets.get(find(v));
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        if(v < 0 || v >= sets.size()) {
            throw new IllegalArgumentException();
        }
        return sets.get(v);
    }

    /* Returns true if nodes V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        if(find(v1) == find(v2)) {
            return true;
        }
        return false;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        if(v < 0 || v >= sets.size()) {
            throw new IllegalArgumentException();
        }
        if(sets.get(v) < 0) {
            return v;
        }
        sets.set(v, find(sets.get(v)));
        return sets.get(v);
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. */
    public void union(int v1, int v2) {
        if(v1 == v2) {
            return;
        }
        v1 = find(v1);
        v2 = find(v2);
        if(v1 == v2) {
            return;
        }
        if(sizeOf(v1) > sizeOf(v2)) {
            int v2s = sizeOf(v2);
            sets.set(v2, v1);
            sets.set(v1, -1*(sizeOf(v1) + v2s));
        }
        else {
            int v1s = sizeOf(v1);
            sets.set(v1, v2);
            sets.set(v2, -1*(sizeOf(v2) + v1s));
        }
    }
}
