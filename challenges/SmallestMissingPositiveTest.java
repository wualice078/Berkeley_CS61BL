public class SmallestMissingPositiveTest {

    private static int passed = 0;
    private static int failed = 0;

    private static void assertEquals(int expected, int actual, String name) {
        if (expected == actual) {
            passed++;
            System.out.println("PASS: " + name);
        } else {
            failed++;
            System.out.println("FAIL: " + name + " expected=" + expected + " actual=" + actual);
        }
    }

    public static void main(String[] args) {
        assertEquals(2, SmallestMissingPositive.firstMissingPositive(new int[]{3, 4, -1, 1}),
                "example [3,4,-1,1] -> 2");
        assertEquals(1, SmallestMissingPositive.firstMissingPositive(new int[]{2, 3, 4}),
                "missing 1");
        assertEquals(3, SmallestMissingPositive.firstMissingPositive(new int[]{1, 2, 0}),
                "[1,2,0] -> 3");
        assertEquals(1, SmallestMissingPositive.firstMissingPositive(new int[]{}),
                "empty -> 1");
        assertEquals(2, SmallestMissingPositive.firstMissingPositive(new int[]{1}),
                "[1] -> 2");
        assertEquals(1, SmallestMissingPositive.firstMissingPositive(new int[]{-5, -2, 0}),
                "all non-positive -> 1");
        assertEquals(5, SmallestMissingPositive.firstMissingPositive(new int[]{1, 2, 3, 4}),
                "complete [1..4] -> 5");
        assertEquals(2, SmallestMissingPositive.firstMissingPositive(new int[]{1, 1, 1, 1}),
                "duplicates of 1 -> 2");
        assertEquals(4, SmallestMissingPositive.firstMissingPositive(new int[]{7, 8, 9, 11, 12, 1, 2, 3}),
                "gap at 4");

        System.out.println();
        System.out.println(passed + " passed, " + failed + " failed");
        if (failed > 0) {
            System.exit(1);
        }
    }
}
