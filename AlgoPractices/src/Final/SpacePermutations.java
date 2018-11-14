package Final;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string, we can insert at most one empty space between each pair of adjacent letters.
 * Print all permutations of strings after insertions of empty spaces.
 *
 * Input: str = "ABC"
 * Output: ABC  AB_C  A_BC  A_B_C
 */

//DFS important: recursion 出来时的状态 和 进去的状态 要保持一致！
//Time: ()
public class SpacePermutations {
    public static List<String> allP(String input) {
        //Assumption: input is not null
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        DFSHelper1(input, 0, sb, result);
        return result;
    }
    //Way 1:
    private static void DFSHelper1(String input, int index, StringBuilder sb, List<String> result) {
        if (index == input.length()) {
            result.add(sb.toString());
            return;
        }

        sb.append(input.charAt(index));

        //add space
        if (index < input.length() - 1) {
            sb.append(' ');
            DFSHelper1(input, index + 1, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }


        //not add space
        DFSHelper1(input, index + 1, sb, result);
        sb.deleteCharAt(sb.length() - 1);

    }

    // Way2:
    private static void DFSHelper2(String input, int index, StringBuilder sb, List<String> result) {
        if (index == input.length()) {
            result.add(sb.toString());
            return;
        }

        //add space
        if (index < input.length() - 1) {
            sb.append(input.charAt(index));
            sb.append(' ');
            DFSHelper2(input, index + 1, sb, result);
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
        }

        //not add space
        sb.append(input.charAt(index));
        DFSHelper2(input, index + 1, sb, result);
        sb.deleteCharAt(sb.length() - 1);

    }

    public static void main(String[] args) {
        System.out.println(SpacePermutations.allP("ABC"));

    }
}
