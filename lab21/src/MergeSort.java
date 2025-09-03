import java.util.Arrays;

public class MergeSort {


    /**
     * @param arr
     *
     * Sort the array arr using merge sort.
     * The merge sort algorithm is as follows:
     * 1. Split the collection to be sorted in half.
     * 2. Recursively call merge sort on each half.
     * 3. Merge the sorted half-lists.
     *
     */
    public static int[] sort(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }
        int[] a = sort(Arrays.copyOfRange(arr, 0, arr.length / 2));
        int[] b = sort(Arrays.copyOfRange(arr, arr.length / 2, arr.length));
        return merge(a, b);
    }

    /**
     * @param a
     * @param b
     *
     * Merge the sorted half-lists.
     *
     * Suggested helper method that will make it easier for you to implement merge sort.
     */
    private static int[] merge(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int i = 0;
        int j = 0;
        while (i < a.length && j < b.length) {
            if (b[j] < a[i]) {
                c[i + j] = b[j];
                j++;
            } else {
                c[i + j] = a[i];
                i++;
            }
        }
        while (i < a.length) {
            c[i + j] = a[i];
            i++;
        }
        while (j < b.length) {
            c[i + j] = b[j];
            j++;
        }
        return c;
    }
}

