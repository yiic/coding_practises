package StringII;
/*
  Reverse the words in a sentence.
  For example:
            “I love Google” → “Google love I”

  Assumptions:
    1. Words are separated by single space
    2. There are no heading or tailing white spaces


  Preprocess: toCharArray()
  Postprocess: return new String(result char[])

  Solution:
    Step 1: reverse whole part (call helper reverse fun)
    Step 2: reverse each word (use two pointers to find the start and end index of each word)

    The order of Step 1 and 2 can be reversed

 */


public class ReverseWordsInSentenceI {
    public static String reverseWords(String input) {
        if (input == null || input.length() == 0) return input;
        char[] in = input.toCharArray();

        //step 1
        reverse(in, 0, in.length - 1);

        //step 2
        int start = 0;
        while (start < in.length) {
            if (in[start] != ' ') {
                int end = start;
                while (end < in.length && in[end] != ' ') {
                    end++;
                }
                reverse(in, start, end - 1); //must be end - 1
                start = end;
            } else {
                start++;
            }
        }
        return new String(in);

    }

    public static void reverse(char[] in, int a, int b) {
        while(a < b) {
            char temp = in[a];
            in[a] = in[b];
            in[b] = temp;
            a++;
            b--;
        }
    }

    // main *******************************************
    public static void main(String[] args) {
        System.out.println(ReverseWordsInSentenceI.reverseWords("I love Google"));
    }

}

/*
 Bugs Analysis:

 1. line 38: the order of the two statements int while (end < in.length && in[end] != ' '); should not be reversed:
    (in[end] != ' ' && end < in.length) --> wrong! ArrayIndexOutOfBoudary!

 2. forgot line 46: start++;

 3. use while loop instead of for loop

 */
