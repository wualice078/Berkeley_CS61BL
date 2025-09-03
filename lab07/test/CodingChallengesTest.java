import org.junit.Test;

import static com.google.common.truth.Truth.assertWithMessage;

public class CodingChallengesTest {

    @Test
    public void testMissingNumber() {
	    int [] arr1 = new int[]{1,3,4,2,6,5,9,7,0};
        assertWithMessage("missing number in 0-9 arr is 8").that(CodingChallenges.missingNumber(arr1)).isEqualTo(8);
        int [] arr2 = new int[]{1};
        assertWithMessage("missing number in 0-1 arr is 0").that(CodingChallenges.missingNumber(arr2)).isEqualTo(0);
    }

    @Test
    public void testIsPermutation() {
	    String s1 = "";
        String s2 = "";
        assertWithMessage("two empty strings are permutations of each other").that(CodingChallenges.isPermutation(s1,s2)).isEqualTo(true);
        s1 = "car";
        s2 = "rat";
        assertWithMessage("car and rat are not permutations of each other").that(CodingChallenges.isPermutation(s1,s2)).isEqualTo(false);
        s1 = "vampire";
        s2 = "chapstick";
        assertWithMessage("vampire and chapstick are not permutations of each other").that(CodingChallenges.isPermutation(s1,s2)).isEqualTo(false);
        s1 = "taco cat";
        s2 = "taco cat";
        assertWithMessage("taco cat and taco cat are permutations of each other").that(CodingChallenges.isPermutation(s1,s2)).isEqualTo(true);
        s1 = " ";
        s2 = "";
        assertWithMessage("empty string and space are not permutations of each other").that(CodingChallenges.isPermutation(s1,s2)).isEqualTo(false);
        s1 = "mermaid tail";
        s2 = "tail mermaid";
        assertWithMessage("mermaid tail and tail mermaid are permutations of each other").that(CodingChallenges.isPermutation(s1,s2)).isEqualTo(true);
    }
}
