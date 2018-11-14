package MidII;

public class MinCutsOfPalindromePartitions {
    public int minCuts(String input) {
        //Assumption: the given string is not null
        if (input.length() == 0) return 0;

        //M[i] represent the min cut when length == i
        int[] M = new int[input.length() + 1];
        M[0] = 0;
        M[1] = 0;

        //loop over every length of the string
        for (int i = 2; i <= input.length(); i++) {
            M[i] = i - 1; //initial max possible cut number
            //loop over each length of the left substring(left cut part)
            for (int j = 1; j < i; j++) {
                boolean isPal = isPalindrome(input.substring(j - 1, i)); //time:O(n)
                if (isPal) {
                    M[i] = Math.min(M[i], M[j - 1] + 1);
                }
            }
        }
        return M[input.length()];
    }

    //Time O(n) --> can optimized to O(1) by using O(n^2) space
    private boolean isPalindrome(String s) {
        //assumption: if length == 0, is a p
        if(s.length() == 0) return true;
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1- i)) return false;
        }
        return true;
    }

//Time: O(n^3) --- 2 nested for loops
//Space: O(n) --- int[] M
}
