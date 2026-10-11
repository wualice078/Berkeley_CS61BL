import java.util.Scanner;

/**
 * Find the smallest missing positive integer in an unsorted array.
 * Runs in O(n) time and uses O(1) extra space via cyclic placement:
 * place each value x in [1, n] at index x - 1, then scan for the first gap.
 */
public class SmallestMissingPositive {

    /**
     * Returns the smallest positive integer not present in orderNumbers.
     * Mutates the array in place to achieve O(1) extra space.
     */
    public static int firstMissingPositive(int[] orderNumbers) {
        int n = orderNumbers.length;

        // Place each value x in range [1, n] at index x - 1.
        int i = 0;
        while (i < n) {
            int x = orderNumbers[i];
            if (x >= 1 && x <= n && orderNumbers[x - 1] != x) {
                int tmp = orderNumbers[x - 1];
                orderNumbers[x - 1] = x;
                orderNumbers[i] = tmp;
            } else {
                i++;
            }
        }

        // First index whose value is not i + 1 is the answer.
        for (i = 0; i < n; i++) {
            if (orderNumbers[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] orderNumbers = new int[n];
        for (int i = 0; i < n; i++) {
            orderNumbers[i] = sc.nextInt();
        }
        sc.close();
        System.out.println(firstMissingPositive(orderNumbers));
    }
}
