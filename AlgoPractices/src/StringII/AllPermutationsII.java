package StringII;
/**
 * Problem: Given a string with possible duplicate characters, return a list with all permutations of the characters.
 * Example: Set = “abc”, all permutations are [“abc”, “acb”, “bac”, “bca”, “cab”, “cba”]
 *          Set = "aba", all permutations are ["aab", "aba", "baa"] --> has duplicates
 *          Set = "", all permutations are [""]
 *          Set = null, all permutations are []
 *
 * Key idea: DFS, de-duplication
 * Data Structure: recursion, HashSet,
 * Solution:
 *          Base case: done all levels, index = input.length
 *
 *          Each level: 1. current position put a candidate letter 每个都到当前的位置上 坐一坐 swap swap
 *                         (for loop on rest letters: swap swap on char[], second swap is after recursion call: swap back the original state)
 *
 *                      2. De-duplication: use HashSet (inside of the helper function) 剩下的字母里面如果有重复，只call一次recursion
 *
 *          How many level: input.length (input.length - 1 在做最后一层，做完到input.length 放到 List 里)
 *
 * Compare to AllPermutationI:
 *      Inside helper recursion function, use a HashSet to do de-duplication (剩下的字母里面如果有重复，只call一次recursion)
 *
 * Time:
 *
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllPermutationsII {
    public static List<String> permutation(String set) {
        List<String> result = new ArrayList<>();
        // conner case
        if (set == null || set.length() == 0) return result;

        char[] input = set.toCharArray();
        dfsRecursion(result, 0, input);
        return result;
    }
    private static void dfsRecursion(List<String> result, int index, char[] input) {
        // base case
        if (index == input.length) {
            result.add(new String(input));
            return;
        }
        Set<Character> occur = new HashSet<>();
        for (int i = index; i < input.length; i++) {
            if (occur.add(input[i])) { // boolean add(e)  -- if HashSet already have e, return false
                swap(input, index, i);
                dfsRecursion(result, index + 1, input);
                swap(input, index, i);
            }
        }
    }

    private static void swap(char[] array, int a, int b) {
        char temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static void main(String[] args) {
        List<String> output = AllPermutationsII.permutation("aba");
        for (String s : output) {
            System.out.println(s);
        }
    }

}
