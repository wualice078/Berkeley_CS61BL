import deque.ArrayDeque61B;

import deque.Deque61B;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

    @Test
    @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
    void noNonTrivialFields() {
        List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                .toList();

        assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
    }

    @Test
    public void addFirstTest() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();

        ad1.addFirst("back"); // after this call we expect: ["back"]
        assertThat(ad1.toList()).containsExactly("back").inOrder();

        ad1.addFirst("middle"); // after this call we expect: ["middle", "back"]
        assertThat(ad1.toList()).containsExactly("middle", "back").inOrder();

        ad1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
        assertThat(ad1.toList()).containsExactly("front", "middle", "back").inOrder();

        ad1.addFirst("7"); // after this call we expect: ["front"]
        ad1.addFirst("6"); // after this call we expect: ["front", "middle"]
        ad1.addFirst("5"); // after this call we expect: ["front", "middle", "back"]
        ad1.addFirst("4");
        ad1.addFirst("3");
        ad1.addFirst("2");
        ad1.addFirst("1");
        assertThat(ad1.toList()).containsExactly("1", "2", "3", "4", "5", "6", "7", "front", "middle", "back").inOrder();

        for (int i = 0; i < 10; i++) {
            ad1.removeFirst();
        }

        ad1.addFirst("back"); // after this call we expect: ["front"]
        ad1.addFirst("middle"); // after this call we expect: ["front", "middle"]
        ad1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
        assertThat(ad1.toList()).containsExactly("front", "middle", "back").inOrder();

            /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
           to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
           but not ["front", "middle", "back"].
         */
        for (String s : ad1) {
            System.out.println(s);
        }
    }


    @Test
    public void addLastTest() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();

        ad1.addLast("front"); // after this call we expect: ["back"]
        assertThat(ad1.toList()).containsExactly("front").inOrder();

        ad1.addLast("middle"); // after this call we expect: ["middle", "back"]
        assertThat(ad1.toList()).containsExactly("front", "middle").inOrder();

        ad1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(ad1.toList()).containsExactly("front", "middle", "back").inOrder();

        ad1.addLast("7"); // after this call we expect: ["front"]
        ad1.addLast("6"); // after this call we expect: ["front", "middle"]
        ad1.addLast("5"); // after this call we expect: ["front", "middle", "back"]
        ad1.addLast("4");
        ad1.addLast("3");
        ad1.addLast("2");
        ad1.addLast("1");
        assertThat(ad1.toList()).containsExactly("front", "middle", "back", "7", "6", "5", "4", "3", "2", "1").inOrder();

        for (int i = 0; i < 10; i++) {
            ad1.removeFirst();
        }

        ad1.addLast("front"); // after this call we expect: ["front"]
        ad1.addLast("middle"); // after this call we expect: ["front", "middle"]
        ad1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(ad1.toList()).containsExactly("front", "middle", "back").inOrder();

        System.out.println(ad1);
    }

    @Test
    public void removeFirstTest() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();

        ad1.addFirst("back"); // after this call we expect: ["back"]
        ad1.addFirst("middle"); // after this call we expect: ["middle", "back"]
        ad1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
        String s1 = ad1.removeFirst();
        assertThat(s1).isEqualTo("front");
        assertThat(ad1.toList()).containsExactly("middle", "back").inOrder();

        String s2 = ad1.removeFirst();
        assertThat(s2).isEqualTo("middle");
        assertThat(ad1.toList()).containsExactly("back").inOrder();

        String s3 = ad1.removeFirst();
        assertThat(s3).isEqualTo("back");
        assertThat(ad1.toList()).isEmpty();

        Deque61B<Integer> ad2 = new ArrayDeque61B<>();
        for (int i = 17; i >= 0; i--) {
            ad2.addFirst(i);
        }
        for (int i = 0; i < 15; i++) {
            ad2.removeFirst();
        }
    }

    @Test
    public void removeLastTest() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();

        ad1.addLast("front"); // after this call we expect: ["back"]
        ad1.addLast("middle"); // after this call we expect: ["middle", "back"]
        ad1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        String s1 = ad1.removeLast();
        assertThat(s1).isEqualTo("back");
        assertThat(ad1.toList()).containsExactly("front", "middle").inOrder();

        String s2 = ad1.removeLast();
        assertThat(s2).isEqualTo("middle");
        assertThat(ad1.toList()).containsExactly("front").inOrder();

        String s3 = ad1.removeLast();
        assertThat(s3).isEqualTo("front");
        assertThat(ad1.toList()).isEmpty();

        Deque61B<Integer> ad2 = new ArrayDeque61B<>();
        for (int i = 0; i <= 17; i++) {
            ad2.addLast(i);
        }
        for (int i = 0; i < 15; i++) {
            ad2.removeLast();
        }
    }

    @Test
    public void getTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        ad1.addFirst(1);
        ad1.addLast(2);
        ad1.addLast(3);
        assertWithMessage("index 1 has value 1").that(ad1.get(1)).isEqualTo(2);
        assertWithMessage("negative index").that(ad1.get(-1)).isEqualTo(null);
        assertWithMessage("out of bounds index").that(ad1.get(17)).isEqualTo(null);
    }

    @Test
    public void isEmptyTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        assertWithMessage("an empty list is empty").that(ad1.isEmpty()).isTrue();

        ad1.addFirst(0);
        assertWithMessage("list {0} is not empty").that(ad1.isEmpty()).isFalse();
    }

    @Test
    public void sizeTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        assertWithMessage("an empty list has size 0").that(ad1.size()).isEqualTo(0);

        ad1.addFirst(0);
        assertWithMessage("list {0} has size 1").that(ad1.size()).isEqualTo(1);

        Deque61B<String> ad2 = new ArrayDeque61B<>();
        assertWithMessage("an empty list has size 0").that(ad2.size()).isEqualTo(0);

        ad2.addLast("sheep");
        assertWithMessage("list {'sheep'} has size 1").that(ad2.size()).isEqualTo(1);

        ad2.addFirst("rabbit");
        assertWithMessage("list {'rabbit','sheep'} has size 2").that(ad2.size()).isEqualTo(2);

        ad2.removeFirst();
        ad2.removeLast();
        assertWithMessage("list {} has size 0").that(ad2.size()).isEqualTo(0);

        ad2.removeFirst();
        assertWithMessage("list {} has size 0").that(ad2.size()).isEqualTo(0);

        ad2.removeLast();
        assertWithMessage("list {} has size 0").that(ad2.size()).isEqualTo(0);
    }

    @Test
    public void toListTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();

        assertThat(ad1.toList()).isEmpty();

        ad1.addFirst(2);
        ad1.addLast(3);
        ad1.addFirst(1);
        assertThat(ad1.toList()).containsExactly(1, 2, 3).inOrder();
    }

    @Test
    public void addLastTestBasicWithoutToList() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();

        ad1.addLast("front"); // after this call we expect: ["front"]
        ad1.addLast("middle"); // after this call we expect: ["front", "middle"]
        ad1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(ad1).containsExactly("front", "middle", "back");
    }

    @Test
    public void testEqualDeques61B() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();
        Deque61B<String> ad2 = new ArrayDeque61B<>();

        ad1.addLast("front");
        ad1.addLast("middle");
        ad1.addLast("back");

        ad2.addLast("front");
        ad2.addLast("middle");
        ad2.addLast("back");

        assertThat(ad1).isEqualTo(ad2);
    }
}
