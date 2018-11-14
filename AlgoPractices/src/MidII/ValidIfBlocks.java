package MidII;

import java.util.ArrayList;
import java.util.List;

public class ValidIfBlocks {

    public static void validIfBlocks(int n) {
        if (n < 0) {
            return;
        }
        List<String> result = new ArrayList<>();
        helper(n, 0, 0,  result);
    }
    private static void helper(int n, int left, int right, List<String> result) {
        if (left == n && right == n) {
            //print current result
            System.out.println("------ A possible valid combination: -------");
            for (String s : result) {
                System.out.println(s);
            }
            return;
        }

        //StringBuilder prefix = new StringBuilder(); // used to combine indent and parenthesis together

        //put "if {"
        if (left < n) {
            StringBuilder prefix = new StringBuilder();
            //add indent, indent# = left - right
            for (int i = 0; i < left - right; i++) {
                prefix.append("  ");
            }
            result.add(prefix.append("if {").toString());
            helper(n, left + 1, right, result);
            result.remove(result.size() - 1);
        }

        //prefix.setLength(0);
        if (right < left) {
            StringBuilder prefix = new StringBuilder();
            //add indent
            for (int i = 0; i < left - right - 1; i++) { // note: right indent should - 1(get back, not get deep， current is indent to go deep)
                prefix.append("  ");
            }
            result.add(prefix.append("}").toString());
            helper(n, left, right + 1, result);
            result.remove(result.size() - 1);
        }
    }

    public static void main(String[] args) {
        ValidIfBlocks.validIfBlocks(3);
    }
}
//time: DFS O(2^2n) 2n levels, each level has 2 branches (if there is no cut)
//space: worst case O(n^2) total recursion levels
//StringBuilder append n 个 为 O(n)