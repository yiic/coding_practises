package StringII;

/*
  Right shift a given string by n characters.

  Assumption: The given string is not null. n >= 0

  preprocess: toCharArray();
  postprocess: return new String(result char[]);

  Solution:
    step 1: reverse whole part
    step 2: reverse frist n part and last length - n part

    order problem: because right shift, watch out the order of step 1 and step 2!

 */

public class ShiftRightByNChar {
    public static String rightShift(String input, int n) {
        if (input .length() == 0) return input;
        char[] in = input.toCharArray();

        n = n % in.length;
        reverse(in, 0, in.length - 1);
        reverse(in, 0, n - 1);
        reverse(in, n, in.length - 1);

        return new String(in);
    }

    public static void reverse(char[] array, int a, int b) {
        while(a < b) {
            char temp = array[a];
            array[a] = array[b];
            array[b] = temp;
            a++;
            b--;
        }
    }

    // main ********************************************************************
    public static void main(String[] args) {
        System.out.println(ShiftRightByNChar.rightShift("abcdefg", 0));
        System.out.println(ShiftRightByNChar.rightShift("abcdefg", 2));
        System.out.println(ShiftRightByNChar.rightShift("abcdefg", 4));
        System.out.println(ShiftRightByNChar.rightShift("abcdefg", 9));
    }
}

/*
    Bugs Analysis:

        1: because it is right shift, the order of step 1 and step 2 matter

        2. don't forget n = n % in.length;
 */


