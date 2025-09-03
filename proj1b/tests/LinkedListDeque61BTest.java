import deque.Deque61B;
import deque.LinkedListDeque61B;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/**
 * Performs some basic linked list tests.
 */
public class LinkedListDeque61BTest {

    @Test
    /** In this test, we have three different assert statements that verify that addFirst works correctly. */
    public void addFirstTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addFirst("back"); // after this call we expect: ["back"]
        assertThat(lld1.toList()).containsExactly("back").inOrder();

        lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
        assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

        lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.addFirst("back"); // after this call we expect: ["front"]
        lld1.addFirst("middle"); // after this call we expect: ["front", "middle"]
        lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

        /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
           to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
           but not ["front", "middle", "back"].
         */
        for (String s : lld1) {
            System.out.println(s);
        }
    }

    @Test
    /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
     *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
    public void addLastTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addLast("front"); // after this call we expect: ["back"]
        assertThat(lld1.toList()).containsExactly("front").inOrder();

        lld1.addLast("middle"); // after this call we expect: ["middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle").inOrder();

        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

        lld1.removeLast();
        lld1.removeLast();
        lld1.removeLast();
        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

        System.out.println(lld1);
    }

    @Test
    /** This test performs interspersed addFirst and addLast calls. */
    public void addFirstAndAddLastTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

        assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
    }

    // Below, you'll write your own tests for LinkedListDeque61B.
    @Test
    public void isEmptyTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertWithMessage("an empty list is empty").that(lld1.isEmpty()).isTrue();

        lld1.addFirst(0);
        assertWithMessage("list {0} is not empty").that(lld1.isEmpty()).isFalse();
    }

    @Test
    public void sizeTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertWithMessage("an empty list has size 0").that(lld1.size()).isEqualTo(0);

        lld1.addFirst(0);
        assertWithMessage("list {0} has size 1").that(lld1.size()).isEqualTo(1);

        Deque61B<String> lld2 = new LinkedListDeque61B<>();
        assertWithMessage("an empty list has size 0").that(lld2.size()).isEqualTo(0);

        lld2.addLast("sheep");
        assertWithMessage("list {'sheep'} has size 1").that(lld2.size()).isEqualTo(1);

        lld2.addFirst("rabbit");
        assertWithMessage("list {'rabbit','sheep'} has size 2").that(lld2.size()).isEqualTo(2);

        lld2.removeFirst();
        lld2.removeLast();
        assertWithMessage("list {} has size 0").that(lld2.size()).isEqualTo(0);

        lld2.removeFirst();
        assertWithMessage("list {} has size 0").that(lld2.size()).isEqualTo(0);

        lld2.removeLast();
        assertWithMessage("list {} has size 0").that(lld2.size()).isEqualTo(0);
    }

    @Test
    public void getTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst(1);
        lld1.addLast(2);
        lld1.addLast(3);
        assertWithMessage("index 1 has value 1").that(lld1.get(1)).isEqualTo(2);
        assertWithMessage("negative index").that(lld1.get(-1)).isEqualTo(null);
        assertWithMessage("out of bounds index").that(lld1.get(17)).isEqualTo(null);
    }

    @Test
    public void getRecursiveTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst(1);
        lld1.addLast(2);
        lld1.addLast(3);
        assertWithMessage("index 1 has value 1").that(lld1.getRecursive(1)).isEqualTo(2);
        assertWithMessage("negative index").that(lld1.getRecursive(-1)).isEqualTo(null);
        assertWithMessage("out of bounds index").that(lld1.getRecursive(17)).isEqualTo(null);
    }

    @Test
    public void removeFirstTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addFirst("back"); // after this call we expect: ["back"]
        lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
        lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
        String s1 = lld1.removeFirst();
        assertThat(s1).isEqualTo("front");
        assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

        String s2 = lld1.removeFirst();
        assertThat(s2).isEqualTo("middle");
        assertThat(lld1.toList()).containsExactly("back").inOrder();

        String s3 = lld1.removeFirst();
        assertThat(s3).isEqualTo("back");
        assertThat(lld1.toList()).isEmpty();
    }

    @Test
    public void removeLastTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addLast("front"); // after this call we expect: ["back"]
        lld1.addLast("middle"); // after this call we expect: ["middle", "back"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        String s1 = lld1.removeLast();
        assertThat(s1).isEqualTo("back");
        assertThat(lld1.toList()).containsExactly("front", "middle").inOrder();

        String s2 = lld1.removeLast();
        assertThat(s2).isEqualTo("middle");
        assertThat(lld1.toList()).containsExactly("front").inOrder();

        String s3 = lld1.removeLast();
        assertThat(s3).isEqualTo("front");
        assertThat(lld1.toList()).isEmpty();
    }

    @Test
    public void toListTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.toList()).isEmpty();

        lld1.addFirst(2);
        lld1.addLast(3);
        lld1.addFirst(1);
        assertThat(lld1.toList()).containsExactly(1, 2, 3).inOrder();
    }

    @Test
    public void addLastTestBasicWithoutToList() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1).containsExactly("front", "middle", "back");
    }

    @Test
    public void testEqualDeques61B() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        Deque61B<String> lld2 = new LinkedListDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");

        assertThat(lld1).isEqualTo(lld2);
    }
}
