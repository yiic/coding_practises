package Final;

/**
 * Given an array of strings, find if all the strings can be chained to form a circle. Two string s1 and s2 can be chained, iff the last letter of s1 is identical to the first letter of s2.
 * For example,
 * “abc” and “cd” can be chained,
 * “abc” and “dz” can not be chained.
 *
 *  Example Input:
 * arr[] = {"aaa", "bbb", "baa", "aab"};
 * Output: True. The given input strings can be chained to form a circle. The strings can be chained as "aaa", "aab", "bbb" and "baa"
 */

//idea：DFS + 剪枝
// Time: O(n!) Space: O(n)
public class StringsChainToCircle {
    public static boolean formCircle(String[] words) {
        if (words == null || words.length == 0) return false;
        return DFSHelper(words, 1); // start from 1!!!
    }

    private static boolean DFSHelper(String[] words, int index) {
        //base case
        if (index == words.length) {
            return canChain(words[index - 1], words[0]);
        }
        //induction rule
        for (int i = index; i < words.length; i++) {
            if (canChain(words[index - 1], words[i])) {
                swap(words, index, i);
                if (DFSHelper(words, index + 1)) {
                    return true;
                }
                swap(words, index, i);
            }
        }
        return false;
    }

    private static void swap (String[] array, int a, int b) {
        String temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    private static boolean canChain(String s1, String s2) { // s1--s2
        return s1.charAt(s1.length() - 1) == s2.charAt(0);
    }

    public static void main(String[] args) {
        System.out.println(StringsChainToCircle.formCircle(new String[]{"aaa", "bbb", "aab", "baa"}));
    }
}

