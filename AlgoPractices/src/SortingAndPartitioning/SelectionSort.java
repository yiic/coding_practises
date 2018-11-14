package SortingAndPartitioning;

/**
 * Time O(n^2)
 * Each iteration of the outer loop sort one value
 * Each iteration of the inner loop find the min of the rest part (then swap)
 */

public class SelectionSort {
    public int[] solve(int[] array) {
        if (array == null || array.length == 0) {
            return array;
        }

        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
        }
        return array;
    }

    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
