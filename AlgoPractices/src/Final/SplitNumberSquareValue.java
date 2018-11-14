package Final;

/**
 * How to cut/split the number into a minimum number of items such that each item is equal to an integer's square value.
 * For example
 *
 * 4 can be split to 1+1+1+1 (4 items) or 2^2 (1 item, which is the solution)
 * Return 1
 * 10 can be split to two items 3^2 + 1^2 (solution) or four items 2^2 + 2^2 + 1^2 +1^2
 * Return 2
 *
 */

public class SplitNumberSquareValue {
    // 右小段 (j*j) + 左大段 (i - j*j)  Time: O(n * n^0.5) = O(n^1.5) Space: O(n)
    public int minCut(int num) {
        if (num < 0) return 0;

        int[] M = new int[num + 1];
        M[0] = 0;

        for (int i = 1; i < M.length; i++) {
            M[i] = i; //max is i: divided into i number of 1s
            for (int j = 1; j * j <= i; j++) { //右小段
                M[i] = Math.min(M[i], M[i - j * j] + 1); // M[i - j * j] 查表，j*j手动设置为Integer's square value
            }
        }
        return M[num];
    }


    //左大段 + 右大段 Time: O(n^2) Space: O(n)
    public int minCut2(int num) {
        if (num < 0) return 0;

        int[] M = new int[num + 1];
        M[0] = 0;
        for (int i = 1; i < M.length; i++) {
            M[i] = i; //max is i: divided into i number of 1s
            for (int j = 1; j <= i; j++) { //右小段
                if (i == j * j) {
                    M[i] = 1;
                    break;
                }
                M[i] = Math.min(M[i], M[j] + M[i - j] + 1); // M[i - j * j] 查表，j*j手动设置为Integer's square value
            }
        }
        return M[num];
    }
}
