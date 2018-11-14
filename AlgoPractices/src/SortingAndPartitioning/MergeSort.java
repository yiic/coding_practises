package SortingAndPartitioning;

public class MergeSort {

    public int[] mergeSort(int[] array) {
        if (array == null || array.length == 0) return array;
        return mergeSortHelper(array, 0, array.length - 1);
    }

    private int[] mergeSortHelper(int[] array, int start, int end) {
        if (start == end) {
            return new int[]{array[start]};
        }

        int mid = start + (end - start) / 2;
        int[] left = mergeSortHelper(array, start, mid);
        int[] right = mergeSortHelper(array, mid + 1, end);

        return merge(left, right);
    }

    private int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length && k < result.length) {
            if (left[i] <= right[j]) {
                result[k] = left[i];
                i++;
            } else {
                result[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < left.length && k < result.length) {
            result[k] = left[i];
            i++;
            k++;
        }
        while (j < right.length && k < result.length) {
            result[k] = right[j];
            j++;
            k++;
        }
        return result;
    }

}
