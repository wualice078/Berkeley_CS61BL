import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class QuickSort {

    /**
     * @param arr
     *
     * Sort the array arr using quicksort with the 3-scan partition algorithm.
     * The quicksort algorithm is as follows:
     * 1. Select a pivot, partition array in place around the pivot.
     * 2. Recursively call quicksort on each subsection of the modified array.
     */
    public static int[] sort(int[] arr) {
        quickSort(arr, 0, arr.length);
        return arr;
    }

    /**
     * @param arr
     * @param start
     * @param end
     *
     * Helper method for sort: runs quicksort algorithm on array from [start:end)
     */
    private static void quickSort(int[] arr, int start, int end) {
        if (end - start < 2) {
            return;
        }
        int[] p = partition(arr, start, end);
        quickSort(arr, start, p[0]);
        quickSort(arr, p[1], end);
    }

    /**
     * @param arr
     * @param start
     * @param end
     *
     * Partition the array in-place following the 3-scan partitioning scheme.
     * You may assume that first item is always selected as the pivot.
     * 
     * Returns a length-2 int array of indices:
     * [end index of "less than" section, start index of "greater than" section]
     *
     * Most of the code for quicksort is in this function
     */
    private static int[] partition(int[] arr, int start, int end) {
        int[] p = new int[2];
        int[] copy = Arrays.copyOfRange(arr, start, end);
        int index = start;

        for (int i = 0; i < copy.length; i++) {
            if (copy[i] < copy[0]) {
                arr[index] = copy[i];
                index++;
            }
        }
        p[0] = index;

        for (int i = 0; i < copy.length; i++) {
            if (copy[i] == copy[0]) {
                arr[index] = copy[i];
                index++;
            }
        }
        p[1] = index;

        for (int i = 0; i < copy.length; i++) {
            if (copy[i] > copy[0]) {
                arr[index] = copy[i];
                index++;
            }
        }
        return p;
    }
}   
