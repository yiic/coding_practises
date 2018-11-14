package StringII;

/**
 * String Compress: “abcccdeee” → “ab1c3de3” 总体shorter，局部longer
 *                  "abcd" → "a1b1c1d1" longer
 *
 * *********************
 * Restriction: in-place
 * *********************
 * Solution:
 *          1. First scan: slow fast pointer --> only copy parts that become same size or shorter size, ignore encoding occurrence == 1
 *             In the meantime: count how many time the pattern become longer (occurrence == 1) appears.
 *
 *          2. Second scan: extend length, and do encoding backwards!! (from right). //much details in code
 *              still two pointers pointing to each input array and result array[].
 *
 *
 * APIs:
 *      boolean Character.isDigit(in[i];
 *      int(0 - 9) to char:  result[j] = (char)(1 + '0');
 *      int(>10) to char[]:  char[] num = Integer.toString(count).toCharArray();
 *
 */

public class StringEncoding {
    public static String compress(String input) {
        //Assumption: input is not null
        if (input.length() == 0) return input;
        char[] in = input.toCharArray();

        // first scan
        int slow = 0; // exclusive
        int fast = 0;
        int single = 0; // count of char that occurrence = 1
        while (fast < in.length) { //each iteration: encode one char
            int count = 0;
            //encode char: fast is at a new char right now
            in[slow] = in[fast];
            slow++;
            // count occurrence
            while (fast < in.length && in[fast] == in[slow - 1]) {
                count++;
                fast++;
            }
            //encode occurrence if > 1, now: in[fast] != in[slow]
            if (count > 1) {
                char[] num = Integer.toString(count).toCharArray();
                for (int i = 0; i < num.length; i++) {
                    in[slow] = num[i];
                    slow++;
                }
            } else {
                single++;
            }

        }
        //return new String(in, 0, slow);

        // second scan (assign '1' when occurrence == 1), backwards
        char[] result = new char[slow + single];
        int i = slow - 1; // input index
        int j = result.length - 1; // result index
        while (j >= 0 && i >= 0) {
            boolean curIsDigi = Character.isDigit(in[i]);
            //boolean lastIsDigi = Character.isDigit(in[i + 1]); //not use this line
            if (j >= 0 && i >= 0 && ((i == slow - 1 && !curIsDigi) || (!curIsDigi && !Character.isDigit(in[i + 1])))) {
                result[j] = (char)(1 + '0');
                result[j - 1] = in[i];
                j -= 2;
                i--;
            } else if (j >= 0 && i >= 0){
                result[j--] = in[i--];
            }
        }
        return new String(result);



    }
    public static void main(String[] args) {
        System.out.println(StringEncoding.compress("abcdef"));
        System.out.println(StringEncoding.compress("aaaaaaaaabcdeeee"));
        System.out.println(StringEncoding.compress("aaaaaaaaaaaaaa"));
    }
}
