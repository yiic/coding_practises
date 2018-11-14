package StringII;
/*
   pre-process: toCharArray()
   post-process: return new String(result char[])

   Solutions：
   1. Iterative： two pointers
   2. Recursion： recursionHelper(in, a + 1, b - 1);

*/

public class ReverseAString {

    // Iterative **************************************************
    public String reverseStringIterative(String input) {

        //Assumption: the given string is not null
        if (input.length() == 0) return input;

        char[] in = input.toCharArray();
        int i = 0, j = in.length - 1;

        while (i < j) {
            swap(in, i, j);
            i++;
            j--;
        }
        return new String(in);
    }

    // Recursion **************************************************
    public String reverseStringRecursion(String input) {

        //Assumption: the given string is not null
        if (input.length() == 0) return input;

        char[] in = input.toCharArray();
        recursionHelper(in, 0, input.length() - 1);
        return new String();
    }

    public void recursionHelper(char[] in, int a, int b) {
        if (a >= b) return;
        swap(in, a, b);
        recursionHelper(in, a + 1, b - 1);
    }

    // Helper: swap ***********************************************
    public void swap(char[] in, int a, int b) {
        char temp = in[a];
        in[a] = in[b];
        in[b] = temp;
    }



}
