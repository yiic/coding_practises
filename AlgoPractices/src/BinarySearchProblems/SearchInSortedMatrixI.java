package BinarySearchProblems;

import java.util.Arrays;

public class SearchInSortedMatrixI {
    private static int[] search(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return new int[]{-1, -1};

        int row = matrix.length;
        int col = matrix[0].length;
        int start = 0, end = row * col - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            int x = mid / col;
            int y = mid % col;
            if (target == matrix[x][y]) {
                return new int[]{x, y};
            } else if (target < matrix[x][y]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
//        int[] result = SearchInSortedMatrixI.search(new int[][]{{1, 2, 3}, {3, 4, 5}, {6, 7, 9}}, 6);
//        for (int i : result) {
//            System.out.println(i);
//        }
        System.out.println(Arrays.toString(SearchInSortedMatrixI.search(new int[][]{{1, 2, 3}, {3, 4, 5}, {6, 7, 9}}, 6)));
    }

}
