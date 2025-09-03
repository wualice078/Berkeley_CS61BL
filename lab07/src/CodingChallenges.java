import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class CodingChallenges {

    /**
     * Return the missing number from an array of length N containing all the
     * values from 0 to N except for one missing number.
     */
    public static int missingNumber(int[] values) {
        Set<Integer> seen = new HashSet<>();
        for(int i = 0; i < values.length; i++) {
            seen.add(values[i]);
        }
        for(int i = 0; i <= seen.size(); i++) {
            if(!seen.contains(i))
                return i;
        }
        return -1;
    }

    /**
     * Returns true if and only if s1 is a permutation of s2. s1 is a
     * permutation of s2 if it has the same number of each character as s2.
     */
    public static boolean isPermutation(String s1, String s2) {
        if(s2.length() != s1.length()) {
            return false;
        }
        Map<Character, Integer> c1 = new HashMap<>();
        Map<Character, Integer> c2 = new HashMap<>();
        for(int i = 0; i < s1.length(); i++) {
            if(c1.containsKey(s1.charAt(i))) {
                c1.replace(s1.charAt(i),c1.get(s1.charAt(i))+1);
            }
            else {
                c1.put(s1.charAt(i),1);
            }
        }
        for(int i = 0; i < s2.length(); i++) {
            if(c2.containsKey(s2.charAt(i))) {
                c2.replace(s2.charAt(i),c2.get(s2.charAt(i))+1);
            }
            else {
                c2.put(s2.charAt(i),1);
            }
        }
        if(c1.equals(c2))
            return true;
        return false;
    }
}
