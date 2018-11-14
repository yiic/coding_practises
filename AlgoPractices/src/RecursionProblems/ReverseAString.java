package RecursionProblems;

public class ReverseAString {
    public static String reverseString(String s) {
        char[] arr = s.toCharArray();
        reverse(arr, 0, arr.length - 1);
        return new String(arr);
    }

    private static void reverse(char[] arr, int i, int j) {
        //base case
        if (i >= j) return;

        swap(arr, i, j);

        reverse(arr, i + 1, j - 1);
    }

    private static void swap(char[] array, int a, int b) {
        char temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static void main(String[] args) {
        String result = ReverseAString.reverseString("apple");
        System.out.println(result);


    }

}
