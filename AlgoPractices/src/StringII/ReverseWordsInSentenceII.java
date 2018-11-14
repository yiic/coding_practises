package StringII;

import java.util.Arrays;

/**
 * ReverseWordsInSentenceI + remove spaces
 */

public class ReverseWordsInSentenceII {
    public static String reverseWords(String input) {
        //If the given string is null, we do not need to do anything.
        if (input == null || input.length() == 0) return input;

        char[] in = input.toCharArray();
        //step1: truncate all heading/trailing/duplicate spaces
        //interval we need: from [0 to slow) inclusive!!
        int slow = -1;
        int fast = 0;
        for (; fast < in.length; fast++) {
            if (in[fast] == ' ' && (fast == 0 || in[fast - 1] == ' ')) {
                continue;
            }
            slow++;
            in[slow] = in[fast];
            //slow++;
        }

        //System.out.println("after for loop, array is like" + "[" + Arrays.toString(in) + "]");
        //System.out.println("slow = " + slow);
        //  after for loop, array is like[[I,  , l, o, v, e,  , G, o, o, g, l, e,  , g, l, e,  ,  ,  ,  ]]
        //  slow = 14, 'g' --> would change even if post-processing

        //post-processing: slow will always stop 1 more position
        if (slow > 0 && in[slow] == ' ') {
            slow--;
        }

        //step2: find each word and do reverse
            int i = 0;
            while (i <= slow) {
                int start = i;
                while (i <= slow && in[i] != ' ') {
                    i++;
                }
                if (i - 1<= slow) {
                    reverse(in, start, i - 1);
                }
                i++; //do not forget!
            }

            reverse(in, 0, slow);

        return new String(in, 0, slow + 1);

    }

    private static void reverse(char[] array, int left, int right) { //inclusive boundary left and right
        while (left < right) {
            swap(array, left, right);
            left++;
            right--;
        }
    }
    private static void swap(char[] array, int a, int b) {
        char temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static void main(String[] args) {
        System.out.println("[" + ReverseWordsInSentenceII.reverseWords("  He love   Google    ") + "]");
    }
}
